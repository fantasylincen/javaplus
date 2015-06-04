//[奖励]在线奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class OnlineTempletConfig {	private static Map<Integer, OnlineTemplet> map;	private static List<Integer> keys;	private static List<OnlineTemplet> all;	static {		load();	}	public static List<OnlineTemplet> getAll() {		return new ArrayList<OnlineTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/OnlineConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, OnlineTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																OnlineTempletConfig.map = map;		OnlineTempletConfig.keys = keys;																List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		OnlineTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, OnlineTemplet> map) {		OnlineTemplet x = new OnlineTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setDuration( Integer.decode( e.attributeValue("duration").trim() ) );		x.setAwards( e.attributeValue("awards") );		OnlineTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static OnlineTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static OnlineTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static OnlineTemplet getMin() {		return get(getMinKey());	}	public static List<OnlineTemplet> findById(int value) {
		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<OnlineTemplet> findByDuration(int value) {
		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getDuration(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<OnlineTemplet> findByAwards(String value) {
		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByDuration() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getDuration();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByDuration() {
		List<Integer> all = new ArrayList<Integer>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getDuration());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}