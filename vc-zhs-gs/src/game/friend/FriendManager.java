package game.friend;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.fighter.HeroTempletCfg;
import config.grow.UserGrowTempletCfg;
import config.talent.TalentTempletCfg;

import define.DefaultCfg;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 好友管理器
 * @author DXF
 *
 */
public class FriendManager {

	/** 玩家好友上限 这个是跟玩家等级挂钩的 */
	public static final short	 						MAX_NUM_LEVEL 	= 60;
	
	private UserInfo									user;
	private final FriendInfoDataProvider 				db 				= FriendInfoDataProvider.getInstance();	
	
	private FriendInfo									friendInfo;
	
	public FriendManager( UserInfo user ) {
		super();
		this.user 			= user;
		this.friendInfo		= db.get( user );
		
		// 如果数据库没有 就添加一条空数据进去
		if( this.friendInfo == null ){
			this.friendInfo = new FriendInfo();
			// 设置好友的记录时间  方便以后 判断是否每日刷新
			friendInfo.recordTime = SystemTimer.currentTimeSecond();
			db.add( user, this.friendInfo );
		}
	}
//	public FriendPageControl getPageControl(){
//		return friendInfo.pageControl;
//	}
//	public BegFriendPageControl getBegPageControl(){
//		return friendInfo.begPageControl;
//	}
	
	public List<FriendBase> getListFriend(){
		List<FriendBase> list = new ArrayList<FriendBase>();
		for( FriendBase f : friendInfo.friends.values() )
			list.add( f );
		return list;
	}
	public List<Integer> getListBeg(){
		return friendInfo.begFriend;
	}
	
	/** 领取次数  */
	public short getGvTimes() {
		return friendInfo.gvTimes;
	}
	
	/** 赠送次数  */
	public short getGetTimes() {
		return friendInfo.getTimes;
	}
	
	public FriendBase getFriend(int uID) {
		return friendInfo.friends.get(uID);
	}

