package cn.mxz.base.handler;



import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.lemon.commons.socket.ISocket;

public class MinaDataHandler extends AbstractDataHandler {

	private MinaPacketDispatcher dispatcher;

	public MinaDataHandler() {
		dispatcher = new MinaPacketDispatcher();
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {

		ISocket socket = new SessionSocket(session);

		onClosed(socket);

		dispatcher.clear(socket.getId());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

		boolean equals = "Don't know how to handle message of type 'java.nio.HeapByteBuffer'.  Are you missing a protocol encoder?".equals(cause.getMessage());

		if(!equals) {

			cause.printStackTrace();
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		ISocket socket = new SessionSocket(session);

		byte[] data = getDataFull(message);

		dispatcher.push(socket.getId(), data);	//将包加入到分发器

//		System.out.println("");
		data = dispatcher.poll(socket.getId());	//取出一个完整的数据包
		ByteBuffer buf = ByteBuffer.wrap( data );
		System.out.println( "xxxx" + buf );
		if( check( socket, buf ) ){
			dispatcher.clear( socket.getId() );
		}
//		else{
//
//		}

//		if(data != null) {
//			onData(socket, data);
//		}
	}

	private byte[] getDataFull(Object message) {
		IoBuffer buffer = (IoBuffer) message;

		byte[] data = new byte[buffer.limit()];

		buffer.get(data);

//		data = Arrays.copyOfRange(data, 0, data.length);
//		[116, 0, 35, 0, 3, 52, 80, 0, 5, 108, 99, 56, 48, 53, 0, 6, 50, 50, 50, 50, 50, 50, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 127
		return data;
	}

	boolean check(ISocket socket, ByteBuffer buf ){
		byte head= 0;
		try{
			head = buf.get();
			short len = buf.getShort();
			byte[] data = new byte[len];
			buf.get(data, 0, len);
			byte foot = buf.get();

			onData(socket, data);
//
		}catch (BufferOverflowException e ){
			System.out.println( "处理毡包" );
			return false;
		}catch (BufferUnderflowException e ){
			System.out.println( "处理毡包" );
			return false;
		}


		return true;
	}
}
