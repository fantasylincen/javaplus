package game.team;

/**
 * 单个英雄 数据
 * @author DXF
 *
 */
public class TeamHero {

	// 英雄唯一ID
	private int			uID;
	
	// 英雄位置
	private byte 		position		= -1;
	
	// 英雄是否死亡	
	private boolean 	isDie			= false;
	
	// 绝对死亡
	private boolean 	isAbsoluteDie	= false;
	
	public TeamHero( int uID, byte pos, boolean isdie){
		this.uID 		= uID;
		this.position 	= pos;
		this.isDie 		= isdie;
	}
	
	public void setUId( int uid ){
		this.uID = uid;
	}
	public int getUId(){
		return this.uID;
	}
	
	public void setPosition( byte pos ){
		this.position = pos;
	}
	public byte getPosition(){
		return this.position;
	}
	
	public void setIsDie( boolean isdie ){
		this.isDie = isdie;
	}
	public boolean getIsDie(){
		return this.isDie;
	}
	
	public void IsAbsoluteDie( boolean isdie ){
		this.isAbsoluteDie = isdie;
	}
	public boolean IsAbsoluteDie(){
		return this.isAbsoluteDie;
	}
	
	public String toString(){
		return "uID:" + uID + " position:" + position + "  isDie:" + isDie + "  isAbsoluteDie:" + isAbsoluteDie;
	}
}
