package cn.mxz.bossbattle;

import java.util.Comparator;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.bossbattle.battle.BossBattle;
import cn.mxz.bossbattle.battle.BossFighter;
import cn.mxz.city.City;
import define.D;


/**
 * 根据伤害值排序的比较类
 * @author Administrator
 *
 */
class SortByDamage implements Comparator<Challenger>{

	@Override
	public int compare(Challenger o1, Challenger o2) {
		return o2.getAllDamage() - o1.getAllDamage();
	}
	
}

/**
 * 参与战斗的玩家信息
 * @author Administrator
 *
 */
public class Challenger {
	
	public static final Comparator<Challenger> DAMAGE_COMPARATOR = new SortByDamage();

	
	private final City					user;
	
	/**
	 * 总伤害
	 */
	private int							allDamage;	

	/**
	 * 上次接收广播消息的时间
	 */
	private int							lastReceivTimeStamp = 0;
	
	/**
	 * 战斗冷却时间
	 */
	private int							endSec = 0;//冷却时间	
	
	/**
	 * 挑战次数
	 */
	private int							challengeCount;
	
	/**
	 * 战斗加成管理
	 */
	private final BossBattleAddtion 	addtion;
	
	/**
	 * 活动奖励管理
	 */
	private final ActivityAward			award;
	
	/**
	 * 名次，只有战斗结束后才有意义
	 */
	private int							rank;
	
	
	

	public Challenger( City user) {
		this.user = user;
		this.award = new ActivityAward( this );
		this.addtion = new BossBattleAddtion(user);
	}

	public int getChallengeCount() {
		return challengeCount;
	}

	public City getUser() {
		return user;
	}	
	
//	/**
//	 * 增加伤害值
//	 * @param damage
//	 * @return
//	 */
//	public int addDamage( int damage ){
//		allDamage += damage;
//		return allDamage;
//	}

	/**
	 * @return allDamage
	 */
	public int getAllDamage() {
		return allDamage;
	}

	public int getLastReceivTimeStamp() {
		int ret = lastReceivTimeStamp;
		lastReceivTimeStamp = (int) (System.currentTimeMillis() / 1000);
		return ret;
	}

	/**
	 * 获取下次战斗的冷却时间 秒
	 * @return
	 */
	public int getFightRemainSec() {
		int current = (int) (System.currentTimeMillis() / 1000);
		int ret = endSec - current;
		ret = Math.max( ret, 0 );
		return ret;
	}
	
	/**
	 * 开始 boss 战斗
	 * @param boss
	 * @return 
	 */
	public BossBattleResult doBattle( BossFighter boss ){
		
		if( getFightRemainSec() > 0 ){
			throw new SureIllegalOperationException( "boss战冷却时间未到" + getAllDamage() );
		}
		
		int oldHp = boss.getHpNow();
		BossBattle bossBattle = new BossBattle( user.getFormation().getSelected(), boss, addtion.getAddtionPercent() ); 
		bossBattle.fighting();
		
		int hpNow = Math.max( 0, boss.getHpNow() );
		int damage = oldHp - hpNow;
		
		allDamage += damage;
		
		int current = (int) (System.currentTimeMillis() / 1000);
		endSec = current + D.BOSS_BATTLE_COLDDOWN;//重新设置冷却时间
		
		challengeCount++;
		addtion.reInit();
		
		BossBattleResult result = new BossBattleResult();
		result.setDamage(damage);
		result.setAllDamage(allDamage);
		result.setSituation( bossBattle.getWarSituation() );
		return result;
	}

	public BossBattleAddtion getAddtion() {
		return addtion;
	}

	public ActivityAward getAward() {
		return award;
	}	
	
	/**
	 * 获取当前挑战次数可以换取的培养丹
	 * @param	isNext 		
	 * 			true	需要计算当前挑战次数+1的培养丹，通常用于前端显示<br>
	 * 			false	只计算本次
	 * @return
	 */
//	public int getPeiyangdanFromChallengeCount( boolean isNext ) {
//		int count = isNext ? challengeCount+1 : challengeCount;
//		if( count == 0 ){
//			return 0;
//		}
//		
//		int id = Math.min( count, BossTrainExtraRankTempletConfig.getMaxKey() );
//		
//		BossTrainExtraRankTemplet templet = BossTrainExtraRankTempletConfig.get(id);
//		return templet.getTrain();
//	}

	/**
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank 要设置的 rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	public void rebirth() {
		addtion.rebirth();
		this.endSec = 0;
	}
	
}
