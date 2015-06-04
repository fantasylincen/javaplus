//[竞技场]排名奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class RankRewardTempletConfig {	private static Map<Integer, RankRewardTemplet> map;	private static List<Integer> keys;	private static List<RankRewardTemplet> all;	static {		load();	}	public static List<RankRewardTemplet> getAll() {		return new ArrayList<RankRewardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/RankRewardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, RankRewardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																RankRewardTempletConfig.map = map;		RankRewardTempletConfig.keys = keys;																List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		RankRewardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, RankRewardTemplet> map) {		RankRewardTemplet x = new RankRewardTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setEnd( Integer.decode( e.attributeValue("end").trim() ) );		x.setDanLv( Integer.decode( e.attributeValue("danLv").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setAwards( e.attributeValue("awards") );		RankRewardTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static RankRewardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static RankRewardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static RankRewardTemplet getMin() {		return get(getMinKey());	}	public static List<RankRewardTemplet> findById(int value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RankRewardTemplet> findByStep(int value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RankRewardTemplet> findByEnd(int value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getEnd(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RankRewardTemplet> findByDanLv(int value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getDanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RankRewardTemplet> findByExplain(String value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<RankRewardTemplet> findByAwards(String value) {
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByEnd() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getEnd();
		}
		return all;
	}
	public static int[] getArrayByDanLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanLv();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByEnd() {
		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getEnd());
		}
		return all;
	}
	public static List<Integer> getListByDanLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getDanLv());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}