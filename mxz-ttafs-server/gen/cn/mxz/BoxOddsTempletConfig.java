//[关卡]宝箱几率package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BoxOddsTempletConfig {	private static Map<Integer, BoxOddsTemplet> map;	private static List<Integer> keys;	private static List<BoxOddsTemplet> all;	static {		load();	}	public static List<BoxOddsTemplet> getAll() {		return new ArrayList<BoxOddsTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BoxOddsConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BoxOddsTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BoxOddsTempletConfig.map = map;		BoxOddsTempletConfig.keys = keys;																List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BoxOddsTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BoxOddsTemplet> map) {		BoxOddsTemplet x = new BoxOddsTemplet();		x.setBoxId( Integer.decode( e.attributeValue("boxId").trim() ) );		x.setBoxName( e.attributeValue("boxName") );		x.setMapId( e.attributeValue("mapId") );		x.setBoxWeight( Integer.decode( e.attributeValue("boxWeight").trim() ) );		BoxOddsTemplet remove = map.put(x.getBoxId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BoxOddsTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BoxOddsTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BoxOddsTemplet getMin() {		return get(getMinKey());	}	public static List<BoxOddsTemplet> findByBoxId(int value) {
		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BoxOddsTemplet> findByBoxName(String value) {
		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BoxOddsTemplet> findByMapId(String value) {
		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getMapId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BoxOddsTemplet> findByBoxWeight(int value) {
		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByBoxId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxId();
		}
		return all;
	}
	public static String[] getArrayByBoxName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxName();
		}
		return all;
	}
	public static String[] getArrayByMapId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getMapId();
		}
		return all;
	}
	public static int[] getArrayByBoxWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxWeight();
		}
		return all;
	}
	public static List<Integer> getListByBoxId() {
		List<Integer> all = new ArrayList<Integer>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxId());
		}
		return all;
	}
	public static List<String> getListByBoxName() {
		List<String> all = new ArrayList<String>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxName());
		}
		return all;
	}
	public static List<String> getListByMapId() {
		List<String> all = new ArrayList<String>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getMapId());
		}
		return all;
	}
	public static List<Integer> getListByBoxWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxWeight());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}