//[奇遇]50[神秘商店]随机物品库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AgainstGoodsLibraryTempletConfig {	private static Map<Integer, AgainstGoodsLibraryTemplet> map;	private static List<Integer> keys;	private static List<AgainstGoodsLibraryTemplet> all;	static {		load();	}	public static List<AgainstGoodsLibraryTemplet> getAll() {		return new ArrayList<AgainstGoodsLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AgainstGoodsLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, AgainstGoodsLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																AgainstGoodsLibraryTempletConfig.map = map;		AgainstGoodsLibraryTempletConfig.keys = keys;																List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		AgainstGoodsLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, AgainstGoodsLibraryTemplet> map) {		AgainstGoodsLibraryTemplet x = new AgainstGoodsLibraryTemplet();		x.setIdfen( Integer.decode( e.attributeValue("idfen").trim() ) );		x.setReward( e.attributeValue("reward") );		x.setPropNeame1( e.attributeValue("propNeame1") );		x.setPropNeame( e.attributeValue("propNeame") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		x.setQuota( Integer.decode( e.attributeValue("quota").trim() ) );		x.setConsume( e.attributeValue("consume") );		AgainstGoodsLibraryTemplet remove = map.put(x.getIdfen(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AgainstGoodsLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AgainstGoodsLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AgainstGoodsLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<AgainstGoodsLibraryTemplet> findByIdfen(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getIdfen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByReward(String value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByPropNeame1(String value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByPropNeame(String value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByType(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByStep(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByWeight(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByNumber(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByQuota(int value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getQuota(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AgainstGoodsLibraryTemplet> findByConsume(String value) {
		List<AgainstGoodsLibraryTemplet> all = new ArrayList<AgainstGoodsLibraryTemplet>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getConsume(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByIdfen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getIdfen();
		}
		return all;
	}
	public static String[] getArrayByReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getReward();
		}
		return all;
	}
	public static String[] getArrayByPropNeame1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame1();
		}
		return all;
	}
	public static String[] getArrayByPropNeame() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByQuota() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getQuota();
		}
		return all;
	}
	public static String[] getArrayByConsume() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AgainstGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getConsume();
		}
		return all;
	}
	public static List<Integer> getListByIdfen() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getIdfen());
		}
		return all;
	}
	public static List<String> getListByReward() {
		List<String> all = new ArrayList<String>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getReward());
		}
		return all;
	}
	public static List<String> getListByPropNeame1() {
		List<String> all = new ArrayList<String>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame1());
		}
		return all;
	}
	public static List<String> getListByPropNeame() {
		List<String> all = new ArrayList<String>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByQuota() {
		List<Integer> all = new ArrayList<Integer>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getQuota());
		}
		return all;
	}
	public static List<String> getListByConsume() {
		List<String> all = new ArrayList<String>();
		for (AgainstGoodsLibraryTemplet f : getAll()) {
			all.add(f.getConsume());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}