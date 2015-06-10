package util.taskscheduling.task;

import java.util.TimerTask;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;

public class EveryDaySetTimeTask2 extends TimerTask{

	@Override
	public void run() {
		
		if( !SetActivityEevet.isOpen1 ) return;
		
		EveryDaySetTimeTask1.isOpen = false;
		
		for( UserInfo user : UserManager.getInstance().getMaps().values() )
			user.isLingQvRiCahgn = false;
	}

}
