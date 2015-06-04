package cn.mxz.market;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.user.market.MarketP.FighterItemPro;

class FighterItemBuilder {

	private City city;
	
	FighterItemBuilder(City city) {
		
		super();
		this.city = city;
	}

	public FighterItemPro build(TradeItem t) {

		FighterItemPro.Builder b = FighterItemPro.newBuilder();

		b.setMes(new FighterMBuilder().build(t.getMes()));

		List<TradePropItem> all = t.getAllProp();

		for (TradePropItem tt : all) {

			b.addHero(new HerMesBuilder(city).build(tt));
		}

		return b.build();
	}

}
