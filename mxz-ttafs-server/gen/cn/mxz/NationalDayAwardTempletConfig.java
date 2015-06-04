//活动]国庆登陆奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class NationalDayAwardTempletConfig {	private static Map<Integer, NationalDayAwardTemplet> map;	private static List<Integer> keys;	private static List<NationalDayAwardTemplet> all;	static {		load();	}	public static List<NationalDayAwardTemplet> getAll() {		return new ArrayList<NationalDayAwardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/NationalDayAwardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, NationalDayAwardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																NationalDayAwardTempletConfig.map = map;		NationalDayAwardTempletConfig.keys = keys;																List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		NationalDayAwardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, NationalDayAwardTemplet> map) {		NationalDayAwardTemplet x = new NationalDayAwardTemplet();		x.setDay( Integer.decode( e.attributeValue("day").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setLog( e.attributeValue("log") );		NationalDayAwardTemplet remove = map.put(x.getDay(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static NationalDayAwardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static NationalDayAwardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static NationalDayAwardTemplet getMin() {		return get(getMinKey());	}	public static List<NationalDayAwardTemplet> findByDay(int value) {
		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NationalDayAwardTemplet> findByAwards(String value) {
		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NationalDayAwardTemplet> findByLog(String value) {
		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getLog(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByDay() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByLog() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getLog();
		}
		return all;
	}
	public static List<Integer> getListByDay() {
		List<Integer> all = new ArrayList<Integer>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByLog() {
		List<String> all = new ArrayList<String>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getLog());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}