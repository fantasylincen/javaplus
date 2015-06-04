//[奇遇]33[boss]属性基础数据表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BossBasicDataTempletConfig {	private static Map<Integer, BossBasicDataTemplet> map;	private static List<Integer> keys;	private static List<BossBasicDataTemplet> all;	static {		load();	}	public static List<BossBasicDataTemplet> getAll() {		return new ArrayList<BossBasicDataTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BossBasicDataConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BossBasicDataTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BossBasicDataTempletConfig.map = map;		BossBasicDataTempletConfig.keys = keys;																List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BossBasicDataTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BossBasicDataTemplet> map) {		BossBasicDataTemplet x = new BossBasicDataTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setSection( e.attributeValue("section") );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setCrit( Integer.decode( e.attributeValue("crit").trim() ) );		x.setDodge( Integer.decode( e.attributeValue("dodge").trim() ) );		x.setBlock( Integer.decode( e.attributeValue("block").trim() ) );		x.setRCrit( Integer.decode( e.attributeValue("rCrit").trim() ) );		x.setHit( Integer.decode( e.attributeValue("hit").trim() ) );		x.setRBlock( Integer.decode( e.attributeValue("rBlock").trim() ) );		x.setCritAddition( Integer.decode( e.attributeValue("critAddition").trim() ) );		x.setSkill( Integer.decode( e.attributeValue("skill").trim() ) );		x.setMagic( Integer.decode( e.attributeValue("magic").trim() ) );		BossBasicDataTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BossBasicDataTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BossBasicDataTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BossBasicDataTemplet getMin() {		return get(getMinKey());	}	public static List<BossBasicDataTemplet> findById(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findBySection(String value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSection(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByHp(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByAttack(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByMAttack(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByDefend(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByMDefend(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findBySpeed(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByCrit(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByDodge(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByBlock(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByRCrit(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByHit(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByRBlock(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByCritAddition(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findBySkill(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BossBasicDataTemplet> findByMagic(int value) {
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayBySection() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSection();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static int[] getArrayByCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}
	public static int[] getArrayByDodge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}
	public static int[] getArrayByBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}
	public static int[] getArrayByRCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}
	public static int[] getArrayByHit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}
	public static int[] getArrayByRBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}
	public static int[] getArrayByCritAddition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}
	public static int[] getArrayBySkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSkill();
		}
		return all;
	}
	public static int[] getArrayByMagic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListBySection() {
		List<String> all = new ArrayList<String>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSection());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<Integer> getListByCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}
	public static List<Integer> getListByDodge() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}
	public static List<Integer> getListByBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}
	public static List<Integer> getListByRCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}
	public static List<Integer> getListByHit() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}
	public static List<Integer> getListByRBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}
	public static List<Integer> getListByCritAddition() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}
	public static List<Integer> getListBySkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSkill());
		}
		return all;
	}
	public static List<Integer> getListByMagic() {
		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}