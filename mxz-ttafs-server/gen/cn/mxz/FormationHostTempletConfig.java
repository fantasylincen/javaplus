//[阵法]被升级阵法概率加成package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FormationHostTempletConfig {	private static Map<String, FormationHostTemplet> map;	private static List<String> keys;	private static List<FormationHostTemplet> all;	static {		load();	}	public static List<FormationHostTemplet> getAll() {		return new ArrayList<FormationHostTemplet>(all);	}	public static List<String> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FormationHostConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<String, FormationHostTemplet> map = Maps.newConcurrentMap();		List<String> keys = new ArrayList<String>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FormationHostTempletConfig.map = map;		FormationHostTempletConfig.keys = keys;																List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();		for(String c : keys) {			all.add(get(c));		}		FormationHostTempletConfig.all = all;	}	private static void put(Element e, Map<String, FormationHostTemplet> map) {		FormationHostTemplet x = new FormationHostTemplet();		x.setSpetLv( e.attributeValue("spetLv") );		x.setFormationSpet( Integer.decode( e.attributeValue("formationSpet").trim() ) );		x.setFormationLv( Integer.decode( e.attributeValue("formationLv").trim() ) );		x.setHostPro( Float.parseFloat( e.attributeValue("hostPro").trim() ) );		x.setSumPro( Float.parseFloat( e.attributeValue("sumPro").trim() ) );		x.setCashNeed( Integer.decode( e.attributeValue("cashNeed").trim() ) );		FormationHostTemplet remove = map.put(x.getSpetLv(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FormationHostTemplet get(String x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static String getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static String getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FormationHostTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FormationHostTemplet getMin() {		return get(getMinKey());	}	public static List<FormationHostTemplet> findBySpetLv(String value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getSpetLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationHostTemplet> findByFormationSpet(int value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getFormationSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationHostTemplet> findByFormationLv(int value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getFormationLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationHostTemplet> findByHostPro(float value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getHostPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationHostTemplet> findBySumPro(float value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getSumPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationHostTemplet> findByCashNeed(int value) {
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getCashNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static String[] getArrayBySpetLv() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getSpetLv();
		}
		return all;
	}
	public static int[] getArrayByFormationSpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getFormationSpet();
		}
		return all;
	}
	public static int[] getArrayByFormationLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getFormationLv();
		}
		return all;
	}
	public static float[] getArrayByHostPro() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getHostPro();
		}
		return all;
	}
	public static float[] getArrayBySumPro() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getSumPro();
		}
		return all;
	}
	public static int[] getArrayByCashNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getCashNeed();
		}
		return all;
	}
	public static List<String> getListBySpetLv() {
		List<String> all = new ArrayList<String>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getSpetLv());
		}
		return all;
	}
	public static List<Integer> getListByFormationSpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getFormationSpet());
		}
		return all;
	}
	public static List<Integer> getListByFormationLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getFormationLv());
		}
		return all;
	}
	public static List<Float> getListByHostPro() {
		List<Float> all = new ArrayList<Float>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getHostPro());
		}
		return all;
	}
	public static List<Float> getListBySumPro() {
		List<Float> all = new ArrayList<Float>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getSumPro());
		}
		return all;
	}
	public static List<Integer> getListByCashNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getCashNeed());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}