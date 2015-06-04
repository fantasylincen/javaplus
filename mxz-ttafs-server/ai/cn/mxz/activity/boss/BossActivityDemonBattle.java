//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//import cn.mxz.FighterExpPrize;
//import cn.mxz.battle.AbstractBattle;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.battle.BattlePrizeAble;
//import cn.mxz.enemy.EmptyExpPrize;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.mission.old.MapDemon;
//import cn.mxz.mission.old.PropPrize;
//
//import com.google.common.collect.Lists;
//
///**
// * 在Boss活动中, 遇到普通小怪的战场
// *
// * @author 林岑
// *
// */
//
//public class BossActivityDemonBattle extends AbstractBattle implements BattlePrizeAble {
//
//	BossActivityDemonBattle(MapDemon d, PlayerCamp selected, int mapId) {
//		super(selected, d.getCamp());
//	}
//
//	@Override
//	protected FighterExpPrize getMapTemplet() {
//		return new EmptyExpPrize();
//	}
//
//	@Override
//	public List<PropPrize> getPropPrize() {
//		return Lists.newArrayList();
//	}
//
//	@Override
//	public List<BattleExpPrize> getExpPrize() {
//		return Lists.newArrayList();
//	}
//
//}
