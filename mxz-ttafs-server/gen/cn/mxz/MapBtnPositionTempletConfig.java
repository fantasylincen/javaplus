//[主地图]位置package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MapBtnPositionTempletConfig {	private static Map<Integer, MapBtnPositionTemplet> map;	private static List<Integer> keys;	private static List<MapBtnPositionTemplet> all;	static {		load();	}	public static List<MapBtnPositionTemplet> getAll() {		return new ArrayList<MapBtnPositionTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MapBtnPositionConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MapBtnPositionTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MapBtnPositionTempletConfig.map = map;		MapBtnPositionTempletConfig.keys = keys;																List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MapBtnPositionTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MapBtnPositionTemplet> map) {		MapBtnPositionTemplet x = new MapBtnPositionTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setBtnX( Integer.decode( e.attributeValue("btnX").trim() ) );		x.setBtnY( Integer.decode( e.attributeValue("btnY").trim() ) );		x.setBtnRectW( Integer.decode( e.attributeValue("btnRectW").trim() ) );		x.setBtnRectH( Integer.decode( e.attributeValue("btnRectH").trim() ) );		x.setLabelX( Integer.decode( e.attributeValue("labelX").trim() ) );		x.setLabelY( Integer.decode( e.attributeValue("labelY").trim() ) );		x.setRowX( Integer.decode( e.attributeValue("rowX").trim() ) );		x.setRowY( Integer.decode( e.attributeValue("rowY").trim() ) );		x.setSelectX( Integer.decode( e.attributeValue("selectX").trim() ) );		x.setSelectY( Integer.decode( e.attributeValue("selectY").trim() ) );		x.setMapX( Integer.decode( e.attributeValue("mapX").trim() ) );		x.setMapY( Integer.decode( e.attributeValue("mapY").trim() ) );		MapBtnPositionTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MapBtnPositionTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MapBtnPositionTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MapBtnPositionTemplet getMin() {		return get(getMinKey());	}	public static List<MapBtnPositionTemplet> findById(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByBtnX(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getBtnX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByBtnY(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getBtnY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByBtnRectW(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getBtnRectW(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByBtnRectH(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getBtnRectH(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByLabelX(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getLabelX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByLabelY(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getLabelY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByRowX(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getRowX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByRowY(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getRowY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findBySelectX(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getSelectX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findBySelectY(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getSelectY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByMapX(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getMapX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MapBtnPositionTemplet> findByMapY(int value) {
		List<MapBtnPositionTemplet> all = new ArrayList<MapBtnPositionTemplet>();
		for (MapBtnPositionTemplet f : getAll()) {
			if(equals(f.getMapY(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByBtnX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getBtnX();
		}
		return all;
	}
	public static int[] getArrayByBtnY() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getBtnY();
		}
		return all;
	}
	public static int[] getArrayByBtnRectW() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getBtnRectW();
		}
		return all;
	}
	public static int[] getArrayByBtnRectH() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getBtnRectH();
		}
		return all;
	}
	public static int[] getArrayByLabelX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getLabelX();
		}
		return all;
	}
	public static int[] getArrayByLabelY() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getLabelY();
		}
		return all;
	}
	public static int[] getArrayByRowX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getRowX();
		}
		return all;
	}
	public static int[] getArrayByRowY() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getRowY();
		}
		return all;
	}
	public static int[] getArrayBySelectX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getSelectX();
		}
		return all;
	}
	public static int[] getArrayBySelectY() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getSelectY();
		}
		return all;
	}
	public static int[] getArrayByMapX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getMapX();
		}
		return all;
	}
	public static int[] getArrayByMapY() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MapBtnPositionTemplet f = get(keys.get(i));
			all[i] = f.getMapY();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByBtnX() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getBtnX());
		}
		return all;
	}
	public static List<Integer> getListByBtnY() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getBtnY());
		}
		return all;
	}
	public static List<Integer> getListByBtnRectW() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getBtnRectW());
		}
		return all;
	}
	public static List<Integer> getListByBtnRectH() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getBtnRectH());
		}
		return all;
	}
	public static List<Integer> getListByLabelX() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getLabelX());
		}
		return all;
	}
	public static List<Integer> getListByLabelY() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getLabelY());
		}
		return all;
	}
	public static List<Integer> getListByRowX() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getRowX());
		}
		return all;
	}
	public static List<Integer> getListByRowY() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getRowY());
		}
		return all;
	}
	public static List<Integer> getListBySelectX() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getSelectX());
		}
		return all;
	}
	public static List<Integer> getListBySelectY() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getSelectY());
		}
		return all;
	}
	public static List<Integer> getListByMapX() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getMapX());
		}
		return all;
	}
	public static List<Integer> getListByMapY() {
		List<Integer> all = new ArrayList<Integer>();
		for (MapBtnPositionTemplet f : getAll()) {
			all.add(f.getMapY());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}