package util.taskscheduling.task;

import game.activity.ActivityManager;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.util.TimerTask;

import user.UserInfo;
import user.UserManager;

public class EveryWeekFront extends TimerTask {
	
	@Override
	public void run() {
		
		if( !ActivityManager.getInstance().isConsumeOrgyIsOpen() ) 
			return;
		
		runActivity();
	}

	public static void runActivity() {
		for( UserInfo user : UserManager.getInstance().getMaps().values() ){
			// 精英副本挑战次数翻倍，活动副本:小龙（经验、金币）， 挑战次数翻倍
			user.getEliteEctypeCountManager().activityAdd();
			
			UpdateManager.instance.update( user, UpdateType.U_15 );
		}
	}

}
