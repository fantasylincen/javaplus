package server.net;

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

import events.Event;
import events.EventBase;

import server.ServerManager;
import util.ErrorCode;


/**
 * @author DXF
 */
public class GameHandler implements IDataHandler, IConnectHandler,IIdleTimeoutHandler, IDisconnectHandler  {

	Logger logger = LoggerFactory.getLogger( GameHandler.class);
	
	
	/**
	 * 除去包头包尾允许接收的最大的包长度
	 */
	private static final int    PACKAGE_LEN = 4096;
		
	
	@Override
	public boolean onDisconnect(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		con.available();
		ServerManager.getInstance().Exit( con );
		
		return false;
	}

	@Override
	public boolean onIdleTimeout(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		
		con.available();// 避免findbugs的警告
		return false;
	}

	@Override
	public boolean onConnect(INonBlockingConnection con) throws IOException, BufferUnderflowException, MaxReadSizeExceededException {
		
		con 		= ConnectionUtils.synchronizedConnection(con);
		
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
		
		byte head 			= 0;
		byte foot	 		= 0;
		short packageNo 	= 0;
		short dataLength 	= 0;
		
		byte data[] 		= null;
		con = ConnectionUtils.synchronizedConnection( con );
		
//		synchronized (con) {
			int available = con.available();
	
			if (available > 0) {
//				System.out.println(con.getId() + " onData" + ",available:" + available + "byte");
				con.markReadPosition();
				try {
					head 		= con.readByte(); 	// 包头
					packageNo 	= con.readShort(); 	// 报号
					dataLength 	= con.readShort();	// 包长
					if (dataLength < 0 || dataLength > PACKAGE_LEN) {
//						CLog.error( MessageFormat.format("server：{0}网络错误, dataLength={1}, available={2}", con.getRemoteAddress(), dataLength, available) );
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
//		}

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

		byte serverid	= (byte) (con.getAttachment() == null ? 0 : (byte)con.getAttachment());
		
//		CLog.debug( buildPrefixStr( con, name ) + "通信包：" + buildPrefixEventStr( event ) );
		
		if( event == null ) {
			code = ErrorCode.PACKAGE_NOT_FOUND;
		} else {
			try{
				
				if( event == Event.SERVER_LOGIN ){
					if( serverid != 0 )
						return;
				}else{
					if( serverid == 0 )
						return;
				}
				
				ByteBuffer buf = ByteBuffer.wrap( data );
				event.run( con, buf );
			}
			catch( Exception e ){
				logger.debug( buildPrefixStr( con, serverid ) + "包执行异常：" , e );
			}
		}

		if (code != ErrorCode.SUCCESS) {
			logger.debug( buildPrefixStr( con, serverid ) + "错误码:[" + code + "] 包:" + event + "[" + eventNo + "] " + serverid );
		}
	}
	
	/** 针对此类，提供一个统一的提示信息前缀 */
	private String buildPrefixStr( INonBlockingConnection con, int serverid ){
		String s = serverid + "";
		s += "[";
		s += con.getRemoteAddress();
		s += "]  ";
		return s;
	}
	
}
