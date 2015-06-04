//[商城]特殊物品购买价格加成倍数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MarketPlaceAdditionTempletConfig {	private static Map<Integer, MarketPlaceAdditionTemplet> map;	private static List<Integer> keys;	private static List<MarketPlaceAdditionTemplet> all;	static {		load();	}	public static List<MarketPlaceAdditionTemplet> getAll() {		return new ArrayList<MarketPlaceAdditionTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MarketPlaceAdditionConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MarketPlaceAdditionTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MarketPlaceAdditionTempletConfig.map = map;		MarketPlaceAdditionTempletConfig.keys = keys;																List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MarketPlaceAdditionTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MarketPlaceAdditionTemplet> map) {		MarketPlaceAdditionTemplet x = new MarketPlaceAdditionTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setMin( Integer.decode( e.attributeValue("min").trim() ) );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setPropId( Integer.decode( e.attributeValue("propId").trim() ) );		x.setX( Float.parseFloat( e.attributeValue("x").trim() ) );		MarketPlaceAdditionTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MarketPlaceAdditionTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MarketPlaceAdditionTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MarketPlaceAdditionTemplet getMin() {		return get(getMinKey());	}	public static List<MarketPlaceAdditionTemplet> findById(int value) {
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceAdditionTemplet> findByMin(int value) {
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceAdditionTemplet> findByMax(int value) {
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceAdditionTemplet> findByPropId(int value) {
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getPropId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceAdditionTemplet> findByX(float value) {
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getMin();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static int[] getArrayByPropId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getPropId();
		}
		return all;
	}
	public static float[] getArrayByX() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getX();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getMin());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<Integer> getListByPropId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getPropId());
		}
		return all;
	}
	public static List<Float> getListByX() {
		List<Float> all = new ArrayList<Float>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getX());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}