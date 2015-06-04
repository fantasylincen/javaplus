//package cn.mxz.regist;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import cn.javaplus.common.util.Util.Random;
//import cn.mxz.RegistRewardLibraryTempletConfig;
//import cn.mxz.util.db.KeyValueCollection;
//import cn.mxz.util.db.KeyValueCollectionFactory;
//
//class RegisterObjects {
//
//	static RegisterManager getRegisterManager() {
//		KeyValueCollection kv = KeyValueCollectionFactory.getMySqlCollection();
//		String key = "RegisterManager:Rewards";
//		String v = kv.get(key);
//
//		if(v == null) {
//			v = init();
//			kv.put(key, v);
//		}
//
//		RegisterManagerImpl r = new RegisterManagerImpl(v);
//
//		if(r.isTimeOut()) {
//			v = init();
//			kv.put(key, v);
//			r = new RegisterManagerImpl(v);
//		}
//
//		return r;
//	}
//
//	private static String init() {
//
//		List<Integer> days = randomDays();	//随机某几天出现奖励
//
//		int day = Util.Time.getDaysOfMonth();//本月有几天
//
//		StringBuilder sb = new StringBuilder(System.currentTimeMillis() + ":");
//
//		for (int d = 1; d <= day; d++) {
//
//			boolean hasReward = days.contains(d);
//
//			int id = hasReward ? randomRewardId() : -1;
//
//			boolean isLast = d == day;
//
//			if(!isLast) {
//
//				sb.append(id + ",");
//
//			} else {
//
//				sb.append(id + "");
//			}
//		}
//
//		return sb.toString();
//	}
//
//
//	/**
//	 * 随机一个奖励ID出来
//	 *
//	 * @return
//	 */
//	private static int randomRewardId() {
//		int[] ws = RegistRewardLibraryTempletConfig.getArrayByWeight();
//		int index = Random.getRandomIndex(ws);
//		return RegistRewardLibraryTempletConfig.getKeys().get(index);
//	}
//
//	private static List<Integer> randomDays() {
//		int count = Random.get(23, 26);
//		int day = Util.Time.getDaysOfMonth();// 本月有几天
//		List<Integer> ls = new ArrayList<Integer>();
//		for (int d = 1; d < day; d++) {
//			ls.add(d);
//		}
//		cn.javaplus.common.util.Util.Collection.upset(ls);
//		return new ArrayList<Integer>(ls.subList(0, count));
//	}
//}
