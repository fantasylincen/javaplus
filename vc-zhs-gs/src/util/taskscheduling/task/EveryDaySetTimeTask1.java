package util.taskscheduling.task;

import game.award.AwardInfo;
import game.award.AwardType;
import game.mail.MailBase;
import game.mail.MailType;

import java.util.TimerTask;

import notice.NoticeManager;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.UtilBase;

public class EveryDaySetTimeTask1 extends TimerTask{

	public static boolean 			isOpen = false;
	@Override
	public void run() {
		
		if( !SetActivityEevet.isOpen1 ) return;
		
		isOpen	= true;
		
		for( UserInfo user : UserManager.getInstance().getMaps().values() ){
			
			if( !user.isOnline() ) continue;
			
			user.isLingQvRiCahgn = true;
			MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 211 + "|" + 60 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
			user.getMailManager().addMail( mail );
			AwardInfo award_m	= new AwardInfo( AwardType.STRENGTH, 0, 60 );
			mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
			user.getMailManager().addMail( mail );
		}
		
	}

}
