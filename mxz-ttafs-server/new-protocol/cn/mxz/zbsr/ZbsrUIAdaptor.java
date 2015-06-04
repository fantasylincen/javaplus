package cn.mxz.zbsr;

import java.util.List;

import cn.mxz.bag.BagAuto;
import cn.mxz.city.City;
import cn.mxz.city.Zbsr;
import cn.mxz.heishi.GoodsUI;
import cn.mxz.heishi.GoodsUIAdaptor;
import cn.mxz.heishi.HeiShi;
import cn.mxz.heishi.HeiShiGoods;

import com.google.common.collect.Lists;

import define.D;

public class ZbsrUIAdaptor implements ZbsrUI {

	private Zbsr	zbsr;

	public ZbsrUIAdaptor(Zbsr zbsr) {
		this.zbsr = zbsr;
	}

	@Override
	public List<ZbsrItem> getGoods() {
		List<ZbsrGoods> goods = zbsr.getGoods();
		List<ZbsrItem> ls = Lists.newArrayList();
		for (ZbsrGoods g : goods) {
			ls.add(new ZbsrGoodsUIAdaptor(g));
		}
		return ls;
	}

	@Override
	public int getGold() {
		City city = zbsr.getCity();
		return city.getPlayer().getGold();
	}

	@Override
	public int getRemainSec() {
		return zbsr.getRemainSec();
	}
}
