package game.battle.auto;

import game.award.AwardInfo;
import game.battle.BattleBase;
import game.battle.IBattleUtil;
import game.battle.formation.IFormation;
import game.fighter.FighterBase;
import game.log.Logs;
import game.pvp.DanGrad;
import game.skill.SkillBase;
import game.talent.TalentBase;
import game.talent.TalentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import define.DefaultCfg;

import util.RandomUtil;

/**
 * 自动回合制的战斗模式
 * @author dxf
 * 2013-6-27 下午05:52:32
 */
public class AutoBattle extends BattleBase {

	private static final IBattleUtil	util = AutoBattleUtil.getInstance();

	private static final int 			MAX_ROUND;//最大回合数
	static{
		MAX_ROUND	= Integer.parseInt( DefaultCfg.getByCfg("max", "max_round") );
	}
	
	/**
	 * 攻方列表
	 */
	private final IFormation					attackers;
	private FighterBase[] 						attackFor;
	Map<TalentType, TalentBase> 				attackTalents; // 天赋  信息
	List<FighterBase> 							aCaptain;// 攻击方 队长
	DanGrad 									attackDanGrad; // 段位
	
	/**
	 * 守方列表
	 */
	private final IFormation 					defenders;
	private FighterBase[] 						defendFor;
	Map<TalentType, TalentBase> 				defendTalents; // 天赋 信息
	List<FighterBase> 							dCaptain;// 防守方 队长
	DanGrad 									defendDanGrad; // 段位
	
	private int 						currentRound = 0;
	
	/**
	 * 胜负结果
	 */
	private boolean 					attackerIsWin;
	
	/**
	 * 奖励列表 
	 */
	private List<AwardInfo>				awardContent;
	
	/**
	 * 是否第一次 
	 */
	private boolean						isOne;
	
	/**
	 * 当前累计伤害
	 */
	private int 						curAccumulativeDamage = 0;
	
	/**
	 * 战况信息
	 */
	private BattleSituation				battleSituation = new BattleSituation( 10240 );	

	public AutoBattle( IFormation attackers, IFormation defenders,
			Map<TalentType, TalentBase> attackTalents, Map<TalentType, TalentBase> defendTalents,
			DanGrad attackDanGrad, DanGrad defendDanGrad, 
			boolean isOne ) {
		super();		
		
		this.attackers 		= attackers;
		this.defenders 		= defenders;
		
		// 天赋信息
		this.attackTalents 	= attackTalents;
		this.defendTalents	= defendTalents;
		
		// 段位信息
		this.attackDanGrad	= attackDanGrad;
		this.defendDanGrad	= defendDanGrad;
		
		this.attackFor 		= new FighterBase[Formation.TOTAL_COUNT]; 
		this.defendFor 		= new FighterBase[Formation.TOTAL_COUNT]; 
		
		// 掉落
		awardContent		= new ArrayList<AwardInfo>();
		// 是否胜利
		attackerIsWin 		= true;
		this.isOne			= isOne;
		
		// 初始化 每个位置的人 
		init();
	}

	// 初始阵型  有人为true 没人就是false
	private void init()
	{
		// 攻击方 队长
		aCaptain = new ArrayList<FighterBase>();
		// 防守方 队长
		dCaptain = new ArrayList<FighterBase>();
		
		for( FighterBase f : attackers.getAllFighters() ){
			this.attackFor[ f.getPosition() % Formation.TOTAL_COUNT ] = f;
			if( f.getIsCaptain() && f.getCaptainSkill().getSkill() != null )
				aCaptain.add(f);
		}
		for( FighterBase f : defenders.getAllFighters() ){
			this.defendFor[ f.getPosition() % Formation.TOTAL_COUNT ] = f;
			if( f.getIsCaptain() && f.getCaptainSkill().getSkill() != null  )
				dCaptain.add(f);
		}
		
		// 将攻击方 的队长信息加入到 攻击方团队的每个战士属性上去
		for( FighterBase f : aCaptain ){
			for( int i = 0; i < attackFor.length; i++ ){
				if( attackFor[i] != null ){
					f.getCaptainSkill().run( attackFor[i] );
				}
			}
		}
		
		// 将攻击方 的队长信息加入到 攻击方团队的每个战士属性上去
		for( FighterBase f : dCaptain ){
			for( int i = 0; i < defendFor.length; i++ ){
				if( defendFor[i] != null )
					f.getCaptainSkill().run( defendFor[i] );
			}
		}
	}
	
