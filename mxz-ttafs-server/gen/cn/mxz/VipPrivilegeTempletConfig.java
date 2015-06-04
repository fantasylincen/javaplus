//[VIP]权限配置（后端用表）package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class VipPrivilegeTempletConfig {	private static Map<Byte, VipPrivilegeTemplet> map;	private static List<Byte> keys;	private static List<VipPrivilegeTemplet> all;	static {		load();	}	public static List<VipPrivilegeTemplet> getAll() {		return new ArrayList<VipPrivilegeTemplet>(all);	}	public static List<Byte> getKeys() {		return keys;	}	private static final String fileName = "res/properties/VipPrivilegeConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Byte, VipPrivilegeTemplet> map = Maps.newConcurrentMap();		List<Byte> keys = new ArrayList<Byte>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																VipPrivilegeTempletConfig.map = map;		VipPrivilegeTempletConfig.keys = keys;																List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();		for(Byte c : keys) {			all.add(get(c));		}		VipPrivilegeTempletConfig.all = all;	}	private static void put(Element e, Map<Byte, VipPrivilegeTemplet> map) {		VipPrivilegeTemplet x = new VipPrivilegeTemplet();		x.setLevel( Byte.parseByte( e.attributeValue("level").trim() ) );		x.setGrowth( Integer.decode( e.attributeValue("growth").trim() ) );		x.setHasVideo( Integer.decode( e.attributeValue("hasVideo").trim() ) );		x.setAccelerate2( Integer.decode( e.attributeValue("accelerate2").trim() ) );		x.setAccelerate3( Integer.decode( e.attributeValue("accelerate3").trim() ) );		x.setAddPower( Integer.decode( e.attributeValue("addPower").trim() ) );		x.setAddVigor( Integer.decode( e.attributeValue("addVigor").trim() ) );		x.setDareTimes( Integer.decode( e.attributeValue("dareTimes").trim() ) );		x.setMoney( Integer.decode( e.attributeValue("money").trim() ) );		x.setAthleticsTimes( Integer.decode( e.attributeValue("athleticsTimes").trim() ) );		x.setGoldenBox( Integer.decode( e.attributeValue("goldenBox").trim() ) );		x.setSilverBox( Integer.decode( e.attributeValue("silverBox").trim() ) );		x.setBagGrade( Integer.decode( e.attributeValue("bagGrade").trim() ) );		x.setMystical( Integer.decode( e.attributeValue("mystical").trim() ) );		x.setFriendLimit( Integer.decode( e.attributeValue("friendLimit").trim() ) );		x.setVigorLimit( Integer.decode( e.attributeValue("vigorLimit").trim() ) );		x.setPowerLimit( Integer.decode( e.attributeValue("powerLimit").trim() ) );		x.setCustodianCD( Integer.decode( e.attributeValue("custodianCD").trim() ) );		x.setAgainst1( Integer.decode( e.attributeValue("against1").trim() ) );		x.setGrid1( Integer.decode( e.attributeValue("grid1").trim() ) );		x.setBossSkip( Integer.decode( e.attributeValue("bossSkip").trim() ) );		x.setCopterAward( Integer.decode( e.attributeValue("copterAward").trim() ) );		x.setMopupCD( Integer.decode( e.attributeValue("mopupCD").trim() ) );		x.setCopterTime( Integer.decode( e.attributeValue("copterTime").trim() ) );		x.setMonsterSoul( Integer.decode( e.attributeValue("monsterSoul").trim() ) );		x.setVioletgold( Integer.decode( e.attributeValue("violetgold").trim() ) );		x.setAgainst2( Integer.decode( e.attributeValue("against2").trim() ) );		x.setGrid2( Integer.decode( e.attributeValue("grid2").trim() ) );		x.setBossAward( Integer.decode( e.attributeValue("bossAward").trim() ) );		x.setChallengeTimes( Integer.decode( e.attributeValue("challengeTimes").trim() ) );		x.setAlliance( Integer.decode( e.attributeValue("alliance").trim() ) );		x.setEquipmentMust( Integer.decode( e.attributeValue("equipmentMust").trim() ) );		x.setBossUnuse( Integer.decode( e.attributeValue("bossUnuse").trim() ) );		x.setFBunuse( Integer.decode( e.attributeValue("FBunuse").trim() ) );		x.setIntensifyCrit( e.attributeValue("intensifyCrit") );		VipPrivilegeTemplet remove = map.put(x.getLevel(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static VipPrivilegeTemplet get(Byte x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Byte getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Byte getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static VipPrivilegeTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static VipPrivilegeTemplet getMin() {		return get(getMinKey());	}	public static List<VipPrivilegeTemplet> findByLevel(byte value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByGrowth(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getGrowth(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByHasVideo(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getHasVideo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAccelerate2(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAccelerate2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAccelerate3(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAccelerate3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAddPower(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAddPower(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAddVigor(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAddVigor(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByDareTimes(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getDareTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByMoney(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getMoney(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAthleticsTimes(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAthleticsTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByGoldenBox(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getGoldenBox(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findBySilverBox(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getSilverBox(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByBagGrade(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getBagGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByMystical(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getMystical(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByFriendLimit(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getFriendLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByVigorLimit(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getVigorLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByPowerLimit(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getPowerLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByCustodianCD(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getCustodianCD(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAgainst1(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAgainst1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByGrid1(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getGrid1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByBossSkip(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getBossSkip(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByCopterAward(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getCopterAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByMopupCD(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getMopupCD(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByCopterTime(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getCopterTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByMonsterSoul(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getMonsterSoul(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByVioletgold(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getVioletgold(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAgainst2(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAgainst2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByGrid2(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getGrid2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByBossAward(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getBossAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByChallengeTimes(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getChallengeTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByAlliance(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getAlliance(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByEquipmentMust(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getEquipmentMust(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByBossUnuse(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getBossUnuse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByFBunuse(int value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getFBunuse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<VipPrivilegeTemplet> findByIntensifyCrit(String value) {
		List<VipPrivilegeTemplet> all = new ArrayList<VipPrivilegeTemplet>();
		for (VipPrivilegeTemplet f : getAll()) {
			if(equals(f.getIntensifyCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static byte[] getArrayByLevel() {
		byte[] all = new byte[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static int[] getArrayByGrowth() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getGrowth();
		}
		return all;
	}
	public static int[] getArrayByHasVideo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getHasVideo();
		}
		return all;
	}
	public static int[] getArrayByAccelerate2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAccelerate2();
		}
		return all;
	}
	public static int[] getArrayByAccelerate3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAccelerate3();
		}
		return all;
	}
	public static int[] getArrayByAddPower() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAddPower();
		}
		return all;
	}
	public static int[] getArrayByAddVigor() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAddVigor();
		}
		return all;
	}
	public static int[] getArrayByDareTimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getDareTimes();
		}
		return all;
	}
	public static int[] getArrayByMoney() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getMoney();
		}
		return all;
	}
	public static int[] getArrayByAthleticsTimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAthleticsTimes();
		}
		return all;
	}
	public static int[] getArrayByGoldenBox() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getGoldenBox();
		}
		return all;
	}
	public static int[] getArrayBySilverBox() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getSilverBox();
		}
		return all;
	}
	public static int[] getArrayByBagGrade() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getBagGrade();
		}
		return all;
	}
	public static int[] getArrayByMystical() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getMystical();
		}
		return all;
	}
	public static int[] getArrayByFriendLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getFriendLimit();
		}
		return all;
	}
	public static int[] getArrayByVigorLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getVigorLimit();
		}
		return all;
	}
	public static int[] getArrayByPowerLimit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getPowerLimit();
		}
		return all;
	}
	public static int[] getArrayByCustodianCD() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getCustodianCD();
		}
		return all;
	}
	public static int[] getArrayByAgainst1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAgainst1();
		}
		return all;
	}
	public static int[] getArrayByGrid1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getGrid1();
		}
		return all;
	}
	public static int[] getArrayByBossSkip() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getBossSkip();
		}
		return all;
	}
	public static int[] getArrayByCopterAward() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getCopterAward();
		}
		return all;
	}
	public static int[] getArrayByMopupCD() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getMopupCD();
		}
		return all;
	}
	public static int[] getArrayByCopterTime() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getCopterTime();
		}
		return all;
	}
	public static int[] getArrayByMonsterSoul() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getMonsterSoul();
		}
		return all;
	}
	public static int[] getArrayByVioletgold() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getVioletgold();
		}
		return all;
	}
	public static int[] getArrayByAgainst2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAgainst2();
		}
		return all;
	}
	public static int[] getArrayByGrid2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getGrid2();
		}
		return all;
	}
	public static int[] getArrayByBossAward() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getBossAward();
		}
		return all;
	}
	public static int[] getArrayByChallengeTimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getChallengeTimes();
		}
		return all;
	}
	public static int[] getArrayByAlliance() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getAlliance();
		}
		return all;
	}
	public static int[] getArrayByEquipmentMust() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentMust();
		}
		return all;
	}
	public static int[] getArrayByBossUnuse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getBossUnuse();
		}
		return all;
	}
	public static int[] getArrayByFBunuse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getFBunuse();
		}
		return all;
	}
	public static String[] getArrayByIntensifyCrit() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipPrivilegeTemplet f = get(keys.get(i));
			all[i] = f.getIntensifyCrit();
		}
		return all;
	}
	public static List<Byte> getListByLevel() {
		List<Byte> all = new ArrayList<Byte>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Integer> getListByGrowth() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getGrowth());
		}
		return all;
	}
	public static List<Integer> getListByHasVideo() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getHasVideo());
		}
		return all;
	}
	public static List<Integer> getListByAccelerate2() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAccelerate2());
		}
		return all;
	}
	public static List<Integer> getListByAccelerate3() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAccelerate3());
		}
		return all;
	}
	public static List<Integer> getListByAddPower() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAddPower());
		}
		return all;
	}
	public static List<Integer> getListByAddVigor() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAddVigor());
		}
		return all;
	}
	public static List<Integer> getListByDareTimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getDareTimes());
		}
		return all;
	}
	public static List<Integer> getListByMoney() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getMoney());
		}
		return all;
	}
	public static List<Integer> getListByAthleticsTimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAthleticsTimes());
		}
		return all;
	}
	public static List<Integer> getListByGoldenBox() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getGoldenBox());
		}
		return all;
	}
	public static List<Integer> getListBySilverBox() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getSilverBox());
		}
		return all;
	}
	public static List<Integer> getListByBagGrade() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getBagGrade());
		}
		return all;
	}
	public static List<Integer> getListByMystical() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getMystical());
		}
		return all;
	}
	public static List<Integer> getListByFriendLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getFriendLimit());
		}
		return all;
	}
	public static List<Integer> getListByVigorLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getVigorLimit());
		}
		return all;
	}
	public static List<Integer> getListByPowerLimit() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getPowerLimit());
		}
		return all;
	}
	public static List<Integer> getListByCustodianCD() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getCustodianCD());
		}
		return all;
	}
	public static List<Integer> getListByAgainst1() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAgainst1());
		}
		return all;
	}
	public static List<Integer> getListByGrid1() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getGrid1());
		}
		return all;
	}
	public static List<Integer> getListByBossSkip() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getBossSkip());
		}
		return all;
	}
	public static List<Integer> getListByCopterAward() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getCopterAward());
		}
		return all;
	}
	public static List<Integer> getListByMopupCD() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getMopupCD());
		}
		return all;
	}
	public static List<Integer> getListByCopterTime() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getCopterTime());
		}
		return all;
	}
	public static List<Integer> getListByMonsterSoul() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getMonsterSoul());
		}
		return all;
	}
	public static List<Integer> getListByVioletgold() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getVioletgold());
		}
		return all;
	}
	public static List<Integer> getListByAgainst2() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAgainst2());
		}
		return all;
	}
	public static List<Integer> getListByGrid2() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getGrid2());
		}
		return all;
	}
	public static List<Integer> getListByBossAward() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getBossAward());
		}
		return all;
	}
	public static List<Integer> getListByChallengeTimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getChallengeTimes());
		}
		return all;
	}
	public static List<Integer> getListByAlliance() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getAlliance());
		}
		return all;
	}
	public static List<Integer> getListByEquipmentMust() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getEquipmentMust());
		}
		return all;
	}
	public static List<Integer> getListByBossUnuse() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getBossUnuse());
		}
		return all;
	}
	public static List<Integer> getListByFBunuse() {
		List<Integer> all = new ArrayList<Integer>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getFBunuse());
		}
		return all;
	}
	public static List<String> getListByIntensifyCrit() {
		List<String> all = new ArrayList<String>();
		for (VipPrivilegeTemplet f : getAll()) {
			all.add(f.getIntensifyCrit());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}