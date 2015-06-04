//[夺宝]夺宝package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SnatchTempletConfig {	private static Map<String, SnatchTemplet> map;	private static List<String> keys;	private static List<SnatchTemplet> all;	static {		load();	}	public static List<SnatchTemplet> getAll() {		return new ArrayList<SnatchTemplet>(all);	}	public static List<String> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SnatchConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<String, SnatchTemplet> map = Maps.newConcurrentMap();		List<String> keys = new ArrayList<String>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SnatchTempletConfig.map = map;		SnatchTempletConfig.keys = keys;																List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();		for(String c : keys) {			all.add(get(c));		}		SnatchTempletConfig.all = all;	}	private static void put(Element e, Map<String, SnatchTemplet> map) {		SnatchTemplet x = new SnatchTemplet();		x.setId( e.attributeValue("id") );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setObjectType( Integer.decode( e.attributeValue("objectType").trim() ) );		x.setFinder( Float.parseFloat( e.attributeValue("finder").trim() ) );		x.setDataNumberMin( Integer.decode( e.attributeValue("dataNumberMin").trim() ) );		x.setDataNumberMax( Integer.decode( e.attributeValue("dataNumberMax").trim() ) );		x.setPlunder( Integer.decode( e.attributeValue("plunder").trim() ) );		x.setText( e.attributeValue("text") );		x.setCodeId( Integer.decode( e.attributeValue("codeId").trim() ) );		SnatchTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SnatchTemplet get(String x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static String getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static String getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SnatchTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SnatchTemplet getMin() {		return get(getMinKey());	}	public static List<SnatchTemplet> findById(String value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByQuality(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByObjectType(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getObjectType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByFinder(float value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getFinder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByDataNumberMin(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getDataNumberMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByDataNumberMax(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getDataNumberMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByPlunder(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getPlunder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByText(String value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getText(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchTemplet> findByCodeId(int value) {
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getCodeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static String[] getArrayById() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByObjectType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getObjectType();
		}
		return all;
	}
	public static float[] getArrayByFinder() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getFinder();
		}
		return all;
	}
	public static int[] getArrayByDataNumberMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getDataNumberMin();
		}
		return all;
	}
	public static int[] getArrayByDataNumberMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getDataNumberMax();
		}
		return all;
	}
	public static int[] getArrayByPlunder() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getPlunder();
		}
		return all;
	}
	public static String[] getArrayByText() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getText();
		}
		return all;
	}
	public static int[] getArrayByCodeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getCodeId();
		}
		return all;
	}
	public static List<String> getListById() {
		List<String> all = new ArrayList<String>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByObjectType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getObjectType());
		}
		return all;
	}
	public static List<Float> getListByFinder() {
		List<Float> all = new ArrayList<Float>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getFinder());
		}
		return all;
	}
	public static List<Integer> getListByDataNumberMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getDataNumberMin());
		}
		return all;
	}
	public static List<Integer> getListByDataNumberMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getDataNumberMax());
		}
		return all;
	}
	public static List<Integer> getListByPlunder() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getPlunder());
		}
		return all;
	}
	public static List<String> getListByText() {
		List<String> all = new ArrayList<String>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getText());
		}
		return all;
	}
	public static List<Integer> getListByCodeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getCodeId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}