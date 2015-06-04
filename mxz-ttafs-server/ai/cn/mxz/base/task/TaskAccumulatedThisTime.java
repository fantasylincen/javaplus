package cn.mxz.base.task;

import db.domain.TaskDTO;


/**
 * 累积任务(本次累积: 比如這一次打到了3個材料,  而與 TaskAccumulated的區別就是,  TaskAccumulated是統計背包裏面當前所有材料有多少個. )
 * @author 	林岑
 * @since	2012-1-18
 */
abstract class TaskAccumulatedThisTime<T extends TaskDTO> extends TaskAccumulated<T> {

	@Override
	protected final boolean isAchievement() {
		return getFinishTimesThisTime() > 0;
	}

}
