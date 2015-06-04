package user;

import java.util.HashMap;
import java.util.Map;


public enum UserStatus {
	/**
	 * 访客状态
	 * 也是玩家连接上来之后的最初状态
	 */
	GUEST(1),
	/**
	 * 新玩家状态
	 */
//	NEW(2),
	
	/**
	 * 正常登陆状态
	 */	
	LOGIN(3),
	/**
	 * 禁止登陆状态
	 */
	BAN(4)
	;//
	
	private byte number;
	
	UserStatus( int value ) {
		this.number = (byte) value;
	}
	private static final Map<Byte, UserStatus> numToEnum = new HashMap<Byte, UserStatus>();
	static{
		for( UserStatus a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public byte toNum() {
		return number;
	}
	public static UserStatus fromNum( byte n ){
		return numToEnum.get( n );
	}
	
	

}
