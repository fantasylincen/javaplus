//[奇遇]15[关卡][神魔]神魔出现表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomEventRappelzTempletConfig {	private static Map<Integer, RandomEventRappelzTemplet> map;	private static List<Integer> keys;	private static List<RandomEventRappelzTemplet> all;	static {		load();	}	public static List<RandomEventRappelzTemplet> getAll() {		return new ArrayList<RandomEventRappelzTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventRappelzConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomEventRappelzTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomEventRappelzTempletConfig.map = map;		RandomEventRappelzTempletConfig.keys = keys;																List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomEventRappelzTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomEventRappelzTemplet> map) {		RandomEventRappelzTemplet x = new RandomEventRappelzTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setProtagonistLv( e.attributeValue("protagonistLv") );		x.setRappelzLv( e.attributeValue("rappelzLv") );		x.setRappelzId( e.attributeValue("rappelzId") );		RandomEventRappelzTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventRappelzTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventRappelzTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventRappelzTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventRappelzTemplet> findById(int value) {
		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventRappelzTemplet> findByProtagonistLv(String value) {
		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getProtagonistLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventRappelzTemplet> findByRappelzLv(String value) {
		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getRappelzLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventRappelzTemplet> findByRappelzId(String value) {
		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getRappelzId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByProtagonistLv() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getProtagonistLv();
		}
		return all;
	}
	public static String[] getArrayByRappelzLv() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getRappelzLv();
		}
		return all;
	}
	public static String[] getArrayByRappelzId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getRappelzId();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByProtagonistLv() {
		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getProtagonistLv());
		}
		return all;
	}
	public static List<String> getListByRappelzLv() {
		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getRappelzLv());
		}
		return all;
	}
	public static List<String> getListByRappelzId() {
		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getRappelzId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}