//[恩怨系统消息]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GrudgeSystemTempletConfig {	private static Map<Integer, GrudgeSystemTemplet> map;	private static List<Integer> keys;	private static List<GrudgeSystemTemplet> all;	static {		load();	}	public static List<GrudgeSystemTemplet> getAll() {		return new ArrayList<GrudgeSystemTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GrudgeSystemConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, GrudgeSystemTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																GrudgeSystemTempletConfig.map = map;		GrudgeSystemTempletConfig.keys = keys;																List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		GrudgeSystemTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, GrudgeSystemTemplet> map) {		GrudgeSystemTemplet x = new GrudgeSystemTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setTypeName( e.attributeValue("typeName") );		x.setContent( e.attributeValue("content") );		x.setModuleName( e.attributeValue("moduleName") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setUrl( e.attributeValue("url") );		GrudgeSystemTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GrudgeSystemTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GrudgeSystemTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GrudgeSystemTemplet getMin() {		return get(getMinKey());	}	public static List<GrudgeSystemTemplet> findById(int value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByType(int value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByTypeName(String value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getTypeName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByContent(String value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByModuleName(String value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getModuleName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByResid(int value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByFormat(String value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GrudgeSystemTemplet> findByUrl(String value) {
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByTypeName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getTypeName();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static String[] getArrayByModuleName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getModuleName();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByTypeName() {
		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getTypeName());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	public static List<String> getListByModuleName() {
		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getModuleName());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}