//[关卡]4[地图]副本掉落限制package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MissionRewardLimitTempletConfig {	private static Map<Integer, MissionRewardLimitTemplet> map;	private static List<Integer> keys;	private static List<MissionRewardLimitTemplet> all;	static {		load();	}	public static List<MissionRewardLimitTemplet> getAll() {		return new ArrayList<MissionRewardLimitTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MissionRewardLimitConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MissionRewardLimitTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MissionRewardLimitTempletConfig.map = map;		MissionRewardLimitTempletConfig.keys = keys;																List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MissionRewardLimitTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MissionRewardLimitTemplet> map) {		MissionRewardLimitTemplet x = new MissionRewardLimitTemplet();		x.setHead( Integer.decode( e.attributeValue("head").trim() ) );		x.setModuleId( Integer.decode( e.attributeValue("moduleId").trim() ) );		x.setComment( e.attributeValue("comment") );		MissionRewardLimitTemplet remove = map.put(x.getHead(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MissionRewardLimitTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MissionRewardLimitTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MissionRewardLimitTemplet getMin() {		return get(getMinKey());	}	public static List<MissionRewardLimitTemplet> findByHead(int value) {
		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getHead(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionRewardLimitTemplet> findByModuleId(int value) {
		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getModuleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionRewardLimitTemplet> findByComment(String value) {
		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByHead() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getHead();
		}
		return all;
	}
	public static int[] getArrayByModuleId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getModuleId();
		}
		return all;
	}
	public static String[] getArrayByComment() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}
	public static List<Integer> getListByHead() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getHead());
		}
		return all;
	}
	public static List<Integer> getListByModuleId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getModuleId());
		}
		return all;
	}
	public static List<String> getListByComment() {
		List<String> all = new ArrayList<String>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}