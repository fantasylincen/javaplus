//[仙市]寻仙package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GodTypeTempletConfig {	private static Map<Integer, GodTypeTemplet> map;	private static List<Integer> keys;	private static List<GodTypeTemplet> all;	static {		load();	}	public static List<GodTypeTemplet> getAll() {		return new ArrayList<GodTypeTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GodTypeConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, GodTypeTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																GodTypeTempletConfig.map = map;		GodTypeTempletConfig.keys = keys;																List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		GodTypeTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, GodTypeTemplet> map) {		GodTypeTemplet x = new GodTypeTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setWayId( Integer.decode( e.attributeValue("wayId").trim() ) );		x.setName( e.attributeValue("name") );		x.setXunXianLingNeed( Integer.decode( e.attributeValue("xunXianLingNeed").trim() ) );		x.setFreeNumber( Integer.decode( e.attributeValue("freeNumber").trim() ) );		x.setTimes( Integer.decode( e.attributeValue("times").trim() ) );		x.setDescribeUp( e.attributeValue("describeUp") );		x.setDescribeDown( e.attributeValue("describeDown") );		x.setUrl( e.attributeValue("url") );		x.setCertainly( e.attributeValue("certainly") );		x.setProbability( e.attributeValue("probability") );		x.setGodBank( e.attributeValue("godBank") );		x.setShowGod( e.attributeValue("showGod") );		x.setFormat( e.attributeValue("format") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		GodTypeTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GodTypeTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GodTypeTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GodTypeTemplet getMin() {		return get(getMinKey());	}	public static List<GodTypeTemplet> findById(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByWayId(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getWayId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByName(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByXunXianLingNeed(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getXunXianLingNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByFreeNumber(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getFreeNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByTimes(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByDescribeUp(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getDescribeUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByDescribeDown(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getDescribeDown(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByUrl(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByCertainly(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getCertainly(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByProbability(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getProbability(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByGodBank(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getGodBank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByShowGod(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getShowGod(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByFormat(String value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodTypeTemplet> findByResid(int value) {
		List<GodTypeTemplet> all = new ArrayList<GodTypeTemplet>();
		for (GodTypeTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByWayId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getWayId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByXunXianLingNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getXunXianLingNeed();
		}
		return all;
	}
	public static int[] getArrayByFreeNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getFreeNumber();
		}
		return all;
	}
	public static int[] getArrayByTimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getTimes();
		}
		return all;
	}
	public static String[] getArrayByDescribeUp() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getDescribeUp();
		}
		return all;
	}
	public static String[] getArrayByDescribeDown() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getDescribeDown();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByCertainly() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getCertainly();
		}
		return all;
	}
	public static String[] getArrayByProbability() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getProbability();
		}
		return all;
	}
	public static String[] getArrayByGodBank() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getGodBank();
		}
		return all;
	}
	public static String[] getArrayByShowGod() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getShowGod();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodTypeTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByWayId() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getWayId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByXunXianLingNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getXunXianLingNeed());
		}
		return all;
	}
	public static List<Integer> getListByFreeNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getFreeNumber());
		}
		return all;
	}
	public static List<Integer> getListByTimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getTimes());
		}
		return all;
	}
	public static List<String> getListByDescribeUp() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getDescribeUp());
		}
		return all;
	}
	public static List<String> getListByDescribeDown() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getDescribeDown());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByCertainly() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getCertainly());
		}
		return all;
	}
	public static List<String> getListByProbability() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getProbability());
		}
		return all;
	}
	public static List<String> getListByGodBank() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getGodBank());
		}
		return all;
	}
	public static List<String> getListByShowGod() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getShowGod());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodTypeTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}