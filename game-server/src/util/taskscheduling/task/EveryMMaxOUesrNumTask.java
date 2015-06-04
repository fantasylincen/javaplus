package util.taskscheduling.task;

import game.log.Logs;

import java.util.TimerTask;

import notice.NoticeManager;

import user.UserManager;
import util.SystemTimer;

import define.GameData;


/**
 * 每分钟最大在线人数
 * @author DXF
 */
public class EveryMMaxOUesrNumTask extends TimerTask{

	@Override
	public void run() {
		
		int curnumber = UserManager.getInstance().getMaps().size();
		
		if( curnumber > GameData.maxOnlineNumber ){
			GameData.maxOnlineNumber 	= curnumber;
			GameData.maxOnlineTime		= SystemTimer.currentTimeSecond();
			Logs.debug( "当前最大在线人数：" + curnumber ) ;
		}
		
		// 更新公告
		NoticeManager.getInstance().update();
	}

}
