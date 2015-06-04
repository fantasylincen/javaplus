//[奇遇]33[boss]buff属性package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BossBuffTempletConfig {	private static Map<Integer, BossBuffTemplet> map;	private static List<Integer> keys;	private static List<BossBuffTemplet> all;	static {		load();	}	public static List<BossBuffTemplet> getAll() {		return new ArrayList<BossBuffTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BossBuffConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BossBuffTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BossBuffTempletConfig.map = map;		BossBuffTempletConfig.keys = keys;																List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BossBuffTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BossBuffTemplet> map) {		BossBuffTemplet x = new BossBuffTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setFrontPar( Float.parseFloat( e.attributeValue("frontPar").trim() ) );		x.setFrontParMax( Float.parseFloat( e.attributeValue("frontParMax").trim() ) );		x.setSycee( Integer.decode( e.attributeValue("sycee").trim() ) );		x.setAddSycee( Float.parseFloat( e.attributeValue("addSycee").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		BossBuffTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BossBuffTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BossBuffTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BossBuffTemplet getMin() {		return get(getMinKey());	}	public static List<BossBuffTemplet> findById(int value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findByName(String value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findByFrontPar(float value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getFrontPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findByFrontParMax(float value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getFrontParMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findBySycee(int value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getSycee(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findByAddSycee(float value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getAddSycee(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBuffTemplet> findByNumber(int value) {
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static float[] getArrayByFrontPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontPar();
		}
		return all;
	}
	public static float[] getArrayByFrontParMax() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontParMax();
		}
		return all;
	}
	public static int[] getArrayBySycee() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getSycee();
		}
		return all;
	}
	public static float[] getArrayByAddSycee() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getAddSycee();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Float> getListByFrontPar() {
		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getFrontPar());
		}
		return all;
	}
	public static List<Float> getListByFrontParMax() {
		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getFrontParMax());
		}
		return all;
	}
	public static List<Integer> getListBySycee() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getSycee());
		}
		return all;
	}
	public static List<Float> getListByAddSycee() {
		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getAddSycee());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}