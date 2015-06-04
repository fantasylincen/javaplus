package cn.mxz.loginreward;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import message.S;
import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.ContinuousEnterRewardsTemplet;
import cn.mxz.ContinuousEnterRewardsTempletConfig;
import cn.mxz.ContinuousRewardsTemplet;
import cn.mxz.ContinuousRewardsTempletConfig;
import cn.mxz.SyceeRewardsTemplet;
import cn.mxz.SyceeRewardsTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.coutinuous.ContinuousManager;
import cn.mxz.coutinuous.ContinuousType;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import define.D;

/**
 * 登陆奖励
 * @author 林岑
 *
 */
public class LoginRewardPlayerImpl implements LoginRewardPlayer {

	private City city;
	private ContinuousManager continuous;

	public LoginRewardPlayerImpl(City city) {

		this.city = city;
		continuous = city.getContinuousManager();
		continuous.doIt(ContinuousType.LOGIN_REWARD);
	}

	/**
	 * 领取登陆元宝奖励
	 */
	@Override
	public Result receiveGoldReward() {
		UserCounter his = city.getUserCounterHistory();
		int times = his.get(CounterKey.LOGIN_GOLD_REWARD_TIMES);
		int canReceiveId = times + 1;
		if (hasReceiveGoldRewardToday()) {
			return new ResultImpl(0, getNext(canReceiveId));
		}
		int gold = sendReward(canReceiveId);
		his.add(CounterKey.LOGIN_GOLD_REWARD_TIMES, 1);

		if (!city.isTester()) { // 开发模式下, 一直可以翻牌
			markReceiveToday();
		}

		return new ResultImpl(gold, getNext(canReceiveId));
	}

	/**
	 * 今天是否领取了送元宝奖励
	 *
	 * @return
	 */
	private boolean hasReceiveGoldRewardToday() {
		UserCounter uc = city.getUserCounter();
		return uc.isMark(CounterKey.HAS_RECEIVE_GOLD_REWARD_TODAY);
	}

	private void markReceiveToday() {
		UserCounter uc = city.getUserCounter();
		uc.mark(CounterKey.HAS_RECEIVE_GOLD_REWARD_TODAY);
	}

	private int getNext(int canReceiveId) {
		int next = canReceiveId + 1;

		SyceeRewardsTemplet temp = SyceeRewardsTempletConfig.get(next);
		if (temp == null) {
			return 0;
		}

		String awards = temp.getAwards();
		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		List<Prize> ss = s.buildPrizes(city.getPlayer(), awards);
		return getCount(ss);
	}

	private int sendReward(int canReceiveId) {
		SyceeRewardsTemplet temp = SyceeRewardsTempletConfig.get(canReceiveId);
		if (temp == null) {
			return 0;
		}
		String awards = temp.getAwards();
		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		List<Prize> send = s.send(city.getPlayer(), awards);
		return getCount(send);
	}

	private int getCount(List<Prize> send) {
		int count = 0;
		for (Prize prize : send) {
			count += prize.getCount();
		}
		return count;
	}

	@Override
	public boolean hasReceive() {
		UserCounter uc = city.getUserCounter();
		return uc.isMark(CounterKey.HAS_OPEN_ALL_CARD);
	}

	@Override
	public int getContinuousDay() {
		int count = continuous.getContinuous(ContinuousType.LOGIN_REWARD) - 1;

		int i = count % 3 + 1;

//		Debuger.debug("LoginRewardPlayerImpl.getContinuousDay() 连续签到天数: " + count + " --- " + i);
		
		if (count > 2) {
			return 3;
		}

		return i;
	}

	/**
	 * 翻牌 获奖
	 */
	@Override
	public OpenResult openAllCard() {

		int continuousDay = getContinuousDay();

		UserCounter his = city.getUserCounterHistory();

		check();

		final int count = getCanOpenCount(); // 可以翻牌的数量

		List<Prize> ps = randomAll();

		Util.Collection.upset(ps);

		List<Prize> received = Util.Collection.sub(ps, count);

		replace(received, his.get(CounterKey.OPEN_CARD_TIMES));

		send(received);

//		if (!city.isTester()) { // 测试玩家, 一直可以翻牌
			mark();
//		}

		Collection<Prize> unreceived = Util.Collection.reject(received, ps);

		his.add(CounterKey.OPEN_CARD_TIMES, 1);

		return new OpenResultImpl(received, unreceived, continuousDay);
	}

	/**
	 * 将奖励替换成策划要求的特殊奖励
	 *
	 * 第一次翻牌, 第二次翻牌为概率
	 *
	 * FAN_PAI_BI_DE_ONE, FAN_PAI_BI_DE_TWO
	 *
	 * @param received
	 * @param i
	 */
	private void replace(List<Prize> received, int times) {
		if (times == 0) {
			replace(D.FAN_PAI_BI_DE_ONE, received); // FAN_PAI_BI_DE_ONE中
													// 随机找一个替换掉
		}

		if (times == 1) {
			replace(D.FAN_PAI_BI_DE_TWO, received);
		}
	}

