//[阵法]上阵人数开启package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ArmyOpenNewTempletConfig {	private static Map<Integer, ArmyOpenNewTemplet> map;	private static List<Integer> keys;	private static List<ArmyOpenNewTemplet> all;	static {		load();	}	public static List<ArmyOpenNewTemplet> getAll() {		return new ArrayList<ArmyOpenNewTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ArmyOpenNewConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ArmyOpenNewTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ArmyOpenNewTempletConfig.map = map;		ArmyOpenNewTempletConfig.keys = keys;																List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ArmyOpenNewTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ArmyOpenNewTemplet> map) {		ArmyOpenNewTemplet x = new ArmyOpenNewTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setLeadLv( Integer.decode( e.attributeValue("leadLv").trim() ) );		x.setArmyState( e.attributeValue("armyState") );		ArmyOpenNewTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ArmyOpenNewTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ArmyOpenNewTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ArmyOpenNewTemplet getMin() {		return get(getMinKey());	}	public static List<ArmyOpenNewTemplet> findById(int value) {
		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArmyOpenNewTemplet> findByLeadLv(int value) {
		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getLeadLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ArmyOpenNewTemplet> findByArmyState(String value) {
		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getArmyState(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByLeadLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getLeadLv();
		}
		return all;
	}
	public static String[] getArrayByArmyState() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getArmyState();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByLeadLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getLeadLv());
		}
		return all;
	}
	public static List<String> getListByArmyState() {
		List<String> all = new ArrayList<String>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getArmyState());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}