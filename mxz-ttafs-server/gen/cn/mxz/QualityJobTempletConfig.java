//[神将]职业属性成长系数package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class QualityJobTempletConfig {	private static Map<Integer, QualityJobTemplet> map;	private static List<Integer> keys;	private static List<QualityJobTemplet> all;	static {		load();	}	public static List<QualityJobTemplet> getAll() {		return new ArrayList<QualityJobTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/QualityJobConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, QualityJobTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																QualityJobTempletConfig.map = map;		QualityJobTempletConfig.keys = keys;																List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		QualityJobTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, QualityJobTemplet> map) {		QualityJobTemplet x = new QualityJobTemplet();		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setTypeJob( e.attributeValue("typeJob") );		x.setTypeJobInterior( e.attributeValue("typeJobInterior") );		x.setHpPar( Float.parseFloat( e.attributeValue("hpPar").trim() ) );		x.setAttackPar( Float.parseFloat( e.attributeValue("attackPar").trim() ) );		x.setMAttackPar( Float.parseFloat( e.attributeValue("mAttackPar").trim() ) );		x.setDefendPar( Float.parseFloat( e.attributeValue("defendPar").trim() ) );		x.setMDefendPar( Float.parseFloat( e.attributeValue("mDefendPar").trim() ) );		x.setSpeedPar( Float.parseFloat( e.attributeValue("speedPar").trim() ) );		x.setCritPar( Float.parseFloat( e.attributeValue("critPar").trim() ) );		x.setDodgePar( Float.parseFloat( e.attributeValue("dodgePar").trim() ) );		x.setBlockPar( Float.parseFloat( e.attributeValue("blockPar").trim() ) );		x.setRCritPar( Float.parseFloat( e.attributeValue("rCritPar").trim() ) );		x.setHitPar( Float.parseFloat( e.attributeValue("hitPar").trim() ) );		x.setRBlockPar( Float.parseFloat( e.attributeValue("rBlockPar").trim() ) );		x.setCritAdditionPar( Float.parseFloat( e.attributeValue("critAdditionPar").trim() ) );		x.setMagicPar( Float.parseFloat( e.attributeValue("magicPar").trim() ) );		QualityJobTemplet remove = map.put(x.getType(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static QualityJobTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static QualityJobTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static QualityJobTemplet getMin() {		return get(getMinKey());	}	public static List<QualityJobTemplet> findByType(int value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByTypeJob(String value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getTypeJob(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByTypeJobInterior(String value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getTypeJobInterior(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByHpPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getHpPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByAttackPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByMAttackPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getMAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByDefendPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByMDefendPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getMDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findBySpeedPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getSpeedPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByCritPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByDodgePar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getDodgePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByBlockPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByRCritPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getRCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByHitPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getHitPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByRBlockPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getRBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByCritAdditionPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getCritAdditionPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<QualityJobTemplet> findByMagicPar(float value) {
		List<QualityJobTemplet> all = new ArrayList<QualityJobTemplet>();
		for (QualityJobTemplet f : getAll()) {
			if(equals(f.getMagicPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByTypeJob() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getTypeJob();
		}
		return all;
	}
	public static String[] getArrayByTypeJobInterior() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getTypeJobInterior();
		}
		return all;
	}
	public static float[] getArrayByHpPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getHpPar();
		}
		return all;
	}
	public static float[] getArrayByAttackPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getAttackPar();
		}
		return all;
	}
	public static float[] getArrayByMAttackPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getMAttackPar();
		}
		return all;
	}
	public static float[] getArrayByDefendPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getDefendPar();
		}
		return all;
	}
	public static float[] getArrayByMDefendPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getMDefendPar();
		}
		return all;
	}
	public static float[] getArrayBySpeedPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getSpeedPar();
		}
		return all;
	}
	public static float[] getArrayByCritPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getCritPar();
		}
		return all;
	}
	public static float[] getArrayByDodgePar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getDodgePar();
		}
		return all;
	}
	public static float[] getArrayByBlockPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getBlockPar();
		}
		return all;
	}
	public static float[] getArrayByRCritPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getRCritPar();
		}
		return all;
	}
	public static float[] getArrayByHitPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getHitPar();
		}
		return all;
	}
	public static float[] getArrayByRBlockPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getRBlockPar();
		}
		return all;
	}
	public static float[] getArrayByCritAdditionPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getCritAdditionPar();
		}
		return all;
	}
	public static float[] getArrayByMagicPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityJobTemplet f = get(keys.get(i));
			all[i] = f.getMagicPar();
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByTypeJob() {
		List<String> all = new ArrayList<String>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getTypeJob());
		}
		return all;
	}
	public static List<String> getListByTypeJobInterior() {
		List<String> all = new ArrayList<String>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getTypeJobInterior());
		}
		return all;
	}
	public static List<Float> getListByHpPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getHpPar());
		}
		return all;
	}
	public static List<Float> getListByAttackPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getAttackPar());
		}
		return all;
	}
	public static List<Float> getListByMAttackPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getMAttackPar());
		}
		return all;
	}
	public static List<Float> getListByDefendPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getDefendPar());
		}
		return all;
	}
	public static List<Float> getListByMDefendPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getMDefendPar());
		}
		return all;
	}
	public static List<Float> getListBySpeedPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getSpeedPar());
		}
		return all;
	}
	public static List<Float> getListByCritPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getCritPar());
		}
		return all;
	}
	public static List<Float> getListByDodgePar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getDodgePar());
		}
		return all;
	}
	public static List<Float> getListByBlockPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getBlockPar());
		}
		return all;
	}
	public static List<Float> getListByRCritPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getRCritPar());
		}
		return all;
	}
	public static List<Float> getListByHitPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getHitPar());
		}
		return all;
	}
	public static List<Float> getListByRBlockPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getRBlockPar());
		}
		return all;
	}
	public static List<Float> getListByCritAdditionPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getCritAdditionPar());
		}
		return all;
	}
	public static List<Float> getListByMagicPar() {
		List<Float> all = new ArrayList<Float>();
		for (QualityJobTemplet f : getAll()) {
			all.add(f.getMagicPar());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}