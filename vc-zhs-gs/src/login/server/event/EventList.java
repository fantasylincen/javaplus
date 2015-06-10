package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import user.UserInfo;

public enum EventList {
	LOGIN_VERIFICATION					( 20, 		new LoginEvent() ), 				// 登陆 登陆服
	LOGIN_PING							( 21, 		new PingEvent() ), 					// ping
	CHECK_CODEEVENT						( 22, 		new CheckCodeEvent() ), 			// 核对验证码
	CHECK_CODECREATE					( 23, 		new CheckCodeCreateEvent() ), 		// 核对验证码 （创建角色的时候的）
	GET_USERDATA						( 24, 		new GetUserdataEvent() ), 			// 获取玩家信息
	INFORM_RECHARGE						( 25, 		new InformRechargeEvent() ); 		// 支付通知
	
	private final short 			number;
	private final ILEvent 			eventInstance;
	
	EventList( int value, ILEvent eventInstance ) {
		if( value >= Short.MAX_VALUE || value < 0 ){
			throw new IllegalArgumentException( "包号不符合规范：" + value );
		}
		this.number 		=  (short) value;
		this.eventInstance 	= eventInstance;
		this.eventInstance.setEventId( number );
	}
	private static final Map<Short, EventList> numToEnum = new HashMap<Short, EventList>();
	
	static{
		for( EventList a : values() ){
			
			EventList p = numToEnum.put( a.number, a );
			if( p != null ){
				throw new RuntimeException( "通信包" + a.number + "重复了" );
			}
		}
	}
	
	public ILEvent getEventInstance() {
		return eventInstance;
	}
	public short toNum() {
		return number;
	}
	public static EventList fromNum( short n ){
		return numToEnum.get( n );
	}
	
	/**
	 * 运行此枚举所对应的包的run方法
	 * @param user
	 * @param buf
	 * @throws IOException 
	 */
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		eventInstance.run( user, buf );
	}
}
