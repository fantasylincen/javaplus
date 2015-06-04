//[消息提示]提示系统package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class HintTempletConfig {	private static Map<Integer, HintTemplet> map;	private static List<Integer> keys;	private static List<HintTemplet> all;	static {		load();	}	public static List<HintTemplet> getAll() {		return new ArrayList<HintTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/HintConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, HintTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																HintTempletConfig.map = map;		HintTempletConfig.keys = keys;																List<HintTemplet> all = new ArrayList<HintTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		HintTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, HintTemplet> map) {		HintTemplet x = new HintTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setLink( e.attributeValue("link") );		x.setShuoming1( e.attributeValue("shuoming1") );		x.setDescription1( e.attributeValue("description1") );		x.setDescription2( e.attributeValue("description2") );		x.setFormat( e.attributeValue("format") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setShuoming2( e.attributeValue("shuoming2") );		HintTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static HintTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static HintTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static HintTemplet getMin() {		return get(getMinKey());	}	public static List<HintTemplet> findById(int value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByName(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByLink(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByShuoming1(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getShuoming1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByDescription1(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getDescription1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByDescription2(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getDescription2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByFormat(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByResid(int value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<HintTemplet> findByShuoming2(String value) {
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getShuoming2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByLink() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}
	public static String[] getArrayByShuoming1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getShuoming1();
		}
		return all;
	}
	public static String[] getArrayByDescription1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getDescription1();
		}
		return all;
	}
	public static String[] getArrayByDescription2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getDescription2();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByShuoming2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getShuoming2();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (HintTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByLink() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}
	public static List<String> getListByShuoming1() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getShuoming1());
		}
		return all;
	}
	public static List<String> getListByDescription1() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getDescription1());
		}
		return all;
	}
	public static List<String> getListByDescription2() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getDescription2());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (HintTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByShuoming2() {
		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getShuoming2());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}