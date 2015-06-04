//[奇遇]机器人难度形象配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SnatchDifficultyTempletConfig {	private static Map<Integer, SnatchDifficultyTemplet> map;	private static List<Integer> keys;	private static List<SnatchDifficultyTemplet> all;	static {		load();	}	public static List<SnatchDifficultyTemplet> getAll() {		return new ArrayList<SnatchDifficultyTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SnatchDifficultyConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, SnatchDifficultyTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SnatchDifficultyTempletConfig.map = map;		SnatchDifficultyTempletConfig.keys = keys;																List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		SnatchDifficultyTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, SnatchDifficultyTemplet> map) {		SnatchDifficultyTemplet x = new SnatchDifficultyTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setStepScope( e.attributeValue("stepScope") );		x.setMin( Float.parseFloat( e.attributeValue("min").trim() ) );		x.setMax( Float.parseFloat( e.attributeValue("max").trim() ) );		SnatchDifficultyTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SnatchDifficultyTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SnatchDifficultyTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SnatchDifficultyTemplet getMin() {		return get(getMinKey());	}	public static List<SnatchDifficultyTemplet> findById(int value) {
		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchDifficultyTemplet> findByStepScope(String value) {
		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getStepScope(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchDifficultyTemplet> findByMin(float value) {
		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SnatchDifficultyTemplet> findByMax(float value) {
		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByStepScope() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getStepScope();
		}
		return all;
	}
	public static float[] getArrayByMin() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getMin();
		}
		return all;
	}
	public static float[] getArrayByMax() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByStepScope() {
		List<String> all = new ArrayList<String>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getStepScope());
		}
		return all;
	}
	public static List<Float> getListByMin() {
		List<Float> all = new ArrayList<Float>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getMin());
		}
		return all;
	}
	public static List<Float> getListByMax() {
		List<Float> all = new ArrayList<Float>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}