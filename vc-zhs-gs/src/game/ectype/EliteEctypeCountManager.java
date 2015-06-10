package game.ectype;

import game.activity.ActivityManager;
import game.award.AwardType;
import game.log.Logs;

import java.util.ArrayList;
import java.util.List;

import manager.DWType;
import config.mission.EctypeType;
import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import define.DefaultCfg;
import define.SystemCfg;
import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 英雄副本 次数记录 管理
 * @author DXF
 *
 */
public class EliteEctypeCountManager {

	private UserInfo							user;
	
	// 记录时间
	public static int 							lastTime = SystemTimer.currentTimeSecond();
	
	private List<EliteCountBase> 				lists;
	
	// 小龙 (经验)
	private List<Byte> 							dragonetLists;
	
	// 小龙 (金币)
	private List<Byte> 							dragonetLists1;
	
	// 试炼
	private List<List<Byte>>					torefineWithFire;
	
	
	private final EliteEctypeDataProvider 		db = EliteEctypeDataProvider.getInstance();
	
	@SuppressWarnings("unchecked")
	public EliteEctypeCountManager( UserInfo user ){
		this.user 		= user;
		Object[] obj	= db.get( user );
		lists 			= (List<EliteCountBase>) obj[0];
		dragonetLists	= (List<Byte>) obj[1];
		dragonetLists1	= (List<Byte>) obj[2];
		torefineWithFire= (List<List<Byte>>) obj[3];
		if( dragonetLists.isEmpty() ){
			//暂时只有3个
			dragonetLists.add( DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists.add( DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists.add( DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists1.add( DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists1.add( DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists1.add( DefaultCfg.ELITE_TODAY_COUNT );
			// 初始试炼
			List<Byte> ls = new ArrayList<Byte>();
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			torefineWithFire.add(ls);
			ls = new ArrayList<Byte>();
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			torefineWithFire.add(ls);
			ls = new ArrayList<Byte>();
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			ls.add( DefaultCfg.ELITE_TODAY_COUNT );
			torefineWithFire.add(ls);
			db.add( user, lists, dragonetLists, dragonetLists1, torefineWithFire );
		}
	}

	public void initElite(){
		for( EliteCountBase e : lists )
			e.init();
	}
	
	public EliteCountBase get( short mid ) {
		
		for( EliteCountBase e : lists ){
			if( e.m_nMid == mid )
				return e;
		}
		return null;
	}
	
	public EliteCount get( short mid, int pid ) {
		
		EliteCountBase ecb = get( mid );
		if( ecb == null ) return null;
		
		return ecb.get(pid);
	}
	
	public List<Byte> getDragonetLists( int id ){
		return id % 10 == 1 ? dragonetLists : dragonetLists1;
	}
	
	/**
	 * 添加一个 信息
	 * @param mid
	 * @param pid
	 */
	public void add( short mid, int pid ){
		
		// 前面先判断 
		InstanceTemplet templet = InstanceTempletCfg.getTempletById( mid );
	
		if( templet == null ){
			Logs.error( user, "添加英雄副本次数信息出错  没有 mid=" + mid + " 这个副本！" );
			return;
		}
		if( templet.getType() != EctypeType.ELITE ) 
			return;
		
		if( !templet.isHaveID(pid) ){
			Logs.error( user, "添加英雄副本次数信息出错 副本ID=" + mid + " 没有pid=" + pid + "这个关卡！" );
			return;
		}
		
		// 在加入 之前判断下 看是否第一次加入数据
//		boolean isOne = lists.isEmpty() ;
		
		// 下面 在直接加入
		put( mid, pid );
		
		// 刷新数据库 这个要及时刷新 因为牵扯到 RMB
//		if( isOne )
//			db.add( user, lists );
//		else
			updata();
	}
	
	/**
	 * 刷新数据
	 * @param mid
	 * @param pid
	 */
	public void update( short mid, int pid ){
		
		EliteCountBase 	ecb = get( mid );
		if( ecb == null ) return;

		EliteCount		ec	= ecb.get( pid );
		if( ec == null ) return;
		
		// 在这 直接减 
		--ec.m_nTodayCount ;
		
		// 及时 刷新数据库
		updata();
	}
	
	/**
	 * 刷新扫荡
	 * @param id
	 * @param missionId
	 * @param sweepCount
	 * @return
	 */
	public boolean update( short id, int missionId, byte sweepCount ) {
		
		EliteCount ec = get( id , missionId );
		if( ec == null ) return true;
		if( ec.m_nTodayCount < sweepCount ) return true;
		
		ec.m_nTodayCount -= sweepCount;
		updata();
		
		return false;
	}
	
	/** 刷新数据库 */
	public void updata(){
		db.updata( user, lists );
	}
	
	// 单个放入
	private void put( short mid, int pid ){
		
		EliteCount ec = new EliteCount( pid );
		
//		if( ActivityManager.getInstance().isConsumeOrgyIsOpen() )
//			ec.activityAdd();
		
		for( EliteCountBase e : lists ){
			
			if( e.m_nMid == mid ){
				e.m_nPList.add( ec );
				return;
			}
		}
		
		EliteCountBase ecb 	= new EliteCountBase();
		ecb.m_nMid 			= mid;
		ecb.m_nPList.add( ec );
		lists.add( ecb );
	}

	/**
	 * 检查  是否次数足够
	 * @param id
	 * @param missionId
	 * @return
	 */
	public boolean checkCount( short id, int missionId ) {
		 
		EliteCountBase 	ecb = get( id );
		if( ecb == null ) return false;

		EliteCount		ec	= ecb.get( missionId );
		if( ec == null ) return false;
		
		byte count = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? ec.m_nTodayCount + DefaultCfg.ELITE_TODAY_COUNT : ec.m_nTodayCount);
		return count > 0;
	}

	/**
	 * 购买次数
	 * @param count
	 * @param mid
	 * @param pid
	 * @return
	 */
	public ErrorCode buyCount( byte count, short mid, int pid ) {
		
		if( count < 1 ) return ErrorCode.UNKNOW_ERROR;
		
		EliteCountBase 	ecb = get( mid );
		if( ecb == null ) return ErrorCode.UNKNOW_ERROR;

		EliteCount		ec	= ecb.get( pid );
		if( ec == null ) return ErrorCode.UNKNOW_ERROR;

		if( ec.m_nBuyCount <= 0 ) return ErrorCode.ECTYPE_NOT_COUNT;
		
		int needGold 		= count == 1 ? DefaultCfg.ELITE_BUYCOUNT_GOLD : ec.m_nBuyCount * DefaultCfg.ELITE_BUYCOUNT_GOLD;
		if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
		if( user.changeAward( AwardType.GOLD, -needGold, "购买英雄副本次数 消耗水晶", DWType.BUYING_COPY_NUMBER ) == -1 ) 
			return ErrorCode.USER_GOLD_NOT_ENOUTH;
		
		ec.m_nTodayCount 	+= ec.m_nBuyCount;
		ec.m_nBuyCount		= 0;
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 判断小龙次数 是否足够
	 * @param id
	 * @param index 
	 * @return
	 */
	public boolean checkDragonetCount ( int id, int index ) {
		
		int type = id / 100;
		
		// 小龙
		if( type == 1 ){
			
			List<Byte> ls 	= id%10 == 1 ? dragonetLists : dragonetLists1;
			byte count 		= (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() && index != 2 ? 
					ls.get(index) + DefaultCfg.ELITE_TODAY_COUNT : ls.get(index));
			
			return count > 0;
		
		// 试炼
		}else if( type == 3 ){
			return torefineWithFire.get(id % 10 - 1).get(index) > 0;
			
		// 大龙
		}else if( type == 2 ){
			
			return true;
		}else if( type == 4 ){
			
			return true;
		}
		
		return false;
	}
	
	//初始小龙
	public void initDragonet(){
		for( int i = 0; i < dragonetLists.size(); i++ ){
			dragonetLists.set( i, DefaultCfg.ELITE_TODAY_COUNT );
			dragonetLists1.set( i, DefaultCfg.ELITE_TODAY_COUNT );
		}
		
		for( List<Byte> ls : torefineWithFire ){
			for( int i = 0; i < ls.size(); i++ )
				ls.set( i, DefaultCfg.ELITE_TODAY_COUNT );
		}
	}
	
	public void update( int id, int index ) {
		// 在这 直接减 
		
		int type = id / 100;
		
		if( type == 1 ){
			int at = id % 10;
			if( at == 1 ){
				byte count = dragonetLists.get(index);
//				if( --count < 0 ) count = 0;
				dragonetLists.set( index, --count );
			}else if( at == 2 ){
				byte count = dragonetLists1.get(index);
//				if( --count < 0 ) count = 0;
				dragonetLists1.set( index, --count );
			}
			// 及时刷新
			updataDragonet();
			
		}else if( type == 3 ){
			byte count = torefineWithFire.get(id % 10).get(index);
			if( --count < 0 ) count = 0;
			torefineWithFire.get(id % 10).set( index, count );
			
			updataTorefine();
			
		}
		
		
	}
	
	public void updataTorefine() {
		db.updataTorefine( user, torefineWithFire );
	}

	public void updataDragonet(){
		db.updataDragonet( user, dragonetLists, dragonetLists1 );
	}

	/**
	 * 每日更新 在外层 刷新数据库
	 * @return
	 */
	public boolean updataTime() {
		
//		if( SystemTimer.updateEveryDay( EliteEctypeCountManager.lastTime * 1000l ) ){
			initElite();
			initDragonet();
//			EliteEctypeCountManager.lastTime = SystemTimer.currentTimeSecond();
//			return true;
//		}
		
		return false;
	}

	public void updataAll() {
		db.updataAll( user, lists, dragonetLists, dragonetLists1, torefineWithFire );
	}

	/** 活动增加 */
//	内容：增加购买体力次数一次，
//	精英副本挑战次数翻倍，
//	活动副本：经验与金币小龙， 挑战次数翻倍;  （注：仅仅只有初级与中级翻倍）
	public void activityAdd() {
		int size = dragonetLists.size() - 1;
		for( int i = 0; i < size; i++ ){
			dragonetLists.set( i, (byte) ( DefaultCfg.ELITE_TODAY_COUNT*2 ) );
			dragonetLists1.set( i, (byte) ( DefaultCfg.ELITE_TODAY_COUNT*2 ) );
		}
		dragonetLists.set( size, DefaultCfg.ELITE_TODAY_COUNT );
		dragonetLists1.set( size, DefaultCfg.ELITE_TODAY_COUNT );
		for( EliteCountBase e : lists )
			e.activityAdd();
	}

	
}


