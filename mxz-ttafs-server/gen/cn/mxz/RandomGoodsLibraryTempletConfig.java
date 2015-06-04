//[道具]通用随机物品库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomGoodsLibraryTempletConfig {	private static Map<Integer, RandomGoodsLibraryTemplet> map;	private static List<Integer> keys;	private static List<RandomGoodsLibraryTemplet> all;	static {		load();	}	public static List<RandomGoodsLibraryTemplet> getAll() {		return new ArrayList<RandomGoodsLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomGoodsLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomGoodsLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomGoodsLibraryTempletConfig.map = map;		RandomGoodsLibraryTempletConfig.keys = keys;																List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomGoodsLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomGoodsLibraryTemplet> map) {		RandomGoodsLibraryTemplet x = new RandomGoodsLibraryTemplet();		x.setIdfen( Integer.decode( e.attributeValue("idfen").trim() ) );		x.setReward( e.attributeValue("reward") );		x.setName( e.attributeValue("name") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setRange( e.attributeValue("range") );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		x.setPropMin( Integer.decode( e.attributeValue("propMin").trim() ) );		x.setPropMax( Integer.decode( e.attributeValue("propMax").trim() ) );		RandomGoodsLibraryTemplet remove = map.put(x.getIdfen(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomGoodsLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomGoodsLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomGoodsLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<RandomGoodsLibraryTemplet> findByIdfen(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getIdfen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByReward(String value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByName(String value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByType(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByQuality(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByWeight(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByRange(String value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getRange(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByModulesId(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByPropMin(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getPropMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomGoodsLibraryTemplet> findByPropMax(int value) {
		List<RandomGoodsLibraryTemplet> all = new ArrayList<RandomGoodsLibraryTemplet>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getPropMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByIdfen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getIdfen();
		}
		return all;
	}
	public static String[] getArrayByReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getReward();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static String[] getArrayByRange() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getRange();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static int[] getArrayByPropMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropMin();
		}
		return all;
	}
	public static int[] getArrayByPropMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropMax();
		}
		return all;
	}
	public static List<Integer> getListByIdfen() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getIdfen());
		}
		return all;
	}
	public static List<String> getListByReward() {
		List<String> all = new ArrayList<String>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getReward());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<String> getListByRange() {
		List<String> all = new ArrayList<String>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getRange());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	public static List<Integer> getListByPropMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getPropMin());
		}
		return all;
	}
	public static List<Integer> getListByPropMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomGoodsLibraryTemplet f : getAll()) {
			all.add(f.getPropMax());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}