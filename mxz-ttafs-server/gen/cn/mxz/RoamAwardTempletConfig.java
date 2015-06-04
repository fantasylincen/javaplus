//[奇遇]25[云游仙人]奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RoamAwardTempletConfig {	private static Map<Integer, RoamAwardTemplet> map;	private static List<Integer> keys;	private static List<RoamAwardTemplet> all;	static {		load();	}	public static List<RoamAwardTemplet> getAll() {		return new ArrayList<RoamAwardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RoamAwardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RoamAwardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RoamAwardTempletConfig.map = map;		RoamAwardTempletConfig.keys = keys;																List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RoamAwardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RoamAwardTemplet> map) {		RoamAwardTemplet x = new RoamAwardTemplet();		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setTime( Integer.decode( e.attributeValue("time").trim() ) );		x.setExp( Integer.decode( e.attributeValue("exp").trim() ) );		x.setWine( Integer.decode( e.attributeValue("wine").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		RoamAwardTemplet remove = map.put(x.getStep(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RoamAwardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RoamAwardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RoamAwardTemplet getMin() {		return get(getMinKey());	}	public static List<RoamAwardTemplet> findByStep(int value) {
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamAwardTemplet> findByTime(int value) {
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamAwardTemplet> findByExp(int value) {
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamAwardTemplet> findByWine(int value) {
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getWine(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamAwardTemplet> findByWeight(int value) {
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByTime() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}
	public static int[] getArrayByExp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getExp();
		}
		return all;
	}
	public static int[] getArrayByWine() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getWine();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByTime() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}
	public static List<Integer> getListByExp() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getExp());
		}
		return all;
	}
	public static List<Integer> getListByWine() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getWine());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}