package cn.mxz.hhdlb;

import cn.mxz.city.City;

public class HhdlbTransformImpl implements HhdlbTransform {

	private City user;

	@Override
	public HhdlbUI getUI() {
		HhdlbManager m = user.getHhdlbManager();
		return new HhdlbUIAdaptor(m);
	}

	@Override
	public void receive() {
		HhdlbManager m = user.getHhdlbManager();
		m.receive();
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
