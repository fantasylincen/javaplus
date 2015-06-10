package core;

import game.events.Event;
import game.events.EventDescrip;
import game.events.all.GMEvent;
import game.events.all.front_animation.FrontAnimationEvent;
import game.events.all.user.UserCreateEvent;
import game.events.all.user.UserLoginEvent;
import game.events.all.user.UserLoginFrontEvent;
import game.log.Logs;

import java.io.IOException;
import java.nio.ByteBuffer;

import login.server.event.EventList;

import org.xsocket.connection.INonBlockingConnection;

import user.UserManager;
import util.ErrorCode;

/**
 * 游戏的主干框架 单例
 * 
 * @author deng
 * 
 *         2013-6-16 下午04:13:27
 */
public final class GameMainLogic implements IGameLogic {

	private static final GameMainLogic 	instance	= new GameMainLogic();

	public static final GameMainLogic getInstance() {
		return instance;
	}

	/**
	 * 处理客户端发送的包信息
	 * 
	 * @param con
	 * @param eventNo
	 * @param data
	 *            去除包头，包尾，包号，包长的附加信息的数据
	 * 
	 * <br>
	 * @throws IOException
	 */
	@Override
	public void packageRun( INonBlockingConnection con, short eventNo, byte[] data ) throws IOException {
		
		Event event 		= Event.fromNum( eventNo );

		ErrorCode code 		= ErrorCode.SUCCESS;

		int name 			= con.getAttachment() == null ? 0 : (Integer)con.getAttachment();
		Logs.debug( UserManager.getInstance().getByName( name ) , "通信包：" + (event == null ? "("+eventNo+ ")-没找到" : buildPrefixEventStr( event )) );
		if( event == null ) {
			code = ErrorCode.PACKAGE_NOT_FOUND;
		} else {
			try{
				ByteBuffer buf = ByteBuffer.wrap( data );
				if( event == Event.GM ){
//					if( name != 0 ){
//						
//					}
					GMEvent p = (GMEvent) Event.GM.getEventInstance();
					p.run( con, buf );
				}else if( event == Event.USER_LOGIN ){
					if( name != 0 ){
						code = ErrorCode.USER_HAS_LOGIN;
					} else {
						UserLoginEvent p = (UserLoginEvent) Event.USER_LOGIN.getEventInstance();
						p.run( con, buf );
					}
				} else if( event == Event.USER_CREATE ){
					if( name != 0 ){
						code = ErrorCode.USER_HAS_LOGIN;
					} else {
						UserCreateEvent p = (UserCreateEvent) Event.USER_CREATE.getEventInstance();
						p.run( con, buf );
					}
				} else if( event == Event.FRONT_ANIMATION ){
					FrontAnimationEvent f = (FrontAnimationEvent) Event.FRONT_ANIMATION.getEventInstance();
					f.run( con, buf );
				} else if( event == Event.USER_LOGIN_FRONT ){
					if( name != 0 ){
						code = ErrorCode.USER_HAS_LOGIN;
					} else {
						UserLoginFrontEvent f = (UserLoginFrontEvent) Event.USER_LOGIN_FRONT.getEventInstance();
						f.run( con, buf );
					}
//				} else if( event == Event.USER_LOGIN_FRONT_VERIFY ){
//					if( name != 0 ){
//						code = ErrorCode.USER_HAS_LOGIN;
//					} else {
//						UserLoginFrontVerifyEvent f = (UserLoginFrontVerifyEvent) Event.USER_LOGIN_FRONT_VERIFY.getEventInstance();
//						f.run( con, buf );
//					}
				} else {
					if( name == 0 ){
						code = ErrorCode.USER_NOT_LOGIN;
					} else {
						code = UserManager.getInstance().eventRun( name, event, data );
					}
				}
			}
			catch( Exception e ){
				Logs.error( buildPrefixStr( con, name ) + "包执行异常：", e );
			}
		}

		if ( code != ErrorCode.SUCCESS ) {
			Logs.error( buildPrefixStr( con, name ) + "错误码:[" + code + "] 包:" + event + "[" + eventNo + "] " + name );
//			event.getEventInstance().sendErrorCode( con, code );
			if( ErrorCode.USER_HAS_LOGIN == code && event == Event.USER_LOGIN ){
				((UserLoginEvent)event.getEventInstance()).run( con, name );
			}
			
		}
	}
	
	/**
	 * 处理登陆服发送的包信息
	 * 
	 * @param con
	 * @param eventNo
	 * @param data
	 *            去除包头，包尾，包号，包长的附加信息的数据
	 * <br>
	 * @throws IOException
	 */
	@Override
	public void loginPakRun( INonBlockingConnection con, short eventNo, byte[] data ) throws IOException {
		
		EventList event = EventList.fromNum( eventNo );

		ErrorCode code 	= ErrorCode.SUCCESS;
		
		if( event == null ) {
			code = ErrorCode.PACKAGE_NOT_FOUND;
		} else {
			ByteBuffer buf = ByteBuffer.wrap( data );
			
			event.run( null, buf );
		}
		
		if (code != ErrorCode.SUCCESS) {
			Logs.error( "错误码:[" + code + "] 包:" + event + "[" + eventNo + "] "  );
		}
	}
	
	private String buildPrefixEventStr(Event event) {
		
		Class<?> c 			= event.getEventInstance().getClass();
		EventDescrip desc 	= c.getAnnotation(EventDescrip.class);
		String s 			=(desc == null) ? "" : desc.desc();
		
		return "(" +event.getEventInstance().getEventId() + ")-" + s;
	}

	/**
	 * 针对此类，提供一个统一的提示信息前缀
	 * @return
	 */
	private String buildPrefixStr( INonBlockingConnection con, int name ){
		String s = "【" + UserManager.getInstance().getNickName( name );
		s += "】";
		s += con.getRemoteAddress();
		s += " > \t";
		return s;
	}
	
	/**
	 * 玩家关闭连接，退出游戏,这里无需考虑网络层的代码，只需考虑user层
	 * 
	 */
	@Override
	public void userExit( INonBlockingConnection con ) throws IOException {
		int name = con.getAttachment() == null ? 0 : (Integer)con.getAttachment();

		if( name != 0 ){
			Logs.debug( UserManager.getInstance().getByName( name ) , "退出游戏！" );
			
			ErrorCode code = UserManager.getInstance().exit( name );
			if (code != ErrorCode.SUCCESS) {
				Logs.error( UserManager.getInstance().getByName( name ), "用户退出发生错误：" + name + "[" + con.getId() + "], 错误码:" + code );
			}
		}

		
	}
}
