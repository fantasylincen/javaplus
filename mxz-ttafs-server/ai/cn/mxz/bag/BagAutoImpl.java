package cn.mxz.bag;

import cn.mxz.city.City;

public class BagAutoImpl implements BagAuto {

	private City	city;

	private BagRouter router;

	public BagAutoImpl(City city) {
		this.city = city;
		router = new BagRouterImpl();
	}


	@Override
	public int getCount(int id) {

		if (router.isSuiPian(id)) {
			return city.getPiecesBag().getCount(id);
		}

		return city.getBag().getCount(id);
	}

	@Override
	public void remove(int id, int count) {

		if(count <= 0) {
			return;
		}
		
		if (router.isSuiPian(id)) {
			Bag<Grid> bag = city.getPiecesBag();
			bag.remove(id, count);
			return;
		}
		Bag<Grid> bag = city.getBag();
		bag.remove(id, count);
	}

	@Override
	public void addProp(int id, int count) {

		if(count <= 0) {
			return;
		}
		
		if (router.isSuiPian(id)) {
			Bag<Grid> bag = city.getPiecesBag();
			bag.addProp(id, count);
			return;
		}

		Bag<Grid> bag = city.getBag();
		bag.addProp(id, count);
	}
}
