//[天命]天命package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ExclusiveTempletConfig {	private static Map<Integer, ExclusiveTemplet> map;	private static List<Integer> keys;	private static List<ExclusiveTemplet> all;	static {		load();	}	public static List<ExclusiveTemplet> getAll() {		return new ArrayList<ExclusiveTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ExclusiveConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ExclusiveTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ExclusiveTempletConfig.map = map;		ExclusiveTempletConfig.keys = keys;																List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ExclusiveTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ExclusiveTemplet> map) {		ExclusiveTemplet x = new ExclusiveTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setJudge( Integer.decode( e.attributeValue("judge").trim() ) );		x.setFighterId( Integer.decode( e.attributeValue("fighterId").trim() ) );		x.setFighterName( e.attributeValue("fighterName") );		x.setExclusiveId( e.attributeValue("exclusiveId") );		x.setExclusiveName( e.attributeValue("exclusiveName") );		x.setNatureType( Integer.decode( e.attributeValue("natureType").trim() ) );		x.setNaturePar( Float.parseFloat( e.attributeValue("naturePar").trim() ) );		x.setNatureFixed( Float.parseFloat( e.attributeValue("natureFixed").trim() ) );		x.setFighterStep( Integer.decode( e.attributeValue("fighterStep").trim() ) );		x.setDescription( e.attributeValue("description") );		ExclusiveTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ExclusiveTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ExclusiveTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ExclusiveTemplet getMin() {		return get(getMinKey());	}	public static List<ExclusiveTemplet> findById(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByName(String value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByType(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByJudge(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getJudge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByFighterId(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getFighterId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByFighterName(String value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getFighterName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByExclusiveId(String value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getExclusiveId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByExclusiveName(String value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getExclusiveName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByNatureType(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getNatureType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByNaturePar(float value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getNaturePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByNatureFixed(float value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getNatureFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByFighterStep(int value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getFighterStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ExclusiveTemplet> findByDescription(String value) {
		List<ExclusiveTemplet> all = new ArrayList<ExclusiveTemplet>();
		for (ExclusiveTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByJudge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getJudge();
		}
		return all;
	}
	public static int[] getArrayByFighterId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getFighterId();
		}
		return all;
	}
	public static String[] getArrayByFighterName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getFighterName();
		}
		return all;
	}
	public static String[] getArrayByExclusiveId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getExclusiveId();
		}
		return all;
	}
	public static String[] getArrayByExclusiveName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getExclusiveName();
		}
		return all;
	}
	public static int[] getArrayByNatureType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getNatureType();
		}
		return all;
	}
	public static float[] getArrayByNaturePar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getNaturePar();
		}
		return all;
	}
	public static float[] getArrayByNatureFixed() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getNatureFixed();
		}
		return all;
	}
	public static int[] getArrayByFighterStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getFighterStep();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ExclusiveTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByJudge() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getJudge());
		}
		return all;
	}
	public static List<Integer> getListByFighterId() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getFighterId());
		}
		return all;
	}
	public static List<String> getListByFighterName() {
		List<String> all = new ArrayList<String>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getFighterName());
		}
		return all;
	}
	public static List<String> getListByExclusiveId() {
		List<String> all = new ArrayList<String>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getExclusiveId());
		}
		return all;
	}
	public static List<String> getListByExclusiveName() {
		List<String> all = new ArrayList<String>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getExclusiveName());
		}
		return all;
	}
	public static List<Integer> getListByNatureType() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getNatureType());
		}
		return all;
	}
	public static List<Float> getListByNaturePar() {
		List<Float> all = new ArrayList<Float>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getNaturePar());
		}
		return all;
	}
	public static List<Float> getListByNatureFixed() {
		List<Float> all = new ArrayList<Float>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getNatureFixed());
		}
		return all;
	}
	public static List<Integer> getListByFighterStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getFighterStep());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (ExclusiveTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}