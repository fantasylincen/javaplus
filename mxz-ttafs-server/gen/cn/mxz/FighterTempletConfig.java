//[战士]战士模版package cn.mxz;import java.io.File;import com.google.common.collect.Maps;import java.util.Iterator;import java.util.Map;import java.util.Collections;import java.util.List;import java.util.ArrayList;import org.dom4j.Document;import org.dom4j.Element;import org.dom4j.io.SAXReader;import org.dom4j.Attribute;public class FighterTempletConfig {	private static Map<Integer, FighterTemplet> map;	private static List<Integer> keys;	private static List<FighterTemplet> all;	static {		load();	}	public static List<FighterTemplet> getAll() {		return new ArrayList<FighterTemplet>(all);	}	public static List<Integer> getKeys() {		return keys;	}	private static final String fileName = "res/properties/FighterConfig.xml";	@SuppressWarnings("unchecked")	public static void load() {		Map<Integer, FighterTemplet> map = Maps.newConcurrentMap();		List<Integer> keys = new ArrayList<Integer>();		try {			File inputXml = new File(fileName);			SAXReader saxReader = new SAXReader();			Document document = saxReader.read(inputXml);			Element employees = document.getRootElement();			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {				Element e = i.next();				try {					put(e, map);				} catch (RuntimeException e1) {					List<Attribute> all = e.attributes();					StringBuilder sb = new StringBuilder();					for (Attribute o : all) {						sb.append("[" + o.getStringValue() + "]");					}					System.err.println("Error:" + fileName + "......" + sb);					throw e1;				}			}		} catch (Exception e) {			e.printStackTrace();		}		keys.addAll(map.keySet());		Collections.sort(keys);																FighterTempletConfig.map = map;		FighterTempletConfig.keys = keys;																List<FighterTemplet> all = new ArrayList<FighterTemplet>();		for(Integer c : keys) {			all.add(get(c));		}		FighterTempletConfig.all = all;	}	private static void put(Element e, Map<Integer, FighterTemplet> map) {		FighterTemplet x = new FighterTemplet();		x.setId( Integer.decode( e.attributeValue("id").trim() ) );		x.setType( Integer.decode( e.attributeValue("type").trim() ) );		x.setName( e.attributeValue("name") );		x.setNameDep( e.attributeValue("nameDep") );		x.setToolUseType( Integer.decode( e.attributeValue("toolUseType").trim() ) );		x.setAccordUse( Integer.decode( e.attributeValue("accordUse").trim() ) );		x.setBackpack( Integer.decode( e.attributeValue("backpack").trim() ) );		x.setRetain( Integer.decode( e.attributeValue("retain").trim() ) );		x.setFormat( e.attributeValue("format") );		x.setResid( Integer.decode( e.attributeValue("resid").trim() ) );		x.setPrestrain( Integer.decode( e.attributeValue("prestrain").trim() ) );		x.setVocation( Integer.decode( e.attributeValue("vocation").trim() ) );		x.setProfession( e.attributeValue("profession") );		x.setEndName( e.attributeValue("endName") );		x.setProfessionId( Integer.decode( e.attributeValue("professionId").trim() ) );		x.setAttackType( Integer.decode( e.attributeValue("attackType").trim() ) );		x.setCategory( Integer.decode( e.attributeValue("category").trim() ) );		x.setGodType( e.attributeValue("godType") );		x.setGodTypeWeight( Integer.decode( e.attributeValue("godTypeWeight").trim() ) );		x.setSex( Integer.decode( e.attributeValue("sex").trim() ) );		x.setLevel( Integer.decode( e.attributeValue("level").trim() ) );		x.setExpPar( Float.parseFloat( e.attributeValue("expPar").trim() ) );		x.setSpot( Integer.decode( e.attributeValue("spot").trim() ) );		x.setKiosk( Integer.decode( e.attributeValue("kiosk").trim() ) );		x.setEctype( Integer.decode( e.attributeValue("ectype").trim() ) );		x.setQuality( Integer.decode( e.attributeValue("quality").trim() ) );		x.setStep( Integer.decode( e.attributeValue("step").trim() ) );		x.setStepAgo( Integer.decode( e.attributeValue("stepAgo").trim() ) );		x.setAttribute( Integer.decode( e.attributeValue("attribute").trim() ) );		x.setSuffId( Integer.decode( e.attributeValue("suffId").trim() ) );		x.setEffectOdds( Float.parseFloat( e.attributeValue("effectOdds").trim() ) );		x.setHp( Integer.decode( e.attributeValue("hp").trim() ) );		x.setAttack( Integer.decode( e.attributeValue("attack").trim() ) );		x.setMAttack( Integer.decode( e.attributeValue("mAttack").trim() ) );		x.setDefend( Integer.decode( e.attributeValue("defend").trim() ) );		x.setMDefend( Integer.decode( e.attributeValue("mDefend").trim() ) );		x.setSpeed( Integer.decode( e.attributeValue("speed").trim() ) );		x.setCrit( Integer.decode( e.attributeValue("crit").trim() ) );		x.setDodge( Integer.decode( e.attributeValue("dodge").trim() ) );		x.setBlock( Integer.decode( e.attributeValue("block").trim() ) );		x.setRCrit( Integer.decode( e.attributeValue("rCrit").trim() ) );		x.setHit( Integer.decode( e.attributeValue("hit").trim() ) );		x.setRBlock( Integer.decode( e.attributeValue("rBlock").trim() ) );		x.setCritAddition( Integer.decode( e.attributeValue("critAddition").trim() ) );		x.setMagic( Integer.decode( e.attributeValue("magic").trim() ) );		x.setSocial( Integer.decode( e.attributeValue("social").trim() ) );		x.setSocialGrow( Float.parseFloat( e.attributeValue("socialGrow").trim() ) );		x.setSocialSX( Integer.decode( e.attributeValue("socialSX").trim() ) );		x.setCommonSkill( Integer.decode( e.attributeValue("commonSkill").trim() ) );		x.setSkill( Integer.decode( e.attributeValue("skill").trim() ) );		x.setTalentVocal( e.attributeValue("TalentVocal") );		x.setRandomVocal( e.attributeValue("RandomVocal") );		x.setCooperationVocal( Integer.decode( e.attributeValue("CooperationVocal").trim() ) );		x.setPictype( Integer.decode( e.attributeValue("pictype").trim() ) );		x.setExclusive1( Integer.decode( e.attributeValue("exclusive1").trim() ) );		x.setExclusive2( Integer.decode( e.attributeValue("exclusive2").trim() ) );		x.setExclusive3( Integer.decode( e.attributeValue("exclusive3").trim() ) );		x.setExclusive4( Integer.decode( e.attributeValue("exclusive4").trim() ) );		x.setExclusive5( Integer.decode( e.attributeValue("exclusive5").trim() ) );		x.setExclusive6( Integer.decode( e.attributeValue("exclusive6").trim() ) );		x.setExclusive7( Integer.decode( e.attributeValue("exclusive7").trim() ) );		x.setExclusive8( Integer.decode( e.attributeValue("exclusive8").trim() ) );		x.setExclusive9( Integer.decode( e.attributeValue("exclusive9").trim() ) );		x.setExclusive10( Integer.decode( e.attributeValue("exclusive10").trim() ) );		x.setExclusive11( Integer.decode( e.attributeValue("exclusive11").trim() ) );		x.setExclusive12( Integer.decode( e.attributeValue("exclusive12").trim() ) );		x.setSoul( Float.parseFloat( e.attributeValue("soul").trim() ) );		x.setSoulExp( Float.parseFloat( e.attributeValue("soulExp").trim() ) );		x.setGodExp( Float.parseFloat( e.attributeValue("godExp").trim() ) );		x.setUrl( e.attributeValue("url") );		x.setPurpose( e.attributeValue("purpose") );		x.setDescription( e.attributeValue("description") );		FighterTemplet remove = map.put(x.getId(), x);		if(remove != null) {			throw new RuntimeException("唯一标识重复了! 不可以有重复的唯一标识");		}	}	public static FighterTemplet get(Integer x) {		synchronized (map) {			return map.get(x);		}	}	/**	 * 最小键值	 */	public static Integer getMaxKey() {		return keys.get(keys.size() - 1);	}	/**	 * 最大键值	 */	public static Integer getMinKey() {		return keys.get(0);	}	/**	 * 键值最大的模板	 */	public static FighterTemplet getMax() {		return get(getMaxKey());	}	/**	 * 键值最小的模板	 */	public static FighterTemplet getMin() {		return get(getMinKey());	}	public static List<FighterTemplet> findById(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByType(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByName(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByNameDep(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getNameDep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByToolUseType(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getToolUseType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByAccordUse(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getAccordUse(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByBackpack(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getBackpack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByRetain(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getRetain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByFormat(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByResid(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByPrestrain(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByVocation(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getVocation(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByProfession(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getProfession(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByEndName(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getEndName(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByProfessionId(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getProfessionId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByAttackType(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getAttackType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByCategory(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByGodType(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getGodType(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByGodTypeWeight(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getGodTypeWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySex(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSex(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByLevel(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExpPar(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExpPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySpot(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSpot(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByKiosk(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getKiosk(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByEctype(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getEctype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByQuality(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByStep(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByStepAgo(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getStepAgo(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByAttribute(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getAttribute(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySuffId(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSuffId(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByEffectOdds(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getEffectOdds(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByHp(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByAttack(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByMAttack(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByDefend(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByMDefend(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySpeed(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByCrit(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByDodge(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByBlock(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByRCrit(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByHit(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByRBlock(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByCritAddition(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByMagic(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySocial(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySocialGrow(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSocialGrow(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySocialSX(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSocialSX(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByCommonSkill(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getCommonSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySkill(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByTalentVocal(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getTalentVocal(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByRandomVocal(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getRandomVocal(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByCooperationVocal(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getCooperationVocal(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByPictype(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive1(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive1(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive2(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive2(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive3(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive3(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive4(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive4(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive5(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive5(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive6(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive6(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive7(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive7(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive8(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive8(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive9(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive9(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive10(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive10(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive11(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive11(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByExclusive12(int value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getExclusive12(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySoul(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSoul(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findBySoulExp(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getSoulExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByGodExp(float value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getGodExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByUrl(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByPurpose(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getPurpose(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static List<FighterTemplet> findByDescription(String value) {
		List<FighterTemplet> all = new ArrayList<FighterTemplet>();
		for (FighterTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}
	public static int[] getArrayById() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}
	public static int[] getArrayByType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}
	public static String[] getArrayByName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}
	public static String[] getArrayByNameDep() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getNameDep();
		}
		return all;
	}
	public static int[] getArrayByToolUseType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getToolUseType();
		}
		return all;
	}
	public static int[] getArrayByAccordUse() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getAccordUse();
		}
		return all;
	}
	public static int[] getArrayByBackpack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getBackpack();
		}
		return all;
	}
	public static int[] getArrayByRetain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getRetain();
		}
		return all;
	}
	public static String[] getArrayByFormat() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}
	public static int[] getArrayByResid() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}
	public static int[] getArrayByPrestrain() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}
	public static int[] getArrayByVocation() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getVocation();
		}
		return all;
	}
	public static String[] getArrayByProfession() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getProfession();
		}
		return all;
	}
	public static String[] getArrayByEndName() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getEndName();
		}
		return all;
	}
	public static int[] getArrayByProfessionId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getProfessionId();
		}
		return all;
	}
	public static int[] getArrayByAttackType() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getAttackType();
		}
		return all;
	}
	public static int[] getArrayByCategory() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}
	public static String[] getArrayByGodType() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getGodType();
		}
		return all;
	}
	public static int[] getArrayByGodTypeWeight() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getGodTypeWeight();
		}
		return all;
	}
	public static int[] getArrayBySex() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSex();
		}
		return all;
	}
	public static int[] getArrayByLevel() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}
	public static float[] getArrayByExpPar() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExpPar();
		}
		return all;
	}
	public static int[] getArrayBySpot() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSpot();
		}
		return all;
	}
	public static int[] getArrayByKiosk() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getKiosk();
		}
		return all;
	}
	public static int[] getArrayByEctype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getEctype();
		}
		return all;
	}
	public static int[] getArrayByQuality() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}
	public static int[] getArrayByStep() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}
	public static int[] getArrayByStepAgo() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getStepAgo();
		}
		return all;
	}
	public static int[] getArrayByAttribute() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getAttribute();
		}
		return all;
	}
	public static int[] getArrayBySuffId() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSuffId();
		}
		return all;
	}
	public static float[] getArrayByEffectOdds() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getEffectOdds();
		}
		return all;
	}
	public static int[] getArrayByHp() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}
	public static int[] getArrayByAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}
	public static int[] getArrayByMAttack() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}
	public static int[] getArrayByDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}
	public static int[] getArrayByMDefend() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}
	public static int[] getArrayBySpeed() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}
	public static int[] getArrayByCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}
	public static int[] getArrayByDodge() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}
	public static int[] getArrayByBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}
	public static int[] getArrayByRCrit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}
	public static int[] getArrayByHit() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}
	public static int[] getArrayByRBlock() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}
	public static int[] getArrayByCritAddition() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}
	public static int[] getArrayByMagic() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}
	public static int[] getArrayBySocial() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}
	public static float[] getArrayBySocialGrow() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSocialGrow();
		}
		return all;
	}
	public static int[] getArrayBySocialSX() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSocialSX();
		}
		return all;
	}
	public static int[] getArrayByCommonSkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getCommonSkill();
		}
		return all;
	}
	public static int[] getArrayBySkill() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSkill();
		}
		return all;
	}
	public static String[] getArrayByTalentVocal() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getTalentVocal();
		}
		return all;
	}
	public static String[] getArrayByRandomVocal() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getRandomVocal();
		}
		return all;
	}
	public static int[] getArrayByCooperationVocal() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getCooperationVocal();
		}
		return all;
	}
	public static int[] getArrayByPictype() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}
	public static int[] getArrayByExclusive1() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive1();
		}
		return all;
	}
	public static int[] getArrayByExclusive2() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive2();
		}
		return all;
	}
	public static int[] getArrayByExclusive3() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive3();
		}
		return all;
	}
	public static int[] getArrayByExclusive4() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive4();
		}
		return all;
	}
	public static int[] getArrayByExclusive5() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive5();
		}
		return all;
	}
	public static int[] getArrayByExclusive6() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive6();
		}
		return all;
	}
	public static int[] getArrayByExclusive7() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive7();
		}
		return all;
	}
	public static int[] getArrayByExclusive8() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive8();
		}
		return all;
	}
	public static int[] getArrayByExclusive9() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive9();
		}
		return all;
	}
	public static int[] getArrayByExclusive10() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive10();
		}
		return all;
	}
	public static int[] getArrayByExclusive11() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive11();
		}
		return all;
	}
	public static int[] getArrayByExclusive12() {
		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getExclusive12();
		}
		return all;
	}
	public static float[] getArrayBySoul() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSoul();
		}
		return all;
	}
	public static float[] getArrayBySoulExp() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getSoulExp();
		}
		return all;
	}
	public static float[] getArrayByGodExp() {
		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getGodExp();
		}
		return all;
	}
	public static String[] getArrayByUrl() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}
	public static String[] getArrayByPurpose() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getPurpose();
		}
		return all;
	}
	public static String[] getArrayByDescription() {
		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}
	public static List<Integer> getListById() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}
	public static List<Integer> getListByType() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}
	public static List<String> getListByName() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}
	public static List<String> getListByNameDep() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getNameDep());
		}
		return all;
	}
	public static List<Integer> getListByToolUseType() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getToolUseType());
		}
		return all;
	}
	public static List<Integer> getListByAccordUse() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getAccordUse());
		}
		return all;
	}
	public static List<Integer> getListByBackpack() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getBackpack());
		}
		return all;
	}
	public static List<Integer> getListByRetain() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getRetain());
		}
		return all;
	}
	public static List<String> getListByFormat() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
	public static List<Integer> getListByResid() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}
	public static List<Integer> getListByPrestrain() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}
	public static List<Integer> getListByVocation() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getVocation());
		}
		return all;
	}
	public static List<String> getListByProfession() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getProfession());
		}
		return all;
	}
	public static List<String> getListByEndName() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getEndName());
		}
		return all;
	}
	public static List<Integer> getListByProfessionId() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getProfessionId());
		}
		return all;
	}
	public static List<Integer> getListByAttackType() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getAttackType());
		}
		return all;
	}
	public static List<Integer> getListByCategory() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}
	public static List<String> getListByGodType() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getGodType());
		}
		return all;
	}
	public static List<Integer> getListByGodTypeWeight() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getGodTypeWeight());
		}
		return all;
	}
	public static List<Integer> getListBySex() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSex());
		}
		return all;
	}
	public static List<Integer> getListByLevel() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
	public static List<Float> getListByExpPar() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExpPar());
		}
		return all;
	}
	public static List<Integer> getListBySpot() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSpot());
		}
		return all;
	}
	public static List<Integer> getListByKiosk() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getKiosk());
		}
		return all;
	}
	public static List<Integer> getListByEctype() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getEctype());
		}
		return all;
	}
	public static List<Integer> getListByQuality() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}
	public static List<Integer> getListByStep() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}
	public static List<Integer> getListByStepAgo() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getStepAgo());
		}
		return all;
	}
	public static List<Integer> getListByAttribute() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getAttribute());
		}
		return all;
	}
	public static List<Integer> getListBySuffId() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSuffId());
		}
		return all;
	}
	public static List<Float> getListByEffectOdds() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getEffectOdds());
		}
		return all;
	}
	public static List<Integer> getListByHp() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}
	public static List<Integer> getListByAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}
	public static List<Integer> getListByMAttack() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}
	public static List<Integer> getListByDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}
	public static List<Integer> getListByMDefend() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}
	public static List<Integer> getListBySpeed() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}
	public static List<Integer> getListByCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}
	public static List<Integer> getListByDodge() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}
	public static List<Integer> getListByBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}
	public static List<Integer> getListByRCrit() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}
	public static List<Integer> getListByHit() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}
	public static List<Integer> getListByRBlock() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}
	public static List<Integer> getListByCritAddition() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}
	public static List<Integer> getListByMagic() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
	public static List<Integer> getListBySocial() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
	public static List<Float> getListBySocialGrow() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSocialGrow());
		}
		return all;
	}
	public static List<Integer> getListBySocialSX() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSocialSX());
		}
		return all;
	}
	public static List<Integer> getListByCommonSkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getCommonSkill());
		}
		return all;
	}
	public static List<Integer> getListBySkill() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSkill());
		}
		return all;
	}
	public static List<String> getListByTalentVocal() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getTalentVocal());
		}
		return all;
	}
	public static List<String> getListByRandomVocal() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getRandomVocal());
		}
		return all;
	}
	public static List<Integer> getListByCooperationVocal() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getCooperationVocal());
		}
		return all;
	}
	public static List<Integer> getListByPictype() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
	public static List<Integer> getListByExclusive1() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive1());
		}
		return all;
	}
	public static List<Integer> getListByExclusive2() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive2());
		}
		return all;
	}
	public static List<Integer> getListByExclusive3() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive3());
		}
		return all;
	}
	public static List<Integer> getListByExclusive4() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive4());
		}
		return all;
	}
	public static List<Integer> getListByExclusive5() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive5());
		}
		return all;
	}
	public static List<Integer> getListByExclusive6() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive6());
		}
		return all;
	}
	public static List<Integer> getListByExclusive7() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive7());
		}
		return all;
	}
	public static List<Integer> getListByExclusive8() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive8());
		}
		return all;
	}
	public static List<Integer> getListByExclusive9() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive9());
		}
		return all;
	}
	public static List<Integer> getListByExclusive10() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive10());
		}
		return all;
	}
	public static List<Integer> getListByExclusive11() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive11());
		}
		return all;
	}
	public static List<Integer> getListByExclusive12() {
		List<Integer> all = new ArrayList<Integer>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getExclusive12());
		}
		return all;
	}
	public static List<Float> getListBySoul() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSoul());
		}
		return all;
	}
	public static List<Float> getListBySoulExp() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getSoulExp());
		}
		return all;
	}
	public static List<Float> getListByGodExp() {
		List<Float> all = new ArrayList<Float>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getGodExp());
		}
		return all;
	}
	public static List<String> getListByUrl() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
	public static List<String> getListByPurpose() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getPurpose());
		}
		return all;
	}
	public static List<String> getListByDescription() {
		List<String> all = new ArrayList<String>();
		for (FighterTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
	private static boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}