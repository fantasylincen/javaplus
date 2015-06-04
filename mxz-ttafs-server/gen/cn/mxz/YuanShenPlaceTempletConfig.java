//[元神]元神位置开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class YuanShenPlaceTempletConfig {	private static Map<Integer, YuanShenPlaceTemplet> map;	private static List<Integer> keys;	private static List<YuanShenPlaceTemplet> all;	static {		load();	}	public static List<YuanShenPlaceTemplet> getAll() {		return new ArrayList<YuanShenPlaceTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/YuanShenPlaceConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, YuanShenPlaceTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																YuanShenPlaceTempletConfig.map = map;		YuanShenPlaceTempletConfig.keys = keys;																List<YuanShenPlaceTemplet> all = new ArrayList<YuanShenPlaceTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		YuanShenPlaceTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, YuanShenPlaceTemplet> map) {		YuanShenPlaceTemplet x = new YuanShenPlaceTemplet();		x.setSpiritPlace( Integer.decode( e.attributeValue("spiritPlace").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		YuanShenPlaceTemplet remove = map.put(x.getSpiritPlace(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static YuanShenPlaceTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static YuanShenPlaceTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static YuanShenPlaceTemplet getMin() {		return get(getMinKey());	}	public static List<YuanShenPlaceTemplet> findBySpiritPlace(int value) {
		List<YuanShenPlaceTemplet> all = new ArrayList<YuanShenPlaceTemplet>();
		for (YuanShenPlaceTemplet f : getAll()) {
			if(equals(f.getSpiritPlace(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenPlaceTemplet> findByLevel(int value) {
		List<YuanShenPlaceTemplet> all = new ArrayList<YuanShenPlaceTemplet>();
		for (YuanShenPlaceTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayBySpiritPlace() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenPlaceTemplet f = get(keys.get(i));
			all[i] = f.getSpiritPlace();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenPlaceTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static List<Integer> getListBySpiritPlace() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenPlaceTemplet f : getAll()) {
			all.add(f.getSpiritPlace());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenPlaceTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}