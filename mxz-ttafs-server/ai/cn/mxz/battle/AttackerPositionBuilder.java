package cn.mxz.battle;

import cn.mxz.protocols.user.battle.WarSituationP.AttackerPositionPro;

class AttackerPositionBuilder {

	public AttackerPositionPro build(AttackAction aa) {
		AttackerPositionPro.Builder b = AttackerPositionPro.newBuilder();
//		b.setPartnerPosition(aa.getPartnerPosition());
		b.setPosition(aa.getPosition());
		b.setIsDogz(aa.isDogz());
		return b.build();
	}

}
