package game.friend;


/**
 * 单个好友 基本信息
 * @author DXF
 *
 */
public class FriendBase {

	// 好友唯一ID
	private int 		uid;
	
	// 可领取体力值
	private int			getStrength;
	
	// 赠送体力值			
	private int 		gvStrength;
	
	public FriendBase(){
	}
	public FriendBase( int uid ){
		this.uid = uid;
	}
	
	public void setUid( int uid ){
		this.uid = uid;
	}
	public int getUid(){
		return this.uid;
	}
	
	public void setGetStrength( int str ){
		this.getStrength = str;
	}
	public int getGetStrength(){
		return this.getStrength;
	}
	
	public void setGvStrength( int gv ){
		this.gvStrength = gv;
	}
	public int getGvStrength(){
		return this.gvStrength;
	}

	public byte gvStatusToByte() {
		if( gvStrength > 0 )
			return 0;
		else if( gvStrength == -1 )
			return 1;
		return 2;
	}

	public byte getStatusToByte() {
		if( getStrength > 0 )
			return 0;
		else if( getStrength == -1 )
			return 1;
		return 2;
	}
}
