package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import cn.mxz.FighterExpPrize;
//import cn.mxz.MissionMapTemplet;
//import cn.mxz.MissionMapTempletConfig;
//import cn.mxz.battle.AbstractBattle;
//import cn.mxz.battle.Camp;
//import cn.mxz.battle.MissionBattle;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.formation.PlayerCamp;
//
//public class MissionBattleImpl extends AbstractBattle implements MissionBattle {
//
//	public class BossPrize implements FighterExpPrize {
//
//		private MissionMapTemplet	temp;
//
//		public BossPrize(MissionMapTemplet temp) {
//			this.temp = temp;
//		}
//
//		@Override
//		public String getBossId() {
//			return temp.getBossId();
//		}
//
//		@Override
//		public String getFreakDropOut() {
//			return temp.getFreakDropOut();
//		}
//
//		@Override
//		public String getBossDropOut() {
//			return temp.getBossDropOut();
//		}
//
//		@Override
//		public int getSingleExp() {
//			return temp.getSingleExp() * 2;
//		}
//
//		@Override
//		public int getSingleCoins() {
//			return temp.getSingleCoins() * 2;
//		}
//
//	}
//
//	private int mapId;
//
//	/**
//	 * @param playerCamp	玩家阵容
//	 * @param mapId			地图ID
//	 * @param demon			地图上遇到的怪物
//	 */
//
//	public MissionBattleImpl(PlayerCamp playerCamp, int mapId, MapDemon demon) {
//
//		super(playerCamp, demon.getCamp());
//
//		this.mapId = mapId;
//
//		addListener(new UpdateFighterHp());
//
//		addListener(new AddScoreWhilePassMapListener(mapId));
//	}
//
//	@Override
//	protected FighterExpPrize getMapTemplet() {
//
//		MissionMapTemplet temp = MissionMapTempletConfig.get(mapId);
//
//		if(containsBoss(temp)) {
//			return new BossPrize(temp);//Boss关卡经验 * 2
//		}
//		return temp;
//	}
//
//
//	private boolean containsBoss(MissionMapTemplet mapTemplet) {
//		Camp<? extends Fighter> up = getUpper();
//
//		for (Fighter f : up.getFighters()) {
//
//			if(mapTemplet.getBossId().contains(f.getTypeId() + "")) {
//
//				return true;
//			}
//		}
//
//		return false;
//	}
//
//
//}
