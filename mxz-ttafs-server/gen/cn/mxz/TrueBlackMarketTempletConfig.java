//[奇遇]黑市package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class TrueBlackMarketTempletConfig {	private static Map<Integer, TrueBlackMarketTemplet> map;	private static List<Integer> keys;	private static List<TrueBlackMarketTemplet> all;	static {		load();	}	public static List<TrueBlackMarketTemplet> getAll() {		return new ArrayList<TrueBlackMarketTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/TrueBlackMarketConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, TrueBlackMarketTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																TrueBlackMarketTempletConfig.map = map;		TrueBlackMarketTempletConfig.keys = keys;																List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		TrueBlackMarketTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, TrueBlackMarketTemplet> map) {		TrueBlackMarketTemplet x = new TrueBlackMarketTemplet();		x.setTypeId( Integer.decode( e.attributeValue("typeId").trim() ) );		x.setName( e.attributeValue("name") );		x.setOrder( Integer.decode( e.attributeValue("order").trim() ) );		x.setPresentDBIndex( Integer.decode( e.attributeValue("presentDBIndex").trim() ) );		x.setState( Integer.decode( e.attributeValue("state").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setHot( Integer.decode( e.attributeValue("hot").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setSpar( e.attributeValue("spar") );		TrueBlackMarketTemplet remove = map.put(x.getTypeId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static TrueBlackMarketTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static TrueBlackMarketTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static TrueBlackMarketTemplet getMin() {		return get(getMinKey());	}	public static List<TrueBlackMarketTemplet> findByTypeId(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getTypeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByName(String value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByOrder(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getOrder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByPresentDBIndex(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getPresentDBIndex(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByState(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getState(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByQuality(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByMax(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByHot(int value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getHot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findByDescription(String value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<TrueBlackMarketTemplet> findBySpar(String value) {
		List<TrueBlackMarketTemplet> all = new ArrayList<TrueBlackMarketTemplet>();
		for (TrueBlackMarketTemplet f : getAll()) {
			if(equals(f.getSpar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByTypeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getTypeId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByOrder() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getOrder();
		}
		return all;
	}
	public static int[] getArrayByPresentDBIndex() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getPresentDBIndex();
		}
		return all;
	}
	public static int[] getArrayByState() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getState();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static int[] getArrayByHot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getHot();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayBySpar() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			TrueBlackMarketTemplet f = get(keys.get(i));
			all[i] = f.getSpar();
		}
		return all;
	}
	public static List<Integer> getListByTypeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getTypeId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByOrder() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getOrder());
		}
		return all;
	}
	public static List<Integer> getListByPresentDBIndex() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getPresentDBIndex());
		}
		return all;
	}
	public static List<Integer> getListByState() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getState());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<Integer> getListByHot() {
		List<Integer> all = new ArrayList<Integer>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getHot());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListBySpar() {
		List<String> all = new ArrayList<String>();
		for (TrueBlackMarketTemplet f : getAll()) {
			all.add(f.getSpar());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}