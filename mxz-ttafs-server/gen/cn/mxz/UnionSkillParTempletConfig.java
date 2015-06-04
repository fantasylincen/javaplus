//[战斗]连携技能伤害加成系数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class UnionSkillParTempletConfig {	private static Map<Integer, UnionSkillParTemplet> map;	private static List<Integer> keys;	private static List<UnionSkillParTemplet> all;	static {		load();	}	public static List<UnionSkillParTemplet> getAll() {		return new ArrayList<UnionSkillParTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/UnionSkillParConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, UnionSkillParTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																UnionSkillParTempletConfig.map = map;		UnionSkillParTempletConfig.keys = keys;																List<UnionSkillParTemplet> all = new ArrayList<UnionSkillParTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		UnionSkillParTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, UnionSkillParTemplet> map) {		UnionSkillParTemplet x = new UnionSkillParTemplet();		x.setMeanLv( Integer.decode( e.attributeValue("meanLv").trim() ) );		x.setHarmPar( Float.parseFloat( e.attributeValue("harmPar").trim() ) );		UnionSkillParTemplet remove = map.put(x.getMeanLv(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static UnionSkillParTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static UnionSkillParTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static UnionSkillParTemplet getMin() {		return get(getMinKey());	}	public static List<UnionSkillParTemplet> findByMeanLv(int value) {
		List<UnionSkillParTemplet> all = new ArrayList<UnionSkillParTemplet>();
		for (UnionSkillParTemplet f : getAll()) {
			if(equals(f.getMeanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<UnionSkillParTemplet> findByHarmPar(float value) {
		List<UnionSkillParTemplet> all = new ArrayList<UnionSkillParTemplet>();
		for (UnionSkillParTemplet f : getAll()) {
			if(equals(f.getHarmPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByMeanLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			UnionSkillParTemplet f = get(keys.get(i));
			all[i] = f.getMeanLv();
		}
		return all;
	}
	public static float[] getArrayByHarmPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			UnionSkillParTemplet f = get(keys.get(i));
			all[i] = f.getHarmPar();
		}
		return all;
	}
	public static List<Integer> getListByMeanLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (UnionSkillParTemplet f : getAll()) {
			all.add(f.getMeanLv());
		}
		return all;
	}
	public static List<Float> getListByHarmPar() {
		List<Float> all = new ArrayList<Float>();
		for (UnionSkillParTemplet f : getAll()) {
			all.add(f.getHarmPar());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}