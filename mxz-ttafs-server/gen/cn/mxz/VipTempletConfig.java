//[VIP]月卡package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VipTempletConfig {	private static Map<Integer, VipTemplet> map;	private static List<Integer> keys;	private static List<VipTemplet> all;	static {		load();		all = new ArrayList<VipTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<VipTemplet> getAll() {		return new ArrayList<VipTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VipConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = Maps.newConcurrentMap();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		VipTemplet x = new VipTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setAccumulate( Integer.decode( e.attributeValue("accumulate").trim() ) );		x.setPrice( Integer.decode( e.attributeValue("price").trim() ) );		x.setPrivilege1( e.attributeValue("privilege1") );		x.setPrivilege2( e.attributeValue("privilege2") );		x.setPrivilege3( e.attributeValue("privilege3") );		x.setWelfare1( e.attributeValue("welfare1") );		x.setWelfare2( e.attributeValue("welfare2") );		x.setWelfare3( e.attributeValue("welfare3") );		x.setWelfare4( e.attributeValue("welfare4") );		VipTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VipTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VipTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VipTemplet getMin() {		return get(getMinKey());	}	public static List<VipTemplet> findById(int value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByAccumulate(int value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getAccumulate(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByPrice(int value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrice(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByPrivilege1(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByPrivilege2(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByPrivilege3(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByWelfare1(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByWelfare2(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByWelfare3(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipTemplet> findByWelfare4(String value) {
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByAccumulate() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getAccumulate();
		}
		return all;
	}
	public static int[] getArrayByPrice() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrice();
		}
		return all;
	}
	public static String[] getArrayByPrivilege1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege1();
		}
		return all;
	}
	public static String[] getArrayByPrivilege2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege2();
		}
		return all;
	}
	public static String[] getArrayByPrivilege3() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege3();
		}
		return all;
	}
	public static String[] getArrayByWelfare1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare1();
		}
		return all;
	}
	public static String[] getArrayByWelfare2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare2();
		}
		return all;
	}
	public static String[] getArrayByWelfare3() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare3();
		}
		return all;
	}
	public static String[] getArrayByWelfare4() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare4();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByAccumulate() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getAccumulate());
		}
		return all;
	}
	public static List<Integer> getListByPrice() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrice());
		}
		return all;
	}
	public static List<String> getListByPrivilege1() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege1());
		}
		return all;
	}
	public static List<String> getListByPrivilege2() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege2());
		}
		return all;
	}
	public static List<String> getListByPrivilege3() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege3());
		}
		return all;
	}
	public static List<String> getListByWelfare1() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare1());
		}
		return all;
	}
	public static List<String> getListByWelfare2() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare2());
		}
		return all;
	}
	public static List<String> getListByWelfare3() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare3());
		}
		return all;
	}
	public static List<String> getListByWelfare4() {
		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare4());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}