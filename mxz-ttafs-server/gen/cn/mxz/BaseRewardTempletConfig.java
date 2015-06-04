//[奖励]基本奖励package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BaseRewardTempletConfig {	private static Map<Integer, BaseRewardTemplet> map;	private static List<Integer> keys;	private static List<BaseRewardTemplet> all;	static {		load();	}	public static List<BaseRewardTemplet> getAll() {		return new ArrayList<BaseRewardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BaseRewardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BaseRewardTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BaseRewardTempletConfig.map = map;		BaseRewardTempletConfig.keys = keys;																List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BaseRewardTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BaseRewardTemplet> map) {		BaseRewardTemplet x = new BaseRewardTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setEndName( e.attributeValue("endName") );		x.setDescription( e.attributeValue("description") );		x.setDescription1( e.attributeValue("description1") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setSpot( Integer.decode( e.attributeValue("spot").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setUrl( e.attributeValue("url") );		BaseRewardTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BaseRewardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BaseRewardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BaseRewardTemplet getMin() {		return get(getMinKey());	}	public static List<BaseRewardTemplet> findById(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByName(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByEndName(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByDescription(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByDescription1(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getDescription1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByToolUseType(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByAccordUse(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByBackpack(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByRetain(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findBySpot(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getSpot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByResid(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByPrestrain(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByPurpose(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByStepAgo(int value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByFormat(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BaseRewardTemplet> findByUrl(String value) {
		List<BaseRewardTemplet> all = new ArrayList<BaseRewardTemplet>();
		for (BaseRewardTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByDescription1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getDescription1();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayBySpot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getSpot();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BaseRewardTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByDescription1() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getDescription1());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListBySpot() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getSpot());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (BaseRewardTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}