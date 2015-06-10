package game.fighter;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import util.RandomUtil;
import config.dangrading.LevelPromoteRuleTemplet;
import config.dangrading.LevelPromoteRuleTempletCfg;
import config.equipment.EquPropertyType;
import config.fighter.HeroTemplet;
import config.grow.HeroGrowTempletCfg;
import define.DefaultCfg;
import define.SystemCfg;
import game.equipment.EquipmentBar;
import game.growup.Quality;
import game.pvp.DanGrad;
import game.skill.AccordSkill;
import game.skill.CaptainSkill;
import game.skill.CommonSkill;
import game.talent.TalentBase;
import game.talent.TalentType;
import game.util.heroGrowup.HeroGrowupFormula;

/**
 * 英雄卡牌，类似于玩家的伙伴，以前的PartnerBase类在适当的时候会被废除掉
 * @author DXF
 * 2013-5-28 下午5:05:04
 */
public class Hero extends FighterBase  {
	
	/** 英雄对应配置表 */
	private final HeroTemplet					templet;
	
	/** 英雄唯一id */
	private int									uID;
	
	/** 英雄等级 */
	private short 								level;
	
	/** 英雄当前经验 */
	private int 								exp;
	
	/** 英雄品质 */
	private  Quality							quality = null;
	
	// 装备栏
	private EquipmentBar						equBar	= null;
	
	
	/**
	 * 通常用于从数据库初始化一个英雄 必须附带唯一ID和等级
	 * @param heroTemplet
	 * @param uid
	 * @param level
	 * @param quality
	 * @param talents
	 */
	public Hero( HeroTemplet templet, int uid, short level, Quality quality ) {
		this.templet 	= templet;
		setLevel( level );
		setUID( uid );
		setQuality( quality );
		setEquBar(new EquipmentBar());
		
		if( templet == null )
			return;
		// 固定属性
		setId( templet.id );
		setIsMonster( false );
		setAwardContent( null );// 英雄没得掉落
		setProfessional( templet.professional );
		setAttackType( templet.attackType );
		setCommonAttack( new CommonSkill( templet.commonAttack ) );
		setSkillAttack( new AccordSkill( templet.skillAttack, (byte)1 ) );
		setCaptainSkill( new CaptainSkill( templet.captainSkill ) );
		setPassiveSkill( templet.passiveSkill );
		// 结算属性
		settlementPropertyToLevel();
		settlementPropertyToQuality();
		// 羁绊
		setFetters( templet.fetters );
		
		///////////////////////////////////
		if( SystemCfg.PLATFORM.equals( "LB" ) ){
			setHpMax( getHpMax() * 2 ) ;
			setHp( getHpMax() ) ;
		}
	}
	
	/**
	 * 拷贝构造函数
	 * @param heroTemplet
	 * @param level
	 * @param position
	*/
	public Hero( Hero fighter ) {
		super( fighter );
		this.level			= fighter.level;
		this.exp			= fighter.exp;
		this.quality		= new Quality( fighter.quality );
		this.templet 		= fighter.templet;
		this.talentHP		= fighter.talentHP;
		this.talentAttack	= fighter.talentAttack;
		this.uID			= fighter.uID;
		this.setEquBar(fighter.getEquBar());
	}
	
	/**
	 * 获取携带等级
	 * @return
	 */
	public short getCarryLevel() {
		return HeroGrowupFormula.getHeroCarryLv( quality );
	}
	
	public short getLevel() {
		return level;
	}
	public void setLevel(short level) {
		this.level = level;
	}
	
	public int getUID() {
		return uID;
	}
	public void setUID(int id) {
		this.uID = id;
	}
	
	public void setQuality( Quality quality ){
		this.quality = quality;
	}
	public Quality getQuality(){
		return this.quality;
	}
	
	/**
	 * 是否非英雄
	 * @return
	 */
	public boolean IsNonHero() {
		return templet.id / 10000 == 3;
	}
	
	
	/** 获得表格ID */
	public int getNid()
	{
		return templet.id;
	}
	
