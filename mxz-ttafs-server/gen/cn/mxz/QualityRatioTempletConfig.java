//[神将]属性成长系数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class QualityRatioTempletConfig {	private static Map<Integer, QualityRatioTemplet> map;	private static List<Integer> keys;	private static List<QualityRatioTemplet> all;	static {		load();	}	public static List<QualityRatioTemplet> getAll() {		return new ArrayList<QualityRatioTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/QualityRatioConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, QualityRatioTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																QualityRatioTempletConfig.map = map;		QualityRatioTempletConfig.keys = keys;																List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		QualityRatioTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, QualityRatioTemplet> map) {		QualityRatioTemplet x = new QualityRatioTemplet();		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setTypeState( e.attributeValue("typeState") );		x.setHpPar( Float.parseFloat( e.attributeValue("hpPar").trim() ) );		x.setAttackPar( Float.parseFloat( e.attributeValue("attackPar").trim() ) );		x.setMAttackPar( Float.parseFloat( e.attributeValue("mAttackPar").trim() ) );		x.setDefendPar( Float.parseFloat( e.attributeValue("defendPar").trim() ) );		x.setMDefendPar( Float.parseFloat( e.attributeValue("mDefendPar").trim() ) );		x.setSpeedPar( Float.parseFloat( e.attributeValue("speedPar").trim() ) );		x.setCritPar( Float.parseFloat( e.attributeValue("critPar").trim() ) );		x.setDodgePar( Float.parseFloat( e.attributeValue("dodgePar").trim() ) );		x.setBlockPar( Float.parseFloat( e.attributeValue("blockPar").trim() ) );		x.setRCritPar( Float.parseFloat( e.attributeValue("rCritPar").trim() ) );		x.setHitPar( Float.parseFloat( e.attributeValue("hitPar").trim() ) );		x.setRBlockPar( Float.parseFloat( e.attributeValue("rBlockPar").trim() ) );		x.setCritAdditionPar( Float.parseFloat( e.attributeValue("critAdditionPar").trim() ) );		x.setMagicPar( Float.parseFloat( e.attributeValue("magicPar").trim() ) );		QualityRatioTemplet remove = map.put(x.getType(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static QualityRatioTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static QualityRatioTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static QualityRatioTemplet getMin() {		return get(getMinKey());	}	public static List<QualityRatioTemplet> findByType(int value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByTypeState(String value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getTypeState(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByHpPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getHpPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByAttackPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByMAttackPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByDefendPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByMDefendPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findBySpeedPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getSpeedPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByCritPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByDodgePar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getDodgePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByBlockPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByRCritPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getRCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByHitPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getHitPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByRBlockPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getRBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByCritAdditionPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getCritAdditionPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityRatioTemplet> findByMagicPar(float value) {
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMagicPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByTypeState() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getTypeState();
		}
		return all;
	}
	public static float[] getArrayByHpPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getHpPar();
		}
		return all;
	}
	public static float[] getArrayByAttackPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getAttackPar();
		}
		return all;
	}
	public static float[] getArrayByMAttackPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMAttackPar();
		}
		return all;
	}
	public static float[] getArrayByDefendPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getDefendPar();
		}
		return all;
	}
	public static float[] getArrayByMDefendPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMDefendPar();
		}
		return all;
	}
	public static float[] getArrayBySpeedPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getSpeedPar();
		}
		return all;
	}
	public static float[] getArrayByCritPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getCritPar();
		}
		return all;
	}
	public static float[] getArrayByDodgePar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getDodgePar();
		}
		return all;
	}
	public static float[] getArrayByBlockPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getBlockPar();
		}
		return all;
	}
	public static float[] getArrayByRCritPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getRCritPar();
		}
		return all;
	}
	public static float[] getArrayByHitPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getHitPar();
		}
		return all;
	}
	public static float[] getArrayByRBlockPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getRBlockPar();
		}
		return all;
	}
	public static float[] getArrayByCritAdditionPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getCritAdditionPar();
		}
		return all;
	}
	public static float[] getArrayByMagicPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMagicPar();
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByTypeState() {
		List<String> all = new ArrayList<String>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getTypeState());
		}
		return all;
	}
	public static List<Float> getListByHpPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getHpPar());
		}
		return all;
	}
	public static List<Float> getListByAttackPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getAttackPar());
		}
		return all;
	}
	public static List<Float> getListByMAttackPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMAttackPar());
		}
		return all;
	}
	public static List<Float> getListByDefendPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getDefendPar());
		}
		return all;
	}
	public static List<Float> getListByMDefendPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMDefendPar());
		}
		return all;
	}
	public static List<Float> getListBySpeedPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getSpeedPar());
		}
		return all;
	}
	public static List<Float> getListByCritPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getCritPar());
		}
		return all;
	}
	public static List<Float> getListByDodgePar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getDodgePar());
		}
		return all;
	}
	public static List<Float> getListByBlockPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getBlockPar());
		}
		return all;
	}
	public static List<Float> getListByRCritPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getRCritPar());
		}
		return all;
	}
	public static List<Float> getListByHitPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getHitPar());
		}
		return all;
	}
	public static List<Float> getListByRBlockPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getRBlockPar());
		}
		return all;
	}
	public static List<Float> getListByCritAdditionPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getCritAdditionPar());
		}
		return all;
	}
	public static List<Float> getListByMagicPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMagicPar());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}