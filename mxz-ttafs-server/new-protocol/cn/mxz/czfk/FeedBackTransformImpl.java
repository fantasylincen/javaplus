package cn.mxz.czfk;

import cn.mxz.city.City;

public class FeedBackTransformImpl implements FeedBackTransform {

	private City user;

	@Override
	public FeedBackUI getUI() {
		FeedBackManager fm = user.getFeedBackManager();
		return new FeedBackUIImpl(fm);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public FeedBackUI receiveById(int id) {
		FeedBackManager fm = user.getFeedBackManager();
		fm.receiveById(id);
		return getUI();
	}

}
