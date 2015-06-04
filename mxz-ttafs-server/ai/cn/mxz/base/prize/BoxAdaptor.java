package cn.mxz.base.prize;

import cn.mxz.VioletGoldBoxTemplet;

public class BoxAdaptor implements BoxTemplet {

	private VioletGoldBoxTemplet box;

	public BoxAdaptor(VioletGoldBoxTemplet box) {
		this.box = box;
	}

	public int getId() {
		return box.getId();
	}

	public int getStop() {
		return box.getStop();
	}

	public String getNumber() {
		return box.getNumber();
	}

	public int getWeight1() {
		return box.getWeight1();
	}

	public int getWeight2() {
		return box.getWeight2();
	}

	public String getAwards10() {
		return box.getAwards10();
	}

	public String getAwards11() {
		return box.getAwards11();
	}

	
}
