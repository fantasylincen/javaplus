//[奇遇]竞技场和夺宝翻牌库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SnatchAndArenaLibraryTempletConfig {	private static Map<Integer, SnatchAndArenaLibraryTemplet> map;	private static List<Integer> keys;	private static List<SnatchAndArenaLibraryTemplet> all;	static {		load();	}	public static List<SnatchAndArenaLibraryTemplet> getAll() {		return new ArrayList<SnatchAndArenaLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SnatchAndArenaLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, SnatchAndArenaLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SnatchAndArenaLibraryTempletConfig.map = map;		SnatchAndArenaLibraryTempletConfig.keys = keys;																List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		SnatchAndArenaLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, SnatchAndArenaLibraryTemplet> map) {		SnatchAndArenaLibraryTemplet x = new SnatchAndArenaLibraryTemplet();		x.setIdfen( Integer.decode( e.attributeValue("idfen").trim() ) );		x.setReward( e.attributeValue("reward") );		x.setName( e.attributeValue("name") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setLvMin( Integer.decode( e.attributeValue("lvMin").trim() ) );		x.setLvMax( Integer.decode( e.attributeValue("lvMax").trim() ) );		x.setPropMin( Integer.decode( e.attributeValue("propMin").trim() ) );		x.setPropMax( Integer.decode( e.attributeValue("propMax").trim() ) );		x.setSzzy( Float.parseFloat( e.attributeValue("szzy").trim() ) );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		SnatchAndArenaLibraryTemplet remove = map.put(x.getIdfen(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SnatchAndArenaLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SnatchAndArenaLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SnatchAndArenaLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<SnatchAndArenaLibraryTemplet> findByIdfen(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getIdfen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByReward(String value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByName(String value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByType(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByQuality(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByWeight(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByLvMin(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getLvMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByLvMax(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getLvMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByPropMin(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getPropMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByPropMax(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getPropMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findBySzzy(float value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchAndArenaLibraryTemplet> findByModulesId(int value) {
		List<SnatchAndArenaLibraryTemplet> all = new ArrayList<SnatchAndArenaLibraryTemplet>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByIdfen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getIdfen();
		}
		return all;
	}
	public static String[] getArrayByReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getReward();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByLvMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getLvMin();
		}
		return all;
	}
	public static int[] getArrayByLvMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getLvMax();
		}
		return all;
	}
	public static int[] getArrayByPropMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropMin();
		}
		return all;
	}
	public static int[] getArrayByPropMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropMax();
		}
		return all;
	}
	public static float[] getArrayBySzzy() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchAndArenaLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static List<Integer> getListByIdfen() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getIdfen());
		}
		return all;
	}
	public static List<String> getListByReward() {
		List<String> all = new ArrayList<String>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getReward());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByLvMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getLvMin());
		}
		return all;
	}
	public static List<Integer> getListByLvMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getLvMax());
		}
		return all;
	}
	public static List<Integer> getListByPropMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getPropMin());
		}
		return all;
	}
	public static List<Integer> getListByPropMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getPropMax());
		}
		return all;
	}
	public static List<Float> getListBySzzy() {
		List<Float> all = new ArrayList<Float>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getSzzy());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchAndArenaLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}