	/**
	 * 添加好友  (没有真真添加 只是发送邀请)
	 * @param touser
	 * @return
	 */
	public ErrorCode addFriend( UserInfo touser ){
		
		// 判断是否达到上限
		if( friendInfo.friends.values().size() >= user.getFriendCapacity() )
			return ErrorCode.FRIEND_NUM_CAPACITY;
		
		// 判断是不是我的好友 如果已经是了 那就不发送了
		if( isFriend( touser.getUID() ) )
			return ErrorCode.UNKNOW_ERROR;
		
		// 如果已经发送邀请了 那么就不能再发送了
		if( touser.getFriendManager().getListBeg().indexOf( user.getUID() ) != -1 )
			return ErrorCode.FRIEND_INVITE_ISSEND;
		
		// 判断 如果 申请列表已经有这个玩家了 那么就直接加为好友
		if( friendInfo.begFriend.indexOf( touser.getUID() ) != -1 ){
			
			// 添加到好友列表
			addFriend( touser.getUID() );
			// 把自己也添加到对方好友列表中
			touser.getFriendManager().addFriend( this.user.getUID() );
			if( !touser.isOnline() ){
				touser.getFriendManager().updataFriend();
			}
			// 完了删除 申请列表数据
			removeBeg( touser.getUID() );
			
			return ErrorCode.FRIEND_ADD_OK;
		}
		
		// 添加到请求列表
		if( touser.getFriendManager().addBegFriend( this.user.getUID() ) ){
			// 如果添加成功  然后就看是否在线 如果没有在线 就刷新一下数据库
			if( !touser.isOnline() ){
				touser.getFriendManager().updataBeg();
				UpdateManager.instance.update( touser, UpdateType.U_30 );
			}
		}
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 添加到请求列表
	 * @param uid
	 */
	public boolean addBegFriend( int uID ) {
		boolean isadd = friendInfo.addBegFriend( uID );
		if( isadd )
			updataClient1(  uID, UpdateType.U_115 );
		return isadd;
	}
	public void removeBeg( int uID ) {
		friendInfo.removeBeg( uID );
	}
	
	public void addFriend( int uID ){
		friendInfo.addFriend( uID );
		updataClient( getFriend(uID), UpdateType.U_110 );
	}
	public void removeFriend( int uID ){
		friendInfo.removeFriend( uID );
	}
	
	// 更新前端
	private void updataClient( FriendBase f, UpdateType type ){
		if( user.isOnline() ){
			List<FriendBase> l = new ArrayList<FriendBase>();
			l.add( f );
			UpdateManager.instance.update( user, type, l );
		}
	}
	// 更新前端
	private void updataClient1( int uid, UpdateType type ){
		if( user.isOnline() ){
			List<Integer> l = new ArrayList<Integer>();
			l.add( uid );
			UpdateManager.instance.update( user, type, l );
		}
	}
	
	/**
	 * 同意邀请 和拒绝邀请 的处理函数
	 * @param u
	 * @param isAgree (是否同意)
	 * @return
	 */
	public ErrorCode begFriendHandle(UserInfo u, boolean isAgree) {
		
		// 判断 是不是有这个用户在 申请列表
		if( friendInfo.begFriend.indexOf( u.getUID() ) == -1 )
			return ErrorCode.UNKNOW_ERROR;
				
		// 先处理拒绝  如果拒绝 直接删除
		if( !isAgree ){
			removeBeg( u.getUID() );
			return ErrorCode.FRIEND_REMOVE_BEG;
		}
		
		// 判断是否达到上限
		if( friendInfo.friends.values().size() >= user.getFriendCapacity() )
			return ErrorCode.FRIEND_NUM_CAPACITY;
		
		// 判断是不是我的好友 如果已经是了 那就不发送了
		if( isFriend( u.getUID() ) ){
			removeBeg( u.getUID() );// 删除掉
			return ErrorCode.UNKNOW_ERROR;
		}
		
		// 添加到好友列表
		addFriend( u.getUID() );
		// 把自己也添加到对方的好友列表中
		u.getFriendManager().addFriend( this.user.getUID() );
		if( !u.isOnline() ){
			u.getFriendManager().updataFriend();
		}
		// 添加成功后 还是要删除掉 申请列表中的数据
		removeBeg( u.getUID() );
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 删除好友
	 * @param u
	 * @return
	 */
	public ErrorCode removeFriend( UserInfo u ) {
		
		// 判断是不是我的好友 如果不是 那就直接返回
		if( !isFriend( u.getUID() ) )
			return ErrorCode.UNKNOW_ERROR;
		
		removeFriend( u.getUID() );
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 获得已经赠送列表
	 * @return
	 */
	public List<Integer> hasPresentedList() {
		return friendInfo.hasPresentedList();
	}
	
	/**
	 * 获得已领取列表
	 * @return
	 */
	public List<Integer> alreadyGettheList() {
		return friendInfo.alreadyGettheList();
	}
	
	/**
	 * 赠送体力
	 * @param uID
	 * @return
	 */
	public ErrorCode gvStrength( int uID ) {
		
		ErrorCode code 			= ErrorCode.SUCCESS;
		
		if( friendInfo.gvTimes >= DefaultCfg.FRIEND_GV_TIMES )
			return ErrorCode.FRIEND_GVTIMES_BEYOND;

		List<FriendBase> list 	= friendInfo.getGv( uID );
		
		if( list.isEmpty() )
			return code;
		
		for( FriendBase fb : list ){
			// 如果赠送次数超出上限 那么就不能再赠送了
			if( friendInfo.gvTimes >= DefaultCfg.FRIEND_GV_TIMES ){
				// 这里将 所有赠送体力值 清零
				friendInfo.resetGV();
				return ErrorCode.SUCCESS;
			}
			
			// 根据UID 获取好友信息
			UserInfo friend = UserManager.getInstance().getByName( fb.getUid() );
			
			// 然后将体力赠送给他
			if( friend.getFriendManager().gainStrength( user.getUID(), fb.getGvStrength() ) )
			{
				// 先将赠送次数加一
				++friendInfo.gvTimes;
				// 这里如果成功  就把体力赋为-1 表示 已经赠送
				fb.setGvStrength(-1);
			}
		}
		
		return code;
	}
	
	/**
	 * 获得体力
	 * @param uID ( 赠送人 )
	 * @param gvStrength
	 * @return
	 */
	private boolean gainStrength( int uID, int gvStrength ) {
		
		FriendBase fb = friendInfo.get( uID );
		
		// 这里将领取体力赋值
		if( fb != null ){
			fb.setGetStrength( gvStrength );
			
			// 这里看是否在线  如果不在线 我们就帮他存数据库
			if( !user.isOnline() ){
//				updataFriend();
			}else{
				List<FriendBase> l = new ArrayList<FriendBase>();
				l.add( fb );
				UpdateManager.instance.update( user, UpdateType.U_112, l );
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 领取体力
	 * @param uID
	 * @return
	 */
	public ErrorCode getStrength(int uID) {
		ErrorCode code 			= ErrorCode.SUCCESS;
		
		if( friendInfo.getTimes >= DefaultCfg.FRIEND_GET_TIMES )
			return ErrorCode.FRIEND_GETTIMES_BEYOND;

		List<FriendBase> list 	= friendInfo.getGet( uID );
		
		if( list.isEmpty() )
			return code;
		
		boolean isget 			= false;
		for( FriendBase fb : list ){
			
			// 如果领取次数超出上限 那么就不能再领取了
			if( friendInfo.getTimes >= DefaultCfg.FRIEND_GET_TIMES ){
				// 这里将 所有领取体力值 清零
//				friendInfo.resetGET( -1 );
				return ErrorCode.SUCCESS;
			}
			
			// 领取成功后 清零
			if( user.changeStrength( fb.getGetStrength(), "好友领取体力" ) != -1 ){
				// 先将领取次数加一
				++friendInfo.getTimes;
				fb.setGetStrength( -1 );
				
				isget			= true;
			}
		}
		
		if( isget )
			UpdateManager.instance.update( user, UpdateType.U_2 );
		
		return code;
	}
	
	/** 刷新数据库 所有数据 */
	public void updata() {
		db.updata( user, friendInfo );
	}
	/** 刷新数据库 好友列表数据 */
	public void updataFriend() {
		db.updataFriend( user, friendInfo );
	}
	/** 刷新数据库 请求列表数据 */
	public void updataBeg() {
		db.updataBeg( user, friendInfo );
	}
	/** 根据记录时间 刷新一个 看是否 过天了  这样做的目的就是不用开一个线程 */
	public boolean updataTime() {
		return friendInfo.updataTime();
	}

	/** 是否我的好友 */
	public boolean isFriend( int uID )	{
		return friendInfo.get(uID) != null;
	}
	
	public static void main( String[] arg ) throws IOException{
		
		// ----------------先 创建玩家
		HeroTempletCfg.init();
		TalentTempletCfg.init();	
		UserGrowTempletCfg.init();
//		for( int i = 1011896; i <= 1011925; i++ ){
//			UserInfo temp = new UserInfo(null, i, "妹子" + (i - 1011896) );
//			UserManager.getInstance().create( null, temp, 10010 );
//		}
//		System.out.println( "玩家添加完成!" );
		
		// ----------------然后添加好友
		int name 		= 1011843;
		UserInfo temp 	= UserManager.getInstance().getByName( name );
		for ( int i = 1011896; i <= 1011925; i++ ){
			if( i != name ){
				temp.getFriendManager().addFriend( i );
				UserInfo ui	= UserManager.getInstance().getByName( i );
				ui.getFriendManager().addFriend( name );
				ui.getFriendManager().updata();
			}
		}
		temp.getFriendManager().updata();
		System.out.println( temp.getNickName() + "-的好友添加完成!" );
	}



}
