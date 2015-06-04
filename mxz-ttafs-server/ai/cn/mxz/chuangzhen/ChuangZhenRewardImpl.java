package cn.mxz.chuangzhen;

import java.util.Iterator;
import java.util.List;

import message.S;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.CopterBuffTemplet;
import cn.mxz.CopterBuffTempletConfig;
import cn.mxz.CopterGoodsLibraryTemplet;
import cn.mxz.CopterGoodsLibraryTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.ChuangZhen;
import define.D;

public class ChuangZhenRewardImpl implements ChuangZhenReward {

	private final class WeightFetcherImplementation implements
			WeightFetcher<CopterGoodsLibraryTemplet> {
		@Override
		public Integer get(CopterGoodsLibraryTemplet t) {
			return t.getWeight();
		}
	}

	private ChuangZhenPlayerImpl	player;
	private List<CopterBuffTemplet>	additionReward	= Lists.newArrayList();
	private List<PropReward>		battleReward	= Lists.newArrayList();

	public ChuangZhenRewardImpl(ChuangZhenPlayerImpl player) {
		this.player = player;
//		generate();
	}

	@Override
	public boolean getHasBtAddition() {
		return !additionReward.isEmpty();
	}

	@Override
	public BattleAddition getBattleAddition() {
		return new BattleAdditionImpl(additionReward);
	}

	@Override
	public boolean getHasBtReward() {
		return !battleReward.isEmpty();
	}

	@Override
	public BattleReward getBattleReward() {
		return new BattleRewardImpl(battleReward, player);
	}

	public void select(int index) {

		if (getHasBtAddition()) {
			CopterBuffTemplet temp = additionReward.get(index);
			send(temp);
			additionReward.clear();

			String id = player.getId();
			City city = CityFactory.getCity(id);
			UserCounter his = city.getUserCounterHistory();

			his.mark(CounterKey.RECEIVE_ADDITION_REWARD);
		}
	}
	

	public void skip() {
		additionReward.clear();
	}

	private void send(CopterBuffTemplet temp) {
		ChuangZhen dto = player.lazyGet();
		checkStar(temp, dto);
		dto.addStarRemain(-temp.getStar());

		addAddition(temp, dto);

		DaoFactory.getChuangZhenDao().update(dto);
	}

	private void addAddition(CopterBuffTemplet temp, ChuangZhen dto) {

		// 属性类型（0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
		List<Integer> c = Util.Collection.getIntegers(temp.getFront());

		float frontPar = temp.getFrontPar();

		if (c.contains(1))
			dto.addAttack(frontPar);
		if (c.contains(3))
			dto.addDefend(frontPar);
		if (c.contains(2))
			dto.addMAttack(frontPar);
		if (c.contains(4))
			dto.addMDefend(frontPar);
		if (c.contains(5))
			dto.addSpeed(frontPar);
		if (c.contains(0))
			dto.addHp(frontPar);

//		Debuger.debug("ChuangZhenRewardImpl.addAddition() " + "att:" + dto.getAttack() + " matt:" + dto.getMAttack() + " def:" + dto.getDefend() + " mdef:" + dto.getMDefend() + " spd:" + dto.getSpeed() + " hp:" + dto.getHp());
	}

	private void checkStar(CopterBuffTemplet temp, ChuangZhen dto) {
		int rm = dto.getStarRemain();

		int need = temp.getStar();

		if (rm < need) {
			throw new OperationFaildException(S.S10221);
		}
	}

	public void receive() {
		if (getHasBtReward()) {
			for (PropReward t : getBattleReward().getPropReward()) {
				City city = CityFactory.getCity(player.getId());
				PrizeImpl p = new PrizeImpl(t.getId(), t.getCount());
				p.award(city.getPlayer());
			}
			battleReward.clear();

			String id = player.getId();
			City city = CityFactory.getCity(id);
			UserCounter his = city.getUserCounterHistory();

			his.mark(CounterKey.RECEIVE_BATTLE_REWARD) ;
		}
	}

