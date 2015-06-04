package cn.mxz.chuangzhen;

import cn.mxz.formation.PlayerCamp;

public interface ChuangZhenPlayer extends ChuangZhenPro{

	PlayerCamp camp();

	/**
	 * 玩家ID
	 * @return
	 */
	String getId();

	/**
	 * 昵称
	 */
	String getNick();

	/**
	 * 在打X关加强属性
	 */
	int getNextAddition();

	/**
	 * 在打X关结算奖励
	 */
	int getNextReward();

	/**
	 * 排名
	 * @return
	 */
	int getRank();

	/**
	 * 当前玩家的金币
	 * @return
	 */
	int getCash();

	/**
	 * 增加获得的星星
	 * @param received
	 */
	void addStar(int received);

	/**
	 * 楼层上升
	 */
	void floorUp();

	/**
	 *重置
	 */
	void reset();

	/**
	 * 闯阵奖励
	 * @return
	 */
	ChuangZhenReward getChuangZhenReward();

	/**
	 * 主界面 力战奋战血战 3个头像
	 * @return
	 */
	ChuangZhenHeads getHeads();

	/**
	 * 玩家等级
	 * @return
	 */
	int getLevel();

	/**
	 * 最大上阵人数
	 * @return
	 */
	int getFormationCount();

}
