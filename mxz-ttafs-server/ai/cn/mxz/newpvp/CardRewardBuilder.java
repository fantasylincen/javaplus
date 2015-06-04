package cn.mxz.newpvp;

import cn.mxz.protocols.user.equipment.EquipmentP.CardRewardPro;

public class CardRewardBuilder {

	public CardRewardPro build(RandomRewardSender s) {
		CardRewardPro.Builder b = CardRewardPro.newBuilder();
		
//		if(Debuger.isDevelop()) {
//			
//			b.setReceived("110001,1");
//			b.setUnreceived1("110001,1");
//			b.setUnreceived2("110001,1");
//			

//		} else {
//			
			String r = s.getReceived();
			b.setReceived(r);
			String r1 = s.getUnreceived1();
			b.setUnreceived1(r1);
			String r2 = s.getUnreceived2();
			b.setUnreceived2(r2);
//			Debuger.debug("CardRewardBuilder.build() " + r + " ======== " + r1 + " ========= " + r2);
//		}151320,30|137426,30|138049,200|138050,1000||137426,1000|138049,2000|138050,1000

		return b.build();
	}

}
