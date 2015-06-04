//[道具]装备package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class EquipmentTempletConfig {	private static Map<Integer, EquipmentTemplet> map;	private static List<Integer> keys;	private static List<EquipmentTemplet> all;	static {		load();	}	public static List<EquipmentTemplet> getAll() {		return new ArrayList<EquipmentTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/EquipmentConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, EquipmentTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																EquipmentTempletConfig.map = map;		EquipmentTempletConfig.keys = keys;																List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		EquipmentTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, EquipmentTemplet> map) {		EquipmentTemplet x = new EquipmentTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setEndName( e.attributeValue("endName") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setDressLevel( Integer.decode( e.attributeValue("dressLevel").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setSpot( Integer.decode( e.attributeValue("spot").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setPartName( e.attributeValue("partName") );		x.setBaseAdditionType( Integer.decode( e.attributeValue("baseAdditionType").trim() ) );		x.setAdditionValue1( Integer.decode( e.attributeValue("additionValue1").trim() ) );		x.setAdditionGrow1( Float.parseFloat( e.attributeValue("additionGrow1").trim() ) );		x.setAdditionType2( Integer.decode( e.attributeValue("additionType2").trim() ) );		x.setAdditionPercent2( Float.parseFloat( e.attributeValue("additionPercent2").trim() ) );		x.setAdditionValue2( Float.parseFloat( e.attributeValue("additionValue2").trim() ) );		x.setAdditionType3( Integer.decode( e.attributeValue("additionType3").trim() ) );		x.setAdditionPercent3( Float.parseFloat( e.attributeValue("additionPercent3").trim() ) );		x.setAdditionValue3( Float.parseFloat( e.attributeValue("additionValue3").trim() ) );		x.setSuitId( Integer.decode( e.attributeValue("suitId").trim() ) );		x.setSuitname( e.attributeValue("suitname") );		x.setSpotName( e.attributeValue("spotName") );		x.setArticleId( Integer.decode( e.attributeValue("articleId").trim() ) );		x.setEquipmentId( e.attributeValue("equipmentId") );		x.setScrollId( e.attributeValue("scrollId") );		x.setStuffId( e.attributeValue("stuffId") );		x.setChip( e.attributeValue("chip") );		x.setMaxLvl( Integer.decode( e.attributeValue("maxLvl").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setSocialGrow( Float.parseFloat( e.attributeValue("socialGrow").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setGiveable( Integer.decode( e.attributeValue("giveable").trim() ) );		x.setSellPrice( Integer.decode( e.attributeValue("sellPrice").trim() ) );		x.setAddUp( Integer.decode( e.attributeValue("addUp").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setPurpose( e.attributeValue("purpose") );		x.setDescription( e.attributeValue("description") );		x.setSketch( e.attributeValue("sketch") );		EquipmentTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static EquipmentTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static EquipmentTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static EquipmentTemplet getMin() {		return get(getMinKey());	}	public static List<EquipmentTemplet> findById(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByName(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByEndName(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByToolUseType(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAccordUse(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByBackpack(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByRetain(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByDressLevel(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getDressLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByFormat(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySpot(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSpot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByQuality(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByStepAgo(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByPartName(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getPartName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByBaseAdditionType(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getBaseAdditionType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionValue1(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionValue1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionGrow1(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionGrow1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionType2(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionType2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionPercent2(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionPercent2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionValue2(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionValue2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionType3(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionType3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionPercent3(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionPercent3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAdditionValue3(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAdditionValue3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySuitId(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSuitId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySuitname(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSuitname(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySpotName(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSpotName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByArticleId(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getArticleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByEquipmentId(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getEquipmentId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByScrollId(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getScrollId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByStuffId(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getStuffId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByChip(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getChip(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByMaxLvl(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getMaxLvl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySocial(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySocialGrow(float value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSocialGrow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByResid(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByPrestrain(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByGiveable(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getGiveable(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySellPrice(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSellPrice(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByAddUp(int value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getAddUp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByUrl(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByPurpose(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findByDescription(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<EquipmentTemplet> findBySketch(String value) {
		List<EquipmentTemplet> all = new ArrayList<EquipmentTemplet>();
		for (EquipmentTemplet f : getAll()) {
			if(equals(f.getSketch(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayByDressLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getDressLevel();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayBySpot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSpot();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static String[] getArrayByPartName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getPartName();
		}
		return all;
	}
	public static int[] getArrayByBaseAdditionType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getBaseAdditionType();
		}
		return all;
	}
	public static int[] getArrayByAdditionValue1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionValue1();
		}
		return all;
	}
	public static float[] getArrayByAdditionGrow1() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionGrow1();
		}
		return all;
	}
	public static int[] getArrayByAdditionType2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionType2();
		}
		return all;
	}
	public static float[] getArrayByAdditionPercent2() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionPercent2();
		}
		return all;
	}
	public static float[] getArrayByAdditionValue2() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionValue2();
		}
		return all;
	}
	public static int[] getArrayByAdditionType3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionType3();
		}
		return all;
	}
	public static float[] getArrayByAdditionPercent3() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionPercent3();
		}
		return all;
	}
	public static float[] getArrayByAdditionValue3() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAdditionValue3();
		}
		return all;
	}
	public static int[] getArrayBySuitId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSuitId();
		}
		return all;
	}
	public static String[] getArrayBySuitname() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSuitname();
		}
		return all;
	}
	public static String[] getArrayBySpotName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSpotName();
		}
		return all;
	}
	public static int[] getArrayByArticleId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getArticleId();
		}
		return all;
	}
	public static String[] getArrayByEquipmentId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentId();
		}
		return all;
	}
	public static String[] getArrayByScrollId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getScrollId();
		}
		return all;
	}
	public static String[] getArrayByStuffId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getStuffId();
		}
		return all;
	}
	public static String[] getArrayByChip() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getChip();
		}
		return all;
	}
	public static int[] getArrayByMaxLvl() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getMaxLvl();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static float[] getArrayBySocialGrow() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSocialGrow();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static int[] getArrayByGiveable() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getGiveable();
		}
		return all;
	}
	public static int[] getArrayBySellPrice() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSellPrice();
		}
		return all;
	}
	public static int[] getArrayByAddUp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getAddUp();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayBySketch() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EquipmentTemplet f = get(keys.get(i));
			all[i] = f.getSketch();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListByDressLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getDressLevel());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListBySpot() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSpot());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<String> getListByPartName() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getPartName());
		}
		return all;
	}
	public static List<Integer> getListByBaseAdditionType() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getBaseAdditionType());
		}
		return all;
	}
	public static List<Integer> getListByAdditionValue1() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionValue1());
		}
		return all;
	}
	public static List<Float> getListByAdditionGrow1() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionGrow1());
		}
		return all;
	}
	public static List<Integer> getListByAdditionType2() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionType2());
		}
		return all;
	}
	public static List<Float> getListByAdditionPercent2() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionPercent2());
		}
		return all;
	}
	public static List<Float> getListByAdditionValue2() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionValue2());
		}
		return all;
	}
	public static List<Integer> getListByAdditionType3() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionType3());
		}
		return all;
	}
	public static List<Float> getListByAdditionPercent3() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionPercent3());
		}
		return all;
	}
	public static List<Float> getListByAdditionValue3() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAdditionValue3());
		}
		return all;
	}
	public static List<Integer> getListBySuitId() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSuitId());
		}
		return all;
	}
	public static List<String> getListBySuitname() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSuitname());
		}
		return all;
	}
	public static List<String> getListBySpotName() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSpotName());
		}
		return all;
	}
	public static List<Integer> getListByArticleId() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getArticleId());
		}
		return all;
	}
	public static List<String> getListByEquipmentId() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getEquipmentId());
		}
		return all;
	}
	public static List<String> getListByScrollId() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getScrollId());
		}
		return all;
	}
	public static List<String> getListByStuffId() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getStuffId());
		}
		return all;
	}
	public static List<String> getListByChip() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getChip());
		}
		return all;
	}
	public static List<Integer> getListByMaxLvl() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getMaxLvl());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<Float> getListBySocialGrow() {
		List<Float> all = new ArrayList<Float>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSocialGrow());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<Integer> getListByGiveable() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getGiveable());
		}
		return all;
	}
	public static List<Integer> getListBySellPrice() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSellPrice());
		}
		return all;
	}
	public static List<Integer> getListByAddUp() {
		List<Integer> all = new ArrayList<Integer>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getAddUp());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListBySketch() {
		List<String> all = new ArrayList<String>();
		for (EquipmentTemplet f : getAll()) {
			all.add(f.getSketch());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}