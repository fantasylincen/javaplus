//[奇遇]35[渡天劫]前端排行奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterRanklistFrontTempletConfig {	private static Map<Integer, CopterRanklistFrontTemplet> map;	private static List<Integer> keys;	private static List<CopterRanklistFrontTemplet> all;	static {		load();	}	public static List<CopterRanklistFrontTemplet> getAll() {		return new ArrayList<CopterRanklistFrontTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterRanklistFrontConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterRanklistFrontTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterRanklistFrontTempletConfig.map = map;		CopterRanklistFrontTempletConfig.keys = keys;																List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterRanklistFrontTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterRanklistFrontTemplet> map) {		CopterRanklistFrontTemplet x = new CopterRanklistFrontTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setGivil( e.attributeValue("givil") );		x.setSycee3( Integer.decode( e.attributeValue("sycee3").trim() ) );		x.setSycee4( Integer.decode( e.attributeValue("sycee4").trim() ) );		x.setSycee5( Integer.decode( e.attributeValue("sycee5").trim() ) );		CopterRanklistFrontTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterRanklistFrontTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterRanklistFrontTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterRanklistFrontTemplet getMin() {		return get(getMinKey());	}	public static List<CopterRanklistFrontTemplet> findById(int value) {
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistFrontTemplet> findByGivil(String value) {
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getGivil(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistFrontTemplet> findBySycee3(int value) {
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistFrontTemplet> findBySycee4(int value) {
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterRanklistFrontTemplet> findBySycee5(int value) {
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee5(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByGivil() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getGivil();
		}
		return all;
	}
	public static int[] getArrayBySycee3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee3();
		}
		return all;
	}
	public static int[] getArrayBySycee4() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee4();
		}
		return all;
	}
	public static int[] getArrayBySycee5() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee5();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByGivil() {
		List<String> all = new ArrayList<String>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getGivil());
		}
		return all;
	}
	public static List<Integer> getListBySycee3() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee3());
		}
		return all;
	}
	public static List<Integer> getListBySycee4() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee4());
		}
		return all;
	}
	public static List<Integer> getListBySycee5() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee5());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}