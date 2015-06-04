//package cn.mxz.init;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 用户来源
// * @author 林岑
// *
// */
//public enum UserSource {
//
//	MXZ(1),
//	LINE_KONG(2);
//
//	private int										number;
//
//	private static final Map<Integer, UserSource>	numToEnum	= new HashMap<Integer, UserSource>();
//	static {
//		for (UserSource t : values()) {
//
//			UserSource s = numToEnum.put(t.number, t);
//			if (s != null) {
//				throw new RuntimeException(t.number + "重复了");
//			}
//		}
//	}
//
//	UserSource(int number) {
//		this.number = number;
//	}
//
//
//	public int toNum() {
//		return number;
//	}
//
//	public static UserSource fromNum(int n) {
//		return numToEnum.get(n);
//	}
//
//
//}
