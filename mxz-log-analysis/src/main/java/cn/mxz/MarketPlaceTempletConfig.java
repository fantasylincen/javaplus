//[商城]物品package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MarketPlaceTempletConfig {	private static Map<Integer, MarketPlaceTemplet> map;	private static List<Integer> keys;	private static List<MarketPlaceTemplet> all;	static {		load();		all = new ArrayList<MarketPlaceTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<MarketPlaceTemplet> getAll() {		return new ArrayList<MarketPlaceTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "D:/workspace/MobileServer/res/properties/MarketPlaceConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = Maps.newConcurrentMap();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		MarketPlaceTemplet x = new MarketPlaceTemplet();		x.setTypeId( Integer.decode( e.attributeValue("typeId").trim() ) );		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setPresentDBIndex( Integer.decode( e.attributeValue("presentDBIndex").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setState( Integer.decode( e.attributeValue("state").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setTab( Integer.decode( e.attributeValue("tab").trim() ) );		x.setType( e.attributeValue("type") );		x.setVipLevelNeed( Integer.decode( e.attributeValue("vipLevelNeed").trim() ) );		x.setDressLevel( Integer.decode( e.attributeValue("dressLevel").trim() ) );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setForevermax( Integer.decode( e.attributeValue("forevermax").trim() ) );		x.setVipLimit( e.attributeValue("vipLimit") );		x.setVipQuota( Integer.decode( e.attributeValue("vipQuota").trim() ) );		x.setHot( Integer.decode( e.attributeValue("hot").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setRoad( e.attributeValue("road") );		x.setGold( Integer.decode( e.attributeValue("gold").trim() ) );		x.setIscash( Integer.decode( e.attributeValue("iscash").trim() ) );		x.setCashOld( Integer.decode( e.attributeValue("cashOld").trim() ) );		x.setCashNew( Integer.decode( e.attributeValue("cashNew").trim() ) );		x.setCouponsOld( Integer.decode( e.attributeValue("couponsOld").trim() ) );		x.setIsSpecial( Integer.decode( e.attributeValue("isSpecial").trim() ) );		x.setCouponsNew( Integer.decode( e.attributeValue("CouponsNew").trim() ) );		x.setIncrease( Integer.decode( e.attributeValue("increase").trim() ) );		MarketPlaceTemplet remove = map.put(x.getTypeId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MarketPlaceTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MarketPlaceTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MarketPlaceTemplet getMin() {		return get(getMinKey());	}	public static List<MarketPlaceTemplet> findByTypeId(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getTypeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findById(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByName(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByPresentDBIndex(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getPresentDBIndex(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByAwards(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByState(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getState(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByQuality(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByTab(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getTab(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByType(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByVipLevelNeed(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getVipLevelNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByDressLevel(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getDressLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByMax(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByForevermax(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getForevermax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByVipLimit(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getVipLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByVipQuota(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getVipQuota(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByHot(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getHot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByDescription(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByRoad(String value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getRoad(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByGold(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getGold(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByIscash(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getIscash(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByCashOld(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getCashOld(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByCashNew(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getCashNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByCouponsOld(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getCouponsOld(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByIsSpecial(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getIsSpecial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByCouponsNew(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getCouponsNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MarketPlaceTemplet> findByIncrease(int value) {
		List<MarketPlaceTemplet> all = new ArrayList<MarketPlaceTemplet>();
		for (MarketPlaceTemplet f : getAll()) {
			if(equals(f.getIncrease(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByTypeId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getTypeId();
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByPresentDBIndex() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getPresentDBIndex();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static int[] getArrayByState() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getState();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByTab() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getTab();
		}
		return all;
	}
	public static String[] getArrayByType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static int[] getArrayByVipLevelNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getVipLevelNeed();
		}
		return all;
	}
	public static int[] getArrayByDressLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getDressLevel();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static int[] getArrayByForevermax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getForevermax();
		}
		return all;
	}
	public static String[] getArrayByVipLimit() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getVipLimit();
		}
		return all;
	}
	public static int[] getArrayByVipQuota() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getVipQuota();
		}
		return all;
	}
	public static int[] getArrayByHot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getHot();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayByRoad() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getRoad();
		}
		return all;
	}
	public static int[] getArrayByGold() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getGold();
		}
		return all;
	}
	public static int[] getArrayByIscash() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getIscash();
		}
		return all;
	}
	public static int[] getArrayByCashOld() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCashOld();
		}
		return all;
	}
	public static int[] getArrayByCashNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCashNew();
		}
		return all;
	}
	public static int[] getArrayByCouponsOld() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCouponsOld();
		}
		return all;
	}
	public static int[] getArrayByIsSpecial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getIsSpecial();
		}
		return all;
	}
	public static int[] getArrayByCouponsNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getCouponsNew();
		}
		return all;
	}
	public static int[] getArrayByIncrease() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceTemplet f = get(keys.get(i));
			all[i] = f.getIncrease();
		}
		return all;
	}
	public static List<Integer> getListByTypeId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getTypeId());
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByPresentDBIndex() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getPresentDBIndex());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<Integer> getListByState() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getState());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByTab() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getTab());
		}
		return all;
	}
	public static List<String> getListByType() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<Integer> getListByVipLevelNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getVipLevelNeed());
		}
		return all;
	}
	public static List<Integer> getListByDressLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getDressLevel());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<Integer> getListByForevermax() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getForevermax());
		}
		return all;
	}
	public static List<String> getListByVipLimit() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getVipLimit());
		}
		return all;
	}
	public static List<Integer> getListByVipQuota() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getVipQuota());
		}
		return all;
	}
	public static List<Integer> getListByHot() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getHot());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListByRoad() {
		List<String> all = new ArrayList<String>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getRoad());
		}
		return all;
	}
	public static List<Integer> getListByGold() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getGold());
		}
		return all;
	}
	public static List<Integer> getListByIscash() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getIscash());
		}
		return all;
	}
	public static List<Integer> getListByCashOld() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getCashOld());
		}
		return all;
	}
	public static List<Integer> getListByCashNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getCashNew());
		}
		return all;
	}
	public static List<Integer> getListByCouponsOld() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getCouponsOld());
		}
		return all;
	}
	public static List<Integer> getListByIsSpecial() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getIsSpecial());
		}
		return all;
	}
	public static List<Integer> getListByCouponsNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getCouponsNew());
		}
		return all;
	}
	public static List<Integer> getListByIncrease() {
		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceTemplet f : getAll()) {
			all.add(f.getIncrease());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}