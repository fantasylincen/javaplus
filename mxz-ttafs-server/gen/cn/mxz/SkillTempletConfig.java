//[战斗][技能]package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class SkillTempletConfig {	private static Map<Integer, SkillTemplet> map;	private static List<Integer> keys;	private static List<SkillTemplet> all;	static {		load();	}	public static List<SkillTemplet> getAll() {		return new ArrayList<SkillTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/SkillConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, SkillTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																SkillTempletConfig.map = map;		SkillTempletConfig.keys = keys;																List<SkillTemplet> all = new ArrayList<SkillTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		SkillTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, SkillTemplet> map) {		SkillTemplet x = new SkillTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setName( e.attributeValue("name") );		x.setNameInterior( e.attributeValue("nameInterior") );		x.setEndName( e.attributeValue("endName") );		x.setStage( Integer.decode( e.attributeValue("stage").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setSkillPro( Float.parseFloat( e.attributeValue("skillPro").trim() ) );		x.setAddPro( Float.parseFloat( e.attributeValue("addPro").trim() ) );		x.setAttackType( Integer.decode( e.attributeValue("attackType").trim() ) );		x.setAtkTypeName( e.attributeValue("atkTypeName") );		x.setCommonAttack( Integer.decode( e.attributeValue("commonAttack").trim() ) );		x.setValueType( Integer.decode( e.attributeValue("valueType").trim() ) );		x.setNormalRatio( Float.parseFloat( e.attributeValue("normalRatio").trim() ) );		x.setMagicRatio( Float.parseFloat( e.attributeValue("magicRatio").trim() ) );		x.setSpeedRatio( Float.parseFloat( e.attributeValue("speedRatio").trim() ) );		x.setBitBonus( Integer.decode( e.attributeValue("bitBonus").trim() ) );		x.setDamageType( Integer.decode( e.attributeValue("damageType").trim() ) );		x.setPasvFront( Integer.decode( e.attributeValue("pasvFront").trim() ) );		x.setFrontPar( Float.parseFloat( e.attributeValue("frontPar").trim() ) );		x.setFrontFixed( Float.parseFloat( e.attributeValue("frontFixed").trim() ) );		x.setFrontGrowPar( Float.parseFloat( e.attributeValue("frontGrowPar").trim() ) );		x.setFrontGrowFixed( Float.parseFloat( e.attributeValue("frontGrowFixed").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setSocialGrow( Float.parseFloat( e.attributeValue("socialGrow").trim() ) );		x.setExclusiveId( e.attributeValue("exclusiveId") );		x.setExclusiveName( e.attributeValue("exclusiveName") );		x.setContent( e.attributeValue("content") );		x.setDescription( e.attributeValue("description") );		x.setSketch( e.attributeValue("sketch") );		x.setMigc( Integer.decode( e.attributeValue("migc").trim() ) );		x.setTx( Integer.decode( e.attributeValue("tx").trim() ) );		x.setIsTragerFirstRound( Integer.decode( e.attributeValue("isTragerFirstRound").trim() ) );		x.setReleaseType( Integer.decode( e.attributeValue("releaseType").trim() ) );		x.setHarmPar( Float.parseFloat( e.attributeValue("harmPar").trim() ) );		x.setHarmFixed( Integer.decode( e.attributeValue("harmFixed").trim() ) );		x.setHarmGrowPar( Float.parseFloat( e.attributeValue("harmGrowPar").trim() ) );		x.setHarmGrowFixed( Float.parseFloat( e.attributeValue("harmGrowFixed").trim() ) );		x.setLocale( Integer.decode( e.attributeValue("locale").trim() ) );		x.setVoiceSA( Integer.decode( e.attributeValue("voiceSA").trim() ) );		x.setVoiceSF( Integer.decode( e.attributeValue("voiceSF").trim() ) );		x.setVoiceSD( Integer.decode( e.attributeValue("voiceSD").trim() ) );		x.setSA( e.attributeValue("SA") );		x.setSF( e.attributeValue("SF") );		x.setSD( e.attributeValue("SD") );		x.setSX( e.attributeValue("SX") );		x.setHitmove( Integer.decode( e.attributeValue("hitmove").trim() ) );		x.setAttackfly( Integer.decode( e.attributeValue("attackfly").trim() ) );		x.setAttackhit( Integer.decode( e.attributeValue("attackhit").trim() ) );		x.setAttacktimes( Integer.decode( e.attributeValue("attacktimes").trim() ) );		x.setHittimes( Integer.decode( e.attributeValue("hittimes").trim() ) );		x.setBloodtimes( Integer.decode( e.attributeValue("bloodtimes").trim() ) );		x.setSpecil( Integer.decode( e.attributeValue("specil").trim() ) );		x.setPath( Integer.decode( e.attributeValue("path").trim() ) );		x.setSkillword( Integer.decode( e.attributeValue("skillword").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setSP( e.attributeValue("SP") );		x.setUrl( e.attributeValue("url") );		x.setSpotName( e.attributeValue("spotName") );		x.setChip( e.attributeValue("chip") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setPurpose( e.attributeValue("purpose") );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		SkillTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static SkillTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static SkillTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static SkillTemplet getMin() {		return get(getMinKey());	}	public static List<SkillTemplet> findById(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByName(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByNameInterior(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getNameInterior(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByEndName(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByStage(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getStage(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByStepAgo(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySkillPro(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSkillPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAddPro(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAddPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAttackType(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAttackType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAtkTypeName(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAtkTypeName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByCommonAttack(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getCommonAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByValueType(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getValueType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByNormalRatio(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getNormalRatio(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByMagicRatio(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getMagicRatio(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySpeedRatio(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSpeedRatio(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByBitBonus(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getBitBonus(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByDamageType(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getDamageType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByPasvFront(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getPasvFront(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByFrontPar(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getFrontPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByFrontFixed(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getFrontFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByFrontGrowPar(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getFrontGrowPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByFrontGrowFixed(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getFrontGrowFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySocial(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySocialGrow(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSocialGrow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByExclusiveId(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getExclusiveId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByExclusiveName(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getExclusiveName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByContent(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByDescription(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySketch(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSketch(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByMigc(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getMigc(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByTx(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getTx(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByIsTragerFirstRound(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getIsTragerFirstRound(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByReleaseType(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getReleaseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHarmPar(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHarmPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHarmFixed(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHarmFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHarmGrowPar(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHarmGrowPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHarmGrowFixed(float value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHarmGrowFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByLocale(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getLocale(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByVoiceSA(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getVoiceSA(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByVoiceSF(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getVoiceSF(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByVoiceSD(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getVoiceSD(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySA(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSA(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySF(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSF(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySD(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSD(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySX(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHitmove(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHitmove(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAttackfly(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAttackfly(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAttackhit(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAttackhit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAttacktimes(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAttacktimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByHittimes(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getHittimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByBloodtimes(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getBloodtimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySpecil(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSpecil(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByPath(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getPath(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySkillword(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSkillword(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByFormat(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySP(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSP(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByUrl(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findBySpotName(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getSpotName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByChip(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getChip(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByToolUseType(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByAccordUse(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByBackpack(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByResid(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByPrestrain(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByPurpose(String value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<SkillTemplet> findByRetain(int value) {
		List<SkillTemplet> all = new ArrayList<SkillTemplet>();
		for (SkillTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByNameInterior() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getNameInterior();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByStage() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getStage();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static float[] getArrayBySkillPro() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSkillPro();
		}
		return all;
	}
	public static float[] getArrayByAddPro() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAddPro();
		}
		return all;
	}
	public static int[] getArrayByAttackType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAttackType();
		}
		return all;
	}
	public static String[] getArrayByAtkTypeName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAtkTypeName();
		}
		return all;
	}
	public static int[] getArrayByCommonAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getCommonAttack();
		}
		return all;
	}
	public static int[] getArrayByValueType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getValueType();
		}
		return all;
	}
	public static float[] getArrayByNormalRatio() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getNormalRatio();
		}
		return all;
	}
	public static float[] getArrayByMagicRatio() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getMagicRatio();
		}
		return all;
	}
	public static float[] getArrayBySpeedRatio() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSpeedRatio();
		}
		return all;
	}
	public static int[] getArrayByBitBonus() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getBitBonus();
		}
		return all;
	}
	public static int[] getArrayByDamageType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getDamageType();
		}
		return all;
	}
	public static int[] getArrayByPasvFront() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getPasvFront();
		}
		return all;
	}
	public static float[] getArrayByFrontPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getFrontPar();
		}
		return all;
	}
	public static float[] getArrayByFrontFixed() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getFrontFixed();
		}
		return all;
	}
	public static float[] getArrayByFrontGrowPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getFrontGrowPar();
		}
		return all;
	}
	public static float[] getArrayByFrontGrowFixed() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getFrontGrowFixed();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static float[] getArrayBySocialGrow() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSocialGrow();
		}
		return all;
	}
	public static String[] getArrayByExclusiveId() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getExclusiveId();
		}
		return all;
	}
	public static String[] getArrayByExclusiveName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getExclusiveName();
		}
		return all;
	}
	public static String[] getArrayByContent() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static String[] getArrayBySketch() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSketch();
		}
		return all;
	}
	public static int[] getArrayByMigc() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getMigc();
		}
		return all;
	}
	public static int[] getArrayByTx() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getTx();
		}
		return all;
	}
	public static int[] getArrayByIsTragerFirstRound() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getIsTragerFirstRound();
		}
		return all;
	}
	public static int[] getArrayByReleaseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getReleaseType();
		}
		return all;
	}
	public static float[] getArrayByHarmPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHarmPar();
		}
		return all;
	}
	public static int[] getArrayByHarmFixed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHarmFixed();
		}
		return all;
	}
	public static float[] getArrayByHarmGrowPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHarmGrowPar();
		}
		return all;
	}
	public static float[] getArrayByHarmGrowFixed() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHarmGrowFixed();
		}
		return all;
	}
	public static int[] getArrayByLocale() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getLocale();
		}
		return all;
	}
	public static int[] getArrayByVoiceSA() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getVoiceSA();
		}
		return all;
	}
	public static int[] getArrayByVoiceSF() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getVoiceSF();
		}
		return all;
	}
	public static int[] getArrayByVoiceSD() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getVoiceSD();
		}
		return all;
	}
	public static String[] getArrayBySA() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSA();
		}
		return all;
	}
	public static String[] getArrayBySF() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSF();
		}
		return all;
	}
	public static String[] getArrayBySD() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSD();
		}
		return all;
	}
	public static String[] getArrayBySX() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSX();
		}
		return all;
	}
	public static int[] getArrayByHitmove() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHitmove();
		}
		return all;
	}
	public static int[] getArrayByAttackfly() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAttackfly();
		}
		return all;
	}
	public static int[] getArrayByAttackhit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAttackhit();
		}
		return all;
	}
	public static int[] getArrayByAttacktimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAttacktimes();
		}
		return all;
	}
	public static int[] getArrayByHittimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getHittimes();
		}
		return all;
	}
	public static int[] getArrayByBloodtimes() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getBloodtimes();
		}
		return all;
	}
	public static int[] getArrayBySpecil() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSpecil();
		}
		return all;
	}
	public static int[] getArrayByPath() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getPath();
		}
		return all;
	}
	public static int[] getArrayBySkillword() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSkillword();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static String[] getArrayBySP() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSP();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayBySpotName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getSpotName();
		}
		return all;
	}
	public static String[] getArrayByChip() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getChip();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByNameInterior() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getNameInterior());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByStage() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getStage());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<Float> getListBySkillPro() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSkillPro());
		}
		return all;
	}
	public static List<Float> getListByAddPro() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAddPro());
		}
		return all;
	}
	public static List<Integer> getListByAttackType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAttackType());
		}
		return all;
	}
	public static List<String> getListByAtkTypeName() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAtkTypeName());
		}
		return all;
	}
	public static List<Integer> getListByCommonAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getCommonAttack());
		}
		return all;
	}
	public static List<Integer> getListByValueType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getValueType());
		}
		return all;
	}
	public static List<Float> getListByNormalRatio() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getNormalRatio());
		}
		return all;
	}
	public static List<Float> getListByMagicRatio() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getMagicRatio());
		}
		return all;
	}
	public static List<Float> getListBySpeedRatio() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSpeedRatio());
		}
		return all;
	}
	public static List<Integer> getListByBitBonus() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getBitBonus());
		}
		return all;
	}
	public static List<Integer> getListByDamageType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getDamageType());
		}
		return all;
	}
	public static List<Integer> getListByPasvFront() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getPasvFront());
		}
		return all;
	}
	public static List<Float> getListByFrontPar() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getFrontPar());
		}
		return all;
	}
	public static List<Float> getListByFrontFixed() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getFrontFixed());
		}
		return all;
	}
	public static List<Float> getListByFrontGrowPar() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getFrontGrowPar());
		}
		return all;
	}
	public static List<Float> getListByFrontGrowFixed() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getFrontGrowFixed());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<Float> getListBySocialGrow() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSocialGrow());
		}
		return all;
	}
	public static List<String> getListByExclusiveId() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getExclusiveId());
		}
		return all;
	}
	public static List<String> getListByExclusiveName() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getExclusiveName());
		}
		return all;
	}
	public static List<String> getListByContent() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	public static List<String> getListBySketch() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSketch());
		}
		return all;
	}
	public static List<Integer> getListByMigc() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getMigc());
		}
		return all;
	}
	public static List<Integer> getListByTx() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getTx());
		}
		return all;
	}
	public static List<Integer> getListByIsTragerFirstRound() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getIsTragerFirstRound());
		}
		return all;
	}
	public static List<Integer> getListByReleaseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getReleaseType());
		}
		return all;
	}
	public static List<Float> getListByHarmPar() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHarmPar());
		}
		return all;
	}
	public static List<Integer> getListByHarmFixed() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHarmFixed());
		}
		return all;
	}
	public static List<Float> getListByHarmGrowPar() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHarmGrowPar());
		}
		return all;
	}
	public static List<Float> getListByHarmGrowFixed() {
		List<Float> all = new ArrayList<Float>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHarmGrowFixed());
		}
		return all;
	}
	public static List<Integer> getListByLocale() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getLocale());
		}
		return all;
	}
	public static List<Integer> getListByVoiceSA() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getVoiceSA());
		}
		return all;
	}
	public static List<Integer> getListByVoiceSF() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getVoiceSF());
		}
		return all;
	}
	public static List<Integer> getListByVoiceSD() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getVoiceSD());
		}
		return all;
	}
	public static List<String> getListBySA() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSA());
		}
		return all;
	}
	public static List<String> getListBySF() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSF());
		}
		return all;
	}
	public static List<String> getListBySD() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSD());
		}
		return all;
	}
	public static List<String> getListBySX() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSX());
		}
		return all;
	}
	public static List<Integer> getListByHitmove() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHitmove());
		}
		return all;
	}
	public static List<Integer> getListByAttackfly() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAttackfly());
		}
		return all;
	}
	public static List<Integer> getListByAttackhit() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAttackhit());
		}
		return all;
	}
	public static List<Integer> getListByAttacktimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAttacktimes());
		}
		return all;
	}
	public static List<Integer> getListByHittimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getHittimes());
		}
		return all;
	}
	public static List<Integer> getListByBloodtimes() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getBloodtimes());
		}
		return all;
	}
	public static List<Integer> getListBySpecil() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSpecil());
		}
		return all;
	}
	public static List<Integer> getListByPath() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getPath());
		}
		return all;
	}
	public static List<Integer> getListBySkillword() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSkillword());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<String> getListBySP() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSP());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListBySpotName() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getSpotName());
		}
		return all;
	}
	public static List<String> getListByChip() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getChip());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (SkillTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}