	/** 获得英雄当前经验 */
	public int getExp(){
		return exp;
	}
	public void initExp( int exp ){
		this.exp = exp;
	}
	/** 获取还剩多少经验满级 */
	public int getGptuExp() {
		return HeroGrowupFormula.gptuExp( this.level, this.exp, quality );
	}
	/**
	 * 给英雄添加经验
	 * @param exp
	 */
	public int addExp( int changeExp )
	{
		if( changeExp <= 0 || isFullLevel() ) return 0;
		
		int maxExp 	= HeroGrowTempletCfg.getTempletById( this.level ).getExp();
		
		this.exp	+= changeExp;
		
		int isUpLevel = 0;
		// 表示升级了 用循环 是可能会连续升几级
		while( this.exp >= maxExp && maxExp != 0 ){
			
			// 将多的减去
			this.exp -= maxExp;
			
			upLevel();
			++isUpLevel;
			
			// 这里表示满级了  当前经验直接清零
			if( isFullLevel() ) {
				this.exp = 0;
				break;
			}
			maxExp = HeroGrowTempletCfg.getTempletById( this.level ).getExp();
		}
		
		// 完了后 结算一下
		if( isUpLevel > 0 )
			settlementPropertyToLevel();
		
		return isUpLevel;
	}
	
	// 升级 
	private void upLevel() {
		++this.level;
	}
	
	/** 是否满级 */
	public boolean isFullLevel(){
		byte actual = HeroGrowupFormula.actualNumberFormula( quality );
		return level == HeroGrowupFormula.levelLimit[actual][1];
	}
	
	/** 是否可以进化 */
	public boolean isEvolution(){
		return (!quality.isMax() && isFullLevel());
	}

	/** 结算英雄属性  等级相关 */
	private void settlementPropertyToLevel() 
	{
		// 先获取 之前其他加成属性
		int attack 			= talentAttack + dangradAttack + equAttack;
		int hp				= talentHP + dangradHP + equHp;
		// 先获得攻击值
		int attackValue 	= HeroGrowupFormula.attackModulusFormula( this.level, this.quality );
		setAttack( (int)(attackValue * (templet.attackPercent*0.01)) + attack );
		// 获得HP值  HP=ATTACK*3
		int hpValue			= (int)(attackValue * 3 * (templet.hpPercent*0.01f));
		
		setHp( hpValue + hp );
		setHpMax( hpValue + hp );
	}
	
	/** 结算英雄属性  品质相关 */
	private void settlementPropertyToQuality() 
	{
		// 闪避
		float dodge			= HeroGrowupFormula.qualityToFormula( this.quality, templet.dodge );
		setDodge( dodge );
		// 普通攻击暴击
		float commonCrit	= HeroGrowupFormula.qualityToFormula( this.quality, templet.commonCrit );
		setCommonCrit( commonCrit + dangradCrit );
		// 技能攻击暴击
		float skillCrit		= HeroGrowupFormula.qualityToFormula( this.quality, templet.skillCrit );
		setSkillCrit( skillCrit + dangradCrit );
		// 是否 精英
		setIsElite( this.quality.isElite() );
	}
	
	private int talentHP 		= 0;  // 记录天赋赐予给他的生命
	private int talentAttack 	= 0;  // 记录天赋赐予给他的攻击力
	/** 结算天赋信息 */
	public void settlementPropertyToTalent( Map<TalentType,TalentBase> 	talents ) {
		
		if( talents.isEmpty() )
			return;
		
		// 算出 没加天赋时候的 血量和攻击
		int nHp 		= getHpMax() - talentHP;
		int nAttack 	= getAttack() - talentAttack;
		
		// 然后 得出现在天赋的数值
		talentHP		= (int)talents.get( TalentType.HP ).getValue();
		talentAttack	= (int)talents.get( getAttackType() == 0 ? TalentType.PHY_ATTACK : TalentType.MAGIC_ATTACK ).getValue();
		
		setHp( nHp + talentHP );
		setHpMax( nHp + talentHP );
		setAttack( nAttack + talentAttack );
	}

