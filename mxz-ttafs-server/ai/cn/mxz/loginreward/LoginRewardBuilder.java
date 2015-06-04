package cn.mxz.loginreward;

import java.util.Collection;

import cn.mxz.bossbattle.Prize;
import cn.mxz.corona.PropsBuilder;
import cn.mxz.protocols.user.PropP.PropsPro;
import cn.mxz.protocols.user.loginReward.LoginRewardP.LoginRewardPro;

public class LoginRewardBuilder {

	public LoginRewardPro build(OpenResult r) {
		LoginRewardPro.Builder b = LoginRewardPro.newBuilder();
		b.setContinuousDay(r.getContinuousDay());
		Collection<Prize> received = r.getReceived();
		Collection<Prize> unReceived = r.getUnReceived();
		PropsPro ps = new PropsBuilder().buildProps(received);
		b.setReceived(ps);
//		Debuger.debug("LoginRewardBuilder.build() 得到的道具数量" + received.size());
//		Debuger.debug("LoginRewardBuilder.build() 没得到的道具数量" + unReceived.size());
		b.setUnReceived(new PropsBuilder().buildProps(unReceived));
		return b.build();
	}

}
