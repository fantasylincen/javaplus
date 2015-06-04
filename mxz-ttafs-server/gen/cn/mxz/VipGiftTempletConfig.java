//[VIP]特权礼包package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VipGiftTempletConfig {	private static Map<Integer, VipGiftTemplet> map;	private static List<Integer> keys;	private static List<VipGiftTemplet> all;	static {		load();	}	public static List<VipGiftTemplet> getAll() {		return new ArrayList<VipGiftTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VipGiftConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, VipGiftTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																VipGiftTempletConfig.map = map;		VipGiftTempletConfig.keys = keys;																List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		VipGiftTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, VipGiftTemplet> map) {		VipGiftTemplet x = new VipGiftTemplet();		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setGiftId( Integer.decode( e.attributeValue("giftId").trim() ) );		x.setName( e.attributeValue("name") );		x.setGoldOld( Integer.decode( e.attributeValue("goldOld").trim() ) );		x.setGoldNew( Integer.decode( e.attributeValue("goldNew").trim() ) );		VipGiftTemplet remove = map.put(x.getLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VipGiftTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VipGiftTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VipGiftTemplet getMin() {		return get(getMinKey());	}	public static List<VipGiftTemplet> findByLevel(int value) {
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipGiftTemplet> findByGiftId(int value) {
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGiftId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipGiftTemplet> findByName(String value) {
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipGiftTemplet> findByGoldOld(int value) {
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGoldOld(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipGiftTemplet> findByGoldNew(int value) {
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGoldNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByGiftId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGiftId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByGoldOld() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGoldOld();
		}
		return all;
	}
	public static int[] getArrayByGoldNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGoldNew();
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByGiftId() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGiftId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByGoldOld() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGoldOld());
		}
		return all;
	}
	public static List<Integer> getListByGoldNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGoldNew());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}