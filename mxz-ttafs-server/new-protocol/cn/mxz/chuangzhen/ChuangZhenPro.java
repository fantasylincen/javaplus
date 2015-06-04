package cn.mxz.chuangzhen;

/**
 * 闯阵界面
 * @author 林岑
 *
 */
public interface ChuangZhenPro {

	/**
	 * @return 当前在哪一层
	 */
	int getCurrentFloor();

	/**
	 * @return	最大达到第几层
	 */
	int getMaxFloor();

	/**
	 * @return 当前闯了多少场
	 */
	int getCurrentTimes();

	/**
	 * @return	最多可闯多少场次
	 */
	int getMaxTimes();

	/**
	 * @return 得星
	 */
	int getStar();

	/**
	 * @return	剩余星星数
	 */
	int getRemainStar();

	/**
	 * @return 加成
	 */
	Addition getAddition();

	/**
	 * 再过好多关结算奖励
	 * @return
	 */
	int getRemainFloor();

	/**
	 * @return 历史最高星星
	 */
	int getStarMaxHistory();
}
