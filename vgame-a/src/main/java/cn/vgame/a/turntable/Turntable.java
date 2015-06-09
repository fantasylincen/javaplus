package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.time.Time;
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
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.a.zhuang.Zhuang;
import cn.vgame.a.zhuang.ZhuangManager;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;
import cn.vgame.share.Xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Turntable {

	/**
	 * 小彩金奖励
	 * 
	 * @author Administrator
	 * 
	 */
	public class XiaoCaiJinReward {
		private String roleId;
		private long countThisTime;
		private double percent;
		private long receive;

		/**
		 * 这次压了多少注
		 * @return
		 */
		public long getCountThisTime() {
			return countThisTime;
		}

		public void setCountThisTime(long countThisTime) {
			this.countThisTime = countThisTime;
		}

		/**
		 * 角色ID
		 * @return
		 */
		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		public void setPercent(double percent) {
			this.percent = percent;
		}

		/**
		 * 获得彩金比例
		 * @return
		 */
		public double getPercent() {
			return percent;
		}

		public void setReceive(long receive) {
			this.receive = receive;
			
		}
		
		/**
		 * 最终获得了好多彩金
		 * @return
		 */
		public long getReceive() {
			return receive;
		}

	}

	public class Controller {

		private boolean isQiangZhiTunFen;
		private long maxKuCun;
		private boolean isToNormal;
		private int dangWei;
		private double tunTuGaiLv;
		private double chuFaTunFenGaiLv;
		private int chuFaTunFenShiChang;
		private long kuCun;
		private String ganSheSec;

		public Controller() {

			KeyValue kv = Server.getKeyValueForever();
			maxKuCun = kv.getLong("MAX_KU_CUN");
			isToNormal = kv.getBoolean("IS_TO_NORMAL");
			dangWei = kv.getInt("DANG_WEI");
			isQiangZhiTunFen = kv.getBoolean("IS_QIANG_ZHI_TUN_FEN");
			tunTuGaiLv = kv.getDouble("TUN_TU_GAI_LV");
			chuFaTunFenGaiLv = kv.getDouble("CHU_FA_TUN_FEN_GAI_LV");
			chuFaTunFenShiChang = kv.getInt("CHU_FA_TUN_FEN_SHI_CHANG");
			kuCun = kv.getLong("KU_CUN");
			ganSheSec = kv.getString("GAN_SHE_SEC");
			if(ganSheSec == null || ganSheSec.equals("0")) {
				ganSheSec= "0:" + System.currentTimeMillis();
			}
		}

		private void saveToDb() {
			KeyValue kv = Server.getKeyValueForever();
			kv.set("MAX_KU_CUN", maxKuCun);
			kv.set("IS_TO_NORMAL", isToNormal);
			kv.set("DANG_WEI", dangWei);
			kv.set("IS_QIANG_ZHI_TUN_FEN", isQiangZhiTunFen);
			kv.set("TUN_TU_GAI_LV", tunTuGaiLv);
			kv.set("CHU_FA_TUN_FEN_GAI_LV", chuFaTunFenGaiLv);
			kv.set("CHU_FA_TUN_FEN_SHI_CHANG", chuFaTunFenShiChang);
			kv.set("KU_CUN", kuCun);
			kv.set("GAN_SHE_SEC", ganSheSec);
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

		/**
		 * 强制干涉剩余时间 返回格式: HH:mm
		 */
		public String getGanSheShengYuShiJian() {
			long min = getRemainGanSheMin();
			long hour = getRemainGanSheHour();
			return hour + ":" + min;
		}

		/**
		 * 剩余干涉分钟
		 */
		public long getRemainGanSheMin() {
			long remainMillis = getGetGanSheRemainSec() * 1000L;
			long m = remainMillis % Time.MILES_ONE_HOUR;
			long min = m / Time.MILES_ONE_MIN;
			return min;
		}

		/**
		 * 剩余干涉小时
		 */
		public long getRemainGanSheHour() {
			long remainMillis = getGetGanSheRemainSec() * 1000L;
			return remainMillis / Time.MILES_ONE_HOUR;
		}

		/**
		 * 系统强制干涉剩余秒
		 * 
		 * @return
		 */
		public int getGetGanSheRemainSec() {
			if (ganSheSec == null || ganSheSec.isEmpty()) {
				return 0;
			}

			String[] sp = ganSheSec.split(":");
			int secAll = new Integer(sp[0]);
			long updateTime = new Long(sp[1]);

			long now = System.currentTimeMillis();
			int timeSpendSec = (int) ((now - updateTime) / 1000);

			return Math.max(0, secAll - timeSpendSec);
		}

		public void setGanSheTime(int hour, int min) {
			int sec = 0;
			sec += hour * 3600;
			sec += min * 60;

			ganSheSec = sec + ":" + System.currentTimeMillis();
			saveToDb();
		}

		/**
		 * 当系统库存正负性发生变化时, 是否将档位跳到正常状态
		 */
		public boolean isToNormal() {
			return isToNormal;
		}

		/**
		 * 系统库存大于了预设警戒值的时候, 强制触发收分程序 N秒 默认1800秒
		 */
		public int getNormalShouFenSec() {
			int shichang = getChuFaTunFenShiChang();
			return shichang * 60;
		}

		public int getDangWei() {
			if (dangWei == 0) {
				dangWei = getNormalDangWei();
				saveToDb();
			}
			return dangWei;
		}

		/**
		 * 正常档位
		 */
		public int getNormalDangWei() {
			return (getDangWeiMin() + getDangWeiMin()) / 2;
		}

		/**
		 * 当库存小于这个值的时候, 强制触发收分程序
		 */
		public long getMaxKuCun() {
			if (maxKuCun == 0) {
				maxKuCun = -10000000;
				saveToDb();
			}
			return maxKuCun;
		}

		public double getHuiBaoLv() {
			Xml xml = Server.getXml();
			Sheet sheet = xml.get("huiBaoLv");
			Row row = sheet.get(getDangWei() + "-" + randomXNumber);
			return row.getDouble("huiBaoLv");
		}

		public String getHuiBaoLvDsc() {
			try {
				Xml xml = Server.getXml();
				Sheet sheet = xml.get("huiBaoLv");
				Row row = sheet.get(getDangWei() + "-" + randomXNumber);
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
		 * @param maxKuCun
		 *            当系统库存大于某值时, 触发强制收分程序
		 * @param isToNormal
		 *            当库存变为0时, 是否把回报率调节到正常值
		 * @param dangWei
		 *            回报率档位(吐分速率)
		 * @param qiangZhiType
		 *            系统强制干涉类型
		 * @param tunTuGaiLv
		 *            系统强制干涉概率
		 * @param chuFaTunFenShiChang
		 *            触发收分程序时, 强制干涉概率
		 * @param chuFaTunFenGaiLv
		 *            触发收分程序时, 强制干涉时长(分钟)
		 */
		public void update(long maxKuCun, boolean isToNormal, int dangWei,
				boolean isQiangZhiTunFen, double tunTuGaiLv,
				double chuFaTunFenGaiLv, int chuFaTunFenShiChang) {

			this.maxKuCun = maxKuCun;
			this.isToNormal = isToNormal;
			this.dangWei = dangWei;
			this.isQiangZhiTunFen = isQiangZhiTunFen;
			this.tunTuGaiLv = tunTuGaiLv;
			this.chuFaTunFenGaiLv = chuFaTunFenGaiLv;
			this.chuFaTunFenShiChang = chuFaTunFenShiChang;
			saveToDb();
		}

		/**
		 * 充值档位到正常值
		 */
		public void setDangWeiToNormal() {
			if (isToNormal()) {
				dangWei = getNormalDangWei();
				saveToDb();
			}
		}

		/**
		 * 是否正在吞分
		 */
		public boolean isTunFening() {
			return isZhengZaiGanShe() && isQiangZhiTunFen();
		}

		/**
		 * 开始吞分
		 */
		public void startTunFen() {

			isQiangZhiTunFen = true;
			tunTuGaiLv = getChuFaTunFenGaiLv();

			saveToDb();

			int chuFaTunFenShiChang = getChuFaTunFenShiChang();

			int min = chuFaTunFenShiChang % 60;
			int hour = chuFaTunFenShiChang / 60;
			setGanSheTime(hour, min);
		}

		/**
		 * 触发收分程序时, 强制干涉概率
		 */
		public double getChuFaTunFenGaiLv() {
			return chuFaTunFenGaiLv;
		}

		/**
		 * 触发收分程序时, 强制干涉时长(分钟)
		 */
		public int getChuFaTunFenShiChang() {
			if (chuFaTunFenShiChang == 0) {
				chuFaTunFenShiChang = 30;
				saveToDb();
			}
			return chuFaTunFenShiChang;
		}

		/**
		 * 干涉程序是否正在干涉
		 */
		public boolean isZhengZaiGanShe() {
			int sec = getGetGanSheRemainSec();
			return sec > 0 && tunTuGaiLv > 0.0001;
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
			long maxLose = Long.MIN_VALUE;// 最大吐分值
			String maxLoseType = null;
			List<String> types = TurntableUtil.getAllTypesWithOutAAndD();
			types = Lists.newArrayList(types);
			Util.Collection.upset(types);
			for (String type : types) {
				long lose = getLose(type);
				if (lose > maxLose) {
					maxLose = lose;
					maxLoseType = type;
				}
			}
			return findRandomOneByType(maxLoseType, all);
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
			long minLose = Long.MAX_VALUE;// 最小吐分值
			String minLoseType = null;
			List<String> types = TurntableUtil.getAllTypesWithOutAAndD();
			types = Lists.newArrayList(types);
			Util.Collection.upset(types);
			for (String type : types) {
				long lose = getLose(type);
				if (lose < minLose) {
					minLose = lose;
					minLoseType = type;
				}
			}
			return findRandomOneByType(minLoseType, all);
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

	}

	/**
	 * 机器人下注
	 */
	public class RobotTask extends TaskSafety {

		@Override
		public void runSafty() {
			RobotManager manager = Server.getRobotManager();
			manager.randomCommit();
		}

	}

	/**
	 * 根据当前压注结果计算指定类型的灯, 会让玩家获得多少金豆
	 * 
	 * @param type
	 * @return
	 */
	private long getLose(String type) {
		long countAll = getCountAllByType(type);
		countAll *= getX(type);
		return countAll;
	}

	/**
	 * 当前指定类型的灯, 总共被下注多少个
	 * 
	 * @param type
	 * @return
	 */
	private long getCountAllByType(String type) {
		return TurntableUtil.getByType(switchs, type);
	}

	public boolean isMustGenerate(int id) {
		return mustGenerateId == id;
	}

	private final class WeightFetcherImplementation implements
			WeightFetcher<Row> {
		@Override
		public Integer get(Row t) {
			int id = t.getInt("id");

			int weightAdd = getWeightAdd(id); // 管理员配置的权重
			return t.getInt("weight-" + getController().getDangWei() + "-"
					+ randomXNumber)
					+ weightAdd;
		}
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
			long reduce = o.getReduce();
			long caiJin = o.getCaiJin();

			long l = add + caiJin/* - reduce*/;
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

	public class RestartTask extends TaskSafety {

		@Override
		protected void process(Exception e) {
			e.printStackTrace();
			Log.e(e);
		}

		@Override
		public void runSafty() {
			submitAnyThing();
			initFields();
			Log.d("restart turn table");
		}

		private void submitAnyThing() {

			KeyValueSaveOnly kv = Server.getKeyValueSaveOnly();
			kv.add("SYSTEM_COIN_IN", coinIn);
			kv.add("SYSTEM_COIN_OUT", coinOut);

			kv.add("TURNTABLE_ROUND", 1); // 总回合数 + 1

			List<String> allTypes = TurntableUtil.getAllTypes();
			for (String type : allTypes) {
				kv.add("SUBMIT_COUNT_HISTOYR:" + type, getCountThisTime(type));
			}

			// 彩金池加入彩金
			if (!isSomeOneGetCaiJin)
				addCaiJinToCaiJinChi();

			updateKuCun();
		}

		/**
		 * 更新库存
		 */
		private void updateKuCun() {
			ZhuangManager z = Server.getZhuangManager();
			Zhuang zh = z.getZhuang();
			if (zh == null) {
				long profit = coinIn - coinOut;
				Controller c = getController();
				long now = c.getKuCun();
				long old = now;
				now += profit;
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
			// if (result == null)
			// return;
			// List<Row> rs = result.getResult();
			// Row first = rs.get(0);
			// String s = first.get("type");
			//
			// StaticDataAdaptor xml = Server.getXml();
			// Sheet sheet = xml.get("x");
			// Row row = sheet.get(s);
			//
			// int x = getX(row.get("type"));
			//
			// int base = Server.getConst().getInt("CAI_JIN_BASE");
			//
			// long add = x * base;
			//
			// caiJin = getCaiJin() + add;
			//
			// saveCaiJinToDb();

			if (result == null)
				return;

			long in = coinIn - coinOut;
			in = Math.max(in, 0);

			String base = Server.getConst().getString("CAI_JIN_SCALE");

			String[] s = base.split("\\-");
			double min = new Double(s[0]);
			double max = new Double(s[1]);

			long add = (long) (Util.Random.get(min, max) * in);
			caiJin = getCaiJin() + add;

			saveCaiJinToDb();

		}


		private void initFields() {

			rankingList = new RankingList();
			result = null;
			id = Util.ID.createId();
			switchs = new SwitchAll();
			timeStart = System.currentTimeMillis();
			isLock = false;
			coinIn = 0;
			coinOut = 0;
			settlements = Maps.newHashMap();
			xiaoCaiJins = Maps.newHashMap();
			caiJinNotice = null;

			isSomeOneGetCaiJin = false;

			randomX();

			Server.getRobotManager().clearAllSwitchs();
		}

		/**
		 * 随机倍率, 在x表中随机
		 */
		private void randomX() {

			randomXNumber = Util.Random.get(1, 5);
		}

	}

	public class GenerateResultTask extends TaskSafety {

		@Override
		protected void process(Exception e) {
			Log.e(e);
			e.printStackTrace();
		}

		@Override
		public void runSafty() {
			result = generateReward();

			settlementForAllRoles();
			settlementForZhuang();

			robotsRequestResult(); // 让机器人请求结果

			Server.getKeyValueSaveOnly().add("TURNTABLE_GENERATE_TIMES", 1);
			Log.d("generate reward", result);
			saveToHistory();
			mustGenerateId = -1;
			Server.getRobotManager().clear();
		}

		private void settlementForZhuang() { // 结算庄家盈亏
			ZhuangManager z = Server.getZhuangManager();
			Zhuang zh = z.getZhuang();
			if (zh != null) {

				long profit = coinIn - coinOut;
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
			}
		}

		private IProfit buildProfit(Zhuang zh, long profit) {
			return new IProfitImplementation(zh, profit);
		}

		private void settlementForAllRoles() {

			generateXiaoCaiJins();//计算所有用户可以获得多少小彩金

			Set<String> keySet = switchs.keySet();
			for (String roleId : keySet) {
				IRole role = Server.getRole(roleId);
				ISwitchs sw = switchs.get(roleId);

				SettlementResult result = settlement(
						Turntable.this.result.getResult(), role, sw);
				rankingList.add(result);
				if (!(role instanceof TestRole)) {
					coinIn += result.getReduce();
					coinOut += result.getAdd();
				}
				settlements.put(role.getId(), result);
			}
			
			removeXiaoCaiJin();
		}

		private void removeXiaoCaiJin() {
			caiJin = getMinCaiJin();
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
					xiaoCaiJins.put(roleId,
							buildXiaoCaiJinReward(roleId, count));
				}
			}

			long countAll = getXiaoCaiJinCountAll();

			
			String base = Server.getConst().getString("CAI_JIN_SCALE_SMALL");

			String[] ss = base.split("\\-");
			double min = new Double(ss[0]);
			double max = new Double(ss[1]);
			
			double caiJinPercentAll = Util.Random.get(min, max);
			
			for (XiaoCaiJinReward r : xiaoCaiJins.values()) {
				double c = r.getCountThisTime();
				double percent = c / countAll * caiJinPercentAll;
				r.setPercent(percent);
			}
		}

		private long getXiaoCaiJinCountAll() {
			long sum = 0;
			for (XiaoCaiJinReward r : xiaoCaiJins.values()) {
				sum += r.getCountThisTime();
			}
			return sum;
		}

		private XiaoCaiJinReward buildXiaoCaiJinReward(String roleId, int count) {
			XiaoCaiJinReward r = new XiaoCaiJinReward();
			r.setRoleId(roleId);
			r.setCountThisTime(count);
			return r;
		}

		private double isXiaoCaiJinHappen() {

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

	private long timeStart;
	private int mustGenerateId = -1; // 本轮必出的东西

	private SwitchAll switchs;
	private String id;
	private Result result;
	private ITimer timer;

	/** 随机倍率序号 */
	private int randomXNumber = 1;

	private boolean isLock;

	private long coinIn; // 金币输入
	private long coinOut; // 金币输出

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

	private Map<String, XiaoCaiJinReward> xiaoCaiJins;

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
			timer.cancel();
		}
		timer = new MyTimer();
		addGenerateResultTask();
		addRestartTask();
		addRobotTask();
	}

	public int getWeight(Row row) {
		String c = "weight-" + getController().getDangWei() + "-"
				+ randomXNumber;
		return row.getInt(c);
	}

	private void addRobotTask() {
		long cd = Server.getConst().getLong("CD");
		long jcd = Server.getConst().getLong("JIE_SUAN_CD");

		long sum = cd + jcd;

		String string = Server.getConst().getString("ROBOT_COMMIT_SEC");
		List<Integer> is = Util.Collection.getIntegers(string);
		for (Integer sec : is) {

			timer.scheduleAtFixedRate(new RobotTask(), sec, sum);
		}
	}

	private void addRestartTask() {
		long cd = Server.getConst().getLong("CD");
		long jcd = Server.getConst().getLong("JIE_SUAN_CD");

		long sum = cd + jcd;
		timer.scheduleAtFixedRate(new RestartTask(), 0, sum);
	}

	private void addGenerateResultTask() {
		long cd = Server.getConst().getLong("CD");
		long jcd = Server.getConst().getLong("JIE_SUAN_CD");

		isLock = true; // 锁定所有用户不可再下注
		long sum = cd + jcd;
		timer.scheduleAtFixedRate(new GenerateResultTask(), cd, sum);
	}

	public long getCd() {
		long cd = Server.getConst().getLong("CD");
		long jcd = Server.getConst().getLong("JIE_SUAN_CD");

		long m = System.currentTimeMillis();
		long t = m - timeStart;

		long rt = cd - t;
		if (rt < 0)
			return cd + jcd + rt;

		return rt;
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

			// if (!Server.getRobotManager().isRobot(role.getId())) {

			coinIn += result.getReduce();
			coinOut += result.getAdd();
			// }

			return new PlayResult(this.result.getResult(), role.getCoin(), null, 0);
		} else {
			throw new ErrorResult(10011).toException();
		}
	}

	/**
	 * 不计时投注
	 */
	public PlayResult playOnceWithOutTime(IRole role, ISwitchs s) {
		checkZhuang(role.getId(), s);
		Result result = generateReward();

		List<Row> rs = result.getResult();
		settlement(rs, role, s);// 结算给当前用户
		return new PlayResult(rs, role.getCoin(), null, 0);
	}

	/**
	 * 开奖
	 */
	private Result generateReward() {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		List<Row> randoms = random(all);
		Result r = new Result();
		r.setResult(randoms);
		return r;
	}

	private List<Row> random(List<Row> all) {
		ArrayList<Row> ls = Lists.newArrayList();
		Row row = randomFirst(all); // 随机出第一个结果
		ls.add(row);
		randomSongDeng(all, ls, row); // 如果出鲨鱼, 送灯
		return ls;
	}

	private void randomSongDeng(List<Row> all, ArrayList<Row> ls, Row row) {
		int count = getSongDengCount(row);
		List<Row> tmp = filter(all, row);
		for (int i = 0; i < count; i++) {
			Row random = randomOne(tmp);
			ls.add(random);
			tmp = filter(tmp, random); // 不能出相同的东西
		}
	}

	private Row randomFirst(List<Row> all) {
		Row row;
		Controller c = getController();
		if (this.mustGenerateId > 0) { // 本轮第一个必出
			row = getMust(all);
		} else if (c.isZhengZaiGanShe() && c.isHappen()) { // 如果干涉程序正在运行,
			row = c.randomOne(all); // 则由干涉程序生成结果
		} else {
			row = randomOne(all);
		}
		return row;
	}

	private List<Row> filter(List<Row> all, Row row) {
		ArrayList<Row> ls = Lists.newArrayList();
		for (Row r : all) {

			String t1 = r.get("type");
			String t2 = row.get("type");

			if (!t1.equals(t2)) {
				ls.add(r);
			}
		}
		return ls;
	}

	private int getSongDengCount(Row row) {
		String type = row.get("type");
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		Row r = sheet.get(type);
		String cs = r.get("songDengCount");
		List<Integer> cc = Util.Collection.getIntegers(cs);
		return Util.Random.get(cc.get(0), cc.get(1));
	}

	private Row randomOne(List<Row> all) {

		WeightFetcher<Row> fet = new WeightFetcherImplementation();

		Row row = Util.Random.getRandomOneByWeight(all, fet);
		return row;
	}

	private Row getMust(List<Row> all) {
		for (Row row : all) {
			if (row.getInt("id") == this.mustGenerateId)
				return row;
		}
		throw new NullPointerException("row " + this.mustGenerateId
				+ "not found");
	}

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

		// Log.d(role.getId(), r.getReduce(), s);
		role.reduceCoin(reduce);// 把所有压了的金币扣了

		long add = 0;
		for (Row row : randoms) {
			add += addCoin(row, role, s);
		}

		long settlementCaiJin = 0;

		Row first = randoms.get(0);
		try {
			settlementCaiJin = settlementCaiJin(role, s, first);
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

		r.setAdd(add);
		r.setXiaoCaiJinAdd(xiaoCaiJinAdd);

		return r;
	}

	private long settlementXiaoCaiJin(IRole role, Row first) {
		XiaoCaiJinReward rwd = xiaoCaiJins.get(role.getId());
		if(rwd == null)
			return 0;
		
		long cj = getCaiJin();
		
		long receive = (long) (rwd.getPercent() * cj);
		
		rwd.setReceive(receive);
		
		role.addCoin(receive);
		
		saveCaiJinLog(role, receive, true);
		
		return receive;
	}

	private void saveCaiJinLog(IRole role, long settlementCaiJin, boolean isSmall) {
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
		if (manager.hasMustTo()) {
			if (manager.isCaiJinMustTo(role.getId())) {
				return settlementCj(role, s, row);
			}
			return 0;
		} else {
			return settlementCj(role, s, row);
		}

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
		boolean hasMustTo = manager.hasMustTo();

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

		if (!hasMustTo && c < condition) {
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
		return row.getInt("x-" + c.getDangWei() + "-" + randomXNumber);
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
		return Server.getKeyValueForever().getLong("SYSTEM_COIN_IN");
	}

	/**
	 * 今日金币输出
	 */
	public long getCoinOutToday() {
		return Server.getKeyValueForever().getLong("SYSTEM_COIN_OUT");
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
	public int getCountHistory(String type) {
		return Server.getKeyValueForever().getInt(
				"SUBMIT_COUNT_HISTOYR:" + type);
	}

	/**
	 * 管理员设置的权重
	 * 
	 * @param idInWeights
	 *            对应到game.xml weights表中的id
	 * @return
	 */
	public int getWeightAdd(int idInWeights) {
		Integer weight = weightByGm.get(idInWeights);
		if (weight == null)
			weight = 0;
		return weight;
	}

	/**
	 * 更新管理员自定义权重
	 * 
	 * @param weightsAdd
	 */
	public void updateWeightsAdd(Map<Integer, Integer> weightsAdd) {
		this.weightByGm = weightsAdd;
		saveWeightsToDb();
	}

	private void saveWeightsToDb() {
		KeyValue kv = Server.getKeyValueForever();
		kv.set("WEIGHTS_BY_GM", weightsToJsonString());
	}

	private String weightsToJsonString() {
		JSONObject o = new JSONObject();
		for (Integer k : weightByGm.keySet()) {
			Integer v = weightByGm.get(k);
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

		SettlementResult result = settlements.get(role.getId());
		List<Row> rst = this.result.getResult();
		if (result == null) {
			commitUserSwitchs(role.getId(), sw);
			result = settlement(rst, role, sw);// 结算给当前用户

			if (!Server.getZhuangManager().isZhuang(role.getId())) {
				rankingList.add(result);// 不是庄家的时候, 才加入排行榜, 不然结算界面会显示庄家排名
			}

			if (!(role instanceof TestRole)) {
				coinIn += result.getReduce();
				coinOut += result.getAdd();
			}
			settlements.put(role.getId(), result);
		}

		long coin = role.getCoin();
		
		long caiJin = result.getCaiJin() + result.getXiaoCaiJinAdd();
		
		return new PlayResult(rst, coin, caiJinNotice, caiJin);
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

}
