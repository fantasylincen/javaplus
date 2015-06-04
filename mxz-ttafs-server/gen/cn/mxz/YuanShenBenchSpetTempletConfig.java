//[元神]元神品阶刷新package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class YuanShenBenchSpetTempletConfig {	private static Map<Integer, YuanShenBenchSpetTemplet> map;	private static List<Integer> keys;	private static List<YuanShenBenchSpetTemplet> all;	static {		load();	}	public static List<YuanShenBenchSpetTemplet> getAll() {		return new ArrayList<YuanShenBenchSpetTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/YuanShenBenchSpetConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, YuanShenBenchSpetTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																YuanShenBenchSpetTempletConfig.map = map;		YuanShenBenchSpetTempletConfig.keys = keys;																List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		YuanShenBenchSpetTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, YuanShenBenchSpetTemplet> map) {		YuanShenBenchSpetTemplet x = new YuanShenBenchSpetTemplet();		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setWeightSans( Integer.decode( e.attributeValue("weightSans").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setMustNumber( Integer.decode( e.attributeValue("mustNumber").trim() ) );		YuanShenBenchSpetTemplet remove = map.put(x.getStep(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static YuanShenBenchSpetTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static YuanShenBenchSpetTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static YuanShenBenchSpetTemplet getMin() {		return get(getMinKey());	}	public static List<YuanShenBenchSpetTemplet> findByStep(int value) {
		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenBenchSpetTemplet> findByWeightSans(int value) {
		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getWeightSans(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenBenchSpetTemplet> findByExplain(String value) {
		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenBenchSpetTemplet> findByMustNumber(int value) {
		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getMustNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByWeightSans() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getWeightSans();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static int[] getArrayByMustNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getMustNumber();
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByWeightSans() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getWeightSans());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<Integer> getListByMustNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getMustNumber());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}