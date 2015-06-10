package user.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import log.CLog;
import nd.Sdk_91;
import nd.Sdk_tb;

import org.xsocket.connection.INonBlockingConnection;

import ppsdk.PPSdk;

import com.dominicsayers.isemail.dns.DNSLookupException;

import dzsdk.DZSdk;
import user.UserManager;
import util.UtilBase;
import util.md5.MD5;
import email.SendMail;
import events.EventBase;
import events.EventDescrip;

@EventDescrip(desc = "用户  请求KEY包" )
public class UserLoginEvent extends EventBase {

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		byte type			= buf.get();
		
		ByteBuffer buffer 	= buildEmptyPackage( 1024 );
		buffer.put( type );
		
		int index			= -1;
		String identifying	= "";
		
		byte code 			= 0;
		String key			= "";
//		System.out.println( con + "  登陆 type=" + type );
		switch( type )
		{
		case 1:// 无账号用终端标识登录获取KEY
		{
			identifying 	= UtilBase.decodeString(buf);// 终端标识
			
			if( identifying.isEmpty() ) {
				CLog.error("请求KEY错误  终端标识为NULL");
				return;
			}
			
			index 			= UserManager.getInstance().getIndex( identifying );
			
			if( index == -1 ) {
				CLog.error("请求KEY错误   index == -1");
				code		= -1;
			}
			
			key				= "1";
		}
		break;
			
		case 2:// 有账号用帐号登录获取KEY
		{
			String account 	= UtilBase.decodeString(buf);// 账号
			String password = UtilBase.decodeString(buf);// 密码
			
			index 			= UserManager.getInstance().getIndex( account, password );
			
			if( index == -1 ) 
				CLog.error("请求KEY 账号不存在  account=" + account );
			if( index == -2 )
				CLog.error("请求KEY 密码错误  password=" + password );
			
			if( index < 0 )
				code		= (byte) index;
			
			key				= "2";
		}
		break;
		
		case 3: // 91 平台
		{
			// 91账号ID
			String accountID 	= UtilBase.decodeString(buf);
			// SessionID
			String sessionID	= UtilBase.decodeString(buf);
			
			if( accountID.isEmpty() || sessionID.isEmpty() ){
				CLog.error( "登陆错误  accountID=" + accountID + "  sessionID=" + sessionID );
				return;
			}
			
			try {
				index 			= Sdk_91.checkUserLogin( accountID, sessionID );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if( index != 1 ) index = -1;
			else
				index 			=  UserManager.getInstance().getIndex( "91_" + accountID );
			
			if( index == -1 ) 
				CLog.error("请求KEY错误   index == -1");
			
			if( index < 0 )
				code		= (byte) index;
			
			key				= "2";
		}
		break;
		case 4: // 同步 平台
		{
			// 同步账号ID
			String accountID 	= UtilBase.decodeString(buf);
			// SessionID
			String sessionID	= UtilBase.decodeString(buf);
			
			if( accountID.isEmpty() || sessionID.isEmpty() ){
				CLog.error( "登陆错误  accountID=" + accountID + "  sessionID=" + sessionID );
				return;
			}
			
			try {
				index 			= Sdk_tb.checkUserLogin( sessionID );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if( Integer.parseInt(accountID) == index && index > 1  )
				index 			=  UserManager.getInstance().getIndex( "tb_" + accountID );
			else
				index			= -1;
			
			if( index == -1 ) 
				CLog.error("请求KEY错误   index == -1");
			
			if( index < 0 )
				code		= (byte) index;
			
			key				= "2";
		}
		break;
		
		case 5: // 安卓 平台
		{
			String account 	= UtilBase.decodeString(buf);// 账号
			String password = UtilBase.decodeString(buf);// 密码
			
			index 			= UserManager.getInstance().getIndex( account, password );
			
			if( index == -1 ) 
				CLog.error("请求KEY 账号不存在  account=" + account );
			if( index == -2 )
				CLog.error("请求KEY 密码错误  password=" + password );
			
			if( index < 0 )
				code		= (byte) index;
			
			key				= "2";
		}
		break;
		
		case 6: // 安卓 平台  注册
		{
			String account  	= UtilBase.decodeString(buf);
			String pass  		= UtilBase.decodeString(buf);
			String mail_address	= UtilBase.decodeString(buf);
			
			int len 	= (account.getBytes().length + account.length())/2;
			int len1 	= (pass.getBytes().length + pass.length())/2;
			
			int code1	= 0;
			
			do{ 
				// 检查长度
				if( len < 6 || len > 12 || len1 < 6 || len1 > 12 ){ code1 = 3;  break; }
				// 检查邮件地址 是否合法
				if( !chekMailAddress( mail_address ) ){ code1 = 2;  break; }
				
				code1	= UserManager.getInstance().register( account, pass, mail_address );
				
			}while(false);
			
			buffer.put( (byte) (code1 > 3 ? 0 : code1)  );
			if( code1 > 3 ){
				// 生成验证码
//				String key	= MD5.md5( SystemTimer.currentTimeMillis() + code1 + "vc2013" );
//				String key	= "";
				
				// 记录验证码
//				ServerManager.getInstance().addKey( code1, key, identifying );
				
				key				= "2";
				
				buffer.putInt( code1 );
				UtilBase.encodeString( buffer, key );
				
				// 表示登陆成功
//				UserManager.getInstance().login( con, code1 );
			}
			sendPackage( con, buffer );
			return ;
		}
		
		case 7:// 找回密码
		{
			String account  	= UtilBase.decodeString(buf);
			String mail_address	= UtilBase.decodeString(buf);
			String password		= UserManager.getInstance().toapplyPassword( account, mail_address );
			
			buffer.put( (password.length() > 1 ? 0 : Byte.parseByte( password )) );
			sendPackage( con, buffer );
			
			// 这里如果成功 那么就
			if( password.length() > 1 )
				SendMail.sendPasswordMail( mail_address, password );
			return;
		}
		case 8:
		{
			// 根据当前毫秒生成一个唯一码
			String UDID = getUDID();
			UtilBase.encodeString( buffer, UDID );
			sendPackage( con, buffer );
			return;
		}
		case 9:// PP助手
		{
			// sid验证码
			String sid 	= UtilBase.decodeString(buf);
			
			if( sid.isEmpty() ){
				CLog.error( "登陆错误  sid=" + sid );
				code 	= 1;
			}else{
				
				try {
					index 			= PPSdk.checkUserLogin( sid );
					code			= 0;
				} catch (Exception e) {
					code			= e.getMessage().matches("[0-9]\\d*") ? Byte.parseByte( e.getMessage() ) : 4;
				}
				
				key				= "2";
			}
		}
		break;
		case 10:// 定制
		{
			// 玩家账号
			String accName 	= UtilBase.decodeString(buf);
			
			if( accName.isEmpty() ){
				CLog.error( "登陆错误  accName=" + accName );
				code 	= 1;
			}else{
				
				try {
					index 			= DZSdk.checkUserLogin( accName );
					code			= 0;
				} catch (Exception e) {
					code			= e.getMessage().matches("[0-9]\\d*") ? Byte.parseByte( e.getMessage() ) : 4;
				}
				
				key				= "2";
			}
		}
		break;
		}
		
//		byte code 		= UserManager.getInstance().examine( index );
		
		if( type == 2 || type == 9 || type == 10 )
			buffer.put( code );
		if( code == 0 ){
			
			// 生成验证码
//			String key	= MD5.md5( SystemTimer.currentTimeMillis() + index + "vc2013" );
			
			
			// 记录验证码
//			ServerManager.getInstance().addKey( index, key, identifying );
			
			buffer.putInt( index );
			UtilBase.encodeString( buffer, key );
			
			// 表示登陆成功
//			UserManager.getInstance().login( con, index );
		}
		
		// 有账号 只是没有激活
		if( code == -3 ){
			buffer.putInt( index );
			// 表示登陆成功
//			UserManager.getInstance().login( con, index );
		}
		
		sendPackage( con, buffer );
		
	}

	private String getUDID() {
		long t = System.nanoTime();
		return MD5.md5( t + "vc" );
	}

	private boolean chekMailAddress(String mail_address) {
		try {
			return SendMail.checkEmail( mail_address );
		} catch (DNSLookupException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		
	}
	
}
