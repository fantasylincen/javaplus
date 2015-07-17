package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.random.Fetcher;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.share.CacheManager;
import cn.vgame.share.Xml;

public class TurntableUtil {

	static Map<String, String> translate = createTranslate();

	/**
	 * 翻译成中文
	 */
	public static final String toChinese(String type) {
		return translate.get(type);
	}

	private static Map<String, String> createTranslate() {
		HashMap<String, String> m = Maps.newHashMap();
		m.put("A", "禽");
		m.put("B", "银");
		m.put("C", "金");
		m.put("D", "兽");
		m.put("E", "燕");
		m.put("F", "鸽");
		m.put("G", "孔");
		m.put("H", "鹰");
		m.put("I", "狮");
		m.put("J", "熊");
		m.put("K", "猴");
		m.put("L", "兔");
		return m;
	}

	public static class SwitchsTemp implements ISwitchs {

		Counter<String> allCounts = new Counter<String>();

		@Override
		public String toString() {
			return TurntableUtil.toString(this);
		}

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

		@Override
		public String toString() {
			return TurntableUtil.toString(this);
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
		CacheManager.put(key, ls);
		return ls;
	}

	public static String toString(ISwitchs s) {
		List<String> types = getAllTypes();
		TypeF fetcher = new TypeF(s);
		return "[" + linkWith(" ", types, fetcher) + "]";
	}

	private static String linkWith(String sp, List<String> types,
			TypeF fetcher) {

		StringBuilder sb = new StringBuilder();
		Iterator<String> it = types.iterator();
		while (it.hasNext()) {
			String s = it.next();
			String str = fetcher.get(s);
			if(str == null)
				continue;
			sb.append(str);
			if (it.hasNext()) {
				sb.append(sp);
			}
		}
		return sb + "";
	}

	public static class TypeF implements Fetcher<String, String> {
	
		private final ISwitchs s;
	
		public TypeF(ISwitchs s) {
			this.s = s;
		}
	
		@Override
		public String get(String t) {
	
			int count = TurntableUtil.getByType(s, t);
			if(count == 0) {
				return null;
			}
			return toChinese(t) + ":" + count;
		}
	
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
		if (s1 == null)
			s1 = new EmptySwitch();
		if (s2 == null)
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
