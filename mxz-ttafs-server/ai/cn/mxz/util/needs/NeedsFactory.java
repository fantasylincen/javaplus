package cn.mxz.util.needs;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.INeedsInExcel;
import cn.mxz.city.PlayerProperty;
import cn.mxz.mission.old.PrizeImpl.NeedsCheckerImpl2;

public class NeedsFactory {

	public static final INeeds getCashBallNeed(int count) {

		return new CashBallNeed(count);
	}

	public static final INeeds getExpNeed(Integer count) {

		return new ExpNeed(count);
	}

	public static final INeeds getLotNeed(Integer count) {

		return new LotNeed(count);
	}

	public static final NeedsChecker getNeedsCheckerImpl(List<INeeds> needs) {

		return new NeedsCheckerImpl(needs);
	}

	public static final NeedsChecker getNeedsCheckerImpl2(INeedsInExcel temp) {

		return new NeedsCheckerImpl2(temp.getNeeds());
	}

	/**
	 * @param temp
	 * @param x
	 *            倍数
	 * @return
	 */

	public static final NeedsChecker getNeedsCheckerImpl2(INeedsInExcel temp, int x) {

		NeedsCheckerImpl2 c = new NeedsCheckerImpl2(temp.getNeeds());

		List<INeeds> ns = new ArrayList<INeeds>();

		for (int i = 0; i < x; i++) {

			ns.addAll(c.getNeeds());
		}

		return getNeedsCheckerImpl(ns);
	}

	public static final INeeds getPropNeed(Integer id, Integer count) {

		return new PropNeed(id, count);
	}

	public static INeeds getPlayerNeed(PlayerProperty playerProperty, Integer count) {
		return new PlayerPropertyNeeds(playerProperty, count);
	}

	public static NeedsChecker getNeedsCheckerImpl2(String... needs) {

		return new NeedsCheckerImpl2(needs);
	}
}
