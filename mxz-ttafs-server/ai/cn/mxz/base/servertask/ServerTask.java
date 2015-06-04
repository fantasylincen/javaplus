package cn.mxz.base.servertask;

import cn.mxz.events.EventDispatcher2;

/**
 * 服务器任务
 * @author 林岑
 */
public interface ServerTask extends EventDispatcher2 {

	/**
	 * 启动任务
	 */
	void start();

}
