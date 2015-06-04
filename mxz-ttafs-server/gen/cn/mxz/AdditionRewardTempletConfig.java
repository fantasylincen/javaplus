//[竞技场]修行值加成package cn.mxz;import java.io.File;import java.util.HashMap;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AdditionRewardTempletConfig {	private static Map<Integer, AdditionRewardTemplet> map;	private static List<Integer> keys;	private static List<AdditionRewardTemplet> all;	static {		load();		all = new ArrayList<AdditionRewardTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<AdditionRewardTemplet> getAll() {		return new ArrayList<AdditionRewardTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AdditionRewardConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = new HashMap<Integer, AdditionRewardTemplet>();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		AdditionRewardTemplet x = new AdditionRewardTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setRatio( e.attributeValue("ratio") );		x.setAddition( Float.parseFloat( e.attributeValue("addition").trim() ) );		AdditionRewardTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AdditionRewardTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AdditionRewardTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AdditionRewardTemplet getMin() {		return get(getMinKey());	}	public static List<AdditionRewardTemplet> findById(int value) {
		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AdditionRewardTemplet> findByRatio(String value) {
		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getRatio(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AdditionRewardTemplet> findByAddition(float value) {
		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByRatio() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getRatio();
		}
		return all;
	}
	public static float[] getArrayByAddition() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByRatio() {
		List<String> all = new ArrayList<String>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getRatio());
		}
		return all;
	}
	public static List<Float> getListByAddition() {
		List<Float> all = new ArrayList<Float>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}