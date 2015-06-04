package cn.mxz.onlinereward;

import cn.mxz.city.City;

public class OnlineRewardTransformImpl implements OnlineRewardTransform {

	private City user;

	@Override
	public OnlineRewardUI getUI() {
		return new OnlineRewardUIImpl(user.getOnlineRewardManager());
	}

	@Override
	public OnlineRewardUI receiveById(int id) {
		OnlineRewardManager manager = user.getOnlineRewardManager();
		manager.receiveById(id);
		return getUI();
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
