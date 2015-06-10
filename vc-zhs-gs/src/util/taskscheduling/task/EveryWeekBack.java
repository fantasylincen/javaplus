package util.taskscheduling.task;


import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.util.TimerTask;

import user.UserInfo;
import user.UserManager;


public class EveryWeekBack extends TimerTask{

	@Override
	public void run() {
		
		for( UserInfo user : UserManager.getInstance().getMaps().values() ){
			user.getEliteEctypeCountManager().updataTime();
			UpdateManager.instance.update( user, UpdateType.U_15 );
		}
	}

	public static void runActivity() {
//		EveryWeekFront.isOpen = false;
//		
//		for( UserInfo user : UserManager.getInstance().getMaps().values() ){
//			user.setBuyStrCountMax( (byte) (user.getBuyStrCountMax() - 1) );
//			user.getEliteEctypeCountManager().updataTime();
//			UpdateManager.instance.update( user, UpdateType.U_15 );
//		}
	}

}
