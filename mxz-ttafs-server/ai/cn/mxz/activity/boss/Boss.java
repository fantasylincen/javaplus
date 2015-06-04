package cn.mxz.activity.boss;

import java.util.List;

import cn.javaplus.math.Fraction;
import cn.mxz.city.City;
import cn.mxz.events.EventDispatcher2;
import cn.mxz.fighter.Fighter;

/**
 * Boss
 * 
 * @author 林岑
 * 
 */
interface Boss extends Fighter, EventDispatcher2 {

	/**
	 * Boss唯一ID
	 * 
	 * @return
	 */
	int getId();

	/**
	 * Boss的模板ID
	 * 
	 * @return
	 */
	int getTempletId();

	/**
	 * Boss的发现者
	 * 
	 * @return
	 */
	City getFinder();

	/**
	 * 该Boss逃跑剩余时间
	 * 
	 * @return 秒
	 */
	int getRemainSec();

	/**
	 * Boss 状态: 1: 我发现的Boss 2:别人邀请我挑战的Boss 3:被击杀的BOss 4:逃跑掉的Boss
	 */
	int getStatus(City city);

	/**
	 * Boss 的生命值
	 * 
	 * @return
	 */
	Fraction getHp();

	/**
	 * 奋力一击时所有属性提升倍数
	 * 
	 * @return
	 */
	int getMultiple();

	/**
	 * 获得所有攻击者, 最小长度为1 (因为至少有一个发现者)
	 * 
	 * @return
	 */
	List<BossChallenger> getBossChallengers();

	/**
	 * 将Boss的协助攻击者改为列表中的玩家
	 * 
	 * @param sub
	 */
	
	void changeHelper(List<BossChallenger> sub);

	/**
	 * 获得该Boss的击杀者
	 * 
	 * @return
	 */
	BossChallenger getKiller();

	/**
	 * 伤害排名第一的挑战者
	 * 
	 * @return
	 */
	BossChallenger getMvp();

	/**
	 * 伤害排名第二的挑战者
	 * 
	 * @return
	 */
	BossChallenger getJMvp();

	/**
	 * 是否是终极Boss
	 * 
	 * @return
	 */
	boolean isSuperBoss();

	/**
	 * 判断Boss是否逃跑了
	 * 
	 
	 * @return
	 */
	
	boolean isRunAway();

	void commit();

	int getShenJia();

}
