package game.mail;


import java.util.HashMap;
import java.util.Map;

public enum MailType {

	/** 系统公告 */
	SYSTEM_NOTICE( 1 ),
	
	/** 系统赠送 */
	SYSTEM_PRESENT( 2 ),
	
	/** 好友聊天信息 */
	FRIEND_MSG( 3 );
	
	private final byte 				number;
	
	MailType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, MailType> numToEnum = new HashMap<Byte, MailType>();
	static{
		for( MailType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static MailType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
}
