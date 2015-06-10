package game.fightoffriend;

import game.fighter.Hero;

import java.nio.ByteBuffer;

import util.UtilBase;

/**
 * 邀请好友列表单个玩家信息 
 * @author DXF
 *
 */
public class FightOfFriendBase {

	/**
	 * 用户唯一ID
	 */
	public  int				m_nUID;
	
	/**
	 * 用户名称
	 */
	public  String			m_nName;
	
	/**
	 * 用户等级
	 */
	public  short			m_nLevel;
	
	/**
	 * 用户战斗力
	 */
	public  int				m_nFighting;
	
	/**
	 * 用户 队长英雄信息
	 */
	public Hero				m_nCaptainHero = null;
	
	/**
	 * 是否好友
	 */
	public  boolean 		m_nIsFriend	;
	
	/**
	 * 是否高级非好友 需要金币
	 */
	public 	int 			m_nIsExpert ;

	
	public void putData( ByteBuffer response, int i ) {
		
		response.putInt( this.m_nUID );
		UtilBase.encodeString( response, this.m_nName );
		response.putShort( this.m_nLevel );
		response.putInt( this.m_nFighting );
		response.putInt( i );
		response.putInt( this.m_nCaptainHero.getNid() );
		response.putShort( this.m_nCaptainHero.getLevel() );
		this.m_nCaptainHero.getQuality().toByte( response );
		response.putInt( this.m_nCaptainHero.getAttack() );
		response.putInt( this.m_nCaptainHero.getHpMax() );
		response.putInt( this.m_nCaptainHero.getCaptainSkill().getID() );
		response.put( (byte) (this.m_nIsFriend ? 1 : 0) );
		response.putInt( this.m_nIsExpert );
	}
}
