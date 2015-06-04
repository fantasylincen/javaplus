//[关卡]随机事件剧本package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomBookTempletConfig {	private static Map<Integer, RandomBookTemplet> map;	private static List<Integer> keys;	private static List<RandomBookTemplet> all;	static {		load();	}	public static List<RandomBookTemplet> getAll() {		return new ArrayList<RandomBookTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomBookConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomBookTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomBookTempletConfig.map = map;		RandomBookTempletConfig.keys = keys;																List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomBookTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomBookTemplet> map) {		RandomBookTemplet x = new RandomBookTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setStoryId( Integer.decode( e.attributeValue("storyId").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setUserId( Integer.decode( e.attributeValue("userId").trim() ) );		x.setName( e.attributeValue("name") );		x.setIsEnd( Integer.decode( e.attributeValue("isEnd").trim() ) );		x.setPosi( Integer.decode( e.attributeValue("posi").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setContent( e.attributeValue("content") );		RandomBookTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomBookTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomBookTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomBookTemplet getMin() {		return get(getMinKey());	}	public static List<RandomBookTemplet> findById(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByStoryId(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getStoryId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByType(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByUserId(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getUserId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByName(String value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByIsEnd(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getIsEnd(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByPosi(int value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getPosi(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByUrl(String value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomBookTemplet> findByContent(String value) {
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByStoryId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getStoryId();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByUserId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getUserId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByIsEnd() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getIsEnd();
		}
		return all;
	}
	public static int[] getArrayByPosi() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getPosi();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByStoryId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getStoryId());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByUserId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getUserId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByIsEnd() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getIsEnd());
		}
		return all;
	}
	public static List<Integer> getListByPosi() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getPosi());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}