//[神将]神将品质表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GodQurlityTempletConfig {	private static Map<Integer, GodQurlityTemplet> map;	private static List<Integer> keys;	private static List<GodQurlityTemplet> all;	static {		load();	}	public static List<GodQurlityTemplet> getAll() {		return new ArrayList<GodQurlityTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GodQurlityConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, GodQurlityTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																GodQurlityTempletConfig.map = map;		GodQurlityTempletConfig.keys = keys;																List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		GodQurlityTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, GodQurlityTemplet> map) {		GodQurlityTemplet x = new GodQurlityTemplet();		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setState( e.attributeValue("state") );		x.setStarCount( Integer.decode( e.attributeValue("starCount").trim() ) );		x.setStarNumber( Integer.decode( e.attributeValue("starNumber").trim() ) );		x.setQualityIdAfterLevelUp( Integer.decode( e.attributeValue("qualityIdAfterLevelUp").trim() ) );		x.setPlayerAdvanceMedicine( Integer.decode( e.attributeValue("playerAdvanceMedicine").trim() ) );		x.setPlayerAdvanceSunrex( Integer.decode( e.attributeValue("playerAdvanceSunrex").trim() ) );		x.setAdvanceMedicine( Integer.decode( e.attributeValue("advanceMedicine").trim() ) );		x.setAdvanceSoul( Integer.decode( e.attributeValue("advanceSoul").trim() ) );		x.setMaxLevel( Integer.decode( e.attributeValue("maxLevel").trim() ) );		x.setShadowLevel( Integer.decode( e.attributeValue("shadowLevel").trim() ) );		x.setMarkUp( Float.parseFloat( e.attributeValue("markUp").trim() ) );		GodQurlityTemplet remove = map.put(x.getQuality(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GodQurlityTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GodQurlityTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GodQurlityTemplet getMin() {		return get(getMinKey());	}	public static List<GodQurlityTemplet> findByQuality(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByStep(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByState(String value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getState(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByStarCount(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getStarCount(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByStarNumber(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getStarNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByQualityIdAfterLevelUp(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getQualityIdAfterLevelUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByPlayerAdvanceMedicine(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getPlayerAdvanceMedicine(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByPlayerAdvanceSunrex(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getPlayerAdvanceSunrex(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByAdvanceMedicine(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getAdvanceMedicine(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByAdvanceSoul(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getAdvanceSoul(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByMaxLevel(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getMaxLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByShadowLevel(int value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getShadowLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodQurlityTemplet> findByMarkUp(float value) {
		List<GodQurlityTemplet> all = new ArrayList<GodQurlityTemplet>();
		for (GodQurlityTemplet f : getAll()) {
			if(equals(f.getMarkUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static String[] getArrayByState() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getState();
		}
		return all;
	}
	public static int[] getArrayByStarCount() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getStarCount();
		}
		return all;
	}
	public static int[] getArrayByStarNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getStarNumber();
		}
		return all;
	}
	public static int[] getArrayByQualityIdAfterLevelUp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getQualityIdAfterLevelUp();
		}
		return all;
	}
	public static int[] getArrayByPlayerAdvanceMedicine() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getPlayerAdvanceMedicine();
		}
		return all;
	}
	public static int[] getArrayByPlayerAdvanceSunrex() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getPlayerAdvanceSunrex();
		}
		return all;
	}
	public static int[] getArrayByAdvanceMedicine() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getAdvanceMedicine();
		}
		return all;
	}
	public static int[] getArrayByAdvanceSoul() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getAdvanceSoul();
		}
		return all;
	}
	public static int[] getArrayByMaxLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getMaxLevel();
		}
		return all;
	}
	public static int[] getArrayByShadowLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getShadowLevel();
		}
		return all;
	}
	public static float[] getArrayByMarkUp() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodQurlityTemplet f = get(keys.get(i));
			all[i] = f.getMarkUp();
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<String> getListByState() {
		List<String> all = new ArrayList<String>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getState());
		}
		return all;
	}
	public static List<Integer> getListByStarCount() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getStarCount());
		}
		return all;
	}
	public static List<Integer> getListByStarNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getStarNumber());
		}
		return all;
	}
	public static List<Integer> getListByQualityIdAfterLevelUp() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getQualityIdAfterLevelUp());
		}
		return all;
	}
	public static List<Integer> getListByPlayerAdvanceMedicine() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getPlayerAdvanceMedicine());
		}
		return all;
	}
	public static List<Integer> getListByPlayerAdvanceSunrex() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getPlayerAdvanceSunrex());
		}
		return all;
	}
	public static List<Integer> getListByAdvanceMedicine() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getAdvanceMedicine());
		}
		return all;
	}
	public static List<Integer> getListByAdvanceSoul() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getAdvanceSoul());
		}
		return all;
	}
	public static List<Integer> getListByMaxLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getMaxLevel());
		}
		return all;
	}
	public static List<Integer> getListByShadowLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getShadowLevel());
		}
		return all;
	}
	public static List<Float> getListByMarkUp() {
		List<Float> all = new ArrayList<Float>();
		for (GodQurlityTemplet f : getAll()) {
			all.add(f.getMarkUp());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}