	/**
	 * 根据传入的战士获取相应的阵形
	 * @param fighter
	 * @param isFriend	
	 * 			true：输入战士这一边的阵形		false:输入战士敌对的阵形
	 * @return
	 */
	private IFormation getFormation( FighterBase fighter, boolean isFriend ){
		if( isFriend ){
			return fighter.getIsBottom() ?  attackers : defenders;
		}
		return fighter.getIsBottom() ? defenders : attackers;
	}
	
	@Override
	public void run( boolean isMovesSequence ) {
		
		battleSituation.putStartFight();
		
		boolean isEnd 		= false;
		
		int index 			= 0;
		
		// 先由攻击方 出招
		boolean is_index 	= isMovesSequence;
		
		while( !isEnd ){
		
			// 回合开始
			battleSituation.putRoundFlag();
			index = 0;
			
			while( index < Formation.TOTAL_COUNT )
			{
				FighterBase currentAttacker = is_index ? attackFor[index] : defendFor[index];
				
				// 在这里 先做交换
				is_index = !is_index;
				
				// 每次在这里加1  如果为true 说明又是新的开始
				if( is_index == isMovesSequence )
					++index;
				
				if( currentAttacker == null || currentAttacker.isDie() )
					continue;
				
				// 记录战斗次数  没其他作用 只为发包
				battleSituation.onceBattle();
				
				// 不管怎么先放攻击者 位置
				battleSituation.putFighter( currentAttacker.getPosition() );
				
				// 获取敌方阵型
				IFormation currentDefenders = getFormation( currentAttacker, false );
				
				// 开始攻击  结算
				if( doAttack( currentAttacker, currentDefenders ) ){
					isEnd = true;
					break;
				}
				
			}
			
			// 当前回合数
			currentRound++;
			// 如果大于最大回合数  
			if( currentRound > MAX_ROUND ){
				Logs.debug( "当前回合数" + currentRound + "超过" + MAX_ROUND + "了！！！！！" );
				this.attackerIsWin 	= false;//平局的情况算防守方胜利
				isEnd 				= true;
			}
		}
		
		battleSituation.putEndFight();
		
		battleSituation.putIsWin( this.attackerIsWin );
	}
	
