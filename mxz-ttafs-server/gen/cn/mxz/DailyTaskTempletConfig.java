//[任务]日常任务package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DailyTaskTempletConfig {	private static Map<Integer, DailyTaskTemplet> map;	private static List<Integer> keys;	private static List<DailyTaskTemplet> all;	static {		load();	}	public static List<DailyTaskTemplet> getAll() {		return new ArrayList<DailyTaskTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DailyTaskConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DailyTaskTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DailyTaskTempletConfig.map = map;		DailyTaskTempletConfig.keys = keys;																List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DailyTaskTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DailyTaskTemplet> map) {		DailyTaskTemplet x = new DailyTaskTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setIsOpenDefault( Integer.decode( e.attributeValue("isOpenDefault").trim() ) );		x.setCode( e.attributeValue("code") );		x.setArg1( e.attributeValue("arg1") );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setIsDaily( Integer.decode( e.attributeValue("isDaily").trim() ) );		x.setName( e.attributeValue("name") );		x.setDescription( e.attributeValue("description") );		x.setBeizhu( e.attributeValue("beizhu") );		x.setLink( e.attributeValue("link") );		x.setUserLevelNeed( Integer.decode( e.attributeValue("userLevelNeed").trim() ) );		x.setIsCheck( Integer.decode( e.attributeValue("isCheck").trim() ) );		x.setIntegral( Integer.decode( e.attributeValue("integral").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setUrl( e.attributeValue("url") );		DailyTaskTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DailyTaskTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DailyTaskTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DailyTaskTemplet getMin() {		return get(getMinKey());	}	public static List<DailyTaskTemplet> findById(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByIsOpenDefault(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsOpenDefault(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByCode(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByArg1(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getArg1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByMax(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByResid(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByPrestrain(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByFormat(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByIsDaily(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsDaily(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByName(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByDescription(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByBeizhu(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getBeizhu(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByLink(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByUserLevelNeed(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getUserLevelNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByIsCheck(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsCheck(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByIntegral(int value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIntegral(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByAwards(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DailyTaskTemplet> findByUrl(String value) {
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByIsOpenDefault() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsOpenDefault();
		}
		return all;
	}
	public static String[] getArrayByCode() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}
	public static String[] getArrayByArg1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getArg1();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByIsDaily() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsDaily();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByBeizhu() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getBeizhu();
		}
		return all;
	}
	public static String[] getArrayByLink() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}
	public static int[] getArrayByUserLevelNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getUserLevelNeed();
		}
		return all;
	}
	public static int[] getArrayByIsCheck() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsCheck();
		}
		return all;
	}
	public static int[] getArrayByIntegral() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIntegral();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByIsOpenDefault() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsOpenDefault());
		}
		return all;
	}
	public static List<String> getListByCode() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}
	public static List<String> getListByArg1() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getArg1());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByIsDaily() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsDaily());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByBeizhu() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getBeizhu());
		}
		return all;
	}
	public static List<String> getListByLink() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}
	public static List<Integer> getListByUserLevelNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getUserLevelNeed());
		}
		return all;
	}
	public static List<Integer> getListByIsCheck() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsCheck());
		}
		return all;
	}
	public static List<Integer> getListByIntegral() {
		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIntegral());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}