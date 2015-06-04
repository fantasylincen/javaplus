//[奇遇]35[渡天劫]后端排行奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterRanklistTempletConfig {	private static Map<Integer, CopterRanklistTemplet> map;	private static List<Integer> keys;	private static List<CopterRanklistTemplet> all;	static {		load();	}	public static List<CopterRanklistTemplet> getAll() {		return new ArrayList<CopterRanklistTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterRanklistConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterRanklistTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterRanklistTempletConfig.map = map;		CopterRanklistTempletConfig.keys = keys;																List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterRanklistTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterRanklistTemplet> map) {		CopterRanklistTemplet x = new CopterRanklistTemplet();		x.setGivil( Integer.decode( e.attributeValue("givil").trim() ) );		x.setRewardType( Integer.decode( e.attributeValue("rewardType").trim() ) );		x.setSycee3( Integer.decode( e.attributeValue("sycee3").trim() ) );		x.setSycee4( Integer.decode( e.attributeValue("sycee4").trim() ) );		x.setSycee5( Integer.decode( e.attributeValue("sycee5").trim() ) );		CopterRanklistTemplet remove = map.put(x.getGivil(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterRanklistTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterRanklistTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterRanklistTemplet getMin() {		return get(getMinKey());	}	public static List<CopterRanklistTemplet> findByGivil(int value) {
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getGivil(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistTemplet> findByRewardType(int value) {
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getRewardType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistTemplet> findBySycee3(int value) {
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistTemplet> findBySycee4(int value) {
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistTemplet> findBySycee5(int value) {
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee5(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByGivil() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getGivil();
		}
		return all;
	}
	public static int[] getArrayByRewardType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getRewardType();
		}
		return all;
	}
	public static int[] getArrayBySycee3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee3();
		}
		return all;
	}
	public static int[] getArrayBySycee4() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee4();
		}
		return all;
	}
	public static int[] getArrayBySycee5() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee5();
		}
		return all;
	}
	public static List<Integer> getListByGivil() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getGivil());
		}
		return all;
	}
	public static List<Integer> getListByRewardType() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getRewardType());
		}
		return all;
	}
	public static List<Integer> getListBySycee3() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee3());
		}
		return all;
	}
	public static List<Integer> getListBySycee4() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee4());
		}
		return all;
	}
	public static List<Integer> getListBySycee5() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee5());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}