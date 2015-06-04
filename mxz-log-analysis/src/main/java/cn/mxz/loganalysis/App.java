package cn.mxz.loganalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.AgainstGoodsLibraryTemplet;
import cn.mxz.AgainstGoodsLibraryTempletConfig;
import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.define.ConfigProperties;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class App {

	public static class Recharge {

		String role;
		String time;
		String count;

		public Recharge(String role, String time, String count) {
			this.role = role;
			this.time = time;
			this.count = count;
		}

		public String getRole() {
			return role;
		}

		public long getTime() {
			long t = new Long(time);
			t *= 1000L;
			return t;
		}

		public int getCount() {
			return new Integer(count);
		}
	}

	public static void main(String[] args) throws Exception {
		print16("1990-01-01", "2222-01-01");
	}

	/**
	 * 商城购买情况
	 * 
	 * 
	 * 回气丹 购买数量:63 寻仙令 购买数量:37544 铜钥匙 购买数量:114 高级传承石 购买数量:47 美酒 购买数量:935 银宝箱
	 * 购买数量:41 金钥匙 购买数量:320 银钥匙 购买数量:114 斗法符文 购买数量:24 战鼓 购买数量:1 神行丹 购买数量:88 神符
	 * 购买数量:24 金宝箱 购买数量:69 乾坤丹 购买数量:3
	 * 
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	static void print2() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"src/main/resources/log_consume.sql"));

		Counter<String> counter = new Counter<String>();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("INSERT INTO `log_consume` VALUES (")) {
				continue;
			}
			line = line.replaceAll("INSERT INTO `log_consume` VALUES \\(", "")
					.replaceAll("\\);", "");
			String[] ss = line.split(", ");
			ConsumeLog x = new ConsumeLog(ss);

			String cc = x.getComment();
			if (cc.startsWith("ShopServiceImpl.buyTool")) {
				String prop = cc.replaceAll("ShopServiceImpl.buyTool\\(", "")
						.replaceAll("\\)", "");
				int id = new Integer(prop.split(",")[0]);
				int count = new Integer(prop.split(",")[1]);
				MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
				String name = temp.getName();
				counter.add(name, count);
			}
		}

		for (String key : counter.keySet()) {
			System.out.println(key + "	  购买数量:" + counter.get(key));
		}
	}

	// XianShiServiceImpl.buyPresent
	/***
	 * 礼包购买详尽日志
	 * 
	 * VIP1特权礼包 礼包 购买数量:162 VIP8特权礼包 礼包 购买数量:1 VIP5特权礼包 礼包 购买数量:10 VIP2特权礼包 礼包
	 * 购买数量:50 VIP6特权礼包 礼包 购买数量:4 VIP4特权礼包 礼包 购买数量:16 逆转大礼包 礼包 购买数量:105 VIP9特权礼包
	 * 礼包 购买数量:1 VIP3特权礼包 礼包 购买数量:24 秘宝大礼包 礼包 购买数量:33 VIP7特权礼包 礼包 购买数量:4
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	static void print3() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"src/main/resources/log_consume.sql"));

		Counter<String> counter = new Counter<String>();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("INSERT INTO `log_consume` VALUES (")) {
				continue;
			}
			line = line.replaceAll("INSERT INTO `log_consume` VALUES \\(", "")
					.replaceAll("\\);", "");
			String[] ss = line.split(", ");
			ConsumeLog x = new ConsumeLog(ss);

			String cc = x.getComment();
			if (cc.startsWith("XianShiServiceImpl.buyPresent")) {
				String prop = cc.replaceAll("XianShiServiceImpl.buyPresent\\(",
						"").replaceAll("\\)", "");
				int id = new Integer(prop.split(",")[0]);
				int count = new Integer(prop.split(",")[1]);
				MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
				String name = temp.getName();
				counter.add(name, count);
			}
		}

		for (String key : counter.keySet()) {
			System.out.println(key + "	 礼包 购买数量:" + counter.get(key));
		}
	}

	/**
	 * 
	 回气丹 直接使用道具数量:328 神行丹 直接使用道具数量:442
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	static void print4() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"src/main/resources/log_consume.sql"));

		Counter<String> counter = new Counter<String>();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("INSERT INTO `log_consume` VALUES (")) {
				continue;
			}
			line = line.replaceAll("INSERT INTO `log_consume` VALUES \\(", "")
					.replaceAll("\\);", "");
			String[] ss = line.split(", ");
			ConsumeLog x = new ConsumeLog(ss);

			String cc = x.getComment();
			if (cc.startsWith("BagServiceImpl.buyAndUseProp(")) {
				String prop = cc.replaceAll("BagServiceImpl.buyAndUseProp\\(",
						"").replaceAll("\\)", "");
				int id = new Integer(prop.split(",")[0]);
				int count = new Integer(prop.split(",")[1]);
				MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
				String name = temp.getName();
				counter.add(name, count);
			}
			if (cc.startsWith("BagServiceImpl.buyAndUseProp2")) {
				String prop = cc.replaceAll("BagServiceImpl.buyAndUseProp2\\(",
						"").replaceAll("\\)", "");
				int id = new Integer(prop.split(",")[0]);
				int count = new Integer(prop.split(",")[1]);
				MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
				String name = temp.getName();
				counter.add(name, count);
			}
		}

		for (String key : counter.keySet()) {
			System.out.println(key + "	 直接使用道具数量:" + counter.get(key));
		}
	}

	// 还有
	// 1、渡天劫的排名元宝奖励产出
	// 2、月卡每日领取的额外产出元宝
	//
	// 姜老板 2014/8/19 15:00:57
	// 3、充值额外赠送的元宝
	// 神行丹 给jjy个数
	/**
	 * Boss战 极限鼓舞 鼓舞 重生次数
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print5() throws IOException, java.text.ParseException {

		boss("2014-08-15 13:00", "2014-08-15 13:30");
		boss("2014-08-15 21:00", "2014-08-15 21:30");

		boss("2014-08-16 13:00", "2014-08-16 13:30");
		boss("2014-08-16 21:00", "2014-08-16 21:30");

		boss("2014-08-17 13:00", "2014-08-17 13:30");
		boss("2014-08-17 21:00", "2014-08-17 21:30");

	}

	static void boss(String timeStart, String timeEnd) throws ParseException {
		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` = '[Gold]' AND log_text LIKE '%IBossTransform%' AND log_time > '"
							+ timeStart + "' AND log_time < '" + timeEnd + "';");

			rs = ps.executeQuery();

			Counter<String> counter = new Counter<String>();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				// UserBaseSql.getNick(roleId)
				// [cn.mxz.bossbattle.IBossTransform.rebirth()|1ms(a)|userId:101110|socketId:165269
				LogDataImpl data = new LogDataImpl(rs);

				String format = s.format(data.getTime());

				String log = data.getLog();

				String nick = format + " " + getNick(log);

				if (log.contains("IBossTransform.inspire")) {
					counter.add(nick + "鼓舞");
				} else if (log.contains("IBossTransform.rebirth")) {
					counter.add(nick + "重生");
				} else if (log.contains("IBossTransform.forceJoinActivity")) {
					counter.add(nick + "强制购买加入活动");
				}
			}

			System.out.println(timeStart + " - " + timeEnd + " 的情况：");
			for (String key : counter.keySet()) {
				System.out.println(key /* + "	 次数:" + counter.get(key) */);
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	static String getNick(String log) {
		// cn.mxz.bossbattle.IBossTransform.inspire;100661;悲测稍;281;-5
		String[] ss = log.split(";");
		// String role = ss[2].replaceAll("userId:", "");
		// return UserBaseSql.getNick(role);
		return ss[2];
	}

	/**
	 * Boss战 极限鼓舞 鼓舞 重生次数
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print15() throws IOException, java.text.ParseException {

		boss("2014-08-15 13:00", "2014-08-15 13:30");
		boss("2014-08-15 21:00", "2014-08-15 21:30");

		boss("2014-08-16 13:00", "2014-08-16 13:30");
		boss("2014-08-16 21:00", "2014-08-16 21:30");

		boss("2014-08-17 13:00", "2014-08-17 13:30");
		boss("2014-08-17 21:00", "2014-08-17 21:30");

	}

	static void boss2(String timeStart, String timeEnd) throws ParseException {
		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%Service%' AND log_text LIKE '%BossTransformImpl%' AND log_time > '"
							+ timeStart + "' AND log_time < '" + timeEnd + "';");

			rs = ps.executeQuery();

			Counter<String> counter = new Counter<String>();
			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);

				String log = data.getLog();
				if (log.contains("BossTransformImpl.inspire(true)")) {
					counter.add("极限鼓舞", 1);
				} else if (log.contains("BossTransformImpl.inspire(false)")) {
					counter.add("鼓舞", 1);
				} else if (log.contains("BossTransformImpl.rebirth")) {
					counter.add("重生", 1);
				}

			}

			System.out.println(timeStart + " - " + timeEnd + " 的情况：");
			for (String key : counter.keySet()) {
				System.out.println(key + "	 次数:" + counter.get(key));
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	/**
	 * 开服礼包产出（程序内部产出）
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print8() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%[Gold]%' AND log_text LIKE 'cn.mxz.openserver.OpenServerTransform.receive;%';");

			rs = ps.executeQuery();
			// 最后一个是元宝产出
			// cn.mxz.prizecenter.IPrizeCenterTransform.getPrizeFromLinekong;100663;十七哥;300;100

			int count = 0;
			Counter<String> counter = new Counter<String>();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String log = data.getLog();
				String[] ss = log.split(";");
				try {
					int gold = new Integer(ss[4]);
					String nick = ss[2];
					count += gold;
					counter.add(nick, gold);
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println(s + " " + counter.get(s));
			}

			System.out.println("开服礼包产出：" + count);

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	// 还有
	// 1、渡天劫的排名元宝奖励产出
	// 2、月卡每日领取的额外产出元宝
	//
	// 姜老板 2014/8/19 15:00:57
	// 3、充值额外赠送的元宝
	// 神行丹 给jjy个数
	/**
	 * 奖励中心产出（程序内部产出）
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print6() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%[Gold]%' AND log_text LIKE 'cn.mxz.prizecenter.IPrizeCenterTransform.getPrize;%';");

			rs = ps.executeQuery();
			// 最后一个是元宝产出
			// cn.mxz.prizecenter.IPrizeCenterTransform.getPrizeFromLinekong;100663;十七哥;300;100

			int count = 0;
			Counter<String> counter = new Counter<String>();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String log = data.getLog();
				String[] ss = log.split(";");
				try {
					int gold = new Integer(ss[4]);
					String nick = ss[2];
					count += gold;
					counter.add(nick, gold);
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println(s + " " + counter.get(s));
			}

			System.out.println("程序内部 发送到奖励中心的产出：" + count);

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	/**
	 * 奖励中心产出（通过GM平台发放的奖励产出）
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print7() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%[Gold]%' AND log_text LIKE 'cn.mxz.prizecenter.IPrizeCenterTransform.getPrizeFromLinekong;%';");

			rs = ps.executeQuery();
			// 最后一个是元宝产出
			// cn.mxz.prizecenter.IPrizeCenterTransform.getPrizeFromLinekong;100663;十七哥;300;100

			int count = 0;
			Counter<String> counter = new Counter<String>();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String log = data.getLog();
				try {
					String[] ss = log.split(";");
					int gold = new Integer(ss[4]);
					String nick = ss[2];
					count += gold;
					counter.add(nick, gold);
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println(s + " " + counter.get(s));
			}

			System.out.println("累计从 GM平台发放到奖励中心的产出：" + count);

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	/**
	 * 
	 * 输出格式： 使用道具 元宝/金贝壳消耗:-740 领取保护奖励 元宝/金贝壳消耗:0 购买道具并使用 元宝/金贝壳消耗:22860 元神重置
	 * 元宝/金贝壳消耗:3553 领取升级奖励 元宝/金贝壳消耗:-146920 寻仙 元宝/金贝壳消耗:0 替补位刷新 元宝/金贝壳消耗:900
	 */
	static void print1() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"src/main/resources/log_consume.sql"));

		Counter<String> counter = new Counter<String>();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("INSERT INTO `log_consume` VALUES (")) {
				continue;
			}
			line = line.replaceAll("INSERT INTO `log_consume` VALUES \\(", "")
					.replaceAll("\\);", "");
			String[] ss = line.split(", ");
			ConsumeLog x = new ConsumeLog(ss);

			String comment = x.getComment().replaceAll("\\(.+", "");

			comment = ServiceTranslator.translate(comment);

			counter.add(comment, new Integer(x.getGold()));

		}
		
		Closer.close(br);
		for (String key : counter.keySet()) {
			System.out.println(key + "	  元宝/金贝壳消耗:" + -counter.get(key));
		}
	}

	/**
	 * 扫荡情况
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	@SuppressWarnings("resource")
	static void print9() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%Service%' AND log_text LIKE 'MissionServiceImpl.mopingUp%';");

			rs = ps.executeQuery();
			// 最后一个是元宝产出
			// cn.mxz.prizecenter.IPrizeCenterTransform.getPrizeFromLinekong;100663;十七哥;300;100

			Counter<String> counter = new Counter<String>();
			// /**
			// * 扫荡
			// *
			// * @param missionId 关卡ID
			// * @param type 扫荡类型 1、主线 2、支线 3、小怪
			// * @param count 扫荡次数
			// * @return id,数量,id,数量 奖品字符串
			// */
			// String mopingUp(int missionId, int type, int count);
			// MissionServiceImpl.mopingUp(2,3,1)|14ms(a)|userId:100661|nick:悲测稍|socketId:18
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

			Counter<String> nickCounter = new Counter<String>();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				// String time = f.format(data.getTime());

				String log = data.getLog();
				String[] ss = log.split("\\|");
				String nick = ss[3].split(":")[1];
				nickCounter.add(nick);
			}

			File fff = new File("moop.txt");
			// fff.createNewFile();
			PrintWriter out = new PrintWriter(fff);

			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%Service%' AND log_text LIKE 'MissionServiceImpl.mopingUp%';");

			rs = ps.executeQuery();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String time = f.format(data.getTime());

				String log = data.getLog();
				String[] ss = log.split("\\|");
				log = ss[0];

				String nick = ss[3].split(":")[1];

				if (nickCounter.get(nick) < 2) {
					continue;
				}

				log = log.replaceAll("MissionServiceImpl.mopingUp\\(", "");
				log = log.replaceAll("\\)", "");

				// 2,3,1
				String[] split = log.split(",");

				String type = getType(split);
				int count = new Integer(split[2]);

				out.println(nick + " 扫荡 " + type + " " + count + " 次");

				counter.add(time + " " + type, count);
			}
			out.println();
			for (String s : counter.keySet()) {
				out.println("累计扫荡： " + s + " " + counter.get(s) + " 次");
			}
			out.flush();

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	// ShopServiceImpl.buyTool(130046,28);100668;鈊葬灬宝贝;40;-280
	// 美酒、神行丹、回气丹
	static void print10() throws IOException, java.text.ParseException {

		String string = "BagServiceImpl.buyAndUseProp2";
		pritBuy(string);

		string = "ShopServiceImpl.buyTool";
		pritBuy(string);
	}

	static void pritBuy(String string) {
		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			// String string = "ShopServiceImpl.buyTool";

			ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME
					+ " WHERE `log_head` LIKE '%Gold%' AND log_text LIKE '"
					+ string + "%';");

			rs = ps.executeQuery();

			// int count = 0;
			Counter<String> counter = new Counter<String>();

			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String time = f.format(data.getTime());

				String log = data.getLog();
				try {

					// ShopServiceImpl.buyTool(130046,28);100668;鈊葬灬宝贝;40;-280
					// 美酒、神行丹、回气丹

					String[] ss = log.split(";");
					// 130046,28
					String buy = ss[0].replaceAll(string + "\\(", "")
							.replaceAll("\\)", "");

					String[] split = buy.split(",");
					int propId = new Integer(split[0]);
					// int buyCount = new Integer(split[1]);
					MarketPlaceTemplet temp = MarketPlaceTempletConfig
							.get(propId);

					int gold = new Integer(ss[4]);
					String a = "美酒、神行丹、回气丹";
					String name = temp.getName();
					if (a.contains(name)) {
						counter.add(time + " " + name, -gold);
					}
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println("购买 " + s + " 累计消耗 " + counter.get(s)
						+ " 元宝");
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}
	}

	// * @param type 扫荡类型 1、主线 2、支线 3、小怪
	static String getType(String[] split) {
		String type = split[1];
		if (type.equals("1")) {
			return "主线";
		}
		if (type.equals("2")) {
			return "支线";
		}
		if (type.equals("3")) {
			return "小怪";
		}
		throw new RuntimeException();
	}

	/**
	 * 奖励中心产出（通过GM平台发放的奖励产出）
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print11() throws IOException, java.text.ParseException {

		List<Recharge> ls = getRecharges();

		Counter<String> counter = new Counter<String>();
		for (Recharge recharge : ls) {
			printFirstThreeTimes(counter, recharge);
		}

		System.out.println("-------结果-------");
		for (String s : counter.keySet()) {
			int cc = counter.get(s);
			System.out.println(s + " 的人数 " + cc);

		}
	}

	/**
	 * 黑市 消费
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print12() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` = '[Gold]' AND log_text LIKE 'cn.mxz.heishi.HeiShiTransform%';");

			rs = ps.executeQuery();

			Counter<String> counter = new Counter<String>();

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String log = data.getLog();
				String[] ss = log.split(";");
				try {
					int gold = new Integer(ss[4]);
					String nick = ss[2];
					String translate = ServiceTranslator.translate(ss[0]);
					counter.add(nick + " " + translate + " 消耗元宝:", -gold);
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println(s + " " + counter.get(s));
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	/**
	 * 黑市 兑换详单
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print13() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;
		// [cn.mxz.heishi.HeiShiTransform.exchange(243)|6ms(a)|userId:100679|socketId:1472
		// [cn.mxz.heishi.HeiShiTransform.refresh()|18ms(a)|userId:100679|socketId:1472

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` = 'Service' AND log_text LIKE '[cn.mxz.heishi.HeiShiTransform.exchange%';");

			rs = ps.executeQuery();

			Counter<String> counter = new Counter<String>();
			Pattern compile = Pattern.compile("exchange\\([0-9]+\\)");
			Pattern dc = Pattern.compile("[0-9]+");

			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
				String log = data.getLog();
				// String[] ss = log.split(";");
				try {
					Matcher m = compile.matcher(log);
					m.find();
					String group = m.group();
					Matcher mm = dc.matcher(group);
					mm.find();
					String id = mm.group();

					AgainstGoodsLibraryTemplet temp = AgainstGoodsLibraryTempletConfig
							.get(new Integer(id));
					String propNeame = temp.getPropNeame();

					// int gold = new Integer(ss[4]);
					// String nick = ss[2];
					// String translate = ServiceTranslator.translate(ss[0]);
					String[] split = temp.getConsume().split(",");
					int needId = new Integer(split[0]);
					int needCount = new Integer(split[1]);
					String needText;
					if (needId == 130031) {
						needText = "美酒";
					} else {
						needText = "元宝";
					}

					counter.add(propNeame + "-" + needText + "", needCount);
				} catch (Exception e) {
					System.err.println(log);
					throw new RuntimeException(e);
				}
			}

			for (String s : counter.keySet()) {
				System.out.println("兑换 " + s + " 累计消耗 " + counter.get(s));
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	/**
	 * 黑市 刷新详单
	 * 
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	static void print14() throws IOException, java.text.ParseException {

		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;
		// [cn.mxz.heishi.HeiShiTransform.exchange(243)|6ms(a)|userId:100679|socketId:1472
		// [cn.mxz.heishi.HeiShiTransform.refresh()|18ms(a)|userId:100679|socketId:1472

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` = 'Service' AND log_text LIKE '[cn.mxz.heishi.HeiShiTransform.refresh%';");

			rs = ps.executeQuery();

			int count = 0;

			while (rs.next()) {
				count++;
			}
			System.out.println("总计刷新次数" + count);

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` = '[Gold]' AND log_text LIKE 'cn.mxz.heishi.HeiShiTransform.refresh%';");

			rs = ps.executeQuery();

			int count = 0;

			while (rs.next()) {
				count++;
			}
			System.out.println("总计元宝刷新次数" + count);

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}

	static void printFirstThreeTimes(Counter<String> counter, Recharge recharge) {
		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;
		// "2014-08-17 21:00", "2014-08-17 21:30"

		long time = recharge.getTime();
		Date date = new Date(time);

		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String format = ff.format(date);

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME
					+ " WHERE `log_head` = 'Gold' AND log_time > '" + format
					+ "' AND log_text LIKE '%;" + recharge.getRole()
					+ ";%;-%' LIMIT 3;");

			rs = ps.executeQuery();

			int count = 1;
			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);

				String log = data.getLog();
				String[] split = log.split(";");
				String whatToDo = split[0];
				String nick = split[2];

				String wtd = whatToDo.replaceAll("\\(.*\\)", "");
				String translate = ServiceTranslator.translate(wtd);

				String what = " 充值后 第" + count++ + "件事情：" + translate + " "
						+ getWhatBuy(whatToDo);

				counter.add(what);

				System.out.println(nick + what);
			}

			System.out.println();

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}
	}

	static String getWhatBuy(String whatToDo) {
		if (whatToDo.startsWith("ShopServiceImpl.buyTool")) {
			String s = whatToDo.replaceAll("ShopServiceImpl.buyTool", "")
					.replaceAll("\\(", "").replaceAll("\\)", "");
			String[] ss = s.split(",");
			int id = new Integer(ss[0]);
			// int count = new Integer(ss[1]);

			MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
			return temp.getName();
		} else if (whatToDo.startsWith("XianShiServiceImpl.buyPresent")) {
			String s = whatToDo.replaceAll("XianShiServiceImpl.buyPresent", "")
					.replaceAll("\\(", "").replaceAll("\\)", "");
			String[] ss = s.split(",");
			int id = new Integer(ss[0]);
			// int count = new Integer(ss[1]);

			MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
			return temp.getName();
		} else {
			return "";
		}
	}

	static List<Recharge> getRecharges() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"src/main/resources/recharge_record.sql"));

		List<Recharge> ls = Lists.newArrayList();

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("INSERT INTO `recharge_record` VALUES (")) {
				continue;
			}
			line = line.replaceAll("INSERT INTO `recharge_record` VALUES \\(",
					"").replaceAll("\\);", "");
			String[] ss = line.split(", ");

			// '100173', '100667', '300', '1407907850'
			String role = ss[1].replaceAll("'", "");
			String time = ss[3].replaceAll("'", "");
			String count = ss[2].replaceAll("'", "");

			ls.add(new Recharge(role, time, count));
		}

		br.close();
		return ls;
	}

	public static class ConsumeLog {

		String id;
		String uname;
		String nick;
		String time;
		String cash;
		String jinBeiKe;
		String comment;
		String gold;

		public ConsumeLog(String[] ss) {
			id = get(ss[0]);
			uname = get(ss[1]);
			nick = get(ss[2]);
			time = get(ss[3]);
			cash = get(ss[4]);
			jinBeiKe = get(ss[5]);
			gold = get(ss[6]);
			comment = get(ss[7]);
		}

		public String getId() {
			return id;
		}

		public String getUname() {
			return uname;
		}

		public String getNick() {
			return nick;
		}

		public String getTime() {
			return time;
		}

		public String getCash() {
			return cash;
		}

		public String getJinBeiKe() {
			return jinBeiKe;
		}

		public String getComment() {
			return comment;
		}

		public String getGold() {
			return gold;
		}

		String get(String string) {
			return string.substring(1, string.length() - 1);
		}

		@Override
		public String toString() {
			return JSON.toJSONString(ConsumeLog.this);
		}
	}
	
	
	
	static void print16(String timeStart, String timeEnd) throws ParseException {
		final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection;

		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection
					.prepareStatement("SELECT * FROM "
							+ TABLE_NAME
							+ " WHERE `log_head` LIKE '%Service%' AND log_text LIKE '%NvwaTransform.buy()%' AND log_time > '"
							+ timeStart + "' AND log_time < '" + timeEnd + "';");

			rs = ps.executeQuery();

			
			Counter<String> counter = new Counter<String>();
			while (rs.next()) {
				LogDataImpl data = new LogDataImpl(rs);
//				[cn.mxz.nvwa.NvwaTransform.buy()|58ms(c)|userId:105570|socketId:18507
				String log = data.getLog();
				String[] ss = log.split("\\|");
				String user = ss[2];
				counter.add(user);
			}

			System.out.println(timeStart + " - " + timeEnd + " 的情况：" + counter.size());
			for (String key : counter.keySet()) {
				System.out.println(key + "	 购买次数:" + counter.get(key));
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps);
			Closer.close(connection);
		}

	}


}
