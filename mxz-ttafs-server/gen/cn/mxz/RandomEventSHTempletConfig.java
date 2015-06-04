//[奇遇]15[关卡][神魔]兽魂参与奖励表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomEventSHTempletConfig {	private static Map<Integer, RandomEventSHTemplet> map;	private static List<Integer> keys;	private static List<RandomEventSHTemplet> all;	static {		load();	}	public static List<RandomEventSHTemplet> getAll() {		return new ArrayList<RandomEventSHTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventSHConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomEventSHTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomEventSHTempletConfig.map = map;		RandomEventSHTempletConfig.keys = keys;																List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomEventSHTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomEventSHTemplet> map) {		RandomEventSHTemplet x = new RandomEventSHTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNum( Integer.decode( e.attributeValue("num").trim() ) );		x.setShouHun( Integer.decode( e.attributeValue("shouHun").trim() ) );		RandomEventSHTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventSHTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventSHTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventSHTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventSHTemplet> findById(int value) {
		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventSHTemplet> findByNum(int value) {
		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getNum(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventSHTemplet> findByShouHun(int value) {
		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getShouHun(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByNum() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getNum();
		}
		return all;
	}
	public static int[] getArrayByShouHun() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getShouHun();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByNum() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getNum());
		}
		return all;
	}
	public static List<Integer> getListByShouHun() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getShouHun());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}