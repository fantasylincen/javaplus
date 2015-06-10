package user.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import com.dominicsayers.isemail.dns.DNSLookupException;

import user.UserManager;
import util.UtilBase;

import email.SendMail;
import events.EventBase;
import events.EventDescrip;

@EventDescrip(desc = "账号绑定" )
public class AccounBindEvent extends EventBase{

	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		
	}

	@Override
	public void run(INonBlockingConnection con, ByteBuffer buf) throws IOException {
		int uid				= buf.getInt();
		String account  	= UtilBase.decodeString(buf);
		String pass  		= UtilBase.decodeString(buf);
		String mail_address	= UtilBase.decodeString(buf);
		
		byte code			= 0;
		int len 			= (account.getBytes().length + account.length())/2;
		int len1 			= (pass.getBytes().length + pass.length())/2;

		do{
			
			// 检查长度
			if( len < 6 || len > 12 || len1 < 6 || len1 > 12 ){ code = 4;  break; }
			// 检查邮件地址 是否合法
			if( !chekMailAddress( mail_address ) ){ code = 3;  break; }
			
			code			= UserManager.getInstance().bindAccount( uid, account, pass, mail_address );
			
		}while(false);
		
		ByteBuffer buffer 	= buildEmptyPackage( 125 );
		buffer.put( code );
		sendPackage( con, buffer );
		
	}

	private boolean chekMailAddress( String mail_address ) {
		try {
			return SendMail.checkEmail( mail_address );
		} catch (DNSLookupException e) {
			e.printStackTrace();
		}
		return false;
	}

}