	public void generate() {
		int nextAddition = player.getNextAddition();
		String id = player.getId();
		City city = CityFactory.getCity(id);
		UserCounter his = city.getUserCounterHistory();

		int f = player.getCurrentFloor();

		boolean mark = his.isMark(CounterKey.RECEIVE_ADDITION_REWARD);
		boolean b = nextAddition == D.CHUANG_ZHEN_ADDITION_FLOOR;
		if (!mark && f != 1 && b) {
			generateAdditionReward();
		}
		
		
		int nextReward = player.getNextReward();
		boolean mark2 = his.isMark(CounterKey.RECEIVE_BATTLE_REWARD);
		boolean needGenerate = nextReward == D.CHUANG_ZHEN_REWARD_FLOOR;
		if (!mark2 && f != 1 && needGenerate) {
			generateBattleReward();
//		} else {
//			clearBattleReward();
		}
		// Debuger.debug("ChuangZhenRewardImpl.generate() nextAddition:" +
		// nextAddition + " nextReward:" + nextReward);
	}

	private void clearBattleReward() {
		battleReward = Lists.newArrayList();
	}

	private void generateBattleReward() {
		List<CopterGoodsLibraryTemplet> all = Lists.newArrayList(CopterGoodsLibraryTempletConfig.getAll());

		filter(all);
		
		WeightFetcher<CopterGoodsLibraryTemplet> weightAble = new WeightFetcherImplementation();

		battleReward = Lists.newArrayList();

		for (int i = 0; i < 2; i++) {
			CopterGoodsLibraryTemplet t = Util.Random.getRandomOneByWeight(all, weightAble);
			String[] split = t.getAwards().split(":");
			int type = new Integer(split[0]);
			int count = Util.Random.getRandomByMinMax(split[1]);
			battleReward.add(new PropRewardImpl(type, count));
			all.remove(t);
		}

		// Debuger.debug("ChuangZhenRewardImpl.generateBattleReward()" +
		// battleReward);

	}

	private void filter(List<CopterGoodsLibraryTemplet> all) {
		Iterator<CopterGoodsLibraryTemplet> it = all.iterator();
		String id = player.getId();
		City user = CityFactory.getCity(id);
		while (it.hasNext()) {
			CopterGoodsLibraryTemplet t = it.next();
			if(!user.getFunctionOpenManager().isOpen(t.getModulesId())) {
				it.remove();
			}
		}
	}

	private void generateAdditionReward() {
		additionReward.clear();
		additionReward.add(getRandom(1));
		additionReward.add(getRandom(2));
		additionReward.add(getRandom(3));

		// Debuger.debug("ChuangZhenRewardImpl.generateAdditionReward()" +
		// additionReward);
	}

	private CopterBuffTemplet getRandom(int i) {
		List<CopterBuffTemplet> all = CopterBuffTempletConfig.findByGrade(i);
		for (CopterBuffTemplet t : additionReward) {
			removeByFront(t, all);
		}
		if(all.isEmpty()) {
			for (CopterBuffTemplet c : additionReward) {
				System.err.println("ChuangZhenRewardImpl.getRandom()" + c.getId());
			}
			throw new RuntimeException(i + "");
		}
		return Util.Collection.getRandomOne(all);
	}


	private void removeByFront(CopterBuffTemplet t, List<CopterBuffTemplet> all) {
		Iterator<CopterBuffTemplet> it = all.iterator();
		while (it.hasNext()) {
			CopterBuffTemplet c = it.next();

//			List<Integer> ns = Util.Collection.getIntegers(c.getFront());

			if (c.getFront().equals(t.getFront())) {
				it.remove();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [hasBattleAddition()=");
		builder.append(getHasBtAddition());
		builder.append(", hasBattleReward()=");
		builder.append(getHasBtReward());
		builder.append("]");
		return builder.toString();
	}


}
