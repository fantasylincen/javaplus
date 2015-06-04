package cn.mxz.base.task;

import db.domain.TaskDTO;


/**
 * 一般任务, 每次任务达成, 任务完成次数 +1
 * @author 	林岑
 * @since	2012-1-18
 */
public abstract class NormalTask<T extends TaskDTO> extends TaskBase<T> {

	@Override
	protected final int getFinishTimesThisTime() {
		return 1;
	}

	@Override
	protected abstract boolean isAchievement();
}
