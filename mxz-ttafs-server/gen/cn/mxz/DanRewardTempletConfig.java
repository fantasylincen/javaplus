//[竞技场]段位奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DanRewardTempletConfig {	private static Map<Integer, DanRewardTemplet> map;	private static List<Integer> keys;	private static List<DanRewardTemplet> all;	static {		load();	}	public static List<DanRewardTemplet> getAll() {		return new ArrayList<DanRewardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DanRewardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DanRewardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DanRewardTempletConfig.map = map;		DanRewardTempletConfig.keys = keys;																List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DanRewardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DanRewardTemplet> map) {		DanRewardTemplet x = new DanRewardTemplet();		x.setDan( Integer.decode( e.attributeValue("dan").trim() ) );		x.setDanLv( Integer.decode( e.attributeValue("danLv").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setTitle( e.attributeValue("title") );		x.setGrading( Integer.decode( e.attributeValue("grading").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setCanDanDown( Integer.decode( e.attributeValue("canDanDown").trim() ) );		x.setRank( Integer.decode( e.attributeValue("rank").trim() ) );		x.setNeedCompareToTianZun( Integer.decode( e.attributeValue("needCompareToTianZun").trim() ) );		x.setWinPoints( Integer.decode( e.attributeValue("winPoints").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setDanId( Integer.decode( e.attributeValue("danId").trim() ) );		x.setUrl( e.attributeValue("url") );		DanRewardTemplet remove = map.put(x.getDan(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DanRewardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DanRewardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DanRewardTemplet getMin() {		return get(getMinKey());	}	public static List<DanRewardTemplet> findByDan(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDan(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByDanLv(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByLevel(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByTitle(String value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getTitle(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByGrading(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getGrading(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByFormat(String value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByCanDanDown(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getCanDanDown(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByRank(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByNeedCompareToTianZun(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getNeedCompareToTianZun(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByWinPoints(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getWinPoints(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findBySocial(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByAwards(String value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByDanId(int value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDanId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DanRewardTemplet> findByUrl(String value) {
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByDan() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDan();
		}
		return all;
	}
	public static int[] getArrayByDanLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanLv();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static String[] getArrayByTitle() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getTitle();
		}
		return all;
	}
	public static int[] getArrayByGrading() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getGrading();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByCanDanDown() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getCanDanDown();
		}
		return all;
	}
	public static int[] getArrayByRank() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}
	public static int[] getArrayByNeedCompareToTianZun() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getNeedCompareToTianZun();
		}
		return all;
	}
	public static int[] getArrayByWinPoints() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getWinPoints();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByDanId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanId();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListByDan() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDan());
		}
		return all;
	}
	public static List<Integer> getListByDanLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDanLv());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<String> getListByTitle() {
		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getTitle());
		}
		return all;
	}
	public static List<Integer> getListByGrading() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getGrading());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByCanDanDown() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getCanDanDown());
		}
		return all;
	}
	public static List<Integer> getListByRank() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}
	public static List<Integer> getListByNeedCompareToTianZun() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getNeedCompareToTianZun());
		}
		return all;
	}
	public static List<Integer> getListByWinPoints() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getWinPoints());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByDanId() {
		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDanId());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}