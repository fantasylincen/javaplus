//[公式]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FormulaTempletConfig {	private static Map<Integer, FormulaTemplet> map;	private static List<Integer> keys;	private static List<FormulaTemplet> all;	static {		load();	}	public static List<FormulaTemplet> getAll() {		return new ArrayList<FormulaTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FormulaConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FormulaTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FormulaTempletConfig.map = map;		FormulaTempletConfig.keys = keys;																List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FormulaTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FormulaTemplet> map) {		FormulaTemplet x = new FormulaTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setType( e.attributeValue("type") );		x.setMethodName( e.attributeValue("methodName") );		x.setReturnType( e.attributeValue("returnType") );		x.setArgs( e.attributeValue("args") );		x.setFormula( e.attributeValue("formula") );		x.setComment( e.attributeValue("comment") );		FormulaTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FormulaTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FormulaTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FormulaTemplet getMin() {		return get(getMinKey());	}	public static List<FormulaTemplet> findById(int value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByType(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByMethodName(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getMethodName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByReturnType(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getReturnType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByArgs(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getArgs(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByFormula(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getFormula(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FormulaTemplet> findByComment(String value) {
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByMethodName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getMethodName();
		}
		return all;
	}
	public static String[] getArrayByReturnType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getReturnType();
		}
		return all;
	}
	public static String[] getArrayByArgs() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getArgs();
		}
		return all;
	}
	public static String[] getArrayByFormula() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getFormula();
		}
		return all;
	}
	public static String[] getArrayByComment() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByType() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByMethodName() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getMethodName());
		}
		return all;
	}
	public static List<String> getListByReturnType() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getReturnType());
		}
		return all;
	}
	public static List<String> getListByArgs() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getArgs());
		}
		return all;
	}
	public static List<String> getListByFormula() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getFormula());
		}
		return all;
	}
	public static List<String> getListByComment() {
		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}