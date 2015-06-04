package cn.mxz.loginreward;

import org.junit.Test;

import cn.mxz.SyceeRewardsTemplet;
import cn.mxz.SyceeRewardsTempletConfig;
import cn.mxz.handler.LoginRewardService;
import cn.mxz.protocols.user.PropP.PropsPro;
import cn.mxz.protocols.user.loginReward.LoginRewardP.LoginRewardPro;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

public class LoginRewardPlayerImplTest extends TestBaseAccessed {

	@Test
	public final void test() {
		for (int i = 0; i < 1000; i++) {

			user.getUserCounter().clear(CounterKey.HAS_OPEN_ALL_CARD);

			LoginRewardService temp = getService(LoginRewardService.class);
			LoginRewardPro all = temp.openAllCard();
			PropsPro rs = all.getReceived();

			int propsCount = rs.getPropsCount();
			System.out.println(propsCount);
			if (propsCount == 0) {
				throw new RuntimeException();
			}
		}
	}

	@Test
	public final void test1() {

		SyceeRewardsTempletConfig.load();
		Debuger.debug("LoginRewardPlayerImplTest.test1()" + getNext(2));

	}

	private static String getNext(int canReceiveId) {
		int next = canReceiveId + 1;

		SyceeRewardsTemplet temp = SyceeRewardsTempletConfig.get(next);

		String awards = temp.getAwards();
		Debuger.debug("LoginRewardPlayerImplTest.getNext()" + awards);
		return awards;
	}

}
