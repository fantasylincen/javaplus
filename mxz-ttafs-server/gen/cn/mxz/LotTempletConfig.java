//[修练场]关注缘分加成暂时未用package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class LotTempletConfig {	private static Map<Integer, LotTemplet> map;	private static List<Integer> keys;	private static List<LotTemplet> all;	static {		load();	}	public static List<LotTemplet> getAll() {		return new ArrayList<LotTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/LotConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, LotTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																LotTempletConfig.map = map;		LotTempletConfig.keys = keys;																List<LotTemplet> all = new ArrayList<LotTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		LotTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, LotTemplet> map) {		LotTemplet x = new LotTemplet();		x.setAttention( Integer.decode( e.attributeValue("attention").trim() ) );		x.setAddition( Float.parseFloat( e.attributeValue("addition").trim() ) );		LotTemplet remove = map.put(x.getAttention(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static LotTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static LotTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static LotTemplet getMin() {		return get(getMinKey());	}	public static List<LotTemplet> findByAttention(int value) {
		List<LotTemplet> all = new ArrayList<LotTemplet>();
		for (LotTemplet f : getAll()) {
			if(equals(f.getAttention(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<LotTemplet> findByAddition(float value) {
		List<LotTemplet> all = new ArrayList<LotTemplet>();
		for (LotTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByAttention() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LotTemplet f = get(keys.get(i));
			all[i] = f.getAttention();
		}
		return all;
	}
	public static float[] getArrayByAddition() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LotTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}
	public static List<Integer> getListByAttention() {
		List<Integer> all = new ArrayList<Integer>();
		for (LotTemplet f : getAll()) {
			all.add(f.getAttention());
		}
		return all;
	}
	public static List<Float> getListByAddition() {
		List<Float> all = new ArrayList<Float>();
		for (LotTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}