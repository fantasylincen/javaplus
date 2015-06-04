package cn.mxz.base.task;

import db.domain.TaskDTO;

/**
 * 累积任务
 * @author 	林岑
 * @since	2012-1-25
 */
abstract class TaskAccumulated<T extends TaskDTO> extends TaskBase<T> {

	/**
	 * 是否是才接的任务
	 * @return
	 */
	
	protected boolean isNew() {
		return getPassed() == 0;
	}
}
