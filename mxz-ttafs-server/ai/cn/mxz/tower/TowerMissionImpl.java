//package cn.mxz.tower;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.db.DAOBase;
//import cn.mxz.MapTemplet;
//import cn.mxz.TowerMissionMapTempletConfig;
//import cn.mxz.activity.boss.BossMissionPrize;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.event.EventDispatcher;
//import cn.mxz.event.EventDispatcherImpl;
//import cn.mxz.event.Listener;
//import cn.mxz.friend.Friend;
//import cn.mxz.friend.FriendManager;
//import cn.mxz.mission.AbstractMission;
//import cn.mxz.mission.MissionMap;
//import cn.mxz.mission.MissionPrize;
//import cn.mxz.mission.PropPrize;
//import cn.mxz.mission.map.JsonMap;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.MissionMark;
//import db.dao.factory.DaoFactory;
//import db.domain.TowerMissionMap;
//
//public class TowerMissionImpl extends AbstractMission<db.domain.TowerMission, TowerMissionMap> implements TowerMission {
//
//	private EventDispatcher dispatcher = new EventDispatcherImpl();
//
//	public TowerMissionImpl(City city) {
//
//		super(city);
//	}
//
//	public void addListener(Listener listener) {
//		dispatcher.addListener(listener);
//	}
//
//	@Override
//	protected DAOBase<db.domain.TowerMission> getMissionDAO() {
//		return DaoFactory.getTowerMissionDAO();
//	}
//
//	@Override
//	protected DAO<String, TowerMissionMap> getMissionMapDAO() {
//		return DaoFactory.getTowerMissionMapDAO();
//	}
//
//	private TowerFightingReward towerFightingReward;
//	private TowerBug bug;
//
//	@Override
//	public void save(TowerFightingReward towerFightingReward) {
//
//		this.towerFightingReward = towerFightingReward;
//	}
//
//	@Override
//	public TowerFightingReward getTowerFightingReward() {
//
//		return towerFightingReward;
//	}
//
//	@Override
//	public MissionMark getMark() {
//
//		return new MissionMark() {
//
//			MissionMap map = getCurrentMap();
//
//			@Override
//			public int getMissionId() {
//
//				return TowerMissionMapTempletConfig.get(getMapId())
//						.getChapterId();
//			}
//
//			@Override
//			public int getMapId() {
//
//				if (map != null) {
//
//					return map.getId() + 1;
//				}
//
//				return TowerMissionMapTempletConfig.getMaxKey();
//			}
//
//			@Override
//			public int getCopyId() {
//
//				return TowerMissionMapTempletConfig.get(getMapId())
//						.getSceneId();
//			}
//		};
//	}
//
//	@Override
//	public void onEnd(MissionMark mark, MissionMap mapId) {
//
//		dispatcher.dispatchEvent(new TowerMapEndEvent());
//	}
//
//	@Override
//	public TowerBug getBug() {
//
//		if (bug != null) { // 如果自己有裂缝, 就用自己的
//
//			return bug;
//		}
//
//		FriendManager fm = city.getActivityFriendManager();
//
//		List<Friend> all = fm.getAll();
//
//		for (Friend friend : all) {
//
//			City city = friend.getFriendCity();
//
//			TowerBug bug2 = city.getTowerMission().getBug();
//
//			if (bug2 != null) {
//
//				return bug2;
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	protected MissionMap newMap(int storyId) {
//
//		MapTemplet temp = TowerMissionMapTempletConfig.get(storyId);
//
//		return new JsonMap(storyId, city, temp);
//	}
//
//	@Override
//	public void setBug(TowerBug bug) {
//
//		this.bug = bug;
//	}
//
//	private BossMissionPrize prize;
//
//	@Override
//	public MissionPrize pickPrize() {
//
//		BossMissionPrize p = this.prize;
//		this.prize = null;
//		return p;
//
//	}
//
//	@Override
//	public void save(List<BattleExpPrize> fighterPrize,
//			List<PropPrize> propPrize, int id) {
//		this.prize = new BossMissionPrize(fighterPrize);
//	}
//
//}
