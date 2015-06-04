//[活动]女娲散财package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class NvWaTempletConfig {	private static Map<Integer, NvWaTemplet> map;	private static List<Integer> keys;	private static List<NvWaTemplet> all;	static {		load();		all = new ArrayList<NvWaTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<NvWaTemplet> getAll() {		return new ArrayList<NvWaTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/NvWaConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, NvWaTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);				NvWaTempletConfig.map = map;		NvWaTempletConfig.keys = keys;	}	private static void put(Element e, Map<Integer, NvWaTemplet> map) {		NvWaTemplet x = new NvWaTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setVolume( Integer.decode( e.attributeValue("volume").trim() ) );		x.setDiscount( Float.parseFloat( e.attributeValue("discount").trim() ) );		NvWaTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static NvWaTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static NvWaTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static NvWaTemplet getMin() {		return get(getMinKey());	}	public static List<NvWaTemplet> findById(int value) {
		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NvWaTemplet> findByVolume(int value) {
		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getVolume(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<NvWaTemplet> findByDiscount(float value) {
		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getDiscount(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByVolume() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getVolume();
		}
		return all;
	}
	public static float[] getArrayByDiscount() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getDiscount();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByVolume() {
		List<Integer> all = new ArrayList<Integer>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getVolume());
		}
		return all;
	}
	public static List<Float> getListByDiscount() {
		List<Float> all = new ArrayList<Float>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getDiscount());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}