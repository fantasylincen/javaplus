//package cn.mxz.activity.boss;
//
//import cn.mxz.AdditionMultiplier;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.fighter.HeroImpl;
//import cn.mxz.fighter.PlayerHeroImpl;
//import cn.mxz.formation.PlayerCamp;
//
///**
// * 普通拷贝的阵营, 该阵营用于打仗的话, 战士的血量不会被扣除
// *
// * @author 林岑
// *
// */
//public class NormalCopyedCamp extends CopyedCamp implements PlayerCamp {
//
//	private PlayerHeroImpl player;
//
//	public NormalCopyedCamp(PlayerCamp camp) {
//
//		super(camp, 1);
//	}
//
//	@Override
//	protected PlayerHeroImpl copyPlayer(PlayerHeroImpl h, AdditionMultiplier attributeX) {
//
//		player = new PlayerHeroImpl(h);
//
//		hpUp(player);
//
//		return player;
//	}
//
//	private void hpUp(Fighter f) {
//
//		f.addHp(f.getAttribute().getHp() - f.getHpNow());
//	}
//
//	@Override
//	protected HeroImpl copyHero(HeroImpl h, AdditionMultiplier attributeX) {
//
//		HeroImpl heroImpl = new HeroImpl(h);
//
//		hpUp(heroImpl);
//
//		return heroImpl;
//	}
//
//	@Override
//	public int getShenJia() {
//		return camp.getShenJia();
//	}
//
//	@Override
//	public Fighter getMainFighter() {
//		return player;
//	}
//
//
//}
