package cn.mxz.bossbattle;


import java.util.List;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.bossbattle.battle.BossFighter;
import cn.mxz.bossbattle.broadcast.Broadcast;
import cn.mxz.city.City;
import cn.mxz.shop.Shopper;
import define.D;

/**
 * 一场战争的战斗记录信息，包括：
 * 击杀者
 * 声望
 * 伤害
 *
 * @author Administrator
 *
 */
public class BattleInfo {


	private BossFighter					boss;

	/**
	 * 击杀者的信息，有可能为null
	 */
	private Challenger					killer;

	private final ChallengerManager		challengerManager;

	private final Broadcast				broadcast;

	/**
	 * boss活动类型，
	 * 1、下午的活动,boss类型为玩家
	 * 2、上午的活动,boss类型为自动系统生成
	 */
	private final int					activityType;

	public BattleInfo(int activityType ) {
		this.activityType = activityType;

		challengerManager = new ChallengerManager();
		broadcast = new Broadcast();
		if( activityType == BossType.MORING ){//晚上的boss战的boss，在八点的时候再决定
			boss = new BossFighter(activityType);
		}
		//this.history = history;
		//boss = new BossFighter(activityType);
	}

	public void genBossHpMax(){
		boss.genHpMax();
	}

	/**
	 * 增加一个玩家
	 * @param user
	 */
	public void joinActivity( City user ){

		challengerManager.add(user);
	}
	
	public void forceJoinActivity(City user) {
		if( challengerManager.findByUser( user) != null ){
			return;
		}
		if( user.getBag().getCount(ChallengerManager.SHENFU_ID) < D.BOSS_BATTLE_NEED_SHENFU ){
			int needShenfu = D.BOSS_BATTLE_NEED_SHENFU - user.getBag().getCount(ChallengerManager.SHENFU_ID);//差几个买几个
			new Shopper(user).buy(ChallengerManager.SHENFU_ID, needShenfu);

		}
		joinActivity(user);		
	}


	/**
	 * @return killer，有可能为null
	 */ 
	public Challenger getKiller() {
		return killer;
	}

	public boolean bossIsDeath() {
		return boss.isDeath();
	}



	public BossBattleHistory getHistory(){
		BossBattleHistory history = new BossBattleHistory( boss.getHpMax() );
		history.saveHistory( challengerManager.getTopTen(), killer );
		return history;
	}

	/**
	 * @return activityType
	 */
	public int getActivityType() {
		return activityType;
	}

	public Challenger getChallenger(City user) {
		return challengerManager.findByUser(user);
	}


	public BossFighter getBoss() {

		return boss;
	}

	public ChallengerManager getChallengerManager() {
		return challengerManager;
	}

	void setKiller(Challenger killer) {
		this.killer = killer;
	}

	public List<? extends IDamageMessage> getMessage(int lastReceivTimeStamp) {
		return broadcast.get(lastReceivTimeStamp);
	}

	/**
	 *
	 * @param user
	 * @param isRebirth
	 * @return
	 */
	BossBattleResult doBattle( City user ) {
		if( boss.isDeath() ){
			throw new SureIllegalOperationException("boos已经死亡，无法挑战" + user.getId() );
		}

		Challenger challenger = getChallenger(user);
		if( challenger == null ){
			throw new SureIllegalOperationException( user + "尚未报名参加活动" );
		}

		BossBattleResult result = challenger.doBattle( boss );

		challengerManager.sort();
		result.setRank( challengerManager.getRank( challenger ) );

		if( boss.isDeath() ){
			killer = challenger;
		}
		broadcast.add( user.getPlayer().getNick(), result.getDamage() );
		return result;
	}

	void calcAward(){
		challengerManager.sendAward(boss, killer);
	}

	public void genBoss() {
		boss = new BossFighter(activityType);
		//boss.genHp();
	}
}
