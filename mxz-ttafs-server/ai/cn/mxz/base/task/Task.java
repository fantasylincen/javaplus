package cn.mxz.base.task;

import db.domain.TaskDTO;



/**
 * 玩家任务
 * @author 	林岑
 * @since	2012年1月15日 20:04:51
 *
 */
public interface Task<T extends TaskDTO> {

	/**
	 * 任务ID
	 */
	int getId();

	/**
	 * 通过数量
	 */
	int getPassed();

	/**
	 * 任务是否已经完成
	 */
	boolean isFinishAll();

	/**
	 * 完成一次该任务
	 * @return 本次达成次数
	 */
	int finishOneTime();

	/**
	 * 任务条件是否完全达成
	 * @param environment 当前任务所处环境
	 */
	boolean hasBeenCompleted();

	/**
	 * 是否已经领取过奖励
	 * @return
	 */
	boolean isGiveBack();

	/**
	 * 归还任务
	 */
	void giveBack();

	T getDTO();

	/**
	 * 初始化
	 * @param DAO
	 * @param task
	 * @param temp
	 */
	void init(TaskDAO<T> DAO, T task, TaskTemplet temp);

	/**
	 * 更新任务环境
	 * @param e
	 */
	void updateEnviroment(Environment e);

	/**
	 * 所需完成最大次数
	 * @return
	 */
	int getMax();

	/**
	 * 该任务是否可见
	 * @return
	 */
	boolean isVisible();
}
