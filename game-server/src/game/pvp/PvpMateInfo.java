package game.pvp;

import game.fighter.Hero;
import game.talent.TalentBase;
import game.talent.TalentType;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;

/**
 * 匹配信息
 * @author DXF
 */
public class PvpMateInfo {

	// 敌对 召唤师信息
	private UserInfo 		_nUser;
	
	// 敌对 出战英雄列表
	private List<Hero> 		_nLists;
	
	// 记录战斗力 免得在算次
	private int 			_nFighting;
	
	// 段位
	private DanGrad			_nDanGrad;
	
	// 天赋
	private ConcurrentHashMap<TalentType,TalentBase> _nTalents = null;
	
	// 是否机器人
	private boolean 		_nIsRobot = false;
	
	public PvpMateInfo( UserInfo user, List<Hero> lists, int fighting ){
		this._nUser 	= user;
		this._nLists 	= lists;
		this._nFighting	= fighting;
	}
	
	public PvpMateInfo() {
	}

	public void setUser( UserInfo user ){
		this._nUser 	= user;
	}
	public UserInfo getUser(){
		return this._nUser;
	}
	
	public void setList( List<Hero> list ){
		this._nLists = list;
	}
	public List<Hero> getList(){
		return this._nLists;
	}
	
	public void setFighting( int fighting ){
		this._nFighting = fighting;
	}
	public int getFighting(){
		return this._nFighting;
	}

	public void setDanGrad( DanGrad danGrad){
		_nDanGrad = danGrad;
	}
	public DanGrad getDanGrad(){
		return _nDanGrad;
	}
	
	public void setTalents( ConcurrentHashMap<TalentType,TalentBase> talents ){
		_nTalents = talents;
	}
	public ConcurrentHashMap<TalentType,TalentBase> getTalents(){
		return _nTalents;
	}
	
	public void setIsRobot( boolean isRobot ){
		_nIsRobot = isRobot;
	}
	public boolean getIsRobot(){
		return _nIsRobot;
	}
	
	public void putHeroData( ByteBuffer response ) {
		response.put( (byte)_nLists.size() );
		for( Hero hero : _nLists ){
			response.putInt( hero.getUID() );
			response.putInt( hero.getNid() );
			response.putShort( hero.getLevel() );
			hero.getQuality().toByte( response );
			response.put( hero.getPosition() );
			response.putInt( hero.getHpMax() );
			response.putInt( hero.getAttack() );
			response.put( (byte) (hero.getIsCaptain() ? 1 : 0) );
			response.put( hero.getSkillAttack().getLevel() );
			response.putInt( hero.getCaptainSkill().getID() );
		}
	}
	
}
