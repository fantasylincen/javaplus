package cn.mxz.heishi;

import java.util.List;

import cn.mxz.bag.BagAuto;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

import define.D;

public class HeiShiUIAdaptor implements HeiShiUI {

	private HeiShi	heiShi;

	public HeiShiUIAdaptor(HeiShi heiShi) {
		this.heiShi = heiShi;
	}

	@Override
	public List<GoodsUI> getGoods() {
		List<HeiShiGoods> goods = heiShi.getGoods();
		List<GoodsUI> ls = Lists.newArrayList();
		for (HeiShiGoods g : goods) {
			ls.add(new GoodsUIAdaptor(g));
		}
		return ls;
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
