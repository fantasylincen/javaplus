package user.net;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.IIdleTimeoutHandler;
import org.xsocket.connection.INonBlockingConnection;

import user.UserManager;
import util.ErrorCode;

import events.Event;
import events.EventBase;


/**
 * @author DXF
 */
public class UserHandler implements IDataHandler, IConnectHandler,IIdleTimeoutHandler, IDisconnectHandler  {

	Logger logger = LoggerFactory.getLogger( UserHandler.class);
	
	
	/**
	 * 除去包头包尾允许接收的最大的包长度
	 */
	private static final int    PACKAGE_LEN = 4096;
		
	
	@Override
	public boolean onDisconnect(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		userExit( con );
		return true;
	}

	@Override
	public boolean onIdleTimeout(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		con.available();// 避免findbugs的警告
		return true;
	}

	@Override
	public boolean onConnect(INonBlockingConnection con) throws IOException, BufferUnderflowException, MaxReadSizeExceededException {
		
		con = ConnectionUtils.synchronizedConnection(con);
		
//		CLog.debug(  "user-[" + con.getRemoteAddress().getHostAddress() + "]-Connect！" );
		
		// 直接发送 当前服务器列表 
//		synchronized (con) {
//			Event.SEND_SERVER_LIST.run( con );
			
			// 发送了 就主动断开
//			con.close();
//		}
		
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
	
		byte head 			= 0;
		byte foot	 		= 0;
		short packageNo 	= 0;
		short dataLength 	= 0;
		
		byte data[] 		= null;
		con 				= ConnectionUtils.synchronizedConnection( con );
		
		synchronized (con) {
			int available = con.available();
	
			if (available > 0) {
//				System.out.println(con.getId() + " onData" + ",available:" + available + "byte");
				con.markReadPosition();
				try {
					head 		= con.readByte(); 	// 包头
					packageNo 	= con.readShort(); 	// 报号
					dataLength 	= con.readShort();	// 包长
					if (dataLength < 0 || dataLength > PACKAGE_LEN) {
//	                    CLog.error( MessageFormat.format("user：{0}网络错误, dataLength={1}, available={2}", con.getRemoteAddress(), dataLength, available) );
						con.close();
						return true;
					}
					data = con.readBytesByLength(dataLength);
					foot = con.readByte(); //包尾
					con.removeReadMark();
	
				} catch (BufferUnderflowException bue) {
					con.resetToReadMark();
					return true;
				}
				// 检查包尾包头 是否正确
				if (!checkInputData(head, foot)) {
					con.close();
					return true;
				}
	
				packageRun( con, packageNo, data );
			}
		}

		return true;
	}

	/**
	 * 检测客户端所发送的首尾标识位是否正确
	 * @param head
	 * @param foot
	 * @return true 首尾包号正确 false 错误
	 */
	private boolean checkInputData( byte head, byte foot ) {
		return head == EventBase.HEAD && foot == EventBase.FOOT;
	}
	
	// 解析包
	private void packageRun(INonBlockingConnection con, short eventNo, byte[] data) {
		
		Event event 	= Event.fromNum( eventNo );

		ErrorCode code 	= ErrorCode.SUCCESS;

		int name 		= con.getAttachment() == null ? 0 : (Integer)con.getAttachment();;
		
//		CLog.debug( buildPrefixStr( con, name ) + "通信包：" + buildPrefixEventStr( event ) );
		
		if( event == null ) {
			code = ErrorCode.PACKAGE_NOT_FOUND;
		} else {
			try{
				ByteBuffer buf = ByteBuffer.wrap( data );
				event.run( con, buf );
			}
			catch( Exception e ){
				logger.debug( buildPrefixStr( con, name ) + "包执行异常：" , e );
			}
		}

		if (code != ErrorCode.SUCCESS) {
			logger.debug( buildPrefixStr( con, name ) + "错误码:[" + code + "] 包:" + event + "[" + eventNo + "] " + name );
		}
	}
	
	// 用户主动退出
	private void userExit(INonBlockingConnection con) throws IOException {
		
//		CLog.debug( "user-[" + con.getRemoteAddress().getHostAddress() + "]" + "-Disconnect！" );
		
		int name = con.getAttachment() == null ? 0 : (Integer)con.getAttachment();

		if( name != 0 ){
			
			ErrorCode code = UserManager.getInstance().exit( name );
			if (code != ErrorCode.SUCCESS) {
				logger.debug( "用户退出发生错误：" + name + "[" + con.getId() + "], 错误码:" + code );
			}
		}
	}
	
	
	
	/** 针对此类，提供一个统一的提示信息前缀 */
	private String buildPrefixStr( INonBlockingConnection con, int name ){
		String s = name + "";
		s += "[";
		s += con.getRemoteAddress();
		s += "]  ";
		return s;
	}
}
