//[奇遇]24[保护妲己]怪物波数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CustodianMapTempletConfig {	private static Map<Integer, CustodianMapTemplet> map;	private static List<Integer> keys;	private static List<CustodianMapTemplet> all;	static {		load();	}	public static List<CustodianMapTemplet> getAll() {		return new ArrayList<CustodianMapTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CustodianMapConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CustodianMapTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CustodianMapTempletConfig.map = map;		CustodianMapTempletConfig.keys = keys;																List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CustodianMapTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CustodianMapTemplet> map) {		CustodianMapTemplet x = new CustodianMapTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setMonsterId( e.attributeValue("monsterId") );		x.setMonsterGrade( Integer.decode( e.attributeValue("monsterGrade").trim() ) );		x.setDriveAward( e.attributeValue("driveAward") );		x.setCoolTime( Integer.decode( e.attributeValue("coolTime").trim() ) );		x.setCoolTimevip( Integer.decode( e.attributeValue("coolTimevip").trim() ) );		x.setWilsonParam( Float.parseFloat( e.attributeValue("wilsonParam").trim() ) );		x.setHurtMin( Integer.decode( e.attributeValue("hurtMin").trim() ) );		CustodianMapTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CustodianMapTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CustodianMapTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CustodianMapTemplet getMin() {		return get(getMinKey());	}	public static List<CustodianMapTemplet> findById(int value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByName(String value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByMonsterId(String value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getMonsterId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByMonsterGrade(int value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getMonsterGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByDriveAward(String value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getDriveAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByCoolTime(int value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getCoolTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByCoolTimevip(int value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getCoolTimevip(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByWilsonParam(float value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getWilsonParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CustodianMapTemplet> findByHurtMin(int value) {
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getHurtMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByMonsterId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterId();
		}
		return all;
	}
	public static int[] getArrayByMonsterGrade() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterGrade();
		}
		return all;
	}
	public static String[] getArrayByDriveAward() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getDriveAward();
		}
		return all;
	}
	public static int[] getArrayByCoolTime() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getCoolTime();
		}
		return all;
	}
	public static int[] getArrayByCoolTimevip() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getCoolTimevip();
		}
		return all;
	}
	public static float[] getArrayByWilsonParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getWilsonParam();
		}
		return all;
	}
	public static int[] getArrayByHurtMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getHurtMin();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByMonsterId() {
		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getMonsterId());
		}
		return all;
	}
	public static List<Integer> getListByMonsterGrade() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getMonsterGrade());
		}
		return all;
	}
	public static List<String> getListByDriveAward() {
		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getDriveAward());
		}
		return all;
	}
	public static List<Integer> getListByCoolTime() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getCoolTime());
		}
		return all;
	}
	public static List<Integer> getListByCoolTimevip() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getCoolTimevip());
		}
		return all;
	}
	public static List<Float> getListByWilsonParam() {
		List<Float> all = new ArrayList<Float>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getWilsonParam());
		}
		return all;
	}
	public static List<Integer> getListByHurtMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getHurtMin());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}