package experiment;

import game.events.EventBase;
import game.events.Event;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import util.UtilBase;

/**
 * 
 * @author admin
 * 2012-8-21 上午09:06:13
 */
public class SendPackage {
	
	ByteBuffer login(){
		String name = "bbzs";
		ByteBuffer data = ByteBuffer.allocate( 10 );
		UtilBase.encodeString(data, name );
		data.putInt(2);
		data.flip();
		return data;
	}
	
	ByteBuffer createContent( short packageNo ){
		ByteBuffer pack = null;
		ByteBuffer data = null;
		switch( packageNo ){
		case 201:
			data = login();
			break;
        default:
            break;
		}
		if( data == null ){
			return null;
		}
		pack = ByteBuffer.allocate( 6 + data.limit() );//6 for 1(HEAD) + 2(PACKAGE NO) + 2(PACKAGE LEN) + 1(FOOT) = 6
		
		pack.put( EventBase.HEAD );
		pack.putShort( packageNo );
		pack.putShort( (short) data.limit() );
		pack.put( data );
		pack.put( EventBase.FOOT );
		pack.flip();
		
		return pack;
	}
	void sendPackage( short packageNo ){
		try {
			
			Socket socket = new Socket();
			InetSocketAddress addr = new InetSocketAddress( "127.0.0.1", 8000 );
			socket.connect( addr );
				
			ByteBuffer buf = createContent(packageNo);
//			
			socket.getOutputStream().write( buf.array() );

//			socket.getOutputStream().flush();
			Thread.sleep( 100000 );
			System.out.println( buf.limit() );
//			socket.getOutputStream().write( buf.array() );
//			byte[] b = new byte[100];
//			System.out.println( socket.getInputStream().read(b) + "字节" );
//			System.out.println( (char)b[7] +"" + (char)b[7]+"" + (char)b[7]);
//			
			socket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
}

	public static void main ( String[] args ) {
		for( int i = 0; i < 1; i++ ){
			new SendPackage().sendPackage( (short) Event.USER_LOGIN.toNum() );
		}
	}
}
