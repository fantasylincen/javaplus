package util;

import game.activity.ActivityManager;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import deng.xxoo.utils.XOTime;

import user.UserInfo;
import user.UserManager;

/**
 * 通过一个独立的线程提供系统时间，减少系统调用
 * 
 * @author admin
 * 
 */
public class SystemTimer {
	private static final ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

	private SystemTimer() {
	};

	private volatile static long time 	= System.currentTimeMillis();
	private volatile static long t 		= 0;
	private volatile static long t1 	= 0;
	private final static int TICK_UNIT 	= 50;
	
	private static class TimerTicker implements Runnable {

		@Override
		public void run() {
			
			// 一秒执行一次的
			if( ( time - t ) >= 1000 ){ 
				t = time;
				update_1000( time );
			}
			
			if( (time - t1) >= 500 ){
				t1 = time;
				update_500( time );
			}
			
			update_50( time );
			
			time = System.currentTimeMillis();
		}

	}

	static {
//		s.scheduleAtFixedRate( new TimerTicker(), TICK_UNIT, TICK_UNIT, TimeUnit.MILLISECONDS );
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				s.shutdown();
//			}
//		});
	}
	
	public static void start(){
		s.scheduleAtFixedRate( new TimerTicker(), TICK_UNIT, TICK_UNIT, TimeUnit.MILLISECONDS );
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
//				s.shutdown();
			}
		});
	}

	/**
	 * 获得系统时间 毫秒
	 * @return
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 计算从当前时间currentDate开始，满足条件dayOfWeek, hourOfDay, 
	 * minuteOfHour, secondOfMinite的最近时间
	 * @return
	 */
	public static long getEarliestDate( int dayOfWeek, int hourOfDay, int minuteOfHour, int secondOfMinite ){
		
//		//计算当前时间的WEEK_OF_YEAR,DAY_OF_WEEK, HOUR_OF_DAY, MINUTE,SECOND等各个字段值
//		int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
//		int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
//		int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
//		int currentMinute = currentDate.get(Calendar.MINUTE);
//		int currentSecond = currentDate.get(Calendar.SECOND);
//
//		//如果输入条件中的dayOfWeek小于当前日期的dayOfWeek,则WEEK_OF_YEAR需要推迟一周
//		boolean weekLater = false;
//		if (dayOfWeek < currentDayOfWeek) {
//			weekLater = true;
//		} else if (dayOfWeek == currentDayOfWeek) {
//			//当输入条件与当前日期的dayOfWeek相等时，如果输入条件中的
//			//hourOfDay小于当前日期的
//			//currentHour，则WEEK_OF_YEAR需要推迟一周	
//			if (hourOfDay < currentHour) {
//				weekLater = true;
//			} else if (hourOfDay == currentHour) {
//                 //当输入条件与当前日期的dayOfWeek, hourOfDay相等时，
//                 //如果输入条件中的minuteOfHour小于当前日期的
//				//currentMinute，则WEEK_OF_YEAR需要推迟一周
//				if (minuteOfHour < currentMinute) {
//					weekLater = true;
//				} else if (minuteOfHour == currentSecond) {
//                     //当输入条件与当前日期的dayOfWeek, hourOfDay， 
//                     //minuteOfHour相等时，如果输入条件中的
//                    //secondOfMinite小于当前日期的currentSecond，
//                    //则WEEK_OF_YEAR需要推迟一周
//					if (secondOfMinite < currentSecond) {
//						weekLater = true;
//					}
//				}
//			}
//		}
//		if (weekLater) {
//			//设置当前日期中的WEEK_OF_YEAR为当前周推迟一周
//			currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);
//		}
//		// 设置当前日期中的DAY_OF_WEEK,HOUR_OF_DAY,MINUTE,SECOND为输入条件中的值。
//		currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
//		currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
//		currentDate.set(Calendar.MINUTE, minuteOfHour);
//		currentDate.set(Calendar.SECOND, secondOfMinite);
//		return currentDate;
		Calendar currentDate 	= Calendar.getInstance();
		if( hourOfDay != -1 )
			currentDate.set( Calendar.HOUR_OF_DAY, hourOfDay );
		if( minuteOfHour != -1 )
			currentDate.set( Calendar.MINUTE, minuteOfHour );
		if( secondOfMinite != -1 )
			currentDate.set( Calendar.SECOND, secondOfMinite );
		
		return currentDate.getTimeInMillis();
	}
	
	/**
	 * 计算从当前时间currentDate开始，满足条件dayOfWeek, hourOfDay, 
	 * minuteOfHour, secondOfMinite的最近时间
	 * @return
	 */
	public static long getEarliestDate( long currentTime, int dayOfWeek, int hourOfDay, int minuteOfHour, int secondOfMinite ) {
		
		Calendar currentDate 	= Calendar.getInstance();
		currentDate.setTimeInMillis( currentTime );
		
		if( hourOfDay != -1 )
			currentDate.set( Calendar.HOUR_OF_DAY, hourOfDay );
		if( minuteOfHour != -1 )
			currentDate.set( Calendar.MINUTE, minuteOfHour );
		if( secondOfMinite != -1 )
			currentDate.set( Calendar.SECOND, secondOfMinite );
		
		return currentDate.getTimeInMillis();
	}
	
	/**
	 * 单个主动  更新每日
	 * @param l
	 * @return
	 */
	public static boolean updateEveryDay( long l ) {
		// 如果 要求时间 小于等于 当前时间  那么说明已经过天了  然后刷新数据
		return XOTime.refTimeInMillis( l, 24, 0, 0 ) < System.currentTimeMillis();
	}
	/** 获取当前到00:00:00的毫秒数 */
	public static long updateEveryDayTotime( long l ){
		return System.currentTimeMillis() - XOTime.refTimeInMillis( l, 24, 0, 0 );
	}
	
	/**
	 * 获得系统时间  秒
	 * @return
	 */
	public static int currentTimeSecond() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	// 1000毫秒执行一次
	private static void update_1000( long t ){
		// 活动系统 时间刷新
		ActivityManager.getInstance().ticker( t );
		
		
	}
	
	// 500毫秒执行一次
	private static void update_500( long t ){
		
	}
	
	// 50毫秒执行一次
	private static void update_50( long t ){
		
		// 刷新每个英雄的 删除和创建
		for( UserInfo u : UserManager.getInstance().getMaps().values() ){
//			synchronized ( u ) {
				u.getHeroManager().runnable();
//			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(currentTimeMillis());
//			Thread.sleep(1000);
//		}
		
		start();
	}

	
}