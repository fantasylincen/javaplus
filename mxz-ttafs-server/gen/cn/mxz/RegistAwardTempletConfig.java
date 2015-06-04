//[奇遇][vip]签到奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RegistAwardTempletConfig {	private static Map<Integer, RegistAwardTemplet> map;	private static List<Integer> keys;	private static List<RegistAwardTemplet> all;	static {		load();	}	public static List<RegistAwardTemplet> getAll() {		return new ArrayList<RegistAwardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RegistAwardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RegistAwardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RegistAwardTempletConfig.map = map;		RegistAwardTempletConfig.keys = keys;																List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RegistAwardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RegistAwardTemplet> map) {		RegistAwardTemplet x = new RegistAwardTemplet();		x.setLv( Integer.decode( e.attributeValue("lv").trim() ) );		x.setDay( Integer.decode( e.attributeValue("day").trim() ) );		x.setTotalDay( Integer.decode( e.attributeValue("totalDay").trim() ) );		x.setAward( Integer.decode( e.attributeValue("award").trim() ) );		x.setFrontFirst( Integer.decode( e.attributeValue("frontFirst").trim() ) );		x.setFrontFirstFixed( Integer.decode( e.attributeValue("frontFirstFixed").trim() ) );		x.setFrontMiddle( Integer.decode( e.attributeValue("frontMiddle").trim() ) );		x.setFrontMiddleFixed( Integer.decode( e.attributeValue("frontMiddleFixed").trim() ) );		x.setFrontTail( Integer.decode( e.attributeValue("frontTail").trim() ) );		x.setFrontTailFixed( Integer.decode( e.attributeValue("frontTailFixed").trim() ) );		RegistAwardTemplet remove = map.put(x.getLv(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RegistAwardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RegistAwardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RegistAwardTemplet getMin() {		return get(getMinKey());	}	public static List<RegistAwardTemplet> findByLv(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByDay(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByTotalDay(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getTotalDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByAward(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontFirst(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontFirstFixed(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontFirstFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontMiddle(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontMiddle(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontMiddleFixed(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontMiddleFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontTail(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontTail(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RegistAwardTemplet> findByFrontTailFixed(int value) {
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontTailFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getLv();
		}
		return all;
	}
	public static int[] getArrayByDay() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}
	public static int[] getArrayByTotalDay() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getTotalDay();
		}
		return all;
	}
	public static int[] getArrayByAward() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}
	public static int[] getArrayByFrontFirst() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirst();
		}
		return all;
	}
	public static int[] getArrayByFrontFirstFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirstFixed();
		}
		return all;
	}
	public static int[] getArrayByFrontMiddle() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddle();
		}
		return all;
	}
	public static int[] getArrayByFrontMiddleFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddleFixed();
		}
		return all;
	}
	public static int[] getArrayByFrontTail() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontTail();
		}
		return all;
	}
	public static int[] getArrayByFrontTailFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontTailFixed();
		}
		return all;
	}
	public static List<Integer> getListByLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getLv());
		}
		return all;
	}
	public static List<Integer> getListByDay() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}
	public static List<Integer> getListByTotalDay() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getTotalDay());
		}
		return all;
	}
	public static List<Integer> getListByAward() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}
	public static List<Integer> getListByFrontFirst() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontFirst());
		}
		return all;
	}
	public static List<Integer> getListByFrontFirstFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontFirstFixed());
		}
		return all;
	}
	public static List<Integer> getListByFrontMiddle() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontMiddle());
		}
		return all;
	}
	public static List<Integer> getListByFrontMiddleFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontMiddleFixed());
		}
		return all;
	}
	public static List<Integer> getListByFrontTail() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontTail());
		}
		return all;
	}
	public static List<Integer> getListByFrontTailFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontTailFixed());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}