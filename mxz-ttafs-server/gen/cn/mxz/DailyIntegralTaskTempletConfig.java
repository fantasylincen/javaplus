//[任务]日常任务积分奖励表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DailyIntegralTaskTempletConfig {	private static Map<Integer, DailyIntegralTaskTemplet> map;	private static List<Integer> keys;	private static List<DailyIntegralTaskTemplet> all;	static {		load();	}	public static List<DailyIntegralTaskTemplet> getAll() {		return new ArrayList<DailyIntegralTaskTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DailyIntegralTaskConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DailyIntegralTaskTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DailyIntegralTaskTempletConfig.map = map;		DailyIntegralTaskTempletConfig.keys = keys;																List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DailyIntegralTaskTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DailyIntegralTaskTemplet> map) {		DailyIntegralTaskTemplet x = new DailyIntegralTaskTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setIntegral( Integer.decode( e.attributeValue("integral").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setModuleId( Integer.decode( e.attributeValue("moduleId").trim() ) );		x.setActivityAwards( e.attributeValue("activityAwards") );		DailyIntegralTaskTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DailyIntegralTaskTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DailyIntegralTaskTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DailyIntegralTaskTemplet getMin() {		return get(getMinKey());	}	public static List<DailyIntegralTaskTemplet> findById(int value) {
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyIntegralTaskTemplet> findByIntegral(int value) {
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getIntegral(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyIntegralTaskTemplet> findByAwards(String value) {
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyIntegralTaskTemplet> findByModuleId(int value) {
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getModuleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyIntegralTaskTemplet> findByActivityAwards(String value) {
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getActivityAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByIntegral() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getIntegral();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByModuleId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getModuleId();
		}
		return all;
	}
	public static String[] getArrayByActivityAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getActivityAwards();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByIntegral() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getIntegral());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByModuleId() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getModuleId());
		}
		return all;
	}
	public static List<String> getListByActivityAwards() {
		List<String> all = new ArrayList<String>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getActivityAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}