//[关卡][0]世界大陆package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MainlandTempletConfig {	private static Map<Integer, MainlandTemplet> map;	private static List<Integer> keys;	private static List<MainlandTemplet> all;	static {		load();	}	public static List<MainlandTemplet> getAll() {		return new ArrayList<MainlandTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MainlandConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MainlandTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MainlandTempletConfig.map = map;		MainlandTempletConfig.keys = keys;																List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MainlandTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MainlandTemplet> map) {		MainlandTemplet x = new MainlandTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setPictype( Integer.decode( e.attributeValue("pictype").trim() ) );		x.setOpen( Integer.decode( e.attributeValue("open").trim() ) );		x.setCondition( Integer.decode( e.attributeValue("condition").trim() ) );		x.setStartCondition( Integer.decode( e.attributeValue("startCondition").trim() ) );		x.setOverCondition( Integer.decode( e.attributeValue("overCondition").trim() ) );		x.setStartChapter( Integer.decode( e.attributeValue("startChapter").trim() ) );		x.setOverChapter( Integer.decode( e.attributeValue("overChapter").trim() ) );		MainlandTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MainlandTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MainlandTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MainlandTemplet getMin() {		return get(getMinKey());	}	public static List<MainlandTemplet> findById(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByName(String value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByPictype(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByOpen(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByCondition(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByStartCondition(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getStartCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByOverCondition(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOverCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByStartChapter(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getStartChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MainlandTemplet> findByOverChapter(int value) {
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOverChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByPictype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}
	public static int[] getArrayByOpen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOpen();
		}
		return all;
	}
	public static int[] getArrayByCondition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getCondition();
		}
		return all;
	}
	public static int[] getArrayByStartCondition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getStartCondition();
		}
		return all;
	}
	public static int[] getArrayByOverCondition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOverCondition();
		}
		return all;
	}
	public static int[] getArrayByStartChapter() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getStartChapter();
		}
		return all;
	}
	public static int[] getArrayByOverChapter() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOverChapter();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByPictype() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
	public static List<Integer> getListByOpen() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOpen());
		}
		return all;
	}
	public static List<Integer> getListByCondition() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getCondition());
		}
		return all;
	}
	public static List<Integer> getListByStartCondition() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getStartCondition());
		}
		return all;
	}
	public static List<Integer> getListByOverCondition() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOverCondition());
		}
		return all;
	}
	public static List<Integer> getListByStartChapter() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getStartChapter());
		}
		return all;
	}
	public static List<Integer> getListByOverChapter() {
		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOverChapter());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}