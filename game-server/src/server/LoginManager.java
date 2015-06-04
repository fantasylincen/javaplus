package server;


import java.io.IOException;
import java.util.TimerTask;

import util.taskscheduling.T;
import util.taskscheduling.TaskScheduling;

import login.server.event.CheckCodeCreateEvent;
import login.server.event.CheckCodeEvent;
import login.server.event.EventList;
import login.server.event.PingEvent;


/**
 * 登陆管理  做了一个自动重连 当登陆服和游戏服5面没有通信将 自动重连
 * @author DXF
 *
 */
public class LoginManager {

	private static final LoginManager instance = new LoginManager();
	public static final LoginManager getInstance(){
		return instance;
	}
	private LoginManager() {	
		
	}
	
	private class CommandTask extends TimerTask{
		@Override
		public void run() {
			
//			if( loginPingTime == 0 ) return;
//			
//			// 延迟
//			int delay = SystemTimer.currentTimeSecond() - differTime - loginPingTime;
//			if( delay >= 2 )
//				CLog.debug( "游戏服和登陆服 延迟 ：" + delay + "秒 (超过5秒将自动重连)");
//			
//			// 大于5秒 那么就重新连接
//			if( delay > 5 )
//			{
//				CLog.error( "登陆服断开 准备重新连接!" );
//				try {
//					ServerManager.reLoadLogin();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			try {
				PingEvent p = (PingEvent)EventList.LOGIN_PING.getEventInstance();
				p.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//	private int loginPingTime					= 0;
//	private int differTime						= 0;
	
	private TaskScheduling 						task ;
	
	
	/**  核对验证码 
	 * @param index 
	 * @param key  
	 * @throws IOException */
	public void CheckVerificationCode( int index, String key ) throws IOException{
		CheckCodeEvent c = (CheckCodeEvent)EventList.CHECK_CODEEVENT.getEventInstance();
		c.run( index, key );
	}
	public void CheckVerificationCode1(int index, String key) throws IOException {
		CheckCodeCreateEvent c = (CheckCodeCreateEvent)EventList.CHECK_CODECREATE.getEventInstance();
		c.run( index, key );
	}
	
	public void setLoginPingTime( int t ){
//		loginPingTime 	= t;
//		differTime		= SystemTimer.currentTimeSecond() - t;
	}
	
	public void start(){
		task = new TaskScheduling();
		task.execute( T.OMNI_TEN_MINUTE, new CommandTask() );
	}
	
	public void stop(){
		task.stop();
		task 			= null;
//		loginPingTime	= 0;
//		differTime		= 0;
	}
	
}

