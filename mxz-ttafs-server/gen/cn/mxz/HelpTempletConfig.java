//[帮助]帮助package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class HelpTempletConfig {	private static Map<Integer, HelpTemplet> map;	private static List<Integer> keys;	private static List<HelpTemplet> all;	static {		load();	}	public static List<HelpTemplet> getAll() {		return new ArrayList<HelpTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/HelpConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, HelpTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																HelpTempletConfig.map = map;		HelpTempletConfig.keys = keys;																List<HelpTemplet> all = new ArrayList<HelpTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		HelpTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, HelpTemplet> map) {		HelpTemplet x = new HelpTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setCategory( e.attributeValue("category") );		x.setClassify( Integer.decode( e.attributeValue("classify").trim() ) );		x.setTitle( e.attributeValue("title") );		x.setDescription( e.attributeValue("description") );		HelpTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static HelpTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static HelpTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static HelpTemplet getMin() {		return get(getMinKey());	}	public static List<HelpTemplet> findById(int value) {
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HelpTemplet> findByCategory(String value) {
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HelpTemplet> findByClassify(int value) {
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getClassify(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HelpTemplet> findByTitle(String value) {
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getTitle(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HelpTemplet> findByDescription(String value) {
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByCategory() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}
	public static int[] getArrayByClassify() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getClassify();
		}
		return all;
	}
	public static String[] getArrayByTitle() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getTitle();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByCategory() {
		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}
	public static List<Integer> getListByClassify() {
		List<Integer> all = new ArrayList<Integer>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getClassify());
		}
		return all;
	}
	public static List<String> getListByTitle() {
		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getTitle());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}