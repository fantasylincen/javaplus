package game.fightoffriend;


import game.fighter.Hero;
import game.friend.FriendBase;
import game.util.fighting.FightingFormula;

import java.util.ArrayList;
import java.util.List;

import define.DefaultCfg;

import user.UserInfo;
import user.UserManager;
import util.SystemTimer;

/**
 * 副本战斗前 的好友列表 管理
 * @author DXF
 *
 */
public class FightOfFriendManager {

	/** 邀请非好友列表上限 */
	private static final short	 						MAX_NUM 						= 4;
	
	// 战斗力 相差 按百分比
	private static final int	 						COMBAT_EFFECTIVENESS_DIFFER 	= 10;
	
	private UserInfo									user;
	
	// 所有人数
	private List<FightOfFriendBase> 					lists;
	
	// 记录所有人数
	private List<RecordFightBase> 						recordLists;
	
	// 当前页
	private byte 										curPage;
	
	// 最大页
	private byte 										maxPage;
	
	public FightOfFriendManager( UserInfo user ) {
		lists		= new ArrayList<FightOfFriendBase>();
		recordLists	= new ArrayList<RecordFightBase>();
		this.user 	= user;
		updata();
	}
	
	/**
	 * 根据 用户ID 获取 玩家信息
	 * @param assistID
	 * @return
	 */
	public FightOfFriendBase getUIDToUserID( int assistID ) {
		
		for( FightOfFriendBase f : lists )
			if( f.m_nUID == assistID ) return f;
		
		return null;
	}
	
	public byte getCurPage(){
		return curPage;
	}
	public byte getMaxPage(){
		return maxPage;
	}
	public short getCurNum(){
		return (short)lists.size();
	}
	
	/**
	 * 根据页数获取 数据
	 * @param page
	 * @return
	 */
	public List<FightOfFriendBase> get( byte page ){
		
		List<FightOfFriendBase> list = new ArrayList<FightOfFriendBase>();
		
		if( page > maxPage ) page = maxPage;
		if( page < 1 ) page = 1;
		
		curPage		= page;
		
		int index 	= (page - 1) * DefaultCfg.PAGE_MAX;
		for( int i = index; i < lists.size() && list.size() < DefaultCfg.PAGE_MAX; i++ ){
			list.add( lists.get(i) );
			if( list.size() >= 10 ) break;
		}
		
		return list;
	}
	
	public void put( int assistID ) {
		RecordFightBase r = new RecordFightBase( assistID, SystemTimer.currentTimeSecond() );
		recordLists.add( r );
	}
	
	// 刷新数据
	public void updata(  ){
		lists.clear();
		
		List<UserInfo> ls 	= UserManager.getInstance().getMemoryAllUser();
		
		// 先刷新记录数据
		updataRecordLists();
		
		addExpert( ls );
		
		addAgoodfriend( ls );

		addFriend();
		
		if( lists.size() < MAX_NUM ){
			addFinallyfriend( ls ); //张弛说 没有人 就让他没有人
		}
		
		curPage			= 1;
		maxPage 		= (byte) (lists.size() / DefaultCfg.PAGE_MAX);
		if( lists.size() % DefaultCfg.PAGE_MAX != 0 )
			maxPage		+= 1;
		
		sort();
	}
	
	

	// 排序
	private void sort(){
		
	}
	
	// 添加高级 非好友 2个
	private void addExpert(List<UserInfo> ls) {
		
		int a 	= 999999999;
		int b	= 999999999;
		UserInfo u1 = null;
		UserInfo u2 = null;
		
		int userFighting 	= FightingFormula.run( user );
		for( UserInfo u : ls ){
			if( u.getUID() == user.getUID() || check( u.getUID() ) || user.getFriendManager().isFriend( u.getUID() ) )
				 continue;
			
			int c 		= FightingFormula.run( u );
			float od 	= (float)c/(float)userFighting;
			
			if( od >= 2 ){
				
				if( c < a || c < b ){
					
					if( a >= b ){
						a 	= c;
						u1 	= u;
					}else{
						b	= c;
						u2	= u;
					}
				}
			}
		}
		
		if( u1 != null )
			add( u1, a, false, 1000 );
		if( u2 != null )
			add( u2, b, false, 1000 );
	}
	
