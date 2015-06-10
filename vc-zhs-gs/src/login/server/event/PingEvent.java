package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import define.SystemCfg;

import server.LoginManager;
import server.ServerManager;
import user.UserInfo;
import user.UserManager;
import game.log.Logs;

public class PingEvent extends ILEvent{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		int t = buf.getInt();
		
		if( t == -1 ){
			LoginEvent l 	= (LoginEvent)EventList.LOGIN_VERIFICATION.getEventInstance();
			l.run( ServerManager.getLoginCon(), null );
			Logs.debug( "和登陆服ping时出现-1  重新和登陆 登陆服!" );
			return;
		}
		LoginManager.getInstance().setLoginPingTime( t );
		
//		System.out.println( "登陆服 发送时间：" + t );
		
		ByteBuffer buffer = buildEmptyPackage( 32 );
		buffer.putShort( SystemCfg.GAME_DISTRICT );
		buffer.putInt( UserManager.getInstance().getMaps().size() );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}

	public void run() throws IOException{
		ByteBuffer buffer = buildEmptyPackage( 32 );
		buffer.put( SystemCfg.GAME_DISTRICT );
		buffer.putInt( UserManager.getInstance().getMaps().size() );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}
	
}
