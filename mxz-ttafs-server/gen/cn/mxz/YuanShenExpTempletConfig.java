//[元神]元神等级经验表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class YuanShenExpTempletConfig {	private static Map<Integer, YuanShenExpTemplet> map;	private static List<Integer> keys;	private static List<YuanShenExpTemplet> all;	static {		load();	}	public static List<YuanShenExpTemplet> getAll() {		return new ArrayList<YuanShenExpTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/YuanShenExpConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, YuanShenExpTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																YuanShenExpTempletConfig.map = map;		YuanShenExpTempletConfig.keys = keys;																List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		YuanShenExpTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, YuanShenExpTemplet> map) {		YuanShenExpTemplet x = new YuanShenExpTemplet();		x.setShadowLevel( Integer.decode( e.attributeValue("shadowLevel").trim() ) );		x.setShadowExp( Integer.decode( e.attributeValue("shadowExp").trim() ) );		x.setShadowSumExp( Integer.decode( e.attributeValue("shadowSumExp").trim() ) );		YuanShenExpTemplet remove = map.put(x.getShadowLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static YuanShenExpTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static YuanShenExpTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static YuanShenExpTemplet getMin() {		return get(getMinKey());	}	public static List<YuanShenExpTemplet> findByShadowLevel(int value) {
		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenExpTemplet> findByShadowExp(int value) {
		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenExpTemplet> findByShadowSumExp(int value) {
		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowSumExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByShadowLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowLevel();
		}
		return all;
	}
	public static int[] getArrayByShadowExp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowExp();
		}
		return all;
	}
	public static int[] getArrayByShadowSumExp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowSumExp();
		}
		return all;
	}
	public static List<Integer> getListByShadowLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowLevel());
		}
		return all;
	}
	public static List<Integer> getListByShadowExp() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowExp());
		}
		return all;
	}
	public static List<Integer> getListByShadowSumExp() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowSumExp());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}