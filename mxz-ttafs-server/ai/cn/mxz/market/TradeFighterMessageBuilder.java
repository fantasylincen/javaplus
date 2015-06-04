package cn.mxz.market;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.user.god.FighterP.FighterPro;
import cn.mxz.protocols.user.market.MarketP;
import cn.mxz.protocols.user.market.MarketP.QueryTradeMesPro;
import cn.mxz.protocols.user.market.MarketP.TradeFighterMessagePro;
import cn.mxz.protocols.user.market.MarketP.TradeMes;
import cn.mxz.protocols.user.market.MarketP.TradedGoods;
import cn.mxz.team.builder.FighterBuilder;
import cn.mxz.user.team.god.Hero;


class TradeFighterMessageBuilder {



	private City city;

	TradeFighterMessageBuilder(City city) {

		this.city = city;

		// TODO Auto-generated constructor stub
	}

	public TradeFighterMessagePro build(List<TradeItem> all, int page) {

		TradeFighterMessagePro.Builder b = TradeFighterMessagePro.newBuilder();

		for (TradeItem t : all) {

			b.addFighters(queryTradem(t));
		}
		return b.build();
		// TODO Auto-generated method stub

	}

	private QueryTradeMesPro queryTradem(TradeItem t) {

		MarketP.QueryTradeMesPro.Builder b = QueryTradeMesPro.newBuilder();

//		List<TradePropItem> allProp = t.getAllProp();
//
//		for (TradePropItem tradePropItem : allProp) {
//			tradePropItem.getId();
//		}

		b.setFighter(trademes(t));

		List<TradePropItem> allProp = t.getAllProp();

		for (TradePropItem tp : allProp) {

			b.addTypes(tradedGoods(tp));
		}

		return b.build();
	}

	private TradedGoods tradedGoods(TradePropItem t) {

		MarketP.TradedGoods.Builder b = TradedGoods.newBuilder();

			b.setTypeProperty(t.getTypeProperty());

			b.setNub(t.getNub());

			int count = city.getBagAuto().getCount(t.getNub());

			if(count > 0){

				b.setIsEnough(true);

			}else{

				b.setIsEnough(false);
			}

		return b.build();
	}

	private TradeMes trademes(TradeItem t) {

		MarketP.TradeMes.Builder b = TradeMes.newBuilder();

		TradeFighter mes = t.getMes();

		b.setNick(mes.getNick());

		b.setMyType(mes.getTypeId());

//		long d = System.currentTimeMillis()/1000 - mes.getTradeTime();
//
//		int hour = (int) (d % 3600);

		//当时间大于48小时将神将从市场上删除
//		if(hour > 48) {
//
//			for (TradePropItem tt : t.getAllProp()) {
//
//				DAO.delete(tt.getTradId());
//			}
//		}

		b.setTradeTime(mes.getTradeTime());

		//是否满足条件
		b.setIsContents(false);

		b.setBelongPlayer(mes.getBelongPlayer());

		Hero god = city.getTeam().get(mes.getFighterId());

		FighterPro build = new FighterBuilder().build(god);

		b.setHero(build);

		return b.build();
	}

}
