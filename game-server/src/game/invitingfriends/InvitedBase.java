package game.invitingfriends;

/**
 * 单个邀请的好友信息
 * @author DXF
 */
public class InvitedBase {

	// 用户ID
	private int				userID;
	
	// 可领取水晶数
	private int 			canBeGottenGold;

	public InvitedBase( int uid ) {
		this.userID = uid;
	}
	
	public void init() {
		canBeGottenGold = 0;
	}
	
	public int getUID() {
		return userID;
	}
	
	public int getGottenGold(){
		return canBeGottenGold;
	}
	
	public void setGottenGold( int gold ){
		this.canBeGottenGold = gold;
	}

	public void addGottenGold( int value ) {
		this.canBeGottenGold += value;
	}
	
}
