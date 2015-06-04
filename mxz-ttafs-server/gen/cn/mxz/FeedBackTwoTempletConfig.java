//[礼包]充值回馈2package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FeedBackTwoTempletConfig {	private static Map<Integer, FeedBackTwoTemplet> map;	private static List<Integer> keys;	private static List<FeedBackTwoTemplet> all;	static {		load();	}	public static List<FeedBackTwoTemplet> getAll() {		return new ArrayList<FeedBackTwoTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FeedBackTwoConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FeedBackTwoTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FeedBackTwoTempletConfig.map = map;		FeedBackTwoTempletConfig.keys = keys;																List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FeedBackTwoTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FeedBackTwoTemplet> map) {		FeedBackTwoTemplet x = new FeedBackTwoTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setBagid( Integer.decode( e.attributeValue("bagid").trim() ) );		x.setBagName( e.attributeValue("bagName") );		x.setMoney( Integer.decode( e.attributeValue("money").trim() ) );		x.setAward( e.attributeValue("award") );		x.setLimit( Integer.decode( e.attributeValue("limit").trim() ) );		FeedBackTwoTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FeedBackTwoTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FeedBackTwoTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FeedBackTwoTemplet getMin() {		return get(getMinKey());	}	public static List<FeedBackTwoTemplet> findById(int value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTwoTemplet> findByBagid(int value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getBagid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTwoTemplet> findByBagName(String value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getBagName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTwoTemplet> findByMoney(int value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getMoney(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTwoTemplet> findByAward(String value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FeedBackTwoTemplet> findByLimit(int value) {
		List<FeedBackTwoTemplet> all = new ArrayList<FeedBackTwoTemplet>();
		for (FeedBackTwoTemplet f : getAll()) {
			if(equals(f.getLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByBagid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getBagid();
		}
		return all;
	}
	public static String[] getArrayByBagName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getBagName();
		}
		return all;
	}
	public static int[] getArrayByMoney() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getMoney();
		}
		return all;
	}
	public static String[] getArrayByAward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}
	public static int[] getArrayByLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTwoTemplet f = get(keys.get(i));
			all[i] = f.getLimit();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByBagid() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getBagid());
		}
		return all;
	}
	public static List<String> getListByBagName() {
		List<String> all = new ArrayList<String>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getBagName());
		}
		return all;
	}
	public static List<Integer> getListByMoney() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getMoney());
		}
		return all;
	}
	public static List<String> getListByAward() {
		List<String> all = new ArrayList<String>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}
	public static List<Integer> getListByLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTwoTemplet f : getAll()) {
			all.add(f.getLimit());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}