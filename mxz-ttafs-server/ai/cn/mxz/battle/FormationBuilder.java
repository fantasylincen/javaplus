package cn.mxz.battle;

import cn.mxz.formation.PlayerCamp;
import cn.mxz.protocols.user.battle.WarSituationP.FormationPro;

class FormationBuilder {

	public FormationPro build(PlayerCamp under, BattleCamp upper) {

		final FormationPro.Builder b = FormationPro.newBuilder();

//		log(under, upper);

		b.setUnders(new CampBuilder().build(under));

		b.setUppers(new CampBuilder().build(upper));

		return b.build();

	}

//	private void log(Camp<? extends Fighter> under, Camp<? extends Fighter> upper) {
//
//		Debuger.debug("");
//
//		Debuger.debug("FormationBuilder.build() 准备战场:");
//
//		Debuger.debug("    >>下方战士:");
//
//		for (final Fighter f : under.getFighters()) {
//
//			Debuger.debug("        >>" + f + " 位置:" + under.getPosition(f));
//		}
//
//		Debuger.debug("");
//
//		Debuger.debug("    >>上方战士:");
//
//		for (final Fighter f : upper.getFighters()) {
//
//			Debuger.debug("        >>" + f + " 位置:" + upper.getPosition(f));
//		}
//
//
//		Debuger.debug("----------------------------");
//
//		Debuger.debug("");
//	}

}
