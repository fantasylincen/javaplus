package cn.mxz.chuangzhen;

/**
 * 排行榜上的玩家
 *
 * @author 林岑
 *
 */
public interface ChuangZhenRankPlayer {

	/**
	 * 战士ID
	 * @return
	 */
	int getFighterId();

	/**
	 * 是否是男人
	 * @return
	 */
	boolean isMan();

	/**
	 * 玩家昵称
	 *
	 * @return
	 */
	String getNick();

	/**
	 * 玩家ID
	 *
	 * @return
	 */
	String getUserId();

	/**
	 * 玩家等级
	 *
	 * @return
	 */
	int getLevel();

	/**
	 * VIP等级
	 *
	 * @return
	 */
	int getVipLevel();

	/**
	 * 渡劫层数
	 *
	 * @return
	 */
	int getFloor();

	/**
	 * 排名
	 *
	 * @return
	 */
	int getRank();

	/**
	 * 星星数
	 *
	 * @return
	 */
	int getStar();
}
