package cn.javaplus.commons.event;

import java.io.IOException;

import cn.javaplus.commons.socket.MSocket;



public interface IConnectHandler extends IHandler {
	public boolean onConnectionOpening(MSocket connection) throws IOException;	
}
