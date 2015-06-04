package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.db.DAOBase;
//import cn.mxz.MapTemplet;
//import cn.mxz.MissionMapTempletConfig;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.mission.events.EndEvent;
//import cn.mxz.mission.listeners.MissionListener;
//import cn.mxz.mission.map.JsonMap;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.Mission;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//import db.dao.factory.DaoFactory;
//import db.domain.MissionMap;
//
//public class MissionImpl extends AbstractMission<db.domain.Mission, MissionMap> implements Mission, NormalMission {
//
//
//	private MissionPrize prize;
//
//	public MissionImpl(City city) {
//
//		super(city);
//	}
//
//	@Override
//	public MissionMark getMark() {
//
//		return new MissionMark() {
//
//			@Override
//			public int getMapId() {
//
//				int i = dto.getMissionId() + 1;
//
//				return i;
//			}
//
//			@Override
//			public int getMissionId() {
//
//				MapTemplet m = MissionMapTempletConfig.get(getMapId());
//
//				return m.getChapterId();
//			}
//
//			@Override
//			public int getCopyId() {
//
//				MapTemplet m = MissionMapTempletConfig.get(getMapId());
//
//				return m.getSceneId();
//			}
//
//			@Override
//			public String toString() {
//				return getMissionId() + "-" + getCopyId() + "-" + getMapId();
//			}
//
//		};
//	}
//
//	@Override
//	public void onEnd(MissionMark markBeforPersonMove, cn.mxz.mission.MissionMap map) {
//
//		for (MissionListener m : listeners) {
//
//			m.onEnd(new EndEvent(this, city, map));
//
//			if(isNewPlayerEnd(markBeforPersonMove)) {
//
//				m.onNewPlayerEnd(new NewPlayerEndEvent(this, city));
//			}
//		}
//	}
//
//	/**
//	 * 新手引导是否结束
//	 * @param markBeforPersonMove	人物移动前关卡信息
//	 * @return
//	 */
//	private boolean isNewPlayerEnd(MissionMark markBeforPersonMove) {
//
//		int mapId = markBeforPersonMove.getMapId();
//
//		MapTemplet temp = MissionMapTempletConfig.get(mapId);
//
//		int isNew = temp.getIsNew();
//
//		MissionMark mark = getMark();
//
//		MapTemplet t2 = MissionMapTempletConfig.get(mark.getMapId());
//
//		int isNew2 = t2.getIsNew();
//
//		return isNew == 1 && isNew2 != 1;
//	}
//
//
//	@Override
//	protected DAOBase<db.domain.Mission> getMissionDAO() {
//		return DaoFactory.getMissionDAO();
//	}
//
//
//	@Override
//	protected DAO<String, MissionMap> getMissionMapDAO() {
//		return DaoFactory.getMissionMapDAO();
//	}
//
//	@Override
//	protected cn.mxz.mission.MissionMap newMap(int storyId) {
//
//		MapTemplet temp = MissionMapTempletConfig.get(storyId);
//
//		JsonMap map = new JsonMap(storyId, city, temp);
//
//		UserCounter his = city.getUserCounterHistory();
//
//		his.set(CounterKey.LAST_MAP_DEMON_MAX, map.getMaxDemonCount(), storyId);
//
//		return map;
//	}
//
//	@Override
//	public MissionPrize pickPrize() {
//
//		MissionPrize prize = this.prize;
//
//		this.prize = null;
//
//		return prize;
//	}
//
//	@Override
//	public void save(List<BattleExpPrize> fighterPrize,	List<PropPrize> propPrize, int star) {
//
//		this.prize = new MissionPrizeImpl(fighterPrize, propPrize, star);
//	}
//
//	@Override
//	public int getLastMapIdAttack() {
//
//		UserCounter uc = city.getUserCounterHistory();
//
//		Integer map = uc.get(CounterKey.LAST_MAP_ATTACK);//最后一次攻击的副本
//
//		if(map == null) {
//
//			map = 0;
//
//			uc.add(CounterKey.LAST_MAP_ATTACK, map);
//		}
//
//		return map;
//	}
//}
