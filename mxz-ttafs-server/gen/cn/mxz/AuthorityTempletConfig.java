//[权限]等级权限配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AuthorityTempletConfig {	private static Map<Integer, AuthorityTemplet> map;	private static List<Integer> keys;	private static List<AuthorityTemplet> all;	static {		load();	}	public static List<AuthorityTemplet> getAll() {		return new ArrayList<AuthorityTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AuthorityConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, AuthorityTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																AuthorityTempletConfig.map = map;		AuthorityTempletConfig.keys = keys;																List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		AuthorityTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, AuthorityTemplet> map) {		AuthorityTemplet x = new AuthorityTemplet();		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setBattleNumber( Integer.decode( e.attributeValue("battleNumber").trim() ) );		x.setFreeTime( Integer.decode( e.attributeValue("freeTime").trim() ) );		x.setExpaddition( Float.parseFloat( e.attributeValue("expaddition").trim() ) );		x.setLevelUpAward( e.attributeValue("levelUpAward") );		x.setInitialAddition( Float.parseFloat( e.attributeValue("initialAddition").trim() ) );		x.setVigorLimit( Integer.decode( e.attributeValue("vigorLimit").trim() ) );		x.setPowerLimit( Integer.decode( e.attributeValue("powerLimit").trim() ) );		x.setSnatchPar( Float.parseFloat( e.attributeValue("snatchPar").trim() ) );		x.setLevelUpgoods( e.attributeValue("levelUpgoods") );		AuthorityTemplet remove = map.put(x.getLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AuthorityTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AuthorityTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AuthorityTemplet getMin() {		return get(getMinKey());	}	public static List<AuthorityTemplet> findByLevel(int value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByBattleNumber(int value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getBattleNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByFreeTime(int value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getFreeTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByExpaddition(float value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getExpaddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByLevelUpAward(String value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevelUpAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByInitialAddition(float value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getInitialAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByVigorLimit(int value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getVigorLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByPowerLimit(int value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getPowerLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findBySnatchPar(float value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getSnatchPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AuthorityTemplet> findByLevelUpgoods(String value) {
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevelUpgoods(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByBattleNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getBattleNumber();
		}
		return all;
	}
	public static int[] getArrayByFreeTime() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getFreeTime();
		}
		return all;
	}
	public static float[] getArrayByExpaddition() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getExpaddition();
		}
		return all;
	}
	public static String[] getArrayByLevelUpAward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevelUpAward();
		}
		return all;
	}
	public static float[] getArrayByInitialAddition() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getInitialAddition();
		}
		return all;
	}
	public static int[] getArrayByVigorLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVigorLimit();
		}
		return all;
	}
	public static int[] getArrayByPowerLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getPowerLimit();
		}
		return all;
	}
	public static float[] getArrayBySnatchPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getSnatchPar();
		}
		return all;
	}
	public static String[] getArrayByLevelUpgoods() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevelUpgoods();
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByBattleNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getBattleNumber());
		}
		return all;
	}
	public static List<Integer> getListByFreeTime() {
		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getFreeTime());
		}
		return all;
	}
	public static List<Float> getListByExpaddition() {
		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getExpaddition());
		}
		return all;
	}
	public static List<String> getListByLevelUpAward() {
		List<String> all = new ArrayList<String>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevelUpAward());
		}
		return all;
	}
	public static List<Float> getListByInitialAddition() {
		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getInitialAddition());
		}
		return all;
	}
	public static List<Integer> getListByVigorLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getVigorLimit());
		}
		return all;
	}
	public static List<Integer> getListByPowerLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getPowerLimit());
		}
		return all;
	}
	public static List<Float> getListBySnatchPar() {
		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getSnatchPar());
		}
		return all;
	}
	public static List<String> getListByLevelUpgoods() {
		List<String> all = new ArrayList<String>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevelUpgoods());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}