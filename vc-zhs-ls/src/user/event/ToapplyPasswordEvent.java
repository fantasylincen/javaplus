package user.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import user.UserManager;
import util.UtilBase;

import email.SendMail;
import events.EventBase;
import events.EventDescrip;


@EventDescrip(desc = "申请密码" )
public class ToapplyPasswordEvent extends EventBase{

	@Override
	public void run(INonBlockingConnection con, String... args)
			throws IOException {
		
	}

	@Override
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		String account  	= UtilBase.decodeString(buf);
		String mail_address	= UtilBase.decodeString(buf);
	
		String password		= UserManager.getInstance().toapplyPassword( account, mail_address );
		
		ByteBuffer buffer 	= buildEmptyPackage( 11 );
		buffer.put( (password.length() > 0 ? 0 : Byte.parseByte( password )) );
		sendPackage( con, buffer );
		
		// 这里如果成功 那么就
		if( password.length() > 0 ){
			SendMail.sendPasswordMail( mail_address, password );
		}
		
		
	}

}
