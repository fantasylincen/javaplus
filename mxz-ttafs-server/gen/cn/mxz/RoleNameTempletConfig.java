//[角色名称]名称package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RoleNameTempletConfig {	private static Map<Integer, RoleNameTemplet> map;	private static List<Integer> keys;	private static List<RoleNameTemplet> all;	static {		load();	}	public static List<RoleNameTemplet> getAll() {		return new ArrayList<RoleNameTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RoleNameConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RoleNameTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RoleNameTempletConfig.map = map;		RoleNameTempletConfig.keys = keys;																List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RoleNameTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RoleNameTemplet> map) {		RoleNameTemplet x = new RoleNameTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setFirst( e.attributeValue("first") );		x.setLast( e.attributeValue("last") );		RoleNameTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RoleNameTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RoleNameTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RoleNameTemplet getMin() {		return get(getMinKey());	}	public static List<RoleNameTemplet> findById(int value) {
		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoleNameTemplet> findByFirst(String value) {
		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoleNameTemplet> findByLast(String value) {
		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getLast(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByFirst() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getFirst();
		}
		return all;
	}
	public static String[] getArrayByLast() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getLast();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByFirst() {
		List<String> all = new ArrayList<String>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getFirst());
		}
		return all;
	}
	public static List<String> getListByLast() {
		List<String> all = new ArrayList<String>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getLast());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}