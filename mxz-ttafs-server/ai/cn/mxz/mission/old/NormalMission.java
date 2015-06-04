package cn.mxz.mission.old;

import cn.mxz.user.mission.Mission;

/**
 * 普通副本
 * @author 林岑
 *
 */

public interface NormalMission extends Mission {

	/**
	 * 最后一个被攻击的关卡
	 * @return
	 */
	int getLastMapIdAttack();

}