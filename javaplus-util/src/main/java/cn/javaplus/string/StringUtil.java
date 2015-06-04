package cn.javaplus.string;
//package cn.javaplus.common.util.string;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 字符串工具
// * @author 	林岑
// * @since	2012年3月23日 14:49:03
// */
//public class StringUtil {
//
//	/**
//	 *
//	 * 将以逗号分隔的字符串equipments的第index个值修改为 value<br>
//	 * <br>
//	 * 例如: 456564,649871,69871,6464,645  index = 3, value = xxxxxx时, 修改后就得到  456564,649871,69871,xxxxxx,645<br>
//	 *  <br>
//	 *  如果传入的字符穿当中包含了-1, 那么就自动将-1置为空<br>
//	 *  例如: "-1, 123, -1"  alter 1, "xx" ----> ",xx,"
//	 * @param equipments	待修改的字符串
//	 * @param index			修改第index个逗号 和 index + 1 个逗号所夹的字符串
//	 * @param value			修改值
//	 */
//	public static String alter(String equipments, int index, String value) {
//
//		equipments = equipments.replaceAll(",,", ",-1,");
//		equipments = equipments.replace(" ", "");//消除字符串中所有空格
//		if(equipments.endsWith(",")) {
//			equipments = equipments + "-1";
//		}
//		if(equipments.startsWith(",")) {
//			equipments = "-1" + equipments;
//		}
//
//
//		String[] equips = equipments.split(",");
//
//		equips[index] = value;
//
//		StringBuffer stringLink = new StringBuffer("");
//		stringLink.append(equips[0].equals("-1") ? "" : equips[0]);
//		for (int i = 1; i < equips.length; i++) {
//			stringLink.append("," + (equips[i].equals("-1") ? "" : equips[i]));
//		}
//
//		return stringLink.toString();
//	}
//
//
//	/*
//	 * 读取字符串, 从data的第i个字节开始读取length个长度
//	 */
//	public static final String subString(byte[] data, int i, int length) {
//		return new String(Arrays.copyOfRange(data, i, length + i));
//	}
//	/**
//	 * 将ID列表转成字符串形式, 以逗号分隔
//	 * @param idList
//	 * @return
//	 */
//	public static String toStringSplitByComma(List<Integer> idList) {
//		if(idList == null || idList.size() == 0) {
//			return "";
//		}
//		String temp = "";
//		for (int i = 0; i < idList.size(); i++) {
//			Integer id = idList.get( i );
//			if(i == 0) {
//				temp += id;
//			} else {
//				temp += "," + id;
//			}
//		}
//		return temp;
//	}
//
//	/**
//	 * 将第一个字符转换成大写
//	 * @param src
//	 * @return
//	 */
//	public static final String firstToUpperCase(String src) {
//		return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toUpperCase());
//	}
//
//
//	/**
//	 * 将小写的命名改为常量命名规则
//	 * @param beanName
//	 * @return
//	 */
//	public static String toConst(String beanName) {
//		List<String> split = splitByUpper(beanName);
//		String s = "";
//		for (String ss : split) {
//			s += "_" + ss.toUpperCase();
//		}
//		while(s.startsWith("_")) {
//			s = s.replaceFirst("_", "");
//		}
//		return s;
//	}
//
//
//	/**
//	 * 所有大写字母的索引位置
//	 * @param beanName
//	 * @return
//	 */
//	private static List<String> splitByUpper(String beanName) {
//		List<Integer> ls = new ArrayList<Integer>();
//		ls.add(0);
//
//		ls.addAll(getUppers(beanName));
//		ls.add(beanName.length());
//
//		List<String> l = new ArrayList<String>();
//
//		for (int i = 0; i < ls.size() - 1; i++) {
//			Integer a = ls.get(i);
//			Integer b = ls.get(i + 1);
//			l.add(beanName.substring(a, b));
//		}
//		return l;
//
//	}
//
//
//	private static List<Integer> getUppers(String beanName) {
//		List<Integer> ls = new ArrayList<Integer>();
//		for (int i = 0; i < beanName.length(); i++) {
//			char c = beanName.charAt(i);
//			if(Character.isUpperCase(c)) {
//				ls.add(i);
//			}
//		}
//		return ls;
//	}
//
//
//
//		/**
//		 * 将字符串的首字母大写，一般用于将表名转换为类名
//		 *
//		 * @param tableName	用下划线连接的全小写字段名, 例如"aaa_bbb_ccc"
//		 *            表名
//		 * @return 类名	"AaaBbbCcc"
//		 */
//		public static String generateClassName(String tableName) {
//			return xxx(tableName).replaceFirst(tableName.substring(0, 1), tableName.substring(0, 1).toUpperCase());
//		}
//
//		public static String xxx(String columnName) {
//			String[] labels = columnName.split("_");
//			String r = labels[0];
//			for (int i = 1; i < labels.length; i++)
//				r = r + generateClassName(labels[i]);
//			return r;
//		}
//
//
//		/**
//		 * 将类名转换为对象 参数名，即第一个字母小写，如：User - user
//		 *
//		 * @param tableName
//		 *            表名
//		 * @return 参数名
//		 */
//		public static String generateParameterName(String tableName) {
//
//			String name = hump(tableName);
//
//			if(name.length() > 7) {
//				StringBuilder sb = new StringBuilder();
//
//				char[] chars = name.toCharArray();
//
//				for (int i = 0; i < chars.length; i++) {
//					char c = chars[i];
//					if(i == 0 || Character.isUpperCase(c)) {
//						sb.append(Character.toLowerCase(c));
//					}
//				}
//				return sb.toString() + "o";
//			} else {
//				return name + "o";
//			}
//		}
//
//		/**
//		 * 将普通字符串, 包含下划线的, 转换成驼峰标识 的字符串
//		 * @param srcText
//		 * @return
//		 */
//		public static String hump(String srcText) {
//
//			//把下划线之后的一个字符都变为大写
//			StringBuilder sb = new StringBuilder();
//			char[] chars = srcText.toCharArray();
//
//			for (int i = 0; i < chars.length; i++) {
//				if(i != 0 && chars[i - 1] == '_' ) {
//					sb.append(Character.toUpperCase(chars[i]));
//				} else {
//					sb.append(Character.toLowerCase(chars[i]));
//				}
//			}
//
//			String text = sb.toString().replace("_", "");
//			return text;
//		}
//
//		/**
//		 * 补起字符串, 用空格补齐, 保证返回值长度大于等于weigth
//		 * @param k
//		 * @param weigth
//		 * @return
//		 */
//		public static String polishing(String k, int weigth) {
//
//			return k + build(weigth - k.length());
//		}
//
//		private static String build(int i) {
//
//			StringBuilder sb = new StringBuilder();
//
//			for (int j = 0; j < i; j++) {
//
//				sb.append(" ");
//			}
//
//			return sb + "";
//		}
//
//
//		public static String firstToLowerCase(String src) {
//			return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toLowerCase());
//		}
//
//
//		/**
//		 * 下划线后的一个字符转为大写, 同时将下划线删除
//		 * @param f
//		 * @return
//		 */
//		public static String parseAfter_ToUpperCase(String f) {
//			//把下划线之后的一个字符都变为大写
//			StringBuilder sb = new StringBuilder();
//			char[] chars = f.toCharArray();
//
//			for (int i = 0; i < chars.length; i++) {
//				if(i != 0 && chars[i - 1] == '_' ) {
//					sb.append(Character.toUpperCase(chars[i]));
//				} else {
//					sb.append(chars[i]);
//				}
//			}
//
//			String text = sb.toString().replace("_", "");
//			return text;
//		}
//	}
