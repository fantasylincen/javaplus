//[消息提示]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AppMaskTempletConfig {	private static Map<Integer, AppMaskTemplet> map;	private static List<Integer> keys;	private static List<AppMaskTemplet> all;	static {		load();	}	public static List<AppMaskTemplet> getAll() {		return new ArrayList<AppMaskTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/AppMaskConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, AppMaskTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																AppMaskTempletConfig.map = map;		AppMaskTempletConfig.keys = keys;																List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		AppMaskTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, AppMaskTemplet> map) {		AppMaskTemplet x = new AppMaskTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setMaskX( Float.parseFloat( e.attributeValue("maskX").trim() ) );		x.setMaskY( Float.parseFloat( e.attributeValue("maskY").trim() ) );		x.setMaskWidth( Float.parseFloat( e.attributeValue("maskWidth").trim() ) );		x.setMaskHeight( Float.parseFloat( e.attributeValue("maskHeight").trim() ) );		x.setHandX( Float.parseFloat( e.attributeValue("handX").trim() ) );		x.setHandY( Float.parseFloat( e.attributeValue("handY").trim() ) );		x.setHandtype( Integer.decode( e.attributeValue("handtype").trim() ) );		x.setTalkX( Float.parseFloat( e.attributeValue("talkX").trim() ) );		x.setTalkY( Float.parseFloat( e.attributeValue("talkY").trim() ) );		x.setNodeOrder( Integer.decode( e.attributeValue("nodeOrder").trim() ) );		x.setNodeId( Integer.decode( e.attributeValue("nodeId").trim() ) );		x.setChildId( Integer.decode( e.attributeValue("childId").trim() ) );		x.setNodeSub( Integer.decode( e.attributeValue("nodeSub").trim() ) );		x.setUSub( Integer.decode( e.attributeValue("uSub").trim() ) );		x.setHideHandler( Integer.decode( e.attributeValue("hideHandler").trim() ) );		x.setModeString( e.attributeValue("modeString") );		x.setDes( e.attributeValue("des") );		x.setSpeakId( e.attributeValue("speakId") );		AppMaskTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AppMaskTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AppMaskTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AppMaskTemplet getMin() {		return get(getMinKey());	}	public static List<AppMaskTemplet> findById(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByMaskX(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getMaskX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByMaskY(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getMaskY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByMaskWidth(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getMaskWidth(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByMaskHeight(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getMaskHeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByHandX(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getHandX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByHandY(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getHandY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByHandtype(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getHandtype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByTalkX(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getTalkX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByTalkY(float value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getTalkY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByNodeOrder(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getNodeOrder(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByNodeId(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getNodeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByChildId(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getChildId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByNodeSub(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getNodeSub(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByUSub(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getUSub(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByHideHandler(int value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getHideHandler(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByModeString(String value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getModeString(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findByDes(String value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getDes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AppMaskTemplet> findBySpeakId(String value) {
		List<AppMaskTemplet> all = new ArrayList<AppMaskTemplet>();
		for (AppMaskTemplet f : getAll()) {
			if(equals(f.getSpeakId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static float[] getArrayByMaskX() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getMaskX();
		}
		return all;
	}
	public static float[] getArrayByMaskY() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getMaskY();
		}
		return all;
	}
	public static float[] getArrayByMaskWidth() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getMaskWidth();
		}
		return all;
	}
	public static float[] getArrayByMaskHeight() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getMaskHeight();
		}
		return all;
	}
	public static float[] getArrayByHandX() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getHandX();
		}
		return all;
	}
	public static float[] getArrayByHandY() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getHandY();
		}
		return all;
	}
	public static int[] getArrayByHandtype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getHandtype();
		}
		return all;
	}
	public static float[] getArrayByTalkX() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getTalkX();
		}
		return all;
	}
	public static float[] getArrayByTalkY() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getTalkY();
		}
		return all;
	}
	public static int[] getArrayByNodeOrder() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getNodeOrder();
		}
		return all;
	}
	public static int[] getArrayByNodeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getNodeId();
		}
		return all;
	}
	public static int[] getArrayByChildId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getChildId();
		}
		return all;
	}
	public static int[] getArrayByNodeSub() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getNodeSub();
		}
		return all;
	}
	public static int[] getArrayByUSub() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getUSub();
		}
		return all;
	}
	public static int[] getArrayByHideHandler() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getHideHandler();
		}
		return all;
	}
	public static String[] getArrayByModeString() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getModeString();
		}
		return all;
	}
	public static String[] getArrayByDes() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getDes();
		}
		return all;
	}
	public static String[] getArrayBySpeakId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AppMaskTemplet f = get(keys.get(i));
			all[i] = f.getSpeakId();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Float> getListByMaskX() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getMaskX());
		}
		return all;
	}
	public static List<Float> getListByMaskY() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getMaskY());
		}
		return all;
	}
	public static List<Float> getListByMaskWidth() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getMaskWidth());
		}
		return all;
	}
	public static List<Float> getListByMaskHeight() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getMaskHeight());
		}
		return all;
	}
	public static List<Float> getListByHandX() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getHandX());
		}
		return all;
	}
	public static List<Float> getListByHandY() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getHandY());
		}
		return all;
	}
	public static List<Integer> getListByHandtype() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getHandtype());
		}
		return all;
	}
	public static List<Float> getListByTalkX() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getTalkX());
		}
		return all;
	}
	public static List<Float> getListByTalkY() {
		List<Float> all = new ArrayList<Float>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getTalkY());
		}
		return all;
	}
	public static List<Integer> getListByNodeOrder() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getNodeOrder());
		}
		return all;
	}
	public static List<Integer> getListByNodeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getNodeId());
		}
		return all;
	}
	public static List<Integer> getListByChildId() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getChildId());
		}
		return all;
	}
	public static List<Integer> getListByNodeSub() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getNodeSub());
		}
		return all;
	}
	public static List<Integer> getListByUSub() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getUSub());
		}
		return all;
	}
	public static List<Integer> getListByHideHandler() {
		List<Integer> all = new ArrayList<Integer>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getHideHandler());
		}
		return all;
	}
	public static List<String> getListByModeString() {
		List<String> all = new ArrayList<String>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getModeString());
		}
		return all;
	}
	public static List<String> getListByDes() {
		List<String> all = new ArrayList<String>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getDes());
		}
		return all;
	}
	public static List<String> getListBySpeakId() {
		List<String> all = new ArrayList<String>();
		for (AppMaskTemplet f : getAll()) {
			all.add(f.getSpeakId());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}