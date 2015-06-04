package cn.mxz.equipment;

import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentPro;
import cn.mxz.user.builder.AttributeBuilder;
import cn.mxz.user.team.god.Hero;

public class EquipmentBuilder {

	public EquipmentPro build(Equipment e) {

		EquipmentPro.Builder b = EquipmentPro.newBuilder();

		b.setAttribute(new AttributeBuilder().build(e.getBase()));

		b.setLevel(e.getLevel());

		int price = e.getShenJia();
//		Debuger.debug("EquipmentBuilder.build() 装备身价:" + price);
		b.setPrice(price);

		b.setPriceLevelUpHistory(e.getPriceLevelUpHistory());

		b.setStep(e.getStep());

		b.setTypeId(e.getTypeId());

		Integer id = e.getId();

		b.setId(id);

		b.setLevelUpCashNeed(e.getLevelUpCashNeed());

		Hero h = e.getHero();

		int fighterId = h != null ? h.getTypeId() : -1;

		b.setFighterId(fighterId);

		int fighterType = h != null ? h.getTypeId() : -1;

		b.setFighterTypeId(fighterType);

		b.setAdditionType(e.getAdditionType());

		b.setTeamIndex(-1);

		b.setBaseAdditionType(e.getTemplet().getBaseAdditionType());

		return b.build();

	}

}
