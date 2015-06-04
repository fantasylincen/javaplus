package cn.mxz.bag;

import cn.mxz.city.City;

public class SuperBagSnapsort implements Snapsort {

	private BagSnapsort	bs1;
	private PiecesBagSnapsort	bs2;

	public SuperBagSnapsort() {
		bs1 = new BagSnapsort();
		bs2 = new PiecesBagSnapsort();
	}

	@Override
	public void snapsort(City city) {
		if(city == null) {
			return ;
		}
		bs1.snapsort(city.getBag());
		bs2.snapsort(city.getPiecesBag());
	}
}
