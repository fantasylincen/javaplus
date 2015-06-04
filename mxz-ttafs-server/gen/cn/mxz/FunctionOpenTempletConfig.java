//[开启]功能开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FunctionOpenTempletConfig {	private static Map<Integer, FunctionOpenTemplet> map;	private static List<Integer> keys;	private static List<FunctionOpenTemplet> all;	static {		load();	}	public static List<FunctionOpenTemplet> getAll() {		return new ArrayList<FunctionOpenTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FunctionOpenConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FunctionOpenTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FunctionOpenTempletConfig.map = map;		FunctionOpenTempletConfig.keys = keys;																List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FunctionOpenTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FunctionOpenTemplet> map) {		FunctionOpenTemplet x = new FunctionOpenTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setType( e.attributeValue("type") );		x.setIdentifying( e.attributeValue("identifying") );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setRemark( e.attributeValue("remark") );		FunctionOpenTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FunctionOpenTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FunctionOpenTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FunctionOpenTemplet getMin() {		return get(getMinKey());	}	public static List<FunctionOpenTemplet> findById(int value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FunctionOpenTemplet> findByName(String value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FunctionOpenTemplet> findByType(String value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FunctionOpenTemplet> findByIdentifying(String value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getIdentifying(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FunctionOpenTemplet> findByLevel(int value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FunctionOpenTemplet> findByRemark(String value) {
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getRemark(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByIdentifying() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getIdentifying();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static String[] getArrayByRemark() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getRemark();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByType() {
		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByIdentifying() {
		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getIdentifying());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<String> getListByRemark() {
		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getRemark());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}