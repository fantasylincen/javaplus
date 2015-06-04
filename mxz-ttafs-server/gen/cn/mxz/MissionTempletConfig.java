//[关卡][1]关卡(章节,第1关卡即第1章)(一个关卡里有多个副本)package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MissionTempletConfig {	private static Map<Integer, MissionTemplet> map;	private static List<Integer> keys;	private static List<MissionTemplet> all;	static {		load();	}	public static List<MissionTemplet> getAll() {		return new ArrayList<MissionTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MissionConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MissionTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MissionTempletConfig.map = map;		MissionTempletConfig.keys = keys;																List<MissionTemplet> all = new ArrayList<MissionTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MissionTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MissionTemplet> map) {		MissionTemplet x = new MissionTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setPictype( Integer.decode( e.attributeValue("pictype").trim() ) );		MissionTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MissionTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MissionTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MissionTemplet getMin() {		return get(getMinKey());	}	public static List<MissionTemplet> findById(int value) {
		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionTemplet> findByName(String value) {
		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionTemplet> findByPictype(int value) {
		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByPictype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByPictype() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}