package game.battle.auto;

import game.award.AwardInfo;
import game.award.AwardType;
import game.award.ectype.EctypeAward;
import game.battle.AttackType;
import game.fighter.FighterBase;

import java.nio.ByteBuffer;
import java.util.List;

public class BattleSituation  {
	
	private ByteBuffer	situation 	= null;
	
	// 战斗次数
	private short battleCount		= 0;
	
	// 记录 累计对敌方造成的伤害
	private int	recordDamage		= 0;
	
	
	public BattleSituation( int size ){
		situation = ByteBuffer.allocate( size );
	}
	
	/** 开始战斗 */
	public void putStartFight(){
		situation.putShort( (short)1 );
	}
	/** 战斗结束 */
	public void putEndFight(){
		situation.putShort(0 , battleCount );
	}
	/** 是否胜利 */
	public void putIsWin( boolean iswin ){
		situation.put( (byte)(iswin ? 1 : 0) );
	}
	
	/** 回合分隔符，标识一个回合开始 */
	public void putRoundFlag( ){
		situation.put( AttackType.BEGIN_ROUND.toNumber() );
	}

	/** 表示开始了一次 战斗 */
	public void onceBattle(){
		++battleCount;
	}
	public int getBattle(){
		return battleCount;
	}
	
	/**
	 * 是否允许出招
	 * @param isCan
	 */
	public void putIsCanHit( boolean isCan ){
		situation.put( (byte)( isCan ? 1 : 0 ) );
	}
	
	/** 获得战斗次数 */
	public short getBattleCount(){
		return this.battleCount;
	}
	
	/** 设置总伤害 （累加） */
	public void setRecordDamage( int damage ){
		recordDamage += damage;
	}

	/** 获得 本次战斗总伤害值  */
	public int getRecordDamage(){
		return this.recordDamage;
	}
	
	/**
	 * 一次攻击的情况
	 * @param attacker
	 * @param defender
	 * @param info
	 */
	public void putNormalAttack( FighterBase defender, AttackInfo info ) {
		situation.put( defender.getPosition() );
		putIsHit( info );
		if( info.isHit() ){
			situation.putInt( info.getDamage() );
		}
	}

	/**
	 * 放 掉落信息
	 */
	public void putDrop( List<AwardInfo> content )
	{
		situation.put( (byte) content.size() );
		for ( AwardInfo a : content ){
			situation.put( a.getAward().toNumber() );
			if( a.getAward() == AwardType.HERO ){
				byte colour = 0;
				byte level	= 0;
				if( a.getArguments() != null && a.getArguments().length >= 3 ){
					colour	= (byte) a.getArguments()[1];
					level	= (byte) a.getArguments()[2];
				}
				situation.put( colour );
				situation.put( level );
			}
			situation.putInt( a.getPropId() );
			situation.putInt( a.getNumber() );
		}
	}
	
	/**
	 * 放 是否死亡
	 */
	public void putIsDie( boolean isdie )
	{
		situation.put( (byte) (isdie ? 1 : 0) );
	}
	
	/**
	 * 放置反击信息
	 * @param damage
	 */
	public void putCounterAttackDamage( int damage ) {
		situation.putInt( damage );
	}
	/**
	 * 放置攻击前buff掉血值
	 * @param damage
	 */
	public void putBuffDamage( int damage ) {
		situation.putInt( damage );
	}
	/**
	 * 放置技能值
	 * @param value
	 */
	public void putSkillValue( int value ) {
		situation.putInt( value );
	}
	
	/**
	 * 技能攻击时 的闪避暴击信息  这个和伤害分开放
	 * @param info
	 */
	public void putIsHit( AttackInfo info ) {
		situation.put( info.getRawData() );
	}

	/**
	 * 放置技能攻击的前缀信息
	 * @param attacker
	 * @param skillId
	 */
	public void putSkillAttackPrefix( int skillId, byte count ) {
		situation.putInt( skillId ).put( count );
	}

	/**
	 * 配合技能攻击，单独放入受技能影响的战士位置
	 * @param position
	 */
	public void putFighter(byte position) {
		situation.put(position);		
	}
	
	/**
	 * 配合技能攻击，单独放入战士受影响的属性个数
	 * @param effectCount
	 */
	public void putEffectCount( byte effectCount ) {
		situation.put( effectCount );
	}

	/**
	 * 最后放入 这个波数的掉落信息
	 * @param award
	 */
	public void putReadyToAward( EctypeAward award ){
		
		situation.put( (byte)(award == null ? 0 : award.getContent().size())  );
		
		if( award != null ){
			for ( AwardInfo a : award.getContent() ){
				situation.put( a.getAward().toNumber() );
				if( a.getAward() == AwardType.HERO ){
					byte colour = 0;
					byte level	= 0;
					if( a.getArguments() != null && a.getArguments().length >= 3 ){
						colour	= (byte) a.getArguments()[1];
						level	= (byte) a.getArguments()[2];
					}
					situation.put( colour );
					situation.put( level );
				}
				situation.putInt( a.getPropId() );
				situation.putInt( a.getNumber() );
			}
		}
	}
	
	public ByteBuffer getData() {
		return situation;
	}

	/**
	 * 放入奖杯个数
	 * @param b
	 */
	public void putAwardTrophy( byte b ) {
		situation.put(b);
	}	
	
}
