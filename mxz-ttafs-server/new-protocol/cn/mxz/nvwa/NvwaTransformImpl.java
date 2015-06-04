package cn.mxz.nvwa;

import cn.mxz.city.City;

public class NvwaTransformImpl implements NvwaTransform {

	private City user;

	@Override
	public NvwaUI getUI() {
		return new NvWaUIImpl(user.getNvwa());
	}

	@Override
	public NvwaUI buy() {
		user.getNvwa().buy();
		return new NvWaUIImpl(user.getNvwa());
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
