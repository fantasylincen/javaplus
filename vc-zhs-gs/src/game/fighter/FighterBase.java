package game.fighter;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import config.fetter.FetterTemplet;
import config.fighter.Professional;
import config.skill.PassiveSkillTemplet;
import config.skill.accord.SkillTemplet;

import game.award.AwardInfo;
import game.skill.AccordSkill;
import game.skill.CaptainSkill;
import game.skill.CommonSkill;
import game.skill.SkillBase;

/**
 * 所有战士的基础类
 * @author DXF
 *
 */
public class FighterBase {
	
	private int id;
	
	// 没出招两次发一个技能
	private final byte AttacksNumConst 	= 2;
	
	private String name					= "未知";
	
	// 是否怪物
	private boolean isMonster			= false;
	
	// 是否处于战场的攻击方，手游中，下方通常是攻击方
	private boolean isBottom 			= true;

	
	// 职业
	private Professional				professional	= null;
	
	// 最大血量 
	private int 						hpMax			= 0;

	// 当前血量
	private int 						hp				= 0;
	
	// 所在阵型中的位置
	private byte 						position 		= -1;
		
	// 攻击类型
	private byte						attackType		= 0;
	
	// 攻击力
	private int 						attack			= 1;


	// 普通攻击 技能模板
	private CommonSkill					commonAttack	= null;
	
	// 技能攻击 技能模板
	private AccordSkill					skillAttack		= null;
	
	// 被动技能 技能模板
	private PassiveSkillTemplet			passiveSkill	= null;
	
	// 队长技能 技能模板
	private CaptainSkill				captainSkill	= null;

	// 闪避 暴击 
	private float 						dodge			= 0;
	private float 						commonCrit		= 0;
	private float 						skillCrit		= 0;
	
	// 掉落 这里主要是用在怪身上
	private List<AwardInfo>				awardContent	= null;
	
	///  //////////    / / // / / // / / / // / / // / /
	// 记录 出招次数 （主要是发技能用）
	private byte 						AttacksNum		= 0;
	
	// 是否精英
	private boolean 					isElite			= false;
	
	// 是否队长
	private boolean 					isCaptain		= false;
	
	// 	暴击 绝对值
	private short						critAbs			= 0;
	//	韧性 绝对值
	private short						tenacityAbs		= 0;
	public byte getAttacksNum() {
		return AttacksNum;
	}

	public void setAttacksNum(byte attacksNum) {
		AttacksNum = attacksNum;
	}

	public short getCritAbs() {
		return critAbs;
	}

	public void setCritAbs(short critAbs) {
		this.critAbs = critAbs;
	}

	public short getTenacityAbs() {
		return tenacityAbs;
	}

	public void setTenacityAbs(short tenacityAbs) {
		this.tenacityAbs = tenacityAbs;
	}

	public short getHitAbs() {
		return hitAbs;
	}

	public void setHitAbs(short hitAbs) {
		this.hitAbs = hitAbs;
	}

	public short getDodgeAbs() {
		return dodgeAbs;
	}

	public void setDodgeAbs(short dodgeAbs) {
		this.dodgeAbs = dodgeAbs;
	}

	public List<FetterTemplet> getFetters() {
		return fetters;
	}

	public void setFetters(List<FetterTemplet> fetters) {
		this.fetters = fetters;
	}

	public byte getAttacksNumConst() {
		return AttacksNumConst;
	}

	public void setMonster(boolean isMonster) {
		this.isMonster = isMonster;
	}

	public void setBottom(boolean isBottom) {
		this.isBottom = isBottom;
	}

	public void setElite(boolean isElite) {
		this.isElite = isElite;
	}

	public void setCaptain(boolean isCaptain) {
		this.isCaptain = isCaptain;
	}

	//	命中 绝对值
	private short						hitAbs			= 0;
	//	闪避 绝对值
	private short						dodgeAbs		= 0;
	
	// 羁绊
	private List<FetterTemplet> 		fetters			= null;
	
	public FighterBase( int id ) {
		this.id = id;
	}

	public FighterBase(){
		
	}
	/**
	 * 拷贝构造函数
	 * @param fighter
	 */
	public FighterBase( FighterBase fighter ) {
		
		this.setId( fighter.getId() );
		this.setName( fighter.getName() );
		this.setIsMonster( fighter.getIsMonster() );
		this.setIsBottom( fighter.getIsBottom() );
		this.setProfessional( fighter.getProfessional() );
		this.setHpMax( fighter.getHpMax() );
		this.setHp( fighter.getHpMax() );
		this.setPosition( fighter.getPosition() );
		this.setAttackType( fighter.getAttackType() );
		this.setAttack( fighter.getAttack() );
		this.setCommonAttack( fighter.getCommonAttack() );
		this.setSkillAttack( fighter.getSkillAttack() );
		this.setPassiveSkill( fighter.getPassiveSkill() );
		this.setCaptainSkill( fighter.getCaptainSkill() );
		this.setDodge( fighter.getDodge() );
		this.setCommonCrit( fighter.getCommonCrit() );
		this.setSkillCrit( fighter.getSkillCrit() );
		this.setAwardContent( fighter.getAwardContent() );
		this.setIsElite( fighter.getIsElite() );
		this.setIsCaptain( fighter.getIsCaptain() );
		this.setCritAbs( fighter.getCritAbs() );
		this.setTenacityAbs( fighter.getTenacityAbs() );
		this.setHitAbs( fighter.getHitAbs() );
		this.setDodgeAbs( fighter.getDodgeAbs() );
		this.setFetters( fighter.getFetters() );
	}

