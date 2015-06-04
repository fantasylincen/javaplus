//[奇遇]15[关卡][神魔]基础奖励表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RandomEventBaseTempletConfig {	private static Map<Integer, RandomEventBaseTemplet> map;	private static List<Integer> keys;	private static List<RandomEventBaseTemplet> all;	static {		load();	}	public static List<RandomEventBaseTemplet> getAll() {		return new ArrayList<RandomEventBaseTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RandomEventBaseConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RandomEventBaseTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RandomEventBaseTempletConfig.map = map;		RandomEventBaseTempletConfig.keys = keys;																List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RandomEventBaseTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RandomEventBaseTemplet> map) {		RandomEventBaseTemplet x = new RandomEventBaseTemplet();		x.setRappelzLv( Integer.decode( e.attributeValue("rappelzLv").trim() ) );		x.setMerit( Integer.decode( e.attributeValue("merit").trim() ) );		x.setFindReward( e.attributeValue("findReward") );		x.setMvpReward( e.attributeValue("mvpReward") );		x.setKillReward( e.attributeValue("killReward") );		x.setBossParam( Float.parseFloat( e.attributeValue("bossParam").trim() ) );		RandomEventBaseTemplet remove = map.put(x.getRappelzLv(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RandomEventBaseTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RandomEventBaseTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RandomEventBaseTemplet getMin() {		return get(getMinKey());	}	public static List<RandomEventBaseTemplet> findByRappelzLv(int value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getRappelzLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventBaseTemplet> findByMerit(int value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getMerit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventBaseTemplet> findByFindReward(String value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getFindReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventBaseTemplet> findByMvpReward(String value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getMvpReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventBaseTemplet> findByKillReward(String value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getKillReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RandomEventBaseTemplet> findByBossParam(float value) {
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getBossParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByRappelzLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getRappelzLv();
		}
		return all;
	}
	public static int[] getArrayByMerit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getMerit();
		}
		return all;
	}
	public static String[] getArrayByFindReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getFindReward();
		}
		return all;
	}
	public static String[] getArrayByMvpReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getMvpReward();
		}
		return all;
	}
	public static String[] getArrayByKillReward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getKillReward();
		}
		return all;
	}
	public static float[] getArrayByBossParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getBossParam();
		}
		return all;
	}
	public static List<Integer> getListByRappelzLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getRappelzLv());
		}
		return all;
	}
	public static List<Integer> getListByMerit() {
		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getMerit());
		}
		return all;
	}
	public static List<String> getListByFindReward() {
		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getFindReward());
		}
		return all;
	}
	public static List<String> getListByMvpReward() {
		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getMvpReward());
		}
		return all;
	}
	public static List<String> getListByKillReward() {
		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getKillReward());
		}
		return all;
	}
	public static List<Float> getListByBossParam() {
		List<Float> all = new ArrayList<Float>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getBossParam());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}