package util.taskscheduling.task;

import game.award.AwardInfo;
import game.award.AwardType;
import game.mail.MailBase;
import game.mail.MailType;

import java.util.TimerTask;

import notice.NoticeManager;

import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.GameData;

import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.UtilBase;

/**
 * 		从开区起， 3日 内（从开区即算起走），等级>=35级即可获得奖励。
		奖励分为 >=35 ，>=45 ，>=55 ，>=65 四个等级，每个等级的奖励都不同。
		奖励将会在开区 第4日 的凌晨00：01统一发放。

 * @author DXF
 */
public class OPENAreaThreeDayTask extends TimerTask{

	@Override
	public void run() {
		
		if( !GameData.isHaveMikkaLogin_3() )
			return;
		
		// 已经过了 开服3天
//		GameData.isHaveMikkaLogin = false;
		
		int list[][] = {
				
				{ 50, 3000 },
				{ 45, 2000 },
				{ 35, 1200 },
				{ 25, 800 }
		};
		
		// >=25将获得800水晶，>=35将获得1200水晶，>=45将获得2000水晶，>=50将获得3000水晶
		int i 		= 0;
		int level	= 0;
		
		for( UserInfo u : UserManager.getInstance().getMaps().values()  ){
			
			level 	= u.getLevel();
			
			for( i = 0; i < list.length; i++ ){
				
				if( level >= list[i][0] ){
					addMail( u, list[i][1] );
					break;
				}
			}
		}
		
		
		
	}
	
	// 添加水晶
	private void addMail( UserInfo u, int gold ){
		MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 213 + "|" + u.getLevel() + "," + gold + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
		u.getMailManager().addMail( mail );
		mail 			= new MailBase( new AwardInfo(AwardType.GOLD, gold), MailType.SYSTEM_PRESENT );
		u.getMailManager().addMail( mail );
		DataLogDataProvider.getInstance().add( u, ConsumelogF.DISTRIBUTED_SYSTEM, gold );
	}

}

