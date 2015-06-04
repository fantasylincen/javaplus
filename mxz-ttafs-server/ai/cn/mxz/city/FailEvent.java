package cn.mxz.city;

import java.util.List;

import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituation;
import cn.mxz.fighter.Fighter;

import com.google.common.primitives.Floats;

import define.D;

/**
 * 战斗失败之后的提示信息
 * @author Administrator
 *
 */
public class FailEvent {
//	private final City 			user;
	private float			hpRate;
	private float			speedRate;
	private float			phyAttackRate;
	private float			phyDefendRate;
	private float			magicAttackRate;
	private float			magicDefendRate;
	private float			attackRate;
	private float			defendRate;
	private final Battle		battle;
	
	
	public FailEvent( City user, WarSituation ws ) {
		battle = ws.getBattle();
		calcHp( );
		calcSpeed(  );;
		calcAttack();
		calcDefend();
		
	}
	
	/**
	 * 计算平均值
	 * @param fighters
	 * @param type	1、物攻  
	 * 				2、法攻
	 * 				3、物防
	 * 				4、法防
	 * 				5、气血
	 * 				6、速度
	 * @return
	 */
	private float calcAverg( List<Fighter> fighters, int type ){
		float ret = 0;
		for( Fighter fighter : fighters ){
			switch( type ){
			case 1:
				ret += fighter.getAttribute().getAttack();
				break;
			case 2:
				ret += fighter.getAttribute().getMAttack();
				break;
			case 3:
				ret += fighter.getAttribute().getDefend();
				break;
			case 4:
				ret += fighter.getAttribute().getMDefend();
				break;
			case 5:
				ret += fighter.getHpMax();
				break;				
			case 6:
				ret += fighter.getAttribute().getSpeed();
				break;
				default:
					break;
			}
			
		}
		return ret/fighters.size();
	}
	/**
	 * 攻击方物防/防守方物攻*3（超过0.5取0.5）+ 攻击方法防/防守方法攻*1 （超超过0.5取0.5）
	 */
	private void calcDefend() {
		
		float myPhyDefend = calcAverg(  battle.getUnder().getFighters(), 3 );		
		float enemyPhyAttack = calcAverg(  battle.getUpper().getFighters(), 1 );
		
		phyDefendRate = (float) (myPhyDefend / enemyPhyAttack * D.FAIL_EVENT_DEFEND_RATE);
		if( phyDefendRate > 0.5f ){
			phyDefendRate = 0.5f;
		}
		
		float myMagicDefend = calcAverg(  battle.getUnder().getFighters(), 4 );
		float enemyMagicAttack = calcAverg(  battle.getUpper().getFighters(), 2 );
		
		magicDefendRate = (float) (myMagicDefend / enemyMagicAttack * D.FAIL_EVENT_DEFEND_RATE);
		if( magicDefendRate > 0.5f ){
			magicDefendRate = 0.5f;
		}
		defendRate = phyDefendRate + magicDefendRate;
		
	}

	private void calcAttack() {
		float myPhyAttack = calcAverg(  battle.getUnder().getFighters(), 1 );		
		float enemyPhyDefend = calcAverg(  battle.getUpper().getFighters(), 3 );
		
		phyAttackRate = (float) (myPhyAttack / enemyPhyDefend / D.FAIL_EVENT_ATTACK_RATE);
		if( phyAttackRate > 0.5f ){
			phyAttackRate = 0.5f;
		}
		
		float myMagicAttack = calcAverg(  battle.getUnder().getFighters(), 2 );
		float enemyMagicDefend = calcAverg(  battle.getUpper().getFighters(), 4 );
		
		magicAttackRate = (float) (myMagicAttack / enemyMagicDefend /D.FAIL_EVENT_ATTACK_RATE);
		if( magicAttackRate > 0.5f ){
			magicAttackRate = 0.5f;
		}
		attackRate = phyAttackRate + magicAttackRate;
		
	}

