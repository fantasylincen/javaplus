//[奇遇]25[云游仙人]物品package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RoamMarketPlaceTempletConfig {	private static Map<Integer, RoamMarketPlaceTemplet> map;	private static List<Integer> keys;	private static List<RoamMarketPlaceTemplet> all;	static {		load();	}	public static List<RoamMarketPlaceTemplet> getAll() {		return new ArrayList<RoamMarketPlaceTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RoamMarketPlaceConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RoamMarketPlaceTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RoamMarketPlaceTempletConfig.map = map;		RoamMarketPlaceTempletConfig.keys = keys;																List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RoamMarketPlaceTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RoamMarketPlaceTemplet> map) {		RoamMarketPlaceTemplet x = new RoamMarketPlaceTemplet();		x.setTypeId( Integer.decode( e.attributeValue("typeId").trim() ) );		x.setName( e.attributeValue("name") );		x.setMin( Integer.decode( e.attributeValue("min").trim() ) );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setCouponsRoam( Integer.decode( e.attributeValue("couponsRoam").trim() ) );		x.setCouponsNew( Integer.decode( e.attributeValue("CouponsNew").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setCashRoam( Integer.decode( e.attributeValue("cashRoam").trim() ) );		x.setCashNew( Integer.decode( e.attributeValue("cashNew").trim() ) );		RoamMarketPlaceTemplet remove = map.put(x.getTypeId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RoamMarketPlaceTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RoamMarketPlaceTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RoamMarketPlaceTemplet getMin() {		return get(getMinKey());	}	public static List<RoamMarketPlaceTemplet> findByTypeId(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getTypeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByName(String value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByMin(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByMax(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByDescription(String value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByCouponsRoam(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getCouponsRoam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByCouponsNew(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getCouponsNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByWeight(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByCashRoam(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getCashRoam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RoamMarketPlaceTemplet> findByCashNew(int value) {
		List<RoamMarketPlaceTemplet> all = new ArrayList<RoamMarketPlaceTemplet>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			if(equals(f.getCashNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByTypeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getTypeId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getMin();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static int[] getArrayByCouponsRoam() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCouponsRoam();
		}
		return all;
	}
	public static int[] getArrayByCouponsNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCouponsNew();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByCashRoam() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCashRoam();
		}
		return all;
	}
	public static int[] getArrayByCashNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamMarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCashNew();
		}
		return all;
	}
	public static List<Integer> getListByTypeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getTypeId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getMin());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<Integer> getListByCouponsRoam() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getCouponsRoam());
		}
		return all;
	}
	public static List<Integer> getListByCouponsNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getCouponsNew());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByCashRoam() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getCashRoam());
		}
		return all;
	}
	public static List<Integer> getListByCashNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (RoamMarketPlaceTemplet f : getAll()) {
			all.add(f.getCashNew());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}