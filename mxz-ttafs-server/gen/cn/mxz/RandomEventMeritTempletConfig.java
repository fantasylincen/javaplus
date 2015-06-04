//[奇遇]15[关卡][神魔]功德奖励表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomEventMeritTempletConfig {	private static Map<Integer, RandomEventMeritTemplet> map;	private static List<Integer> keys;	private static List<RandomEventMeritTemplet> all;	static {		load();	}	public static List<RandomEventMeritTemplet> getAll() {		return new ArrayList<RandomEventMeritTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventMeritConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomEventMeritTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomEventMeritTempletConfig.map = map;		RandomEventMeritTempletConfig.keys = keys;																List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomEventMeritTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomEventMeritTemplet> map) {		RandomEventMeritTemplet x = new RandomEventMeritTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setMerit( Integer.decode( e.attributeValue("merit").trim() ) );		x.setAwards( e.attributeValue("awards") );		RandomEventMeritTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventMeritTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventMeritTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventMeritTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventMeritTemplet> findById(int value) {
		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventMeritTemplet> findByMerit(int value) {
		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getMerit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventMeritTemplet> findByAwards(String value) {
		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByMerit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getMerit();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByMerit() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getMerit());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}