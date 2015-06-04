package cn.mxz.newpvp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.collections.list.ArrayListReadOnly;
import cn.javaplus.util.Util;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.events.Events;
import cn.mxz.events.RandomPvpRobotEvent;
import cn.mxz.events.newpvp.PvpPlaceResortEvent;
import cn.mxz.script.Script;
import cn.mxz.util.debuger.SystemLog;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import db.GameDB;
import db.dao.impl.PvpDao;
import db.dao.impl.PvpDao1;
import db.domain.Pvp;
import db.domain.PvpImpl;

public class PvpPlaceImpl implements PvpPlace {

	private static PvpPlaceImpl instance;
	private List<PvpPlayer> players;

	private PvpPlaceImpl() {
		init();
	}

	@Override
	public void init() {
		players = Lists.newArrayList();
		PvpDao DAO = getDAO();
		List<Pvp> all = DAO.getAll();
		for (Pvp pvp : all) {
			City city = CityFactory.getCity(pvp.getUname());
			if (city == null) {
				DAO.delete(pvp);
				SystemLog.debug("PvpPlaceImpl.initPlayers() 删除死用户:"
						+ pvp.getUname());
				continue;
			}
			players.add(new PvpPlayerImpl(pvp));
		}
		SystemLog.debug("PvpPlaceImpl.initPlayers() Pvp排行榜初始化成功! 人数:"
				+ players.size());
		resort();
	}

	private PvpDao getDAO() {
		return new PvpDao1(GameDB.getInstance());
	}

	@Override
	public void resort() {

		ArrayList<PvpPlayer> ps = Lists.newArrayList(players);
		Collections.sort(ps);
		players = ps;
		Events.getInstance().dispatch(new PvpPlaceResortEvent(players));
	}

	public static final PvpPlace getInstance() {
		if (instance == null) {
			instance = new PvpPlaceImpl();
		}
		return instance;
	}

	@Override
	public int getRankInAll(String id) {

		int rank = 1;
		for (PvpPlayer p : players) {
			String uname = p.getDto().getUname();
			if (uname.equals(id)) {
				break;
			}
			rank++;
		}

		return rank;
	}

	@Override
	public int getRankInAll(PvpPlayer p) {
		int index = players.indexOf(p);
		if (index == -1) {
			return players.size() + 1;// 最后一名
		}
		return index + 1;
	}

