//[战斗][技能]package cn.mxz;public class SkillTemplet implements ISkillTemplet,Chipable {	/** 	 * 技能ID 	 */	private int id;	/** 	 * 技能名稱 	 */	private String name;	/** 	 * 怪物名稱（內部查看） 	 */	private String nameInterior;	/** 	 * 後端名稱 	 */	private String endName;	/** 	 * 技能品階 	 */	private int stage;	/** 	 * 前端品階（道具類的統一欄位，等價這裡的品質） 	 */	private int stepAgo;	/** 	 * 技能觸發幾率（-1表示另外的觸發方式） 	 */	private float skillPro;	/** 	 * 下回合增加觸發幾率 	 */	private float addPro;	/** 	 * 攻擊類型：1(扣血)單體攻擊；2(扣血)小貫穿（攻擊直線2人）；3(扣血)大貫穿（攻擊直線3人）；4(扣血)橫排；5(扣血)濺射（相鄰目標衰減50%）；6(扣血)全體；7(扣血)隨機攻擊一個單體目標；8(扣血)隨機攻擊兩個目標；9(扣血)隨機攻擊三個目標21(加血)自己；22(加血)氣血最少；23(加血)全體；24(加血)對己方隨機1個損血目標；25(加血)對己方隨機2個損血目標；31（不扣血）自己；32（不扣血）氣血最少；33（不扣血）前排目標；34（不扣血）中排目標；35（不扣血）後排目標 	 */	private int attackType;	/** 	 * 攻擊類型名稱 	 */	private String atkTypeName;	/** 	 * 是否是普通攻擊（0否 1是） 	 */	private int commonAttack;	/** 	 * 前段顯示數數值型別（1固定值 2加號百分數 3加號固定值）整數 	 */	private int valueType;	/** 	 * 物理攻擊比例 	 */	private float normalRatio;	/** 	 * 法術攻擊比例 	 */	private float magicRatio;	/** 	 * 速度所占比例 	 */	private float speedRatio;	/** 	 * 計算傷害時，目標位置是否增加額外普通攻擊傷害（0否 1是） 	 */	private int bitBonus;	/** 	 * 技能傷害類型（1物理 2法術 3無技能傷害效果） 	 */	private int damageType;	/** 	 * 被動技能效果類型（-1非被動技能沒有屬性加成 0氣血 1物攻 2法攻 3物防 4法防 5速度 6暴擊 7閃避 8格擋 9抗暴 10 命中 11破格 12會心） 	 */	private int pasvFront;	/** 	 * 被動技能數值（百分比） 	 */	private float frontPar;	/** 	 * 被動技能數值（固定值） 	 */	private float frontFixed;	/** 	 * 被動技能成長（百分比） 	 */	private float frontGrowPar;	/** 	 * 被動技能成長（固定值） 	 */	private float frontGrowFixed;	/** 	 * 身價 	 */	private int social;	/** 	 * 身價成長 	 */	private float socialGrow;	/** 	 * 聯攜技能啟動id 	 */	private String exclusiveId;	/** 	 * 聯攜技能啟動對應名稱名稱 	 */	private String exclusiveName;	/** 	 * 技能描述（上限27字） 	 */	private String content;	/** 	 * 技能描述（上限27字） 	 */	private String description;	/** 	 * 簡要描述（上限10字） 	 */	private String sketch;	/** 	 * 所占法力值 	 */	private int migc;	/** 	 * 特效效果（1單體 2全體） 	 */	private int tx;	/** 	 * 是否第一回合觸發(0否 1是) 	 */	private int isTragerFirstRound;	/** 	 * 技能釋放類型（1主動傷害 2輔助buff3被動） 	 */	private int releaseType;	/** 	 * 主動技能傷害值公式係數 	 */	private float harmPar;	/** 	 * 固定值傷害（與防禦無關） 	 */	private int harmFixed;	/** 	 * 主動技能傷害成長係數 	 */	private float harmGrowPar;	/** 	 * 主動技能傷害成長固定值（與防禦無關） 	 */	private float harmGrowFixed;	/** 	 * 技能釋放位置（0原地左右，1原地放大,2瞬移，3直接飛） 	 */	private int locale;	/** 	 * F1 物理技能發招
F2 法術技能發招 	 */	private int voiceSA;	/** 	 * 飛行聲音 	 */	private int voiceSF;	/** 	 * 
S1 物理技能受招
S2 火系技能受招
S3 土系技能受招
S4 水系技能受招
S5 雷法技能受招
S6 妖法技能受招
S7 異術技能受招
S8 天法技能受招
S9 BUFF
S10鬼手技能受招
S11 普通攻擊火法
S12 普通攻擊土法
S13 普通攻擊水法
S14 普通攻擊雷法
S15 普通攻擊妖術
S16 普通攻擊天法
S17 普通攻擊鬼手
S18 刀劍技能受招
S19 大錘技能受招
S20 刺技能受招
S21 箭技能受招
S22 爪技能受招
S101 將人
S102 綠茵行者
S103 苦行浪人
S104 魅影刺客
S105 符篆師
S106 宣鼎丹士
S0 普通受招 	 */	private int voiceSD;	/** 	 * 發招特效（沒有填-1） 	 */	private String SA;	/** 	 * 發招飛行彈道 	 */	private String SF;	/** 	 * 受招特效（33和15因錯臨時改為18） 	 */	private String SD;	/** 	 * 傷血特效（傷血特效和冒血數位一起） 	 */	private String SX;	/** 	 * 受招動作（1放大） 	 */	private int hitmove;	/** 	 * 發招與飛行特效是否同時顯示（不同時則是特效播放完之後放下一個,0否，1是） 	 */	private int attackfly;	/** 	 * 發招與受招特效是否一致播放（0否，1是） 	 */	private int attackhit;	/** 	 * 發招次數 	 */	private int attacktimes;	/** 	 * 受招次數 	 */	private int hittimes;	/** 	 * 傷血次數 	 */	private int bloodtimes;	/** 	 * 是否有特效特效（1有0否） 	 */	private int specil;	/** 	 * 受招在敵方是否鏡像（1是，0否） 	 */	private int path;	/** 	 * 技能名字資源 	 */	private int skillword;	/** 	 * 資源格式 	 */	private String format;	/** 	 * 圖示id 	 */	private String SP;	/** 	 * URL 	 */	private String url;	/** 	 * 對應天命神將 	 */	private String spotName;	/** 	 * 合成所需碎片 	 */	private String chip;	/** 	 * 道具類型(1-絕技，2仙術，3禦法，0心法 4神獸 5聯攜) 	 */	private int toolUseType;	/** 	 * 是否可以主動使用（1可以主動使用 0不主動使用） 	 */	private int accordUse;	/** 	 * 是否放入背包（0不放 1放入 ） 	 */	private int backpack;	/** 	 * 資源ID(用於讀取圖片位址) 	 */	private int resid;	/** 	 * 是否是初始載入頭像資源（1為是；0為否） 	 */	private int prestrain;	/** 	 * 物品用途 	 */	private String purpose;	/** 	 * 是否保留（0不保留 1保留） 	 */	private int retain;	/**	 * 技能ID	 */	void setId(int id) {		this.id = id;	}	/**	 * 技能ID	 */	public int getId() {		return this.id;	}	/**	 * 技能名稱	 */	void setName(String name) {		this.name = name;	}	/**	 * 技能名稱	 */	public String getName() {		return this.name;	}	/**	 * 怪物名稱（內部查看）	 */	void setNameInterior(String nameInterior) {		this.nameInterior = nameInterior;	}	/**	 * 怪物名稱（內部查看）	 */	public String getNameInterior() {		return this.nameInterior;	}	/**	 * 後端名稱	 */	void setEndName(String endName) {		this.endName = endName;	}	/**	 * 後端名稱	 */	public String getEndName() {		return this.endName;	}	/**	 * 技能品階	 */	void setStage(int stage) {		this.stage = stage;	}	/**	 * 技能品階	 */	public int getStage() {		return this.stage;	}	/**	 * 前端品階（道具類的統一欄位，等價這裡的品質）	 */	void setStepAgo(int stepAgo) {		this.stepAgo = stepAgo;	}	/**	 * 前端品階（道具類的統一欄位，等價這裡的品質）	 */	public int getStepAgo() {		return this.stepAgo;	}	/**	 * 技能觸發幾率（-1表示另外的觸發方式）	 */	void setSkillPro(float skillPro) {		this.skillPro = skillPro;	}	/**	 * 技能觸發幾率（-1表示另外的觸發方式）	 */	public float getSkillPro() {		return this.skillPro;	}	/**	 * 下回合增加觸發幾率	 */	void setAddPro(float addPro) {		this.addPro = addPro;	}	/**	 * 下回合增加觸發幾率	 */	public float getAddPro() {		return this.addPro;	}	/**	 * 攻擊類型：1(扣血)單體攻擊；2(扣血)小貫穿（攻擊直線2人）；3(扣血)大貫穿（攻擊直線3人）；4(扣血)橫排；5(扣血)濺射（相鄰目標衰減50%）；6(扣血)全體；7(扣血)隨機攻擊一個單體目標；8(扣血)隨機攻擊兩個目標；9(扣血)隨機攻擊三個目標21(加血)自己；22(加血)氣血最少；23(加血)全體；24(加血)對己方隨機1個損血目標；25(加血)對己方隨機2個損血目標；31（不扣血）自己；32（不扣血）氣血最少；33（不扣血）前排目標；34（不扣血）中排目標；35（不扣血）後排目標	 */	void setAttackType(int attackType) {		this.attackType = attackType;	}	/**	 * 攻擊類型：1(扣血)單體攻擊；2(扣血)小貫穿（攻擊直線2人）；3(扣血)大貫穿（攻擊直線3人）；4(扣血)橫排；5(扣血)濺射（相鄰目標衰減50%）；6(扣血)全體；7(扣血)隨機攻擊一個單體目標；8(扣血)隨機攻擊兩個目標；9(扣血)隨機攻擊三個目標21(加血)自己；22(加血)氣血最少；23(加血)全體；24(加血)對己方隨機1個損血目標；25(加血)對己方隨機2個損血目標；31（不扣血）自己；32（不扣血）氣血最少；33（不扣血）前排目標；34（不扣血）中排目標；35（不扣血）後排目標	 */	public int getAttackType() {		return this.attackType;	}	/**	 * 攻擊類型名稱	 */	void setAtkTypeName(String atkTypeName) {		this.atkTypeName = atkTypeName;	}	/**	 * 攻擊類型名稱	 */	public String getAtkTypeName() {		return this.atkTypeName;	}	/**	 * 是否是普通攻擊（0否 1是）	 */	void setCommonAttack(int commonAttack) {		this.commonAttack = commonAttack;	}	/**	 * 是否是普通攻擊（0否 1是）	 */	public int getCommonAttack() {		return this.commonAttack;	}	/**	 * 前段顯示數數值型別（1固定值 2加號百分數 3加號固定值）整數	 */	void setValueType(int valueType) {		this.valueType = valueType;	}	/**	 * 前段顯示數數值型別（1固定值 2加號百分數 3加號固定值）整數	 */	public int getValueType() {		return this.valueType;	}	/**	 * 物理攻擊比例	 */	void setNormalRatio(float normalRatio) {		this.normalRatio = normalRatio;	}	/**	 * 物理攻擊比例	 */	public float getNormalRatio() {		return this.normalRatio;	}	/**	 * 法術攻擊比例	 */	void setMagicRatio(float magicRatio) {		this.magicRatio = magicRatio;	}	/**	 * 法術攻擊比例	 */	public float getMagicRatio() {		return this.magicRatio;	}	/**	 * 速度所占比例	 */	void setSpeedRatio(float speedRatio) {		this.speedRatio = speedRatio;	}	/**	 * 速度所占比例	 */	public float getSpeedRatio() {		return this.speedRatio;	}	/**	 * 計算傷害時，目標位置是否增加額外普通攻擊傷害（0否 1是）	 */	void setBitBonus(int bitBonus) {		this.bitBonus = bitBonus;	}	/**	 * 計算傷害時，目標位置是否增加額外普通攻擊傷害（0否 1是）	 */	public int getBitBonus() {		return this.bitBonus;	}	/**	 * 技能傷害類型（1物理 2法術 3無技能傷害效果）	 */	void setDamageType(int damageType) {		this.damageType = damageType;	}	/**	 * 技能傷害類型（1物理 2法術 3無技能傷害效果）	 */	public int getDamageType() {		return this.damageType;	}	/**	 * 被動技能效果類型（-1非被動技能沒有屬性加成 0氣血 1物攻 2法攻 3物防 4法防 5速度 6暴擊 7閃避 8格擋 9抗暴 10 命中 11破格 12會心）	 */	void setPasvFront(int pasvFront) {		this.pasvFront = pasvFront;	}	/**	 * 被動技能效果類型（-1非被動技能沒有屬性加成 0氣血 1物攻 2法攻 3物防 4法防 5速度 6暴擊 7閃避 8格擋 9抗暴 10 命中 11破格 12會心）	 */	public int getPasvFront() {		return this.pasvFront;	}	/**	 * 被動技能數值（百分比）	 */	void setFrontPar(float frontPar) {		this.frontPar = frontPar;	}	/**	 * 被動技能數值（百分比）	 */	public float getFrontPar() {		return this.frontPar;	}	/**	 * 被動技能數值（固定值）	 */	void setFrontFixed(float frontFixed) {		this.frontFixed = frontFixed;	}	/**	 * 被動技能數值（固定值）	 */	public float getFrontFixed() {		return this.frontFixed;	}	/**	 * 被動技能成長（百分比）	 */	void setFrontGrowPar(float frontGrowPar) {		this.frontGrowPar = frontGrowPar;	}	/**	 * 被動技能成長（百分比）	 */	public float getFrontGrowPar() {		return this.frontGrowPar;	}	/**	 * 被動技能成長（固定值）	 */	void setFrontGrowFixed(float frontGrowFixed) {		this.frontGrowFixed = frontGrowFixed;	}	/**	 * 被動技能成長（固定值）	 */	public float getFrontGrowFixed() {		return this.frontGrowFixed;	}	/**	 * 身價	 */	void setSocial(int social) {		this.social = social;	}	/**	 * 身價	 */	public int getSocial() {		return this.social;	}	/**	 * 身價成長	 */	void setSocialGrow(float socialGrow) {		this.socialGrow = socialGrow;	}	/**	 * 身價成長	 */	public float getSocialGrow() {		return this.socialGrow;	}	/**	 * 聯攜技能啟動id	 */	void setExclusiveId(String exclusiveId) {		this.exclusiveId = exclusiveId;	}	/**	 * 聯攜技能啟動id	 */	public String getExclusiveId() {		return this.exclusiveId;	}	/**	 * 聯攜技能啟動對應名稱名稱	 */	void setExclusiveName(String exclusiveName) {		this.exclusiveName = exclusiveName;	}	/**	 * 聯攜技能啟動對應名稱名稱	 */	public String getExclusiveName() {		return this.exclusiveName;	}	/**	 * 技能描述（上限27字）	 */	void setContent(String content) {		this.content = content;	}	/**	 * 技能描述（上限27字）	 */	public String getContent() {		return this.content;	}	/**	 * 技能描述（上限27字）	 */	void setDescription(String description) {		this.description = description;	}	/**	 * 技能描述（上限27字）	 */	public String getDescription() {		return this.description;	}	/**	 * 簡要描述（上限10字）	 */	void setSketch(String sketch) {		this.sketch = sketch;	}	/**	 * 簡要描述（上限10字）	 */	public String getSketch() {		return this.sketch;	}	/**	 * 所占法力值	 */	void setMigc(int migc) {		this.migc = migc;	}	/**	 * 所占法力值	 */	public int getMigc() {		return this.migc;	}	/**	 * 特效效果（1單體 2全體）	 */	void setTx(int tx) {		this.tx = tx;	}	/**	 * 特效效果（1單體 2全體）	 */	public int getTx() {		return this.tx;	}	/**	 * 是否第一回合觸發(0否 1是)	 */	void setIsTragerFirstRound(int isTragerFirstRound) {		this.isTragerFirstRound = isTragerFirstRound;	}	/**	 * 是否第一回合觸發(0否 1是)	 */	public int getIsTragerFirstRound() {		return this.isTragerFirstRound;	}	/**	 * 技能釋放類型（1主動傷害 2輔助buff3被動）	 */	void setReleaseType(int releaseType) {		this.releaseType = releaseType;	}	/**	 * 技能釋放類型（1主動傷害 2輔助buff3被動）	 */	public int getReleaseType() {		return this.releaseType;	}	/**	 * 主動技能傷害值公式係數	 */	void setHarmPar(float harmPar) {		this.harmPar = harmPar;	}	/**	 * 主動技能傷害值公式係數	 */	public float getHarmPar() {		return this.harmPar;	}	/**	 * 固定值傷害（與防禦無關）	 */	void setHarmFixed(int harmFixed) {		this.harmFixed = harmFixed;	}	/**	 * 固定值傷害（與防禦無關）	 */	public int getHarmFixed() {		return this.harmFixed;	}	/**	 * 主動技能傷害成長係數	 */	void setHarmGrowPar(float harmGrowPar) {		this.harmGrowPar = harmGrowPar;	}	/**	 * 主動技能傷害成長係數	 */	public float getHarmGrowPar() {		return this.harmGrowPar;	}	/**	 * 主動技能傷害成長固定值（與防禦無關）	 */	void setHarmGrowFixed(float harmGrowFixed) {		this.harmGrowFixed = harmGrowFixed;	}	/**	 * 主動技能傷害成長固定值（與防禦無關）	 */	public float getHarmGrowFixed() {		return this.harmGrowFixed;	}	/**	 * 技能釋放位置（0原地左右，1原地放大,2瞬移，3直接飛）	 */	void setLocale(int locale) {		this.locale = locale;	}	/**	 * 技能釋放位置（0原地左右，1原地放大,2瞬移，3直接飛）	 */	public int getLocale() {		return this.locale;	}	/**	 * F1 物理技能發招
F2 法術技能發招	 */	void setVoiceSA(int voiceSA) {		this.voiceSA = voiceSA;	}	/**	 * F1 物理技能發招
F2 法術技能發招	 */	public int getVoiceSA() {		return this.voiceSA;	}	/**	 * 飛行聲音	 */	void setVoiceSF(int voiceSF) {		this.voiceSF = voiceSF;	}	/**	 * 飛行聲音	 */	public int getVoiceSF() {		return this.voiceSF;	}	/**	 * 
S1 物理技能受招
S2 火系技能受招
S3 土系技能受招
S4 水系技能受招
S5 雷法技能受招
S6 妖法技能受招
S7 異術技能受招
S8 天法技能受招
S9 BUFF
S10鬼手技能受招
S11 普通攻擊火法
S12 普通攻擊土法
S13 普通攻擊水法
S14 普通攻擊雷法
S15 普通攻擊妖術
S16 普通攻擊天法
S17 普通攻擊鬼手
S18 刀劍技能受招
S19 大錘技能受招
S20 刺技能受招
S21 箭技能受招
S22 爪技能受招
S101 將人
S102 綠茵行者
S103 苦行浪人
S104 魅影刺客
S105 符篆師
S106 宣鼎丹士
S0 普通受招	 */	void setVoiceSD(int voiceSD) {		this.voiceSD = voiceSD;	}	/**	 * 
S1 物理技能受招
S2 火系技能受招
S3 土系技能受招
S4 水系技能受招
S5 雷法技能受招
S6 妖法技能受招
S7 異術技能受招
S8 天法技能受招
S9 BUFF
S10鬼手技能受招
S11 普通攻擊火法
S12 普通攻擊土法
S13 普通攻擊水法
S14 普通攻擊雷法
S15 普通攻擊妖術
S16 普通攻擊天法
S17 普通攻擊鬼手
S18 刀劍技能受招
S19 大錘技能受招
S20 刺技能受招
S21 箭技能受招
S22 爪技能受招
S101 將人
S102 綠茵行者
S103 苦行浪人
S104 魅影刺客
S105 符篆師
S106 宣鼎丹士
S0 普通受招	 */	public int getVoiceSD() {		return this.voiceSD;	}	/**	 * 發招特效（沒有填-1）	 */	void setSA(String SA) {		this.SA = SA;	}	/**	 * 發招特效（沒有填-1）	 */	public String getSA() {		return this.SA;	}	/**	 * 發招飛行彈道	 */	void setSF(String SF) {		this.SF = SF;	}	/**	 * 發招飛行彈道	 */	public String getSF() {		return this.SF;	}	/**	 * 受招特效（33和15因錯臨時改為18）	 */	void setSD(String SD) {		this.SD = SD;	}	/**	 * 受招特效（33和15因錯臨時改為18）	 */	public String getSD() {		return this.SD;	}	/**	 * 傷血特效（傷血特效和冒血數位一起）	 */	void setSX(String SX) {		this.SX = SX;	}	/**	 * 傷血特效（傷血特效和冒血數位一起）	 */	public String getSX() {		return this.SX;	}	/**	 * 受招動作（1放大）	 */	void setHitmove(int hitmove) {		this.hitmove = hitmove;	}	/**	 * 受招動作（1放大）	 */	public int getHitmove() {		return this.hitmove;	}	/**	 * 發招與飛行特效是否同時顯示（不同時則是特效播放完之後放下一個,0否，1是）	 */	void setAttackfly(int attackfly) {		this.attackfly = attackfly;	}	/**	 * 發招與飛行特效是否同時顯示（不同時則是特效播放完之後放下一個,0否，1是）	 */	public int getAttackfly() {		return this.attackfly;	}	/**	 * 發招與受招特效是否一致播放（0否，1是）	 */	void setAttackhit(int attackhit) {		this.attackhit = attackhit;	}	/**	 * 發招與受招特效是否一致播放（0否，1是）	 */	public int getAttackhit() {		return this.attackhit;	}	/**	 * 發招次數	 */	void setAttacktimes(int attacktimes) {		this.attacktimes = attacktimes;	}	/**	 * 發招次數	 */	public int getAttacktimes() {		return this.attacktimes;	}	/**	 * 受招次數	 */	void setHittimes(int hittimes) {		this.hittimes = hittimes;	}	/**	 * 受招次數	 */	public int getHittimes() {		return this.hittimes;	}	/**	 * 傷血次數	 */	void setBloodtimes(int bloodtimes) {		this.bloodtimes = bloodtimes;	}	/**	 * 傷血次數	 */	public int getBloodtimes() {		return this.bloodtimes;	}	/**	 * 是否有特效特效（1有0否）	 */	void setSpecil(int specil) {		this.specil = specil;	}	/**	 * 是否有特效特效（1有0否）	 */	public int getSpecil() {		return this.specil;	}	/**	 * 受招在敵方是否鏡像（1是，0否）	 */	void setPath(int path) {		this.path = path;	}	/**	 * 受招在敵方是否鏡像（1是，0否）	 */	public int getPath() {		return this.path;	}	/**	 * 技能名字資源	 */	void setSkillword(int skillword) {		this.skillword = skillword;	}	/**	 * 技能名字資源	 */	public int getSkillword() {		return this.skillword;	}	/**	 * 資源格式	 */	void setFormat(String format) {		this.format = format;	}	/**	 * 資源格式	 */	public String getFormat() {		return this.format;	}	/**	 * 圖示id	 */	void setSP(String SP) {		this.SP = SP;	}	/**	 * 圖示id	 */	public String getSP() {		return this.SP;	}	/**	 * URL	 */	void setUrl(String url) {		this.url = url;	}	/**	 * URL	 */	public String getUrl() {		return this.url;	}	/**	 * 對應天命神將	 */	void setSpotName(String spotName) {		this.spotName = spotName;	}	/**	 * 對應天命神將	 */	public String getSpotName() {		return this.spotName;	}	/**	 * 合成所需碎片	 */	void setChip(String chip) {		this.chip = chip;	}	/**	 * 合成所需碎片	 */	public String getChip() {		return this.chip;	}	/**	 * 道具類型(1-絕技，2仙術，3禦法，0心法 4神獸 5聯攜)	 */	void setToolUseType(int toolUseType) {		this.toolUseType = toolUseType;	}	/**	 * 道具類型(1-絕技，2仙術，3禦法，0心法 4神獸 5聯攜)	 */	public int getToolUseType() {		return this.toolUseType;	}	/**	 * 是否可以主動使用（1可以主動使用 0不主動使用）	 */	void setAccordUse(int accordUse) {		this.accordUse = accordUse;	}	/**	 * 是否可以主動使用（1可以主動使用 0不主動使用）	 */	public int getAccordUse() {		return this.accordUse;	}	/**	 * 是否放入背包（0不放 1放入 ）	 */	void setBackpack(int backpack) {		this.backpack = backpack;	}	/**	 * 是否放入背包（0不放 1放入 ）	 */	public int getBackpack() {		return this.backpack;	}	/**	 * 資源ID(用於讀取圖片位址)	 */	void setResid(int resid) {		this.resid = resid;	}	/**	 * 資源ID(用於讀取圖片位址)	 */	public int getResid() {		return this.resid;	}	/**	 * 是否是初始載入頭像資源（1為是；0為否）	 */	void setPrestrain(int prestrain) {		this.prestrain = prestrain;	}	/**	 * 是否是初始載入頭像資源（1為是；0為否）	 */	public int getPrestrain() {		return this.prestrain;	}	/**	 * 物品用途	 */	void setPurpose(String purpose) {		this.purpose = purpose;	}	/**	 * 物品用途	 */	public String getPurpose() {		return this.purpose;	}	/**	 * 是否保留（0不保留 1保留）	 */	void setRetain(int retain) {		this.retain = retain;	}	/**	 * 是否保留（0不保留 1保留）	 */	public int getRetain() {		return this.retain;	}}