package game.events;


import game.log.Logs;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;
import util.ErrorCode;


/**
 * 所有通信包的基类
 * @author deng
 * 2013-6-26
 */
public abstract class EventBase implements IEvent {
	
	//private final static Logger logger = LoggerFactory.getLogger( BasePackage.class ); 
	public static final byte HEAD		= 127;
	public static final byte FOOT		= 126;

	private short packageNo;

	/**
	 * 从客户端收取包并进行逻辑处理
	 * 通常也会返回一个应答包到客户端
	 * @throws IOException 
	 */
	@Override
	public abstract void run( UserInfo user, ByteBuffer buf ) throws IOException;
	
	/**
	 * 创建一个长度为capacity的缓冲包，此包包括包头，包号，包长（占位符）
	 * @param capacity
	 * @return
	 */
	protected ByteBuffer buildEmptyPackage( int capacity ){
		ByteBuffer buff = ByteBuffer.allocate(capacity);
		buff.put( HEAD );
		buff.putShort( getEventId() );
		buff.putShort( (short) 0 );//长度占位符
		return buff;
	}
	
	/**
	 * 向客户端发送包
	 * @param user
	 * @param buff
	 * 		包括包头(byte)，包号(short)，包长(short)，包内容(byte[])

	 * @throws IOException 
	 * 
	 * @注意	由于设置了setFlushmode( FlushMode.ASYNC );，所以，后续程序不得在对此buffer进行任何包括读取在内的操作！！！！！！
	 */
	public void sendPackage( INonBlockingConnection con, ByteBuffer buffer ) throws IOException{
		buffer.putShort( 3, (short) (buffer.position() - 5) );//设置内容长度
		buffer.put( FOOT );
		buffer.flip();
		
		if( con == null || !con.isOpen() ){
			Logs.error( "发包时出错  con=null！ （可能原因服务端还没发包时客户端就已经断开连接了）" );
		}else{
			con.write( buffer );
		}
	}
	
	/**
	 * 处理仅仅回送一个ErrorCode的情况
	 * @param conn
	 * @param code
	 * @throws IOException
	 */
	public void sendErrorCode( INonBlockingConnection con, ErrorCode code ) throws IOException{
		ByteBuffer buffer = buildEmptyPackage( 8 );
		buffer.putShort( (short) code.ordinal() );			//错误代码
		sendPackage( con, buffer );
	}
	
	/**

	 * 复制此buffer，然后进行打印，避免影响原有的ByteBuffer
	 * @param buffer
	 * @return
	 */
	public String toString( ByteBuffer buffer ) {
		ByteBuffer copy = buffer.asReadOnlyBuffer();
		if( copy.position() != 0 ){
			copy.flip();
		}
		String str = "HEAD:" + copy.get() + "\n";;
		str += "包号:" + copy.getShort() + "\n";
		int len =  copy.getShort();
		str += "包长:" + len + "\n";
		byte[] data = new byte[len];
		copy.get( data );
		str += "FOOT:" + copy.get();
		return str;
	}
	@Override
	public short getEventId (){
		return packageNo;
	}
	public void setEventId( short eventId ){
		this.packageNo = eventId;
	}
	
	/**
	 * 测试toString函数
	 * @param args
	 */
	public static void main(String[] args) {
//		ByteBuffer buf = ByteBuffer.allocate( 1024 );
//		buf.put( EventBase.HEAD );
//		buf.putShort( Event.USER_CREATE.toNum() );
//		buf.putShort( (short) 4 );
//		buf.putInt( 88 );
//		buf.put( EventBase.FOOT );
//		
//		UserLoginEvent p = new UserLoginEvent();
//		System.out.println( buf );
//		System.out.println( p.toString( buf ) );
//		
//		System.out.println( buf );
	}

}
