//[活动]活动配置表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ActivityTempletConfig {	private static Map<Integer, ActivityTemplet> map;	private static List<Integer> keys;	private static List<ActivityTemplet> all;	static {		load();	}	public static List<ActivityTemplet> getAll() {		return new ArrayList<ActivityTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ActivityConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ActivityTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ActivityTempletConfig.map = map;		ActivityTempletConfig.keys = keys;																List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ActivityTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ActivityTemplet> map) {		ActivityTemplet x = new ActivityTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setCode( e.attributeValue("code") );		x.setName( e.attributeValue("name") );		x.setRank( Integer.decode( e.attributeValue("rank").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setTime( e.attributeValue("time") );		x.setOpen( Integer.decode( e.attributeValue("open").trim() ) );		x.setShow( Integer.decode( e.attributeValue("show").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setUrl( e.attributeValue("url") );		ActivityTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ActivityTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ActivityTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ActivityTemplet getMin() {		return get(getMinKey());	}	public static List<ActivityTemplet> findById(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByCode(String value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByName(String value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByRank(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByQuality(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByTime(String value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByOpen(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByShow(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getShow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByLevel(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByResid(int value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByFormat(String value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ActivityTemplet> findByUrl(String value) {
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByCode() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByRank() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static String[] getArrayByTime() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}
	public static int[] getArrayByOpen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getOpen();
		}
		return all;
	}
	public static int[] getArrayByShow() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getShow();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByCode() {
		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByRank() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<String> getListByTime() {
		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}
	public static List<Integer> getListByOpen() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getOpen());
		}
		return all;
	}
	public static List<Integer> getListByShow() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getShow());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}