package cn.mxz.user;

import cn.mxz.handler.UserService;

import com.google.protobuf.AbstractMessage;

public interface ServiceManager {

	UserService getUserService();

	/**
	 * @param packet	包号 参考PacketDefine里面的常量
	 * @param message	返回的消息
	 */
	void send(int packet, AbstractMessage message);

}
