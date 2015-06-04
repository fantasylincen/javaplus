//[消息提示]游戏公告package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GameNoticeTempletConfig {	private static Map<Integer, GameNoticeTemplet> map;	private static List<Integer> keys;	private static List<GameNoticeTemplet> all;	static {		load();		all = new ArrayList<GameNoticeTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<GameNoticeTemplet> getAll() {		return new ArrayList<GameNoticeTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GameNoticeConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = Maps.newConcurrentMap();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		GameNoticeTemplet x = new GameNoticeTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setTime( e.attributeValue("time") );		x.setContent( e.attributeValue("content") );		GameNoticeTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GameNoticeTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GameNoticeTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GameNoticeTemplet getMin() {		return get(getMinKey());	}	public static List<GameNoticeTemplet> findById(int value) {
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GameNoticeTemplet> findByName(String value) {
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GameNoticeTemplet> findByType(int value) {
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GameNoticeTemplet> findByTime(String value) {
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GameNoticeTemplet> findByContent(String value) {
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByTime() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByTime() {
		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}