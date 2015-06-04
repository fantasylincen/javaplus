package cn.mxz.init;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.javaplus.util.Util;
import cn.mxz.RoleNameTemplet;
import cn.mxz.RoleNameTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.testbase.ServiceTestBase;
import cn.mxz.util.debuger.Debuger;

/**
 * 用户接入测试
 *
 * @author 林岑
 * @since 2013年7月4日 20:11:17
 */
public class InitServiceTest extends ServiceTestBase {


	@Test
	public void test() {

		Map<String, String> na = WorldFactory.getWorld().getNickManager().getNickAll();

		int count = 0;

		for (int i = 0; i < 100000; i++) {

			String nick = getNick();
			if (na.containsKey(nick)) {
				System.out.println("InitServiceTest.test() 昵称重复" + nick);
				count++;
			} else {
				System.out.println("InitServiceTest.test() 不重复");
			}
		}

		Debuger.debug("InitServiceTest.test() 总计:" + count);

	}

	private String getNick() {
		List<RoleNameTemplet> all = RoleNameTempletConfig.getAll();

		RoleNameTemplet r1 = Util.Random.getRandomOne(all);
		RoleNameTemplet r2 = Util.Random.getRandomOne(all);

		return r1.getFirst() + r2.getLast();
	}

	// private InitServiceImpl service;
	//
	// //
	// // private static final String id = "xf2";
	//
	// @Before
	// public void before() {
	// service = new InitServiceImpl();
	// }
	//
	// static int k;
	//
	// @Test
	// public void createNewUser() {
	//
	// // create("ta"); //ta1-ta30发给运营商了
	//
	// // create("a");
	// // create("mxz");
	// // create("lxy");
	// //
	// // create("xf");
	// // create("qw");
	// // create("zh");
	// // create("lc");
	// // create("ls");
	// // create("hbs");
	// // create("jjy");
	// // create("zk");
	// // create("dw");
	//
	// // create("hjl");
	// // create("fl");
	// // create("rcs");
	// // create("pc");
	// // create("hcl");
	//
	// Thread[] tt = new Thread[] {
	//
	// buildCreator("hjl"), buildCreator("zk"), buildCreator("dw"),
	// buildCreator("ls"), buildCreator("jjy"), buildCreator("hbs"),
	// buildCreator("zh"),
	//
	// };
	//
	// // for (int i = 0; i < 30; i++) {
	// //
	// // final int kk = i;
	// // tt[i] = new Thread(){
	// //
	// // public void run() {
	// //
	// // // DAO2<String, String, UserCounters> DAO =
	// // DaoFactory.getUserCountersDAO();
	// // //
	// // // for (int i = 3000; i < 10000; i++) {
	// // // UserCounters c = new UserCounters();
	// // // c.setCount(1);
	// // // String key = "test" + kk + ", " + i;
	// // // c.setCounterId(key);
	// // // c.setUname(key);
	// // // DAO.add(c);
	// // //
	// // // System.out.println("success:" + key + ", " + k++);
	// // // }
	// // };
	// //
	// // };
	// // }
	//
	// for (int i = 0; i < tt.length; i++) {
	//
	// Thread thread = tt[i];
	// tt[i].start();
	// try {
	// thread.join();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	//
	// private Thread buildCreator(final String string) {
	// return new Thread() {
	// @Override
	// public void run() {
	// create(string);
	// }
	// };
	// }
	//
	// private static int count = 0;
	//
	// private void create(String id) {
	// List<UserData> all = DaoFactory.getUserDataDAO().getAll();
	//
	// int max = findMax(all, id);
	//
	// for (int i = max + 1; i < max + 20; i++) {
	//
	// // try {
	// //
	// // createUser(id + i);
	// //
	// // } catch (Exception e1) {
	// //
	// // e1.printStackTrace();
	// // }
	//
	// try {
	//
	// TestSocket socket = new TestSocket();
	//
	// service.init(socket);
	//
	// service.access(id + i, "222222");
	//
	// service.setNick("nick" + id + i);
	//
	// service.setUserType(300001 + Util.R.nextInt(5));
	//
	// service.create("");
	//
	// System.out.println("总计创建数:" + count++);
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// private int findMax(List<UserData> all, String id) {
	// int max = 0;
	// for (UserData userData : all) {
	// String uname = userData.getUname();
	// if (uname.startsWith(id)) {
	// int i = new Integer(uname.replaceAll(id, ""));
	// if (i > max) {
	// max = i;
	// }
	// }
	// }
	// return max;
	// }
	//
	// @Test
	// public void createOne() {
	//
	// InitService service = (InitService) factory.get("initService");
	//
	// int randomInt = Random.get(105, 999);
	//
	// String id = "lc" + randomInt;
	//
	// DAO<String, UserData> DAO = DaoFactory.getUserDataDAO();
	//
	// while (DAO.get(id) != null) {
	//
	// randomInt = Random.get(105, 999);
	//
	// id = "lc" + randomInt;
	// }
	//
	// service.access(id, 222222 + "");
	//
	// service.setNick("x");
	//
	// service.setUserType(300004);
	//
	// long t1 = System.currentTimeMillis();
	//
	// service.create("");
	//
	// long t2 = System.currentTimeMillis() - t1;
	//
	// System.out.println(t2);
	// }
	//
	// // private void createUser(String id) {
	// //
	// // PreparedStatement ps = null;
	// //
	// // ResultSet rs = null;
	// //
	// // // TLogicServerManagement tlsmo = null;
	// //
	// // try {
	// //
	// // String sql =
	// // "insert into t_user (USER_ID,USER_PASSWORDK) values (?, ?);";
	// //
	// // ps = InitDB.getInstance().getPst(sql);
	// //
	// // ps.setString(1, id);
	// //
	// // String pwd = Util.getRandomInt(100000, 999999) + "";
	// //
	// // ps.setString(2, pwd);
	// //
	// // System.out.println(id + "      " + pwd);
	// //
	// // ps.executeUpdate();
	// //
	// // } catch (SQLException e) {
	// //
	// // e.printStackTrace();
	// // } finally {
	// //
	// // Closer.close(rs, ps);
	// // }
	// // }
	//
	// @Test
	// public void test3() {
	//
	// newThread();
	//
	// Util.Thread.sleep(100000000);
	// }
	//
	// private void newThread() {
	// new Thread() {
	// public void run() {
	// int i = 0;
	// while (true) {
	// try {
	//
	// create2(getRandomId());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// System.out.println("创建到第:" + i + "个玩家!");
	// i++;
	// }
	// };
	// }.start();
	// }
	//
	// private void create2(String id) {
	//
	// TestSocket socket = new TestSocket();
	//
	// service.init(socket);
	//
	// service.access(id, "222222");
	//
	// service.setNick("nick_" + id);
	//
	// service.setUserType(300001 + Util.R.nextInt(5));
	//
	// service.create("");
	// socket.close();
	// }
	//
	// private String getRandomId() {
	// return "tester" + Util.Random.get(100000000, 999999999);
	// }
	//
	// @Test
	// public void testCounter() {
	//
	// DAO<String, KeyValue> DAO = DaoFactory.getKeyValueDAO();
	//
	// for (int i = 0; i < 10000; i++) {
	//
	// KeyValue k = new KeyValueImpl();
	// k.setK("key " + i);
	// k.setV("xxxxxxxxxxxxxxxxx");
	//
	// DAO.add(k);
	//
	// System.out.println(i);
	// }
	//
	// // DAO2<String, String, UserCounters> DAO =
	// // DaoFactory.getUserCountersDAO();
	// //
	// // List<UserCounters> ls = Lists.newArrayList();
	// //
	// //
	// // long t1 = System.nanoTime();
	// //
	// // for (int i = 6300; i < 6910; i++) {
	// //
	// // UserCounters o = new UserCounters();
	// // o.setCount(1);
	// // o.setUname("lincentest");
	// // o.setCounterId(i + "lincentest");
	// //
	// // ls.add(o);
	// // // DAO.add(o);
	// // }
	// //
	// // DAO.addAll(ls);
	// // System.out.println("耗时:" + (System.nanoTime() - t1) / 1000000000f);
	// }
	//
	// @Test
	// public void test6() {
	// System.err.println("如果该程序在每个服务器上面运行过了, 就可以删除了");
	// System.err.println("如果该程序在每个服务器上面运行过了, 就可以删除了");
	// System.err.println("如果该程序在每个服务器上面运行过了, 就可以删除了");
	//
	// DAO<String, UserData> DAO = DaoFactory.getUserDataDAO();
	// cn.javaplus.common.db.DAO<String, UserBase> BDAO =
	// DaoFactory.getUserBaseDAO();
	//
	// List<UserData> all = DAO.getAll();
	// Debuger.debug("TestOnServerStart.onEvent() 正在搬迁数据表....");
	// int i = 0;
	// for (UserData base : all) {
	// UserBase b = BDAO.get(base.getUname());
	// if (b == null) {
	// b = new UserBaseImpl();
	// b.setNick(base.getNick());
	// b.setUname(base.getUname());
	// BDAO.add(b);
	// if (i % 1000 == 0) {
	// Debuger.debug("TestOnServerStart.onEvent() 进度:" + i * 1000);
	// }
	// }
	// i++;
	// }
	//
	// Debuger.debug("TestOnServerStart.onEvent() 搬迁完成");
	// }
	//
	//
	// @Test
	// public void test7() {
	//
	//
	// Runnable r = new Runnable() {
	//
	//
	// public void run() {
	// DAO<String, UserBase> DAO = DaoFactory.getUserBaseDAO();
	// int index2 = 20000;
	// while (true) {
	//
	// List<UserBase> ls = Lists.newArrayList();
	//
	// for (int i = 0; i < 1000; i++) {
	//
	// String randomId = getId(i, index2);
	// UserBase b = new UserBaseImpl();
	// b.setUname(randomId);
	// b.setNick("nick_" + randomId);
	// ls.add(b);
	// }
	// try {
	// DAO.addAll(ls);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// Debuger.debug("InitServiceTest.test7() 增加了1000 用户");
	// index2 ++;
	// }
	// }
	//
	// private String getId(int i, int index2) {
	// return "mc" + "_" + i + "_"+ index2;
	// }
	// };
	//
	//
	// r.run();
	// }
}
