package cn.mxz.tower;

/**
 * 裂缝
 * @author 林岑
 *
 */
interface TowerBug {

	/**
	 * 是否是我自己发现的裂缝
	 * @return
	 */
	boolean isMine(String userId);

	/**
	 * 发现裂缝的人
	 * @return
	 */
	String getFinder();

	/**
	 * 裂缝剩余时间(秒)
	 * @return
	 */
	int getRemainSec();

}
