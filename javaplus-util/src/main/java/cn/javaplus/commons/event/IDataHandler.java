package cn.javaplus.commons.event;

import java.io.IOException;

import cn.javaplus.commons.socket.MySocket;


public interface IDataHandler extends IHandler {

	public void onData(MySocket connection) throws IOException;

	public void onDestroy(MySocket connection) throws IOException;
}
