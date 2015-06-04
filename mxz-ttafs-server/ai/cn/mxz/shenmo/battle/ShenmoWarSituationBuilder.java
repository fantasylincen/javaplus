package cn.mxz.shenmo.battle;

import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.city.City;
import cn.mxz.protocols.shenmo.ShenMoP.ShenmoWarSituationPro;

public class ShenmoWarSituationBuilder {

	public ShenmoWarSituationPro build(ShemmoWarSituation fight, City city) {
		ShenmoWarSituationPro.Builder b = ShenmoWarSituationPro.newBuilder();
		b.setWarSituation(new WarSituationBuilder().build(city, fight.getSituation()));
		b.setGongde( fight.getGongde() );
		b.setDamage( fight.getDamage() );
		//b.setPrize(value);
		return b.build();
	}

}
