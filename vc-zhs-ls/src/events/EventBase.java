package events;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

/**
 * 所有通信包的基类
 * @author DXF
 *
 */
public abstract class EventBase implements IEvent {

	public static final byte	HEAD		= 127; //包头
	public static final byte 	FOOT		= 126; //包尾

	private short 				packageNo; //报号

	/**
	 * 运行包事件的处理函数   发
	 * @param user
	 * @param buf
	 * @throws IOException
	 */
	public abstract void run( INonBlockingConnection con, String ... args  ) throws IOException; 
	
	/**
	 * 运行包事件的处理函数   收
	 * @param user
	 * @param buf
	 * @throws IOException
	 */
	public abstract void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException; 
	
	@Override
	public short getEventId() {
		return packageNo;
	}
	public void setEventId( short id ){
		this.packageNo = id;
	}
	
	
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
	 * @param buff 包括包头(byte)，包号(short)，包长(short)，包内容(byte[])
	 * @throws IOException 
	 * @注意	由于设置了setFlushmode( FlushMode.ASYNC );，所以，后续程序不得在对此buffer进行任何包括读取在内的操作！！！！！！
	 */
	public void sendPackage( INonBlockingConnection con, ByteBuffer buffer ) throws IOException{
		buffer.putShort( 3, (short) (buffer.position() - 5) );//设置内容长度
		buffer.put( FOOT );
		buffer.flip();
		
		if( con == null || !con.isOpen() ) {
//			System.out.println( "出现发送的时候 已经为null" );
			return;
		}
		
		con.write( buffer );
		//由于设置了setFlushmode( FlushMode.ASYNC );，所以，后续程序不得在对buffer进行任何包括读取在内的操作！！！！！！
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
	
	
}
