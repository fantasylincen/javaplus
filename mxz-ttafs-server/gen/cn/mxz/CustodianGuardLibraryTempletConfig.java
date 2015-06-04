//[奇遇]24[保护妲己]三日五日奖励库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CustodianGuardLibraryTempletConfig {	private static Map<Integer, CustodianGuardLibraryTemplet> map;	private static List<Integer> keys;	private static List<CustodianGuardLibraryTemplet> all;	static {		load();	}	public static List<CustodianGuardLibraryTemplet> getAll() {		return new ArrayList<CustodianGuardLibraryTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CustodianGuardLibraryConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CustodianGuardLibraryTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CustodianGuardLibraryTempletConfig.map = map;		CustodianGuardLibraryTempletConfig.keys = keys;																List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CustodianGuardLibraryTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CustodianGuardLibraryTemplet> map) {		CustodianGuardLibraryTemplet x = new CustodianGuardLibraryTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setPropNeame( e.attributeValue("propNeame") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setModulesId( Integer.decode( e.attributeValue("modulesId").trim() ) );		x.setProbability( Float.parseFloat( e.attributeValue("probability").trim() ) );		CustodianGuardLibraryTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CustodianGuardLibraryTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CustodianGuardLibraryTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CustodianGuardLibraryTemplet getMin() {		return get(getMinKey());	}	public static List<CustodianGuardLibraryTemplet> findById(int value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByPropNeame(String value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByType(int value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByStep(int value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByWeight(int value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByModulesId(int value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianGuardLibraryTemplet> findByProbability(float value) {
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getProbability(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByPropNeame() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByModulesId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}
	public static float[] getArrayByProbability() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getProbability();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByPropNeame() {
		List<String> all = new ArrayList<String>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByModulesId() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
	public static List<Float> getListByProbability() {
		List<Float> all = new ArrayList<Float>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getProbability());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}