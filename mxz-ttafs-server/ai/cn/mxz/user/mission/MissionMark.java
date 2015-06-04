package cn.mxz.user.mission;

/**
 * 副本标记
 * @author 林岑
 *
 */
interface MissionMark {

	/**
	 * 地图ID
	 */
	
	int getMapId();

	/**
	 * 副本ID
	 * @return
	 */
	
	int getCopyId();

	/**
	 * 关卡ID
	 * @return
	 */
	
	int getMissionId();
}
