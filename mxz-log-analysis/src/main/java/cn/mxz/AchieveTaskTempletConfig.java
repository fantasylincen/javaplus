//[任务]成就任务package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class AchieveTaskTempletConfig {	private static Map<Integer, AchieveTaskTemplet> map;	private static List<Integer> keys;	private static List<AchieveTaskTemplet> all;	static {		load();		all = new ArrayList<AchieveTaskTemplet>();		for(Integer c : keys) {			all.add(get(c));		}	}	public static List<AchieveTaskTemplet> getAll() {		return new ArrayList<AchieveTaskTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "D:/workspace/MobileServer/res/properties/AchieveTaskConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		map = Maps.newConcurrentMap();		keys = new ArrayList<Integer>();		try {			synchronized (map) {				File inputXml = new File(fileName);				SAXReader saxReader = new SAXReader();					Document document = saxReader.read(inputXml);					Element employees = document.getRootElement();					for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {						Element e = i.next();						try {							put(e);						} catch (RuntimeException e1) {							List<Attribute> all = e.attributes();							StringBuilder sb = new StringBuilder();							for (Attribute o : all) {								sb.append("[" + o.getStringValue() + "]");							}							System.err.println("Error:" + fileName + "......" + sb);							throw e1;						}					}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);	}	private static void put(Element e) {		AchieveTaskTemplet x = new AchieveTaskTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setOpenOnGiveBack( e.attributeValue("openOnGiveBack") );		x.setChapterNeed( Integer.decode( e.attributeValue("chapterNeed").trim() ) );		x.setCode( e.attributeValue("code") );		x.setReturnType( e.attributeValue("returnType") );		x.setArg1( e.attributeValue("arg1") );		x.setMax( Integer.decode( e.attributeValue("max").trim() ) );		x.setDescription( e.attributeValue("description") );		x.setChapter( Short.parseShort( e.attributeValue("chapter").trim() ) );		x.setGoalid( Short.parseShort( e.attributeValue("goalid").trim() ) );		x.setIsOpenDefault( Integer.decode( e.attributeValue("isOpenDefault").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setName( e.attributeValue("name") );		x.setLink( Integer.decode( e.attributeValue("link").trim() ) );		x.setUserLevelNeed( Integer.decode( e.attributeValue("userLevelNeed").trim() ) );		x.setIsCheck( Integer.decode( e.attributeValue("isCheck").trim() ) );		x.setAwards( e.attributeValue("awards") );		x.setUrl( e.attributeValue("url") );		AchieveTaskTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static AchieveTaskTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static AchieveTaskTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static AchieveTaskTemplet getMin() {		return get(getMinKey());	}	public static List<AchieveTaskTemplet> findById(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByOpenOnGiveBack(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getOpenOnGiveBack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByChapterNeed(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getChapterNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByCode(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByReturnType(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getReturnType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByArg1(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getArg1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByMax(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByDescription(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByChapter(short value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByGoalid(short value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getGoalid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByIsOpenDefault(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getIsOpenDefault(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByResid(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByPrestrain(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByFormat(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByName(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByLink(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByUserLevelNeed(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getUserLevelNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByIsCheck(int value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getIsCheck(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByAwards(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<AchieveTaskTemplet> findByUrl(String value) {
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByOpenOnGiveBack() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getOpenOnGiveBack();
		}
		return all;
	}
	public static int[] getArrayByChapterNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getChapterNeed();
		}
		return all;
	}
	public static String[] getArrayByCode() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}
	public static String[] getArrayByReturnType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getReturnType();
		}
		return all;
	}
	public static String[] getArrayByArg1() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getArg1();
		}
		return all;
	}
	public static int[] getArrayByMax() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static short[] getArrayByChapter() {
		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getChapter();
		}
		return all;
	}
	public static short[] getArrayByGoalid() {
		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getGoalid();
		}
		return all;
	}
	public static int[] getArrayByIsOpenDefault() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsOpenDefault();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByLink() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}
	public static int[] getArrayByUserLevelNeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getUserLevelNeed();
		}
		return all;
	}
	public static int[] getArrayByIsCheck() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsCheck();
		}
		return all;
	}
	public static String[] getArrayByAwards() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByOpenOnGiveBack() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getOpenOnGiveBack());
		}
		return all;
	}
	public static List<Integer> getListByChapterNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getChapterNeed());
		}
		return all;
	}
	public static List<String> getListByCode() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}
	public static List<String> getListByReturnType() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getReturnType());
		}
		return all;
	}
	public static List<String> getListByArg1() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getArg1());
		}
		return all;
	}
	public static List<Integer> getListByMax() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<Short> getListByChapter() {
		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getChapter());
		}
		return all;
	}
	public static List<Short> getListByGoalid() {
		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getGoalid());
		}
		return all;
	}
	public static List<Integer> getListByIsOpenDefault() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getIsOpenDefault());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByLink() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}
	public static List<Integer> getListByUserLevelNeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getUserLevelNeed());
		}
		return all;
	}
	public static List<Integer> getListByIsCheck() {
		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getIsCheck());
		}
		return all;
	}
	public static List<String> getListByAwards() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}