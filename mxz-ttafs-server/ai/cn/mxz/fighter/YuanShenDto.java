package cn.mxz.fighter;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.javaplus.math.Fraction;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.YuanShenBenchSpetTemplet;
import cn.mxz.YuanShenBenchSpetTempletConfig;
import cn.mxz.YuanShenExpSpetTemplet;
import cn.mxz.YuanShenExpSpetTempletConfig;
import cn.mxz.YuanShenExpTemplet;
import cn.mxz.YuanShenExpTempletConfig;
import cn.mxz.YuanShenPlaceTemplet;
import cn.mxz.YuanShenPlaceTempletConfig;
import cn.mxz.YuanShenTemplet;
import cn.mxz.YuanShenTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.formation.AdditionType;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Sets;

import db.dao.impl.DaoFactory;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;

public class YuanShenDto {

	/**
	 * 
	 */
	private final HeroImpl hero;
	private int index;

	YuanShenDto(HeroImpl heroImpl, int index) {
		hero = heroImpl;
		this.index = index;
	}

	public int getType() {
		return hero.getDto().getYuanshenType(index);
	}

	public int getLevel() {
		return hero.getDto().getYuanshenLevel(index);
	}

	public void setLevel(int v) {
		hero.getDto().setYuanshenLevel(index, v);
	}

	public void setType(int value) {
		hero.getDto().setYuanshenType(index, value);
	}

	public void addExp(int add) {
		hero.getDto().setYuanshenExp(index,
				hero.getDto().getYuanshenExp(index) + add);
	}

	public Fraction getExp() {
		int exp = hero.getDto().getYuanshenExp(index);
		int level = getLevel();
		YuanShenExpTemplet temp = YuanShenExpTempletConfig.get(level);
		if (temp == null) {
			throw new NullPointerException("等级:" + level);
		}

		return new Fraction(exp, (int) (temp.getShadowExp() * getExpPar()));
	}

	public float getExpPar() {

		int typeId = getType();
		YuanShenTemplet t = YuanShenTempletConfig.get(typeId);
		int step = t.getStep();
		YuanShenExpSpetTemplet tt = YuanShenExpSpetTempletConfig.get(step);
		return tt.getSpetAdd();
	}

	public void addLevel(int add) {
		setLevel(getLevel() + add);
	}

	public boolean isOpen() {
		int index2 = index + 1;

		YuanShenPlaceTemplet temp = YuanShenPlaceTempletConfig.get(index2);

		if (temp == null) {
			return false;
		}

		int level = hero.getLevel();
		if (level < temp.getLevel()) {
			return false;
		}

		int type = getType();

		int lv = getLevel();

		if (YuanShenExpTempletConfig.get(lv) == null) {
			return false;
		}

		return YuanShenTempletConfig.getKeys().contains(type);
	}

	boolean needOpen() {
		int level = hero.getLevel();
		int index2 = index + 1;

		YuanShenPlaceTemplet temp = YuanShenPlaceTempletConfig.get(index2);

		if (temp == null) {
			return false;
		}

		return level >= temp.getLevel();
	}

	public void open() {

		if (isFirst()) {

			int quality = hero.getQuality();
			GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
			int step = temp.getStep();

			this.setType(getRandomType(step));

		} else {

			this.setType(getRandomInitId());
		}

		this.setLevel(1);
		commit();
	}

	/**
	 * 设置这个元神的总经验
	 * 
	 * @param all
	 */
	public void setExpAll(int all) {
		float expPar = getExpPar();
		int level = getLevel(all, expPar);
		int exp = (int) (all - YuanShenExpTempletConfig.get(level)
				.getShadowSumExp() * expPar);
		setLevel(level);
		hero.getDto().setYuanshenExp(index, exp);
		
	}

	private int getLevel(int all, float expPar) {
		List<YuanShenExpTemplet> a = YuanShenExpTempletConfig.getAll();
		for (int i = 0; i < a.size() - 1; i++) {
			YuanShenExpTemplet front = a.get(i);
			YuanShenExpTemplet behind = a.get(i + 1);

			if (isIn(all, expPar, front, behind)) {
				return front.getShadowLevel();
			}
		}
		return YuanShenExpTempletConfig.getMaxKey();
	}

