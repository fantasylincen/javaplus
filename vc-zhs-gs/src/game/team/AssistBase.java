package game.team;

import game.fighter.Hero;

/**
 * 协助好友信息
 * @author DXF
 *
 */
public class AssistBase extends TeamHero {

	// 好友ID
	private int  	_nUserID;
	
	// 好友队长英雄 信息
	private Hero 	_nCaptainHero;
	
	// 高级玩家扣除金币
	private int 	_nExpert;
	
	public AssistBase( int uID ) {
		super(uID, (byte) -1, false );
	}

	public AssistBase(int uid, byte pos, boolean isdie) {
		super(uid, pos, isdie );
	}

	public void setUserUID( int uid ){
		this._nUserID = uid ;
	}
	
	public int getUserUID(){
		return this._nUserID;
	}
	
	public void setCaptainHero( Hero hero ){
		this._nCaptainHero = hero;
	}
	
	public Hero getCaptainHero(){
		return this._nCaptainHero;
	}
	
	public void setExpert( int value ){
		_nExpert = value;
	}
	public int getExpert(){
		return _nExpert;
	}

	public String getHeorContent() {
		
		StringBuilder content = new StringBuilder();
		
		content.append( _nCaptainHero.getUID() ).append( "," );
		content.append( _nCaptainHero.getNid() ).append( "," );
		content.append( _nCaptainHero.getLevel() ).append( "," );
		content.append( _nCaptainHero.getQuality().toContent() ).append( "," );
		content.append( _nCaptainHero.getSkillAttack().getLevel() ).append( "," );
		content.append( _nCaptainHero.getCaptainSkill().getID() );
		
		return content.toString();
	}
	
}
