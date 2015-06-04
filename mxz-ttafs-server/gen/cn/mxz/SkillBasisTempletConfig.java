//[战斗][技能]技能材料基础概率package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SkillBasisTempletConfig {	private static Map<String, SkillBasisTemplet> map;	private static List<String> keys;	private static List<SkillBasisTemplet> all;	static {		load();	}	public static List<SkillBasisTemplet> getAll() {		return new ArrayList<SkillBasisTemplet>(all);	}	public static List<String> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SkillBasisConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<String, SkillBasisTemplet> map = Maps.newConcurrentMap();		List<String> keys = new ArrayList<String>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SkillBasisTempletConfig.map = map;		SkillBasisTempletConfig.keys = keys;																List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();		for(String c : keys) {			all.add(get(c));		}		SkillBasisTempletConfig.all = all;	}	private static void put(Element e, Map<String, SkillBasisTemplet> map) {		SkillBasisTemplet x = new SkillBasisTemplet();		x.setSpetLv( e.attributeValue("spetLv") );		x.setFormationLv( Integer.decode( e.attributeValue("formationLv").trim() ) );		x.setFormationSpet( Integer.decode( e.attributeValue("formationSpet").trim() ) );		x.setBasisPro( Float.parseFloat( e.attributeValue("basisPro").trim() ) );		SkillBasisTemplet remove = map.put(x.getSpetLv(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SkillBasisTemplet get(String x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static String getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static String getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SkillBasisTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SkillBasisTemplet getMin() {		return get(getMinKey());	}	public static List<SkillBasisTemplet> findBySpetLv(String value) {
		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getSpetLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillBasisTemplet> findByFormationLv(int value) {
		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getFormationLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillBasisTemplet> findByFormationSpet(int value) {
		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getFormationSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillBasisTemplet> findByBasisPro(float value) {
		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getBasisPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static String[] getArrayBySpetLv() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getSpetLv();
		}
		return all;
	}
	public static int[] getArrayByFormationLv() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getFormationLv();
		}
		return all;
	}
	public static int[] getArrayByFormationSpet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getFormationSpet();
		}
		return all;
	}
	public static float[] getArrayByBasisPro() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getBasisPro();
		}
		return all;
	}
	public static List<String> getListBySpetLv() {
		List<String> all = new ArrayList<String>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getSpetLv());
		}
		return all;
	}
	public static List<Integer> getListByFormationLv() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getFormationLv());
		}
		return all;
	}
	public static List<Integer> getListByFormationSpet() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getFormationSpet());
		}
		return all;
	}
	public static List<Float> getListByBasisPro() {
		List<Float> all = new ArrayList<Float>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getBasisPro());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}