package cn.mxz.base.prize;

import cn.mxz.GoldBoxTemplet;

public class GoldBoxAdatpor implements BoxTemplet {

	private GoldBoxTemplet gb;

	public GoldBoxAdatpor(GoldBoxTemplet gb) {
		this.gb = gb;
	}

	public int getStop() {
		return gb.getStop();
	}

	public int getId() {
		return gb.getId();
	}

	public String getNumber() {
		return gb.getNumber();
	}

	public int getWeight1() {
		return gb.getWeight1();
	}

	public int getWeight2() {
		return gb.getWeight2();
	}

	public String getAwards10() {
		return gb.getAwards10();
	}

	public String getAwards11() {
		return gb.getAwards11();
	}

}
