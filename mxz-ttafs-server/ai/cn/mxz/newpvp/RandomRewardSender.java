package cn.mxz.newpvp;

import java.util.List;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.SnatchAndArenaLibraryTemplet;
import cn.mxz.SnatchAndArenaLibraryTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.old.CashBall;

import com.google.common.collect.Lists;

import define.D;

public class RandomRewardSender {

	private String received = "";
	private String unreceived1 = "";
	private String unreceived2 = "";
	private City user;

	public void sendReward(City city) {
		this.user = city;
		List<SnatchAndArenaLibraryTemplet> temp = getRandom(3, city);
		received = build(temp.get(0));
		unreceived1 = build(temp.get(1));
		unreceived2 = build(temp.get(2));
		city.getPrizeSender1().send(received);
	}

	public City getUser() {
		return user;
	}

	private List<SnatchAndArenaLibraryTemplet> getRandom(int count, City city) {
		List<SnatchAndArenaLibraryTemplet> all = getInLevelScope(city
				.getLevel());
		List<SnatchAndArenaLibraryTemplet> ls = Lists.newArrayList();
		for (int i = 0; i < count; i++) {
			ls.add(removeRandomOne(all));
		}
		return ls;
	}

	private SnatchAndArenaLibraryTemplet removeRandomOne(
			List<SnatchAndArenaLibraryTemplet> all) {
		WeightFetcher<SnatchAndArenaLibraryTemplet> weightAble = new WeightFetcher<SnatchAndArenaLibraryTemplet>() {

			@Override
			public Integer get(SnatchAndArenaLibraryTemplet t) {
				return t.getWeight();
			}
		};
		return Util.Random.removeRandomOneByWeight(all, weightAble);
	}

	private List<SnatchAndArenaLibraryTemplet> getInLevelScope(int level) {
		List<SnatchAndArenaLibraryTemplet> all = SnatchAndArenaLibraryTempletConfig
				.getAll();
		List<SnatchAndArenaLibraryTemplet> ls = Lists.newArrayList();
		for (SnatchAndArenaLibraryTemplet t : all) {
			if (isLevelIn(level, t) && user.getFunctionOpenManager().isOpen(t.getModulesId())) {
				ls.add(t);
			}
		}
		return ls;
	}

	private boolean isLevelIn(int level, SnatchAndArenaLibraryTemplet t) {
		int min = t.getLvMin();
		int max = t.getLvMax();
		return level >= min && level <= max;
	}

	private String build(SnatchAndArenaLibraryTemplet t) {
		int min = t.getPropMin();
		int max = t.getPropMax();
		return t.getReward() + "," + Util.Random.get(min, max);
	}

	public String getReceived() {
		return parse(received);
	}

	public String getUnreceived1() {
		return parse(unreceived1);
	}

	public String getUnreceived2() {
		return parse(unreceived2);
	}

	/**
	 * 如果是金币球, 转换为实际的金币
	 * 
	 * @param a
	 * @return
	 */
	private String parse(String a) {
		if (a.startsWith(D.ID_CASH_BALL + "")) {
			return buildCashBall(a);
		}
		return a;
	}

	private String buildCashBall(String a) {
		List<Integer> s = Util.Collection.getIntegers(a);
		Integer count = s.get(1);
		CashBall c = new CashBall(user.getPlayer(), count);
		return D.CASH_ID + "," + c.getCount();
	}

}
