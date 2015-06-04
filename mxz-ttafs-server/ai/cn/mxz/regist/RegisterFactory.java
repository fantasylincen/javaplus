//package cn.mxz.regist;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.user.City;
//import cn.mxz.util.db.KeyValueCollection;
//import cn.mxz.util.db.KeyValueCollectionFactory;
//
//public class RegisterFactory {
//
//	public static Register create(City c) {
//
//		KeyValueCollection kv = KeyValueCollectionFactory.getMySqlCollection();
//
//		String value = kv.get(key(c));
//
//
//		if(value == null) {
//
//			value = init();
//
//			kv.put(key(c), value);
//		}
//
//		RegisterImpl r = new RegisterImpl(c, value);
//
//		if(r.isTimeOut()) {	//如果已经过期了
//
//			value = init();
//
//			kv.put(key(c), value);
//
//			r = new RegisterImpl(c, value);
//		}
//
//		return r;
//	}
//
//	static Object key(City c) {
//		String key = c.getId() + ":regist";
//		return key;
//	}
//
//	/**
//	 * 生成签到表 初始化个人签到表, 初始化结果:   138812312311:0,1,0,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,0..   (时间:0或1逗号连接, 0表示没领取, 1表示已经领取)
//	 */
//	private static String init() {
//		int day = Util.Time.getDaysOfMonth();	// 本月有几天
//		StringBuilder sb = new StringBuilder();
//
//		sb.append(System.currentTimeMillis() + ":");
//
//		for (int d = 0; d < day; d++) {
//			sb.append("0");
//			if(d != day - 1) {	//不是最后一个
//				sb.append(",");
//			}
//		}
//		return sb.toString();
//	}
//}
