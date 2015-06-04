package cn.mxz.openserver;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;

public class OpenServerTransformImpl implements OpenServerTransform {

	private City user;

	@Override
	public OpenServerUI getOpenServerUI() {
		OpenServerRewardManager manager = user.getOpenServerRewardManager();
		List<OpenServerReward> rewards = manager.getRewards();
		return new OpenServerUIImpl(rewards, user);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public void receive(int day) {
		OpenServerRewardManager manager = user.getOpenServerRewardManager();
		List<OpenServerReward> rewards = manager.getRewards();
		for (OpenServerReward o : rewards) {
			if (o.getDay() == day) {
				if(o.getCanReceive()) {
					o.sendReward();
				}
			}
		}
		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(user);
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
	}

}
