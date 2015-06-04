//[礼包]充值回馈package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FeedBackTempletConfig {	private static Map<Integer, FeedBackTemplet> map;	private static List<Integer> keys;	private static List<FeedBackTemplet> all;	static {		load();	}	public static List<FeedBackTemplet> getAll() {		return new ArrayList<FeedBackTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FeedBackConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FeedBackTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FeedBackTempletConfig.map = map;		FeedBackTempletConfig.keys = keys;																List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FeedBackTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FeedBackTemplet> map) {		FeedBackTemplet x = new FeedBackTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setBagid( Integer.decode( e.attributeValue("bagid").trim() ) );		x.setBagName( e.attributeValue("bagName") );		x.setMoney( Integer.decode( e.attributeValue("money").trim() ) );		x.setLimit( Integer.decode( e.attributeValue("limit").trim() ) );		FeedBackTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FeedBackTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FeedBackTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FeedBackTemplet getMin() {		return get(getMinKey());	}	public static List<FeedBackTemplet> findById(int value) {
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTemplet> findByBagid(int value) {
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getBagid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTemplet> findByBagName(String value) {
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getBagName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTemplet> findByMoney(int value) {
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getMoney(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTemplet> findByLimit(int value) {
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByBagid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getBagid();
		}
		return all;
	}
	public static String[] getArrayByBagName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getBagName();
		}
		return all;
	}
	public static int[] getArrayByMoney() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getMoney();
		}
		return all;
	}
	public static int[] getArrayByLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getLimit();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByBagid() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getBagid());
		}
		return all;
	}
	public static List<String> getListByBagName() {
		List<String> all = new ArrayList<String>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getBagName());
		}
		return all;
	}
	public static List<Integer> getListByMoney() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getMoney());
		}
		return all;
	}
	public static List<Integer> getListByLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getLimit());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}