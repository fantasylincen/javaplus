//[道具]金宝箱特殊开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GoldBoxTempletConfig {	private static Map<Integer, GoldBoxTemplet> map;	private static List<Integer> keys;	private static List<GoldBoxTemplet> all;	static {		load();	}	public static List<GoldBoxTemplet> getAll() {		return new ArrayList<GoldBoxTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GoldBoxConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, GoldBoxTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																GoldBoxTempletConfig.map = map;		GoldBoxTempletConfig.keys = keys;																List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		GoldBoxTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, GoldBoxTemplet> map) {		GoldBoxTemplet x = new GoldBoxTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNumber( e.attributeValue("number") );		x.setWeight1( Integer.decode( e.attributeValue("weight1").trim() ) );		x.setWeight2( Integer.decode( e.attributeValue("weight2").trim() ) );		x.setStop( Integer.decode( e.attributeValue("stop").trim() ) );		x.setAwards10( e.attributeValue("awards10") );		x.setAwards11( e.attributeValue("awards11") );		GoldBoxTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GoldBoxTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GoldBoxTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GoldBoxTemplet getMin() {		return get(getMinKey());	}	public static List<GoldBoxTemplet> findById(int value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByNumber(String value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByWeight1(int value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByWeight2(int value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByStop(int value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getStop(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByAwards10(String value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards10(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GoldBoxTemplet> findByAwards11(String value) {
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards11(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByNumber() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByWeight1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight1();
		}
		return all;
	}
	public static int[] getArrayByWeight2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight2();
		}
		return all;
	}
	public static int[] getArrayByStop() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getStop();
		}
		return all;
	}
	public static String[] getArrayByAwards10() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards10();
		}
		return all;
	}
	public static String[] getArrayByAwards11() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards11();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByNumber() {
		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByWeight1() {
		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getWeight1());
		}
		return all;
	}
	public static List<Integer> getListByWeight2() {
		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getWeight2());
		}
		return all;
	}
	public static List<Integer> getListByStop() {
		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getStop());
		}
		return all;
	}
	public static List<String> getListByAwards10() {
		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getAwards10());
		}
		return all;
	}
	public static List<String> getListByAwards11() {
		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getAwards11());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}