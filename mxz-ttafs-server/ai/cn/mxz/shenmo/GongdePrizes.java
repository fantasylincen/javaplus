package cn.mxz.shenmo;

import cn.mxz.RandomEventMeritTemplet;
import cn.mxz.RandomEventMeritTempletConfig;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class GongdePrizes {
	
	private final City					user;

	private UserCounter					counter;
	
	private static final int			MAX_PRIZE_COUNT = RandomEventMeritTempletConfig.getAll().size();
	
	GongdePrizes( City user ){
		this.user = user;		
		counter = user.getUserCounter();		
	}
	

	/**
	 * 功德领奖,index从1开始
	 */
	void getPrize( int index ){
		if( index < 1 || index > MAX_PRIZE_COUNT ){
			System.err.println( user.getId() + "此index不合法" + index );
			return;
		}
		if( counter.get( CounterKey.GONGDE_PRIZE, index ) == 1 ){
			System.err.println( user.getId() + "此index已经领奖" + index );
			return;
		}
		RandomEventMeritTemplet t = RandomEventMeritTempletConfig.get( index );
		if( counter.get( CounterKey.GONG_DE) < t.getMerit() ){
			System.err.println( user.getId() + "功德值不够" + index );
			return;
		}
		
		String prize = t.getAwards();
		PrizeSenderFactory.getPrizeSender().send( user.getPlayer(), prize );
		counter.set( CounterKey.GONGDE_PRIZE, 1, index );
	}
	
	/**
	 * 如果有功德奖励没有领取，应该给前端一个提示
	 * @return
	 */
	public boolean showGongdeTips(){
		
		for( int i = 1; i <= MAX_PRIZE_COUNT; i++ ){
			
			if( counter.get( CounterKey.GONGDE_PRIZE, i ) == 0 ){//此奖励尚未领取
				RandomEventMeritTemplet t = RandomEventMeritTempletConfig.get( i );
				if( counter.get( CounterKey.GONG_DE) >= t.getMerit() ){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 返回客户端显示的功德是否获奖的字符串，用逗号分隔，1代表领奖，0代表未领奖
	 * @return
	 */
	public String getPrizeStr(){
		String result = "";
		for( int i = 1; i <= MAX_PRIZE_COUNT; i++ ){
			result += counter.get( CounterKey.GONGDE_PRIZE, i ) + ",";
		}
//		result = result.substring( 0, endIndex)
		return result;		
	}


	public int getGongde() {
		return counter.get( CounterKey.GONG_DE);
	}
}
