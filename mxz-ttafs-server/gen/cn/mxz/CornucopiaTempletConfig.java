//[奇遇]26[摇钱树]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CornucopiaTempletConfig {	private static Map<Integer, CornucopiaTemplet> map;	private static List<Integer> keys;	private static List<CornucopiaTemplet> all;	static {		load();	}	public static List<CornucopiaTemplet> getAll() {		return new ArrayList<CornucopiaTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CornucopiaConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CornucopiaTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CornucopiaTempletConfig.map = map;		CornucopiaTempletConfig.keys = keys;																List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CornucopiaTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CornucopiaTemplet> map) {		CornucopiaTemplet x = new CornucopiaTemplet();		x.setTimes( Integer.decode( e.attributeValue("times").trim() ) );		x.setCouponsNeed( Integer.decode( e.attributeValue("couponsNeed").trim() ) );		x.setCash( e.attributeValue("cash") );		x.setExplain( e.attributeValue("explain") );		CornucopiaTemplet remove = map.put(x.getTimes(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CornucopiaTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CornucopiaTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CornucopiaTemplet getMin() {		return get(getMinKey());	}	public static List<CornucopiaTemplet> findByTimes(int value) {
		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CornucopiaTemplet> findByCouponsNeed(int value) {
		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getCouponsNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CornucopiaTemplet> findByCash(String value) {
		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getCash(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CornucopiaTemplet> findByExplain(String value) {
		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByTimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getTimes();
		}
		return all;
	}
	public static int[] getArrayByCouponsNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getCouponsNeed();
		}
		return all;
	}
	public static String[] getArrayByCash() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getCash();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static List<Integer> getListByTimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getTimes());
		}
		return all;
	}
	public static List<Integer> getListByCouponsNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getCouponsNeed());
		}
		return all;
	}
	public static List<String> getListByCash() {
		List<String> all = new ArrayList<String>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getCash());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}