//[剧情]新手剧情package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class NewDialogueTempletConfig {	private static Map<Integer, NewDialogueTemplet> map;	private static List<Integer> keys;	private static List<NewDialogueTemplet> all;	static {		load();	}	public static List<NewDialogueTemplet> getAll() {		return new ArrayList<NewDialogueTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/NewDialogueConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, NewDialogueTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																NewDialogueTempletConfig.map = map;		NewDialogueTempletConfig.keys = keys;																List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		NewDialogueTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, NewDialogueTemplet> map) {		NewDialogueTemplet x = new NewDialogueTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setContent( e.attributeValue("content") );		NewDialogueTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static NewDialogueTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static NewDialogueTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static NewDialogueTemplet getMin() {		return get(getMinKey());	}	public static List<NewDialogueTemplet> findById(int value) {
		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NewDialogueTemplet> findByName(String value) {
		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NewDialogueTemplet> findByResid(int value) {
		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NewDialogueTemplet> findByContent(String value) {
		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}