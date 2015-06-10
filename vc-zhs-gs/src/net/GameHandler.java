package net;

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
import org.xsocket.connection.IIdleTimeoutHandler;
import org.xsocket.connection.INonBlockingConnection;


import core.GameMainLogic;

/**
 * @author liukun 2012-8-16
 */
public class GameHandler implements IDataHandler, IConnectHandler,
		IIdleTimeoutHandler, IDisconnectHandler {

	/**
	 * 除去包头包尾允许接收的最大的包长度
	 */
	private static final int    PACKAGE_LEN = 16384;
	
	private final GameMainLogic gameLogic = GameMainLogic.getInstance();

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xsocket.connection.IIdleTimeoutHandler#onIdleTimeout(org.xsocket.
	 * connection.INonBlockingConnection)
	 */
	@Override
	public boolean onIdleTimeout(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		con.available();// 避免findbugs的警告
		return false;
		// return true;//不切断连接
	}

	@Override
	public boolean onConnect(INonBlockingConnection con) throws IOException, BufferUnderflowException, MaxReadSizeExceededException {
		
		con = ConnectionUtils.synchronizedConnection(con);
		
		Logs.debug(  "[" + con.getRemoteAddress() + "]-连接成功！" );
		
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException, ClosedChannelException,	MaxReadSizeExceededException {

		byte head 			= 0;
		byte foot 			= 0;
		short packageNo 	= 0;
		short dataLength 	= 0;
		byte data[] 		= null;
		con 				= ConnectionUtils.synchronizedConnection( con );
		
//		int available 		= con.available();

		while ( con.available() > 0 ) {
			con.markReadPosition();
			try {
				head 		= con.readByte();
				packageNo 	= con.readShort(); 
				dataLength 	= con.readShort();
				if (dataLength < 0 || dataLength > PACKAGE_LEN) {
					Logs.error( MessageFormat.format("{0} 网络错误 : dataLength={1}, available={2}, packageNo={3}, attachment={4}", 
                    		con.getRemoteAddress(), dataLength, con.available(), packageNo, con.getAttachment() ) );
					con.close();
					return true;
				}
				data 		= con.readBytesByLength(dataLength);
				foot 		= con.readByte();
				con.removeReadMark();

			} catch (BufferUnderflowException bue) {
				con.resetToReadMark();
				return true;
			}
			if (!checkInputData(head, foot)) {
				Logs.error( MessageFormat.format("{0} 包头包尾错误 : dataLength={1}, available={2}, packageNo={3}, attachment={4}", 
                		con.getRemoteAddress(), dataLength, con.available(), packageNo, con.getAttachment() ) );
				con.close();
				return true;
			}

			gameLogic.packageRun( con, packageNo, data );
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
	
	/**
	 * 主动响应玩家关闭连接的事件
	 */
	@Override
	public boolean onDisconnect(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		//System.out.println(con.getId() + " onDisconnect " + System.nanoTime() );
		gameLogic.userExit( con );
		return false;
	}
}
