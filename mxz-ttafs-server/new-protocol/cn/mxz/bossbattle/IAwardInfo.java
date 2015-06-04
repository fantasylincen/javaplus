package cn.mxz.bossbattle;

import java.util.List;

/**
 * 奖励面板
 * @author Administrator
 *
 */
public interface IAwardInfo {

	/**
	 * boss的总血量
	 * @return
	 */
	int getBossHpMax();

	/**
	 * boss是否死亡
	 * @return
	 */
	boolean getBossIsDie();

	/**
	 * 排名信息
	 * @return
	 */
	IRankInfo getRankInfo();

	/**
	 * 我的奖品
	 * @return
	 */
	List<Prize> getPrize();
	
	
	/**
	 * 获取领奖中心的id
	 * @return
	 */
	int getIdInPrizeCenter();

	//void setIdInPrizeCenter(int id);

}
