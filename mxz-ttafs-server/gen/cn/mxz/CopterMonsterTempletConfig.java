//[奇遇]35[渡天劫]怪物配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterMonsterTempletConfig {	private static Map<Integer, CopterMonsterTemplet> map;	private static List<Integer> keys;	private static List<CopterMonsterTemplet> all;	static {		load();	}	public static List<CopterMonsterTemplet> getAll() {		return new ArrayList<CopterMonsterTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterMonsterConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterMonsterTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterMonsterTempletConfig.map = map;		CopterMonsterTempletConfig.keys = keys;																List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterMonsterTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterMonsterTemplet> map) {		CopterMonsterTemplet x = new CopterMonsterTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setProfession( e.attributeValue("profession") );		x.setArise( Integer.decode( e.attributeValue("arise").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setCrit( Integer.decode( e.attributeValue("crit").trim() ) );		x.setDodge( Integer.decode( e.attributeValue("dodge").trim() ) );		x.setBlock( Integer.decode( e.attributeValue("block").trim() ) );		x.setRCrit( Integer.decode( e.attributeValue("rCrit").trim() ) );		x.setHit( Integer.decode( e.attributeValue("hit").trim() ) );		x.setRBlock( Integer.decode( e.attributeValue("rBlock").trim() ) );		x.setCritAddition( Integer.decode( e.attributeValue("critAddition").trim() ) );		x.setMagic( Integer.decode( e.attributeValue("magic").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setCommonSkill( Integer.decode( e.attributeValue("commonSkill").trim() ) );		x.setSkill( Integer.decode( e.attributeValue("skill").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setAttribute( Integer.decode( e.attributeValue("attribute").trim() ) );		x.setSuffId( Integer.decode( e.attributeValue("suffId").trim() ) );		x.setEffectOdds( Float.parseFloat( e.attributeValue("effectOdds").trim() ) );		x.setQualityAdd( Integer.decode( e.attributeValue("qualityAdd").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setCategory( Integer.decode( e.attributeValue("category").trim() ) );		x.setProfessionId( Integer.decode( e.attributeValue("professionId").trim() ) );		CopterMonsterTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterMonsterTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterMonsterTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterMonsterTemplet getMin() {		return get(getMinKey());	}	public static List<CopterMonsterTemplet> findById(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByName(String value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByResid(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByProfession(String value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getProfession(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByArise(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getArise(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByStep(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByHp(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByAttack(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByMAttack(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByDefend(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByMDefend(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findBySpeed(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByCrit(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByDodge(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByBlock(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByRCrit(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByHit(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByRBlock(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByCritAddition(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByMagic(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByLevel(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByCommonSkill(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getCommonSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findBySkill(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByDescription(String value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByAttribute(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getAttribute(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findBySuffId(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getSuffId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByEffectOdds(float value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getEffectOdds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByQualityAdd(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getQualityAdd(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByQuality(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByCategory(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterMonsterTemplet> findByProfessionId(int value) {
		List<CopterMonsterTemplet> all = new ArrayList<CopterMonsterTemplet>();
		for (CopterMonsterTemplet f : getAll()) {
			if(equals(f.getProfessionId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByProfession() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getProfession();
		}
		return all;
	}
	public static int[] getArrayByArise() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getArise();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static int[] getArrayByCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}
	public static int[] getArrayByDodge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}
	public static int[] getArrayByBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}
	public static int[] getArrayByRCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}
	public static int[] getArrayByHit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}
	public static int[] getArrayByRBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}
	public static int[] getArrayByCritAddition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}
	public static int[] getArrayByMagic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByCommonSkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getCommonSkill();
		}
		return all;
	}
	public static int[] getArrayBySkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getSkill();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static int[] getArrayByAttribute() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getAttribute();
		}
		return all;
	}
	public static int[] getArrayBySuffId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getSuffId();
		}
		return all;
	}
	public static float[] getArrayByEffectOdds() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getEffectOdds();
		}
		return all;
	}
	public static int[] getArrayByQualityAdd() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getQualityAdd();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByCategory() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}
	public static int[] getArrayByProfessionId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterMonsterTemplet f = get(keys.get(i));
			all[i] = f.getProfessionId();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByProfession() {
		List<String> all = new ArrayList<String>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getProfession());
		}
		return all;
	}
	public static List<Integer> getListByArise() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getArise());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<Integer> getListByCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}
	public static List<Integer> getListByDodge() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}
	public static List<Integer> getListByBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}
	public static List<Integer> getListByRCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}
	public static List<Integer> getListByHit() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}
	public static List<Integer> getListByRBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}
	public static List<Integer> getListByCritAddition() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}
	public static List<Integer> getListByMagic() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByCommonSkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getCommonSkill());
		}
		return all;
	}
	public static List<Integer> getListBySkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getSkill());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<Integer> getListByAttribute() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getAttribute());
		}
		return all;
	}
	public static List<Integer> getListBySuffId() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getSuffId());
		}
		return all;
	}
	public static List<Float> getListByEffectOdds() {
		List<Float> all = new ArrayList<Float>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getEffectOdds());
		}
		return all;
	}
	public static List<Integer> getListByQualityAdd() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getQualityAdd());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByCategory() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}
	public static List<Integer> getListByProfessionId() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterMonsterTemplet f : getAll()) {
			all.add(f.getProfessionId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}