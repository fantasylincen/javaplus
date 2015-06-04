package cn.mxz;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import mongo.gen.MongoGen.Daos;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import cn.javaplus.excel.LemonSheet;
import cn.javaplus.excel.LemonWorkBook;
import cn.javaplus.security.DES;
import cn.javaplus.util.Util;
import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.base.config.Cfg;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.server.MongoCollectionFetcher;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.NotInitOverException;
import cn.mxz.handler.InitService;
import cn.mxz.handler.PvpService;
import cn.mxz.handler.UserService;
import cn.mxz.heishi.HeiShi;
import cn.mxz.init.ReadyUserImpl;
import cn.mxz.init.SocketManager;
import cn.mxz.log.LogTableDAO;
import cn.mxz.log.MXZLogger.LogData;
import cn.mxz.protocols.pvp.PvpP.PvpUser;
import cn.mxz.protocols.pvp.PvpP.PvpUsersPro;
import cn.mxz.temp.TempKey;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.testbase.TestSocket;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.thirdpaty.ThirdPartyRole;
import cn.mxz.user.init.ReadyUser;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;
import cn.mxz.util.sencitive.SencitiveConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lemon.commons.database.DBProperties;
import com.lemon.commons.database.DataBasePool;
import com.lemon.commons.event.IDataHandler;
import com.lemon.commons.socket.MultithreadedServer;
import com.lemon.commons.socket.MySocket;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import db.DataBasePoolC3P0;
import db.GameDB;
import db.dao.impl.DaoFactory;

@SuppressWarnings("unused")
public class TestAll extends TestBaseAccessed {
	
	
	
	
	
//	public static void main(String[] args) {
//		XiangLu xiangLu = new XiangLuImpl();
//		xiangLu.shangXiang();
////		、、。。。
////		、。。。
//		
//		
//	}
	
	public static void main(String[] args) {
		 String ss = DES.encrypt("司馬仲達");
		 System.out.println(DES.decrypt(ss));
	}
	
	
	
	
//	public static void main(String args[]){
//		int score[] = {67,89,87,69,90,100,75,90} ;	// 定义整型数组
//		int age[] = {31,30,18,17,8,9,1,39} ;		// 定义整型数组
//		TestAll.sort(score) ;		// 数组排序
//		
//		print(score) ;		// 数组打印
//		System.out.println("\n---------------------------") ;
//		sort(age) ;			// 数组排序
//		print(age) ;		// 数组打印
//	}
	
	public static void sort(int temp[]){		// 执行排序操作
		for(int i=1;i<temp.length;i++){
			for(int j=0;j<temp.length;j++){
				if(temp[i]<temp[j]){
					int x = temp[i] ;
					temp[i] = temp[j] ;
					temp[j] = x ;
				}
			}
		}
	}
	