	private void replace(String reward, List<Prize> received) {
		List<Prize> ps = PrizeSenderFactory.getPrizeSender()
				.buildPrizes(city.getPlayer(), reward);
		Prize one = Util.Random.getRandomOne(ps);
		received.remove(0);
		received.add(one);
	}

	private List<Prize> randomAll() {

		// [4-step:3|3-step:3+1-step:4]+2-step:2+1-id:130034+1-id:130036+3-id:130031

		List<Prize> ls = Lists.newArrayList();

		if (Util.Random.isHappen(0.5f)) {
			ls.addAll(get(4, 3));
		} else {
			ls.addAll(get(3, 3));
			ls.addAll(get(1, 4));
		}

		ls.addAll(get(2, 2));
		ls.addAll(get("130034,1"));
		ls.addAll(get("130036,1"));
		ls.addAll(get("130031,3"));

		// Debuger.debug("LoginRewardPlayerImpl.randomAll() 随机物品数量:" +
		// ls.size());

		// for (Prize prize : ls) {
		// System.out.print(prize.getId() + ":" + prize.getCount() + " -- ");
		// }
		// System.out.println();

		return ls;

	}

	private Collection<Prize> get(String award) {
		return PrizeSenderFactory.getPrizeSender().buildPrizes(city.getPlayer(), award);
	}

	/**
	 * @param count
	 *            奖励数量
	 * @param step
	 *            品阶
	 * @return
	 */
	private List<Prize> get(int count, int step) {

		List<ContinuousEnterRewardsTemplet> ts = random(count, step);
		List<Integer> ls = Util.Collection.getListByOneFields(
				new IntegerFetcher<ContinuousEnterRewardsTemplet>() {

					@Override
					public Integer get(ContinuousEnterRewardsTemplet t) {
						return t.getId();
					}
				}, ts);

		List<Prize> ps = Lists.newArrayList();

		for (Integer id : ls) {
			ps.add(new PrizeImpl(id, 1));
		}
		return ps;
	}

	private List<ContinuousEnterRewardsTemplet> random(int count, int step) {

		List<ContinuousEnterRewardsTemplet> all = ContinuousEnterRewardsTempletConfig
				.findByQuality(step);

		filter(all);
		
		List<ContinuousEnterRewardsTemplet> ls = Lists.newArrayList();

		Set<Integer> set = Sets.newHashSet();

		while (set.size() < count) {
			ContinuousEnterRewardsTemplet r = getRandomOne(all);
			if (set.add(r.getId())) {
				ls.add(r);
			}
		}
		return ls;

	}

	private void filter(List<ContinuousEnterRewardsTemplet> all) {
		Iterator<ContinuousEnterRewardsTemplet> it = all.iterator();
		while (it.hasNext()) {
			ContinuousEnterRewardsTemplet t = it.next();
			if(!city.getFunctionOpenManager().isOpen(t.getModulesId())) {
				it.remove();
			}
		}
	}

	private ContinuousEnterRewardsTemplet getRandomOne(
			List<ContinuousEnterRewardsTemplet> all) {
		WeightFetcher<ContinuousEnterRewardsTemplet> weightAble = new WeightFetcher<ContinuousEnterRewardsTemplet>() {

			@Override
			public Integer get(ContinuousEnterRewardsTemplet t) {
				return t.getWeight();
			}
		};
		return Util.Random.getRandomOneByWeight(all, weightAble);
	}

	private void send(List<Prize> received) {
		for (Prize prize : received) {
			prize.award(city.getPlayer());
		}
	}

	// private String build(ContinuousEnterRewardsTemplet random, int count) {
	// return random.getId() + "," + count;
	// }

	private int getCanOpenCount() {
		int continuousDay = getContinuousDay();
		ContinuousRewardsTemplet temp = ContinuousRewardsTempletConfig
				.get(continuousDay);
		if (temp == null) {
			throw new OperationFaildException(S.S10215);
		}
		return temp.getNumber();
	}

	private void check() {

		if (hasReceive()) {
			throw new OperationFaildException(S.S10211);
		}
	}

	private void mark() {
		continuous.doIt(ContinuousType.LOGIN_REWARD);
		UserCounter uc = city.getUserCounter();
		uc.mark(CounterKey.HAS_OPEN_ALL_CARD);
	}

	@Override
	public boolean isShowable() {
		return city.getLevel() >= D.FAN_PAI_OPEN_LEVEL && !hasReceive();
	}
}
