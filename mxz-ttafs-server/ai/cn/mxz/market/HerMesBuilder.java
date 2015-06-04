package cn.mxz.market;

import cn.mxz.city.City;
import cn.mxz.protocols.user.market.MarketP.HerMesPro;

class HerMesBuilder {

	private City city;

	
	HerMesBuilder(City city) {
		this.city = city;
	}

	public HerMesPro build(TradePropItem tt) {
		
		HerMesPro.Builder b = HerMesPro.newBuilder();
		
		b.setTypeProperty(tt.getTypeProperty());
		
		b.setNub(tt.getNub());
		
		int count = city.getBagAuto().getCount(tt.getTypeProperty());
		
		if(count > tt.getNub()){
			
			b.setIsEnough(true);
			
		}else{
			
			b.setIsEnough(false);
		}
		
		
		return b.build();
	}

}
