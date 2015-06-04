//[常量定义]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DefineTempletConfig {	private static Map<String, DefineTemplet> map;	private static List<String> keys;	private static List<DefineTemplet> all;	static {		load();	}	public static List<DefineTemplet> getAll() {		return new ArrayList<DefineTemplet>(all);	}	public static List<String> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DefineConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<String, DefineTemplet> map = Maps.newConcurrentMap();		List<String> keys = new ArrayList<String>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DefineTempletConfig.map = map;		DefineTempletConfig.keys = keys;																List<DefineTemplet> all = new ArrayList<DefineTemplet>();		for(String c : keys) {			all.add(get(c));		}		DefineTempletConfig.all = all;	}	private static void put(Element e, Map<String, DefineTemplet> map) {		DefineTemplet x = new DefineTemplet();		x.setName( e.attributeValue("name") );		x.setType( e.attributeValue("type") );		x.setValue( e.attributeValue("value") );		x.setExplain( e.attributeValue("explain") );		DefineTemplet remove = map.put(x.getName(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DefineTemplet get(String x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static String getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static String getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DefineTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DefineTemplet getMin() {		return get(getMinKey());	}	public static List<DefineTemplet> findByName(String value) {
		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DefineTemplet> findByType(String value) {
		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DefineTemplet> findByValue(String value) {
		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DefineTemplet> findByExplain(String value) {
		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByValue() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getValue();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByType() {
		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByValue() {
		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getValue());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}