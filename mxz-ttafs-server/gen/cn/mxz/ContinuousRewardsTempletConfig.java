//[奖励]连续登录翻牌次数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ContinuousRewardsTempletConfig {	private static Map<Integer, ContinuousRewardsTemplet> map;	private static List<Integer> keys;	private static List<ContinuousRewardsTemplet> all;	static {		load();	}	public static List<ContinuousRewardsTemplet> getAll() {		return new ArrayList<ContinuousRewardsTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ContinuousRewardsConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ContinuousRewardsTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ContinuousRewardsTempletConfig.map = map;		ContinuousRewardsTempletConfig.keys = keys;																List<ContinuousRewardsTemplet> all = new ArrayList<ContinuousRewardsTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ContinuousRewardsTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ContinuousRewardsTemplet> map) {		ContinuousRewardsTemplet x = new ContinuousRewardsTemplet();		x.setEnterDay( Integer.decode( e.attributeValue("enterDay").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		ContinuousRewardsTemplet remove = map.put(x.getEnterDay(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ContinuousRewardsTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ContinuousRewardsTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ContinuousRewardsTemplet getMin() {		return get(getMinKey());	}	public static List<ContinuousRewardsTemplet> findByEnterDay(int value) {
		List<ContinuousRewardsTemplet> all = new ArrayList<ContinuousRewardsTemplet>();
		for (ContinuousRewardsTemplet f : getAll()) {
			if(equals(f.getEnterDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ContinuousRewardsTemplet> findByNumber(int value) {
		List<ContinuousRewardsTemplet> all = new ArrayList<ContinuousRewardsTemplet>();
		for (ContinuousRewardsTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByEnterDay() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ContinuousRewardsTemplet f = get(keys.get(i));
			all[i] = f.getEnterDay();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ContinuousRewardsTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static List<Integer> getListByEnterDay() {
		List<Integer> all = new ArrayList<Integer>();
		for (ContinuousRewardsTemplet f : getAll()) {
			all.add(f.getEnterDay());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (ContinuousRewardsTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}