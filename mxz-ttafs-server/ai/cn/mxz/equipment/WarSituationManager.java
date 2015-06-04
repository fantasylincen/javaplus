package cn.mxz.equipment;

import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;

interface WarSituationManager {

	/**
	 * 保存战况信息
	 * @param situation
	 */
	int save(WarSituationPro situation);

}