	// 加入好友
	private void addFriend(){
		
		int i				= 0;
		for( FriendBase f : user.getFriendManager().getListFriend() ){
			UserInfo temp			= UserManager.getInstance().getByName( f.getUid() );
			if( temp == null || check( f.getUid() ) ) continue;
			
			if( ++i > MAX_NUM ) return;
			
			if( !add( temp, FightingFormula.run( temp ), true, 0 ) )
				--i;
		}
	}
	
	// 加入非好友
	private void addAgoodfriend( List<UserInfo> ls ){
		
		int i 				= lists.size();
		float userFighting 	= FightingFormula.run( user );
		
		int value			= COMBAT_EFFECTIVENESS_DIFFER;
		while( (value += 10) <= 90 ){
			
			for( UserInfo u : ls ){
				if( i >= MAX_NUM*2 ) return;
				if( u.getUID() == user.getUID() || check( u.getUID() ) || user.getFriendManager().isFriend( u.getUID() ) )
					 continue;
				
				float fighting 	= FightingFormula.run( u );
				
				float f			= (1f - ( fighting / userFighting ) ) * 100f;
				
				if( Math.abs( f ) <= value ){
					if( add( u, (int)fighting, false, 0 ) )
						i++;
				}
			}
			
		}
		
	}
	
	// 最后添加
	private void addFinallyfriend( List<UserInfo> ls ){
		
		int i 					= lists.size();
		for( UserInfo u : ls ){
			
			if( u.getUID() == user.getUID() || check( u.getUID() ) || user.getFriendManager().isFriend( u.getUID() ) ) continue;
			
			if( i++ > MAX_NUM*2 ) return;
			
			int fighting 		= FightingFormula.run( u );
			if( !add( u, fighting, false, 0 ) )
				--i;
		}
	}
	
	
	private boolean add( UserInfo u, int fighting, boolean isfriend, int b ){
		
		if( u == null ) return false;
		
		FightOfFriendBase fof 	= new FightOfFriendBase();
		fof.m_nUID 				= u.getUID();
		fof.m_nName 			= u.getNickName();
		fof.m_nLevel			= u.getLevel();
		fof.m_nFighting			= fighting;
		fof.m_nIsFriend			= isfriend;
		fof.m_nIsExpert			= b;
		
		Hero hero				= u.getTeamManager().getCaptain();
		if( hero == null ) return false;
		fof.m_nCaptainHero		= new Hero( hero );
		
		lists.add( fof );
		
		return true;
	}
	
	private boolean check( int uid ) {
		for( RecordFightBase r : recordLists )
			if( r.m_uID == uid  ) return true;
		for( FightOfFriendBase f : lists )
			if( f.m_nUID == uid ) return true;
		
		UserInfo u	= UserManager.getInstance().getByName( uid );
		Hero hero	= u.getTeamManager().getCaptain();
		if( hero == null ) return true;
		// 这里是检查携带等级
		if( user.getLevel() < hero.getCarryLevel() ) 
			return true;
		
		return false;
	}

	private void updataRecordLists() {
		
		int t						= SystemTimer.currentTimeSecond();
		
		int i 						= 0;
		while( i < recordLists.size() ){
			
			RecordFightBase r 		= recordLists.get(i);
			if( t - r.m_recordTime >= DefaultCfg.FIGHT_INVITE_MAXTIME ) {
				recordLists.set( i, recordLists.get(recordLists.size() - 1) );
				recordLists.remove( recordLists.size() - 1 );
			}else{
				i ++;
			}
		}
		
	}
	
}
