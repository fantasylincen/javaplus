package game.events;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;




public interface IEvent {

	/**
	 * 运行包事件的处理函数
	 * @param user
	 * @param buf
	 * @throws IOException
	 */
	public void run( UserInfo user, ByteBuffer buf ) throws IOException; 

	/**
	 * 获取事件id
	 * @return
	 */
	short getEventId ();
}
