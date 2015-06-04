//[各种规则]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RolesTempletConfig {	private static Map<String, RolesTemplet> map;	private static List<String> keys;	private static List<RolesTemplet> all;	static {		load();	}	public static List<RolesTemplet> getAll() {		return new ArrayList<RolesTemplet>(all);	}	public static List<String> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RolesConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<String, RolesTemplet> map = Maps.newConcurrentMap();		List<String> keys = new ArrayList<String>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RolesTempletConfig.map = map;		RolesTempletConfig.keys = keys;																List<RolesTemplet> all = new ArrayList<RolesTemplet>();		for(String c : keys) {			all.add(get(c));		}		RolesTempletConfig.all = all;	}	private static void put(Element e, Map<String, RolesTemplet> map) {		RolesTemplet x = new RolesTemplet();		x.setName( e.attributeValue("name") );		x.setRole( e.attributeValue("role") );		x.setExplain( e.attributeValue("explain") );		RolesTemplet remove = map.put(x.getName(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RolesTemplet get(String x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static String getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static String getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RolesTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RolesTemplet getMin() {		return get(getMinKey());	}	public static List<RolesTemplet> findByName(String value) {
		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RolesTemplet> findByRole(String value) {
		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getRole(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RolesTemplet> findByExplain(String value) {
		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByRole() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getRole();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByRole() {
		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getRole());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}