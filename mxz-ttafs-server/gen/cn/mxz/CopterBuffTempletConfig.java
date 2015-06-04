//[奇遇]35[渡天劫]buff配置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class CopterBuffTempletConfig {	private static Map<Integer, CopterBuffTemplet> map;	private static List<Integer> keys;	private static List<CopterBuffTemplet> all;	static {		load();	}	public static List<CopterBuffTemplet> getAll() {		return new ArrayList<CopterBuffTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/CopterBuffConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, CopterBuffTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																CopterBuffTempletConfig.map = map;		CopterBuffTempletConfig.keys = keys;																List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		CopterBuffTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, CopterBuffTemplet> map) {		CopterBuffTemplet x = new CopterBuffTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setPrevious( Integer.decode( e.attributeValue("previous").trim() ) );		x.setFront( e.attributeValue("front") );		x.setFrontPar( Float.parseFloat( e.attributeValue("frontPar").trim() ) );		x.setGrade( Integer.decode( e.attributeValue("grade").trim() ) );		x.setStar( Integer.decode( e.attributeValue("star").trim() ) );		CopterBuffTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static CopterBuffTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static CopterBuffTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static CopterBuffTemplet getMin() {		return get(getMinKey());	}	public static List<CopterBuffTemplet> findById(int value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterBuffTemplet> findByPrevious(int value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getPrevious(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterBuffTemplet> findByFront(String value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getFront(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterBuffTemplet> findByFrontPar(float value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getFrontPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterBuffTemplet> findByGrade(int value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<CopterBuffTemplet> findByStar(int value) {
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getStar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByPrevious() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getPrevious();
		}
		return all;
	}
	public static String[] getArrayByFront() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getFront();
		}
		return all;
	}
	public static float[] getArrayByFrontPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontPar();
		}
		return all;
	}
	public static int[] getArrayByGrade() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getGrade();
		}
		return all;
	}
	public static int[] getArrayByStar() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getStar();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByPrevious() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getPrevious());
		}
		return all;
	}
	public static List<String> getListByFront() {
		List<String> all = new ArrayList<String>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getFront());
		}
		return all;
	}
	public static List<Float> getListByFrontPar() {
		List<Float> all = new ArrayList<Float>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getFrontPar());
		}
		return all;
	}
	public static List<Integer> getListByGrade() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getGrade());
		}
		return all;
	}
	public static List<Integer> getListByStar() {
		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getStar());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}