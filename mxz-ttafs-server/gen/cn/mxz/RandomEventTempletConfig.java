//[关卡]问号随机事件package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomEventTempletConfig {	private static Map<Integer, RandomEventTemplet> map;	private static List<Integer> keys;	private static List<RandomEventTemplet> all;	static {		load();	}	public static List<RandomEventTemplet> getAll() {		return new ArrayList<RandomEventTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomEventTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomEventTempletConfig.map = map;		RandomEventTempletConfig.keys = keys;																List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomEventTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomEventTemplet> map) {		RandomEventTemplet x = new RandomEventTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setEventName( e.attributeValue("eventName") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setEventWeight( Integer.decode( e.attributeValue("eventWeight").trim() ) );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		x.setAppear( e.attributeValue("appear") );		x.setMaxInMap( Integer.decode( e.attributeValue("maxInMap").trim() ) );		x.setNeeds( e.attributeValue("needs") );		x.setAwards( e.attributeValue("awards") );		x.setPlot( Integer.decode( e.attributeValue("plot").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setPic( Integer.decode( e.attributeValue("pic").trim() ) );		x.setScript( e.attributeValue("script") );		RandomEventTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventTemplet> findById(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByEventName(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getEventName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByType(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByEventWeight(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getEventWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByModulesId(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByAppear(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getAppear(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByMaxInMap(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getMaxInMap(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByNeeds(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getNeeds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByAwards(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByPlot(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getPlot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByFormat(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByPic(int value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getPic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventTemplet> findByScript(String value) {
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getScript(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByEventName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getEventName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByEventWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getEventWeight();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static String[] getArrayByAppear() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getAppear();
		}
		return all;
	}
	public static int[] getArrayByMaxInMap() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getMaxInMap();
		}
		return all;
	}
	public static String[] getArrayByNeeds() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getNeeds();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByPlot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getPlot();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByPic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getPic();
		}
		return all;
	}
	public static String[] getArrayByScript() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getScript();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByEventName() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getEventName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByEventWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getEventWeight());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	public static List<String> getListByAppear() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getAppear());
		}
		return all;
	}
	public static List<Integer> getListByMaxInMap() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getMaxInMap());
		}
		return all;
	}
	public static List<String> getListByNeeds() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getNeeds());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByPlot() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getPlot());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByPic() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getPic());
		}
		return all;
	}
	public static List<String> getListByScript() {
		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getScript());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}