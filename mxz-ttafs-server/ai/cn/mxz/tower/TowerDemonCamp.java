//package cn.mxz.tower;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import cn.javaplus.common.util.Util.Random;
//import cn.mxz.FighterTemplet;
//import cn.mxz.FighterTempletConfig;
//import cn.mxz.battle.AbstractCamp;
//import cn.mxz.battle.Camp;
//import cn.mxz.fighter.Fighter;
//
//import com.google.common.collect.Lists;
//
//
//class TowerDemonCamp extends AbstractCamp<Fighter> {
//
//	private Camp<? extends Fighter>	camp;
//
//	/**
//	 * 裂缝怪物
//	 */
//	private Map<Integer, BugFighter> bugFighter;
//
//	TowerDemonCamp(Camp<? extends Fighter> camp) {
//
//		this.camp = camp;
//
//		generateBugFighter();
//	}
//
//	private void generateBugFighter() {
//
//		bugFighter = new HashMap<Integer, BugFighter>();
//
//		int nextInt = Random.get(1, 3);
//
//		for (int i = 0; i < nextInt; i++) {
//
//			if(isBugFighterAll()) {
//
//				break;
//			}
//
//			BugFighter build = build();
//
//			bugFighter.put(getRandomPos(), build);
//		}
//	}
//
//	private Integer getRandomPos() {
//
//		List<Integer> emptyPos = getEmptyPos();
//
//		return cn.javaplus.common.util.Util.Collection.getRandomOne(emptyPos);
//	}
//
//	/**
//	 * 所有不是裂缝怪物的位置
//	 * @return
//	 */
//	private List<Integer> getEmptyPos() {
//
//		List<Integer> list = new ArrayList<Integer>();
//
//		List<? extends Fighter> fighters = camp.getFighters();
//
//		for (Fighter demon : fighters) {
//
//			int p = camp.getPosition(demon);
//
//			if(!bugFighter.containsKey(p)) {
//
//				list.add(p);
//			}
//		}
//
//		return list;
//	}
//
//	private boolean isBugFighterAll() {
//
//		return camp.getFighters().size() <= bugFighter.size();
//	}
//
//	private BugFighter build() {
//
//		List<FighterTemplet> ls = FighterTempletConfig.findByIsBugDemo(1);
//
//		FighterTemplet temp = cn.javaplus.common.util.Util.Collection.getRandomOne(ls);
//
//		return new BugFighterImpl(temp);
//	}
//
//	@Override
//	public int getPosition(Fighter f) {
//
//		if(hasBeenReplace(f)) {
//
//			return -1;
//		}
//
//		if(bugFighter.containsValue(f)) {
//
//			for (Entry<Integer, BugFighter> e : bugFighter.entrySet()) {
//
//				BugFighter value = e.getValue();
//
//				Integer pos = e.getKey();
//
//				if(value.equals(f)) {
//
//					return pos;
//				}
//			}
//		}
//
//		return camp.getPosition(f);
//	}
//
//	@Override
//	public List<Fighter> getFighters() {
//
//		List<Fighter> fightersFront = Lists.newArrayList(camp.getFighters());
//
//		Iterator<Fighter> it = fightersFront.iterator();
//
//		while (it.hasNext()) {
//
//			Fighter demon = it.next();
//
//			if(hasBeenReplace(demon)) {
//
//				it.remove();
//			}
//		}
//
//		return cn.javaplus.common.util.Util.Collection.merge(fightersFront, getBugFighters());
//	}
//
//	/**
//	 * 判断demon是否被裂缝怪物给替换掉了
//	 * @param demon
//	 * @return
//	 */
//	private boolean hasBeenReplace(Fighter demon) {
//
//		int position = camp.getPosition(demon);
//
//		return bugFighter.containsKey(position);
//	}
//
//	private List<Fighter> getBugFighters() {
//
//		List<Fighter> list = new ArrayList<Fighter>();
//
//		Collection<BugFighter> values = bugFighter.values();
//
//		for (BugFighter bugFighter : values) {
//
//			list.add(bugFighter);
//		}
//
//		return list;
//	}
//
//	@Override
//	public int getShenJia() {
//		return camp.getShenJia();
//	}
//
//	@Override
//	public String getMainFighterName() {
//		return null;
//	}
//}
