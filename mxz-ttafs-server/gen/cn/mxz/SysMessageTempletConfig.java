//[消息提示]系统信息package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SysMessageTempletConfig {	private static Map<Integer, SysMessageTemplet> map;	private static List<Integer> keys;	private static List<SysMessageTemplet> all;	static {		load();	}	public static List<SysMessageTemplet> getAll() {		return new ArrayList<SysMessageTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SysMessageConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, SysMessageTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SysMessageTempletConfig.map = map;		SysMessageTempletConfig.keys = keys;																List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		SysMessageTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, SysMessageTemplet> map) {		SysMessageTemplet x = new SysMessageTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setPriority( Integer.decode( e.attributeValue("priority").trim() ) );		x.setValue( e.attributeValue("value") );		SysMessageTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SysMessageTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SysMessageTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SysMessageTemplet getMin() {		return get(getMinKey());	}	public static List<SysMessageTemplet> findById(int value) {
		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SysMessageTemplet> findByType(int value) {
		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SysMessageTemplet> findByPriority(int value) {
		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getPriority(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SysMessageTemplet> findByValue(String value) {
		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByPriority() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getPriority();
		}
		return all;
	}
	public static String[] getArrayByValue() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getValue();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByPriority() {
		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getPriority());
		}
		return all;
	}
	public static List<String> getListByValue() {
		List<String> all = new ArrayList<String>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getValue());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}