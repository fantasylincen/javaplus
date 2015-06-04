//[VIP]VIP权限（前端用表）package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VIPAuthorityTempletConfig {	private static Map<Integer, VIPAuthorityTemplet> map;	private static List<Integer> keys;	private static List<VIPAuthorityTemplet> all;	static {		load();	}	public static List<VIPAuthorityTemplet> getAll() {		return new ArrayList<VIPAuthorityTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VIPAuthorityConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, VIPAuthorityTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																VIPAuthorityTempletConfig.map = map;		VIPAuthorityTempletConfig.keys = keys;																List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		VIPAuthorityTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, VIPAuthorityTemplet> map) {		VIPAuthorityTemplet x = new VIPAuthorityTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setContent( e.attributeValue("content") );		x.setVip0( e.attributeValue("vip0") );		x.setVip1( e.attributeValue("vip1") );		x.setVip2( e.attributeValue("vip2") );		x.setVip3( e.attributeValue("vip3") );		x.setVip4( e.attributeValue("vip4") );		x.setVip5( e.attributeValue("vip5") );		x.setVip6( e.attributeValue("vip6") );		x.setVip7( e.attributeValue("vip7") );		x.setVip8( e.attributeValue("vip8") );		x.setVip9( e.attributeValue("vip9") );		x.setVip10( e.attributeValue("vip10") );		x.setVip11( e.attributeValue("vip11") );		x.setVip12( e.attributeValue("vip12") );		x.setVip13( e.attributeValue("vip13") );		x.setVip14( e.attributeValue("vip14") );		x.setVip15( e.attributeValue("vip15") );		x.setVip16( e.attributeValue("vip16") );		VIPAuthorityTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VIPAuthorityTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VIPAuthorityTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VIPAuthorityTemplet getMin() {		return get(getMinKey());	}	public static List<VIPAuthorityTemplet> findById(int value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByName(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByContent(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip0(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip0(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip1(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip2(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip3(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip4(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip5(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip5(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip6(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip6(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip7(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip7(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip8(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip8(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip9(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip9(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip10(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip10(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip11(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip11(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip12(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip12(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip13(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip13(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip14(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip14(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip15(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip15(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VIPAuthorityTemplet> findByVip16(String value) {
		List<VIPAuthorityTemplet> all = new ArrayList<VIPAuthorityTemplet>();
		for (VIPAuthorityTemplet f : getAll()) {
			if(equals(f.getVip16(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static String[] getArrayByVip0() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip0();
		}
		return all;
	}
	public static String[] getArrayByVip1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip1();
		}
		return all;
	}
	public static String[] getArrayByVip2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip2();
		}
		return all;
	}
	public static String[] getArrayByVip3() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip3();
		}
		return all;
	}
	public static String[] getArrayByVip4() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip4();
		}
		return all;
	}
	public static String[] getArrayByVip5() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip5();
		}
		return all;
	}
	public static String[] getArrayByVip6() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip6();
		}
		return all;
	}
	public static String[] getArrayByVip7() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip7();
		}
		return all;
	}
	public static String[] getArrayByVip8() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip8();
		}
		return all;
	}
	public static String[] getArrayByVip9() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip9();
		}
		return all;
	}
	public static String[] getArrayByVip10() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip10();
		}
		return all;
	}
	public static String[] getArrayByVip11() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip11();
		}
		return all;
	}
	public static String[] getArrayByVip12() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip12();
		}
		return all;
	}
	public static String[] getArrayByVip13() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip13();
		}
		return all;
	}
	public static String[] getArrayByVip14() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip14();
		}
		return all;
	}
	public static String[] getArrayByVip15() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip15();
		}
		return all;
	}
	public static String[] getArrayByVip16() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VIPAuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVip16();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	public static List<String> getListByVip0() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip0());
		}
		return all;
	}
	public static List<String> getListByVip1() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip1());
		}
		return all;
	}
	public static List<String> getListByVip2() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip2());
		}
		return all;
	}
	public static List<String> getListByVip3() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip3());
		}
		return all;
	}
	public static List<String> getListByVip4() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip4());
		}
		return all;
	}
	public static List<String> getListByVip5() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip5());
		}
		return all;
	}
	public static List<String> getListByVip6() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip6());
		}
		return all;
	}
	public static List<String> getListByVip7() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip7());
		}
		return all;
	}
	public static List<String> getListByVip8() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip8());
		}
		return all;
	}
	public static List<String> getListByVip9() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip9());
		}
		return all;
	}
	public static List<String> getListByVip10() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip10());
		}
		return all;
	}
	public static List<String> getListByVip11() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip11());
		}
		return all;
	}
	public static List<String> getListByVip12() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip12());
		}
		return all;
	}
	public static List<String> getListByVip13() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip13());
		}
		return all;
	}
	public static List<String> getListByVip14() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip14());
		}
		return all;
	}
	public static List<String> getListByVip15() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip15());
		}
		return all;
	}
	public static List<String> getListByVip16() {
		List<String> all = new ArrayList<String>();
		for (VIPAuthorityTemplet f : getAll()) {
			all.add(f.getVip16());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}