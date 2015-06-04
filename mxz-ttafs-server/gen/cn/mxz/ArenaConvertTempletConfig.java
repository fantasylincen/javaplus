//[竞技场]物品兑换package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ArenaConvertTempletConfig {	private static Map<Integer, ArenaConvertTemplet> map;	private static List<Integer> keys;	private static List<ArenaConvertTemplet> all;	static {		load();	}	public static List<ArenaConvertTemplet> getAll() {		return new ArrayList<ArenaConvertTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ArenaConvertConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ArenaConvertTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ArenaConvertTempletConfig.map = map;		ArenaConvertTempletConfig.keys = keys;																List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ArenaConvertTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ArenaConvertTemplet> map) {		ArenaConvertTemplet x = new ArenaConvertTemplet();		x.setTypeId( Integer.decode( e.attributeValue("typeId").trim() ) );		x.setName( e.attributeValue("name") );		x.setOrder( Integer.decode( e.attributeValue("order").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setHonor( Integer.decode( e.attributeValue("honor").trim() ) );		x.setConvertnumber( Integer.decode( e.attributeValue("convertnumber").trim() ) );		x.setConvertMax( Integer.decode( e.attributeValue("convertMax").trim() ) );		x.setCoolTime( Integer.decode( e.attributeValue("coolTime").trim() ) );		x.setLv( Integer.decode( e.attributeValue("lv").trim() ) );		ArenaConvertTemplet remove = map.put(x.getTypeId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ArenaConvertTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ArenaConvertTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ArenaConvertTemplet getMin() {		return get(getMinKey());	}	public static List<ArenaConvertTemplet> findByTypeId(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getTypeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByName(String value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByOrder(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getOrder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByQuality(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByDescription(String value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByHonor(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getHonor(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByConvertnumber(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getConvertnumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByConvertMax(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getConvertMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByCoolTime(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getCoolTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArenaConvertTemplet> findByLv(int value) {
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByTypeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getTypeId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByOrder() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getOrder();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static int[] getArrayByHonor() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getHonor();
		}
		return all;
	}
	public static int[] getArrayByConvertnumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getConvertnumber();
		}
		return all;
	}
	public static int[] getArrayByConvertMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getConvertMax();
		}
		return all;
	}
	public static int[] getArrayByCoolTime() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getCoolTime();
		}
		return all;
	}
	public static int[] getArrayByLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getLv();
		}
		return all;
	}
	public static List<Integer> getListByTypeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getTypeId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByOrder() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getOrder());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<Integer> getListByHonor() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getHonor());
		}
		return all;
	}
	public static List<Integer> getListByConvertnumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getConvertnumber());
		}
		return all;
	}
	public static List<Integer> getListByConvertMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getConvertMax());
		}
		return all;
	}
	public static List<Integer> getListByCoolTime() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getCoolTime());
		}
		return all;
	}
	public static List<Integer> getListByLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getLv());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}