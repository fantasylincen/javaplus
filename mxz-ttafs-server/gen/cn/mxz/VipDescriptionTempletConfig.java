//[VIP]描述package cn.mxz;import java.io.File;import java.util.HashMap;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VipDescriptionTempletConfig {	private static Map<Integer, VipDescriptionTemplet> map;	private static List<Integer> keys;	private static List<VipDescriptionTemplet> all;	static {		load();		all = new ArrayList<VipDescriptionTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<VipDescriptionTemplet> getAll() {		return new ArrayList<VipDescriptionTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VipDescriptionConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = new HashMap<Integer, VipDescriptionTemplet>();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		VipDescriptionTemplet x = new VipDescriptionTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setDescription1( e.attributeValue("description1") );		x.setDescription2( e.attributeValue("description2") );		x.setDescription3( e.attributeValue("description3") );		x.setDescription4( e.attributeValue("description4") );		x.setDescription5( e.attributeValue("description5") );		x.setDescription6( e.attributeValue("description6") );		x.setDescription7( e.attributeValue("description7") );		x.setDescription8( e.attributeValue("description8") );		x.setDescription9( e.attributeValue("description9") );		VipDescriptionTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VipDescriptionTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VipDescriptionTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VipDescriptionTemplet getMin() {		return get(getMinKey());	}	public static List<VipDescriptionTemplet> findById(int value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription1(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription2(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription3(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription4(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription5(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription5(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription6(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription6(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription7(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription7(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription8(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription8(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipDescriptionTemplet> findByDescription9(String value) {
		List<VipDescriptionTemplet> all = new ArrayList<VipDescriptionTemplet>();
		for (VipDescriptionTemplet f : getAll()) {
			if(equals(f.getDescription9(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByDescription1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription1();
		}
		return all;
	}
	public static String[] getArrayByDescription2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription2();
		}
		return all;
	}
	public static String[] getArrayByDescription3() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription3();
		}
		return all;
	}
	public static String[] getArrayByDescription4() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription4();
		}
		return all;
	}
	public static String[] getArrayByDescription5() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription5();
		}
		return all;
	}
	public static String[] getArrayByDescription6() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription6();
		}
		return all;
	}
	public static String[] getArrayByDescription7() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription7();
		}
		return all;
	}
	public static String[] getArrayByDescription8() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription8();
		}
		return all;
	}
	public static String[] getArrayByDescription9() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipDescriptionTemplet f = get(keys.get(i));
			all[i] = f.getDescription9();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByDescription1() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription1());
		}
		return all;
	}
	public static List<String> getListByDescription2() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription2());
		}
		return all;
	}
	public static List<String> getListByDescription3() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription3());
		}
		return all;
	}
	public static List<String> getListByDescription4() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription4());
		}
		return all;
	}
	public static List<String> getListByDescription5() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription5());
		}
		return all;
	}
	public static List<String> getListByDescription6() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription6());
		}
		return all;
	}
	public static List<String> getListByDescription7() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription7());
		}
		return all;
	}
	public static List<String> getListByDescription8() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription8());
		}
		return all;
	}
	public static List<String> getListByDescription9() {
		List<String> all = new ArrayList<String>();
		for (VipDescriptionTemplet f : getAll()) {
			all.add(f.getDescription9());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}