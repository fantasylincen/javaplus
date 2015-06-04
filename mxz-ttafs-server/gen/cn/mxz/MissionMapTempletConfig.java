//[关卡][4]关卡地图package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class MissionMapTempletConfig {	private static Map<Integer, MissionMapTemplet> map;	private static List<Integer> keys;	private static List<MissionMapTemplet> all;	static {		load();	}	public static List<MissionMapTemplet> getAll() {		return new ArrayList<MissionMapTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/MissionMapConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, MissionMapTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																MissionMapTempletConfig.map = map;		MissionMapTempletConfig.keys = keys;																List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		MissionMapTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, MissionMapTemplet> map) {		MissionMapTemplet x = new MissionMapTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setPlotBegin1( Integer.decode( e.attributeValue("plotBegin1").trim() ) );		x.setPlotBegin2( Integer.decode( e.attributeValue("plotBegin2").trim() ) );		x.setPlotEnd1( Integer.decode( e.attributeValue("plotEnd1").trim() ) );		x.setPlotEnd2( Integer.decode( e.attributeValue("plotEnd2").trim() ) );		x.setPlotEnd3( Integer.decode( e.attributeValue("plotEnd3").trim() ) );		x.setPlotEnd4( Integer.decode( e.attributeValue("plotEnd4").trim() ) );		x.setIsNew( Integer.decode( e.attributeValue("isNew").trim() ) );		x.setCouplet1( e.attributeValue("couplet1") );		x.setCouplet2( e.attributeValue("couplet2") );		x.setMainlandId( Integer.decode( e.attributeValue("mainlandId").trim() ) );		x.setChapterId( Integer.decode( e.attributeValue("chapterId").trim() ) );		x.setSceneId( Integer.decode( e.attributeValue("sceneId").trim() ) );		x.setTollgateId( Integer.decode( e.attributeValue("tollgateId").trim() ) );		x.setWidth( Integer.decode( e.attributeValue("width").trim() ) );		x.setHeight( Integer.decode( e.attributeValue("height").trim() ) );		x.setIgnoreScale( Float.parseFloat( e.attributeValue("ignoreScale").trim() ) );		x.setBoxScale( Float.parseFloat( e.attributeValue("boxScale").trim() ) );		x.setDemonScale( Float.parseFloat( e.attributeValue("demonScale").trim() ) );		x.setAskScale( Float.parseFloat( e.attributeValue("askScale").trim() ) );		x.setMonsterId( e.attributeValue("monsterId") );		x.setMonsterDropOut( e.attributeValue("monsterDropOut") );		x.setLineMonsterDropOut( e.attributeValue("lineMonsterDropOut") );		x.setMonsterNumber( Integer.decode( e.attributeValue("monsterNumber").trim() ) );		x.setBossId( e.attributeValue("bossId") );		x.setBossDropOut( e.attributeValue("bossDropOut") );		x.setBossDropOutFirst( e.attributeValue("bossDropOutFirst") );		x.setLineBossId( e.attributeValue("lineBossId") );		x.setLineBossDropOut( e.attributeValue("lineBossDropOut") );		x.setLineBossDropOutFirst( e.attributeValue("lineBossDropOutFirst") );		x.setMonsterGrade( Integer.decode( e.attributeValue("monsterGrade").trim() ) );		x.setPlayGrade( Integer.decode( e.attributeValue("playGrade").trim() ) );		x.setSingleCoins( Integer.decode( e.attributeValue("singleCoins").trim() ) );		x.setPictype( Integer.decode( e.attributeValue("pictype").trim() ) );		x.setBattle( Integer.decode( e.attributeValue("battle").trim() ) );		x.setFighting( Integer.decode( e.attributeValue("fighting").trim() ) );		x.setWilsonParam( Float.parseFloat( e.attributeValue("wilsonParam").trim() ) );		x.setLineWilsonParam( Float.parseFloat( e.attributeValue("lineWilsonParam").trim() ) );		x.setBossParam( Float.parseFloat( e.attributeValue("bossParam").trim() ) );		x.setLineBossParam( Float.parseFloat( e.attributeValue("lineBossParam").trim() ) );		x.setHurtMin( Integer.decode( e.attributeValue("hurtMin").trim() ) );		MissionMapTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static MissionMapTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static MissionMapTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static MissionMapTemplet getMin() {		return get(getMinKey());	}	public static List<MissionMapTemplet> findById(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByName(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotBegin1(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotBegin1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotBegin2(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotBegin2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotEnd1(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotEnd1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotEnd2(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotEnd2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotEnd3(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotEnd3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlotEnd4(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlotEnd4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByIsNew(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getIsNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByCouplet1(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getCouplet1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByCouplet2(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getCouplet2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByMainlandId(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getMainlandId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByChapterId(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getChapterId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findBySceneId(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getSceneId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByTollgateId(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getTollgateId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByWidth(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getWidth(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByHeight(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getHeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByIgnoreScale(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getIgnoreScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBoxScale(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBoxScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByDemonScale(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getDemonScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByAskScale(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getAskScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByMonsterId(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getMonsterId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByMonsterDropOut(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getMonsterDropOut(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineMonsterDropOut(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineMonsterDropOut(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByMonsterNumber(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getMonsterNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBossId(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBossId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBossDropOut(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBossDropOut(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBossDropOutFirst(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBossDropOutFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineBossId(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineBossId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineBossDropOut(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineBossDropOut(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineBossDropOutFirst(String value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineBossDropOutFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByMonsterGrade(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getMonsterGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPlayGrade(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPlayGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findBySingleCoins(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getSingleCoins(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByPictype(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBattle(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBattle(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByFighting(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getFighting(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByWilsonParam(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getWilsonParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineWilsonParam(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineWilsonParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByBossParam(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getBossParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByLineBossParam(float value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getLineBossParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<MissionMapTemplet> findByHurtMin(int value) {
		List<MissionMapTemplet> all = new ArrayList<MissionMapTemplet>();
		for (MissionMapTemplet f : getAll()) {
			if(equals(f.getHurtMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByPlotBegin1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotBegin1();
		}
		return all;
	}
	public static int[] getArrayByPlotBegin2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotBegin2();
		}
		return all;
	}
	public static int[] getArrayByPlotEnd1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotEnd1();
		}
		return all;
	}
	public static int[] getArrayByPlotEnd2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotEnd2();
		}
		return all;
	}
	public static int[] getArrayByPlotEnd3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotEnd3();
		}
		return all;
	}
	public static int[] getArrayByPlotEnd4() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlotEnd4();
		}
		return all;
	}
	public static int[] getArrayByIsNew() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getIsNew();
		}
		return all;
	}
	public static String[] getArrayByCouplet1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getCouplet1();
		}
		return all;
	}
	public static String[] getArrayByCouplet2() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getCouplet2();
		}
		return all;
	}
	public static int[] getArrayByMainlandId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getMainlandId();
		}
		return all;
	}
	public static int[] getArrayByChapterId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getChapterId();
		}
		return all;
	}
	public static int[] getArrayBySceneId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getSceneId();
		}
		return all;
	}
	public static int[] getArrayByTollgateId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getTollgateId();
		}
		return all;
	}
	public static int[] getArrayByWidth() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getWidth();
		}
		return all;
	}
	public static int[] getArrayByHeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getHeight();
		}
		return all;
	}
	public static float[] getArrayByIgnoreScale() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getIgnoreScale();
		}
		return all;
	}
	public static float[] getArrayByBoxScale() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBoxScale();
		}
		return all;
	}
	public static float[] getArrayByDemonScale() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getDemonScale();
		}
		return all;
	}
	public static float[] getArrayByAskScale() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getAskScale();
		}
		return all;
	}
	public static String[] getArrayByMonsterId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterId();
		}
		return all;
	}
	public static String[] getArrayByMonsterDropOut() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterDropOut();
		}
		return all;
	}
	public static String[] getArrayByLineMonsterDropOut() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineMonsterDropOut();
		}
		return all;
	}
	public static int[] getArrayByMonsterNumber() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterNumber();
		}
		return all;
	}
	public static String[] getArrayByBossId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBossId();
		}
		return all;
	}
	public static String[] getArrayByBossDropOut() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBossDropOut();
		}
		return all;
	}
	public static String[] getArrayByBossDropOutFirst() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBossDropOutFirst();
		}
		return all;
	}
	public static String[] getArrayByLineBossId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineBossId();
		}
		return all;
	}
	public static String[] getArrayByLineBossDropOut() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineBossDropOut();
		}
		return all;
	}
	public static String[] getArrayByLineBossDropOutFirst() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineBossDropOutFirst();
		}
		return all;
	}
	public static int[] getArrayByMonsterGrade() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterGrade();
		}
		return all;
	}
	public static int[] getArrayByPlayGrade() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPlayGrade();
		}
		return all;
	}
	public static int[] getArrayBySingleCoins() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getSingleCoins();
		}
		return all;
	}
	public static int[] getArrayByPictype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}
	public static int[] getArrayByBattle() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBattle();
		}
		return all;
	}
	public static int[] getArrayByFighting() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getFighting();
		}
		return all;
	}
	public static float[] getArrayByWilsonParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getWilsonParam();
		}
		return all;
	}
	public static float[] getArrayByLineWilsonParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineWilsonParam();
		}
		return all;
	}
	public static float[] getArrayByBossParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getBossParam();
		}
		return all;
	}
	public static float[] getArrayByLineBossParam() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getLineBossParam();
		}
		return all;
	}
	public static int[] getArrayByHurtMin() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionMapTemplet f = get(keys.get(i));
			all[i] = f.getHurtMin();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByPlotBegin1() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotBegin1());
		}
		return all;
	}
	public static List<Integer> getListByPlotBegin2() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotBegin2());
		}
		return all;
	}
	public static List<Integer> getListByPlotEnd1() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotEnd1());
		}
		return all;
	}
	public static List<Integer> getListByPlotEnd2() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotEnd2());
		}
		return all;
	}
	public static List<Integer> getListByPlotEnd3() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotEnd3());
		}
		return all;
	}
	public static List<Integer> getListByPlotEnd4() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlotEnd4());
		}
		return all;
	}
	public static List<Integer> getListByIsNew() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getIsNew());
		}
		return all;
	}
	public static List<String> getListByCouplet1() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getCouplet1());
		}
		return all;
	}
	public static List<String> getListByCouplet2() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getCouplet2());
		}
		return all;
	}
	public static List<Integer> getListByMainlandId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getMainlandId());
		}
		return all;
	}
	public static List<Integer> getListByChapterId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getChapterId());
		}
		return all;
	}
	public static List<Integer> getListBySceneId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getSceneId());
		}
		return all;
	}
	public static List<Integer> getListByTollgateId() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getTollgateId());
		}
		return all;
	}
	public static List<Integer> getListByWidth() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getWidth());
		}
		return all;
	}
	public static List<Integer> getListByHeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getHeight());
		}
		return all;
	}
	public static List<Float> getListByIgnoreScale() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getIgnoreScale());
		}
		return all;
	}
	public static List<Float> getListByBoxScale() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBoxScale());
		}
		return all;
	}
	public static List<Float> getListByDemonScale() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getDemonScale());
		}
		return all;
	}
	public static List<Float> getListByAskScale() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getAskScale());
		}
		return all;
	}
	public static List<String> getListByMonsterId() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getMonsterId());
		}
		return all;
	}
	public static List<String> getListByMonsterDropOut() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getMonsterDropOut());
		}
		return all;
	}
	public static List<String> getListByLineMonsterDropOut() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineMonsterDropOut());
		}
		return all;
	}
	public static List<Integer> getListByMonsterNumber() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getMonsterNumber());
		}
		return all;
	}
	public static List<String> getListByBossId() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBossId());
		}
		return all;
	}
	public static List<String> getListByBossDropOut() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBossDropOut());
		}
		return all;
	}
	public static List<String> getListByBossDropOutFirst() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBossDropOutFirst());
		}
		return all;
	}
	public static List<String> getListByLineBossId() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineBossId());
		}
		return all;
	}
	public static List<String> getListByLineBossDropOut() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineBossDropOut());
		}
		return all;
	}
	public static List<String> getListByLineBossDropOutFirst() {
		List<String> all = new ArrayList<String>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineBossDropOutFirst());
		}
		return all;
	}
	public static List<Integer> getListByMonsterGrade() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getMonsterGrade());
		}
		return all;
	}
	public static List<Integer> getListByPlayGrade() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPlayGrade());
		}
		return all;
	}
	public static List<Integer> getListBySingleCoins() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getSingleCoins());
		}
		return all;
	}
	public static List<Integer> getListByPictype() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
	public static List<Integer> getListByBattle() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBattle());
		}
		return all;
	}
	public static List<Integer> getListByFighting() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getFighting());
		}
		return all;
	}
	public static List<Float> getListByWilsonParam() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getWilsonParam());
		}
		return all;
	}
	public static List<Float> getListByLineWilsonParam() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineWilsonParam());
		}
		return all;
	}
	public static List<Float> getListByBossParam() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getBossParam());
		}
		return all;
	}
	public static List<Float> getListByLineBossParam() {
		List<Float> all = new ArrayList<Float>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getLineBossParam());
		}
		return all;
	}
	public static List<Integer> getListByHurtMin() {
		List<Integer> all = new ArrayList<Integer>();
		for (MissionMapTemplet f : getAll()) {
			all.add(f.getHurtMin());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}