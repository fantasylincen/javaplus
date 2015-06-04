//[关卡]引导地图提取怪物表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DemonGroupTempletConfig {	private static Map<Integer, DemonGroupTemplet> map;	private static List<Integer> keys;	private static List<DemonGroupTemplet> all;	static {		load();	}	public static List<DemonGroupTemplet> getAll() {		return new ArrayList<DemonGroupTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DemonGroupConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DemonGroupTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DemonGroupTempletConfig.map = map;		DemonGroupTempletConfig.keys = keys;																List<DemonGroupTemplet> all = new ArrayList<DemonGroupTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DemonGroupTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DemonGroupTemplet> map) {		DemonGroupTemplet x = new DemonGroupTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setGroup( e.attributeValue("group") );		DemonGroupTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DemonGroupTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DemonGroupTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DemonGroupTemplet getMin() {		return get(getMinKey());	}	public static List<DemonGroupTemplet> findById(int value) {
		List<DemonGroupTemplet> all = new ArrayList<DemonGroupTemplet>();
		for (DemonGroupTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DemonGroupTemplet> findByGroup(String value) {
		List<DemonGroupTemplet> all = new ArrayList<DemonGroupTemplet>();
		for (DemonGroupTemplet f : getAll()) {
			if(equals(f.getGroup(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DemonGroupTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByGroup() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DemonGroupTemplet f = get(keys.get(i));
			all[i] = f.getGroup();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (DemonGroupTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByGroup() {
		List<String> all = new ArrayList<String>();
		for (DemonGroupTemplet f : getAll()) {
			all.add(f.getGroup());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}