package cn.mxz.loginreward;

import org.junit.Test;

import cn.mxz.handler.LoginRewardService;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.util.counter.CounterKey;

public class LoginRewardServiceImplTest extends TestBaseAccessed {

	@Test
	public final void letItCanOpen() {
		user.getUserCounter().clear(CounterKey.HAS_OPEN_ALL_CARD);

	}

	@Test
	public final void test111() {
		for (int i = 1; i < 100; i++) {
			System.out.println(i + "," + ((i - 1) % 3 + 1));
		}
	}

	@Test
	public final void test() {
		letItCanOpen();
		LoginRewardService ls = getService(LoginRewardService.class);
		int continuousDay = ls.getContinuousDay();
		System.out.println("连续签到次数:" + continuousDay);
		ls.openAllCard();

	}

}
