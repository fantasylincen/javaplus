//[礼包]礼包奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class PresentTempletConfig {	private static Map<Integer, PresentTemplet> map;	private static List<Integer> keys;	private static List<PresentTemplet> all;	static {		load();	}	public static List<PresentTemplet> getAll() {		return new ArrayList<PresentTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/PresentConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, PresentTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																PresentTempletConfig.map = map;		PresentTempletConfig.keys = keys;																List<PresentTemplet> all = new ArrayList<PresentTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		PresentTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, PresentTemplet> map) {		PresentTemplet x = new PresentTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setKey( e.attributeValue("key") );		x.setName( e.attributeValue("name") );		x.setAwards( e.attributeValue("awards") );		x.setExplain( e.attributeValue("explain") );		PresentTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static PresentTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static PresentTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static PresentTemplet getMin() {		return get(getMinKey());	}	public static List<PresentTemplet> findById(int value) {
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PresentTemplet> findByKey(String value) {
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getKey(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PresentTemplet> findByName(String value) {
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PresentTemplet> findByAwards(String value) {
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PresentTemplet> findByExplain(String value) {
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByKey() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getKey();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByKey() {
		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getKey());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}