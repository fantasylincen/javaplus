package cn.mxz.mission.old.map;
//package cn.mxz.mission.map;
//
//import cn.mxz.mission.Location;
//import cn.mxz.mission.MapBox;
//import cn.mxz.mission.MapDemon;
//import cn.mxz.mission.MapMoney;
//import cn.mxz.mission.MapRandomEvent;
//import cn.mxz.mission.MapRandomEventImpl;
//import cn.mxz.mission.MapStory;
//import cn.mxz.mission.MissionMap;
//import cn.mxz.mission.Person;
//import db.domain.MissionMapData;
//
//
///**
// *
// * 地图转换器
// * @author 林岑
// *
// */
//public class MapParser <MissionMapType extends MissionMapData> {
//
//	/** 妖怪 */
//	static final short DEMON = 1;
//
//	/** 宝箱 */
//	public static final short BOX = 2;
//
//	/** 问号 */
//	static final short QUESTION = 3;
//
//	/** 感叹号 */
//	public static final short EXCLAMATION = 4;
//
//	/** BOSS */
//	public static final short BOSS = 5;
//
//	static final short STATIC_DEMONS = 6;
//
//	static final short STATIC_BOXES = 7;
//
//	static final short STATIC_EVENTS = 8;
//
//	static final short STATIC_MAPMONEY = 9;
//
//	static final short STATIC_GOD = 10;
//
//	static final short STATIC_STORY = 11;
//
//
//	/**
//	 * 将副本地图转换为数据库交互对象
//	 * @param mp
//	 * @param userId
//	 * @param newMap	空白地图对象
//	 * @return
//	 */
//	public MissionMapType parse(MissionMap mp, String userId, MissionMapType newMap) {
//
//		final Person person = mp.getPerson();
//
////		List<MapDemonImpl> boss2 = mp.getBoss();
////
////		if(!boss2.isEmpty()) {
////
////			newMap.setBossId(boss2.get(0).getId());
////
////			newMap.setBossIndex((short)boss2.get(0).getLocation().getIndex());
////
////			newMap.setBossPath((short)boss2.get(0).getLocation().getPath());
////
////            newMap.setPlotEnd1Id(boss2.get(0).getFirstStoryId());
////
////			newMap.setPlotEnd2Id(boss2.get(0).getSecondStoryId());
////
////			newMap.setEndDome(boss2.get(0).getLocation().getIndex());
////
////		} else {
//
//			newMap.setBossId(DbMap.EMPTY);
//
//			newMap.setBossIndex((short)DbMap.EMPTY);
//
//			newMap.setBossPath((short)DbMap.EMPTY);
////		}
//
//		newMap.setUname(userId);
//
//		newMap.setMapId(mp.getId());
//
//
//		savePerson(newMap, person);
//
//
//		int index = 0;
//
//		index = saveBoxes(mp, newMap, index);
//
//		index = savaStaticGod(mp, newMap, index);
//
//		index = saveStaticMapMoney(mp, newMap, index);
//
//		index = saveStaticEvents(mp, newMap, index);
//
//		index = saveStaticDemons(mp, newMap, index);
//
//		index = saveStaticBoxes(mp, newMap, index);
//
//		index = saveStaticStory(mp, newMap, index);
//
//		index = saveDemons(mp, newMap, index);
//
//        index = saveEvents(mp, newMap, index);
//
//		return newMap;
//	}
//
//	private void savePerson(final MissionMapType map, final Person person) {
//		final Location location = person.getLocation();
//
//		map.setPersonPath(location.getPath());
//
//		map.setPersonIndex(location.getIndex());
//	}
//
//	private int saveEvents(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapRandomEventImpl mEvents : mp.getEvents()) {
//
//			map.setGoodsType(index, QUESTION);
//
//			Location l = mEvents.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, mEvents.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveDemons(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapDemon p : mp.getDemons()) {
//
//			map.setGoodsType(index, DEMON);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			if(p.isFirstDemon()) {
//
//				map.setStartDome(index);
//
//                map.setPlotBegin1Id(p.getFirstStoryId());
//
//				map.setPlotBegin2Id(p.getSecondStoryId());
//
//			} else if(p.isLastDemon()) {
//
//				map.setEndDome(index);
//
//                map.setPlotEnd1Id(p.getFirstStoryId());
//
//				map.setPlotEnd2Id(p.getSecondStoryId());
//			}
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveStaticStory(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapStory p : mp.getStaticStory()) {
//
//			map.setGoodsType(index, STATIC_STORY);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, 1);
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveStaticBoxes(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapBox p : mp.getStaticBoxes()) {
//
//			map.setGoodsType(index, STATIC_BOXES);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveStaticDemons(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapDemon p : mp.getStaticDemons()) {
//
//			map.setGoodsType(index, STATIC_DEMONS);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveStaticEvents(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapRandomEvent p : mp.getStaticEvents()) {
//
//			map.setGoodsType(index, STATIC_EVENTS);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveStaticMapMoney(MissionMap mp, final MissionMapType map,
//			int index) {
//
//		for (MapMoney p : mp.getStaticMapMoney()) {
//
//			map.setGoodsType(index, STATIC_MAPMONEY);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int savaStaticGod(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapDemon p : mp.getStaticGod()) {
//
//			map.setGoodsType(index, STATIC_GOD);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//	private int saveBoxes(MissionMap mp, final MissionMapType map,
//			int index) {
//		for (MapBox p : mp.getBoxes()) {
//
//			map.setGoodsType(index, BOX);
//
//			Location l = p.getLocation();
//
//			map.setPath(index, (short) l.getPath());
//
//			map.setIndex(index, (short) l.getIndex());
//
//			map.setGoodsId(index, p.getId());
//
//			index ++;
//		}
//		return index;
//	}
//
//}
