package cn.mxz.shenmo;

import cn.mxz.city.City;
import cn.mxz.protocols.shenmo.ShenMoP.ShenMoListPro;

public class ShenMoListBuilder {

	public ShenMoListPro build( City user ) {
		UserShenmo userShenmo = user.getUserShenmo();
		
		ShenMoListPro.Builder b = ShenMoListPro.newBuilder();	
		for (ShenmoItem shenMo : userShenmo.getOurShenmo() ) {
			b.addShenmos(new ShenMoBuilder().build(shenMo, user));
		}
		b.setGongdePrize( userShenmo.getGongdePrize().getPrizeStr() );
		b.setGongde(userShenmo.getGongdePrize().getGongde() );
		return b.build();
	}

}
