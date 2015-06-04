package cn.mxz.base.task;


import java.util.ArrayList;
import java.util.List;

import db.domain.TaskDTO;


/**
 * 即时任务: 收到客户端某个数据包, 并且成功处理后即可完成的任务
 * @author 	林岑
 * @since	2012-1-16
 */

public abstract class TaskRealTime<T extends TaskDTO> extends TaskAccumulatedThisTime<T> {

	private Environment	environment;

	public TaskRealTime() {

		initConditions();
	}

	@Override
	public void updateEnviroment(Environment environment) {

		this.environment = environment;
	}


	@Override
	protected final int getFinishTimesThisTime() {

		for (Integer id : allCondition) {

			if(environment.getPacketId() == id) {

				return 1;
			}
		}

		return 0;
	}

	/**
	 * 初始化所有完成条件
	 */
	protected abstract void initConditions();

	/**
	 *
	 * 添加一个完成条件
	 *
	 * @param packetId 包号:PacketDefine
	 */
	protected void addCondition(int packetId) {

		allCondition.add(packetId);
	}

	/**
	 * 所有需要达成的条件
	 */
	private List<Integer> allCondition = new ArrayList<Integer>();
}
