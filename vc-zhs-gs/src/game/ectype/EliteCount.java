package game.ectype;

import game.log.Logs;

import util.SystemTimer;
import define.DefaultCfg;

/**
 * 单个关卡的 记录信息
 * @author DXF
 */
public class EliteCount {
	
	/**
	 * 关卡ID
	 */
	public int 		m_nPid;
	
	/**
	 * 每日免费次数
	 */
	public byte 	m_nTodayCount;
	
	/**
	 * 可购买次数 
	 */
	public byte 	m_nBuyCount;
	
	/**
	 * 用于数据库
	 */
	public EliteCount(){}
	
	public EliteCount( int pid ) {
		m_nPid 			= pid;
		
		init();
	}
	
	/**
	 * 刷新一下 时间
	 * @return 
	 */
	public boolean updataTime(){
		
		if( SystemTimer.updateEveryDay( EliteEctypeCountManager.lastTime * 1000l ) ){
			
			init();		// 赠送体力值
			Logs.debug( "英雄副本次数记录执行了每日刷新" );
			return true;
		}
		
		return false;
	}

	public void init() {
		m_nTodayCount 	= DefaultCfg.ELITE_TODAY_COUNT;
		m_nBuyCount		= DefaultCfg.ELITE_BUY_COUNT;
	}

	public void activityAdd() {
		m_nTodayCount 	= DefaultCfg.ELITE_TODAY_COUNT*2;
		m_nBuyCount		= DefaultCfg.ELITE_BUY_COUNT;
	}
	
	
}
