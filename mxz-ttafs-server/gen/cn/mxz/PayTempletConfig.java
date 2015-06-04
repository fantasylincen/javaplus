//[充值]充值金额及返额（GooglePlay）package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class PayTempletConfig {	private static Map<Integer, PayTemplet> map;	private static List<Integer> keys;	private static List<PayTemplet> all;	static {		load();	}	public static List<PayTemplet> getAll() {		return new ArrayList<PayTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/PayConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, PayTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																PayTempletConfig.map = map;		PayTempletConfig.keys = keys;																List<PayTemplet> all = new ArrayList<PayTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		PayTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, PayTemplet> map) {		PayTemplet x = new PayTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setItemid( e.attributeValue("itemid") );		x.setName( e.attributeValue("name") );		x.setRecommend( Integer.decode( e.attributeValue("recommend").trim() ) );		x.setReveal( Integer.decode( e.attributeValue("reveal").trim() ) );		x.setIosOrAndroid( Integer.decode( e.attributeValue("IosOrAndroid").trim() ) );		x.setRmb( Integer.decode( e.attributeValue("Rmb").trim() ) );		x.setSycee( Integer.decode( e.attributeValue("Sycee").trim() ) );		x.setRebate( Integer.decode( e.attributeValue("rebate").trim() ) );		x.setSyceeGet( Integer.decode( e.attributeValue("SyceeGet").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setUrl( e.attributeValue("url") );		PayTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static PayTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static PayTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static PayTemplet getMin() {		return get(getMinKey());	}	public static List<PayTemplet> findById(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByItemid(String value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getItemid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByName(String value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByRecommend(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getRecommend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByReveal(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getReveal(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByIosOrAndroid(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getIosOrAndroid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByRmb(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getRmb(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findBySycee(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getSycee(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByRebate(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getRebate(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findBySyceeGet(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getSyceeGet(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByFormat(String value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByResid(int value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<PayTemplet> findByUrl(String value) {
		List<PayTemplet> all = new ArrayList<PayTemplet>();
		for (PayTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByItemid() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getItemid();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByRecommend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getRecommend();
		}
		return all;
	}
	public static int[] getArrayByReveal() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getReveal();
		}
		return all;
	}
	public static int[] getArrayByIosOrAndroid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getIosOrAndroid();
		}
		return all;
	}
	public static int[] getArrayByRmb() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getRmb();
		}
		return all;
	}
	public static int[] getArrayBySycee() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getSycee();
		}
		return all;
	}
	public static int[] getArrayByRebate() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getRebate();
		}
		return all;
	}
	public static int[] getArrayBySyceeGet() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getSyceeGet();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PayTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByItemid() {
		List<String> all = new ArrayList<String>();
		for (PayTemplet f : getAll()) {
			all.add(f.getItemid());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (PayTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByRecommend() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getRecommend());
		}
		return all;
	}
	public static List<Integer> getListByReveal() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getReveal());
		}
		return all;
	}
	public static List<Integer> getListByIosOrAndroid() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getIosOrAndroid());
		}
		return all;
	}
	public static List<Integer> getListByRmb() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getRmb());
		}
		return all;
	}
	public static List<Integer> getListBySycee() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getSycee());
		}
		return all;
	}
	public static List<Integer> getListByRebate() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getRebate());
		}
		return all;
	}
	public static List<Integer> getListBySyceeGet() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getSyceeGet());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (PayTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (PayTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (PayTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}