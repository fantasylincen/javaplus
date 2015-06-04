package game.friend;

import game.log.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import util.SystemTimer;

import define.DefaultCfg;

/**
 * 好友数据
 * @author DXF
 *
 */
public class FriendInfo {

	/** 玩家好友列表 */
	public ConcurrentHashMap<Integer,FriendBase> 		friends 		= new ConcurrentHashMap<Integer, FriendBase>();
	
	/** 好友请求列表 */
	public List<Integer> 								begFriend 		= new ArrayList<Integer>();
	
	/** 向好友赠送体力次数 */
	public short 										gvTimes			= 0;
	
	/** 领取体力次数 */
	public short										getTimes		= 0;

	/** 记录时间   这个时间是用来确定是否过天 已达到每日的效果 */
	public int											recordTime		= 0;		
	
//	// 分页控制 好友列表
//	public FriendPageControl							pageControl;
//	
//	// 分页控制 请求列表
//	public BegFriendPageControl							begPageControl;
	
	public FriendInfo(){
//		pageControl 		= new FriendPageControl( friends );
//		begPageControl		= new BegFriendPageControl( begFriend );
	}
	
	/**
	 * 添加到好友列表
	 * @param uid
	 */
	public void addFriend(int uid) {
		FriendBase fb 	= new FriendBase();
		fb.setUid(uid);
		
		int value		= gvTimes >= DefaultCfg.FRIEND_GV_TIMES ? 0 : DefaultCfg.FRIEND_INIT_GV;
		fb.setGvStrength( value );
		fb.setGetStrength(0);
		friends.putIfAbsent( fb.getUid(), fb );
		
//		pageControl.updata( friends );
	}
	
	/**
	 * 删除一个好友
	 * @param uid
	 */
	public void removeFriend( int uid ) {
		friends.remove(uid);
		
//		pageControl.updata( friends );
	}
	
	/**
	 * 添加到请求列表
	 * @param uid
	 */
	public boolean addBegFriend( int uID ) {
		
		if( begFriend.indexOf( uID ) != -1 )
			return false;
		
		if( begFriend.size() < DefaultCfg.BEG_FRIEND_MAX_NUM )
			begFriend.add( uID );
		else
			begFriend.set( begFriend.size() - 1, uID );
		
//		begPageControl.updata( begFriend );
		
		return true;
	}
	
	/**
	 * 删除一个申请
	 * @param uid
	 */
	public void removeBeg( int uid ) {
		int idx = begFriend.indexOf(uid);
		if( idx != -1 ){
			begFriend.remove(idx);
//			begPageControl.updata( begFriend );
		}
	}
	
	/**
	 *  获取可以赠送的好友
	 * @param uID
	 * @return
	 */
	public List<FriendBase> getGv( int uID ) {
		List<FriendBase> list = new ArrayList<FriendBase>(); 
		
		if( uID != -1 ){// 如果为不为-1那么就找出这个好友  
			FriendBase fb = friends.get( uID );
			
			// 这里看 是不是可以赠送
			if( fb != null && fb.getGvStrength() > 0 ){
				list.add(fb);
			}
		}else{// 获取全部可以赠送的好友
			
			for( FriendBase fb : friends.values() ){
				if( fb.getGvStrength() > 0 )
					list.add(fb);
			}
		}
		
		return list;
	}
	/**
	 * 获得已经赠送列表
	 * @return
	 */
	public List<Integer> hasPresentedList() {
		List<Integer> list = new ArrayList<Integer>();
		for( FriendBase fb : friends.values() ){
			if( fb.getGvStrength() == -1 )
				list.add( fb.getUid() );
		}
		return list;
	}
	
	
	/**
	 * 获取可以领取体力的好友
	 * @param uID
	 * @return
	 */
	public List<FriendBase> getGet(int uID) {
		List<FriendBase> list = new ArrayList<FriendBase>(); 
		
		if( uID != -1 ){// 如果为不为-1那么就找出这个好友  
			FriendBase fb = friends.get( uID );
			
			// 这里看 是不是可以赠送
			if( fb != null && fb.getGetStrength() > 0 ){
				list.add(fb);
			}
		}else{// 获取全部可以领取的好友
			
			for( FriendBase fb : friends.values() ){
				if( fb.getGetStrength() > 0 )
					list.add(fb);
			}
		}
		
		return list;
	}

	public List<Integer> alreadyGettheList() {
		List<Integer> list = new ArrayList<Integer>();
		for( FriendBase fb : friends.values() ){
			if( fb.getGetStrength() == -1 )
				list.add( fb.getUid() );
		}
		return list;
	}
	
	/**
	 * 根据uID获取好友信息
	 * @param uID
	 * @return
	 */
	public FriendBase get(int uID) {
		return friends.get(uID);
	}

	/**
	 * 将好友信息 编码成字符窜
	 * @return
	 */
	public String getFriendContent() {
		
		StringBuilder output = new StringBuilder();
		
		for( FriendBase fb : friends.values() )
		{
			output.append( fb.getUid() ).append(",");
			output.append( fb.getGvStrength() ).append(",");
			output.append( fb.getGetStrength() );
			output.append( "|" );
		}
		
		return output.toString();
	}

	/**
	 * 将申请信息 编码成字符窜
	 * @return
	 */
	public String getBegContent() {
		
		StringBuilder output = new StringBuilder();
		
		for( int fb : begFriend )
		{
			output.append( fb );
			output.append( "|" );
		}
		
		return output.toString();
	}
	
	/** 清零所有 赠送值 */
	public void resetGV() {
		for( FriendBase fb : friends.values() ){
			if( fb.getGvStrength() != -1 )
				fb.setGvStrength(0);
		}
	}
	
	/** 初始所有  赠送值 */
	public void initGV(){
		for( FriendBase fb : friends.values() )
			fb.setGvStrength( DefaultCfg.FRIEND_INIT_GV );
	}
	
	/** 清零所有 领取值 */
	public void resetGET( int value ) {
		for( FriendBase fb : friends.values() ){
			if( fb.getGetStrength() < 0 ){
				fb.setGetStrength(0);
			}
		}
	}

	/** 刷新时间  算法*/
	public boolean updataTime() {
		
		// 如果 要求时间 小于等于 当前时间  那么说明已经过天了  然后刷新数据
		if( SystemTimer.updateEveryDay( recordTime * 1000l ) ){
			resetGET( -1 ); // 领取体力值 
			initGV();		// 赠送体力值
			gvTimes		= 0;// 赠送体力值次数
			getTimes	= 0;// 领取体力值次数
			recordTime	= SystemTimer.currentTimeSecond(); // 记录时间
			Logs.debug( "好友系统执行了每日刷新" );
			return true;
		}
		
		return false;
	}





}
