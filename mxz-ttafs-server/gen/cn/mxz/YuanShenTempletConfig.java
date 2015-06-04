//[元神]元神库package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class YuanShenTempletConfig {	private static Map<Integer, YuanShenTemplet> map;	private static List<Integer> keys;	private static List<YuanShenTemplet> all;	static {		load();	}	public static List<YuanShenTemplet> getAll() {		return new ArrayList<YuanShenTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/YuanShenConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, YuanShenTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																YuanShenTempletConfig.map = map;		YuanShenTempletConfig.keys = keys;																List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		YuanShenTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, YuanShenTemplet> map) {		YuanShenTemplet x = new YuanShenTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setShadowName( e.attributeValue("shadowName") );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setWeight( Integer.decode( e.attributeValue("weight").trim() ) );		x.setMagic( Integer.decode( e.attributeValue("magic").trim() ) );		x.setBaseAdditionType( Integer.decode( e.attributeValue("baseAdditionType").trim() ) );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setCrit( Integer.decode( e.attributeValue("crit").trim() ) );		x.setDodge( Integer.decode( e.attributeValue("dodge").trim() ) );		x.setBlock( Integer.decode( e.attributeValue("block").trim() ) );		x.setRCrit( Integer.decode( e.attributeValue("rCrit").trim() ) );		x.setHit( Integer.decode( e.attributeValue("hit").trim() ) );		x.setRBlock( Integer.decode( e.attributeValue("rBlock").trim() ) );		x.setCritAddition( Integer.decode( e.attributeValue("critAddition").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setSocialGrow( Float.parseFloat( e.attributeValue("socialGrow").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setDescription( e.attributeValue("description") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setFormat( e.attributeValue("format") );		YuanShenTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static YuanShenTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static YuanShenTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static YuanShenTemplet getMin() {		return get(getMinKey());	}	public static List<YuanShenTemplet> findById(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByShadowName(String value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getShadowName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByStep(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByWeight(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByMagic(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByBaseAdditionType(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getBaseAdditionType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByHp(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByAttack(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByMAttack(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByDefend(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByMDefend(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findBySpeed(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByCrit(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByDodge(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByBlock(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByRCrit(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByHit(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByRBlock(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByCritAddition(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findBySocial(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findBySocialGrow(float value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getSocialGrow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByUrl(String value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByDescription(String value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByResid(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByPrestrain(int value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<YuanShenTemplet> findByFormat(String value) {
		List<YuanShenTemplet> all = new ArrayList<YuanShenTemplet>();
		for (YuanShenTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByShadowName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getShadowName();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}
	public static int[] getArrayByMagic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}
	public static int[] getArrayByBaseAdditionType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getBaseAdditionType();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static int[] getArrayByCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}
	public static int[] getArrayByDodge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}
	public static int[] getArrayByBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}
	public static int[] getArrayByRCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}
	public static int[] getArrayByHit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}
	public static int[] getArrayByRBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}
	public static int[] getArrayByCritAddition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static float[] getArrayBySocialGrow() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getSocialGrow();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByShadowName() {
		List<String> all = new ArrayList<String>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getShadowName());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
	public static List<Integer> getListByMagic() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
	public static List<Integer> getListByBaseAdditionType() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getBaseAdditionType());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<Integer> getListByCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}
	public static List<Integer> getListByDodge() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}
	public static List<Integer> getListByBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}
	public static List<Integer> getListByRCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}
	public static List<Integer> getListByHit() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}
	public static List<Integer> getListByRBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}
	public static List<Integer> getListByCritAddition() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<Float> getListBySocialGrow() {
		List<Float> all = new ArrayList<Float>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getSocialGrow());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (YuanShenTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}