//[仙市]聚魂招募package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class JuHunRecruitTempletConfig {	private static Map<Integer, JuHunRecruitTemplet> map;	private static List<Integer> keys;	private static List<JuHunRecruitTemplet> all;	static {		load();	}	public static List<JuHunRecruitTemplet> getAll() {		return new ArrayList<JuHunRecruitTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/JuHunRecruitConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, JuHunRecruitTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																JuHunRecruitTempletConfig.map = map;		JuHunRecruitTempletConfig.keys = keys;																List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		JuHunRecruitTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, JuHunRecruitTemplet> map) {		JuHunRecruitTemplet x = new JuHunRecruitTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setSteps( e.attributeValue("steps") );		x.setNeed( Integer.decode( e.attributeValue("need").trim() ) );		x.setGodBank( e.attributeValue("godBank") );		JuHunRecruitTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static JuHunRecruitTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static JuHunRecruitTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static JuHunRecruitTemplet getMin() {		return get(getMinKey());	}	public static List<JuHunRecruitTemplet> findById(int value) {
		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<JuHunRecruitTemplet> findBySteps(String value) {
		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getSteps(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<JuHunRecruitTemplet> findByNeed(int value) {
		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<JuHunRecruitTemplet> findByGodBank(String value) {
		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getGodBank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayBySteps() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getSteps();
		}
		return all;
	}
	public static int[] getArrayByNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getNeed();
		}
		return all;
	}
	public static String[] getArrayByGodBank() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getGodBank();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListBySteps() {
		List<String> all = new ArrayList<String>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getSteps());
		}
		return all;
	}
	public static List<Integer> getListByNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getNeed());
		}
		return all;
	}
	public static List<String> getListByGodBank() {
		List<String> all = new ArrayList<String>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getGodBank());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}