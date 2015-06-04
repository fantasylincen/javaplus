//[剧情]章节节点剧情package cn.mxz;import java.io.File;import java.util.HashMap;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SectionStoryTempletConfig {	private static Map<Integer, SectionStoryTemplet> map;	private static List<Integer> keys;	private static List<SectionStoryTemplet> all;	static {		load();		all = new ArrayList<SectionStoryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<SectionStoryTemplet> getAll() {		return new ArrayList<SectionStoryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SectionStoryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = new HashMap<Integer, SectionStoryTemplet>();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		SectionStoryTemplet x = new SectionStoryTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setStoryid( Integer.decode( e.attributeValue("storyid").trim() ) );		x.setName( e.attributeValue("name") );		x.setContent( e.attributeValue("content") );		x.setCoord( e.attributeValue("coord") );		x.setPicid( Integer.decode( e.attributeValue("picid").trim() ) );		x.setUrl( e.attributeValue("url") );		SectionStoryTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SectionStoryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SectionStoryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SectionStoryTemplet getMin() {		return get(getMinKey());	}	public static List<SectionStoryTemplet> findById(int value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByStoryid(int value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getStoryid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByName(String value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByContent(String value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByCoord(String value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getCoord(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByPicid(int value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getPicid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SectionStoryTemplet> findByUrl(String value) {
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByStoryid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getStoryid();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static String[] getArrayByCoord() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getCoord();
		}
		return all;
	}
	public static int[] getArrayByPicid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getPicid();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByStoryid() {
		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getStoryid());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	public static List<String> getListByCoord() {
		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getCoord());
		}
		return all;
	}
	public static List<Integer> getListByPicid() {
		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getPicid());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}