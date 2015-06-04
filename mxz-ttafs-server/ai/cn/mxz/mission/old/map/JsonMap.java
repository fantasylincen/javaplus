//package cn.mxz.mission.map;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import cn.javaplus.common.util.Util;
//import cn.mxz.BossEventMapTemplet;
//import cn.mxz.BoxOddsTemplet;
//import cn.mxz.BoxOddsTempletConfig;
//import cn.mxz.DemonGroupTemplet;
//import cn.mxz.DemonGroupTempletConfig;
//import cn.mxz.MapTemplet;
//import cn.mxz.MissionMapTemplet;
//import cn.mxz.RandomEventTemplet;
//import cn.mxz.RandomEventTempletConfig;
//import cn.mxz.TowerMissionMapTemplet;
//import cn.mxz.mission.Location;
//import cn.mxz.mission.MapBoxImpl;
//import cn.mxz.mission.MapMoneyImpl;
//import cn.mxz.mission.MapNode;
//import cn.mxz.mission.MapRandomEventImpl;
//import cn.mxz.mission.MapStoryImpl;
//import cn.mxz.mission.Person;
//import cn.mxz.mission.demon.MapDemonImpl;
//import cn.mxz.mission.person.PersonImpl;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.util.LocationImpl;
//import cn.mxz.util.debuger.Debuger;
//import cn.mxz.yunyou.YunYouObjects;
//import cn.mxz.yunyou.YunYouPlayer;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Sets;
//
//import define.D;
//
///**
// * @author 林岑
// *
// */
//public final class JsonMap extends AbstractMissionMap {
//
//	protected final List<MapNode>	allNode			= Lists.newArrayList();
//
//	private final List<MapNodeImpl>	bufferNodeImpl	= Lists.newArrayList();
//
//	protected Person				person;
//
//	protected int					mapId;
//
//	private City					city;
//
//	private MapTemplet				temp;
//
//	private Set<MapNodeImpl>		allRepeated		= Sets.newHashSet();
//
//	public JsonMap(final int mapId, City city, MapTemplet temp) {
//
//		this.city = city;
//
//		this.mapId = mapId;
//
//		this.temp = temp;
//
//		String data;
//		try {
//
//			Debuger.debug("JsonMap.JsonMap() 读取:" + mapId + ".json");
//
//			String path;
//
//			if (temp instanceof MissionMapTemplet) {
//
//				path = "map";
//
//			} else if (temp instanceof BossEventMapTemplet) {
//
//				path = "boss_map";
//
//			} else if (temp instanceof TowerMissionMapTemplet) {
//
//				path = "tower_map";
//
//			} else {
//
//				throw new RuntimeException("无法识别的地图类型:" + temp.getClass().getSimpleName());
//			}
//			//
//			// Enumeration currentCategories = Logger.getCurrentCategories();
//			// while (currentCategories.hasMoreElements()) {
//			// Logger object = (Logger) currentCategories.nextElement();
//			// System.out.println("test: " + object.getName());
//			// }
//
//			data = readJsonData("res/" + path + "/" + mapId + ".json");
//
//		} catch (Exception e) {
//
//			throw new RuntimeException("mapId = " + mapId, e);
//		}
//
//		final JSONArray array = JSONArray.fromObject(data);
//
//		JSONObject jsonObject = null;
//
//		MapBean mp = null;
//
//		boolean isBig = false;
//
//		String[] nexts;
//
//		boolean isEnd = false;
//
//		MapNodeImpl mapNodeImpl = null;
//
//		for (int i = 0; i < array.size(); i++) {
//
//			for (int j = 0; j < array.getJSONArray(i).size(); j++) {
//
//				jsonObject = array.getJSONArray(i).getJSONObject(j);
//
//				mp = (MapBean) JSONObject.toBean(jsonObject, MapBean.class);
//
//				if (mp.getO() == 2 || mp.getO() == 3) {
//
//					isBig = true;
//					isEnd = false;
//				} else {
//
//					if (mp.getO() == -2) {
//
//						isEnd = true;
//						isBig = true;
//					} else {
//
//						isBig = false;
//						isEnd = false;
//					}
//				}
//
//				nexts = mp.getN().split(",");
//
//				int parseInt = Integer.parseInt(mp.getX());
//				int parseInt2 = Integer.parseInt(mp.getY());
//				int parseInt3 = Integer.parseInt(mp.getP());
//				int parseInt4 = Integer.parseInt(mp.getI());
//				mapNodeImpl = new MapNodeImpl(parseInt, parseInt2, parseInt3, parseInt4, isBig, isEnd, false, mp.getO(), nexts);
//
//				add(mapNodeImpl);
//
//				if (!"".equals(mp.getT())) {
//
//					setStatices(mp, mapNodeImpl);
//				}
//			}
//		}
//
//		// 初始化BOSS
//		setBoss(temp);
//
//		if (temp.getIsNew() == 0) {
//
//			// 初始化怪物和宝箱
//			setDemonsAndBoxes(temp);
//		}
//
//		int index = allNode.get(0).getLocation().getIndex();
//
//		int path = allNode.get(0).getLocation().getPath();
//
//		person = new PersonImpl(index, path, city);
//
//		fix();
//	}
//
//	private void add(MapNodeImpl mapNodeImpl) {
//
//		if (allNode.contains(mapNodeImpl)) {
//
//			allRepeated.add(mapNodeImpl);
//		}
//
//		allNode.add(mapNodeImpl);
//
//		// Debuger.debug("JsonMap.add() 所有重复的:" + allRepeated.size() + ":" +
//		// allRepeated);
//	}
//
//	class MapNodeImpl implements MapNode {
//
//		private final int			path;
//
//		private final int			index;
//
//		private final int			o;
//
//		private final boolean		isBig;
//
//		private final boolean		isEnd;
//
//		private int					x;
//
//		private int					y;
//
//		private boolean				hasWhat;
//
//		private final List<Integer>	nexts	= Lists.newArrayList();
//
//		public MapNodeImpl(int x, int y, final int path, final int index, final boolean isBig, final boolean isEnd, final boolean hasWhat, final int o, final String[] next) {
//
//			this.x = x;
//
//			this.y = y;
//
//			this.path = path;
//
//			this.index = index;
//
//			this.isBig = isBig;
//
//			this.isEnd = isEnd;
//
//			this.hasWhat = hasWhat;
//
//			this.o = o;
//
//			for (final String i : next) {
//
//				if (!i.trim().isEmpty()) {
//
//					nexts.add(Integer.parseInt(i));
//				}
//			}
//		}
//
//		public int getX() {
//
//			return x;
//		}
//
//		public int getY() {
//
//			return y;
//		}
//
//		public int getPath() {
//
//			return path;
//		}
//
//		public boolean isHasWhat() {
//
//			return hasWhat;
//		}
//
//		public void setHasWhat(final boolean hasWhat) {
//
//			this.hasWhat = hasWhat;
//		}
//
//		@Override
//		public boolean isEnd() {
//
//			return isEnd;
//		}
//
//		@Override
//		public boolean hasNext() {
//
//			if (o == -2) {
//
//				return false;
//			} else {
//
//				return true;
//			}
//		}
//
//		@Override
//		public String toString() {
//			return getLocation().toString();
//		}
//
//		@Override
//		public List<MapNode> next() {
//
//			final List<MapNode> ms = Lists.newArrayList();
//
//			for (final MapNode n : allNode) {
//
//				if (n.getLocation().getPath() == getLocation().getPath() && n.getLocation().getIndex() - 1 == getLocation().getIndex()) {
//
//					ms.add(n);
//
//					return ms;
//				}
//			}
//
//			for (final Integer path : nexts) {
//
//				for (final MapNode n : allNode) {
//
//					if (n.getLocation().getPath() == path) {
//
//						ms.add(n);
//
//						break;
//					}
//				}
//			}
//
//			return ms;
//		}
//
//		@Override
//		public boolean isBig() {
//
//			return isBig;
//		}
//
//		@Override
//		public Location getLocation() {
//
//			return new LocationImpl(path, index);
//		}
//
//		@Override
//		public List<MapNode> getNearest() {
//
//			return getBigStones();
//		}
//
//		@Override
//		public boolean isEmpty() {
//
//			Location l = getLocation();
//
//			return !l.isIn(getDemons()) && !l.isIn(getBoxes()) && !l.isIn(getEvents()) && !l.isIn(getGod()) && !l.isIn(getMapMoney()) /*
//																																	 * &&
//																																	 * !
//																																	 * l
//																																	 * .
//																																	 * isIn
//																																	 * (
//																																	 * getBoss
//																																	 * (
//																																	 * )
//																																	 * )
//																																	 */;
//		}
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + getOuterType().hashCode();
//			result = prime * result + x;
//			result = prime * result + y;
//			return result;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			MapNodeImpl other = (MapNodeImpl) obj;
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
//			if (x != other.x)
//				return false;
//			if (y != other.y)
//				return false;
//			return true;
//		}
//
//		@Override
//		public MapNode nextBig() {
//
//			List<MapNode> next = next();
//
//			return findBig(next);
//		}
//
//		private MapNode findBig(List<MapNode> next) {
//
//			for (MapNode mapNode : next) {
//
//				if (mapNode.isBig()) {
//
//					return mapNode;
//
//				} else {
//
//					return findBig(mapNode.next());
//				}
//			}
//
//			return null;
//		}
//
//		private JsonMap getOuterType() {
//			return JsonMap.this;
//		}
//
//	}
//
//	public List<MapNode> getAllNode() {
//
//		return allNode;
//	}
//
//	protected void setStatices(MapBean mp, MapNodeImpl mapNodeImpl) {
//
//		String[] vIds = mp.getV().split(",");
//
//		int t = Integer.parseInt(mp.getT());
//
//		int parseInt0 = -1;
//
//		int parseInt1 = -1;
//
//		int parseInt2 = -1;
//
//		try {
//
//			parseInt0 = Integer.parseInt(vIds[0]);
//
//		} catch (IndexOutOfBoundsException e) {
//		}
//
//		try {
//
//			parseInt1 = Integer.parseInt(vIds[1]);
//
//		} catch (IndexOutOfBoundsException e) {
//		}
//
//		try {
//
//			parseInt2 = Integer.parseInt(vIds[2]);
//
//		} catch (IndexOutOfBoundsException e) {
//		}
//
//		int path = mapNodeImpl.getLocation().getPath();
//
//		int index = mapNodeImpl.getLocation().getIndex();
//
//		switch (t) {
//
//		case 1:
//
//			staticBoxes.add(new MapBoxImpl(path, index, parseInt0, parseInt1, parseInt2));
//
//			break;
//
//		case 2:
//
//			staticMapMoney.add(new MapMoneyImpl(path, index, parseInt0, parseInt1, parseInt2, city));
//
//			break;
//
//		case 3:
//
//			staticEvents.add(new MapRandomEventImpl(parseInt0, mapNodeImpl.getLocation(), RandomEventTempletConfig.get(parseInt0).getPlot(), city, parseInt1, parseInt2));
//
//			break;
//
//		case 4:
//
//			DemonGroupTemplet tep = DemonGroupTempletConfig.get(parseInt0);
//
//			if (tep == null) {
//
//				throw new NullPointerException(parseInt0 + "");
//			}
//
//			String group = tep.getGroup();
//
//			String text = group.split("\\|")[0];
//
//			int fighterId = Integer.parseInt(text.split(",")[0]);
//
//			staticDemons.add(new MapDemonImpl(temp, path, index, fighterId, false, parseInt1, parseInt2, false, false, getId(), parseInt0));
//
//			break;
//
//		case 5:
//
//			staticStory.add(new MapStoryImpl(path, index, parseInt0, parseInt1));
//
//			break;
//		}
//
//		mapNodeImpl.setHasWhat(true);
//	}
//
//	/**
//	 * 算出有多少条路
//	 *
//	 * @return 多少条路
//	 */
//	private int findWayCount() {
//
//		MapNodeImpl mapNode = (MapNodeImpl) allNode.get(allNode.size() - 1);
//
//		if (mapNode.isEnd()) {
//
//			return mapNode.getPath();
//		} else {
//
//			for (MapNode node : allNode) {
//
//				MapNodeImpl mNode = (MapNodeImpl) node;
//
//				if (mNode.isEnd()) {
//
//					return mNode.getPath();
//				}
//			}
//		}
//
//		return 0;
//	}
//
//	/**
//	 * 根据多少条路分别出来
//	 *
//	 * @param mNode
//	 *            存储路
//	 * @param path
//	 *            第path条路
//	 */
//	private void setMapNode(List<MapNode> mNode, int path) {
//
//		for (MapNode node : allNode) {
//
//			MapNodeImpl n = (MapNodeImpl) node;
//
//			if (n.getPath() == path) {
//
//				mNode.add(n);
//			}
//		}
//	}
//
//	/**
//	 * 初始化BOSS
//	 *
//	 * @param missionMapTemplet
//	 *            地图配置表
//	 */
//	protected void setBoss(MapTemplet missionMapTemplet) {
//
//		MapNodeImpl mapNode = (MapNodeImpl) allNode.get(allNode.size() - 1);
//
//		MapNode lastButOne = getLastButOne(); // 倒数第二个石块
//
//		// 获取最后一个石块；
//		if (!mapNode.isEnd()) {
//
//			for (MapNode node : allNode) {
//
//				MapNodeImpl mNode = (MapNodeImpl) node;
//
//				boolean end = mNode.isEnd();
//
//				if (end) {
//
//					mapNode = mNode;
//				}
//			}
//		}
//
//		String bossIds = missionMapTemplet.getBossId();
//
//		if (!("".equals(bossIds))) {
//
//			int plotEnd1 = missionMapTemplet.getPlotEnd1();
//
//			int plotEnd2 = missionMapTemplet.getPlotEnd2();
//
//			int path = lastButOne.getLocation().getPath();
//			int index = lastButOne.getLocation().getIndex();
//
//			String[] split = bossIds.split(",");
//
//			int parseInt = Integer.parseInt(split[Util.R.nextInt(split.length)]);
//
//			for (final MapDemonImpl d : getDemons()) {
//
//				final boolean equals = d.getLocation().equals(new LocationImpl(path, index));
//
//				if (equals) {
//
//					remove(d); // 移除已有的怪物
//				}
//			}
//
//			demons.add(new MapDemonImpl(city, missionMapTemplet, path, index, parseInt, true, plotEnd1, plotEnd2, false, true, getId()));
//
//			((MapNodeImpl) lastButOne).setHasWhat(true);
//
//		}
//	}
//
//	/**
//	 * 倒数第二块大石头
//	 *
//	 * @param mapNode
//	 * @return
//	 */
//	private MapNode getLastButOne() {
//
//		MapNode mapNode = allNode.get(0);
//
//		if (mapNode.isEnd()) {
//
//			throw new RuntimeException("该地图只有一个节点?");
//		}
//
//		while (true) {
//
//			MapNode ns = mapNode.nextBig();
//
//			boolean end;
//			try {
//				end = ns.isEnd();
//			} catch (Exception e) {
//
//				throw new RuntimeException("地图ID:" + mapId);
//			}
//
//			if (end) {
//
//				return mapNode;
//
//			} else {
//
//				mapNode = ns;
//
//				// Debuger.debug("JsonMap.getLastButOne() 后续节点:" +
//				// ns.getLocation() + " Node : " + mapNode.getLocation());
//
//			}
//		}
//	}
//
//	/**
//	 * 初始化怪物
//	 *
//	 * @param mNode
//	 *            初始化路
//	 * @param monsterId
//	 *            初始化怪ID
//	 * @param demonScale
//	 *            放怪比例
//	 * @param missionMapTemplet
//	 *            地图信息
//	 */
//	private void setDemons(List<MapNode> mNode, String[] monsterId, float demonScale, String starOrEnd, MapTemplet missionMapTemplet) {
//
//		final Random r = new Random();
//
//		int bigCount = 0;
//
//		for (MapNode md : mNode) {
//
//			if (md.isBig()) {
//
//				bigCount++;
//			}
//		}
//
//		int rockNumber = (int) (bigCount * demonScale);
//
//		// 怪ID
//		int demonId = 0;
//
//		// 设置怪坐标
//		int postion = 0;
//
//		MapNodeImpl bufferNode = null;
//
//		boolean unhas = true;
//
//		int j = 0;
//
//		for (int i = 0; i < rockNumber && j < 1000000; j++) {
//
//			demonId = Integer.parseInt(monsterId[r.nextInt(monsterId.length)]);
//
//			postion = r.nextInt(mNode.size());
//
//			bufferNode = (MapNodeImpl) mNode.get(postion);
//
//			if (allRepeated.contains(bufferNode)) {
//
//				continue;
//			}
//
//			// 若是大石头 && 没有放置东西
//			if (bufferNode.isBig() && !bufferNode.isHasWhat()) {
//
//				for (MapNodeImpl mipl : bufferNodeImpl) {
//
//					if (bufferNode.getX() == mipl.getX() && bufferNode.getY() == mipl.getY()) {
//
//						unhas = false;
//
//						break;
//					}
//				}
//
//				if (unhas) {
//
//					int path = bufferNode.getLocation().getPath();
//					int index = bufferNode.getLocation().getIndex();
//
//					MapDemonImpl m = new MapDemonImpl(city, missionMapTemplet, path, index, demonId, false, -1, -1, false, false, getId());
//
//					Debuger.debug("JsonMap.setDemons() 生成的怪物位置:" + m.getLocation());
//
//					if ("start".equals(starOrEnd)) {
//
//						if (postion != 0) {
//
//							demons.add(m);
//
//							bufferNode.setHasWhat(true);
//
//							i++;
//						}
//
//					} else if ("end".equals(starOrEnd)) {
//
//						if (!bufferNode.isEnd()) {
//
//							demons.add(m);
//
//							bufferNode.setHasWhat(true);
//
//							i++;
//						}
//
//					} else {
//
//						demons.add(m);
//
//						bufferNode.setHasWhat(true);
//
//						i++;
//					}
//
//					bufferNodeImpl.add(bufferNode);
//
//				} else {
//
//					unhas = true;
//				}
//			}
//		}
//
//		setFirstDemonIts(missionMapTemplet);
//
//		setLastDemonIts(missionMapTemplet);
//	}
//
//	/**
//	 * 给第一个怪设置剧情
//	 *
//	 * @param missionMapTemplet
//	 *            地图信息
//	 */
//	private void setFirstDemonIts(MapTemplet missionMapTemplet) {
//
//		if (demons.isEmpty()) {
//
//			return;
//		}
//
//		MapDemonImpl firstDemon = demons.get(0);
//
//		for (MapDemonImpl demon : demons) {
//
//			if (firstDemon.getLocation().getPath() > demon.getLocation().getPath() && firstDemon.getLocation().getIndex() > demon.getLocation().getIndex()) {
//
//				firstDemon = demon;
//			}
//		}
//
//		firstDemon.setFirstDemon(true);
//
//		firstDemon.setFirstStoryId(missionMapTemplet.getPlotBegin1());
//
//		firstDemon.setSecondStoryId(missionMapTemplet.getPlotBegin2());
//	}
//
//	/**
//	 * 给最后一个怪设置剧情
//	 *
//	 * @param missionMapTemplet
//	 *            地图信息
//	 *
//	 * @return 无意义
//	 */
//	private boolean setLastDemonIts(MapTemplet missionMapTemplet) {
//
//		if (demons.isEmpty()) {
//
//			return false;
//		}
//
//		MapDemonImpl lastDemon = demons.get(0);
//
//		for (MapDemonImpl demon : demons) {
//
//			if (lastDemon.getLocation().getPath() < demon.getLocation().getPath() && lastDemon.getLocation().getIndex() < demon.getLocation().getIndex()) {
//
//				lastDemon = demon;
//			}
//		}
//
//		lastDemon.setLastDemon(true);
//
//		lastDemon.setFirstStoryId(missionMapTemplet.getPlotEnd1());
//
//		lastDemon.setSecondStoryId(missionMapTemplet.getPlotEnd2());
//
//		return true;
//	}
//
//	/**
//	 * 初始化怪物以及宝箱
//	 */
//	protected void setDemonsAndBoxes(MapTemplet temp) {
//
//		// 怪物种类ID
//		String[] monsterId = temp.getMonsterId().split(",");
//
//		// 每条路怪物所在比例
//		float demonScale = temp.getDemonScale();
//
//		float boxScale = temp.getBoxScale();
//
//		float randomScale = 1 - (demonScale + boxScale + temp.getIgnoreScale());
//
//		List<MapNode> mNode = null;
//
//		for (int i = 0; i < findWayCount(); i++) {
//
//			mNode = Lists.newArrayList();
//
//			setMapNode(mNode, (i + 1));
//
//			String startOrEnd = "center";
//
//			if (i == 0) {
//
//				startOrEnd = "start";
//
//			} else if (i == findWayCount() - 1) {
//
//				startOrEnd = "end";
//			}
//
//			setDemons(mNode, monsterId, demonScale, startOrEnd, temp);
//
//			setBoxes(mNode, boxScale, startOrEnd);
//
//			initRandomEvents(mNode, randomScale, startOrEnd);
//		}
//	}
//
//	private void initRandomEvents(List<MapNode> mNode, float evnetsScale, String starOrEnd) {
//
//		final Random r = new Random();
//
//		int bigCount = 0;
//
//		for (MapNode md : mNode) {
//
//			if (md.isBig()) {
//
//				bigCount++;
//			}
//		}
//
//		float fl = bigCount * evnetsScale;
//
//		int rockNumber = 0;
//
//		if (fl > 0.5 && fl < 1.0) {
//
//			rockNumber = 1;
//		} else if (fl > 1) {
//
//			rockNumber = (int) fl;
//		}
//
//		// 设置怪坐标
//		int postion = 0;
//
//		MapNodeImpl bufferNode = null;
//
//		int j = 0;
//
//		for (int i = 0; i < rockNumber && j < 100000; j++) {
//
//			postion = r.nextInt(mNode.size());
//
//			bufferNode = (MapNodeImpl) mNode.get(postion);
//
//			if (allRepeated.contains(bufferNode)) {
//
//				continue;
//			}
//
//			RandomEventTemplet temp = new RandomEventGenerator().generate(this.temp);
//
//			
//			// 若是大石头 && 没有放置东西
//			if (bufferNode.isBig() && !bufferNode.isHasWhat() && temp != null) {
//
//				if(getCount(temp) >= temp.getMaxInMap()) {	//如果已经生成的事件数量已经达到了最大值
//					continue;
//				}
//				
//				if(temp.getId() == D.YUN_YOU_XIAN_REN_EVENT_ID) {
//					YunYouPlayer player = YunYouObjects.getPlayer(city.getId());
//					if(player.getXianRenCount() >= D.MAX_YUN_YOU_XIAN_REN_COUNT) {	//如果已经遇到了云游先人, 就不要生成云游先人事件
//						continue;
//					}
//				}
//				
//				int plot = temp.getPlot();
//
//				MapRandomEventImpl mm = new MapRandomEventImpl(temp.getId(), bufferNode.getLocation(), plot, city, -1, -1);
//
//				if ("start".equals(starOrEnd)) {
//
//					if (postion != 0) {
//
//						i = addEvent(bufferNode, i, mm);
//					}
//
//				} else if ("end".equals(starOrEnd)) {
//
//					if (!bufferNode.isEnd()) {
//
//						i = addEvent(bufferNode, i, mm);
//
//					}
//
//				} else {
//
//					i = addEvent(bufferNode, i, mm);
//				}
//			}
//		}
//	}
//
//	/**
//	 * 获得当前地图内, 某个随机事件的数量
//	 * @param temp2
//	 * @return
//	 */
//	private int getCount(RandomEventTemplet temp2) {
//		int count = 0;
//		for (MapRandomEventImpl m : events) {
//			if(m.getId() == temp2.getId()) {
//				count ++;
//			}
//		}
//		return count;
//	}
//
//	private int addEvent(MapNodeImpl bufferNode, int i, MapRandomEventImpl mm) {
//
//		events.add(mm);
//
//		bufferNode.setHasWhat(true);
//
//		i++;
//
//		return i;
//
//	}
//
//	/**
//	 * 初始化宝箱
//	 */
//	private void setBoxes(List<MapNode> mNode, float boxScale, String starOrEnd) {
//
//		int bigCount = 0;
//
//		for (MapNode md : mNode) {
//
//			if (md.isBig()) {
//
//				bigCount++;
//			}
//		}
//
//		int rockNumber = (int) (bigCount * boxScale);
//
//		// 设置怪坐标
//		int postion = 0;
//
//		MapNodeImpl bufferNode = null;
//
//		int j = 0;
//
//		for (int i = 0; i < rockNumber && j < 100000; j++) {
//
//			int[] ws = BoxOddsTempletConfig.getArrayByBoxWeight();
//
//			int index = Util.getRandomIndex(ws);
//
//			BoxOddsTemplet temp = BoxOddsTempletConfig.get(BoxOddsTempletConfig.getKeys().get(index));
//
//			postion = Util.R.nextInt(mNode.size());
//
//			bufferNode = (MapNodeImpl) mNode.get(postion);
//
//			if (allRepeated.contains(bufferNode)) {
//
//				continue;
//			}
//
//			// 若是大石头 && 没有放置东西
//			if (bufferNode.isBig() && !bufferNode.isHasWhat()) {
//
//				int boxId = temp.getBoxId();
//
//				if (!showAble(temp)) {
//
//					continue;
//				}
//
//				boolean isMoney = (boxId == D.MONEY_BAG_ID || boxId == D.BIG_MONEY_BAG_ID);
//
//				if ("start".equals(starOrEnd)) {
//
//					if (postion != 0) {
//
//						addBox(boxId, bufferNode, isMoney);
//
//						i++;
//					}
//
//				} else if ("end".equals(starOrEnd)) {
//
//					if (!bufferNode.isEnd()) {
//
//						addBox(boxId, bufferNode, isMoney);
//
//						i++;
//
//					}
//
//				} else {
//
//					addBox(boxId, bufferNode, isMoney);
//
//					i++;
//				}
//			}
//		}
//
//	}
//
//	/**
//	 * 宝箱是否可以出现在地图上
//	 *
//	 * @param temp2
//	 * @return
//	 */
//	private boolean showAble(BoxOddsTemplet temp2) {
//
//		String mapId2 = temp2.getMapId().trim();
//
//		String[] split = mapId2.split("-");
//
//		try {
//
//			int min = new Integer(split[0].trim());
//
//			int max = new Integer(split[1].trim());
//
//			return min <= mapId && mapId <= max;
//
//		} catch (NumberFormatException e) {
//
//			return true;
//		}
//	}
//
//	private void addBox(int boxId, MapNodeImpl bufferNode, boolean isMoney) {
//
//		int path = bufferNode.getLocation().getPath();
//
//		int index = bufferNode.getLocation().getIndex();
//
//		if (isMoney) {
//
//			MapMoneyImpl e = new MapMoneyImpl(path, index, boxId, -1, -1, city);
//
//			mapMoney.add(e);
//
//		} else {
//
//			MapBoxImpl mb = new MapBoxImpl(path, index, boxId, -1, -1);
//
//			boxes.add(mb);
//		}
//
//		bufferNode.setHasWhat(true);
//	}
//
//	@Override
//	public MapNode getEnd() {
//
//		for (final MapNode mNode : allNode) {
//
//			if (((MapNodeImpl) mNode).isEnd()) {
//
//				return mNode;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public MapNode getStart() {
//
//		return allNode.get(0);
//	}
//
//	@Override
//	public Person getPerson() {
//
//		return person;
//	}
//
//	@Override
//	public int getId() {
//
//		return mapId;
//	}
//
//	protected String readJsonData(final String path) {
//
//		String data = "";
//
//		final File file = new File(path);
//
//		BufferedReader reader = null;
//
//		try {
//
//			reader = new BufferedReader(new FileReader(file));
//
//			String tempString = "";
//
//			// 一次读入一行，直到读入null为文件结束
//			while ((tempString = reader.readLine()) != null) {
//
//				data = data + tempString;
//
//			}
//
//			reader.close();
//
//		} catch (final IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			if (reader != null) {
//
//				try {
//
//					reader.close();
//
//				} catch (final IOException e) {
//
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return data;
//	}
//
//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
//	@Override
//	public MissionMark getMark() {
//
//		return new MissionMark() {
//
//			int	chapterId	= temp.getChapterId();
//
//			int	sceneId		= temp.getSceneId();
//
//			int	id			= getId();
//
//			@Override
//			public int getMissionId() {
//				return chapterId;
//			}
//
//			@Override
//			public int getCopyId() {
//				return sceneId;
//			}
//
//			@Override
//			public int getMapId() {
//				return id;
//			}
//
//			@Override
//			public String toString() {
//				return getMissionId() + "-" + getCopyId() + "-" + getMapId();
//			}
//		};
//	}
//
//	/**
//	 * 这张地图玩家最大可遇到的怪物数量
//	 * @return
//	 */
//	public int getMaxDemonCount() {
//
//		int count = 0;
//		MapNode start = getStart();
//
//		List<MapDemonImpl> demons2 = getDemons();
//
//		while (start.hasNext()) {
//			start = start.next().get(0);
//			if(start.isBig()) {
//				Location l = start.getLocation();
//
//				for (final MapDemonImpl d : demons2) {
//
//					final boolean equals = d.getLocation().equals( new LocationImpl(l.getPath(), l.getIndex()));
//
//					if (equals) {
//						count++;
//					}
//				}
//			}
//		}
//
//		return count;
//	}
//
//}