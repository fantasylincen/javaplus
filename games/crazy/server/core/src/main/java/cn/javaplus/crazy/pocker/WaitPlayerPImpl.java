package cn.javaplus.crazy.pocker;

import cn.javaplus.crazy.main.WaitPlayerP;

public class WaitPlayerPImpl implements WaitPlayerP {

	private Place current;

	public WaitPlayerPImpl(Place current) {
		this.current = current;
	}

	@Override
	public int getPlaceNumber() {
		return current.getPlaceNumber();
	}

}
