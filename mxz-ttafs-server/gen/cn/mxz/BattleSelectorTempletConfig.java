//[战斗]单体攻击package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BattleSelectorTempletConfig {	private static Map<Integer, BattleSelectorTemplet> map;	private static List<Integer> keys;	private static List<BattleSelectorTemplet> all;	static {		load();	}	public static List<BattleSelectorTemplet> getAll() {		return new ArrayList<BattleSelectorTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BattleSelectorConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BattleSelectorTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BattleSelectorTempletConfig.map = map;		BattleSelectorTempletConfig.keys = keys;																List<BattleSelectorTemplet> all = new ArrayList<BattleSelectorTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BattleSelectorTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BattleSelectorTemplet> map) {		BattleSelectorTemplet x = new BattleSelectorTemplet();		x.setPosition( Integer.decode( e.attributeValue("position").trim() ) );		x.setDst( e.attributeValue("dst") );		BattleSelectorTemplet remove = map.put(x.getPosition(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BattleSelectorTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BattleSelectorTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BattleSelectorTemplet getMin() {		return get(getMinKey());	}	public static List<BattleSelectorTemplet> findByPosition(int value) {
		List<BattleSelectorTemplet> all = new ArrayList<BattleSelectorTemplet>();
		for (BattleSelectorTemplet f : getAll()) {
			if(equals(f.getPosition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BattleSelectorTemplet> findByDst(String value) {
		List<BattleSelectorTemplet> all = new ArrayList<BattleSelectorTemplet>();
		for (BattleSelectorTemplet f : getAll()) {
			if(equals(f.getDst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByPosition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BattleSelectorTemplet f = get(keys.get(i));
			all[i] = f.getPosition();
		}
		return all;
	}
	public static String[] getArrayByDst() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BattleSelectorTemplet f = get(keys.get(i));
			all[i] = f.getDst();
		}
		return all;
	}
	public static List<Integer> getListByPosition() {
		List<Integer> all = new ArrayList<Integer>();
		for (BattleSelectorTemplet f : getAll()) {
			all.add(f.getPosition());
		}
		return all;
	}
	public static List<String> getListByDst() {
		List<String> all = new ArrayList<String>();
		for (BattleSelectorTemplet f : getAll()) {
			all.add(f.getDst());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}