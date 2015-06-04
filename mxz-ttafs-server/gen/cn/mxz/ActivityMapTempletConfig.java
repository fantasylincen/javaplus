//[地图]活动地图package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ActivityMapTempletConfig {	private static Map<Integer, ActivityMapTemplet> map;	private static List<Integer> keys;	private static List<ActivityMapTemplet> all;	static {		load();	}	public static List<ActivityMapTemplet> getAll() {		return new ArrayList<ActivityMapTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ActivityMapConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ActivityMapTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ActivityMapTempletConfig.map = map;		ActivityMapTempletConfig.keys = keys;																List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ActivityMapTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ActivityMapTemplet> map) {		ActivityMapTemplet x = new ActivityMapTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setFormat( e.attributeValue("format") );		ActivityMapTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ActivityMapTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ActivityMapTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ActivityMapTemplet getMin() {		return get(getMinKey());	}	public static List<ActivityMapTemplet> findById(int value) {
		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityMapTemplet> findByType(int value) {
		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityMapTemplet> findByResid(int value) {
		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityMapTemplet> findByFormat(String value) {
		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}