package game.ectype;

import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;


/**
 * 副本星级
 * @author DXF
 */
public class EctypeStartLevel {

	private UserInfo user;
	
	private ConcurrentHashMap<Integer,Byte> starLevel;
	
	private EctypeStartLevelDataProvider	db 	=  EctypeStartLevelDataProvider.getInstance();
	
	public EctypeStartLevel( UserInfo user ) {
		this.user = user;
		starLevel = db.get( user) ;
		if( starLevel == null ){
			starLevel = new ConcurrentHashMap<Integer, Byte>();
			db.add( user );
		}
	}

	public byte get( int id ){
		if( starLevel.get(id) == null )
			return 0;
		return starLevel.get(id);
	}
	
	public void set( int id, byte star ){
		if( starLevel.get(id) == null )
			starLevel.putIfAbsent( id, star );
		else
			starLevel.replace( id, star );
	}
	
	public void update(){
		db.update( user, starLevel );
	}
	
}
