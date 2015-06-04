package cn.mxz.listeners.pvp;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.RandomPvpRobotEvent;
import cn.mxz.newpvp.NormalPvpFightUser;
import cn.mxz.newpvp.PvpFightUser;
import cn.mxz.newpvp.PvpPlace;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.newpvp.PvpRobotUser;
import cn.mxz.script.Script;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class PvpRobotReplaceListener implements Listener<RandomPvpRobotEvent> {

	@Override
	public void onEvent(RandomPvpRobotEvent event) {

		PvpPlayer me = event.getPlayer();
		
		UserCounter his = me.getCity().getUserCounterHistory();
		
		int times = his.get(CounterKey.PVP_CHALLENGE_TIMES) + 1;

		// 前面1-4场用最低档的机器人，5-7场用1、2档机器人

		List<PvpFightUser> ls = event.getThreePlayers();

		if (isIn(times, 1, 4)) {
			useRobot(me, ls, 0);
		} else if (isIn(times, 5, 7)) {
			useRobot(me, ls, 0, 1);
		}

		// if(me.getCity().isTester()) {
		// ls.clear();
		// ls.add(get("lc9"));
		// ls.add(get("lc9"));
		// ls.add(get("lc9"));
		// }
	}

	private PvpFightUser get(String string) {
		PvpPlace is = PvpPlaceImpl.getInstance();
		PvpPlayer pvpPlayer = is.get(string);
		return new NormalPvpFightUser(pvpPlayer);
	}

	private void useRobot(PvpPlayer me, List<PvpFightUser> ls, int... dangci) {

		ls.clear();

		for (int i = 0; i < 3; i++) {
			int dc = dangci[Util.R.nextInt(dangci.length)];
			ls.add(buildRandomRobot(dc, me));
		}
	}

	private PvpFightUser buildRandomRobot(int dc, PvpPlayer me) {
		List<Double> s = Script.Pvp.getShenJiaScope(me.getShenJia(),
				me.getCurrentWinTimesInDanId(), me.getLoseStreak());
		int minIndex = dc * 2;
		int maxIndex = dc * 2 + 1;
		return buildRobot(me, s.get(minIndex), s.get(maxIndex));
	}

	private PvpFightUser buildRobot(PvpPlayer me, double minPercent,
			double maxPercent) {
		City city = me.getCity();
		return new PvpRobotUser(city, minPercent, maxPercent);
	}

	private boolean isIn(int times, int min, int max) {
		return times <= max && times >= min;
	}
}
