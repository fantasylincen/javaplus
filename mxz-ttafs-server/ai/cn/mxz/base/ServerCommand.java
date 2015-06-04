package cn.mxz.base;

/**
 * 系统操作命令(比如:强化装备,  比如:进阶神将.....)
 * @author 林岑
 *
 */
public interface ServerCommand {

	/**
	 * 操作前检查
	 */
	void check();

	/**
	 * 执行该操作
	 */
	void run();

}
