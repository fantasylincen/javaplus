package cn.mxz.heishi;

import java.util.List;

import cn.mxz.bag.BagAuto;
import cn.mxz.city.City;
import define.D;

public class HeiShiSingleUIAdaptor implements HeiShiSingleUI {

	private HeiShi heiShi;
	private int id;

	public HeiShiSingleUIAdaptor(HeiShi heiShi, int id) {
		this.heiShi = heiShi;
		this.id = id;
	}

	@Override
	public GoodsUI getGoods() {
		List<HeiShiGoods> goods = heiShi.getGoods();
		for (HeiShiGoods g : goods) {
			int id2 = g.getId();
			if(id == id2) {
				return new GoodsUIAdaptor(g);
			}
		}
		throw new RuntimeException();
	}


	@Override
	public int getWineCount() {
		City city = heiShi.getCity();
		BagAuto auto = city.getBagAuto();
		int count = auto.getCount(D.ID_B_WINE);
		return count;
	}

	@Override
	public int getGold() {
		City city = heiShi.getCity();
		return city.getPlayer().getGold();
	}

	@Override
	public int getRemainSec() {
		return heiShi.getRemainSec();
	}
}
