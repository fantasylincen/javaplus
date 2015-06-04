package cn.mxz.base.task;

import db.domain.TaskDTO;

/**
 * 累积任务(全部累积) 直接统计当前的完成情况
 *
 * @author 林岑
 * @since 2012-1-18
 */

public abstract class TaskAccumulatedAll<T extends TaskDTO> extends TaskAccumulated<T> {

	@Override
	protected boolean isAchievement() {
		return true;
	}

	@Override
	public int finishOneTime() {
//		if(getId() == 10409) {
//			Debuger.debug("TaskAccumulatedAll.finishOneTime()");
//		}
		int old = task.getFinishtimes();
		int now = getFinishTimesNow();
		int d = now - old;
		d = Math.max(0, d);
		if (d > 0) {
			task.setFinishtimes(now);
			DAO.save(task);
		}
		return d;
	}

	/**
	 * 当前已经达成的次数
	 *
	 * @return
	 */
	protected abstract int getFinishTimesNow();

	@Override
	protected final int getFinishTimesThisTime() {
		return 0;
	}
}
