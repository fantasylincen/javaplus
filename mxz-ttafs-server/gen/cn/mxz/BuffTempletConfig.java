//[战斗]Bufferpackage cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class BuffTempletConfig {	private static Map<Integer, BuffTemplet> map;	private static List<Integer> keys;	private static List<BuffTemplet> all;	static {		load();	}	public static List<BuffTemplet> getAll() {		return new ArrayList<BuffTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/BuffConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, BuffTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																BuffTempletConfig.map = map;		BuffTempletConfig.keys = keys;																List<BuffTemplet> all = new ArrayList<BuffTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		BuffTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, BuffTemplet> map) {		BuffTemplet x = new BuffTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setHpReduce( Integer.decode( e.attributeValue("hpReduce").trim() ) );		x.setHpReduceScale( Float.parseFloat( e.attributeValue("hpReduceScale").trim() ) );		x.setLowerDefense( Integer.decode( e.attributeValue("lowerDefense").trim() ) );		x.setLowerDefensePar( Float.parseFloat( e.attributeValue("lowerDefensePar").trim() ) );		x.setWaterEffect( Integer.decode( e.attributeValue("waterEffect").trim() ) );		x.setReleaseSkill( Integer.decode( e.attributeValue("releaseSkill").trim() ) );		x.setCanHit( Byte.parseByte( e.attributeValue("canHit").trim() ) );		x.setRounds( Integer.decode( e.attributeValue("rounds").trim() ) );		x.setResId( Integer.decode( e.attributeValue("resId").trim() ) );		x.setCategory( Integer.decode( e.attributeValue("category").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setType( Integer.decode( e.attributeValue("Type").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setFriendlyStatus( Integer.decode( e.attributeValue("friendlyStatus").trim() ) );		x.setDescription( e.attributeValue("description") );		BuffTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static BuffTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static BuffTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static BuffTemplet getMin() {		return get(getMinKey());	}	public static List<BuffTemplet> findById(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByName(String value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByHpReduce(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getHpReduce(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByHpReduceScale(float value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getHpReduceScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByLowerDefense(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getLowerDefense(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByLowerDefensePar(float value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getLowerDefensePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByWaterEffect(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getWaterEffect(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByReleaseSkill(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getReleaseSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByCanHit(byte value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getCanHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByRounds(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getRounds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByResId(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getResId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByCategory(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByFormat(String value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByType(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByUrl(String value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByFriendlyStatus(int value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getFriendlyStatus(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<BuffTemplet> findByDescription(String value) {
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static int[] getArrayByHpReduce() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getHpReduce();
		}
		return all;
	}
	public static float[] getArrayByHpReduceScale() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getHpReduceScale();
		}
		return all;
	}
	public static int[] getArrayByLowerDefense() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getLowerDefense();
		}
		return all;
	}
	public static float[] getArrayByLowerDefensePar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getLowerDefensePar();
		}
		return all;
	}
	public static int[] getArrayByWaterEffect() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getWaterEffect();
		}
		return all;
	}
	public static int[] getArrayByReleaseSkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getReleaseSkill();
		}
		return all;
	}
	public static byte[] getArrayByCanHit() {
		byte[] all = new byte[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getCanHit();
		}
		return all;
	}
	public static int[] getArrayByRounds() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getRounds();
		}
		return all;
	}
	public static int[] getArrayByResId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getResId();
		}
		return all;
	}
	public static int[] getArrayByCategory() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static int[] getArrayByFriendlyStatus() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getFriendlyStatus();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<Integer> getListByHpReduce() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getHpReduce());
		}
		return all;
	}
	public static List<Float> getListByHpReduceScale() {
		List<Float> all = new ArrayList<Float>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getHpReduceScale());
		}
		return all;
	}
	public static List<Integer> getListByLowerDefense() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getLowerDefense());
		}
		return all;
	}
	public static List<Float> getListByLowerDefensePar() {
		List<Float> all = new ArrayList<Float>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getLowerDefensePar());
		}
		return all;
	}
	public static List<Integer> getListByWaterEffect() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getWaterEffect());
		}
		return all;
	}
	public static List<Integer> getListByReleaseSkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getReleaseSkill());
		}
		return all;
	}
	public static List<Byte> getListByCanHit() {
		List<Byte> all = new ArrayList<Byte>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getCanHit());
		}
		return all;
	}
	public static List<Integer> getListByRounds() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getRounds());
		}
		return all;
	}
	public static List<Integer> getListByResId() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getResId());
		}
		return all;
	}
	public static List<Integer> getListByCategory() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<Integer> getListByFriendlyStatus() {
		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getFriendlyStatus());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}