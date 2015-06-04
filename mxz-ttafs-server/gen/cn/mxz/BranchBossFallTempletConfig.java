//[关卡]4[地图]支线boss的随机掉落package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BranchBossFallTempletConfig {	private static Map<Integer, BranchBossFallTemplet> map;	private static List<Integer> keys;	private static List<BranchBossFallTemplet> all;	static {		load();	}	public static List<BranchBossFallTemplet> getAll() {		return new ArrayList<BranchBossFallTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BranchBossFallConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BranchBossFallTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BranchBossFallTempletConfig.map = map;		BranchBossFallTempletConfig.keys = keys;																List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BranchBossFallTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BranchBossFallTemplet> map) {		BranchBossFallTemplet x = new BranchBossFallTemplet();		x.setIdentif( Integer.decode( e.attributeValue("identif").trim() ) );		x.setPropId( Integer.decode( e.attributeValue("propId").trim() ) );		x.setName( e.attributeValue("name") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setRange( e.attributeValue("range") );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		BranchBossFallTemplet remove = map.put(x.getIdentif(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BranchBossFallTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BranchBossFallTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BranchBossFallTemplet getMin() {		return get(getMinKey());	}	public static List<BranchBossFallTemplet> findByIdentif(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getIdentif(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByPropId(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getPropId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByName(String value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByType(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByQuality(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByWeight(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByRange(String value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getRange(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BranchBossFallTemplet> findByModulesId(int value) {
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByIdentif() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getIdentif();
		}
		return all;
	}
	public static int[] getArrayByPropId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getPropId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static String[] getArrayByRange() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getRange();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static List<Integer> getListByIdentif() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getIdentif());
		}
		return all;
	}
	public static List<Integer> getListByPropId() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getPropId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<String> getListByRange() {
		List<String> all = new ArrayList<String>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getRange());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}