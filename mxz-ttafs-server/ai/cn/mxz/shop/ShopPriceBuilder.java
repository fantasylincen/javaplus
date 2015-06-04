package cn.mxz.shop;

import cn.mxz.protocols.user.shops.ShopsAllP.ShopPricePro;

public class ShopPriceBuilder {

	public ShopPricePro build(Price price) {
		ShopPricePro.Builder b = ShopPricePro.newBuilder();
		b.setCash(price.getCashNew());
		b.setGold(price.getGoldNew());
		b.setIsJinBeiKe(price.isJinBeiKe());
		return b.build();
	}

}
