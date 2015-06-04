package game.events.all.fight;

/**
 * 战斗 基本数据
 * @author DXF
 *
 */
public class BattleHeroBase {

	/**
	 * 英雄唯一ID
	 */
	public int  			m_nUID;
	
	/**
	 * 英雄位置
	 */
	public byte 			m_nPosition;
	
	public BattleHeroBase( int uID, byte pos ) {
	
		this.m_nUID 		= uID;
		this.m_nPosition 	= pos;
	}
}
