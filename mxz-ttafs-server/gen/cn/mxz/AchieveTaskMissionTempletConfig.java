//[任务]成就章节奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AchieveTaskMissionTempletConfig {	private static Map<Short, AchieveTaskMissionTemplet> map;	private static List<Short> keys;	private static List<AchieveTaskMissionTemplet> all;	static {		load();	}	public static List<AchieveTaskMissionTemplet> getAll() {		return new ArrayList<AchieveTaskMissionTemplet>(all);	}	public static List<Short> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AchieveTaskMissionConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Short, AchieveTaskMissionTemplet> map = Maps.newConcurrentMap();		List<Short> keys = new ArrayList<Short>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																AchieveTaskMissionTempletConfig.map = map;		AchieveTaskMissionTempletConfig.keys = keys;																List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();		for(Short c : keys) {			all.add(get(c));		}		AchieveTaskMissionTempletConfig.all = all;	}	private static void put(Element e, Map<Short, AchieveTaskMissionTemplet> map) {		AchieveTaskMissionTemplet x = new AchieveTaskMissionTemplet();		x.setChapter( Short.parseShort( e.attributeValue("chapter").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setAwards( e.attributeValue("awards") );		AchieveTaskMissionTemplet remove = map.put(x.getChapter(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AchieveTaskMissionTemplet get(Short x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Short getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Short getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AchieveTaskMissionTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AchieveTaskMissionTemplet getMin() {		return get(getMinKey());	}	public static List<AchieveTaskMissionTemplet> findByChapter(short value) {
		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskMissionTemplet> findByExplain(String value) {
		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskMissionTemplet> findByAwards(String value) {
		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static short[] getArrayByChapter() {
		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getChapter();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static List<Short> getListByChapter() {
		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getChapter());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}