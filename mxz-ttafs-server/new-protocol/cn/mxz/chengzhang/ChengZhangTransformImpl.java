package cn.mxz.chengzhang;

import cn.mxz.city.City;

public class ChengZhangTransformImpl implements ChengZhangTransform {

	private City user;

	@Override
	public ChengZhangUI getData() {
		return new ChengZhangUIImpl(user.getChengZhangPlayer());
	}

	@Override
	public ChengZhangUI buy() {
		user.getChengZhangPlayer().buy();
		return getData();
	}

	@Override
	public ChengZhangUI receive(int id) {
		user.getChengZhangPlayer().receive(id);
		return getData();
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
