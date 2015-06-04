//package cn.mxz.fighter;
//
//import java.util.Collection;
//import java.util.Map;
//
//import cn.javaplus.util.Util;
//
//import com.google.common.collect.Maps;
//
//import define.D;
//
///**
// * 死亡时间获取器
// * 
// * @author 林岑
// * 
// */
//public class ReviseRemainFetcher {
//
//	private static ReviseRemainFetcher	instance;
//	private Map<Integer, Integer>		define;
//
//	private ReviseRemainFetcher() {
//		define = Maps.newHashMap();
//		String[] split = D.REVISE_TIME.split(",");
//		for (String string : split) {
//			if (!string.isEmpty()) {
//				String[] s = string.split(":");
//				define.put(new Integer(s[0]), new Integer(s[1]));
//			}
//		}
//	}
//
//	public static final ReviseRemainFetcher getInstance() {
//		if (instance == null) {
//			instance = new ReviseRemainFetcher();
//		}
//		return instance;
//	}
//
//	/**
//	 * 根据死亡次数, 获取复活所需剩余时间
//	 * 
//	 * @param dieCount
//	 * @return
//	 */
//	public int getRemainSec(int dieCount) {
//		Integer min = define.get(dieCount);
//		if (min == null) {
//			min = getMin();
//		}
//		return min * 60;
//	}
//
//	private Integer getMin() {
//		Collection<Integer> values = define.values();
//		return Util.Collection.getMin(values);
//	}
//}
