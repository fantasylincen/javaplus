//[神兽]神兽成长package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DogzLevelTempletConfig {	private static Map<Integer, DogzLevelTemplet> map;	private static List<Integer> keys;	private static List<DogzLevelTemplet> all;	static {		load();	}	public static List<DogzLevelTemplet> getAll() {		return new ArrayList<DogzLevelTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DogzLevelConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DogzLevelTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DogzLevelTempletConfig.map = map;		DogzLevelTempletConfig.keys = keys;																List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DogzLevelTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DogzLevelTemplet> map) {		DogzLevelTemplet x = new DogzLevelTemplet();		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setSoulPower( Integer.decode( e.attributeValue("soulPower").trim() ) );		x.setSoulNumber( Integer.decode( e.attributeValue("soulNumber").trim() ) );		x.setUpgrade( Float.parseFloat( e.attributeValue("upgrade").trim() ) );		x.setSingleSoulPower( Float.parseFloat( e.attributeValue("singleSoulPower").trim() ) );		x.setSumSoulNumber( Integer.decode( e.attributeValue("sumSoulNumber").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		DogzLevelTemplet remove = map.put(x.getLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DogzLevelTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DogzLevelTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DogzLevelTemplet getMin() {		return get(getMinKey());	}	public static List<DogzLevelTemplet> findByLevel(int value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findBySoulPower(int value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSoulPower(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findBySoulNumber(int value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSoulNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findByUpgrade(float value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getUpgrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findBySingleSoulPower(float value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSingleSoulPower(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findBySumSoulNumber(int value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSumSoulNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzLevelTemplet> findBySocial(int value) {
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayBySoulPower() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSoulPower();
		}
		return all;
	}
	public static int[] getArrayBySoulNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSoulNumber();
		}
		return all;
	}
	public static float[] getArrayByUpgrade() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getUpgrade();
		}
		return all;
	}
	public static float[] getArrayBySingleSoulPower() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSingleSoulPower();
		}
		return all;
	}
	public static int[] getArrayBySumSoulNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSumSoulNumber();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListBySoulPower() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSoulPower());
		}
		return all;
	}
	public static List<Integer> getListBySoulNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSoulNumber());
		}
		return all;
	}
	public static List<Float> getListByUpgrade() {
		List<Float> all = new ArrayList<Float>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getUpgrade());
		}
		return all;
	}
	public static List<Float> getListBySingleSoulPower() {
		List<Float> all = new ArrayList<Float>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSingleSoulPower());
		}
		return all;
	}
	public static List<Integer> getListBySumSoulNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSumSoulNumber());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}