package cn.javaplus.base.server;


/**
 * 游戏逻辑服务器
 */
public interface Server  {

	/**
	 * 启动该服务器
	 */
	void start();

	/**
	 * 停止该服务器
	 */
	void stop();

	/**
	 * 是否正在运行
	 * @return
	 */
	boolean isRunning();
	
}
