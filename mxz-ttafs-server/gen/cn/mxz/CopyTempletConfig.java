//[关卡][2]副本章节(一个副本里有多个剧情)package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopyTempletConfig {	private static Map<Integer, CopyTemplet> map;	private static List<Integer> keys;	private static List<CopyTemplet> all;	static {		load();	}	public static List<CopyTemplet> getAll() {		return new ArrayList<CopyTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopyConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopyTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopyTempletConfig.map = map;		CopyTempletConfig.keys = keys;																List<CopyTemplet> all = new ArrayList<CopyTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopyTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopyTemplet> map) {		CopyTemplet x = new CopyTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setMissionId( Integer.decode( e.attributeValue("missionId").trim() ) );		x.setName( e.attributeValue("name") );		x.setIsNew( Integer.decode( e.attributeValue("isNew").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setFormat( e.attributeValue("format") );		x.setPictype( Integer.decode( e.attributeValue("pictype").trim() ) );		x.setAward1( e.attributeValue("award1") );		x.setAward2( e.attributeValue("award2") );		x.setAward3( e.attributeValue("award3") );		CopyTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopyTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopyTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopyTemplet getMin() {		return get(getMinKey());	}	public static List<CopyTemplet> findById(int value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByMissionId(int value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getMissionId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByName(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByIsNew(int value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getIsNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByDescription(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByFormat(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByPictype(int value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByAward1(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByAward2(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopyTemplet> findByAward3(String value) {
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByMissionId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getMissionId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByIsNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getIsNew();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByPictype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}
	public static String[] getArrayByAward1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward1();
		}
		return all;
	}
	public static String[] getArrayByAward2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward2();
		}
		return all;
	}
	public static String[] getArrayByAward3() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward3();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByMissionId() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getMissionId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByIsNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getIsNew());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByPictype() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
	public static List<String> getListByAward1() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward1());
		}
		return all;
	}
	public static List<String> getListByAward2() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward2());
		}
		return all;
	}
	public static List<String> getListByAward3() {
		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward3());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}