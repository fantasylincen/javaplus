package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.time.taskutil.TaskSafety;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.IRole;
import cn.vgame.a.account.Role;
import cn.vgame.a.events.Events;
import cn.vgame.a.gen.dto.MongoGen.CaiJinLogDao;
import cn.vgame.a.gen.dto.MongoGen.CaiJinLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.SystemKeyValueDao;
import cn.vgame.a.gen.dto.MongoGen.SystemKeyValueDto;
import cn.vgame.a.gm.test.TestRole;
import cn.vgame.a.message.MessageManager;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.a.robot.Robot;
import cn.vgame.a.robot.RobotManager;
import cn.vgame.a.system.Const;
import cn.vgame.a.turntable.GetAllSwitchsAction.Xs;
import cn.vgame.a.turntable.generator.ExcelGenerator;
import cn.vgame.a.turntable.generator.PZResultGenerator;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.a.zhuang.Zhuang;
import cn.vgame.a.zhuang.ZhuangManager;
import cn.vgame.share.CacheManager;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;
import cn.vgame.share.Xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Turntable {

	public class Controller {

		private final class TunFenComparator implements Comparator<String> {
			@Override
			public int compare(String o1, String o2) {
				long l1 = getLose(o1);
				long l2 = getLose(o2);

				return (int) (l1 - l2);
			}
		}

		private final class TuFenComparator implements Comparator<String> {
			@Override
			public int compare(String o1, String o2) {
				long l1 = getLose(o1);
				long l2 = getLose(o2);

				return (int) (l2 - l1);
			}
		}

		private boolean isQiangZhiTunFen;
		private double tunTuGaiLv;
		private long kuCun;
		private long tunTuLiang;
		private double kuCunShuaiJian;
		private long kuCunShuaiJianZhi;

		public Controller() {

			KeyValue kv = Server.getKeyValueForever();
			isQiangZhiTunFen = kv.getBoolean("IS_QIANG_ZHI_TUN_FEN");
			tunTuGaiLv = kv.getDouble("TUN_TU_GAI_LV");
			kuCun = kv.getLong("KU_CUN");
			tunTuLiang = kv.getLong("TUN_TU_LIANG");

			kuCunShuaiJian = kv.getDouble("KU_CUN_SHUAI_JIAN");
			setKuCunShuaiJianZhi(kv.getLong("KU_CUN_SHUAI_JIAN_ZHI"));
		}

		private void saveToDb() {
			KeyValue kv = Server.getKeyValueForever();
			kv.set("IS_QIANG_ZHI_TUN_FEN", isQiangZhiTunFen);
			kv.set("TUN_TU_GAI_LV", tunTuGaiLv);
			kv.set("KU_CUN", kuCun);
			kv.set("TUN_TU_LIANG", tunTuLiang);
			kv.set("KU_CUN_SHUAI_JIAN", kuCunShuaiJian);
			kv.set("KU_CUN_SHUAI_JIAN_ZHI", getKuCunShuaiJianZhi());
		}

		public long getKuCun() {
			return kuCun;
		}

		/**
		 * 系统强制吞吐分概率
		 * 
		 * @return
		 */
		public double getQiangZhiTunTuGaiLv() {
			return tunTuGaiLv;
		}

		public int getDangWei() {
			return 1;
		}

		/**
		 * 正常档位
		 */
		public int getNormalDangWei() {
			return (getDangWeiMin() + getDangWeiMin()) / 2;
		}

		public double getHuiBaoLv() {
			Xml xml = Server.getXml();
			Sheet sheet = xml.get("huiBaoLv");
			Row row = sheet.get(getDangWei() + "-"
					+ resultGenerator.getRandomXNumber());
			return row.getDouble("huiBaoLv");
		}

		public String getHuiBaoLvDsc() {
			try {
				Xml xml = Server.getXml();
				Sheet sheet = xml.get("huiBaoLv");
				Row row = sheet.get(getDangWei() + "-"
						+ resultGenerator.getRandomXNumber());
				return row.get("dsc");
			} catch (Exception e) {
				return "";
			}
		}

		public int getDangWeiMin() {
			return 1;
		}

		public int getDangWeiMax() {
			return 9;
		}

		/**
		 * 是否强制吞分
		 */
		public boolean isQiangZhiTunFen() {
			return isQiangZhiTunFen;
		}

		/**
		 * 强制吞分吐分概率
		 */
		public double getTunTuGaiLv() {
			return tunTuGaiLv;
		}

		/**
		 * @param qiangZhiType
		 *            系统强制干涉类型
		 * @param tunTuGaiLv
		 *            系统强制干涉概率
		 * @param tunTuLiang
		 *            吞吐量
		 * @param kuCunShuaiJian
		 *            库存衰比例
		 * @param kuCunShuaiJianZhi
		 *            库存衰减值
		 */
		public void update(boolean isQiangZhiTunFen, double tunTuGaiLv,
				long tunTuLiang, double kuCunShuaiJian, long kuCunShuaiJianZhi) {

			this.isQiangZhiTunFen = isQiangZhiTunFen;
			this.tunTuGaiLv = tunTuGaiLv;
			this.kuCunShuaiJian = kuCunShuaiJian;
			this.setKuCunShuaiJianZhi(kuCunShuaiJianZhi);
			this.setTunTuLiang(tunTuLiang);
			saveToDb();
		}

		/**
		 * 是否正在吞分
		 */
		public boolean isTunFening() {
			return isZhengZaiGanShe() && isQiangZhiTunFen();
		}

		/**
		 * 干涉程序是否正在干涉
		 */
		public boolean isZhengZaiGanShe() {
			if (!(resultGenerator instanceof ExcelGenerator)) {
				return false;
			}
			return tunTuLiang > 0 && tunTuGaiLv > 0.0001;
		}

		/**
		 * 是否在指定干涉概率的情况下, 产生了干涉
		 */
		public boolean isHappen() {
			boolean happen = Util.Random.isHappen(tunTuGaiLv);
			return happen;
		}

		/**
		 * 如果正在吐分, 生成一个吐分最多的结果 如果正在吞分, 生成一个吞分最多的结果
		 * 
		 * @param all
		 * @return
		 */
		public Row randomOne(List<Row> all) {
			if (isQiangZhiTunFen()) {
				return createTunFenRow(all);
			} else {
				return createTuFenRow(all);
			}
		}

		/**
		 * 生成一个强制吐分结果
		 */
		private Row createTuFenRow(List<Row> all) {
			TuFenComparator c = new TuFenComparator();
			List<String> types = TurntableUtil.getAllTypesWithOutAAndD();
			types = Lists.newArrayList(types);
			Util.Collection.upset(types);
			Collections.sort(types, c);

			List<String> sub = Util.Collection.sub(types, 3);
			Util.Collection.upset(sub);
			String randomOne = sub.get(0);
			return findRandomOneByType(randomOne, all);
		}

		private Row findRandomOneByType(String type, List<Row> all) {
			all = getAllTypeIs(type, all);
			Util.Collection.upset(all);
			try {
				return all.get(0);
			} catch (Exception e) {
				throw new IllegalArgumentException(type + ": " + all);
			}
		}

		private List<Row> getAllTypeIs(String type, List<Row> all) {
			ArrayList<Row> ls = Lists.newArrayList();
			for (Row row : all) {
				String t = row.get("type");
				if (type.equals(t))
					ls.add(row);
			}
			return ls;
		}

		/**
		 * 生成一个强制吞分结果
		 */
		private Row createTunFenRow(List<Row> all) {
			TunFenComparator c = new TunFenComparator();
			List<String> types = TurntableUtil.getAllTypesWithOutAAndD();
			types = Lists.newArrayList(types);

			types.remove("B");
			types.remove("C");

			Util.Collection.upset(types);
			Collections.sort(types, c);

			List<String> sub = Util.Collection.sub(types, 3);
			Util.Collection.upset(sub);
			String randomOne = sub.get(0);
			return findRandomOneByType(randomOne, all);
		}

		/**
		 * 设置系统库存
		 * 
		 * @param kuCun
		 */
		public void setKuCun(long kuCun) {
			this.kuCun = kuCun;
			saveToDb();
		}

		public long getTunTuLiang() {
			return tunTuLiang;
		}

		public void setTunTuLiang(long tunTuLiang) {
			this.tunTuLiang = tunTuLiang;
		}

		public double getKuCunShuaiJian() {
			return kuCunShuaiJian;
		}

		public void setKuCunShuaiJian(double kuCunShuaiJian) {
			this.kuCunShuaiJian = kuCunShuaiJian;
		}

		public long getKuCunShuaiJianZhi() {
			return kuCunShuaiJianZhi;
		}

		public void setKuCunShuaiJianZhi(long kuCunShuaiJianZhi) {
			this.kuCunShuaiJianZhi = kuCunShuaiJianZhi;
		}

	}

	/**
	 * 机器人下注
	 */
	public class RobotTask extends TaskSafety {

		@Override
		public void runSafty() {
			RobotManager manager = Server.getRobotManager();
			manager.randomCommit();
			long memory = Runtime.getRuntime().freeMemory();
			Log.d("robot commit", memory / (1024 * 1024) + "M");
		}

		@Override
		protected void process(Exception e) {
			Log.e(e);
			e.printStackTrace();
		}

	}

	/**
	 * 根据当前压注结果计算指定类型的灯, 会让玩家获得多少金豆
	 * 
	 * @param type
	 * @return
	 */
	private long getLose(String type) {
		long countAll = switchs.getByTypeWithOutRobot(type);
		countAll *= getX(type);
		return countAll;
	}

	public boolean isMustGenerate(int id) {
		return mustGenerateId == id;
	}

	private void submitAnyThing() {

		KeyValueSaveOnly kv = Server.getKeyValueSaveOnly();
		kv.add("SYSTEM_COIN_IN", inOut.getIn());
		kv.add("SYSTEM_COIN_OUT", inOut.getOut());

		kv.add("TURNTABLE_ROUND", 1); // 总回合数 + 1

		List<String> allTypes = TurntableUtil.getAllTypes();
		for (String type : allTypes) {
			kv.add("SUBMIT_COUNT_HISTOYR:" + type, getCountThisTime(type));
		}

		// 彩金池加入彩金
		if (!isSomeOneGetCaiJin)
			addCaiJinToCaiJinChi();

		updateKuCun();
		shaiJianKuCun();
	}

	private void shaiJianKuCun() {
		Controller c = getController();
		long kuCun = c.getKuCun();
		if (kuCun < 0)
			return;

		long reduce = (long) (kuCun * c.getKuCunShuaiJian());
		reduce += c.getKuCunShuaiJianZhi();
		c.setKuCun(kuCun - reduce);

		KeyValueSaveOnly o = Server.getKeyValueSaveOnly();

		o.add("KU_CUN_SHUAI_JIAN_LIANG", reduce);// 记录库存衰减量
	}

	public int getRoleCount() {
		if (switchs == null)
			return 0;
		int roleCount = switchs.getRoleCount()
				- Server.getRobotManager().getRobotCount();

		roleCount = Math.max(0, roleCount);

		if (roleCount != 0) {
			CacheManager.put("ROLE_COUNT", roleCount);
		} else {
			Object object = CacheManager.get("ROLE_COUNT");
			if (object == null)
				return roleCount;
			int count = (int) object;
			return count;
		}

		return roleCount;
	}

	private void initFields() {

		rankingList = new RankingList();
		result = null;
		id = Util.ID.createId();
		switchs = new SwitchAll();
		// timeStart = System.currentTimeMillis();
		isLock = false;
		inOut = new CoinInOut();
		settlements = Maps.newHashMap();
		xiaoCaiJins = Maps.newHashMap();
		xiaoCaiJinThisRound = getXiaoCaiJinThisRound();
		caiJinNotice = null;

		isSomeOneGetCaiJin = false;

		Server.getRobotManager().clearAllSwitchs();
		resultGenerator.updateRandomXNumber();
	}

	/**
	 * 此轮小彩金总量
	 */
	private long getXiaoCaiJinThisRound() {
		long xiaoCaiJinThisRound = getCaiJin();
		xiaoCaiJinThisRound = (long) (xiaoCaiJinThisRound * getXiaoCaiJinPercent());
		return xiaoCaiJinThisRound;
	}

	/**
	 * 更新库存
	 */
	private void updateKuCun() {
		ZhuangManager z = Server.getZhuangManager();
		Zhuang zh = z.getZhuang();
		if (zh == null) {
			long systemIn = inOut.getIn() - inOut.getOut()
					- inOut.getCaiJinOut();
			long systemOut = -systemIn;
			Controller c = getController();
			long now = c.getKuCun();
			long old = now;
			now += systemIn;

			if (c.isZhengZaiGanShe()) {
				if (c.isTunFening()) {
					c.setTunTuLiang(c.getTunTuLiang() - systemIn);
				} else {
					c.setTunTuLiang(c.getTunTuLiang() - systemOut);
				}
			}

			c.setKuCun(now);

			if (isChanged(now, old)) {
				Events.dispatch(new KuCunToZeroEvent(now));
			}
			Events.dispatch(new KuCunUpdateEvent(now));
		}
	}

	/**
	 * 两个数的正负性相反
	 * 
	 * @param now
	 * @param old
	 * @return
	 */
	private boolean isChanged(long now, long old) {
		if (old == 0 || now == 0)
			return true;
		old /= Math.abs(old);
		now /= Math.abs(now);
		return old * now < 0;
	}

	private void addCaiJinToCaiJinChi() {

		long kuCun = getController().getKuCun();
		if (kuCun < 0) { // 如果库存小于0, 就不出彩金
			return;
		}

		if (result == null)
			return;

		long in = inOut.getIn() - inOut.getOut() - inOut.getCaiJinOut();
		in = Math.max(in, 0);

		String base = Server.getConst().getString("CAI_JIN_SCALE");

		String[] s = base.split("\\-");
		double min = new Double(s[0]);
		double max = new Double(s[1]);

		long add = (long) (Util.Random.get(min, max) * in);
		caiJin = getCaiJin() + add;

		saveCaiJinToDb();
	}

	private static final class IProfitImplementation implements IProfit {

		private final Zhuang zh;
		private final long profit;

		public IProfitImplementation(Zhuang zh, long profit) {
			this.zh = zh;
			this.profit = profit;
		}

		@Override
		public int compareTo(IProfit o) {
			return getCompare(o) - getCompare(this);
		}

		private int getCompare(IProfit o) {
			long add = o.getAdd();
			/* long reduce = */o.getReduce();
			long caiJin = o.getCaiJin();

			long l = add + caiJin/* - reduce */;
			return (int) l;
		}

		@Override
		public String toString() {
			return getNick() + " add:" + getAdd();
		}

		@Override
		public String getRoleId() {
			return zh.getRoleId();
		}

		@Override
		public long getReduce() {
			return 0;
		}

		@Override
		public String getNick() {
			return zh.getNick();
		}

		@Override
		public long getCaiJin() {
			return 0;
		}

		@Override
		public long getAdd() {
			return profit;
		}
	}

	public class RoundEndTask extends TaskSafety {

		@Override
		protected void process(Exception e) {
			e.printStackTrace();
			Log.e(e);
		}

		@Override
		public void runSafty() {
			boolean jinShaYuThisTime = isJinShaYuThisTime();

			submitAnyThing();
			initFields();

			mustGenerateId = -1;

			Log.d("round end, restart turn table");
			if (jinShaYuThisTime) {
				int pauseSec = Server.getConst().getInt("JIN_SHA_YU_PAUSE_SEC");
				timer.pause(pauseSec * 1000L);
			}
		}

	}

	/**
	 * 这次是不是出了金鲨鱼
	 * 
	 * @return
	 */
	private boolean isJinShaYuThisTime() {
		if (result == null)
			return false;
		List<Row> rs = result.getResult();
		Row row = rs.get(0);
		String type = row.get("type");
		return type.equals("C") || type.equals("B");
	}

	public class GenerateResultTask extends TaskSafety {

		@Override
		protected void process(Exception e) {
			Log.e(e);
			e.printStackTrace();
		}

		@Override
		public void runSafty() {
			result = resultGenerator.generateReward(switchs);

			settlementForAllRoles();
			settlementForZhuang();

			robotsRequestResult(); // 让机器人请求结果

			Server.getKeyValueSaveOnly().add("TURNTABLE_GENERATE_TIMES", 1);
			Log.d("generate reward", result);
			saveToHistory();
		}

		private void settlementForZhuang() { // 结算庄家盈亏
			ZhuangManager z = Server.getZhuangManager();
			Zhuang zh = z.getZhuang();
			if (zh != null) {

				long profit = inOut.getIn() - inOut.getOut();
				if (rankingList != null) {
					IProfit buildProfit = buildProfit(zh, profit);
					rankingList.setZhuang(buildProfit);
				}
				Role role = zh.role();
				if (profit < 0) {
					long win = -profit;
					win = Math.min(role.getCoin(), win);
					role.reduceCoin(win);
				} else {
					role.addCoin(profit);
				}
				Log.d(role.getId(), role.getNick(), buildResult(), profit);
			}
		}

		private IProfit buildProfit(Zhuang zh, long profit) {
			return new IProfitImplementation(zh, profit);
		}

		private void settlementForAllRoles() {

			generateXiaoCaiJins();// 计算所有用户可以获得多少小彩金

			Set<String> keySet = switchs.keySet();
			List<Row> r = Turntable.this.result.getResult();
			for (String roleId : keySet) {
				IRole role = Server.getRole(roleId);
				ISwitchs sw = switchs.get(roleId);

				SettlementResult result = settlement(r, role, sw);
				rankingList.add(result);

				RobotManager rm = Server.getRobotManager();
				boolean isRobot = rm.isRobot(roleId);
				boolean isTestRole = role instanceof TestRole;

				long xcjadd = result.getXiaoCaiJinAdd();
				long cj = result.getCaiJin();
				long rdc = result.getReduce();
				long add = result.getAdd();

				if (!isTestRole) {
					Log.d("bet", roleId, isRobot ? "robot" : "player",
							role.getNick(), sw, buildResult(), "-" + rdc, "+"
									+ add, cj, xcjadd, role.getCoin());
				}

				if (!isTestRole && !isRobot) {

					inOut.addIn(rdc);
					inOut.addOut(add);
					inOut.addCaiJinOut(cj + xcjadd);
				}
				settlements.put(roleId, result);
			}

			initCaiJin();
		}

		private String buildResult() {
			List<Row> r = Turntable.this.result.getResult();
			StringBuilder sb = new StringBuilder();
			for (Row row : r) {
				sb.append(row.get("type"));
			}
			return sb.toString();
		}

		private void initCaiJin() {
			caiJin = Math.max(getMinCaiJin(), caiJin);
			saveCaiJinToDb();
		}

		private void generateXiaoCaiJins() {

			double p = isXiaoCaiJinHappen();

			if (!Util.Random.isHappen(p)) {
				return;
			}

			Set<String> keySet = switchs.keySet();

			long condition = Server.getConst().getLong(
					"CAI_JIN_CONDITION_SMALL");

			String type = getRewardTypeThisTime();
			for (String roleId : keySet) {
				ISwitchs sw = switchs.get(roleId);
				int count = TurntableUtil.getByType(sw, type);
				if (count >= condition) {
					xiaoCaiJins.put(roleId, buildCommitThisTime(roleId, count));
				}
			}

			long countAll = getCommitAll();

			for (RoleCommitThisTime r : xiaoCaiJins.values()) {
				double c = r.getCountThisTime();
				double percent = c / countAll;
				r.setPercent(percent); // 它可以获得的比例
			}
		}

		private long getCommitAll() {
			long sum = 0;
			for (RoleCommitThisTime r : xiaoCaiJins.values()) {
				sum += r.getCountThisTime();
			}
			return sum;
		}

		private RoleCommitThisTime buildCommitThisTime(String roleId, int count) {
			RoleCommitThisTime r = new RoleCommitThisTime();
			r.setRoleId(roleId);
			r.setCountThisTime(count);
			return r;
		}

		private double isXiaoCaiJinHappen() {

			long kuCun = getController().getKuCun();

			if (kuCun < 0) // 库存为负数, 不发生小彩金彩金
				return 0;

			double p = Server.getConst().getDouble("CAI_JIN_PROBABILITY_SMALL");

			if (!Util.Random.isHappen(p)) {
				return 0;
			}

			List<Row> rr = Turntable.this.result.getResult();
			Row first = rr.get(0);
			String type = first.get("type");

			int xxx = getX(type);

			int dxxd = Server.getConst().getInt("CAI_JIN_X_SMALL");

			if (xxx < dxxd)
				return 0;

			long caiJin2 = getCaiJin();
			long dd = Server.getConst().getLong("CAI_JIN_L");

			if (caiJin2 > dd) {
				return 0;
			}
			return Server.getConst().getDouble("CAI_JIN_CONDITION_SMALL");
		}

		private void robotsRequestResult() {
			RobotManager m = Server.getRobotManager();
			Collection<Robot> robots = m.getRobots();
			for (Robot robot : robots) {
				robot.getResultFromTurnTable();
			}
		}

		private void saveToHistory() {
			try {
				GetHistoryResult history = getHistory();
				History his = new History();
				Row first = result.getResult().get(0);
				int id = first.getInt("id");
				his.setId(id);
				List<History> hhh = history.getHistory();
				hhh.add(his);
				if (hhh.size() > 20) {
					hhh.remove(0);
				}
				saveToDb(history);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	void saveToDb(GetHistoryResult history) {
		String dbJson = JSON.toJSONString(history);
		KeyValue kv = Server.getKeyValueForever();
		kv.set("HISTORY", dbJson);
	}

	public String getRewardTypeThisTime() {
		List<Row> rs = Turntable.this.result.getResult();
		return rs.get(0).get("type");
	}

	public static Turntable instance;

	// private long timeStart;
	private int mustGenerateId = -1; // 本轮必出的东西

	private SwitchAll switchs;
	private String id;
	private Result result;
	private MyTimer timer;

	private boolean isLock;

	private CoinInOut inOut = new CoinInOut();

	// 管理员配置的权重
	private Map<Integer, Integer> weightByGm = loadFromDB();

	// 结算结果
	private Map<String, SettlementResult> settlements = Maps.newHashMap();

	private Long caiJin;

	/**
	 * 是否有某个人 中了彩金
	 */
	private boolean isSomeOneGetCaiJin;

	private GetHistoryResult history;

	private Controller controller;

	private RankingList rankingList;

	private String caiJinNotice;

	private Map<String, RoleCommitThisTime> xiaoCaiJins;

	long xiaoCaiJinThisRound;

	ResultGenerator resultGenerator = new PZResultGenerator();

	public static Turntable getInstance() {
		if (instance == null) {
			instance = new Turntable();
		}
		return instance;
	}

	private void saveCaiJinToDb() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = dao.get("CAI_JIN");
		if (dto == null) {
			dto = new SystemKeyValueDto();
			dto.setKey("CAI_JIN");
			dto.setValue(caiJin + "");
		}
		dao.save(dto);
	}

	public Controller getController() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	public void init() {
		if (timer != null) {
			timer.stop();
		}
		timer = new MyTimer();

		initFields();

		long cd = Server.getConst().getLong("CD");
		long jcd = Server.getConst().getLong("JIE_SUAN_CD");

		isLock = true; // 锁定所有用户不可再下注
		long sum = cd + jcd;
		timer.setBaseTask(new GenerateResultTask(), cd, sum);

		timer.appendTask(new RoundEndTask(), jcd);

		String string = Server.getConst().getString("ROBOT_COMMIT_SEC");
		List<Integer> is = Util.Collection.getIntegers(string);
		for (int sec : is) {

			timer.appendTask(new RobotTask(), sec * 1000 - cd);
			Log.d("add robot task", sec);
		}

		timer.start();

	}

	public int getWeight(Row row) {
		String c = "weight-" + getController().getDangWei() + "-"
				+ resultGenerator.getRandomXNumber();
		return row.getInt(c);
	}

	public long getCd() {
		return timer.getBaseTaskCd() + 1900;
	}

	public void commitUserSwitchs(String id, ISwitchs s) {
		checkZhuang(id, s);
		if (!isLock) {
			switchs.save(id, s);
		}
	}

	private void checkZhuang(String id, ISwitchs s) {
		if (TurntableUtil.getCountAll(s) == 0) {
			return;
		}
		if (Server.getZhuangManager().isZhuang(id)) {
			throw new ErrorResult(10029).toException();
		}
	}

	public SwitchAll getSwitchs() {
		return switchs;
	}

	public String getId() {
		return id;
	}

	/**
	 * 计时投注
	 * 
	 * @param role
	 * @param s
	 * @return
	 */
	public Object playOnce(IRole role, ISwitchs s) {
		checkZhuang(role.getId(), s);
		if (result == null)
			return new PlayOnceError(this, "result not found");
		if (settlements.containsKey(role.getId())) {
			SettlementResult result = settlement(this.result.getResult(), role,
					s);// 结算给当前用户
			settlements.put(role.getId(), result);

			inOut.addIn(result.getReduce());
			inOut.addOut(result.getAdd());

			return new PlayResult(this.result.getResult(), role.getCoin(),
					null, 0);
		} else {
			throw new ErrorResult(10011).toException();
		}
	}

	// /**
	// * 不计时投注
	// */
	// public PlayResult playOnceWithOutTime(IRole role, ISwitchs s) {
	// checkZhuang(role.getId(), s);
	// Result result = resultGenerator.generateReward(s);
	//
	// List<Row> rs = result.getResult();
	// settlement(rs, role, s);// 结算给当前用户
	// return new PlayResult(rs, role.getCoin(), null, 0);
	// }

	/**
	 * 结算金币
	 * 
	 * @param randoms
	 *            weights 表里面的数据
	 * @param role
	 * @param s
	 * @return 盈利结果: 本次消耗金币和获得金币
	 */
	private SettlementResult settlement(List<Row> randoms, IRole role,
			ISwitchs s) {

		SettlementResult r = new SettlementResult();
		long reduce = TurntableUtil.getCountAll(s);
		r.setReduce(reduce);
		r.setRoleId(role.getId());
		r.setNick(role.getNick());

		role.reduceCoin(reduce);// 把所有压了的金币扣了

		long add = 0;
		for (Row row : randoms) {
			add += addCoin(row, role, s);
		}

		long settlementCaiJin = 0;

		Row first = randoms.get(0);
		try {
			settlementCaiJin = settlementCaiJin(role, s, first);
			addJiangQuan(settlementCaiJin, role);
		} catch (Exception e) {
			e.printStackTrace();
		}

		add += settlementCaiJin;

		r.setSettlementCaiJin(settlementCaiJin);

		if (settlementCaiJin != 0) {
			r.setCaiJinRoleId(role.getId());
		}

		if (r.isSettlementCaiJin()) {
			isSomeOneGetCaiJin = true;
			saveCaiJinLog(role, settlementCaiJin, false);
		}

		long xiaoCaiJinAdd = settlementXiaoCaiJin(role, first);

		addJiangQuan(xiaoCaiJinAdd, role);

		r.setAdd(add);
		r.setXiaoCaiJinAdd(xiaoCaiJinAdd);

		return r;
	}

	/**
	 * 根据彩金增加奖券
	 * 
	 * @param caiJin
	 * @param role
	 */
	private void addJiangQuan(long caiJin, IRole role) {
		Const cc = Server.getConst();
		int min = cc.getInt("JIANG_QUAN_CAI_JIN_MIN");
		if (caiJin < min)
			return;
		double scale = cc.getDouble("JIANG_QUAN_SCALE");
		long add = (long) (scale * caiJin);

		role.addJiangQuan(add);
		if (add != 0)
			Log.d("add jiang quan settlement", role.getId(), role.getNick(),
					add);
	}

	private long settlementXiaoCaiJin(IRole role, Row first) {
		RoleCommitThisTime rwd = xiaoCaiJins.get(role.getId());
		if (rwd == null)
			return 0;

		long receive = (long) (rwd.getPercent() * xiaoCaiJinThisRound);

		rwd.setReceive(receive);

		role.addCoin(receive);

		saveCaiJinLog(role, receive, true);

		reduceCaiJin(receive);

		return receive;
	}

	/**
	 * 从彩金池扣彩金
	 */
	private void reduceCaiJin(long r) {
		caiJin -= r;
		saveCaiJinToDb();
	}

	private double getXiaoCaiJinPercent() {
		String base = Server.getConst().getString("CAI_JIN_SCALE_SMALL");

		String[] ss = base.split("\\-");
		double min = new Double(ss[0]);
		double max = new Double(ss[1]);

		double caiJinPercentAll = Util.Random.get(min, max);
		return caiJinPercentAll;
	}

	private void saveCaiJinLog(IRole role, long settlementCaiJin,
			boolean isSmall) {
		CaiJinLogDao dao = Daos.getCaiJinLogDao();
		CaiJinLogDto dto = dao.createDTO();
		dto.setNick(role.getNick());
		dto.setId(Util.ID.createId());
		dto.setCaiJin(settlementCaiJin);
		dto.setRoleId(role.getId());
		dto.setIsSmall(isSmall);
		dao.save(dto);
	}

	/**
	 * 如果彩金池里面还有钱, 而且本轮出了金鲨鱼, 而且玩家压注了金鲨鱼, 那么就给该玩家结算彩金
	 * 
	 * @param role
	 * @param s
	 * @param row
	 * @return
	 */
	private long settlementCaiJin(IRole role, ISwitchs s, Row row) {

		if (role instanceof TestRole) {
			return 0;
		}

		RobotManager manager = Server.getRobotManager();

		if (manager.isRobot(role.getId())) { // 2015年6月11日 14:59:20 林岑加 如果是机器人,
												// 就不要获得彩金
			return 0;
		}

		return settlementCj(role, s, row);

	}

	/**
	 * 结算大彩金
	 * 
	 * @param role
	 * @param s
	 * @param row
	 * @return
	 */
	private long settlementCj(IRole role, ISwitchs s, Row row) {

		if (role instanceof TestRole) {
			return 0;
		}

		RobotManager manager = Server.getRobotManager();

		if (manager.isRobot(role.getId())) { // 2015年6月11日 14:59:20 林岑加 如果是机器人,
												// 就不要获得彩金
			return 0;
		}

		// boolean hasMustTo = manager.hasMustTo();

		int c = s.getC();
		if (c <= 0) { // 没有压金鲨
			return 0;
		}

		boolean isJinSha = row.get("type").equals("C");
		if (!isJinSha) {
			return 0;
		}
		long caij = getCaiJin();

		Const cst = Server.getConst();

		long ccc = cst.getLong("CAI_JIN_L"); // 机器人下注时 每项下注金额(范围) 格式: 最小值-最大值

		if (caij <= ccc) {
			return 0;
		}

		long condition = cst.getLong("CAI_JIN_CONDITION");

		if (/* !hasMustTo && */c < condition) {
			return 0;
		}

		long minCaiJin = getMinCaiJin();

		caij -= minCaiJin; // 可分配彩金

		if (caij <= 0) {
			return 0;
		}

		String base = Server.getConst().getString("CAI_JIN_SCALE");

		String[] ss = base.split("\\-");
		double min = new Double(ss[0]);
		double max = new Double(ss[1]);

		double percent = Util.Random.get(min, max);

		long add = (long) (caij * percent);
		role.addCoin(add);

		boolean robot = Server.getRobotManager().isRobot(role.getId());

		Log.d("add cai jin", role.getId(), role.getNick(), add, robot ? "robot"
				: "player");
		if (add > 0) {
			MessageManager msm = Server.getMessageManager();
			// msm.sendNotice(10017, role.getNick(), add);
			caiJinNotice = msm.buildMessage(10017, role.getNick(), add);
		}
		return add;
	}

	private long addCoin(Row row, IRole role, ISwitchs s) {
		String categray = row.get("categray");// 大类
		String type = row.get("type");// 小类

		long add = 0;
		add += add(role, categray, s);
		add += add(role, type, s);
		return add;
	}

	private long add(IRole role, String type, ISwitchs s) {
		int byType = TurntableUtil.getByType(s, type);
		if (byType == 0)
			return 0;
		long add = byType * getX(type);
		role.addCoin(add);
		return add;
	}

	public int getX(String type) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		Row row = sheet.get(type);
		if (row == null) {
			throw new NullPointerException(type);
		}
		Controller c = getController();
		return row.getInt("x-" + c.getDangWei() + "-"
				+ resultGenerator.getRandomXNumber());
	}

	/**
	 * 历史金币输入
	 */
	public long getCoinInHistory() {
		return Server.getKeyValueForever().getLong("SYSTEM_COIN_IN");
	}

	/**
	 * 历史金币输出
	 */
	public long getCoinOutHistory() {
		return Server.getKeyValueForever().getLong("SYSTEM_COIN_OUT");
	}

	/**
	 * 今日金币输入
	 */
	public long getCoinInToday() {
		return Server.getKeyValueDaily().getLong("SYSTEM_COIN_IN");
	}

	/**
	 * 今日金币输出
	 */
	public long getCoinOutToday() {
		return Server.getKeyValueDaily().getLong("SYSTEM_COIN_OUT");
	}

	/**
	 * 今日开奖次数
	 */
	public int getGenerateTimesToday() {
		KeyValue kv = Server.getKeyValueDaily();
		return kv.getInt("TURNTABLE_GENERATE_TIMES");
	}

	/**
	 * 历史开奖次数
	 */
	public int getGenerateTimesHistory() {
		KeyValue kv = Server.getKeyValueForever();
		return kv.getInt("TURNTABLE_GENERATE_TIMES");
	}

	/**
	 * 本轮下注量
	 * 
	 * @param type
	 *            A - L
	 * 
	 *            A 2 0-0 飞禽 B 24 1-1 银鲨鱼 C 48 1-3 金鲨鱼 D 2 0-0 走兽 E 6 0-0 燕子 F 8
	 *            0-0 鸽子 G 8 0-0 孔雀 H 12 0-0 老鹰 I 12 0-0 狮子 J 8 0-0 熊猫 K 8 0-0
	 *            猴子 L 6 0-0 兔子
	 * @return
	 */
	public long getCountThisTime(String type) {
		if (switchs == null)
			return 0;
		return TurntableUtil.getByType(switchs, type);
	}

	/**
	 * 历史下注量
	 * 
	 * @param type
	 *            A - L
	 * 
	 *            A 2 0-0 飞禽 B 24 1-1 银鲨鱼 C 48 1-3 金鲨鱼 D 2 0-0 走兽 E 6 0-0 燕子 F 8
	 *            0-0 鸽子 G 8 0-0 孔雀 H 12 0-0 老鹰 I 12 0-0 狮子 J 8 0-0 熊猫 K 8 0-0
	 *            猴子 L 6 0-0 兔子
	 * @return
	 */
	public long getCountHistory(String type) {
		return Server.getKeyValueForever().getLong(
				"SUBMIT_COUNT_HISTOYR:" + type);
	}

	/**
	 * 更新管理员自定义权重
	 * 
	 * @param weightsAdd
	 */
	public void updateWeightsAdd(Map<Integer, Integer> weightsAdd) {
		this.setWeightByGm(weightsAdd);
		saveWeightsToDb();
	}

	private void saveWeightsToDb() {
		KeyValue kv = Server.getKeyValueForever();
		kv.set("WEIGHTS_BY_GM", weightsToJsonString());
	}

	private String weightsToJsonString() {
		JSONObject o = new JSONObject();
		for (Integer k : getWeightByGm().keySet()) {
			Integer v = getWeightByGm().get(k);
			o.put(k.toString(), v);
		}
		return o.toJSONString();
	}

	private Map<Integer, Integer> loadFromDB() {
		KeyValue kv = Server.getKeyValueForever();
		String string = kv.getString("WEIGHTS_BY_GM");
		HashMap<Integer, Integer> map = Maps.newHashMap();
		if (string == null) {
			return map;
		}
		JSONObject obj = JSON.parseObject(string);

		for (String k : obj.keySet()) {
			Object v = obj.get(k);
			map.put(new Integer(k), new Integer(v.toString()));
		}
		return map;
	}

	public void setMustGenerateId(int id) {
		this.mustGenerateId = id;
	}

	public int getMustGenerateId() {
		return mustGenerateId;
	}

	public Object getPlayResult(IRole role, ISwitchs sw) {

		if (result == null)
			return new PlayOnceError(this, "result not found");

		SettlementResult result = getResult(role);
		List<Row> rst = this.result.getResult();

		long coin = role.getCoin();

		if (result == null)
			return new PlayResult(rst, 0, caiJinNotice, 0);

		long caiJin = result.getCaiJin() + result.getXiaoCaiJinAdd();

		return new PlayResult(rst, coin, caiJinNotice, caiJin);
	}

	private SettlementResult getResult(IRole role) {
		return settlements.get(role.getId());
	}

	/**
	 * 获得结算结果
	 */
	public IProfit getSettlementResult(String id) {
		return settlements.get(id);
	}

	/**
	 * 彩金
	 */
	public long getCaiJin() {
		if (caiJin == null)
			caiJin = loadFromDb();
		return caiJin;
	}

	private Long loadFromDb() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = dao.get("CAI_JIN");
		if (dto == null) {
			dto = new SystemKeyValueDto();
			dto.setKey("CAI_JIN");
			dto.setValue(getMinCaiJin() + "");
			dao.save(dto);
		}

		return new Long(dto.getValue());
	}

	private long getMinCaiJin() {
		long long1 = Server.getConst().getLong("CAI_JIN_LOWEST");
		return long1;
	}

	/**
	 * 倍率
	 */
	public Xs getXs() {
		return new XsImpl(this);
	}

	public GetHistoryResult getHistory() {
		if (history == null)
			history = loadHistoryFromDb();
		return history;
	}

	private GetHistoryResult loadHistoryFromDb() {

		KeyValue kv = Server.getKeyValueForever();
		String dbJson = kv.getString("HISTORY");

		GetHistoryResult history;

		if (dbJson == null) {
			history = new GetHistoryResult();
			saveToDb(history);
		} else {
			history = JSON.parseObject(dbJson, GetHistoryResult.class);
		}
		return history;
	}

	/**
	 * 总回合数
	 */
	public int getRound() {
		return Server.getKeyValueForever().getInt("TURNTABLE_ROUND");
	}

	public RankingList getRankingList() {
		return rankingList;
	}

	public Map<Integer, Integer> getWeightByGm() {
		return weightByGm;
	}

	public void setWeightByGm(Map<Integer, Integer> weightByGm) {
		this.weightByGm = weightByGm;
	}

}
