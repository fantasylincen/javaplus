package login.server.net;

import game.events.EventBase;
import game.log.Logs;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;
import java.text.MessageFormat;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;


import core.GameMainLogic;

/**
 * 连接登陆服  数据的处理类 
 * @author DXF
 *
 */
public class LoginHandler implements IDataHandler ,IConnectHandler ,IDisconnectHandler  {

	/**
	 * 除去包头包尾允许接收的最大的包长度
	 */
	private static final int    PACKAGE_LEN = 4096;
	
	private final GameMainLogic gameLogic 	= GameMainLogic.getInstance();
	
	
	@Override
	public boolean onDisconnect(INonBlockingConnection con ) throws IOException {
		
		Logs.error("loginServer address：" + con.getRemoteAddress().getHostAddress() + "  -disconnect!\n");
		
		return false;
	}

	@Override
	public boolean onConnect(INonBlockingConnection con ) throws IOException, BufferUnderflowException, MaxReadSizeExceededException {
		
		Logs.debug("loginServer address：" + con.getRemoteAddress().getHostAddress() + "  -connected succeed!\n");
		
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con ) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
		
		byte head 			= 0;
		byte foot	 		= 0;
		short packageNo 	= 0;
		short dataLength 	= 0;
		byte data[] 		= null;
		con 				= ConnectionUtils.synchronizedConnection( con );
		// synchronized (con) {
		int available = con.available();

		if (available > 0) {
//			System.out.println(con.getId() + " onData" + ",available:" + available + "byte");
			con.markReadPosition();
			try {
				head = con.readByte();
				packageNo = con.readShort(); 
				dataLength = con.readShort();
				if (dataLength < 0 || dataLength > PACKAGE_LEN) {
                    Logs.error( MessageFormat.format("{0}网络错误，dataLength = {1}", con.getRemoteAddress(), dataLength));
					con.close();
					return true;
				}
				data = con.readBytesByLength(dataLength);
				foot = con.readByte();
				con.removeReadMark();

			} catch (BufferUnderflowException bue) {
				con.resetToReadMark();
				return true;
			}
			if (!checkInputData(head, foot)) {
				con.close();
				return true;
			}

			gameLogic.loginPakRun( con, packageNo, data );
		}

		return true;
	}
	
	/**
	 * 检测客户端所发送的首尾标识位是否正确
	 * 
	 * @param head
	 * @param foot
	 * @return true 首尾包号正确 false 错误
	 */
	private boolean checkInputData( byte head, byte foot ) {
		return head == EventBase.HEAD && foot == EventBase.FOOT;
	}
	
}
