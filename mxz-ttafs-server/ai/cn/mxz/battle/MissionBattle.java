package cn.mxz.battle;

import cn.mxz.mission.type.MissionPrizeReceiver;



/**
 * 副本战场
 * @author 	林岑
 * @since	2013年6月6日 17:19:30
 *
 */
public interface MissionBattle extends BattlePrizeAble, MissionPrizeReceiver {

	/**
	 * 战斗后获得的星星数量
	 * @return
	 */
	int getStar();

	boolean isMain();

	boolean isBoss();

	/**
	 * 地图ID
	 * @return
	 */
	int getMapId();

	int getHpStart();
}
