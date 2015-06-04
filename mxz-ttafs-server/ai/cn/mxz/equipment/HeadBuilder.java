package cn.mxz.equipment;

import cn.mxz.fighter.Fighter;
import cn.mxz.protocols.user.equipment.EquipmentP.PartnerHeadPro;

public class HeadBuilder {

	public PartnerHeadPro build(Fighter f) {
		PartnerHeadPro.Builder b = PartnerHeadPro.newBuilder();
		b.setId(f.getTypeId());
		b.setLevel(f.getLevel());
		return b.build();
	}

}
