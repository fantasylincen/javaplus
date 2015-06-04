//[神兽]神兽package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class DogzTempletConfig {	private static Map<Integer, DogzTemplet> map;	private static List<Integer> keys;	private static List<DogzTemplet> all;	static {		load();	}	public static List<DogzTemplet> getAll() {		return new ArrayList<DogzTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/DogzConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, DogzTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																DogzTempletConfig.map = map;		DogzTempletConfig.keys = keys;																List<DogzTemplet> all = new ArrayList<DogzTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		DogzTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, DogzTemplet> map) {		DogzTemplet x = new DogzTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setDogzId( Integer.decode( e.attributeValue("dogzId").trim() ) );		x.setDogzName( e.attributeValue("dogzName") );		x.setEndName( e.attributeValue("endName") );		x.setOpen( Integer.decode( e.attributeValue("open").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setName( e.attributeValue("name") );		x.setInitLevel( Integer.decode( e.attributeValue("initLevel").trim() ) );		x.setInitAngry( Integer.decode( e.attributeValue("initAngry").trim() ) );		x.setAttribute( Integer.decode( e.attributeValue("attribute").trim() ) );		x.setSuffId( Integer.decode( e.attributeValue("suffId").trim() ) );		x.setEffectOdds( Float.parseFloat( e.attributeValue("effectOdds").trim() ) );		x.setProtagonistLv( Integer.decode( e.attributeValue("protagonistLv").trim() ) );		x.setDogzSource( e.attributeValue("dogzSource") );		x.setFormat( e.attributeValue("format") );		x.setPicType( Integer.decode( e.attributeValue("picType").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setCommonSkill( Integer.decode( e.attributeValue("commonSkill").trim() ) );		x.setDogzSikll( Integer.decode( e.attributeValue("dogzSikll").trim() ) );		x.setAttackN( Integer.decode( e.attributeValue("attackN").trim() ) );		x.setMAttackN( Integer.decode( e.attributeValue("mAttackN").trim() ) );		x.setDescription( e.attributeValue("description") );		DogzTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static DogzTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static DogzTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static DogzTemplet getMin() {		return get(getMinKey());	}	public static List<DogzTemplet> findById(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByDogzId(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getDogzId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByDogzName(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getDogzName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByEndName(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByOpen(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByStepAgo(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByToolUseType(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByAccordUse(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByBackpack(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByRetain(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByResid(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByPurpose(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByName(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByInitLevel(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getInitLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByInitAngry(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getInitAngry(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByAttribute(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getAttribute(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findBySuffId(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getSuffId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByEffectOdds(float value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getEffectOdds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByProtagonistLv(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getProtagonistLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByDogzSource(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getDogzSource(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByFormat(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByPicType(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getPicType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByPrestrain(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByUrl(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByCommonSkill(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getCommonSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByDogzSikll(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getDogzSikll(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByAttackN(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getAttackN(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByMAttackN(int value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getMAttackN(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<DogzTemplet> findByDescription(String value) {
		List<DogzTemplet> all = new ArrayList<DogzTemplet>();
		for (DogzTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByDogzId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getDogzId();
		}
		return all;
	}
	public static String[] getArrayByDogzName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getDogzName();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByOpen() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getOpen();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByInitLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getInitLevel();
		}
		return all;
	}
	public static int[] getArrayByInitAngry() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getInitAngry();
		}
		return all;
	}
	public static int[] getArrayByAttribute() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getAttribute();
		}
		return all;
	}
	public static int[] getArrayBySuffId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getSuffId();
		}
		return all;
	}
	public static float[] getArrayByEffectOdds() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getEffectOdds();
		}
		return all;
	}
	public static int[] getArrayByProtagonistLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getProtagonistLv();
		}
		return all;
	}
	public static String[] getArrayByDogzSource() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getDogzSource();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByPicType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getPicType();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static int[] getArrayByCommonSkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getCommonSkill();
		}
		return all;
	}
	public static int[] getArrayByDogzSikll() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getDogzSikll();
		}
		return all;
	}
	public static int[] getArrayByAttackN() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getAttackN();
		}
		return all;
	}
	public static int[] getArrayByMAttackN() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getMAttackN();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByDogzId() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getDogzId());
		}
		return all;
	}
	public static List<String> getListByDogzName() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getDogzName());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByOpen() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getOpen());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByInitLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getInitLevel());
		}
		return all;
	}
	public static List<Integer> getListByInitAngry() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getInitAngry());
		}
		return all;
	}
	public static List<Integer> getListByAttribute() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getAttribute());
		}
		return all;
	}
	public static List<Integer> getListBySuffId() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getSuffId());
		}
		return all;
	}
	public static List<Float> getListByEffectOdds() {
		List<Float> all = new ArrayList<Float>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getEffectOdds());
		}
		return all;
	}
	public static List<Integer> getListByProtagonistLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getProtagonistLv());
		}
		return all;
	}
	public static List<String> getListByDogzSource() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getDogzSource());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByPicType() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getPicType());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<Integer> getListByCommonSkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getCommonSkill());
		}
		return all;
	}
	public static List<Integer> getListByDogzSikll() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getDogzSikll());
		}
		return all;
	}
	public static List<Integer> getListByAttackN() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getAttackN());
		}
		return all;
	}
	public static List<Integer> getListByMAttackN() {
		List<Integer> all = new ArrayList<Integer>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getMAttackN());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (DogzTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}