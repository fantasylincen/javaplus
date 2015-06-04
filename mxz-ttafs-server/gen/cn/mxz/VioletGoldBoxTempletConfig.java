//[道具]紫金宝箱特殊开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VioletGoldBoxTempletConfig {	private static Map<Integer, VioletGoldBoxTemplet> map;	private static List<Integer> keys;	private static List<VioletGoldBoxTemplet> all;	static {		load();	}	public static List<VioletGoldBoxTemplet> getAll() {		return new ArrayList<VioletGoldBoxTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VioletGoldBoxConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, VioletGoldBoxTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																VioletGoldBoxTempletConfig.map = map;		VioletGoldBoxTempletConfig.keys = keys;																List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		VioletGoldBoxTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, VioletGoldBoxTemplet> map) {		VioletGoldBoxTemplet x = new VioletGoldBoxTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNumber( e.attributeValue("number") );		x.setWeight1( Integer.decode( e.attributeValue("weight1").trim() ) );		x.setWeight2( Integer.decode( e.attributeValue("weight2").trim() ) );		x.setAwards10( e.attributeValue("awards10") );		x.setStop( Integer.decode( e.attributeValue("stop").trim() ) );		x.setAwards11( e.attributeValue("awards11") );		VioletGoldBoxTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VioletGoldBoxTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VioletGoldBoxTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VioletGoldBoxTemplet getMin() {		return get(getMinKey());	}	public static List<VioletGoldBoxTemplet> findById(int value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByNumber(String value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByWeight1(int value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByWeight2(int value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByAwards10(String value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards10(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByStop(int value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getStop(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VioletGoldBoxTemplet> findByAwards11(String value) {
		List<VioletGoldBoxTemplet> all = new ArrayList<VioletGoldBoxTemplet>();
		for (VioletGoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards11(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByNumber() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByWeight1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight1();
		}
		return all;
	}
	public static int[] getArrayByWeight2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight2();
		}
		return all;
	}
	public static String[] getArrayByAwards10() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards10();
		}
		return all;
	}
	public static int[] getArrayByStop() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getStop();
		}
		return all;
	}
	public static String[] getArrayByAwards11() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VioletGoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards11();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByNumber() {
		List<String> all = new ArrayList<String>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByWeight1() {
		List<Integer> all = new ArrayList<Integer>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getWeight1());
		}
		return all;
	}
	public static List<Integer> getListByWeight2() {
		List<Integer> all = new ArrayList<Integer>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getWeight2());
		}
		return all;
	}
	public static List<String> getListByAwards10() {
		List<String> all = new ArrayList<String>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getAwards10());
		}
		return all;
	}
	public static List<Integer> getListByStop() {
		List<Integer> all = new ArrayList<Integer>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getStop());
		}
		return all;
	}
	public static List<String> getListByAwards11() {
		List<String> all = new ArrayList<String>();
		for (VioletGoldBoxTemplet f : getAll()) {
			all.add(f.getAwards11());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}