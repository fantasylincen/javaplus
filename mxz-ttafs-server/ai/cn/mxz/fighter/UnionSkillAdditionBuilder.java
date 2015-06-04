//package cn.mxz.fighter;
//
//import cn.mxz.FighterTemplet;
//import cn.mxz.FighterTempletConfig;
//import cn.mxz.UnionSkillTemplet;
//import cn.mxz.UnionSkillTempletConfig;
//import cn.mxz.protocols.user.god.FighterP.UnionSkillAdditionPro;
//import cn.mxz.team.builder.UnionSkill;
//import cn.mxz.user.builder.AttributeBuilder;
//import cn.mxz.user.team.god.Hero;
//
//public class UnionSkillAdditionBuilder {
//
//	public UnionSkillAdditionPro build(UnionSkill unionSkill, Hero hero) {
//
//		FighterTemplet fTemp = FighterTempletConfig.get(hero.getTypeId());
//
//		int id = unionSkill.getId();
//		UnionSkillTemplet temp = UnionSkillTempletConfig.get(id);
//
//		UnionSkillAdditionPro.Builder b = UnionSkillAdditionPro.newBuilder();
//		b.setAddition(new AttributeBuilder().build(unionSkill.getAddition()));
//		b.setFighter1(fTemp.getName());
//		b.setFighter2(temp.getSpot());
//		b.setId(temp.getId());
//
//
//		return b.build();
//	}
//
//}