	private synchronized PvpPlayer find(String userId) {
		for (PvpPlayer p : players) {
			String uname = p.getDto().getUname();
			if (uname.equals(userId)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public PvpPlayer get(String id) {
		PvpPlayer find = find(id);
		if (find == null) {
			if (CityFactory.getCity(id) != null) {
				find = init(id);
			}
		}

		return find;
	}

	private PvpPlayer init(String id) {
		Pvp p = new PvpImpl();
		p.setDanId(DanRewardTempletConfig.getMinKey());
		p.setPractice(0);
		p.setUname(id);

		PvpPlayerImpl player = new PvpPlayerImpl(p);
		players.add(player);

		PvpDao dao = getDAO();
		dao.save(p);

		resort();
		return player;
	}

	@Override
	public PvpPlayer getFirst() {
		// List<NewPvpPlayer> tops = getTopsToday();
		List<PvpPlayer> tops = getTops(1, 10);

		// if (tops.isEmpty()) {
		// throw new NoFirstException();
		// }

		if (tops.isEmpty()) {
			return null;
		}
		return tops.get(0);
	}

	// /**
	// * 获取和战斗力相近的玩家
	// *
	// * @param player
	// * @param count
	// * 数量
	// * @return
	// */
	// private Collection<NewPvpPlayer> getRandomByCapacity(NewPvpPlayer player,
	// int count) {
	//
	// Set<NewPvpPlayer> ls = Sets.newHashSet();
	//
	// addByCapacity(player, count, ls);
	//
	// addNearest(player, count, ls);
	//
	// return ls;
	// }

	// private void addByCapacity(NewPvpPlayer player, int count,
	// Set<NewPvpPlayer> ls) {
	//
	// Scope s = getCapacityScope(player);
	//
	// List<String> random = new UserRandom().random((int) s.getMin(),
	// (int) s.getMax(), count);
	//
	// random.remove(player.getId());
	// for (String uname : random) {
	// ls.add(get(uname));
	// }
	// }
	//
	// private void addNearest(NewPvpPlayer player, int count, Set<NewPvpPlayer>
	// ls) {
	// // int c = 0;
	//
	// List<NewPvpPlayer> nearests = Lists.newArrayList(getNearests(player
	// .getId()));
	// nearests.remove(player);
	// Util.Collection.upset(nearests);
	//
	// Iterator<NewPvpPlayer> it = nearests.iterator();
	//
	// while (it.hasNext() && ls.size() < count) {
	// //
	// ls.add(it.next());
	//
	// // NewPvpPlayer randomPlayer = getRandomPlayer(player, s);
	// // ls.add(randomPlayer);
	// }
	// }

	// // 当前身价为5329 随机到的玩家身价高10W以上的，低的只有3000多。。。。
	// // l 常规排位对手寻找规则： 　 　 　 　
	// // Ø 仙位内进行随机； 　 　 　 　 　
	// // Ø 随机范围战斗力=[玩家当前战斗力*（1-20%），玩家当前战斗力*（1+100%）]
	// // Ø 真随机； 　 　 　 　 　
	// // l 级别晋级赛对手寻找规则： 　 　 　 　
	// // Ø 仙位内进行随机； 　 　 　 　 　
	// // Ø 随机范围战斗力=[玩家当前战斗力*（1-5%），玩家当前战斗力*（1+10%）]
	// // Ø 伪随机：晋级赛中第一场一定随机出一个低于玩家当前战斗力的玩家；
	// private Scope getCapacityScope(NewPvpPlayer player) {
	// boolean up = player.isInUpMatch();
	// int c = getShenJia(player);
	// if (up) {
	// return new Scope((long) (c * 0.8f), c * 2);
	// } else {
	// return new Scope((long) (c * 0.95f), (long) (c * 1.1f));
	// }
	// }
	//
	// private int getShenJia(NewPvpPlayer player) {
	// int c = player.getCity().getFormation().getShenJia();
	// return c;
	// }

	@Override
	public List<PvpPlayer> getAll() {
		return new ArrayListReadOnly<PvpPlayer>(players);
	}

	@Override
	public List<PvpPlayer> getNearests(String userId) {
		int up = 10;
		int down = 10;

		List<PvpPlayer> nearests = getNearests(userId, up, down);
		return nearests;
	}

	private List<PvpPlayer> getNearests(String userId, int up, int down) {

		PvpPlayer player = find(userId);

		if (player == null) {
			return last(down);
		}
		int index = players.indexOf(player);

		return sub(index, up, down);
	}

	/**
	 * 截取某段PVP玩家
	 * 
	 * @param index
	 *            位置
	 * @param pvpUpVisible
	 *            前方可见多少个人
	 * @param pvpDownVisible
	 *            后方可见多少个人
	 * @return
	 */
	private List<PvpPlayer> sub(int index, int pvpUpVisible, int pvpDownVisible) {
		int from = index - pvpUpVisible;
		int to = index + pvpDownVisible;

		from = Math.max(from, 0);
		to = Math.min(to, players.size());

		return new ArrayListReadOnly<PvpPlayer>(players.subList(from, to));
	}

	/**
	 * 排行榜最后count个人
	 * 
	 * @param count
	 * @return
	 */
	private List<PvpPlayer> last(int count) {
		int size = players.size();
		count = Math.min(count, size);
		int from = size - count;
		return new ArrayListReadOnly<PvpPlayer>(players.subList(from, size));
	}

	@Override
	public List<PvpPlayer> getTops(int rankStart, int count) {

		int index = rankStart - 1;

		if (count > 30) {
			throw new SureIllegalOperationException("-最多只能获取30个");
		}

		int from = Math.max(index, 0);
		int to = index + count;
		to = Math.min(to, players.size());

		return new ArrayListReadOnly<PvpPlayer>(players.subList(from, to));
	}

	@Override
	public List<PvpFightUser> getRandomUsers(City city) {

		// /** 1、初始匹配：段位比自己高一级、相同级、低一级（同时战斗力的取值范围1.05-1.15,0.95-1.05,0.85-0.95）
		// */
		// public static final String PVP_RANDOM_USER =
		// "1:1.05-1.15,0:0.95-1.05,-1:0.85-0.95";

		// 1、初始匹配：段位比自己高一级、相同级、低一级（同时战斗力的取值范围1.05-1.15,0.95-1.05,0.85-0.95）
		// 2、胜利后：随机的3个敌人段位+1级，但最高不能超过5级（战斗力范围上下限增加0.1）。晋级后这个胜利清0；恢复到初始匹配
		// 3、如果没有玩家，用机器人替代。
		// 4、如果连续失败3场，则难度降低1级，但最低不超过初始匹配。
		List<PvpFightUser> ls = Lists.newArrayList();

		PvpPlayer me = city.getNewPvpManager().getPlayer();

		List<Double> s = Script.Pvp.getShenJiaScope(me.getShenJia(),
				me.getCurrentWinTimesInDanId(), me.getLoseStreak());

		int dan = me.getDan();

		// /2014年7月25日 13:40:30 屏蔽 规则变更, 先找段位相同的, 如果段位没有相同的, 找比他高1段位/低1段位的 ..
		// 同时去掉战斗力 限制 jjy
		// List<Double> lvScope = Script.Pvp.getLevelScope(dan,
		// me.getCurrentWinStreak(), me.getLoseStreak());
		//
		//
		//

		Integer min = DanRewardTempletConfig.getMinKey();
		Integer max = DanRewardTempletConfig.getMaxKey();
		int danMax = dan;
		int danMin = dan;
		while (true) {

			if (danMax == danMin) {
				ls.addAll(getRandomPlayers(me, 3, danMax));
			} else {
				ls.addAll(getRandomPlayers(me, 3, danMax, danMin));
			}

			if (ls.size() >= 3) {
				break;
			}

			danMax++;
			danMin--;

			danMax = Math.min(max, danMax);
			danMin = Math.max(min, danMin);

			if (danMax == max && danMin == min) {
				break;
			}

		}

		while (ls.size() > 3) {
			ls.remove(ls.size() - 1);
		}

		Iterator<Double> it = s.iterator();
		while (ls.size() < 3) {
			Double min1 = it.next();
			Double max1 = it.next();
			ls.add(buildRobot(me, min1, max1));
		}

		// for (int i = 0; i < s.size(); i += 2) {
		// Double min = s.get(i);
		// Double max = s.get(i + 1);
		// double levelAdd = lvScope.get(i / 2);
		//
		// ls.add(getRandomPlayer(me, (int) levelAdd, min, max));
		// }

		Events.getInstance().dispatch(new RandomPvpRobotEvent(ls, me));
		city.getNewPvpManager().saveRandomUsers(ls);
//
//		 if (city.isTester() || city.getId().equals("535762368536") || city.getId().equals("431306719659")) {
//			 ls.clear();
//			 add(ls, "535762368536", "431306719659", "lc1");
//		 }

		return ls;
	}

	// private PvpFightUser getRandomPlayer(PvpPlayer me, int levelAdd,
	// double minPercent, double maxPercent) {
	//
	// int minSj = (int) (me.getShenJia() * minPercent);
	// int maxSj = (int) (me.getShenJia() * maxPercent);
	//
	// // List<NewPvpPlayer> ps = getByDanId(me, levelAdd + me.getDanId());
	// List<PvpPlayer> ps = getByDanId(me);
	//
	// ps.remove(me);
	//
	// if (ps.isEmpty()) {
	// return buildRobot(me, minPercent, maxPercent);
	// }
	//
	// ps = getByCapacity(ps, minSj, maxSj);
	//
	// ps.remove(me);
	//
	// if (ps.isEmpty()) {
	// return buildRobot(me, minPercent, maxPercent);
	// }
	//
	// Util.Collection.upset(ps);
	//
	// PvpPlayer player = ps.get(0);
	//
	// if(player.getId().equals(me.getId())) {
	// return buildRobot(me, minPercent, maxPercent);
	// }
	//
	// return new NormalPvpFightUser(player);
	// }

	private void add(List<PvpFightUser> ls, String... string) {
		for (String id : string) {
			City city2 = CityFactory.getCity(id);
			if (city2 != null) {
				PvpPlayer p = city2.getNewPvpManager().getPlayer();
				NormalPvpFightUser pl = new NormalPvpFightUser(p);
				ls.add(pl);
			}
		}
	}

	private Collection<PvpFightUser> getRandomPlayers(PvpPlayer me, int countMax, int... dans) {
		ArrayList<PvpFightUser> ls = Lists.newArrayList();
		List<PvpPlayer> ps = getByDanId(countMax, dans);

		ps.remove(me);

		Util.Collection.upset(ps);

		for (PvpPlayer p : ps) {
			ls.add(new NormalPvpFightUser(p));
		}
		return ls;
	}

	//
	// private List<PvpPlayer> getByDanId(PvpPlayer me) {
	// int dan = me.getDan();
	// List<PvpPlayer> ls = Lists.newArrayList();
	// for (PvpPlayer aa : getAll()) {
	// if (Math.abs(aa.getDan() - dan) == 1) {
	// int sj = aa.getShenJia();
	// if (sj <= 0) {
	// aa.updateShenJia();
	// }
	// ls.add(aa);
	// }
	// }
	//
	// Util.Collection.upset(ls);
	// return Util.Collection.sub(ls, 3);
	// }

	/**
	 * 最大返回3个 返回所有段位为danIds的玩家
	 * 
	 * @param countMax 最大返回数量
	 * @param danIds
	 * @return
	 */
	private List<PvpPlayer> getByDanId(int countMax, int... danIds) {
		HashSet<Integer> set = Sets.newHashSet();
		for (int i : danIds) {
			set.add(i);
		}

		List<PvpPlayer> ls = Lists.newArrayList();
		List<PvpPlayer> all = Lists.newArrayList(getAll());
		Util.Collection.upset(all);
		
		for (PvpPlayer aa : all) {
			if(countMax <= 0) {
				break;
			}
			if (set.contains(aa.getDan())) {
				int sj = aa.getShenJia();
				if (sj <= 0) {
					aa.updateShenJia();
				}
				
				City city = aa.getCity();
				FunctionOpenManager m = city.getFunctionOpenManager();
				boolean isOpenDouFa = m.isOpen(ModuleType.DouFaMoKuai);
				if (isOpenDouFa) {
					ls.add(aa);
					countMax--;
				}
			}
		}

		Util.Collection.upset(ls);
		return Util.Collection.sub(ls, 3);
	}

	private PvpFightUser buildRobot(PvpPlayer me, double minPercent,
			double maxPercent) {
		City city = me.getCity();
		return new PvpRobotUser(city, minPercent, maxPercent);
	}

	// private List<PvpPlayer> getByCapacity(List<PvpPlayer> ps, int min, int
	// max) {
	//
	// List<PvpPlayer> ls = Lists.newArrayList();
	// for (PvpPlayer p : ps) {
	// if (p.getShenJia() <= max && min <= p.getShenJia()) {
	// ls.add(p);
	// }
	// }
	// return ls;
	//
	// }

	//
	// private List<NewPvpPlayer> getByDanId(NewPvpPlayer me, int l) {
	// List<NewPvpPlayer> ls = Lists.newArrayList();
	// for (NewPvpPlayer p : players) {
	// if (p.getDanId() == l) {
	// ls.add(p);
	// }
	// }
	// return ls;
	// }

	@Override
	public PvpPlayer getLastTianZun() {
		List<PvpPlayer> all = getTianZuns();

		Collections.reverse(all);
		for (PvpPlayer p : all) {
			return p;
		}
		return null;
	}

	@Override
	public List<PvpPlayer> getTianZuns() {
		List<PvpPlayer> all = getAll();
		List<PvpPlayer> sub = Util.Collection.sub(all, 12);
		Iterator<PvpPlayer> it = sub.iterator();
		while (it.hasNext()) {
			PvpPlayer n = it.next();
			if (!n.isTianZun()) {
				it.remove();
			}
		}
		return sub;
	}

	// @Override
	// public int getTianZunCount() {
	// List<NewPvpPlayer> all = getAll();
	// int danLvMax = getDanLvMax();
	// int count = 0;
	// for (NewPvpPlayer p : all) {
	// int danId = p.getDanId();
	// DanRewardTemplet temp = DanRewardTempletConfig.get(danId);
	// int lv = temp.getDanLv();
	// if (lv == danLvMax) {
	// count++;
	// } else {
	// break;
	// }
	// }
	// return count;
	// }
	//
	// private int getDanLvMax() {
	// List<DanRewardTemplet> all = DanRewardTempletConfig.getAll();
	// Fetcher<DanRewardTemplet, Integer> f = new
	// IntegerFetcher<DanRewardTemplet>() {
	//
	// @Override
	// public Integer get(DanRewardTemplet t) {
	// return t.getDanLv();
	// }
	// };
	//
	// List<Integer> allk = Util.Collection.getListByOneFields(f, all);
	//
	// return Util.Collection.getMax(allk);
	// }

	// 1、天尊一共只能有10个，排名第一的是大天尊
	// 2、当一品玄仙胜点达到100，且胜率高于其中一名天尊，才会触发晋级赛
	// 3、成功晋级后，之前的第10名玩家变成一品玄仙，且保留60-90点胜点。新晋级玩家按天尊排名。
	// 达到一品玄仙且胜点达到100，可以晋级到天尊，天尊一共只有10个位置，排名第一的是大天尊

	// old
	// @Override
	// public List<NewPvpPlayer> getRandomUsers(City city) {
	//
	// PvpManager m = city.getNewPvpManager();
	// NewPvpPlayer player = m.getPlayer();
	//
	// int level = player.getDanLevel();
	//
	// /** 随机获取3个和我战斗力相近的玩家 */
	// Collection<NewPvpPlayer> a = getRandomByCapacity(player,
	// D.PVP_RANDOM_COUNT);
	//
	// ArrayList<NewPvpPlayer> ls = Lists.newArrayList(a);
	// Util.Collection.upset(ls);
	// return new ArrayListReadOnly<NewPvpPlayer>(ls);
	// }

	// /**
	// * 获得一个战斗力在范围s中的玩家
	// *
	// * @param player
	// * @param s
	// * @return
	// */
	// private NewPvpPlayer getRandomPlayer(NewPvpPlayer player, Scope s) {
	// for (int i = 0; i < 1000; i++) {
	// NewPvpPlayer random = Util.Random.getRandomOneWithOut(player, players);
	// int c = getShenJia(random);
	// if (random.getUname().equals(player.getUname())) {
	// continue;
	// }
	// if (s.contains(c)) {
	// random.getCity().getPlayer().updateShenJia();
	// return random;
	// }
	// }
	//
	// return Util.Random.getRandomOneWithOut(player, players);
	// }
	//
	// /**
	// * 获取所有段位等级为level的玩家
	// *
	// * @param level
	// * @return
	// */
	// private List<NewPvpPlayer> getByDanLevel(int level) {
	// List<NewPvpPlayer> ls = Lists.newArrayList();
	// for (NewPvpPlayer p : this.players) {
	// int lv = p.getDanLevel();
	// if (lv == level) {
	// ls.add(p);
	// }
	// }
	// return ls;
	// }
	//
	// @Override
	// public List<NewPvpPlayer> getTopsToday() {
	//
	// DAO2<Integer, String, PvpTops> DAO = DaoFactory.getPvpTopsDAO();
	//
	// int count = DAO.getCount();
	//
	// if (count == 0) {
	// initTops();
	// saveUpdateLastTime();
	// }
	//
	// if (timeUp()) {
	// deleteTops();
	// initTops();
	// saveUpdateLastTime();
	// }
	//
	// List<PvpTops> all = DAO.getAll();
	//
	// return build(all);
	//
	// }
	//
	// private List<NewPvpPlayer> build(List<PvpTops> all) {
	// List<NewPvpPlayer> ls = Lists.newArrayList();
	// for (PvpTops pt : all) {
	//
	// ls.add(get(pt.getUname()));
	// }
	// return ls;
	// }
	//
	// private void initTops() {
	//
	// DAO2<Integer, String, PvpTops> DAO = DaoFactory.getPvpTopsDAO();
	// List<NewPvpPlayer> tops = getTops(1, 10);
	//
	// PvpTops pt = new PvpTopsImpl();
	//
	// int rank = 1;
	// for (NewPvpPlayer p : tops) {
	// pt.setUname(p.getUname());
	// pt.setRank(rank);
	// rank++;
	//
	// DAO.add(pt);
	// }
	// }
	//
	// private void deleteTops() {
	// DAO2<Integer, String, PvpTops> DAO = DaoFactory.getPvpTopsDAO();
	// DAO.clear();
	// }
	//
	// /**
	// * 记录最后更新时间
	// */
	// private void saveUpdateLastTime() {
	// KeyValueManager kv = new KeyValueManagerImpl();
	// kv.put(KeyValueDefine.PVP_TOPS_UPDATE_DAY, Util.Time.getCurrentDay());
	// }
	//
	// /**
	// * 是否过时了
	// *
	// * @return
	// */
	// private boolean timeUp() {
	// KeyValueManager kv = new KeyValueManagerImpl();
	// String day = kv.get(KeyValueDefine.PVP_TOPS_UPDATE_DAY);
	// Integer d = new Integer(day);
	// return Util.Time.getCurrentDay() != d;
	// }
}
