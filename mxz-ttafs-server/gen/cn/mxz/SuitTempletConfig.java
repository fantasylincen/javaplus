//[道具][装备]套装属性package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SuitTempletConfig {	private static Map<Integer, SuitTemplet> map;	private static List<Integer> keys;	private static List<SuitTemplet> all;	static {		load();	}	public static List<SuitTemplet> getAll() {		return new ArrayList<SuitTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SuitConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, SuitTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SuitTempletConfig.map = map;		SuitTempletConfig.keys = keys;																List<SuitTemplet> all = new ArrayList<SuitTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		SuitTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, SuitTemplet> map) {		SuitTemplet x = new SuitTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setSuitId( Integer.decode( e.attributeValue("suitId").trim() ) );		x.setRow( Integer.decode( e.attributeValue("row").trim() ) );		x.setBaseAdditionType( Integer.decode( e.attributeValue("baseAdditionType").trim() ) );		x.setAdditionValue( Integer.decode( e.attributeValue("additionValue").trim() ) );		SuitTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SuitTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SuitTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SuitTemplet getMin() {		return get(getMinKey());	}	public static List<SuitTemplet> findById(int value) {
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SuitTemplet> findBySuitId(int value) {
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getSuitId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SuitTemplet> findByRow(int value) {
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getRow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SuitTemplet> findByBaseAdditionType(int value) {
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getBaseAdditionType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SuitTemplet> findByAdditionValue(int value) {
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getAdditionValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayBySuitId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getSuitId();
		}
		return all;
	}
	public static int[] getArrayByRow() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getRow();
		}
		return all;
	}
	public static int[] getArrayByBaseAdditionType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getBaseAdditionType();
		}
		return all;
	}
	public static int[] getArrayByAdditionValue() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getAdditionValue();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListBySuitId() {
		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getSuitId());
		}
		return all;
	}
	public static List<Integer> getListByRow() {
		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getRow());
		}
		return all;
	}
	public static List<Integer> getListByBaseAdditionType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getBaseAdditionType());
		}
		return all;
	}
	public static List<Integer> getListByAdditionValue() {
		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getAdditionValue());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}