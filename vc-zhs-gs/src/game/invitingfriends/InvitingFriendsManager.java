package game.invitingfriends;

import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.util.List;

import manager.DWType;

import user.UserInfo;
import util.ErrorCode;

/**
 * 邀请好友 管理中心
 * @author DXF
 */
public class InvitingFriendsManager {

	private UserInfo									user;
	private final InvitingFriendsProvider 				db = InvitingFriendsProvider.getInstance();
	
	private InvitingFriendsBase							invitingFriendsBase;
	
	public InvitingFriendsManager( UserInfo user ){
		this.user 			= user;
		invitingFriendsBase	= db.get( user );
		
		if( invitingFriendsBase == null ){
			invitingFriendsBase	= new InvitingFriendsBase();
			db.add( user, invitingFriendsBase );
		}
	}

	public InvitingFriendsBase getInvitingFriends(){
		return this.invitingFriendsBase;
	}
	
	public List<InvitedBase> getList() {
		return invitingFriendsBase.getList();
	}

	public int getIMyID() {
		return invitingFriendsBase.getIMyID();
	}

	/**
	 * 确认添加
	 * @param u
	 * @return
	 */
	public ErrorCode toAdd( UserInfo u ) {
		
		// 如果有了 就直接返回
		if( invitingFriendsBase.getIMyID() != -1 )
			return ErrorCode.INVITING_IS_HAVE;
		
		// 直接设置
		invitingFriendsBase.setIMyID( u.getUID() );
		
		db.updata( user, invitingFriendsBase );
		
		return u.getInvitingFriendsManager().addInvitihgFriend( user );
	}

	/**
	 * 添加 邀请好友
	 * @param user2
	 * @return
	 */
	public ErrorCode addInvitihgFriend( UserInfo u ) {
		
		ErrorCode code = invitingFriendsBase.add( u );
		
		if( code == ErrorCode.SUCCESS )
			db.updata( user, invitingFriendsBase );
			
		return code;
	}
	
	
	/**
	 * 领取水晶
	 * @param u
	 * @return
	 */
	public ErrorCode getTheCrystal( UserInfo u ) {
		
		int id		= u.getUID();
		
		int value 	= invitingFriendsBase.getBeGottenGold( id );
		
		if( value == 0 ) return ErrorCode.INVITING_NOT_GETGOLD;
		
		// 这里 领取后  就立马 清零
		invitingFriendsBase.setBeGottenGold( id, 0 );
		
		user.changeAward(AwardType.GOLD, value, "邀请好友 领取水晶", DWType.SYSTEM_IS_PRESENTED );
		
		UpdateManager.instance.update( user, UpdateType.U_4 );
		
		db.updata( user, invitingFriendsBase );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 记录 我邀请的好友 升级有关奖励信息
	 * @param u
	 * @param isUpgrade 相差等级
	 */
	public void awardToRecord( UserInfo u, short isUpgrade ) {
	
		// 记录 系统奖励
		user.getAwardManager().recordInvting( u.getLevel() );
		
		// 记录 邀请好友里面的奖励信息
		if( u.getLevel() >= InvitingRecortToAward.value[0][0] )
			record( u , isUpgrade );
	}

	//记录奖励信息
	private void record( UserInfo u , short isUpgrade ) {
		
		int value = InvitingRecortToAward.get( u.getLevel(), isUpgrade );
		
		if( value != 0 ){
			invitingFriendsBase.addBeGottenGold( u.getUID(), value );
		}
		
	}

	
}