	public void setId( int id ){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	
	public void setName( String name ){
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setIsMonster( boolean isMonster ){
		this.isMonster = isMonster;
	}
	public boolean getIsMonster() {
		return this.isMonster;
	}

	
	public void setIsBottom( boolean isBottom ){
		this.isBottom = isBottom;
	}
	public boolean getIsBottom() {
		return this.isBottom;
	}
	
	public void setProfessional( Professional professional ){
		this.professional = professional;
	}
	public Professional getProfessional(){
		return this.professional;
	}
	
	public void setHpMax( int hp ){
		this.hpMax = hp;
	} 
	public int getHpMax() {
		return this.hpMax;
	}
	
	public void setHp( int hp ){
		this.hp = hp;
	}
	public int getHp(){
		return this.hp;
	}
	
	public void setPosition( byte pos ){
		this.position = pos;
	}
	public byte getPosition(){
		return this.position;
	}
	
	public void setAttackType( byte aType ){
		this.attackType = aType;
	}
	public byte getAttackType() {
		return this.attackType;
	}
	
	public void setAttack( int attack ){
		this.attack = attack;
	}
	public int getAttack() {
		return this.attack;
	}

	public void setCommonAttack( CommonSkill skill ){
		this.commonAttack = skill;
	}
	public CommonSkill getCommonAttack() {
		return this.commonAttack;
	}

	public void setSkillAttack( AccordSkill skill ){
		this.skillAttack = skill;
	}
	public AccordSkill getSkillAttack() {
		return this.skillAttack;
	}
	
	public void setPassiveSkill( PassiveSkillTemplet skill ){
		this.passiveSkill = skill;
	}
	public PassiveSkillTemplet getPassiveSkill() {
		return this.passiveSkill;
	}
	
	public void setCaptainSkill( CaptainSkill skill ){
		this.captainSkill = skill;
	}
	public CaptainSkill getCaptainSkill() {
		return this.captainSkill;
	}
	
	public void setDodge( float dodge ){
		this.dodge = dodge;
	} 
	public float getDodge() {
		return this.dodge;
	}
	
	public void setCommonCrit(float commonCrit) {
		this.commonCrit = commonCrit;
	}
	public float getCommonCrit(){
		return this.commonCrit;
	}
	
	public void setSkillCrit(float skillCrit) {
		this.skillCrit = skillCrit;
	}
	public float getSkillCrit(){
		return this.skillCrit;
	}

	public void setAwardContent( List<AwardInfo> award ){
		this.awardContent = award;
	} 
	public List<AwardInfo> getAwardContent() {
		return this.awardContent;
	}
	
	public void setIsElite( boolean isElite ){
		AttacksNum = 1;
		if( isElite ) AttacksNum = AttacksNumConst;
		this.isElite = isElite;
	}
	public boolean getIsElite(){
		return this.isElite;
	}
	
	public void setIsCaptain( boolean isCaptain ){
		this.isCaptain = isCaptain;
	}
	public boolean getIsCaptain(){
		return this.isCaptain;
	}
	
	public Object toSimpleString() {
		return "name=" + name + ", position=" + position;
	}

	@Override
	public String toString() {
		return "FighterBase [" +
				"id=" + id + 
				", hpMax=" + hpMax + 
				", hp=" + hp + 
				", position=" + position + 
				", attackType=" + attackType +
				", attack=" + attack +
				", 普通攻击=" + commonAttack + 
				", 技能攻击=" + skillAttack + 
				", 被动技能=" + passiveSkill + 
				", 出招次数=" + AttacksNum + 
				"]";
	}
	
	/**
	 *  使用技能检查
	 * @return
	 */
	public boolean canSkill() 
	{
		if( skillAttack == null ) return false;
		
		if( ++AttacksNum > AttacksNumConst ){
			AttacksNum = 0;
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 是否死亡
	 * @return
	 */
	public boolean isDie() {
		return hp <= 0;
	}

	/**
	 * 获得技能
	 * @return
	 */
	public SkillBase getSkill() {
		
		boolean isCan			= canSkill();
		
		SkillTemplet templet	= null;
		
		byte level				= 0;
		
		if( skillAttack == null || skillAttack.getSkill() == null ){
			templet				= commonAttack.getSkill();
			if( templet == null )
				return null;
		}
		
		if( templet == null ){
			templet	= isCan ? skillAttack.getSkill() : commonAttack.getSkill();
			level	= (byte) (isCan ? skillAttack.getLevel() : 0);
		}
		
		return new SkillBase( templet, level, isCan, attackType );
	}

	/**
	 * 获得普通技能
	 * @return
	 */
	public SkillBase getCommonSkill() {
		return new SkillBase(commonAttack.getSkill(), (byte)0, false, attackType);
	}

	/** 结算羁绊  */
	public void runFetter( List<FighterBase> attackers ) {
		if( fetters == null || fetters.isEmpty() ) return;
		
		for( FetterTemplet fetter : fetters ){
			if( fetter.isActivate( attackers ) ){
				fetter.run( this );
			}
		}
	}

}
