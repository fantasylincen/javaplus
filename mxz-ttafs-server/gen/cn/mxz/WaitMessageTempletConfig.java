//[消息提示]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class WaitMessageTempletConfig {	private static Map<Integer, WaitMessageTemplet> map;	private static List<Integer> keys;	private static List<WaitMessageTemplet> all;	static {		load();	}	public static List<WaitMessageTemplet> getAll() {		return new ArrayList<WaitMessageTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/WaitMessageConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, WaitMessageTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																WaitMessageTempletConfig.map = map;		WaitMessageTempletConfig.keys = keys;																List<WaitMessageTemplet> all = new ArrayList<WaitMessageTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		WaitMessageTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, WaitMessageTemplet> map) {		WaitMessageTemplet x = new WaitMessageTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setContent( e.attributeValue("content") );		WaitMessageTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static WaitMessageTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static WaitMessageTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static WaitMessageTemplet getMin() {		return get(getMinKey());	}	public static List<WaitMessageTemplet> findById(int value) {
		List<WaitMessageTemplet> all = new ArrayList<WaitMessageTemplet>();
		for (WaitMessageTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<WaitMessageTemplet> findByContent(String value) {
		List<WaitMessageTemplet> all = new ArrayList<WaitMessageTemplet>();
		for (WaitMessageTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitMessageTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitMessageTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (WaitMessageTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (WaitMessageTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}