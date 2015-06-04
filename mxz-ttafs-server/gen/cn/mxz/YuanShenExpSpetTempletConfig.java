//[元神]元神品质经验加成表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class YuanShenExpSpetTempletConfig {	private static Map<Integer, YuanShenExpSpetTemplet> map;	private static List<Integer> keys;	private static List<YuanShenExpSpetTemplet> all;	static {		load();	}	public static List<YuanShenExpSpetTemplet> getAll() {		return new ArrayList<YuanShenExpSpetTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/YuanShenExpSpetConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, YuanShenExpSpetTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																YuanShenExpSpetTempletConfig.map = map;		YuanShenExpSpetTempletConfig.keys = keys;																List<YuanShenExpSpetTemplet> all = new ArrayList<YuanShenExpSpetTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		YuanShenExpSpetTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, YuanShenExpSpetTemplet> map) {		YuanShenExpSpetTemplet x = new YuanShenExpSpetTemplet();		x.setSpet( Integer.decode( e.attributeValue("spet").trim() ) );		x.setSpetAdd( Float.parseFloat( e.attributeValue("spetAdd").trim() ) );		YuanShenExpSpetTemplet remove = map.put(x.getSpet(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static YuanShenExpSpetTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static YuanShenExpSpetTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static YuanShenExpSpetTemplet getMin() {		return get(getMinKey());	}	public static List<YuanShenExpSpetTemplet> findBySpet(int value) {
		List<YuanShenExpSpetTemplet> all = new ArrayList<YuanShenExpSpetTemplet>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenExpSpetTemplet> findBySpetAdd(float value) {
		List<YuanShenExpSpetTemplet> all = new ArrayList<YuanShenExpSpetTemplet>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			if(equals(f.getSpetAdd(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayBySpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}
	public static float[] getArrayBySpetAdd() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpetAdd();
		}
		return all;
	}
	public static List<Integer> getListBySpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}
	public static List<Float> getListBySpetAdd() {
		List<Float> all = new ArrayList<Float>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			all.add(f.getSpetAdd());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}