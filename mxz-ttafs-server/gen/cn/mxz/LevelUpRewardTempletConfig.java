//[奇遇]28[等级奖励]等级奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class LevelUpRewardTempletConfig {	private static Map<Integer, LevelUpRewardTemplet> map;	private static List<Integer> keys;	private static List<LevelUpRewardTemplet> all;	static {		load();	}	public static List<LevelUpRewardTemplet> getAll() {		return new ArrayList<LevelUpRewardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/LevelUpRewardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, LevelUpRewardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																LevelUpRewardTempletConfig.map = map;		LevelUpRewardTempletConfig.keys = keys;																List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		LevelUpRewardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, LevelUpRewardTemplet> map) {		LevelUpRewardTemplet x = new LevelUpRewardTemplet();		x.setNeedLevel( Integer.decode( e.attributeValue("needLevel").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setExplain( e.attributeValue("explain") );		LevelUpRewardTemplet remove = map.put(x.getNeedLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static LevelUpRewardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static LevelUpRewardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static LevelUpRewardTemplet getMin() {		return get(getMinKey());	}	public static List<LevelUpRewardTemplet> findByNeedLevel(int value) {
		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getNeedLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<LevelUpRewardTemplet> findByAwards(String value) {
		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<LevelUpRewardTemplet> findByExplain(String value) {
		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByNeedLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getNeedLevel();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static List<Integer> getListByNeedLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getNeedLevel());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}