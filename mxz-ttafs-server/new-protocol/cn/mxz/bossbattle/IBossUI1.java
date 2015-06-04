package cn.mxz.bossbattle;

/**
 * Boss战 第一个界面
 * @author Administrator
 *
 */
public interface IBossUI1 {

	/**
	 * 是上午还是下午的boss战
	 * @return
	 */
	boolean isMorning();
	
	
	/**
	 * Boss名字
	 * @return
	 */
	String getBossName();
	
	/**
	 * Bossid，用于显示形象
	 * @return
	 */
	int getBossId();
	
	
	
	/**
	 * boss战开启剩余时间  秒
	 * @return
	 */
	int getStartRemainSec();
	
	/**
	 * boss战剩余时间  秒
	 * @return
	 */
	int getEndRemainSec();
	
	/**
	 * Boss总血量
	 * @return
	 */
	int getBossHp();
	
	
	/**
	 * boss当前血量
	 * @return
	 */
	int getCurrentBossHp();
	
	/**
	 * 当前参战人数
	 * @return
	 */	
	int getChallengerCount();
	
	/**
	 * 上次排行榜信息
	 */
	IRankInfoWithKiller getHistory();
	
	/**
	 * 是否第一次进入
	 * @return
	 */
	boolean isNewChallenger();
//	
	/**
	 * 返回神符数量
	 * @return
	 */
	int getShenfu();
	
	
	/**
	 * 获取声望
	 */
	int getReputation();
	
}
