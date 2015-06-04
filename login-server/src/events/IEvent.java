package events;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;


public interface IEvent {

	/**
	 * 运行包事件的处理函数   发
	 * @param user
	 * @param buf
	 * @throws IOException
	 */
	public void run( INonBlockingConnection con, String ... args  ) throws IOException; 
	
	/**
	 * 运行包事件的处理函数   收
	 * @param user
	 * @param buf
	 * @throws IOException
	 */
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException; 

	/**
	 * 获取事件id
	 * @return
	 */
	short getEventId ();
	
}