	public int[] calcResult(){
		int[] result = new int[5];
		result[0] = (int) (hpRate * 100);
		result[1] = (int) (attackRate * 100);
		result[2] = (int) (defendRate * 100);
		result[3] = (int) (speedRate * 100);
		result[4] = calcMinValue();
//		System.out.println( "xxxxxxxxxxxxxxxxxxxxxxxxxxx　" +result[4]);
		return result;
	}
	/**
	 * 返回
	 *
	 * 1、物攻  
	 * 2、法攻
	 * 3、物防
	 * 4、法防
	 * 5、气血
	 * 6、速度
	 */
	private int calcMinValue() {
		float[] temp = new float[4];
		temp[0] = hpRate;
		temp[1] = attackRate;
		temp[2] = defendRate;
		temp[3] = speedRate;
//		Util.Array.getMin(temp);
		float min = Floats.min(temp);
		if( min == temp[0] ){
			return 5;
		}
		if( min == temp[3] ){
			return 6;
		}
		
		if( min == temp[1] ){
			if( phyAttackRate > magicAttackRate ){
				return 2;
			}
			else{
				return 1;
			}
		}
		
		//只剩下一种情况了
		if( phyDefendRate > magicDefendRate ){
			return 4;
		}
		else{
			return 3;
		}
	}
	
	

	private void calcSpeed() {
		float mySpeed = calcAverg(  battle.getUnder().getFighters(), 6 );
		
		float enemySpeed = calcAverg(  battle.getUpper().getFighters(), 6 );
		speedRate = (float) (mySpeed / enemySpeed * D.FAIL_EVENT_SPEED_RATE);
		if( speedRate > 1 ){
			speedRate = 1;
		}			
	}

