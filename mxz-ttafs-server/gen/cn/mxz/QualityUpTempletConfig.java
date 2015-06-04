//[神将]属性成长表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class QualityUpTempletConfig {	private static Map<Integer, QualityUpTemplet> map;	private static List<Integer> keys;	private static List<QualityUpTemplet> all;	static {		load();	}	public static List<QualityUpTemplet> getAll() {		return new ArrayList<QualityUpTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/QualityUpConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, QualityUpTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																QualityUpTempletConfig.map = map;		QualityUpTempletConfig.keys = keys;																List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		QualityUpTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, QualityUpTemplet> map) {		QualityUpTemplet x = new QualityUpTemplet();		x.setRank( Integer.decode( e.attributeValue("rank").trim() ) );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setCrit( Integer.decode( e.attributeValue("crit").trim() ) );		x.setDodge( Integer.decode( e.attributeValue("dodge").trim() ) );		x.setBlock( Integer.decode( e.attributeValue("block").trim() ) );		x.setRCrit( Integer.decode( e.attributeValue("rCrit").trim() ) );		x.setHit( Integer.decode( e.attributeValue("hit").trim() ) );		x.setRBlock( Integer.decode( e.attributeValue("rBlock").trim() ) );		x.setCritAddition( Integer.decode( e.attributeValue("critAddition").trim() ) );		x.setMagic( Integer.decode( e.attributeValue("magic").trim() ) );		QualityUpTemplet remove = map.put(x.getRank(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static QualityUpTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static QualityUpTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static QualityUpTemplet getMin() {		return get(getMinKey());	}	public static List<QualityUpTemplet> findByRank(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByHp(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByAttack(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByMAttack(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByDefend(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByMDefend(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findBySpeed(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByCrit(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByDodge(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByBlock(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByRCrit(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByHit(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByRBlock(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByCritAddition(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityUpTemplet> findByMagic(int value) {
		List<QualityUpTemplet> all = new ArrayList<QualityUpTemplet>();
		for (QualityUpTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByRank() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static int[] getArrayByCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}
	public static int[] getArrayByDodge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}
	public static int[] getArrayByBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}
	public static int[] getArrayByRCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}
	public static int[] getArrayByHit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}
	public static int[] getArrayByRBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}
	public static int[] getArrayByCritAddition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}
	public static int[] getArrayByMagic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityUpTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}
	public static List<Integer> getListByRank() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<Integer> getListByCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}
	public static List<Integer> getListByDodge() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}
	public static List<Integer> getListByBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}
	public static List<Integer> getListByRCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}
	public static List<Integer> getListByHit() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}
	public static List<Integer> getListByRBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}
	public static List<Integer> getListByCritAddition() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}
	public static List<Integer> getListByMagic() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityUpTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}