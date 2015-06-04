package cn.mxz.market;

import cn.javaplus.time.Time;
import cn.mxz.protocols.user.market.MarketP.FighterMPro;

class FighterMBuilder {

	public FighterMPro build(TradeFighter mes) {
		
		FighterMPro.Builder b = FighterMPro.newBuilder();
		
		b.setNick(mes.getNick());
		
		b.setMyType(mes.getTypeId());
		
		b.setMyFighterId(mes.getFighterId());
		
		long i = mes.getTradeTime();
		
		i *= 1000;
		
		long d = System.currentTimeMillis() - i;

		int hour = (int) (d / Time.MILES_ONE_HOUR);

		System.out.println(hour);
		
		b.setTradeTime(48 * 3600 - (int)d/1000);
		
//		b.setTradeTime(mes.getTradeTime());
		
		return b.build();
	}

}
