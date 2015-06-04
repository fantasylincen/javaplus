package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.share.CacheManager;
import cn.vgame.share.Xml;

public class TurntableUtil {

	public static class SwitchsTemp implements ISwitchs {

		
		Counter<String> allCounts = new Counter<String>();

		/**
		 * A 2 飞禽
		 */
		public int getA() {
			return allCounts.get("A");
		}

		/**
		 * B 24 银鲨鱼
		 */
		public int getB() {
			return allCounts.get("B");
		}

		/**
		 * C 48 金鲨鱼
		 */
		public int getC() {
			return allCounts.get("C");
		}

		/**
		 * D 2 走兽
		 */
		public int getD() {
			return allCounts.get("D");
		}

		/**
		 * E 6 燕子
		 */
		public int getE() {
			return allCounts.get("E");
		}

		/**
		 * F 8 鸽子
		 */
		public int getF() {
			return allCounts.get("F");
		}

		/**
		 * G 8 孔雀
		 */
		public int getG() {
			return allCounts.get("G");
		}

		/**
		 * H 12 老鹰
		 */
		public int getH() {
			return allCounts.get("H");
		}

		/**
		 * I 12 狮子
		 */
		public int getI() {
			return allCounts.get("I");
		}

		/**
		 * J 8 熊猫
		 */
		public int getJ() {
			return allCounts.get("J");
		}

		/**
		 * K 8 猴子
		 */
		public int getK() {
			return allCounts.get("K");
		}

		/**
		 * L 6 兔子
		 */
		public int getL() {
			return allCounts.get("L");
		}

		public void add(String type, int byType) {
			allCounts.add(type, byType);
		}
}

	public static class EmptySwitch implements ISwitchs {

		@Override
		public int getA() {
			
			return 0;
		}

		@Override
		public int getB() {
			
			return 0;
		}

		@Override
		public int getC() {
			
			return 0;
		}

		@Override
		public int getD() {
			
			return 0;
		}

		@Override
		public int getE() {
			
			return 0;
		}

		@Override
		public int getF() {
			
			return 0;
		}

		@Override
		public int getG() {
			
			return 0;
		}

		@Override
		public int getH() {
			
			return 0;
		}

		@Override
		public int getI() {
			
			return 0;
		}

		@Override
		public int getJ() {
			
			return 0;
		}

		@Override
		public int getK() {
			
			return 0;
		}

		@Override
		public int getL() {
			
			return 0;
		}

	}

	/**
	 * 压了多少金币
	 * 
	 * @param s
	 */
	public static long getCountAll(ISwitchs s) {
		long count = 0;
		List<String> allTypes = getAllTypes();
		for (String type : allTypes) {
			count += getByType(s, type);
		}
		return count;
	}

	public static long getCountAllWithOutAAndD(ISwitchs s) {
		long count = 0;
		List<String> allTypes = getAllTypesWithOutAAndD();
		for (String type : allTypes) {
			count += getByType(s, type);
		}
		return count;
	}

	public static List<String> getAllTypesWithOutAAndD() {
		List<String> types = Lists.newArrayList(getTypes());
		types.remove("A");
		types.remove("D");
		return types;
	}
	

	public static List<String> getAllTypes() {
		return Lists.newArrayList(getTypes());
	}
	
	

	@SuppressWarnings("unchecked")
	private static List<String> getTypes() {
		String key = "ALL_TYPES";
		Object types = CacheManager.get(key);
		if (types != null)
			return (List<String>) types;

		Sheet sheet = Xml.getSheet("x");

		List<Row> a = sheet.getAll();
		ArrayList<String> ls = Lists.newArrayList();
		for (Row r : a) {
			String type = r.get("type");
			ls.add(type);
		}
		CacheManager.put(key, 30000, ls);
		return ls;
	}

	public static int getByType(ISwitchs s, String type) {

		if ("A".equals(type)) {
			return s.getA();
		}
		if ("B".equals(type)) {
			return s.getB();
		}
		if ("C".equals(type)) {
			return s.getC();
		}
		if ("D".equals(type)) {
			return s.getD();
		}
		if ("E".equals(type)) {
			return s.getE();
		}
		if ("F".equals(type)) {
			return s.getF();
		}
		if ("G".equals(type)) {
			return s.getG();
		}
		if ("H".equals(type)) {
			return s.getH();
		}
		if ("I".equals(type)) {
			return s.getI();
		}
		if ("J".equals(type)) {
			return s.getJ();
		}
		if ("K".equals(type)) {
			return s.getK();
		}
		if ("L".equals(type)) {
			return s.getL();
		}

		return 0;
	}

	public static ISwitchs add(ISwitchs s1, ISwitchs s2) {
		if(s1 == null)
			s1 = new EmptySwitch();
		if(s2 == null)
			s2 = new EmptySwitch();
		
		SwitchsTemp t = new SwitchsTemp();
		List<String> types = getTypes();
		for (String type : types) {
			t.add(type, getByType(s1, type));
			t.add(type, getByType(s2, type));
		}
		return t;
	}

}
