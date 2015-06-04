package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.Map;
//
//import cn.javaplus.common.db.DAO;
//import cn.mxz.BossEventMapTempletConfig;
//import cn.mxz.MapTemplet;
//import cn.mxz.MissionMapTempletConfig;
//import cn.mxz.TowerMissionMapTempletConfig;
//import cn.mxz.activity.boss.BossMissionImpl;
//import cn.mxz.city.CityFactory;
//import cn.mxz.mission.listeners.EndListener;
//import cn.mxz.mission.listeners.NewPlayerEndListener;
//import cn.mxz.mission.listeners.RecoverHpListener;
//import cn.mxz.mission.map.DbMap;
//import cn.mxz.tower.TowerMission;
//import cn.mxz.tower.TowerMissionImpl;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.Captures;
//import cn.mxz.util.debuger.Debuger;
//import db.dao.factory.DaoFactory;
//import db.domain.BossMission;
//import db.domain.MissionMapData;
//
///**
// * 副本工厂
// *
// * @author 林岑
// */
//public class MissionFactory {
//
//	/**
//	 * 初始化一个副本
//	 *
//	 * @param city
//	 * @return
//	 */
//	public static NormalMission createMission(City city) {
//
//		final DAO<String, db.domain.Mission> DAO = DaoFactory.getMissionDAO();
//
//		db.domain.Mission m = DAO.get(city.getId());
//
//		boolean isNewUser = m == null;// 是否是新手
//
//		if (isNewUser) {
//
//			m = DAO.createDTO();
//
//			m.setUname(city.getId());
//
//			m.setMissionId(MissionMapTempletConfig.getMinKey() - 1);
//
//			DAO.add(m);
//		}
//
//		final MissionImpl mission = new MissionImpl(city);
//
//		mission.setDto(m);
//
//		MissionMap map = null;
//
//		map = createMissionMap(DaoFactory.getMissionMapDAO(), city, MissionMapTempletConfig.map);
//
//		mission.setCurrentMap(map);
//
//		mission.addListener(new RecoverHpListener());
//
//		mission.addListener(new EndListener());
//
//		mission.addListener(new ClearningStar());
//
//		mission.addListener(new NewPlayerEndListener());
//
//		mission.addListener(new SaveLastMapId());
//
//		mission.addListener(new StarManagerListener());
//		
//		mission.addListener(new GiveUpMapListener());
//
//		if (isNewUser) { // 初始化第一章地图, 如果不需要初始化, 直接屏蔽也没影响
//
//			mission.buildMap(MissionMapTempletConfig.getMinKey());
//		}
//
//		return mission;
//	}
//
//	/**
//	 * 爬塔副本
//	 * @param city
//	 * @return
//	 */
//	public static TowerMission createTowerMission(City city) {
//
//		final DAO<String, db.domain.TowerMission> DAO = DaoFactory.getTowerMissionDAO();
//
//		db.domain.TowerMission m = DAO.get(city.getId());
//
//		boolean isNewUser = m == null;
//
//		if (isNewUser) {
//
//			m = DAO.createDTO();
//
//			m.setUname(city.getId());
//
//			m.setMissionId(TowerMissionMapTempletConfig.getMaxKey());
//
//			DAO.add(m);
//		}
//
//		final TowerMissionImpl mission = new TowerMissionImpl(city);
//
//		mission.setDto(m);
//
//		mission.setCurrentMap(createMissionMap(DaoFactory.getTowerMissionMapDAO(), city, TowerMissionMapTempletConfig.map));
//
//		mission.addListener(new RecoverHpListener());
//
//		mission.addListener(new GiveUpMapListener());
//		
//		return mission;
//	}
//
//	/**
//	 * 初始化副本地图
//	 *
//	 * @param city
//	 * @param config
//	 * @return
//	 */
//	private static <MissionMapType extends MissionMapData> cn.mxz.mission.MissionMap createMissionMap(DAO<String, MissionMapType> DAO, City city, Map<Integer, ? extends MapTemplet> config) {
//
//		MissionMapType map = DAO.get(city.getId());
//
//		if (map == null) {
//
//			return null;
//		}
//
//		MapTemplet temp = config.get(map.getMapId());
//
//		cn.mxz.mission.MissionMap mp = new DbMap(map, city, temp);
//
//		Debuger.debug("MissionFactory.createMissionMap() 初始化地图中人物的位置:" + mp.getPerson().getLocation());
//
//
//		return mp;
//	}
//
//	public static cn.mxz.activity.boss.BossMission createBossMission(City city) {
//
//		final DAO<String, BossMission> DAO = DaoFactory.getBossMissionDAO();
//
//		db.domain.BossMission m = DAO.get(city.getId());
//
//		boolean isNewUser = m == null;// 是否是新手
//
//		if (isNewUser) {
//
//			m = DAO.createDTO();
//
//			m.setUname(city.getId());
//
//			m.setMissionId(BossEventMapTempletConfig.getMinKey() - 1);
//
//			DAO.add(m);
//		}
//
//
//		final BossMissionImpl mission = new BossMissionImpl(city);
//
//		mission.addListener(new RecoverHpListener());
//
//		mission.addListener(new GiveUpMapListener());
//
//		mission.setDto(m);
//
//		MissionMap map = createMissionMap(DaoFactory.getBossMissionMapDAO(), city, BossEventMapTempletConfig.map);
//
//		if(map != null) {
//
//			Person person = map.getPerson();
//
//			person.addListener(new RandomBossListener());
//		}
//
//		mission.setCurrentMap(map);
//
//		return mission;
//	}
//
//	public static Captures getCaptures(String id) {
//
//		return new CapturesImpl(CityFactory.getCity(id));
//	}
//}
