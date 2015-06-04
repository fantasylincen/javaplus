//[神将]神将属性颜色表package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class GodColourTempletConfig {	private static Map<Integer, GodColourTemplet> map;	private static List<Integer> keys;	private static List<GodColourTemplet> all;	static {		load();	}	public static List<GodColourTemplet> getAll() {		return new ArrayList<GodColourTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/GodColourConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, GodColourTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																GodColourTempletConfig.map = map;		GodColourTempletConfig.keys = keys;																List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		GodColourTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, GodColourTemplet> map) {		GodColourTemplet x = new GodColourTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setColor( e.attributeValue("color") );		GodColourTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static GodColourTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static GodColourTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static GodColourTemplet getMin() {		return get(getMinKey());	}	public static List<GodColourTemplet> findById(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByHp(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByAttack(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByMAttack(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByDefend(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByMDefend(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findBySpeed(int value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<GodColourTemplet> findByColor(String value) {
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getColor(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static String[] getArrayByColor() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getColor();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<String> getListByColor() {
		List<String> all = new ArrayList<String>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getColor());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}