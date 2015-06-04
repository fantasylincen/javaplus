//[阵法]替补属性库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FormationBenchTempletConfig {	private static Map<Integer, FormationBenchTemplet> map;	private static List<Integer> keys;	private static List<FormationBenchTemplet> all;	static {		load();	}	public static List<FormationBenchTemplet> getAll() {		return new ArrayList<FormationBenchTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FormationBenchConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FormationBenchTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FormationBenchTempletConfig.map = map;		FormationBenchTempletConfig.keys = keys;																List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FormationBenchTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FormationBenchTemplet> map) {		FormationBenchTemplet x = new FormationBenchTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setSpet( Integer.decode( e.attributeValue("spet").trim() ) );		x.setNatureType( Integer.decode( e.attributeValue("natureType").trim() ) );		x.setAddition( Float.parseFloat( e.attributeValue("addition").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		FormationBenchTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FormationBenchTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FormationBenchTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FormationBenchTemplet getMin() {		return get(getMinKey());	}	public static List<FormationBenchTemplet> findById(int value) {
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchTemplet> findBySpet(int value) {
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchTemplet> findByNatureType(int value) {
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getNatureType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchTemplet> findByAddition(float value) {
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationBenchTemplet> findByWeight(int value) {
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayBySpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}
	public static int[] getArrayByNatureType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getNatureType();
		}
		return all;
	}
	public static float[] getArrayByAddition() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListBySpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}
	public static List<Integer> getListByNatureType() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getNatureType());
		}
		return all;
	}
	public static List<Float> getListByAddition() {
		List<Float> all = new ArrayList<Float>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}