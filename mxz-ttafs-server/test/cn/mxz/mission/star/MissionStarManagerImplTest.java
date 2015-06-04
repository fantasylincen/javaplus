package cn.mxz.mission.star;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.mxz.testbase.TestBaseAccessed;

import com.google.common.collect.Lists;

public class MissionStarManagerImplTest extends TestBaseAccessed {

	private List<Boolean>	status;
	private float			percent;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void test() {

		// 没领 没领 没领
		status = Lists.newArrayList(false, false, true);
		percent = 1.9f;
		int aaa = aaa();
		System.out.println(aaa);
		// /** 30 不可领取 */
		// public static final int PERCENT_30_X = 0;
		// /** 30 可领取 */
		// public static final int PERCENT_30_O = 1;
		// /** 60 不可领取 */
		// public static final int PERCENT_60_X = 2;
		// /** 60 可领取 */
		// public static final int PERCENT_60_O = 3;
		// /** 90 不可领取 */
		// public static final int PERCENT_90_X = 4;
		// /** 90 可领取 */
		// public static final int PERCENT_90_O = 5;
		// /** 全部领取完成 */
		// public static final int COMPLETE = 6;
	}

	private int aaa() {

		if (hasReceive(Reward.TYPE.PERCENT_100)) {
			return Reward.Status.COMPLETE;
		}

		if (percent >= 0.9 && !hasReceive(Reward.TYPE.PERCENT_100)) {
			return Reward.Status.PERCENT_100_O;
		}

		if (hasReceive(Reward.TYPE.PERCENT_60)) {
			return Reward.Status.PERCENT_100_X;
		}

		if (percent >= 0.6 && !hasReceive(Reward.TYPE.PERCENT_60)) {
			return Reward.Status.PERCENT_60_O;
		}

		if (hasReceive(Reward.TYPE.PERCENT_30)) {
			return Reward.Status.PERCENT_60_X;
		}

		if (percent >= 0.3 && !hasReceive(Reward.TYPE.PERCENT_30)) {
			return Reward.Status.PERCENT_30_O;
		}

		return Reward.Status.PERCENT_30_X;
	}

	private boolean hasReceive(int t) {
		Boolean boolean1 = status.get(t - 1);
		return boolean1;
	}

	@Test
	public void test2() {
		MissionStarManager sm = user.getMission().getStarManager();
		sm.addBossStar(1, 1);
	}
}
