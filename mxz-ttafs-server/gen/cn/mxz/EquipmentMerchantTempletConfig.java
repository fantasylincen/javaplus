//[奇遇][装备商人]随机物品库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class EquipmentMerchantTempletConfig {	private static Map<Integer, EquipmentMerchantTemplet> map;	private static List<Integer> keys;	private static List<EquipmentMerchantTemplet> all;	static {		load();	}	public static List<EquipmentMerchantTemplet> getAll() {		return new ArrayList<EquipmentMerchantTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/EquipmentMerchantConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, EquipmentMerchantTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																EquipmentMerchantTempletConfig.map = map;		EquipmentMerchantTempletConfig.keys = keys;																List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		EquipmentMerchantTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, EquipmentMerchantTemplet> map) {		EquipmentMerchantTemplet x = new EquipmentMerchantTemplet();		x.setIdfen( Integer.decode( e.attributeValue("idfen").trim() ) );		x.setEquipmentTempletId( Integer.decode( e.attributeValue("equipmentTempletId").trim() ) );		x.setPropNeame( e.attributeValue("propNeame") );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		x.setQuota( Integer.decode( e.attributeValue("quota").trim() ) );		x.setConsume( e.attributeValue("consume") );		EquipmentMerchantTemplet remove = map.put(x.getIdfen(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static EquipmentMerchantTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static EquipmentMerchantTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static EquipmentMerchantTemplet getMin() {		return get(getMinKey());	}	public static List<EquipmentMerchantTemplet> findByIdfen(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getIdfen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByEquipmentTempletId(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getEquipmentTempletId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByPropNeame(String value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByStep(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByWeight(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByNumber(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByQuota(int value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getQuota(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentMerchantTemplet> findByConsume(String value) {
		List<EquipmentMerchantTemplet> all = new ArrayList<EquipmentMerchantTemplet>();
		for (EquipmentMerchantTemplet f : getAll()) {
			if(equals(f.getConsume(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByIdfen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getIdfen();
		}
		return all;
	}
	public static int[] getArrayByEquipmentTempletId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentTempletId();
		}
		return all;
	}
	public static String[] getArrayByPropNeame() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByQuota() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getQuota();
		}
		return all;
	}
	public static String[] getArrayByConsume() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentMerchantTemplet f = get(keys.get(i));
			all[i] = f.getConsume();
		}
		return all;
	}
	public static List<Integer> getListByIdfen() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getIdfen());
		}
		return all;
	}
	public static List<Integer> getListByEquipmentTempletId() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getEquipmentTempletId());
		}
		return all;
	}
	public static List<String> getListByPropNeame() {
		List<String> all = new ArrayList<String>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByQuota() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getQuota());
		}
		return all;
	}
	public static List<String> getListByConsume() {
		List<String> all = new ArrayList<String>();
		for (EquipmentMerchantTemplet f : getAll()) {
			all.add(f.getConsume());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}