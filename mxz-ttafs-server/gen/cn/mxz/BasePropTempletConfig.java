//[奖励]id类型表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BasePropTempletConfig {	private static Map<Integer, BasePropTemplet> map;	private static List<Integer> keys;	private static List<BasePropTemplet> all;	static {		load();	}	public static List<BasePropTemplet> getAll() {		return new ArrayList<BasePropTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BasePropConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BasePropTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BasePropTempletConfig.map = map;		BasePropTempletConfig.keys = keys;																List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BasePropTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BasePropTemplet> map) {		BasePropTemplet x = new BasePropTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setDescription( e.attributeValue("description") );		x.setXml( e.attributeValue("xml") );		BasePropTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BasePropTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BasePropTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BasePropTemplet getMin() {		return get(getMinKey());	}	public static List<BasePropTemplet> findById(int value) {
		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BasePropTemplet> findByName(String value) {
		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BasePropTemplet> findByDescription(String value) {
		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BasePropTemplet> findByXml(String value) {
		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getXml(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByXml() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getXml();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByXml() {
		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getXml());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}