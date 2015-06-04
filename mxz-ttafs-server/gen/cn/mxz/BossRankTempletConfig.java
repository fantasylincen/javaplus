//[奇遇]33[boss]排名奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BossRankTempletConfig {	private static Map<Integer, BossRankTemplet> map;	private static List<Integer> keys;	private static List<BossRankTemplet> all;	static {		load();	}	public static List<BossRankTemplet> getAll() {		return new ArrayList<BossRankTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BossRankConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BossRankTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BossRankTempletConfig.map = map;		BossRankTempletConfig.keys = keys;																List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BossRankTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BossRankTemplet> map) {		BossRankTemplet x = new BossRankTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setEnd( Integer.decode( e.attributeValue("end").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setPrestige( Integer.decode( e.attributeValue("prestige").trim() ) );		x.setAwards( e.attributeValue("awards") );		BossRankTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BossRankTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BossRankTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BossRankTemplet getMin() {		return get(getMinKey());	}	public static List<BossRankTemplet> findById(int value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossRankTemplet> findByStep(int value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossRankTemplet> findByEnd(int value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getEnd(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossRankTemplet> findByExplain(String value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossRankTemplet> findByPrestige(int value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getPrestige(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossRankTemplet> findByAwards(String value) {
		List<BossRankTemplet> all = new ArrayList<BossRankTemplet>();
		for (BossRankTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByEnd() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getEnd();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static int[] getArrayByPrestige() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getPrestige();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossRankTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByEnd() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getEnd());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<Integer> getListByPrestige() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getPrestige());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (BossRankTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}