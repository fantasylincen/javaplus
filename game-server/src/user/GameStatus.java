package user;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩家的游戏状态
 * @author DXF
 *
 */
public enum GameStatus {
	/**
	 * 正常游戏状态
	 */	
	NORMAL(0) {
		@Override
		public void run( UserInfo user ) {
			
		}
	},
	
	/**
	 * 战斗状态
	 */
	FIGHTING(1) {
		@Override
		public void run( UserInfo user ) {
		}
	};
	
	private byte number;
	
	GameStatus( int value ) {
		this.number = (byte) value;
	}
	private static final Map<Byte, GameStatus> numToEnum = new HashMap<Byte, GameStatus>();
	static{
		for( GameStatus a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public byte toNum() {
		return number;
	}
	public static GameStatus fromNum( byte n ){
		return numToEnum.get( n );
	}
	
	
	public abstract void run( UserInfo user );
	
}