	private int dangradHP		= 0;
	private int dangradAttack	= 0;
	private float dangradCrit	= 0f;
	/** 结算段位信息 */
	public void settlementPropertyToDangrad( DanGrad danGrad ){
		
		if( danGrad == null ) return;
		
		LevelPromoteRuleTemplet l 	= LevelPromoteRuleTempletCfg.get( danGrad ) ;
		
		// 算出 没加段位时候的 血量和攻击和暴击
		int nHp 			= getHpMax() - dangradHP;
		int nAttack 		= getAttack() - dangradAttack;
		float nCommonCrit 	= getCommonCrit() - dangradCrit;
		float nSkillCrit 	= getSkillCrit() - dangradCrit;
		
		// 然后 得出现在段位的数值
		dangradHP			= l.getBoonHP();
		dangradAttack		= l.getBoonAttack();
		dangradCrit			= l.getBoonCrit() / 10000;
		
		setHp( nHp + dangradHP );
		setHpMax( nHp + dangradHP );
		setAttack( nAttack + dangradAttack );
		setCommonCrit( nCommonCrit + dangradCrit );
		setSkillCrit( nSkillCrit + dangradCrit );
	}
	
	private int equAttack 		= 0;
	private int equHp			= 0;
	/** 结算装备信息 */
	public void settlementPropertyToEquip() {
		
		// 算出 原本血量和攻击和暴击
		int nHp 			= getHpMax() - equHp;
		int nAttack 		= getAttack() - equAttack;
		
		// 然后 得出现在的
		equHp				= getEquBar().getOwnValue( EquPropertyType.HP );
		equAttack			= getEquBar().getOwnValue( getAttackType() == 0 ? EquPropertyType.PHY_ATTACK : EquPropertyType.MAG_ATTACK );
		
		setHp( nHp + equHp );
		setHpMax( nHp + equHp );
		setAttack( nAttack + equAttack );
		
		// 这里特殊加一下 绝对值
		setCritAbs( (short) getEquBar().getOwnValue( EquPropertyType.CRIT ) );
		setTenacityAbs( (short) getEquBar().getOwnValue( EquPropertyType.TENACITY ) );
		setHitAbs( (short) getEquBar().getOwnValue( EquPropertyType.HIT ) );
		setDodgeAbs( (short) getEquBar().getOwnValue( EquPropertyType.DODGE ) );
	}
	
	
	/**
	 * 升级主动技能
	 * @param skillOdds (几率)
	 */
	public boolean upgeradeSkill( byte skillOdds ) {
		
		int rand 	= RandomUtil.getRandomInt(0, 9999);
		int odds	= (int) skillOdds * 100;
		
		if( rand >= odds ) return false;
		
		getSkillAttack().updatalevel();
		
		return true;
	}

	/** 进化 */
	public void evolution() {
		
//		if( !isEvolution() )
//			return;
		
		quality	= HeroGrowupFormula.evolution( quality );
		
		settlementPropertyToLevel();
		settlementPropertyToQuality();
	}

	public String toString(){
		byte actual = HeroGrowupFormula.actualNumberFormula( quality );
		return "[UID：" + uID + 
				",品质：" + quality.toNumber() + "-" + quality.getLevel() +
				",职业：" + templet.professional.toNumber() + 
				",等级：" + level + "/" + HeroGrowupFormula.levelLimit[actual][1] +
				"]";
	}

	public String getToName() {
		return templet.name + quality.toName();
	}

	/**
	 * 取得额外值
	 * @param i 0钱 1经验
	 * @return
	 */
	public int getExtraValue( int i ) {
		int value 	= templet.extraValue[i];
		if( value == 0 ){
			value	= i == 0 ? level * DefaultCfg.SELL_HERO_CASH : level * DefaultCfg.DEVOUR_EXP_RATIO;
		}
		return value;
	}
	
	/** 卸下所有装备 */
	public void demountEquip() {
		getEquBar().demountAll();
	}

	public EquipmentBar getEquBar() {
		return equBar;
	}

	public void setEquBar(EquipmentBar equBar) {
		this.equBar = equBar;
	}


}
