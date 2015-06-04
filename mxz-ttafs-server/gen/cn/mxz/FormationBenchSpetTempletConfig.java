//[阵法]替补属性品质刷新package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FormationBenchSpetTempletConfig {	private static Map<Integer, FormationBenchSpetTemplet> map;	private static List<Integer> keys;	private static List<FormationBenchSpetTemplet> all;	static {		load();	}	public static List<FormationBenchSpetTemplet> getAll() {		return new ArrayList<FormationBenchSpetTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FormationBenchSpetConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FormationBenchSpetTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FormationBenchSpetTempletConfig.map = map;		FormationBenchSpetTempletConfig.keys = keys;																List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FormationBenchSpetTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FormationBenchSpetTemplet> map) {		FormationBenchSpetTemplet x = new FormationBenchSpetTemplet();		x.setSpet( Integer.decode( e.attributeValue("spet").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setMustNumber( Integer.decode( e.attributeValue("mustNumber").trim() ) );		FormationBenchSpetTemplet remove = map.put(x.getSpet(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FormationBenchSpetTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FormationBenchSpetTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FormationBenchSpetTemplet getMin() {		return get(getMinKey());	}	public static List<FormationBenchSpetTemplet> findBySpet(int value) {
		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchSpetTemplet> findByWeight(int value) {
		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchSpetTemplet> findByExplain(String value) {
		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchSpetTemplet> findByMustNumber(int value) {
		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getMustNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayBySpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static int[] getArrayByMustNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getMustNumber();
		}
		return all;
	}
	public static List<Integer> getListBySpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<Integer> getListByMustNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getMustNumber());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}