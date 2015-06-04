package cn.javaplus.task;

/**
 * 服务器任务中心ID配置
 *
 * @author 林岑
 * @since 2012年11月16日 17:22:15
 *
 */
public enum TaskCenterType {

	RANKING_LIST, //
	NORMAL_TASK_A, //
	NORMAL_TASK_B,
	NORMAL_TASK_C, //
	TIMED_TASK_TASK, //
	CHONG_ZHI, // 充值任务线程
	DB_ALWAYS_OPERATION, // 数据库防休眠
	ONLINE_SIZE_TASK, // 实时在线人数
	ANTI_TASK, // 防沉迷
	CONFIG_RELOAD_TASK, // 配置重新读取任务
	BOSS_TIMER, 
	NORMAL_TASK_D,
}
