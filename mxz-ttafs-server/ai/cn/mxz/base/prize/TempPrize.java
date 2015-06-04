package cn.mxz.base.prize;

import cn.mxz.PrizeInExcel;

public class TempPrize implements PrizeInExcel {
	
	private final String prize;

	TempPrize(String prize) {
		this.prize = prize;
	}

	@Override
	public String getAwards() {
		return prize;
	}
}