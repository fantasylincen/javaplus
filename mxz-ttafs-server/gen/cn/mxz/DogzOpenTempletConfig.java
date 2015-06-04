//[神兽]神兽开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DogzOpenTempletConfig {	private static Map<Integer, DogzOpenTemplet> map;	private static List<Integer> keys;	private static List<DogzOpenTemplet> all;	static {		load();	}	public static List<DogzOpenTemplet> getAll() {		return new ArrayList<DogzOpenTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DogzOpenConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DogzOpenTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DogzOpenTempletConfig.map = map;		DogzOpenTempletConfig.keys = keys;																List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DogzOpenTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DogzOpenTemplet> map) {		DogzOpenTemplet x = new DogzOpenTemplet();		x.setCount( Integer.decode( e.attributeValue("count").trim() ) );		x.setUserLevel( Integer.decode( e.attributeValue("userLevel").trim() ) );		x.setDogzLevel( Integer.decode( e.attributeValue("dogzLevel").trim() ) );		x.setComment( e.attributeValue("comment") );		DogzOpenTemplet remove = map.put(x.getCount(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DogzOpenTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DogzOpenTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DogzOpenTemplet getMin() {		return get(getMinKey());	}	public static List<DogzOpenTemplet> findByCount(int value) {
		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getCount(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzOpenTemplet> findByUserLevel(int value) {
		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getUserLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzOpenTemplet> findByDogzLevel(int value) {
		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getDogzLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzOpenTemplet> findByComment(String value) {
		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByCount() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getCount();
		}
		return all;
	}
	public static int[] getArrayByUserLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getUserLevel();
		}
		return all;
	}
	public static int[] getArrayByDogzLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getDogzLevel();
		}
		return all;
	}
	public static String[] getArrayByComment() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}
	public static List<Integer> getListByCount() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getCount());
		}
		return all;
	}
	public static List<Integer> getListByUserLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getUserLevel());
		}
		return all;
	}
	public static List<Integer> getListByDogzLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getDogzLevel());
		}
		return all;
	}
	public static List<String> getListByComment() {
		List<String> all = new ArrayList<String>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}