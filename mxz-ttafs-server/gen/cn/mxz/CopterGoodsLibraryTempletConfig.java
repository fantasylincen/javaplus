//[奇遇]35[渡天劫]物品奖励库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterGoodsLibraryTempletConfig {	private static Map<Integer, CopterGoodsLibraryTemplet> map;	private static List<Integer> keys;	private static List<CopterGoodsLibraryTemplet> all;	static {		load();	}	public static List<CopterGoodsLibraryTemplet> getAll() {		return new ArrayList<CopterGoodsLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterGoodsLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterGoodsLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterGoodsLibraryTempletConfig.map = map;		CopterGoodsLibraryTempletConfig.keys = keys;																List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterGoodsLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterGoodsLibraryTemplet> map) {		CopterGoodsLibraryTemplet x = new CopterGoodsLibraryTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setPropNeame( e.attributeValue("propNeame") );		x.setAwards( e.attributeValue("awards") );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		x.setSzzy( Float.parseFloat( e.attributeValue("szzy").trim() ) );		CopterGoodsLibraryTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterGoodsLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterGoodsLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterGoodsLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<CopterGoodsLibraryTemplet> findById(int value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterGoodsLibraryTemplet> findByPropNeame(String value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterGoodsLibraryTemplet> findByAwards(String value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterGoodsLibraryTemplet> findByWeight(int value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterGoodsLibraryTemplet> findByModulesId(int value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterGoodsLibraryTemplet> findBySzzy(float value) {
		List<CopterGoodsLibraryTemplet> all = new ArrayList<CopterGoodsLibraryTemplet>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByPropNeame() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static float[] getArrayBySzzy() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterGoodsLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByPropNeame() {
		List<String> all = new ArrayList<String>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	public static List<Float> getListBySzzy() {
		List<Float> all = new ArrayList<Float>();
		for (CopterGoodsLibraryTemplet f : getAll()) {
			all.add(f.getSzzy());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}