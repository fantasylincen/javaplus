package cn.mxz.bossbattle;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface IBossTransform {

	IBossUI1 getBossUI1();


	IBossUI2 getBossUI2();

	/**
	 * 战斗
	 * @return
	 */
	//WarSituation doBattle( boolean isRebirth );

	/**
	 * 鼓舞士气
	 * @param isMax		是否极限鼓舞
	 * @return 
	 */
	ISubBossUI2 inspire( Boolean isMax );

	/**
	 * 重生
	 * @return 
	 */
	ISubBossUI2 rebirth();

	/**
	 * 报名参加到活动
	 */
	void joinActivity();
	
	/**
	 * 神符不足，强制购买并加入到活动
	 */
	void forceJoinActivity();

	/**
	 * 当前排名列表
	 * @return
	 */
	IRankInfoWithMyself getRankList();

	/**
	 * 打开奖励面板
	 * @return
	 */
	IAwardInfo getAward();

	void setUser(City user);
}