	/**
	 * 开始攻击 结算
	 * @param currentAttacker
	 * @param currentDefenders
	 * @param canSkill
	 * @return
	 */
	private boolean doAttack(FighterBase attacker, IFormation currentDefenderTeam ) {
		
		// 获取攻击者技能 
		SkillBase skill			= attacker.getSkill();
		
		List<FighterBase> npc 	= null;
		
		if( skill != null ){
			// 取得技能要攻击的所有战士
			npc = skill.getRival() == 0 ? 
					currentDefenderTeam.getFighterOnEffect( attacker, skill.getChoose() ) :
						getFormation( attacker, true ).getFighterOnEffect( attacker, skill.getChoose() );
			
			// 如果没有 切换普通攻击
			if( npc != null ){
				if( skill.getIsCan() && npc.isEmpty() ){
					skill 		= attacker.getCommonSkill();
					npc 		= skill.getRival() == 0 ? 
									currentDefenderTeam.getFighterOnEffect( attacker, skill.getChoose() ) :
										getFormation( attacker, true ).getFighterOnEffect( attacker, skill.getChoose() );
				}
			}
		} 
		
		int skillID = skill != null ? skill.getId() : 0;
		byte count 	= (byte) (npc == null ? 0 : npc.size());
		
		battleSituation.putSkillAttackPrefix( skillID, count );
		
		// 对每一个 敌对英雄的技能特效操作
		if( npc != null ){
			for( FighterBase f : npc ){
				
				AttackInfo info = util.calcAttackInfo( attacker, f, attackTalents, defendTalents, 
						aCaptain, dCaptain, attackDanGrad, defendDanGrad, skill );
				
				battleSituation.putNormalAttack( f, info );
				
				// 如果不是 怪物 那么就记录 伤害值
				if( !attacker.getIsMonster() ) 
					battleSituation.setRecordDamage( info.getDamage() );
					
				if( info.isHit() ) if( reduceHp( f, info.getDamage() ) ) {
					return true;
				}
			}
		}

		return false;
	}

	
	/**
	 * 对被攻击方进行扣hp等一系列操作<br>
	 * 如果一方全军覆没，则返回true
	 * 
	 * @param defender			被攻击的战士
	 * @param damage
	 * @return
	 * 			true			被攻击方全军覆没了<br>
	 * 			false			被攻击方继续存活
	 */
	public boolean reduceHp( FighterBase defender, int damage ){
		
		// 这里记录 挑战者对防御者的 累计伤害
		if( !defender.getIsBottom() && damage > 0 )
			curAccumulativeDamage += damage;
		
		int changHP = defender.getHp() - damage;
		changHP 	= changHP < 0 ? 0 : changHP;
		changHP		= changHP > defender.getHpMax() ? defender.getHpMax() : changHP;
		
		// 设置血量
		defender.setHp( changHP );
		
		battleSituation.putIsDie( defender.isDie() );
		
		//被攻击战士已经挂了 那么 就查看所以战士 是否挂了
		if( defender.isDie() ){	
			
			// 如果是怪物 那么就要进行掉落计算
//			if( defender.getIsMonster() ){
				calculationDrop( defender.getAwardContent() );
//			}
			
			// 这里判断是否 全军覆没
			if( getFormation( defender, true ).isAllDie() ){
				attackerIsWin = !defender.getIsBottom();
				return true;
			}
		}
		return false;
	}

	// 计算掉落
	private void calculationDrop( List<AwardInfo> content )
	{
		List<AwardInfo> awardInfo = new ArrayList<AwardInfo>();
		
		if( content != null ){
			
			for( AwardInfo award : content ){
				if( award.getPropId() == -1 ) continue;
				int rand 		= (int) ((isOne ? award.getRand()[1] : award.getRand()[0]) * 100);
				int	randValue	= RandomUtil.getRandomInt(0, 9999);
				if( randValue < rand ){
					awardInfo.add( award );
					break;
				}
			}
			
			if( awardInfo.size() != 0 )
				awardContent.addAll( awardInfo );
		}
		
		battleSituation.putDrop( awardInfo );
	}
	
	/**
	 * 获得攻击方是否全部死亡
	 * @return
	 */
	public boolean attackIsAllDie(){
		return attackers.isAllDie();
	}
	
	public BattleSituation getBattleSituation() {
		return battleSituation;
	}
	
	@Override
	public boolean getAttackerIsWin() {
		return attackerIsWin;
	}
	
	public int getCurrentRound(){
		return currentRound;
	}
	
	public int getAccumulativeDamage(){
		return curAccumulativeDamage;
	}
	
	/**
	 * 获得当前战斗完的 奖励列表
	 * @return
	 */
	public List<AwardInfo> getAwardContent(){
		return awardContent;
	}

	public static void main(String[] args) {
	}

	public List<FighterBase> getAttackHeroData() {
		List<FighterBase> list = new ArrayList<FighterBase>();
		for( int i = 0; i < attackFor.length; i++ ){
			if( attackFor[i] != null )
				list.add( attackFor[i] );
		}
		return list;
	}

	public List<FighterBase> getDefenseHeroData() {
		List<FighterBase> list = new ArrayList<FighterBase>();
		for( int i = 0; i < defendFor.length; i++ ){
			if( defendFor[i] != null )
				list.add( defendFor[i] );
		}
		return list;
	}

	public int battleCount() {
		return battleSituation.getBattle();
	}

	public void addAward( AwardInfo christmasDungeonAward ) {
		awardContent.add(christmasDungeonAward);
	}
	
}
