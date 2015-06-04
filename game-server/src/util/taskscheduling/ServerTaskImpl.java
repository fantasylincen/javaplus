package util.taskscheduling;

import define.GameData;
import util.taskscheduling.task.EveryDayAwardTask;
import util.taskscheduling.task.EveryDayLogInitTask;
import util.taskscheduling.task.EveryDaySetTimeTask1;
import util.taskscheduling.task.EveryDaySetTimeTask2;
import util.taskscheduling.task.EveryDaySetTimeTask3;
import util.taskscheduling.task.EveryDaySetTimeTask4;
import util.taskscheduling.task.EveryMMaxOUesrNumTask;
import util.taskscheduling.task.EveryTMUpataNoticeTask;
import util.taskscheduling.task.OPENAreaThreeDayTask;
import util.taskscheduling.task.UserCountOfHourTask;

/**
 * 服务器任务调度 管理
 * @author DXF
 *
 */
public class ServerTaskImpl {

	
	private TaskScheduling task ;
	
	
	public ServerTaskImpl (){
		
		task = new TaskScheduling();
	}
	
	
	public void start(){

		task.execute( T.OMNI_MINUTE, new EveryMMaxOUesrNumTask() ); 		// 每分钟结算当前最大在线人数
		
		task.execute( T.OMNI_SECOND, new EveryTMUpataNoticeTask() ); 		// 每一秒更新公告信息 
		
		task.execute( T.OMNI_HORA, new UserCountOfHourTask() ); 			// 每小时用户在线情况  主要记录日志
		
		task.execute( T.LOG_INIT, new EveryDayLogInitTask() ); 				// 每日 日志初始化
		
		task.execute( T.EVERY_DAY, new EveryDayAwardTask() ); 				// 每日更新
		
//		task.execute( T.EVERY_DAY, new EveryDayMaxOUesrNumTask() ); 		// 每日最大在线人数
		
//		task.execute( T.EVERY_WEEK_FRONT, new EveryWeekFront() );				// 每周前
//		task.execute( T.EVERY_WEEK_BACK, new EveryWeekBack() );					// 每周后
	
		
		// 每日12点-14点   每日晚18点-20点
		task.execute( T.EVERY_12, new EveryDaySetTimeTask1() );					
		task.execute( T.EVERY_14, new EveryDaySetTimeTask2() );					
		task.execute( T.EVERY_18, new EveryDaySetTimeTask3() );					
		task.execute( T.EVERY_20, new EveryDaySetTimeTask4() );					
		
		if( GameData.isHaveMikkaLogin_3() )
			task.execute( T.OPEN_AREA_THREE_DAY, new OPENAreaThreeDayTask() ); 	// 从开区起3天
	}
	
	public static void main( String[] args ){
	 
		new ServerTaskImpl().start();
	}
}
