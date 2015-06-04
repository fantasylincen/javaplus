//[VIP]成长计划package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class PmtpTempletConfig {	private static Map<Integer, PmtpTemplet> map;	private static List<Integer> keys;	private static List<PmtpTemplet> all;	static {		load();	}	public static List<PmtpTemplet> getAll() {		return new ArrayList<PmtpTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/PmtpConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, PmtpTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																PmtpTempletConfig.map = map;		PmtpTempletConfig.keys = keys;																List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		PmtpTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, PmtpTemplet> map) {		PmtpTemplet x = new PmtpTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setRebate( Integer.decode( e.attributeValue("rebate").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		PmtpTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static PmtpTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static PmtpTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static PmtpTemplet getMin() {		return get(getMinKey());	}	public static List<PmtpTemplet> findById(int value) {
		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PmtpTemplet> findByName(String value) {
		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PmtpTemplet> findByRebate(int value) {
		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getRebate(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PmtpTemplet> findByLevel(int value) {
		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByRebate() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getRebate();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByRebate() {
		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getRebate());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}