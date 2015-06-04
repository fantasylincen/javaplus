//package cn.mxz.tower;
//
//import java.util.List;
//
//import cn.mxz.FighterExpPrize;
//import cn.mxz.battle.AbstractBattle;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.battle.Camp;
//import cn.mxz.battle.MissionBattle;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.mission.old.PropPrize;
//
//import com.google.common.collect.Lists;
//
//
//class TowerBattleImpl extends AbstractBattle implements MissionBattle {
//
//
//	/**
//	 * @param playerCamp	玩家阵容
//	 * @param mapId			地图ID
//	 * @param camp			怪物阵容
//	 */
//	TowerBattleImpl(PlayerCamp playerCamp, int mapId, Camp<? extends Fighter> camp) {
//
//		super(playerCamp, camp);
//	}
//
//	@Override
//	protected FighterExpPrize getMapTemplet() {
//
//		return null;
//	}
//
//	@Override
//	public int getStar() {
//		return 0;
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
//	@Override
//	public boolean isMain() {
//		return false;
//	}
//
//	@Override
//	public boolean isBoss() {
//		return false;
//	}
//
//	@Override
//	public int getMapId() {
//		return 0;
//	}
//}
