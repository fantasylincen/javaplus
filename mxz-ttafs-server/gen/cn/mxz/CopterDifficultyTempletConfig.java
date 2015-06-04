//[奇遇]35[渡天劫]难度配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterDifficultyTempletConfig {	private static Map<Integer, CopterDifficultyTemplet> map;	private static List<Integer> keys;	private static List<CopterDifficultyTemplet> all;	static {		load();	}	public static List<CopterDifficultyTemplet> getAll() {		return new ArrayList<CopterDifficultyTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterDifficultyConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterDifficultyTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterDifficultyTempletConfig.map = map;		CopterDifficultyTempletConfig.keys = keys;																List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterDifficultyTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterDifficultyTemplet> map) {		CopterDifficultyTemplet x = new CopterDifficultyTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setNumber( Integer.decode( e.attributeValue("number").trim() ) );		x.setDifficulty( Integer.decode( e.attributeValue("difficulty").trim() ) );		x.setLevelsOf( e.attributeValue("LevelsOf") );		x.setDifficultyDeploy( e.attributeValue("difficultyDeploy") );		x.setStar( Integer.decode( e.attributeValue("star").trim() ) );		x.setFactor( Float.parseFloat( e.attributeValue("factor").trim() ) );		CopterDifficultyTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterDifficultyTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterDifficultyTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterDifficultyTemplet getMin() {		return get(getMinKey());	}	public static List<CopterDifficultyTemplet> findById(int value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByNumber(int value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByDifficulty(int value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getDifficulty(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByLevelsOf(String value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getLevelsOf(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByDifficultyDeploy(String value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getDifficultyDeploy(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByStar(int value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getStar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterDifficultyTemplet> findByFactor(float value) {
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getFactor(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}
	public static int[] getArrayByDifficulty() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getDifficulty();
		}
		return all;
	}
	public static String[] getArrayByLevelsOf() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getLevelsOf();
		}
		return all;
	}
	public static String[] getArrayByDifficultyDeploy() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getDifficultyDeploy();
		}
		return all;
	}
	public static int[] getArrayByStar() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getStar();
		}
		return all;
	}
	public static float[] getArrayByFactor() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getFactor();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
	public static List<Integer> getListByDifficulty() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getDifficulty());
		}
		return all;
	}
	public static List<String> getListByLevelsOf() {
		List<String> all = new ArrayList<String>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getLevelsOf());
		}
		return all;
	}
	public static List<String> getListByDifficultyDeploy() {
		List<String> all = new ArrayList<String>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getDifficultyDeploy());
		}
		return all;
	}
	public static List<Integer> getListByStar() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getStar());
		}
		return all;
	}
	public static List<Float> getListByFactor() {
		List<Float> all = new ArrayList<Float>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getFactor());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}