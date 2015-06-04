//[属性]属性id表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class NatureTempletConfig {	private static Map<Integer, NatureTemplet> map;	private static List<Integer> keys;	private static List<NatureTemplet> all;	static {		load();	}	public static List<NatureTemplet> getAll() {		return new ArrayList<NatureTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/NatureConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, NatureTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																NatureTempletConfig.map = map;		NatureTempletConfig.keys = keys;																List<NatureTemplet> all = new ArrayList<NatureTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		NatureTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, NatureTemplet> map) {		NatureTemplet x = new NatureTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNatureName( e.attributeValue("natureName") );		x.setEquipmentPart( e.attributeValue("equipmentPart") );		NatureTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static NatureTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static NatureTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static NatureTemplet getMin() {		return get(getMinKey());	}	public static List<NatureTemplet> findById(int value) {
		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NatureTemplet> findByNatureName(String value) {
		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getNatureName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NatureTemplet> findByEquipmentPart(String value) {
		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getEquipmentPart(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByNatureName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getNatureName();
		}
		return all;
	}
	public static String[] getArrayByEquipmentPart() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentPart();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByNatureName() {
		List<String> all = new ArrayList<String>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getNatureName());
		}
		return all;
	}
	public static List<String> getListByEquipmentPart() {
		List<String> all = new ArrayList<String>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getEquipmentPart());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}