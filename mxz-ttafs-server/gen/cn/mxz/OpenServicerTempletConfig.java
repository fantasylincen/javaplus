//[奇遇]49[开服礼包]开服礼包package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class OpenServicerTempletConfig {	private static Map<Integer, OpenServicerTemplet> map;	private static List<Integer> keys;	private static List<OpenServicerTemplet> all;	static {		load();	}	public static List<OpenServicerTemplet> getAll() {		return new ArrayList<OpenServicerTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/OpenServicerConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, OpenServicerTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																OpenServicerTempletConfig.map = map;		OpenServicerTempletConfig.keys = keys;																List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		OpenServicerTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, OpenServicerTemplet> map) {		OpenServicerTemplet x = new OpenServicerTemplet();		x.setDay( Integer.decode( e.attributeValue("day").trim() ) );		x.setAward( e.attributeValue("award") );		x.setAwardLog( e.attributeValue("awardLog") );		OpenServicerTemplet remove = map.put(x.getDay(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static OpenServicerTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static OpenServicerTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static OpenServicerTemplet getMin() {		return get(getMinKey());	}	public static List<OpenServicerTemplet> findByDay(int value) {
		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<OpenServicerTemplet> findByAward(String value) {
		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<OpenServicerTemplet> findByAwardLog(String value) {
		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getAwardLog(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByDay() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}
	public static String[] getArrayByAward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}
	public static String[] getArrayByAwardLog() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getAwardLog();
		}
		return all;
	}
	public static List<Integer> getListByDay() {
		List<Integer> all = new ArrayList<Integer>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}
	public static List<String> getListByAward() {
		List<String> all = new ArrayList<String>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}
	public static List<String> getListByAwardLog() {
		List<String> all = new ArrayList<String>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getAwardLog());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}