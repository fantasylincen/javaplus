package cn.mxz.mission.old.map;
//package cn.mxz.mission.map;
//
//import java.util.List;
//
//import cn.mxz.MapTemplet;
//import cn.mxz.RandomEventTemplet;
//import cn.mxz.RandomEventTempletConfig;
//import cn.mxz.mission.Location;
//import cn.mxz.mission.LocationAble;
//import cn.mxz.mission.MapBoxImpl;
//import cn.mxz.mission.MapMoneyImpl;
//import cn.mxz.mission.MapNode;
//import cn.mxz.mission.MapRandomEventImpl;
//import cn.mxz.mission.MapStoryImpl;
//import cn.mxz.mission.MissionMap;
//import cn.mxz.mission.Person;
//import cn.mxz.mission.demon.MapDemonImpl;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.util.LocationImpl;
//
//import com.google.common.collect.Lists;
//
//import db.domain.MissionMapData;
//
//public final class DbMap extends AbstractMissionMap {
//
//	private final MissionMapData	map;
//
//	private MissionMap			jsonMap;
//
//	static final int			EMPTY	= -1;
//
//	protected City					city;
//
//	private MapTemplet	temp;
//
//	public DbMap(final MissionMapData map, City city, MapTemplet temp) {
//
//		this.map = map;
//
//		this.temp = temp;
//
//		jsonMap = new JsonMap(map.getMapId(), city, temp);
//
//		this.city = city;
//
//		jsonMap.getPerson().moveTo(map.getPersonPath(), map.getPersonIndex());
//
//		boxes = buildBoxes();
//
//		demons = buildDemons();
//
//		events = buildEvents();
//
//		staticDemons = buildStaticDemons();
//
//		staticBoxes = buildStaticBoxes();
//
//		staticEvents = buildStaticEvents();
//
//		staticMapMoney = buildStaticMapMoney();
//
//		staticGod = buildStaticGod();
//
//		staticStory = buildStaticStory();
//
//		fix();
//	}
//
//	private List<MapStoryImpl> buildStaticStory() {
//
//		final List<MapStoryImpl> ls = Lists.newArrayList();
//
//		List<MapStoryImpl> sd = ((JsonMap) jsonMap).getStaticStory();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			if (type == MapParser.STATIC_STORY) {
//
//				for (MapStoryImpl mn : sd) {
//
//					Location lc = mn.getLocation();
//
//					if (lc.getPath() == map.getPath(i) && lc.getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapDemonImpl> buildStaticDemons() {
//
//		final List<MapDemonImpl> ls = Lists.newArrayList();
//
//		List<MapDemonImpl> sd = ((JsonMap) jsonMap).getStaticDemons();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			int id = map.getGoodsId(i);
//
//			if (type == MapParser.STATIC_DEMONS && id != 0) {
//
//				for (MapDemonImpl mn : sd) {
//
//					Location lc = mn.getLocation();
//
//					if (lc.getPath() == map.getPath(i) && lc.getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//
//						setDemonIts(i, mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapBoxImpl> buildStaticBoxes() {
//
//		final List<MapBoxImpl> ls = Lists.newArrayList();
//
//		List<MapBoxImpl> staticBoxes2 = ((JsonMap) jsonMap).getStaticBoxes();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			if (type == MapParser.STATIC_BOXES) {
//
//				for (MapBoxImpl mn : staticBoxes2) {
//
//					if (mn.getLocation().getPath() == map.getPath(i) && mn.getLocation().getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapRandomEventImpl> buildStaticEvents() {
//
//		final List<MapRandomEventImpl> ls = Lists.newArrayList();
//
//		List<MapRandomEventImpl> staticEvents2 = ((JsonMap) jsonMap).getStaticEvents();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			if (type == MapParser.STATIC_EVENTS) {
//
//				for (MapRandomEventImpl mn : staticEvents2) {
//
//					if (mn.getLocation().getPath() == map.getPath(i) && mn.getLocation().getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapMoneyImpl> buildStaticMapMoney() {
//
//		final List<MapMoneyImpl> ls = Lists.newArrayList();
//
//		List<MapMoneyImpl> smm = ((JsonMap) jsonMap).getStaticMapMoney();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			boolean isStaticMapMoney = type == MapParser.STATIC_MAPMONEY;
//
//			if (isStaticMapMoney) {
//
//				for (MapMoneyImpl mn : smm) {
//
//					Location lc = mn.getLocation();
//
//					if (lc.getPath() == map.getPath(i) && lc.getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapDemonImpl> buildStaticGod() {
//
//		final List<MapDemonImpl> ls = Lists.newArrayList();
//
//		List<MapDemonImpl> staticGod2 = ((JsonMap) jsonMap).getStaticGod();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			if (type == MapParser.STATIC_GOD) {
//
//				for (MapDemonImpl mn : staticGod2) {
//
//					if (mn.getLocation().getPath() == map.getPath(i) && mn.getLocation().getIndex() == map.getIndex(i)) {
//
//						ls.add(mn);
//
//						setDemonIts(i, mn);
//					}
//				}
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapRandomEventImpl> buildEvents() {
//
//		final List<MapRandomEventImpl> ls = Lists.newArrayList();
//
//		Location l = null;
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			int id = map.getGoodsId(i);
//
//			l = new LocationImpl(map.getPath(i), map.getIndex(i));
//
//			RandomEventTemplet temp = RandomEventTempletConfig.get(id);
//
//			if (type == MapParser.QUESTION && temp != null) {
//
//				final MapRandomEventImpl db = new MapRandomEventImpl(id, l, temp.getPlot(), city, -1, -1);
//
//				ls.add(db);
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapDemonImpl> buildDemons() {
//
//		final List<MapDemonImpl> ls = Lists.newArrayList();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			if (type == MapParser.DEMON) {
//
//				short path = map.getPath(i);
//
//				short index = map.getIndex(i);
//
//				int goodsId = map.getGoodsId(i);
//
//				int mapId = map.getMapId();
//
//				MapDemonImpl db = new MapDemonImpl(city, temp, path, index, goodsId, false, -1, -1, false, false, mapId);
//
//				setDemonIts(i, db);
//
//				ls.add(db);
//			}
//		}
//
//		return ls;
//	}
//
//	private List<MapBoxImpl> buildBoxes() {
//
//		final List<MapBoxImpl> ls = Lists.newArrayList();
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short type = map.getGoodsType(i);
//
//			int id = map.getGoodsId(i);
//
//			if (type == MapParser.BOX && id != 0) {
//
//				final MapBoxImpl db = new MapBoxImpl(map.getPath(i), map.getIndex(i), id, -1, -1);
//
//				ls.add(db);
//			}
//		}
//
//		return ls;
//	}
//
//	@Override
//	public MapNode getStart() {
//		return jsonMap.getStart();
//	}
//
//	@Override
//	public Person getPerson() {
//		return jsonMap.getPerson();
//	}
//
//	@Override
//	public int getId() {
//		return map.getMapId();
//	}
//
//	@Override
//	public void remove(LocationAble l) {
//
//		if (l == null) {
//
//			return;
//		}
//
//		jsonMap.remove(l);
//
//		super.remove(l);
//
//		final int i = getIndex(l.getLocation());
//
//		map.setGoodsId(i, EMPTY);
//
//		map.setGoodsType(i, (short) EMPTY);
//	}
//
//	private int getIndex(final Location l) {
//
//		for (int i = 0; i < db.domain.MissionMap.GOODS_ID__LEN; i++) {
//
//			final short path = map.getPath(i);
//
//			final short index = map.getIndex(i);
//
//			if (path == l.getPath() && index == l.getIndex()) {
//
//				return i;
//			}
//		}
//
//		return -1;
//	}
//
//	/**
//	 * 初始化怪剧情
//	 *
//	 * @param i
//	 *            怪索引
//	 * @param db
//	 *            怪信息
//	 */
//	private void setDemonIts(int i, MapDemonImpl db) {
//
//		final short bossIndex = map.getBossIndex();
//
//		final int startDome = map.getStartDome();
//
//		final int endDome = map.getEndDome();
//
//		final int plotBegin1Id = map.getPlotBegin1Id();
//
//		final int plotBegin2Id = map.getPlotBegin2Id();
//
//		final int plotEnd1Id = map.getPlotEnd1Id();
//
//		final int plotEnd2Id = map.getPlotEnd2Id();
//
//		if (i == bossIndex) {
//
//			db.setBoss(true);
//		}
//
//		if (i == startDome) {
//
//			db.setFirstDemon(true);
//
//			db.setFirstStoryId(plotBegin1Id);
//
//			db.setSecondStoryId(plotBegin2Id);
//		}
//
//		if (i == endDome) {
//
//			db.setLastDemon(true);
//
//			db.setFirstStoryId(plotEnd1Id);
//
//			db.setSecondStoryId(plotEnd2Id);
//		}
//	}
//
//	@Override
//	public MissionMark getMark() {
//
//		return jsonMap.getMark();
//	}
//
////	@Override
////	public List<MapDemonImpl> getBoss() {
////
////		final List<MapDemonImpl> ls = Lists.newArrayList();
////
////		if (map.getBossPath() != -1) {
////
////			final MapDemonImpl db = new MapDemonImpl(temp, map.getBossPath(), map.getBossIndex(), map.getBossId(), true, map.getPlotEnd1Id(), map.getPlotEnd2Id(), false, true, map.getMapId());
////
////			ls.add(db);
////		}
////
////		return ls;
////	}
//
//}