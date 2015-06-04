//[道具]材料package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class StuffTempletConfig {	private static Map<Integer, StuffTemplet> map;	private static List<Integer> keys;	private static List<StuffTemplet> all;	static {		load();	}	public static List<StuffTemplet> getAll() {		return new ArrayList<StuffTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/StuffConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, StuffTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																StuffTempletConfig.map = map;		StuffTempletConfig.keys = keys;																List<StuffTemplet> all = new ArrayList<StuffTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		StuffTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, StuffTemplet> map) {		StuffTemplet x = new StuffTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setDuobaoname( e.attributeValue("duobaoname") );		x.setEndName( e.attributeValue("endName") );		x.setOrder( Integer.decode( e.attributeValue("order").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setEquipmentType( Integer.decode( e.attributeValue("equipmentType").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setArticleId( Integer.decode( e.attributeValue("articleId").trim() ) );		x.setEquipAdvId( Integer.decode( e.attributeValue("equipAdvId").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setSource( e.attributeValue("source") );		x.setAddUp( Integer.decode( e.attributeValue("addUp").trim() ) );		x.setSellPrice( Integer.decode( e.attributeValue("sellPrice").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setSpot( Integer.decode( e.attributeValue("spot").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setDescription( e.attributeValue("description") );		x.setSketch( e.attributeValue("sketch") );		StuffTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static StuffTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static StuffTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static StuffTemplet getMin() {		return get(getMinKey());	}	public static List<StuffTemplet> findById(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByName(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByDuobaoname(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getDuobaoname(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByEndName(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByOrder(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getOrder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByAwards(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByToolUseType(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByEquipmentType(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getEquipmentType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByType(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByAccordUse(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByBackpack(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByRetain(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByArticleId(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getArticleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByEquipAdvId(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getEquipAdvId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByQuality(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByStepAgo(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByPurpose(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findBySource(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getSource(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByAddUp(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getAddUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findBySellPrice(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getSellPrice(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByFormat(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findBySpot(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getSpot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByResid(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByPrestrain(int value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByUrl(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findByDescription(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<StuffTemplet> findBySketch(String value) {
		List<StuffTemplet> all = new ArrayList<StuffTemplet>();
		for (StuffTemplet f : getAll()) {
			if(equals(f.getSketch(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByDuobaoname() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getDuobaoname();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByOrder() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getOrder();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByEquipmentType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentType();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayByArticleId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getArticleId();
		}
		return all;
	}
	public static int[] getArrayByEquipAdvId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getEquipAdvId();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayBySource() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getSource();
		}
		return all;
	}
	public static int[] getArrayByAddUp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getAddUp();
		}
		return all;
	}
	public static int[] getArrayBySellPrice() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getSellPrice();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayBySpot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getSpot();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayBySketch() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			StuffTemplet f = get(keys.get(i));
			all[i] = f.getSketch();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByDuobaoname() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getDuobaoname());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByOrder() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getOrder());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByEquipmentType() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getEquipmentType());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListByArticleId() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getArticleId());
		}
		return all;
	}
	public static List<Integer> getListByEquipAdvId() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getEquipAdvId());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListBySource() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getSource());
		}
		return all;
	}
	public static List<Integer> getListByAddUp() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getAddUp());
		}
		return all;
	}
	public static List<Integer> getListBySellPrice() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getSellPrice());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListBySpot() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getSpot());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListBySketch() {
		List<String> all = new ArrayList<String>();
		for (StuffTemplet f : getAll()) {
			all.add(f.getSketch());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}