	private void calcHp() {
		float myHp = calcAverg(  battle.getUnder().getFighters(), 5 );
		float enemyHp = calcAverg(  battle.getUpper().getFighters(), 5 );
		hpRate = (float) (myHp / enemyHp * D.FAIL_EVENT_HP_RATE);
		if( hpRate > 1 ){
			hpRate = 1;
		}
	}
	
	
//	private int check(){
//		int ret = checkEquipment();
//		if( ret != 0 ){
//			return ret;
//		}
//		
//		ret = checkSkill();
//		if( ret != 0 ){
//			return ret;
//		}
//		
//		ret = checkTactical();
//		if( ret != 0 ){
//			return ret;
//		}
//		return 0;
//		
//	}
//
//
//	private List<Integer> checkMisc() {
//		List<Integer> list = Lists.newArrayList();
//		if( user.getLevel() >= 10 ){
//			
//			list.add( 4 );//天命开启
//		}
//		if( user.getLevel() >= 20){			
//			list.add( 5 );//元神
//		}
//		if( user.getLevel() >= 19){//助威团			
//			list.add( 6 );
//		}
//		return list;
//	}
//
//
//	private int checkTactical() {
//		int errorNo = 3;
//		if( user.getFormation().getCurrentTactical() == null ){
//			return errorNo;
//		}
//		
//		if( tacticalInBagIsEnough() && !tacticalIsMaxLevel() ){
//			return errorNo;
//		}
//		
//		return 0;
//		
//	}
//
//
//	private int checkSkill() {
//		int errorNo = 2;
//		for( Hero h : getHeros() ){	
//			if( h.getSkills().size() < 5 ){//存在技能空位
//				return errorNo;
//			}			
//		}
//		
//		if( skillInBagIsEnough()  ){
//			for( Hero h : getHeros() ){	
//				for( Skill skill : h.getSkills() ){
//					if( !skillIsMaxLevel( skill ) ){
//						return errorNo;
//					}
//				}		
//			}
//		}
//			
//		
//		return 0;
//	}
//
//
//	/**
//	 * 要返回多值
//	 * 7、强化界面
//	 * 8、穿戴界面
//	 * 9、获得界面
//	 * 10、合成界面
//	 * @return
//	 */
//	private int checkEquipment() {
//		int errorNo = 0;
//		if(!equipmentIsMaxLevel()){
//			return 7;
//		}
//		
//		if( !equipmentIsFull() ){
//			return equipmentIsFull1();
//			
//		}
//		
//		if( hasDrawings()){
//			return 10;
//		}
//		
////		for( Hero h : getHeros() ){
////			for( Equipment equipment : h.getEquipments() ){
////				if( !equipmentIsMaxLevel( equipment ) ){
////					return 7;
////				}
////			}
////			if( h.getEquipments().size() < D.MAX_EQUIPMENT_COUNT ){
////				return errorNo;
////			}
////		}
////		for( Hero h : getHeros() ){
////			
////		}
//		
//		
//		return 0;
//	}
//	
//	/**
//	 * 道具是否存在图纸
//	 * @param equipment
//	 * @return
//	 */
//	private boolean hasDrawings() {
//		// TODO 自动生成的方法存根
//		return false;
//	}
//
//
//	/**
//	 * 获取上阵伙伴列表
//	 * @return
//	 */
//	List<Hero> getHeros(){
//		return user.getFormation().getSelected().getFighters();
//		
//	}
//	
//	/**
//	 * 判断玩家装备是否强化到最高等级
//	 * @param euipment
//	 * @return
//	 * 			装备没有都强化到最高等级，返回false
//	 * 			装备都是最高等级，返回true
//	 */
//	private boolean equipmentIsMaxLevel( ){
//		int maxLevel = user.getLevel() + D.XXXXX;
//		if( maxLevel >= 100 ){
//			maxLevel = 100;
//		}
//		for( Hero hero : getHeros() ){
//			for( Equipment equipment : hero.getEquipments() ){			
//			
//				if( equipment.getLevel() < maxLevel ){
//				return false;
//				}
//			}
//		}
//		return true;
//	}
//	
//	private boolean equipmentIsFull( ){
//		for( Hero hero : getHeros() ){
//			if( hero.getEquipments().size() < D.MAX_EQUIPMENT_COUNT ){
//				return false;
//			}			
//		}
//		return true;
//	}
//	
//	/**
//	 * 玩家没有装备没有穿满的情况下，判断背包是否存在未使用装备，如果存在返回8（去穿戴）,否则返回9（去获得）
//	 * @param euipment
//	 * @return
//	 */
//	private int equipmentIsFull1( ){
//				
//		Collection<? extends Equipment> all = user.getEquipmentManager().getAll().values();
//		
//		for( Hero hero : getHeros() ){
//			
//			List<EmptyGrid> grid =  hero.getEmptyGrids();
//			
//			for (EmptyGrid g : grid) {
//				if(g.canInsertAnyOne(all)) {
//					return 8;
//				}
//			}
//			
////			if( hero.getEquipments().size() < D.MAX_EQUIPMENT_COUNT ){
////				return errorNo;
////			}
//			
//		}
//		return 9;
//	}
//	
//	
//	/**
//	 * 技能是否满级
//	 * @param euipment
//	 * @return
//	 */
//	private boolean skillIsMaxLevel( Skill skill ){
//		return skill.getLevel() == D.MAX_SKILL_LEVEL;
//	}
//	
//	/**
//	 * 判断未装备技能是否大于15
//	 * @return
//	 */
//	private boolean skillInBagIsEnough( ){
//		SkillManager sm = user.getSkillManager();
//		List<Skill> ls = sm.getSkillsUnEquipment();
//		return ls.size() > 15;
//	}
//	
//	/**
//	 * 判断未装备阵法是否大于10
//	 * @return
//	 */
//	private boolean tacticalInBagIsEnough( ){
//		return user.getFormation().getTacticalsAll().size( ) > 10;
//	}
//	
//	private boolean tacticalIsMaxLevel( ){
//		return user.getFormation().getCurrentTactical().getLevel() == 6;
//	}


}
