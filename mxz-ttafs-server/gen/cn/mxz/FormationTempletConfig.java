//[阵法]阵型package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FormationTempletConfig {	private static Map<Integer, FormationTemplet> map;	private static List<Integer> keys;	private static List<FormationTemplet> all;	static {		load();	}	public static List<FormationTemplet> getAll() {		return new ArrayList<FormationTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FormationConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FormationTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FormationTempletConfig.map = map;		FormationTempletConfig.keys = keys;																List<FormationTemplet> all = new ArrayList<FormationTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FormationTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FormationTemplet> map) {		FormationTemplet x = new FormationTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setEndName( e.attributeValue("endName") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setSellPrice( Integer.decode( e.attributeValue("sellPrice").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setGiveable( Integer.decode( e.attributeValue("giveable").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setSpet( Integer.decode( e.attributeValue("spet").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setFrontFirst( Integer.decode( e.attributeValue("frontFirst").trim() ) );		x.setFrontFirstPar( Float.parseFloat( e.attributeValue("frontFirstPar").trim() ) );		x.setFrontFirstFixed( Integer.decode( e.attributeValue("frontFirstFixed").trim() ) );		x.setFrontMiddle( Integer.decode( e.attributeValue("frontMiddle").trim() ) );		x.setFrontMiddlePar( Float.parseFloat( e.attributeValue("frontMiddlePar").trim() ) );		x.setFrontMiddleFixed( Integer.decode( e.attributeValue("frontMiddleFixed").trim() ) );		x.setFrontTail( Integer.decode( e.attributeValue("frontTail").trim() ) );		x.setFrontTailPar( Float.parseFloat( e.attributeValue("frontTailPar").trim() ) );		x.setFrontTailFixed( Integer.decode( e.attributeValue("frontTailFixed").trim() ) );		x.setChip( e.attributeValue("chip") );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setSocialGrow( Float.parseFloat( e.attributeValue("socialGrow").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setDescription( e.attributeValue("description") );		x.setSketch( e.attributeValue("sketch") );		x.setUrl( e.attributeValue("url") );		FormationTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FormationTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FormationTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FormationTemplet getMin() {		return get(getMinKey());	}	public static List<FormationTemplet> findById(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByName(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByEndName(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByToolUseType(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByAccordUse(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByBackpack(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findBySellPrice(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getSellPrice(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByResid(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByPrestrain(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFormat(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByGiveable(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getGiveable(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByRetain(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findBySpet(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByStepAgo(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontFirst(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontFirstPar(float value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontFirstPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontFirstFixed(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontFirstFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontMiddle(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontMiddle(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontMiddlePar(float value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontMiddlePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontMiddleFixed(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontMiddleFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontTail(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontTail(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontTailPar(float value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontTailPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByFrontTailFixed(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getFrontTailFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByChip(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getChip(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findBySocial(int value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findBySocialGrow(float value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getSocialGrow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByPurpose(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByDescription(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findBySketch(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getSketch(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormationTemplet> findByUrl(String value) {
		List<FormationTemplet> all = new ArrayList<FormationTemplet>();
		for (FormationTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayBySellPrice() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getSellPrice();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByGiveable() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getGiveable();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static int[] getArrayBySpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static int[] getArrayByFrontFirst() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirst();
		}
		return all;
	}
	public static float[] getArrayByFrontFirstPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirstPar();
		}
		return all;
	}
	public static int[] getArrayByFrontFirstFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirstFixed();
		}
		return all;
	}
	public static int[] getArrayByFrontMiddle() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddle();
		}
		return all;
	}
	public static float[] getArrayByFrontMiddlePar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddlePar();
		}
		return all;
	}
	public static int[] getArrayByFrontMiddleFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddleFixed();
		}
		return all;
	}
	public static int[] getArrayByFrontTail() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontTail();
		}
		return all;
	}
	public static float[] getArrayByFrontTailPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontTailPar();
		}
		return all;
	}
	public static int[] getArrayByFrontTailFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getFrontTailFixed();
		}
		return all;
	}
	public static String[] getArrayByChip() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getChip();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static float[] getArrayBySocialGrow() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getSocialGrow();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayBySketch() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getSketch();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListBySellPrice() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getSellPrice());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByGiveable() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getGiveable());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<Integer> getListBySpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<Integer> getListByFrontFirst() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontFirst());
		}
		return all;
	}
	public static List<Float> getListByFrontFirstPar() {
		List<Float> all = new ArrayList<Float>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontFirstPar());
		}
		return all;
	}
	public static List<Integer> getListByFrontFirstFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontFirstFixed());
		}
		return all;
	}
	public static List<Integer> getListByFrontMiddle() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontMiddle());
		}
		return all;
	}
	public static List<Float> getListByFrontMiddlePar() {
		List<Float> all = new ArrayList<Float>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontMiddlePar());
		}
		return all;
	}
	public static List<Integer> getListByFrontMiddleFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontMiddleFixed());
		}
		return all;
	}
	public static List<Integer> getListByFrontTail() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontTail());
		}
		return all;
	}
	public static List<Float> getListByFrontTailPar() {
		List<Float> all = new ArrayList<Float>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontTailPar());
		}
		return all;
	}
	public static List<Integer> getListByFrontTailFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getFrontTailFixed());
		}
		return all;
	}
	public static List<String> getListByChip() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getChip());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<Float> getListBySocialGrow() {
		List<Float> all = new ArrayList<Float>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getSocialGrow());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListBySketch() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getSketch());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (FormationTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}