	public static void print(int temp[]){		// 输出数组内容
		for(int i=0;i<temp.length;i++){
			System.out.print(temp[i] + "\t") ;
		}
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class Door {
		public int getColor(){
			return 0;
		}
	}

	
	static class T3 extends Thread {

		@Override
		public void run() {
			yield();
			for (int i = 0; i < 50; i++) {
				System.out.println("a");
			}
		}
	}
	
	static class T4 extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				System.out.println("b");
			}
		}
	}

	/**
	 * 执行命令，获取返回值
	 * 
	 * @param cmd
	 *            要执行的dos命令
	 * @param path
	 * @return 执行结果按行返回List<String>
	 */
	private static List<String> runCmd(String cmd, String path) {

		List<String> ret = Lists.newArrayList();
		try {
			Process process = Runtime.getRuntime().exec(cmd, null,
					new File(path));
			BufferedInputStream in = new BufferedInputStream(
					process.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"GBK"));
			String s;
			while ((s = br.readLine()) != null) {
				ret.add(s);
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 如果倒数第lastIndex行包含这s中的任意一个 表示成功
	 * 
	 * @param runCmd
	 * @param lastIndex
	 * @param s
	 */
	private static void checkLast(List<String> runCmd, int lastIndex,
			String... s) {
		String last = runCmd.get(runCmd.size() - lastIndex);
		for (String ss : s) {
			if (last.contains(ss)) {
				return;
			}
		}
		throw new RuntimeException("命令执行出错:" + last);
	}

	private static void page() {
		List<Integer> ls = Lists.newArrayList();
		int c = Util.Random.get(1, 50);
		for (int i = 1; i <= c; i++) {
			ls.add(i);
		}

		List<List<Integer>> page = Util.Collection.page(ls, 4);
		for (List<Integer> list : page) {
			System.out.println(list);
		}

		System.out.println("=== " + c);
	}

	private static void a() {
		List<String> ls = new ArrayList<String>();
		ls.add("a");
		ls.add("b");
		ls.add("c");
		ls.add("d");
		ls.add("e");
		List<String> all = zuHe(ls);
		for (String test : all) {
			System.out.println(test);
		}
		System.out.println("size : " + all.size());
	}

	private static List<String> zuHe(List<String> ls) {
		List<String> all = new ArrayList<String>();
		int size = ls.size();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					all.add(ls.get(i) + ls.get(j) + ls.get(k));
				}
			}
		}
		return all;
	}

	private static class B {
		int a;
		int b;
		int c;
		int d;
		int e;

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public int getD() {
			return d;
		}

		public void setD(int d) {
			this.d = d;
		}

		public int getE() {
			return e;
		}

		public void setE(int e) {
			this.e = e;
		}
	}

	// public static void main(String[] args) throws Exception {
	//
	// long time = System.currentTimeMillis();
	// for (int i = 0; i < 100000; i++) {
	// B a = new B();
	// Object json = JSON.toJSON(a);
	// JSON.parse(json.toString());
	// }
	// System.out.println(System.currentTimeMillis() - time);
	//
	// // initServer();
	//
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":1,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":1,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":1,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":1,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":1,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":1,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":1,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":1,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":1,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":1,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":1,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":1,"vip13":0,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":1,"vip14":0,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":1,"vip15":0,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":1,"vip16":0}
	// //
	// {"vip0":0,"vip1":0,"vip2":0,"vip3":0,"vip4":0,"vip5":0,"vip6":0,"vip7":0,"vip8":0,"vip9":0,"vip10":0,"vip11":0,"vip12":0,"vip13":0,"vip14":0,"vip15":0,"vip16":1}
	// }

	// private static void printVip() {
	// List<VipPrivilegeTemplet> all = VipPrivilegeTempletConfig.getAll();
	// Fetcher<VipPrivilegeTemplet, Object> c = new Fetcher<VipPrivilegeTemplet,
	// Object>() {
	//
	// @Override
	// public Object get(VipPrivilegeTemplet t) {
	// return "\"vip" + t.getLevel() + "\":" + t.getSilverBox();
	// }
	// };
	//
	// Debuger.debug("{" + Util.Collection.linkWith(", ", all, c) + "}");
	// }

	// public static void main(String[] args) throws Exception {
	//
	// printEvents();
	//
	// Collection<City> all = WorldFactory.getWorld().getAll(); PrintWriter pw =
	// new PrintWriter(new OutputStreamWriter(new
	// FileOutputStream("log.txt"),"UTF-8")); for (City city : all) { if
	// (city.getId().startsWith("pvp_robot")) continue; int day =
	// cn.javaplus.util.Util.Time.getCurrentDay(); int lastDay =
	// cn.javaplus.util.Util.Time.getDay(city.getLastLoginMillis()); boolean
	// isLoginToday = day == lastDay; Player player = city.getPlayer(); int
	// times = player.get(PlayerProperty.LOGIN_TIMES_HISTORY) + 1;
	// pw.println(city.getId() + "," + city.getPlayer().getNick() + "," +
	// city.getLevel() + "," + isLoginToday + "," + times); } pw.flush();
	// pw.close();
	// }

	private static void initServer() {
		DataBasePool.SIZE = 20;

		PropertyConfigurator.configure("res/log4j.properties"); // 初始化日志配置
		ServerConfig.init("-serverId:559102"); // 根据main输入参数,决定服务器ID,运行类型等
		Debuger.init(); // 调试器初始化
		// InitDB.getInstance(); //初始化SP库

		SencitiveConfig.init();

		SystemLog.debug("ServiceTestBase.init() 服务器区ID:"
				+ ServerConfig.getServerId());

		DaoFactory.setFetcher(GameDB.getInstance());
		Daos.setCollectionFetcher(new MongoCollectionFetcher());
	}

	private static void initSocket(City city) {
		final World world = WorldFactory.getWorld();

		final SocketManager socketManager = world.getSocketManager();

		socketManager.bind(new TestSocket(), city);
	}

	private static void printEvents() {
		List<String> content = Util.File.getLines(TestAll.class
				.getResource("test"));

		HashMap<String, String> map = Maps.newHashMap();

		for (String string : content) {
			String string2 = map.get(key(string));
			if (string2 != null) {
				map.remove(key(string));
			} else {
				map.put(key(string), string);
			}
		}

		for (String string : map.values()) {

			System.out.println("TestAll.enclosing_method()" + string);
		}

	}

	private static String key(String string) {
		// Pattern compile = Pattern.compile("cn\\.mxz.*");
		// Matcher matcher = compile.matcher(string);
		// boolean find = matcher.find();
		// return matcher.group();
		return string;
	}

	// private static void testFormula() {
	//
	//
	//
	// for (int times = 1; times < 100; times++) {
	// int x = get(times);
	// System.out.println("次数:" + times + " 倍数:" + x);
	// }
	//
	//
	// }
	//
	// /***
	// * 根据次数取倍数
	// * @param times
	// * @return
	// */
	// private static int get(int times) {
	// return (int) Math.pow(times, Math.log(2, times));
	// }

	private static void findTimeTooLong() {
		List<String> lines = Util.File
				.getLines("C:/Users/Administrator/Desktop/logtable.txt");
		for (String string : lines) {
			if (string.contains("(c)")) {
				System.out.println(string);
			}
		}
	}

	private static String executString(String nowRule) throws Exception {

		ClassLoader parent = ClassLoader.getSystemClassLoader();

		GroovyClassLoader loader = new GroovyClassLoader(parent);

		Class<?> gclass = loader.parseClass(nowRule);

		GroovyObject groovyObject = (GroovyObject) gclass.newInstance();

		groovyObject.invokeMethod("run", "");
		return nowRule;
	}

	private static void testLog() {
		DBProperties p = new DBProperties();

		p.setDataCon("jdbc:mysql://localhost:3306/log_db_temp");

		p.setDrives(Cfg.DBDriver);

		p.setPassword("game_ttafs");

		p.setUser("root");

		DataBasePoolC3P0 fecher = new DataBasePoolC3P0(p);

		// Connection c = fecher.getConnection();

		LogTableDAO dao = new LogTableDAO(fecher);
		List<LogData> ls = Lists.newArrayList();
		LogData e = new LogDataTest();
		ls.add(e);
		dao.addAll(ls);

		// PreparedStatement ps = null;
		// ResultSet rs = null;
		//
		// int i = 10;
		//
		// try {
		// String sql = "select * from logtable";
		// ps = c.prepareStatement(sql);
		// rs = ps.executeQuery();
		// while (rs.next()) {
		//
		// String text = rs.getString("log_text");
		// if (text.contains("u31818990")) {
		// System.out.println(text);
		// if (text.contains("153015")) {
		// break;
		// }
		// }
		// }
		// } catch (SQLException e) {
		// throw new SQLRuntimeException(e);
		// } finally {
		// Closer.close(rs, ps);
		// Closer.close(c);
		// }
	}

	private static void testSameType() {
		System.out.println(Util.JavaType.isSameBaseType(int.class,
				Integer.class));
		System.out.println(Util.JavaType.isSameBaseType(Integer.class,
				int.class));
		System.out.println(Util.JavaType.isSameBaseType(Double.class,
				Double.class));
		System.out.println(Util.JavaType.isSameBaseType(Double.class,
				double.class));
		System.out.println(Util.JavaType.isSameBaseType(double.class,
				double.class));
		System.out.println(Util.JavaType.isSameBaseType(double.class,
				Double.class));
		System.out.println(Util.JavaType.isSameBaseType(TestAll.class,
				TestAll.class));
		System.out.println(Util.JavaType.isSameBaseType(TestAll.class,
				Byte.class));
		System.out.println(Util.JavaType.isSameBaseType(Double.class,
				Byte.class));
		System.out.println(Util.JavaType.isSameBaseType(double.class,
				Byte.class));
		System.out.println(Util.JavaType
				.isSameBaseType(float.class, Byte.class));
		System.out.println(Util.JavaType.isSameBaseType(float.class,
				float.class));

		Integer a = new Integer(1);

		System.out.println(Util.JavaType.isInstance(Integer.class, 1));
	}

	private static void testServer() {
		MultithreadedServer s = new MultithreadedServer(11111);
		IDataHandler handler = new IDataHandler() {

			@Override
			public void onDestroy(MySocket connection) throws IOException {

			}

			@Override
			public void onData(MySocket s) throws IOException {
				while (!s.getBufferList().isEmpty()) {
					final byte[] data = s.getBufferList().poll();
					// String message = new String(data);
					ByteBuffer buffer = ByteBuffer.allocate(1);
					buffer.put((byte) 1);
					s.write(buffer);
					System.out.println("" + Arrays.toString(data));
				}
			}
		};
		s.setHandler(handler);
		Thread thread = new Thread(s);
		thread.start();
	}

	private static void testExcel() {

		LemonWorkBook book = new LemonWorkBook(
				"D:/workspace/InternetFiles/策划资料/手机数值/[战士]战士模版_Fighter.xls");
		LemonSheet sheet = book.getSheet(0);
		String[][] autoGet = sheet.autoGet2();
		for (int i = 0; i < autoGet.length; i++) {
			String[] strings = autoGet[i];
			for (int j = 4; j < 5; j++) {
				String string = strings[j];
				System.out.print(string + "   ");
			}
			System.out.println();
		}
	}

	// private static void testBranchBoss() {
	// BranchBossFallTempletConfig.load();
	// List<BranchBossFallTemplet> all = BranchBossFallTempletConfig.getAll();
	// for (int a = 0; a < 1000; a++) {
	//
	// for (int i = 4; i < 109; i++) {
	//
	// ZXSJGenerator z = new ZXSJGenerator(i);
	// z.generate();
	// }
	// }
	//
	// }

	@After
	public void end() {
		Debuger.debug("TestBaseAccessed.end()");
	}

	private static void testLineKong() {
		try {
			ThirdPartyPlatform e = ThirdPartyPlatformFactory
					.getThirdPartyPlatform("113.208.129.53", 14583);

			// 432796269 蓝港ID
			// 10183436 用户名
			// e.deleteRole("10183436", "1");
			String lineKongId = "12084442";
			// createUserToWorld(roleId, lineKongId);

//			while (true) {
//				long time = System.currentTimeMillis();
//				String roleId = e.getRoleId(lineKongId);
//				Util.Thread.sleep(1000);
//				Debuger.debug("TestAll.testLineKong()" + roleId);
//				Debuger.debug("{enclosing_type}.testLineKong 耗时:"
//						+ (System.currentTimeMillis() - time));
//			}

			//
			// City city = CityFactory.getCity(roleId);
			//
			// initSocket(city);
			//
			// city.getTempCollection().put(TempKey.OPERATION_THIS_TIME,
			// "TestAll.testLinekong()");
			// // roleId = e.createUser(lineKongId, false, 1, "192.168.1.1",
			// // "张三疯");
			// System.out.println(roleId);
			// ThirdPartyRole role = getRole(roleId, lineKongId);
			// int gold = e.getGold(role);
			// System.out.println(gold);
			// e.pay(ThirdPartyPlatformFactory.createRole(city), 1);
			// gold = e.getGold(role);
			// System.out.println(gold);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHeiShiRefresh() {

		Collection<City> all = WorldFactory.getWorld().getAll();
		// System.out.println(all);

		City city = CityFactory.getCity("lc101_r0");
		city.getPlayer().addGiftGold(1000000);
		HeiShi heiShi = city.getHeiShi();
		for (int i = 0; i < 1000; i++) {
			heiShi.refresh();
		}
	}

	@Test
	public void testPay() {
//
//		try {
//			ThirdPartyPlatform e = ThirdPartyPlatformFactory
//					.getThirdPartyPlatform("192.168.1.55", 5000);
//			e.connect();
//			e.bind();
//			e.bind();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

//	/**
//	 * 删除蓝港服务器角色
//	 */
//	@Test
//	public void testLinkong() {
//
//		try {
//			ThirdPartyPlatform e = ThirdPartyPlatformFactory
//					.getThirdPartyPlatform("113.208.129.53", 14583);
//
//			// 432796269 蓝港ID
//			// 10183436 用户名
//			// e.deleteRole("10183436", "1");
//			String lineKongId = "432796269";
//			String roleId = e.getRoleId(lineKongId);
//			// createUserToWorld(roleId, lineKongId);
//
//			City city = CityFactory.getCity(roleId);
//
//			city.getTempCollection().put(TempKey.OPERATION_THIS_TIME,
//					"TestAll.testLinekong()");
//			// roleId = e.createUser(lineKongId, false, 1, "192.168.1.1",
//			// "张三疯");
//			System.out.println(roleId);
//			ThirdPartyRole role = getRole(roleId, lineKongId);
//			int gold = e.getGold(role);
//			e.pay(ThirdPartyPlatformFactory.createRole(city), 1);
//			System.out.println(gold);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private void createUserToWorld(String roleId, String lineKongId) {

		ReadyUser ready = new ReadyUserImpl();
		ready.setAccounts(lineKongId);
		ready.setClientType(1);
		ready.setFighterTypeId(300006);
		ready.setNick("张三疯");
		ready.setRoleId(roleId);
		CityFactory.createNewCity(ready);
	}

	private static ThirdPartyRole getRole(String roleId, String linekongId) {
		City city = CityFactory.getCity(roleId);
		city.getTempCollection().put(TempKey.USER_ID, linekongId);
		return ThirdPartyPlatformFactory.createRole(city);
	}

	private static void testString2Int() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(("12gsad:;" + i).hashCode());
		}
	}

	private static void testMongoDB3() {
	}

	private static void testCouchBase2() throws Exception {
		// (Subset) of nodes in the cluster to establish a connection
		List<URI> hosts = Arrays.asList(new URI("http://127.0.0.1:8091/pools"));

		// Name of the Bucket to connect to
		String bucket = "default";

		// Password of the bucket (empty) string if none
		String password = "";

		// // Connect to the Cluster
		// CouchbaseClient client = new CouchbaseClient(hosts, bucket,
		// password);
		// // D:\work\apache-maven-3.1.1\bin
		// Gson gson = new Gson();
		//
		// User user1 = new User("John", "Doe");
		// User user2 = new User("Matt", "Ingenthron");
		// User user3 = new User("Michael", "Nitschinger");
		//
		// client.set("user1", gson.toJson(user1)).get();
		// client.set("user2", gson.toJson(user2)).get();
		// client.set("user3", gson.toJson(user3)).get();

		// String designDoc = "users";
		// String viewName = "by_firstname";
		// View view = client.getView(designDoc, viewName);
		//
		// // 2: Create a Query object to customize the Query
		// Query query = new Query();
		// query.setIncludeDocs(true); // Include the full document body
		//
		// // 3: Actually Query the View and return the results
		// ViewResponse response = client.query(view, query);
		//
		// // 4: Iterate over the Data and print out the full document
		// for (ViewRow row : response) {
		// System.out.println(row.getDocument());
		// }
	}

	private static void testCouchBase() throws Exception {

		// (Subset) of nodes in the cluster to establish a connection
		List<URI> hosts = Arrays.asList(new URI("http://localhost:8091/pools"));

		// Name of the Bucket to connect to
		String bucket = "default";

		// Password of the bucket (empty) string if none
		String password = "";

		// Connect to the Cluster
		CouchbaseClient client = new CouchbaseClient(hosts, bucket, password);

		long time = System.currentTimeMillis();

		String designDoc = "users";
		String viewName = "by_firstname";
		View view = client.getView(designDoc, viewName);

		// 2: Create a Query object to customize the Query
		Query query = new Query();
		query.setIncludeDocs(true); // Include the full document body

		// 3: Actually Query the View and return the results
		ViewResponse response = client.query(view, query);

		for (ViewRow row : response) {
			System.out.println(row.getDocument());
		}

		client.shutdown();
	}

	public class RobotUser extends Thread {

		private int index;

		public RobotUser(int index) {
			this.index = index;
		}

		@Override
		public void run() {
			TestSocket socket = new TestSocket();
			createUser(socket);
			loopRequest(socket);
		}

		private void loopRequest(TestSocket socket) {
			UserService s = getService(UserService.class);
			while (true) {
				s.getData();
				Util.Thread.sleep(3000); // 每隔3秒 请求一次用户数据
			}
		}

		private void createUser(TestSocket socket) {

			String id = userId + "_" + index;
			InitService s = getService(InitService.class);

			s.init(socket);
			s.access(userId, "", 1, "", 1, 1, ""); // 接入游戏服务器
			s.getRandomNick(); // 随机生成昵称
			s.createUser(300006, ""); // 创建角色
		}
	}

	public static class A {
		int a;
		int b;
		int c;

		// public int getA() {
		// System.out.println("a");
		// return a;
		// }
		public int getB() {
			System.out.println("b");
			return b;
		}

		public int getC() {
			System.out.println("c");
			return c;
		}

		public void setA(int a) {
			this.a = a;
		}

		public void setB(int b) {
			this.b = b;
		}

		public void setC(int c) {
			this.c = c;
		}

	}

	private static Socket socket;
	private String userId;
	private static AA aa;

	/**
	 * 增加止血草和还魂丹
	 */
	@Test
	public final void addProp() {
		Bag<Grid> bag = user.getBag();
		bag.addProp(130003, 100);
		bag.addProp(130004, 100);
		// Debuger.debug("TestAll.addProp() 添加道具成功");
	}

	/**
	 * 所有战士恢复满血
	 */
	@Test
	public final void hpFull() {
		Collection<Hero> all = user.getTeam().getAll();
		for (Hero hero : all) {
			hero.addHp(100000);
			hero.commit();
		}
	}

	/**
	 * 无限战斗测试
	 */
	@Test
	public final void loopFighting() {
		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			fighting();
			Debuger.debug("TestAll.loopFighting() 当前战斗次数--------------------------------------------------"
					+ i);
		}
	}

	/**
	 * 战斗测试
	 */
	@Test
	public final void fighting() {
		// BattleService s = getService(BattleService.class);
		// s.fightingTest();

//		PvpService s = getService(PvpService.class);
//		PvpUsersPro us = s.getRandomUser();
//		List<PvpUser> ls = us.getUsersList();
//		PvpUser pv = ls.get(0);
//		String id = pv.getUser().getUserId();
//
//		s.fighting(id);
	}

	/**
	 * readJson
	 */
	@Test
	public final void readJson() {
		String content = Util.File.getContent("res/json.json");
		Debuger.debug("TestAll.readJson()" + content);

		JSONObject parseObject = JSON.parseObject(content);
	}

	/**
	 * readJson
	 */
	@Test
	public final void readJson2() {
		JSON.toJSON(user.getChuangZhenPlayer());
	}

	@Test
	public final void testCreate() {

		userId = Util.Random.getRandomString(8);
		userId = "u" + userId;

		for (int i = 0; i < 1000; i++) {
			new RobotUser(i).start(); // 新建一个用户 开始随机测试
			Util.Thread.sleep(300);
		}

	}

	public static void testMapDB() {

		// configure and open database using builder pattern.
		// all options are available with code auto-completion.
		DB db = DBMaker.newFileDB(new File("db/testdb")).closeOnJvmShutdown()
		// .encryptionEnable("game_ttafs")
				.make();

		// open existing an collection (or create new)
		ConcurrentNavigableMap<Integer, String> map = db
				.getTreeMap("HelloWorldMapDB");

		for (int i = 0; i < 10000; i++) {
			map.put(i, "xxx");
		}

		// map.put(1, "one");
		// map.put(2, "two");
		// // map.keySet() is now [1,2]

		db.commit(); // persist changes into disk

		// map.put(3, "three");
		// map.keySet() is now [1,2,3]
		// db.rollback(); //revert recent changes
		// map.keySet() is now [1,2]

		db.close();
	}

	private static void test1() {
		A a = new A();
		String s = JSON.toJSONString(a);
		System.out.println(s);
	}

	static class User {
		private final String firstname;
		private final String lastname;

		public User(String firstname, String lastname) {
			this.firstname = firstname;
			this.lastname = lastname;
		}

		public String getFirstname() {
			return firstname;
		}

		public String getLastname() {
			return lastname;
		}
	}

	static class AA {
		private static Lock l1 = new ReentrantLock();
		static long l;
		static AtomicLong l2 = new AtomicLong(0);

		void add() {

			try {
				if (l1.tryLock(1, TimeUnit.SECONDS)) {
					try {
						l += 1;
						l2.addAndGet(1);
						Util.Thread.sleep(100);
						l1.unlock();

					} finally {
						l1.unlock();
					}
				} else {
					throw new NotInitOverException();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		@Override
		public String toString() {
			return l + "," + l2;
		}
	}

	/**
	 * 锁测试
	 */
	private static void testLock() {

		final Lock l1 = new ReentrantLock();

		new Thread() {
			public void run() {
				l1.tryLock();
				System.out.println("start1");
				Util.Thread.sleep(2000);
				l1.unlock();
				System.out.println("end1");
			};
		}.start();

		new Thread() {
			public void run() {
				Util.Thread.sleep(100);
				l1.tryLock();
				System.out.println("start2");
				Util.Thread.sleep(1000);
				l1.unlock();
				System.out.println("end2");
			};
		}.start();

	}

	private static class L extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				aa.add();
			}
		}

	}

	@Test
	public void testMongoDB2() {
		// ConnectionManager.begin();
		City city = getCity("lc100");
		UserCounter uc = city.getUserCounter();

		CounterKey id = CounterKey.DOGZ_FEED_TIMES;
		System.out.println(uc.get(id));

		for (int i = 0; i < 1000; i++) {

			uc.add(id, 1);
			uc.get(id);
			// System.out.println(uc.get(id));

			uc.clear(id);
			// System.out.println(uc.get(id));

			uc.mark(id);
			// System.out.println(uc.isMark(id));

			uc.set(id, 10);
			// System.out.println(uc.get(id));

			uc.clear(id);
			// System.out.println(uc.get(id));

			uc.set(id, 11);
			// System.out.println(uc.get(id));
		}
	}

	private static void testMongoDB() throws UnknownHostException {

		MongoClient mc = new MongoClient();
		com.mongodb.DB db = mc.getDB("test");
		DBCollection c = db.getCollection("test");

		// c.drop();

		// for (int i = 0; i < 1000000; i++) {
		// // BasicDBObject o = new BasicDBObject();
		// // o.put("mb-first-document", i + "Hello 000000000000000000000!");
		// // o.put("_id", i );
		// // o.put("count", 2);
		// BasicDBObject b = new BasicDBObject();
		// b.put("name", "lk" + i);
		// b.put("age", 1);
		// b.put("city", 1);
		// b.put("aa", 1);
		// b.put("ab", 1);
		// b.put("ac", 1);
		// b.put("ad", 1);
		// c.insert(b);
		// }

		c.ensureIndex("name");
		// c.dropIndex("name");

		DBCursor find = c.find(new BasicDBObject("name", "lc1"));

		while (find.hasNext()) {
			DBObject n = find.next();
			System.out.println(n);
		}

		// System.out.println(c.findOne());

		// c.findOne(o);

	}

}
