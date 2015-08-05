package cn.javaplus.crazy.pocker;

import cn.javaplus.crazy.main.TipP;

public class TipPImpl implements TipP {

	private int placeNumber;
	private String message;

	public TipPImpl(int placeNumber, String message) {
		this.placeNumber = placeNumber;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getPlaceNumber() {
		return placeNumber;
	}

}
