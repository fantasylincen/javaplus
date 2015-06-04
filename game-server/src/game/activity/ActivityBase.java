package game.activity;

import config.activity.ActivityTemplet;
import config.activity.ActivityTempletCfg;
import util.SystemTimer;
import util.UtilBase;

/**
 * 单个活动 基础类
 * @author DXF
 */
public class ActivityBase {

	// 活动ID 这里是表格ID
	private short				_nId;
	
	// 是否开启 1,开启  0,关闭 
	private boolean 			_nIsOpen;
	
	// 剩余时间
	private int					_nSurplusTime;
	
	// 初始化 
	public ActivityBase( short id ){
		this._nId = id;
	}
	
	public short getNID() {
		return _nId;
	}
	
	public boolean isOpen() {
		return _nIsOpen;
	}
	
	/**
	 * 获得剩余时间
	 * @return
	 */
	public int updataTime(){
		
		long curTime			= SystemTimer.currentTimeMillis();
		
		// 获得当前 周几
		byte curWeek 			= UtilBase.getWeek( curTime );
		
		ActivityTemplet templet = ActivityTempletCfg.getTempletById( _nId );
		
		_nIsOpen				= templet.isBelong( curWeek );
//		_nIsOpen				= true;
		if( _nIsOpen ){ // 如果是属于这个周 看开启时间
			
			byte point			= templet.getTimePoint( curTime );
			if( point != 2 ) _nIsOpen = false;
			return templet.getSurplusTime( point, curTime, curWeek );
//			return 1000;
		}else{
			return templet.getSurplusTime( curTime, curWeek );
		}
		
	}


	public void close() {
		_nIsOpen = false;
	}

	/**
	 * 刷新时间
	 * @param t
	 */
	public void ticker( long t ) {
		_nSurplusTime = updataTime();
	}

	public int getSurplusTime() {
		return _nSurplusTime;
	}

	public int getType(){
		return _nId / 100;
	}
}


