package passback.server;

import game.events.EventBase;
import game.log.Logs;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.text.MessageFormat;

import nd.Sdk_91;
import nd.Sdk_tb;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.IIdleTimeoutHandler;
import org.xsocket.connection.INonBlockingConnection;

import recharge.lb.LbVerifyPurchase;

import define.SystemCfg;

import util.UtilBase;

public class PassBackHandler  implements IDataHandler, IConnectHandler,
IIdleTimeoutHandler, IDisconnectHandler {

	@Override
	public boolean onDisconnect(INonBlockingConnection connection)
			throws IOException {
		
//		System.out.println( connection.getRemoteAddress() + " 连接成功");
		return false;
	}

	@Override
	public boolean onIdleTimeout(INonBlockingConnection connection)
			throws IOException {
		return false;
	}

	@Override
	public boolean onConnect(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			MaxReadSizeExceededException {
//		System.out.println( connection.getRemoteAddress() + " 连接关闭" );
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
		
		byte head 			= 0;
		byte foot 			= 0;
		short packageNo 	= 0;
		short dataLength 	= 0;
		byte data[] 		= null;
		con 				= ConnectionUtils.synchronizedConnection( con );
		
		while ( con.available() > 0 ) {
			con.markReadPosition();
			try {
				head 		= con.readByte();
				packageNo 	= con.readShort(); 
				dataLength 	= con.readShort();
				if (dataLength < 0 || dataLength > 8192) {
                    Logs.error( MessageFormat.format("{0} 网络错误 : dataLength={1}, available={2}, packageNo={3}, attachment={4}", 
                    		con.getRemoteAddress(), dataLength, con.available(), packageNo, con.getAttachment() ) );
					con.close();
					con		= null;
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
				con		= null;
				return true;
			}

			// 如果报号 不正确也返回
			if( packageNo != 1 ){
				Logs.error( MessageFormat.format("{0} 包号错误 : dataLength={1}, available={2}, packageNo={3}, attachment={4}", 
                		con.getRemoteAddress(), dataLength, con.available(), packageNo, con.getAttachment() ) );
				con.close();
				con		= null;
				return true;
			}
			
			onData( con, data );
		}

		return true;
	}

	private void onData( INonBlockingConnection con, byte data[] ) throws IOException{
		ByteBuffer buf 			= ByteBuffer.wrap( data );
		
		String content 			= UtilBase.decodeString(buf);
		
		if( SystemCfg.PLATFORM.equals( "91" ) )
			handle_91( content );
		else if( SystemCfg.PLATFORM.equals( "TB" ) )
			handle_tb( content );
		else if( SystemCfg.PLATFORM.equals( "LB" ) )
			handle_lb( content );
		
		// 回复收到信息了
		ByteBuffer buff = ByteBuffer.allocate(2);
		buff.put( (byte) 49 );
		buff.flip();
		con.write( buff );
		con.flush();
		con.close();
	}
	
	private boolean handle_lb( String content ) throws IOException {
		String list[] = content.split( "&" );
		if( list.length == 0 || list == null || content.isEmpty() )
			return false;
		if( list.length != 12 )
			return false;
		
//		System.out.println( content );
		
		String app		= list[0].split("=")[1];
		String cbi		= list[1].split("=")[1];
		String ct		= list[2].split("=")[1];
		String fee		= list[3].split("=")[1];
		String pt		= list[4].split("=")[1];
		String sdk		= list[5].split("=")[1];
		String ssid		= list[6].split("=")[1];
		String st		= list[7].split("=")[1];
		String tcd		= list[8].split("=")[1];
		String uid		= list[9].split("=")[1];
		String ver		= list[10].split("=")[1];
		String sign		= list[11].split("=")[1];
		
		return LbVerifyPurchase.payResultNotify( content, app,cbi,ct,fee,pt,sdk,ssid,st,tcd,uid,ver,sign );
	}

	private boolean handle_tb( String content ) throws IOException {
		
		String list[]			= content.split( "&" );
		
		if( list.length == 0 || list == null || content.isEmpty() )
			return false;
		
//		System.out.println( content );
//		System.out.println( list.length );
//		System.out.println( list[0].split("=")[1] );
		
		if( list.length != 8 )
			return false;
		
		String source		= list[0].split("=")[1];
		String trade_no		= list[1].split("=")[1];
		String amount		= list[2].split("=")[1];
		String partner		= list[3].split("=")[1];
		String paydes		= list[4].split("=")[1];
		String debug		= list[5].split("=")[1];
		String tborder		= list[6].split("=")[1];
		String Sign			= list[7].split("=")[1];
		
		Sdk_tb.payResultNotify( source, trade_no, amount, partner, paydes, debug, tborder, Sign );
		
//		System.out.println( "接受到数据：" +  "source=" + source + 
//		 " trade_no=" + trade_no + 
//		 " amount=" + amount + 
//		 " partner=" + partner + 
//		 " paydes=" + paydes + 
//		 " debug=" + debug + 
//		 " tborder=" + tborder + 
//		 " Sign=" + Sign );
		
		return true;
	}

	private boolean handle_91( String content ) throws IOException{
		
		String list[]			= content.split( "&" );
		
		if( list.length == 0 || list == null || content.isEmpty() )
			return false;
		
		if( list.length != 15 )
			return false;
		
//		System.out.println( content );
//		System.out.println( list.length );
//		System.out.println( list[0].split("=")[1] );
		
		String Appid			= list[0].split("=")[1]; 
		String Act				= list[1].split("=")[1]; 
		String ProductName		= URLDecoder.decode( list[2].split("=")[1], "UTF-8" ); 
		String ConsumeStreamId	= list[3].split("=")[1]; 
		String CooOrderSerial	= list[4].split("=")[1]; 
		String Uin				= list[5].split("=")[1]; 
		String GoodsId			= list[6].split("=")[1]; 
		String GoodsInfo		= URLDecoder.decode( list[7].split("=")[1], "UTF-8" ); 
		String GoodsCount		= list[8].split("=")[1]; 
		String OriginalMoney	= list[9].split("=")[1]; 
		String OrderMoney		= list[10].split("=")[1]; 
		String Note				= URLDecoder.decode( list[11].split("=")[1], "UTF-8" ); 
		String PayStatus		= list[12].split("=")[1]; 
		String CreateTime		= list[13].split("=")[1]; 
		String Sign				= list[14].split("=")[1]; 
		
		Sdk_91 sdk 				= new Sdk_91();
		sdk.payResultNotify( content, Appid, Act, ProductName, ConsumeStreamId, CooOrderSerial, Uin, GoodsId, GoodsInfo, GoodsCount, OriginalMoney, OrderMoney, Note, PayStatus, CreateTime, Sign );
		
//		System.out.println( "接受到数据：" +  "Appid=" + Appid + 
//				 " Act=" + Act + 
//				 " ProductName=" + ProductName + 
//				 " ConsumeStreamId=" + ConsumeStreamId + 
//				 " CooOrderSerial=" + CooOrderSerial + 
//				 " Uin=" + Uin + 
//				 " GoodsId=" + GoodsId + 
//				 " GoodsInfo=" + GoodsInfo + 
//				 " GoodsCount=" + GoodsCount + 
//				 " OriginalMoney=" + OriginalMoney + 
//				 " OrderMoney=" + OrderMoney + 
//				 " Note=" + Note + 
//				 " PayStatus=" + PayStatus + 
//				 " CreateTime=" + CreateTime + 
//				 " Sign=" + Sign );
		
		return true;
	}
	
	private boolean checkInputData(byte head, byte foot) {
		return head == EventBase.HEAD && foot == EventBase.FOOT;
	}

}
