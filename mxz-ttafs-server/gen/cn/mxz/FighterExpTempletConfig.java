//[神将]经验配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FighterExpTempletConfig {	private static Map<Integer, FighterExpTemplet> map;	private static List<Integer> keys;	private static List<FighterExpTemplet> all;	static {		load();	}	public static List<FighterExpTemplet> getAll() {		return new ArrayList<FighterExpTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FighterExpConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FighterExpTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FighterExpTempletConfig.map = map;		FighterExpTempletConfig.keys = keys;																List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FighterExpTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FighterExpTemplet> map) {		FighterExpTemplet x = new FighterExpTemplet();		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setPlayer( Integer.decode( e.attributeValue("player").trim() ) );		x.setPlayerSum( Integer.decode( e.attributeValue("playerSum").trim() ) );		FighterExpTemplet remove = map.put(x.getLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FighterExpTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FighterExpTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FighterExpTemplet getMin() {		return get(getMinKey());	}	public static List<FighterExpTemplet> findByLevel(int value) {
		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterExpTemplet> findByPlayer(int value) {
		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getPlayer(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterExpTemplet> findByPlayerSum(int value) {
		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getPlayerSum(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByPlayer() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getPlayer();
		}
		return all;
	}
	public static int[] getArrayByPlayerSum() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getPlayerSum();
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByPlayer() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getPlayer());
		}
		return all;
	}
	public static List<Integer> getListByPlayerSum() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getPlayerSum());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}