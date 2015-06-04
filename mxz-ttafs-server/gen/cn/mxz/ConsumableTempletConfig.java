//[道具]消耗品package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class ConsumableTempletConfig {	private static Map<Integer, ConsumableTemplet> map;	private static List<Integer> keys;	private static List<ConsumableTemplet> all;	static {		load();	}	public static List<ConsumableTemplet> getAll() {		return new ArrayList<ConsumableTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/ConsumableConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, ConsumableTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																ConsumableTempletConfig.map = map;		ConsumableTempletConfig.keys = keys;																List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		ConsumableTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, ConsumableTemplet> map) {		ConsumableTemplet x = new ConsumableTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setEndName( e.attributeValue("endName") );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setCanOpen( Integer.decode( e.attributeValue("canOpen").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setSpot( Integer.decode( e.attributeValue("spot").trim() ) );		x.setIsOpen( Integer.decode( e.attributeValue("isOpen").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setExplain( e.attributeValue("explain") );		x.setNeeds( e.attributeValue("needs") );		x.setAwards( e.attributeValue("awards") );		x.setWeight( e.attributeValue("weight") );		x.setCountMin( Integer.decode( e.attributeValue("countMin").trim() ) );		x.setCountMax( Integer.decode( e.attributeValue("countMax").trim() ) );		x.setAdditionMin( Integer.decode( e.attributeValue("additionMin").trim() ) );		x.setAdditionMax( Integer.decode( e.attributeValue("additionMax").trim() ) );		x.setCertainly( e.attributeValue("certainly") );		x.setRank( Integer.decode( e.attributeValue("rank").trim() ) );		x.setAddUp( Integer.decode( e.attributeValue("addUp").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setSource( e.attributeValue("source") );		x.setUrl( e.attributeValue("url") );		x.setLogExplain4Buy( e.attributeValue("logExplain4Buy") );		x.setDescription( e.attributeValue("description") );		x.setFormat( e.attributeValue("format") );		ConsumableTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static ConsumableTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static ConsumableTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static ConsumableTemplet getMin() {		return get(getMinKey());	}	public static List<ConsumableTemplet> findById(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByName(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByEndName(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByQuality(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByStepAgo(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByCanOpen(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getCanOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByResid(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByPrestrain(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByToolUseType(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByAccordUse(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByBackpack(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByRetain(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findBySpot(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getSpot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByIsOpen(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getIsOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByType(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByExplain(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByNeeds(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getNeeds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByAwards(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByWeight(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByCountMin(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getCountMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByCountMax(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getCountMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByAdditionMin(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getAdditionMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByAdditionMax(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getAdditionMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByCertainly(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getCertainly(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByRank(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByAddUp(int value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getAddUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByPurpose(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findBySource(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getSource(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByUrl(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByLogExplain4Buy(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getLogExplain4Buy(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByDescription(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<ConsumableTemplet> findByFormat(String value) {
		List<ConsumableTemplet> all = new ArrayList<ConsumableTemplet>();
		for (ConsumableTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static int[] getArrayByCanOpen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getCanOpen();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayBySpot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getSpot();
		}
		return all;
	}
	public static int[] getArrayByIsOpen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getIsOpen();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByExplain() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}
	public static String[] getArrayByNeeds() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getNeeds();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByWeight() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByCountMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getCountMin();
		}
		return all;
	}
	public static int[] getArrayByCountMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getCountMax();
		}
		return all;
	}
	public static int[] getArrayByAdditionMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getAdditionMin();
		}
		return all;
	}
	public static int[] getArrayByAdditionMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getAdditionMax();
		}
		return all;
	}
	public static String[] getArrayByCertainly() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getCertainly();
		}
		return all;
	}
	public static int[] getArrayByRank() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}
	public static int[] getArrayByAddUp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getAddUp();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayBySource() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getSource();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByLogExplain4Buy() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getLogExplain4Buy();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ConsumableTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<Integer> getListByCanOpen() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getCanOpen());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListBySpot() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getSpot());
		}
		return all;
	}
	public static List<Integer> getListByIsOpen() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getIsOpen());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByExplain() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
	public static List<String> getListByNeeds() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getNeeds());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByWeight() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByCountMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getCountMin());
		}
		return all;
	}
	public static List<Integer> getListByCountMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getCountMax());
		}
		return all;
	}
	public static List<Integer> getListByAdditionMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getAdditionMin());
		}
		return all;
	}
	public static List<Integer> getListByAdditionMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getAdditionMax());
		}
		return all;
	}
	public static List<String> getListByCertainly() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getCertainly());
		}
		return all;
	}
	public static List<Integer> getListByRank() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}
	public static List<Integer> getListByAddUp() {
		List<Integer> all = new ArrayList<Integer>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getAddUp());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListBySource() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getSource());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByLogExplain4Buy() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getLogExplain4Buy());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (ConsumableTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}