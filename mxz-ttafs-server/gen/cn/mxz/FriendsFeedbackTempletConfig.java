//[奇遇][邀请有礼]邀请有礼package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FriendsFeedbackTempletConfig {	private static Map<Integer, FriendsFeedbackTemplet> map;	private static List<Integer> keys;	private static List<FriendsFeedbackTemplet> all;	static {		load();	}	public static List<FriendsFeedbackTemplet> getAll() {		return new ArrayList<FriendsFeedbackTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FriendsFeedbackConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FriendsFeedbackTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FriendsFeedbackTempletConfig.map = map;		FriendsFeedbackTempletConfig.keys = keys;																List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FriendsFeedbackTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FriendsFeedbackTemplet> map) {		FriendsFeedbackTemplet x = new FriendsFeedbackTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setAward( e.attributeValue("award") );		x.setDescription( e.attributeValue("description") );		FriendsFeedbackTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FriendsFeedbackTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FriendsFeedbackTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FriendsFeedbackTemplet getMin() {		return get(getMinKey());	}	public static List<FriendsFeedbackTemplet> findById(int value) {
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FriendsFeedbackTemplet> findByNumber(int value) {
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FriendsFeedbackTemplet> findByLevel(int value) {
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FriendsFeedbackTemplet> findByAward(String value) {
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FriendsFeedbackTemplet> findByDescription(String value) {
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static String[] getArrayByAward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<String> getListByAward() {
		List<String> all = new ArrayList<String>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}