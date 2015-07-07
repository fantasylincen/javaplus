package cn.javaplus.stock.t;

import cn.javaplus.util.Util;

public class PriceAdaptor2 implements IPrice {

	private IPrice p2;
	private long v;

	public PriceAdaptor2(long v, IPrice p2) {
		this.v = v;
		this.p2 = p2;
	}

	public double getPrice() {
		return p2.getPrice();
	}

	public double getUp() {
		return p2.getUp();
	}

	@Override
	public long getV() {
		return v;
//		return Util.Random.get(0, 200);
//		return 200;
	}

	public long getSell1() {
		return p2.getSell1();
	}

	public long getSell2() {
		return p2.getSell2();
	}

	public long getSell3() {
		return p2.getSell3();
	}

	public long getSell4() {
		return p2.getSell4();
	}

	public long getSell5() {
		return p2.getSell5();
	}

	public long getBuy1() {
		return p2.getBuy1();
	}

	public long getBuy2() {
		return p2.getBuy2();
	}

	public long getBuy3() {
		return p2.getBuy3();
	}

	public long getBuy4() {
		return p2.getBuy4();
	}

	public long getBuy5() {
		return p2.getBuy5();
	}


}
