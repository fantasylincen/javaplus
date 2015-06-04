//[奇遇]15[关卡][神魔]碎片库表package cn.mxz;import java.io.File;import java.util.ArrayList;import java.util.Collections;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import org.dom4j.Attribute;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;public class RandomEventChipTempletConfig {	private static Map<Integer, RandomEventChipTemplet> map;	private static List<Integer> keys;	private static List<RandomEventChipTemplet> all;	static {		load();		all = new ArrayList<RandomEventChipTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<RandomEventChipTemplet> getAll() {		return new ArrayList<RandomEventChipTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventChipConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = new HashMap<Integer, RandomEventChipTemplet>();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		RandomEventChipTemplet x = new RandomEventChipTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setRappelzId( Integer.decode( e.attributeValue("rappelzId").trim() ) );		x.setDogzID( Integer.decode( e.attributeValue("dogzID").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		RandomEventChipTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventChipTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventChipTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventChipTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventChipTemplet> findById(int value) {
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventChipTemplet> findByName(String value) {
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventChipTemplet> findByRappelzId(int value) {
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getRappelzId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventChipTemplet> findByDogzID(int value) {
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getDogzID(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventChipTemplet> findByWeight(int value) {
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByRappelzId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getRappelzId();
		}
		return all;
	}
	public static int[] getArrayByDogzID() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getDogzID();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByRappelzId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getRappelzId());
		}
		return all;
	}
	public static List<Integer> getListByDogzID() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getDogzID());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}