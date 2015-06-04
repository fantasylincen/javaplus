package cn.mxz.loginreward;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.LoginRewardService;
import cn.mxz.protocols.user.loginReward.LoginRewardP.LoginRewardPro;
import cn.mxz.util.debuger.Debuger;

@Component("loginRewardService")
@Scope("prototype")
public class LoginRewardServiceImpl extends AbstractService implements LoginRewardService {

	@Override
	public int getContinuousDay() {
		City c = getCity();
		LoginRewardPlayer p = c.getLoginRewardPlayer();
		return p.getContinuousDay();
	}

	@Override
	public LoginRewardPro openAllCard() {

		City c = getCity();
		LoginRewardPlayer p = c.getLoginRewardPlayer();
		OpenResult r = p.openAllCard();

		return new LoginRewardBuilder().build(r);
	}

	@Override
	public String receiveGoldReward() {
		City c = getCity();
		LoginRewardPlayer p = c.getLoginRewardPlayer();
		Result r = p.receiveGoldReward();
		String string = r.getReceived() + "," + r.getNextGold();
		Debuger.debug("LoginRewardServiceImpl.receiveGoldReward() 领元宝:" + string);
		return string;
	}
}