//package cn.mxz.fighter;
//
//import cn.mxz.AdditionMultiplier;
//
//public class CharmAdditionBuilder {
//
////	public CharmAdditionPro buildNone() {
////		CharmAdditionPro.Builder b = CharmAdditionPro.newBuilder();
////
////		String e = "0";
////
////		b.setAttack(e);
////		b.setCrit(e);
////		b.setDefend(e);
////		b.setDodge(e);
////		b.setDodge(e);
////		b.setHp(e);
////		b.setMAttack(e);
////		b.setMDefend(e);
////		b.setSpeed(e);
////
////		return b.build();
////	}
//
//	public String buildCharmAdditionAva(AdditionMultiplier pas) {
//
//		float v = 0;
//
//		v += pas.getAttackPar();
//		v += pas.getCritPar();
//		v += pas.getDefendPar();
//		v += pas.getDodgePar();
//		v += pas.getHpPar();
//		v += pas.getMAttackPar();
//		v += pas.getMDefendPar();
//		v += pas.getSpeedPar();
//
//		v /= 8;
//
//		return new PercentParser().parse(v);
//	}
//
////	public CharmAdditionPro build(AdditionMultiplier pas) {
////		CharmAdditionPro.Builder b = CharmAdditionPro.newBuilder();
////
////		PercentParser p = new PercentParser();//百分比转换器
////		b.setAttack(p.parse(pas.getAttackPar()));
////		b.setCrit(p.parse(pas.getCritPar()));
////		b.setDefend(p.parse(pas.getDefendPar()));
////		b.setDodge(p.parse(pas.getDodgePar()));
////		b.setHp(p.parse(pas.getHpPar()));
////		b.setMAttack(p.parse(pas.getMAttackPar()));
////		b.setMDefend(p.parse(pas.getMDefendPar()));
////		b.setSpeed(p.parse(pas.getSpeedPar()));
////
////		return b.build();
////	}
//
//
//}