	private boolean isIn(int all, float expPar, YuanShenExpTemplet front,
			YuanShenExpTemplet behind) {
		int ef = (int) (front.getShadowSumExp() * expPar);
		int eb = (int) (behind.getShadowSumExp() * expPar);
		return ef <= all && all < eb;
	}

	private int getRandomType(int step) {

		// 123 2
		// 45 3
		// 6 4

		if (Sets.newHashSet(1, 2, 3).contains(step)) {
			return getRandomId(2);
		}

		if (Sets.newHashSet(4, 5).contains(step)) {
			return getRandomId(3);
		}

		if (Sets.newHashSet(6).contains(step)) {
			return getRandomId(4);
		}

		throw new SureIllegalOperationException("品质不存在");
	}

	private int getRandomId(int step) {
		List<YuanShenTemplet> findByStep = YuanShenTempletConfig
				.findByStep(step);
		YuanShenTemplet randomOne = Util.Random.getRandomOne(findByStep);
		return randomOne.getId();
	}

	private boolean isFirst() {
		return index == 0;
	}

	public void commit() {
		DaoFactory.getNewFighterDao().update(hero.getDto());
	}

	public Integer getRandomInitId() {
		int step = getRandomStep();
		return getRandomInYuanShenConfig(step);
	}

	private Integer getRandomInYuanShenConfig(int step) {

		List<YuanShenTemplet> all = YuanShenTempletConfig.findByStep(step);

		Iterator<YuanShenTemplet> it = all.iterator();

		while (it.hasNext()) {
			YuanShenTemplet y = it.next();
			// 是否已经有这个元神了 || 法术系不能随机物理攻击, 物理系不能随机法术攻击
			if (has(y) || xxx(y.getBaseAdditionType())) {
				it.remove();
			}
		}

		WeightFetcher<YuanShenTemplet> weightAble = new WeightFetcher<YuanShenTemplet>() {

			@Override
			public Integer get(YuanShenTemplet t) {
				return t.getWeight();
			}
		};
		YuanShenTemplet t = Util.Random.getRandomOneByWeight(all, weightAble);

		return t.getId();
	}

	private boolean xxx(int yuanShenType) {
		int type = hero.getTemplet().getAttackType();
		int att = AdditionType.ATTACK.toNum();// 1
		int matt = AdditionType.MATTACK.toNum();// 2
		if (yuanShenType == att && type == matt) {
			return true;
		}
		if (yuanShenType == matt && type == att) {
			return true;
		}
		return false;
	}

	private boolean has(YuanShenTemplet y) {

		Set<Integer> s = Sets.newHashSet();

		NewFighter d = hero.getDto();

		for (int i = 0; i < NewFighterImpl.YUANSHEN_TYPE_LEN; i++) {
			int t = d.getYuanshenType(i);
			s.add(t);
		}

		return s.contains(y.getId());
	}

	private int getRandomStep() {

		UserCounter uc = hero.city.getUserCounterHistory();
		int count = uc.get(CounterKey.YUAN_SHEN_RESET);

		List<YuanShenBenchSpetTemplet> all = YuanShenBenchSpetTempletConfig
				.getAll();
		
		Collections.reverse(all);

//		Debuger.debug("YuanShenDto.getRandomStep() count = " + count  );
		for (YuanShenBenchSpetTemplet t : all) { // 必出规则
			int mustNumber = t.getMustNumber();
			if(mustNumber == -1) {
				continue;
			}
			if (0 == (count + 1) % mustNumber) {
//				Debuger.debug("YuanShenDto.getRandomStep() count = " + count + "  , step = " + t.getStep() );
				return t.getStep();
			}
		}

		WeightFetcher<YuanShenBenchSpetTemplet> weightAble = new WeightFetcher<YuanShenBenchSpetTemplet>() {

			@Override
			public Integer get(YuanShenBenchSpetTemplet t) {
				return t.getWeightSans();
			}
		};
		YuanShenBenchSpetTemplet t = Util.Random.getRandomOneByWeight(all,
				weightAble);

		int step = t.getStep();
		return step;
	}

	public int getExpAll() {
		int level = getLevel();
		YuanShenExpTemplet temp = YuanShenExpTempletConfig.get(level);
		float all = temp.getShadowSumExp() * getExpPar();
		return (int) (getExp().getNumerator() + all);
	}
}