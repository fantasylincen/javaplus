//package cn.mxz.fighter;
//
//import java.util.List;
//
//import cn.mxz.AdditionMultiplier;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.dogz.Dogz;
//import cn.mxz.dogz.DogzBuilder;
//import cn.mxz.dogz.DogzManager;
//import cn.mxz.practice.PracticeCenterImpl;
//import cn.mxz.protocols.user.god.FighterP.FighterAdditionPro;
//import cn.mxz.pvp.PvpFactory;
//import cn.mxz.team.builder.UnionSkill;
//import cn.mxz.user.City;
//import cn.mxz.user.Player;
//import cn.mxz.user.builder.AttributeBuilder;
//import cn.mxz.user.team.god.Hero;
//import define.D;
//
//public class FighterAdditionBuilder {
//
//	public FighterAdditionPro build(City city, Hero hero) {
//
//		Player player = city.getPlayer();
//
//		FighterAdditionPro.Builder b = FighterAdditionPro.newBuilder();
//		b.setPhysicalMax(D.MAX_PHYSICAL);
//		b.setCharm(player.get(PlayerProperty.CHARM));
//		b.setCharmStarCount(player.getCharmStartCount());
//		b.setIsPracticing(PracticeCenterImpl.getInstance().isPracticing(city.getId()));
//		b.setCareCount(PracticeCenterImpl.getInstance().getCareCount(city));
//
//		DogzManager m = city.getDogzManager();
//		Dogz fighting = m.getFighting();
//		if(fighting != null) {
//			b.setFighting(new DogzBuilder().build(fighting, city.getBag()));
//		}
//
//		Additions additions = hero.getAdditions();
//		b.setAll(new AttributeBuilder().build(additions.getAll()));//总计加成
//		b.setPvp(new AttributeBuilder().build(additions.getPvpAddition()));//Pvp加成
//		b.setDanId(PvpFactory.getPvpPlayer(city.getId()).getDanId());
//
//		if(hero instanceof PlayerHero) {
//			PlayerHero p = (PlayerHero) hero;
//			AdditionMultiplier pas = p.getCharmAdditions();
//			b.setAddition(new CharmAdditionBuilder().buildCharmAdditionAva(pas));//关注加成
//			b.setCharmAddition(new CharmAdditionBuilder().build(pas));//魅力加成
//
//		} else {
//			b.setAddition("0");
//			b.setCharmAddition(new CharmAdditionBuilder().buildNone());
//		}
//
//
//		List<UnionSkill> unionSkills = hero.getUnionSkills();
//
//		for (UnionSkill unionSkill : unionSkills) {
//			b.addUnionSkillAddition(new UnionSkillAdditionBuilder().build(unionSkill, hero));
//		}
//
//		return b.build();
//	}
//
//}
