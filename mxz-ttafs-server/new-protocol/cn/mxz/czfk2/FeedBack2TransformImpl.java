package cn.mxz.czfk2;

import cn.mxz.city.City;

public class FeedBack2TransformImpl implements FeedBack2Transform {

	private City user;

	@Override
	public FeedBack2UI getUI() {
		FeedBackManager2 fm = user.getFeedBackManager2();
		return new FeedBack2UIImpl(fm);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public FeedBack2UI receiveById(int id) {
		FeedBackManager2 fm = user.getFeedBackManager2();
		fm.receiveById(id);
		return getUI();
	}

}
