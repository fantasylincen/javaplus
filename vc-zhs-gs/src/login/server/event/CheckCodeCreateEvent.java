package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import config.saward.SAwardType;

import define.SystemCfg;

import server.ServerManager;
import user.UserInfo;
import user.UserManager;
import user.UserStatus;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import game.events.Event;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.events.all.user.UserCreateEvent;
import game.log.Logs;
import game.log.L;

/**
 * 核对创建角色的时候 的验证码
 * @author DXF
 */
public class CheckCodeCreateEvent extends ILEvent{

	@Override
	public void run(UserInfo x, ByteBuffer buf) throws IOException {
	
		byte cc 				= buf.get();
		
		// 表示已经验证了  
		if( cc == -1 ){
			Logs.debug( "已经和登陆服验证了 " );
		}
		
		int index				= buf.getInt();
		String identifying 		= UtilBase.decodeString(buf);
		UserInfo user 			= UserManager.getInstance().getByName( index );
		
		ErrorCode code 			= cc == 0 ? ErrorCode.SUCCESS : ErrorCode.USER_NOT_CODE;
		
		if( code == ErrorCode.SUCCESS ){
			
			do{
				if( user == null ) {
					code = ErrorCode.USER_INVALID_LOGIN;
					break;
				}
				
				code 		= UserManager.getInstance().create( null, user, 1 );
				if( code != ErrorCode.SUCCESS ) break;
			
				// 记录登陆时间   不用保存数据库
				user.setLoginTime( SystemTimer.currentTimeSecond() );
				
				user.setStatus( UserStatus.LOGIN );
				
				// 记录日志
				Logs.log( L.L_001 , 
						user.getUID() + "," +
						user.getNickName() + "," + identifying
						);
				
				// 记录每日登陆
				int value 		= 1;
				user.setDayLoginCount( value );
				user.getAwardManager().record( SAwardType.LAG, value );
				user.getAwardManager().record( SAwardType.LAM, value );
				
				// 下面主动更新 信息
				UpdateManager.instance.update( user, UpdateType.U_100, user.getHeroManager().getLists() );
				UpdateManager.instance.update( user, UpdateType.U_34 );
				UpdateManager.instance.updateMain( user );
			
				Logs.debug( user, "注册成功！" );
			}while( false );
		}
		
		// 这里应该是不会为NULL的
		if( user == null || user.getCon() == null ) 
		{
			Logs.error( user, "用户登陆错误 con=NULL" );
			return;
		}
		
		// 然后发送登陆信息
		UserCreateEvent xxx = (UserCreateEvent)Event.USER_CREATE.getEventInstance();
		xxx.run( user, code );
		
		// 记录IP
		if( code == ErrorCode.SUCCESS )
			UserManager.getInstance().addIPRecord( user.getCon().getRemoteAddress().getHostAddress(), user.getNickName() );
	}

	
	public void run( int index, String key ) throws IOException {
		ByteBuffer buffer = buildEmptyPackage( 256 );
		buffer.putShort( SystemCfg.GAME_DISTRICT );
		buffer.putInt( index );
		UtilBase.encodeString( buffer, key );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}

}
