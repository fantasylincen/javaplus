package cn.mxz.base.task;

import com.lemon.commons.socket.ISocket;

/**
 * 任务环境
 * @author 林岑
 *
 */
public interface Environment {

	/**
	 * 协议ID
	 * @return
	 */
	int getPacketId();

	
	ISocket getSocket();

	/**
	 * 是否需要进行任务检查
	 
	 * @return
	 */
	boolean needCheck();

}
