package cn.mxz.bossbattle;

import java.util.List;


public interface IBossUI2 {
	
	/**
	 * 本次战斗已经使用的元宝数
	 * @return
	 */
	int getCurUseInspireGold();
	
	/**
	 * 最近最大的伤害值列表
	 * @return
	 */
	//List<? extends IDamageMessage> getDamages();
	List<IDamageMessage> getDamages();
	
	/**
	 * 挑战剩余时间 秒
	 * @return
	 */
	int	getFightRemainSec();
	
	/**
	 * boss战剩余时间 秒
	 * @return
	 */
	int	getRemainSec();
	
	/**
	 * 挑战者人数
	 * @return
	 */
	int getChallengerCount();
	
	/**
	 * 下一次鼓舞士气所需要的元宝
	 * @return
	 */
	int getInspireGold();
	
	/**
	 * 极限鼓舞士气所需要的元宝
	 * @return
	 */
	int getMaxInspireGold();
	
	/**
	 * 浴火重生所需要的元宝
	 * @return
	 */
	int getRibirthGold();
	
	/**
	 * 已经使用的元宝
	 * @return
	 */
	int getUseGold();
	
	/**
	 * 属性加成
	 * @return
	 */
	float getAddtionPercent();
	
	/**
	 * boss的当前血量
	 * @return
	 */
	int getBossHpCurrent();
	
	/**
	 * 返回玩家 的挑战次数
	 * @return
	 */
	int getChallengCount();

	/**
	 * 是否使用过浴火重生
	 * @return
	 */
	boolean isRibirthInTurn();
	
//	/**
//	 * 获取使用元宝换取的培养丹
//	 * @return
//	 */
//	int getPeiyangdanFromGold();
	
	/**
	 * 获取下一挑战次数换取的培养丹
	 * @return
	 */
//	int getPeiyangdanFromChallengeCount();
	
	int getGold();
	
}
