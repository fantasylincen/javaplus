package game.activity;


import java.util.ArrayList;
import java.util.List;

import telnet.events.SetActivityEevet;

import lombok.Getter;
import lombok.Setter;

import config.activity.ActivityTemplet;
import config.activity.ActivityTempletCfg;

/**
 * 活动管理  这里暂时 只有大龙
 * @author DXF
 */
public class ActivityManager {
	
	private static ActivityManager instance = new ActivityManager();
	public static  ActivityManager getInstance(){
		return instance;
	}
	
	public boolean isConsumeOrgyIsOpen() {
		return consumeOrgyIsOpen;
	}

	public void setConsumeOrgyIsOpen(boolean consumeOrgyIsOpen) {
		this.consumeOrgyIsOpen = consumeOrgyIsOpen;
	}

	// 所有活动的 开启时间 
	private List<ActivityBase> 							_nActivitys = new ArrayList<ActivityBase>();
	
	// 这个数据库 主要记录 大龙数据
	private final ActivityDataProvider 					db = ActivityDataProvider.getInstance();

	// 消费狂欢日	是否开启	
	private boolean  									consumeOrgyIsOpen 	= false; // 
	
	// 高阶技能刷新日
	private boolean 									restoreIsOpen		= false;
	
	// 大龙信息
	private DragonBase									_dragonBase 		= null;
	private ActivityManager(  ) {
		
		addDefault();
		_dragonBase	= db.get();
		if( _dragonBase == null )
		{
			_dragonBase = new DragonBase();
			_dragonBase.init();
//			db.add( _dragonBase );
		}
	}
	
	public List<ActivityBase> getList() {
		List<ActivityBase> ret = new ArrayList<ActivityBase>();
		for( ActivityBase a : _nActivitys ){
			if( !SetActivityEevet.isOpen7 && a.getType() == 4 )
				continue;
			if( a.getNID() == 201 )// 大龙不显示
				continue;
			
			ret.add( a );
		}
		return ret;
	}

	public DragonBase getDragon() {
		return _dragonBase;
	}
	
	public ActivityBase get( short id ) {
		
		for( ActivityBase a : _nActivitys )
			if( a.getNID() == id ) return a;
		
		return null;
	}

	/** 每毫米 执行一次   */
	public void ticker( long t ) {
		
		for( ActivityBase a : _nActivitys ){
			a.ticker( t );
			
			// 大龙
			if( (int)(a.getNID() * 0.01) == 2 )
			{
				if( _dragonBase.isOpen() != a.isOpen() )
					_dragonBase.isOpen( a.isOpen() );
				
//				if( _dragonBase.isHandle() ) continue;
//				
//				// 如果结束 要发放奖励
//				if( !a.isOpen() && !_dragonBase.isDie() ){
//					_dragonBase.dieHandle();
//					update();
//				}
			}
			
		}
	}
	
	public void update(){
		db.update( _dragonBase );
	}
	
	// 添加 默认活动时间
	private void addDefault() {
		
		for( ActivityTemplet a : ActivityTempletCfg.getList()  ){
			
			ActivityBase act = new ActivityBase( a.m_nId );
			_nActivitys.add( act );
		}
		
	}

	public boolean isRestoreIsOpen() {
		return restoreIsOpen;
	}

	public void setRestoreIsOpen(boolean restoreIsOpen) {
		this.restoreIsOpen = restoreIsOpen;
	}

}
