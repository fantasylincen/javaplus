package events;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.xsocket.connection.INonBlockingConnection;

import server.event.CheckCodeCreateEvent;
import server.event.CheckCodeEvent;
import server.event.GetUserdataEvent;
import server.event.InformRechargeEvent;
import server.event.PingEvent;
import server.event.ServerLoginEvent;
import user.UserInfo;
import user.event.AccounBindEvent;
import user.event.SendServerListEvent;
import user.event.UserLoginEvent;

public enum Event{
	
	// ----------------服务器
	SERVER_LOGIN						( 20,		new ServerLoginEvent() ),		// 服务器连接
	PING_EVENT							( 21, 		new PingEvent() ), 				// Ping
	CHECK_CODEEVENT						( 22, 		new CheckCodeEvent() ), 		// 核对验证码
	CHECK_CODEECREATE					( 23, 		new CheckCodeCreateEvent() ), 	// 核对验证码 （创建角色的时候 验证）
	GET_USERDATA						( 24, 		new GetUserdataEvent() ), 	// 获取玩家信息
	INFORM_RECHARGE						( 25, 		new InformRechargeEvent() ), 	// 支付通知
	
	// ----------------用户
	USER_LOGIN							( 102, 		new UserLoginEvent() ), //用户  登录
	SEND_SERVER_LIST					( 103, 		new SendServerListEvent() ), //发送服务器列表
	ACCOUN_BINDEVENT					( 104, 		new AccounBindEvent() ); //绑定账号
//	TOAPPLY_FORA_PASSWORD				( 105, 		new ToapplyPasswordEvent() ); //申请密码
	
	
	private final short 			number;
	private final EventBase 		eventInstance;
	
	Event( int value, EventBase eventInstance ) {
		if( value >= Short.MAX_VALUE || value < 0 ){
			throw new IllegalArgumentException( "包号不符合规范：" + value );
		}
		this.number =  (short) value;
		this.eventInstance = eventInstance;
		this.eventInstance.setEventId( number );
	}
	private static final Map<Short, Event> numToEnum = new HashMap<Short, Event>();
	
	static{
		for( Event a : values() ){
			Event p = numToEnum.put( a.number, a );
			if( p != null ){
				throw new RuntimeException( "通信包" + a.number + "重复了" );
			}
		}
	}
	
	public EventBase getEventInstance() {
		return eventInstance;
	}
	public short toNum() {
		return number;
	}
	public static Event fromNum( short n ){
		return numToEnum.get( n );
	}
	
	/**
	 * 运行此枚举所对应的包的run方法
	 * @param user
	 * @param buf
	 * @throws IOException 
	 */
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		eventInstance.run( user.getCon(), buf );
	}
	
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		eventInstance.run( con, buf );
	}
	
	public void run( INonBlockingConnection con, String ... args ) throws IOException {
		eventInstance.run( con, args );
	}
	
}
