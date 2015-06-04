//[奇遇]50[神秘商店]随机物品类型表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AgainstTypeLibraryTempletConfig {	private static Map<Integer, AgainstTypeLibraryTemplet> map;	private static List<Integer> keys;	private static List<AgainstTypeLibraryTemplet> all;	static {		load();	}	public static List<AgainstTypeLibraryTemplet> getAll() {		return new ArrayList<AgainstTypeLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AgainstTypeLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, AgainstTypeLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																AgainstTypeLibraryTempletConfig.map = map;		AgainstTypeLibraryTempletConfig.keys = keys;																List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		AgainstTypeLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, AgainstTypeLibraryTemplet> map) {		AgainstTypeLibraryTemplet x = new AgainstTypeLibraryTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setWeight2( Integer.decode( e.attributeValue("weight2").trim() ) );		x.setWeight3( Integer.decode( e.attributeValue("weight3").trim() ) );		x.setSzzy( Float.parseFloat( e.attributeValue("szzy").trim() ) );		x.setSzzy2( Float.parseFloat( e.attributeValue("szzy2").trim() ) );		x.setSzzy3( Float.parseFloat( e.attributeValue("szzy3").trim() ) );		AgainstTypeLibraryTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AgainstTypeLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AgainstTypeLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AgainstTypeLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<AgainstTypeLibraryTemplet> findById(int value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findByName(String value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findByWeight(int value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findByWeight2(int value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getWeight2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findByWeight3(int value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getWeight3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findBySzzy(float value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findBySzzy2(float value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstTypeLibraryTemplet> findBySzzy3(float value) {
		List<AgainstTypeLibraryTemplet> all = new ArrayList<AgainstTypeLibraryTemplet>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByWeight2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight2();
		}
		return all;
	}
	public static int[] getArrayByWeight3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight3();
		}
		return all;
	}
	public static float[] getArrayBySzzy() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy();
		}
		return all;
	}
	public static float[] getArrayBySzzy2() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy2();
		}
		return all;
	}
	public static float[] getArrayBySzzy3() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstTypeLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy3();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByWeight2() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getWeight2());
		}
		return all;
	}
	public static List<Integer> getListByWeight3() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getWeight3());
		}
		return all;
	}
	public static List<Float> getListBySzzy() {
		List<Float> all = new ArrayList<Float>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getSzzy());
		}
		return all;
	}
	public static List<Float> getListBySzzy2() {
		List<Float> all = new ArrayList<Float>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getSzzy2());
		}
		return all;
	}
	public static List<Float> getListBySzzy3() {
		List<Float> all = new ArrayList<Float>();
		for (AgainstTypeLibraryTemplet f : getAll()) {
			all.add(f.getSzzy3());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}