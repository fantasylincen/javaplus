package cn.mxz.mission.star;

/**
 * 副本星星
 * @author 林岑
 *
 */
public interface MissionStar {

	/**
	 * @return	关卡ID
	 */
	int getMissionId();

	/**
	 * @return	获得的星星数量
	 */
	int getCount();

	/**
	 * @return	最大星星数量
	 */
	int getMax();

}
