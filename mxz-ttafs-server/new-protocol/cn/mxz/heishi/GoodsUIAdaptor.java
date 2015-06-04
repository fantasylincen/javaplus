package cn.mxz.heishi;

import cn.mxz.AgainstGoodsLibraryTemplet;
import cn.mxz.AgainstGoodsLibraryTempletConfig;

public class GoodsUIAdaptor implements GoodsUI {

	private HeiShiGoods	goods;

	public GoodsUIAdaptor(HeiShiGoods goods) {
		this.goods = goods;
	}

	@Override
	public String getName() {
		return goods.getName();
	}

	@Override
	public int getId() {
		return goods.getId();
	}

	@Override
	public int getCount() {
		return goods.getCountExchangeEvery();
	}

	@Override
	public int getLimit() {
//		return goods.getLimit();
		int id = goods.getId();
		AgainstGoodsLibraryTemplet temp = AgainstGoodsLibraryTempletConfig.get(id);
		return temp.getQuota()/* * temp.getNumber()*/;
	}

	@Override
	public String getNeeds() {
		return goods.getNeeds();
	}

	@Override
	public String getPrize() {
		return goods.getPrize();
	}

	@Override
	public boolean getHasExchange() {
		return goods.hasExchange();
	}

	@Override
	public int getCountRemain() {
		int id = goods.getId();
		AgainstGoodsLibraryTemplet temp = AgainstGoodsLibraryTempletConfig.get(id);
		return goods.getCountRemain() / temp.getNumber();
	}

}
