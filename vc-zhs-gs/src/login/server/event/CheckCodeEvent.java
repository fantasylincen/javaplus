package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import define.SystemCfg;

import server.ServerManager;
import user.UserInfo;
import user.UserManager;
import user.UserStatus;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import game.award.ectype.EctypeAward;
import game.events.Event;
import game.events.all.user.UserLoginEvent;
import game.log.Logs;
import game.log.L;

/**
 * 核对验证码
 * @author DXF
 *
 */
public class CheckCodeEvent extends ILEvent{

	@Override
	public void run( UserInfo x, ByteBuffer buf ) throws IOException {
		
		byte cc 				= buf.get();
		
		int index				= buf.getInt();
		String identifying 		= UtilBase.decodeString(buf);
		UserInfo user 			= UserManager.getInstance().getByName( index );
		
		EctypeAward	readyAwards	= null;
		ErrorCode code 			= cc == 0 ? ErrorCode.SUCCESS : ErrorCode.USER_NOT_CODE;
		
		// 表示已经验证了  
		if( cc == -1 ){
			Logs.error( user, "还没有登陆过登陆服! " );
		}
				
		if( code == ErrorCode.SUCCESS ){
			
			if( user != null ){
				
				// 这里看 是否断线前在副本中
				readyAwards 	= user.getEctypeReconnectManager().getReadyAwards();
				
				if( readyAwards != null )
					code = ErrorCode.FIGHTER_STATUS;
				
				// 获得 终端标识 为了记录日志
				Logs.log( L.L_002 , 
						user.getUID() + "," +
						user.getNickName() + "," +
						identifying + "," + user.isHaveMonthCard() 
						);
				
				// 记录登陆时间   不用保存数据库
				user.setLoginTime( SystemTimer.currentTimeSecond() );
				
				user.setStatus( UserStatus.LOGIN );
				
				Logs.debug( user, "登陆成功！" );
			}else{
				code = ErrorCode.USER_INVALID_LOGIN;
			}
		}
		
		// 这里应该是不会为NULL的
		if( user == null || user.getCon() == null ) 
		{
			Logs.error( user, "用户登陆错误 con=NULL" );
			return;
		}
		
		// 然后发送登陆信息
		UserLoginEvent xxx = (UserLoginEvent)Event.USER_LOGIN.getEventInstance();
		xxx.run( user, code );
		
		// 记录IP
		if( code == ErrorCode.SUCCESS )
			UserManager.getInstance().addIPRecord( user.getCon().getRemoteAddress().getHostAddress(), user.getNickName() );
	}
	
	
	public void run( int index, String code ) throws IOException {
		ByteBuffer buffer = buildEmptyPackage( 256 );
		buffer.put( SystemCfg.GAME_DISTRICT );
		buffer.putInt( index );
		UtilBase.encodeString( buffer, code );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}
	
}
