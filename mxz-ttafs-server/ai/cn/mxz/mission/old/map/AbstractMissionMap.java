//package cn.mxz.mission.old.map;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import message.S;
//import cn.javaplus.common.util.Util;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.mission.old.Location;
//import cn.mxz.mission.old.LocationAble;
//import cn.mxz.mission.old.MapBox;
//import cn.mxz.mission.old.MapBoxImpl;
//import cn.mxz.mission.old.MapDemon;
//import cn.mxz.mission.old.MapMoneyImpl;
//import cn.mxz.mission.old.MapNode;
//import cn.mxz.mission.old.MapPath;
//import cn.mxz.mission.old.MapRandomEvent;
//import cn.mxz.mission.old.MapRandomEventImpl;
//import cn.mxz.mission.old.MapStoryImpl;
//import cn.mxz.mission.old.MissionMap;
//import cn.mxz.mission.old.Person;
//import cn.mxz.mission.old.demon.MapDemonImpl;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.util.LocationImpl;
//
//import com.google.common.collect.Lists;
//
///**
// * 剧情地图
// *
// * @author 林岑
// */
//public abstract class AbstractMissionMap implements MissionMap {
//
//	public class MapPathImpl implements MapPath {
//
//		private final List<MapNode> nodes;
//
//		private MapPathImpl(final int path) {
//
//			nodes = getStones();
//
//			final Iterator<MapNode> it = nodes.iterator();
//
//			while (it.hasNext()) {
//
//				final MapNode n = it.next();
//
//				if (n.getLocation().getPath() != path) {
//
//					it.remove();
//				}
//			}
//		}
//
//		@Override
//		public MapNode getNode(final int index) {
//
//			for (final MapNode n : nodes) {
//
//				if (n.getLocation().getIndex() == index) {
//
//					return n;
//				}
//			}
//
//			return null;
//		}
//
//	}
//
//	protected List<MapDemonImpl> demons = Lists.newArrayList();
//
//	protected List<MapBoxImpl> boxes = Lists.newArrayList();
//
//	protected List<MapRandomEventImpl> events = Lists.newArrayList();
//
//	protected List<MapMoneyImpl> mapMoney = Lists.newArrayList();
//
//	private List<MapDemonImpl> god = Lists.newArrayList();
//
//	protected List<MapDemonImpl> staticDemons = Lists.newArrayList();
//
//	protected List<MapBoxImpl> staticBoxes = Lists.newArrayList();
//
//	protected List<MapRandomEventImpl> staticEvents = Lists.newArrayList();
//
//	protected List<MapMoneyImpl> staticMapMoney = Lists.newArrayList();
//
//	protected List<MapDemonImpl> staticGod = Lists.newArrayList();
//
//	protected List<MapStoryImpl> staticStory = Lists.newArrayList();
//
//	@Override
//	public void remove(final LocationAble o) {
//
//		demons.remove(o);
//		boxes.remove(o);
//		events.remove(o);
//		mapMoney.remove(o);
//		god.remove(o);
//
//		staticDemons.remove(o);
//		staticBoxes.remove(o);
//		staticEvents.remove(o);
//		staticMapMoney.remove(o);
//		staticGod.remove(o);
//	}
//
//	@Override
//	public MapNode getEnd() {
//
//		MapNode node = getStart();
//
//		while (node.hasNext()) {
//
//			final List<MapNode> next = node.next();
//
//
//			try {
//
//				node = next.get(0);
//
//			} catch (final Exception e) {
//
//				throw new RuntimeException("地图有问题!", e);
//			}
//		}
//
//		return node;
//	}
//
//	@Override
//	public final MapDemon getDemon(final int path, final int index) {
//
////		List<MapDemonImpl> demons2 = Util.merge(getDemons() , getBoss());
//
//		for (final MapDemonImpl d : getDemons()) {
//
//			final boolean equals = d.getLocation().equals( new LocationImpl(path, index));
//
//			if (equals) {
//
//				return d;
//			}
//		}
//
//		throw new OperationFaildException(S.S10037, index);
//	}
//
//	@Override
//	public final List<MapNode> getBigStones() {
//		return getStones(true);
//	}
//
//	/**
//	 * 获得所有指定的石头
//	 *
//	 * @param isBig
//	 *            是否是大石头
//	 * @return
//	 */
//	private List<MapNode> getStones() {
//
//		final List<MapNode> ls = Lists.newArrayList();
//
//		final MapNode node = getStart();
//
//		addNode(node, ls);
//
//		if (node.hasNext()) {
//
//			addNext(node.next(), ls);
//		}
//
//		return ls;
//	}
//
//	private void addNode(final MapNode node, final List<MapNode> ls) {
//		ls.add(node);
//	}
//
//	private void addNext(final List<MapNode> next, final List<MapNode> ls) {
//
//		for (final MapNode mn : next) {
//
//			addNode(mn, ls);
//
//			if (mn.hasNext()) {
//
//				addNext(mn.next(), ls);
//			}
//		}
//	}
//
//	@Override
//	public final List<MapNode> getSmallStones() {
//		return getStones(false);
//	}
//
//	private List<MapNode> getStones(final boolean isBig) {
//
//		final List<MapNode> stones = getStones();
//
//		final Iterator<MapNode> it = stones.iterator();
//
//		while (it.hasNext()) {
//
//			final MapNode n = it.next();
//
//			if (n.isBig() != isBig) {
//
//				it.remove();
//			}
//
//		}
//		return stones;
//	}
//
//	@Override
//	public boolean isEnd() {
//
//		final Location personLocation = getPerson().getLocation();
//
//		final Location endLocation = getEnd().getLocation();
//
//		return personLocation.equals(endLocation);
//	}
//
//	@Override
//	public final float getCompleteness() {
//		return 0.5f;
//	}
//
//	@Override
//	public MapNode getPersonNode() {
//
////		System.out.println("AbstractMissionMap.getPersonNode() + " + getPerson().getLocation());
////		
////		for (MapNode m : getBigStones()) {
////			System.out.println("AbstractMissionMap.getPersonNode()" + m.getLocation());
////		}
////		
//		return getTouched(getPerson(), getBigStones());
//	}
//
//	private static <T extends LocationAble> T getTouched(final Person p, final List<T> s) {
//
//		for (final T b : s) {
//
//			if (p.getLocation().equals(b.getLocation())) {
//
//				return b;
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public MapPath getPath(final int path) {
//
//		return new MapPathImpl(path);
//	}
//
//	@Override
//	public abstract MissionMark getMark();
//
//	@Override
//	public final MapBox getBoxTouched() {
//
//		return getTouched(getPerson(), getBoxes());
//	}
//
//	@Override
//	public final MapRandomEvent getRandomTouched() {
//
//		return getTouched(getPerson(), getEvents());
//	}
//
//	@Override
//	public final MapMoneyImpl getMapMoneyTouched() {
//
//		return getTouched(getPerson(), getMapMoney());
//	}
//
//	@Override
//	public final List<MapDemonImpl> getDemons() {
//
//		return Util.merge(getStaticDemons(), getDynamicDemons());
//	}
//
//	protected void fix() {
//
//		final Set<Location> map = new HashSet<Location>();
//
//		put(staticDemons, map);
//		put(staticBoxes, map);
//		put(staticEvents, map);
//		put(staticMapMoney, map);
//		put(staticGod, map);
//		put(staticStory, map);
//
//		put(demons, map);
//		put(boxes, map);
//		put(events, map);
//		put(mapMoney, map);
//		put(god, map);
//
//		MapNode end = getEnd();
//
//		Iterator<MapDemonImpl> it = demons.iterator();
//
//		while (it.hasNext()) {
//
//			MapDemonImpl next = it.next();
//
//			if(next.getLocation().equals(end.getLocation())) {
//
//				it.remove();
//			}
//		}
//	}
//
//	private void put(final List<? extends LocationAble> ds, final Set<Location> set) {
//
//		final Iterator<? extends LocationAble> it = ds.iterator();
//
//		while (it.hasNext()) {
//
//			final LocationAble next = it.next();
//
//			if (set.contains(next.getLocation())) {
//
//				it.remove();
//			}
//
//			set.add(next.getLocation());
//
//		}
//	}
//
//	@Override
//	public final List<MapBoxImpl> getBoxes() {
//
//		return Util.merge(getStaticBoxes(), getDynamicBoxes());
//	}
//
//	@Override
//	public final List<MapRandomEventImpl> getEvents() {
//
//		return Util.merge(getStaticEvents(), getDynamicEvents());
//	}
//
//	@Override
//	public final List<MapDemonImpl> getGod() {
//
//		return Util.merge(getStaticGod(), getDynamicGod());
//	}
//
//	@Override
//	public final List<MapMoneyImpl> getMapMoney() {
//
//		return Util.merge(getStaticMapMoney(), getDynamicMapMoney());
//	}
//
//	@Override
//	public final List<MapDemonImpl> getDynamicDemons() {
//
//		return demons;
//	}
//
//	@Override
//	public final List<MapBoxImpl> getDynamicBoxes() {
//
////		for (MapBoxImpl b : boxes) {
////
////			Debuger.debug("AbstractMissionMap.getDynamicBoxes() 宝箱ID:" + b.getId());
////		}
//
//		return boxes;
//	}
//
//	@Override
//	public final List<MapRandomEventImpl> getDynamicEvents() {
//
//		return events;
//	}
//
//	@Override
//	public final List<MapDemonImpl> getDynamicGod() {
//		return god;
//	}
//
//	@Override
//	public final List<MapMoneyImpl> getDynamicMapMoney() {
//		return mapMoney;
//	}
//
//	@Override
//	public final List<MapDemonImpl> getStaticDemons() {
//
//		return staticDemons;
//	}
//
//	@Override
//	public final List<MapBoxImpl> getStaticBoxes() {
//
//		return staticBoxes;
//	}
//
//	@Override
//	public final List<MapRandomEventImpl> getStaticEvents() {
//
//		return staticEvents;
//	}
//
//	@Override
//	public final List<MapDemonImpl> getStaticGod() {
//
//		return staticGod;
//	}
//
//	@Override
//	public final List<MapMoneyImpl> getStaticMapMoney() {
//
//		return staticMapMoney;
//	}
//
//	@Override
//	public final List<MapStoryImpl> getStaticStory() {
//
//		return staticStory;
//	}
//}
