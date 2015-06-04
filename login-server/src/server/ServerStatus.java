package server;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器 状态
 * @author DXF
 *
 */
public enum ServerStatus {

	// 0，流畅  1，良好  2，拥挤  3，爆满 
	/** 流畅 */
	SS_0(0),
	
	/** 良好 */
	SS_1(1),
	
	/** 拥挤 */
	SS_2(2),
	
	/** 爆满 */
	SS_3(3);
	
	private byte number;
	
	ServerStatus( int value ) {
		this.number = (byte) value;
	}
	private static final Map<Byte, ServerStatus> numToEnum = new HashMap<Byte, ServerStatus>();
	static{
		for( ServerStatus a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public byte toNum() {
		return number;
	}
	public static ServerStatus fromNum( byte n ){
		return numToEnum.get( n );
	}
	
	
}
