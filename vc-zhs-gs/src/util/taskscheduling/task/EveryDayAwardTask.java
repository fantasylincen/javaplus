package util.taskscheduling.task;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import game.log.L;
import game.mail.MailBase;
import game.mail.MailType;

import java.io.IOException;
import java.util.Collection;
import java.util.TimerTask;

import lua.Lua;
import lua.LuaProxy;

import notice.NoticeManager;

import config.saward.SAwardType;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;
import define.GameData;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.UtilBase;


/**
 * 每日奖励任务
 * @author DXF
 */
public class EveryDayAwardTask extends TimerTask {

	@Override
	public void run() {
		++GameData.NUMERATIONTODAY;
		// 清空
//		NoticeManager.getInstance().clear();
		// ----------------记录每日 最大在线人数
		Logs.log( L.L_012, GameData.maxOnlineNumber + "," + GameData.maxOnlineTime );
		// 然后清空
		GameData.maxOnlineNumber 	= 0;
		GameData.maxOnlineTime		= 0;
		// 清空每日活跃人数
		GameData.dailyActiveUsers.clear();
		
		// 英雄主题周
		if( SetActivityEevet.isOpen4 > 0 ){
			if( ++SetActivityEevet.isOpen4 > 7 ) SetActivityEevet.isOpen4 = 0;
			SetActivityEevet.update();
		}
		// ----------------每日 都初始化一下 因为 他不会自动创建日志表
//		CLog.init();
		
		// ----------------下面更新每个玩家数据---------------------
		Collection<UserInfo> lists	= UserManager.getInstance().getMaps().values();
//		CLog.error( "每日执行更新  userSize=" + UserManager.getInstance().getMaps().size() );
//		long begin					= System.nanoTime();
		
		// 流逝人数
		int turnover 				= 0;
		int curTime					= SystemTimer.currentTimeSecond();
		
		// 这里直接将所有 人的拿来设置
		for( UserInfo user : lists ){
			
			if( user.numerationToDay == GameData.NUMERATIONTODAY )
				continue;
			user.numerationToDay = GameData.NUMERATIONTODAY;

//			synchronized (user) 
			{
				
				// 如果大于7天算流逝 60 * 60 * 24 * 7 = 604800;
				if( !user.isTurnover && curTime - user.getLastLogoutTime() >= 604800 ){
					user.isTurnover = true;
					++turnover;
				}
				
				user.getAwardManager().resetRecord( SAwardType.LAG, 1 );
				user.getAwardManager().resetRecord( SAwardType.LAM, 1 );
				user.getAwardManager().resetRecord( SAwardType.LAT, 0 );
//				user.getAwardManager().resetRecord( SAwardType.LAS, 1 );
				user.getAwardManager().resetRecord( SAwardType.STC, 0 );
				user.getAwardManager().resetRecord( SAwardType.YTC, 0 );
				user.getAwardManager().resetRecord( SAwardType.POP, 0 );
				user.getAwardManager().resetRecord( SAwardType.CCE1, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CCE2, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CCE3, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CCE4, 0 );
				user.getAwardManager().resetRecord( SAwardType.CEE1, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CEE2, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CEE3, 0 );
//				user.getAwardManager().resetRecord( SAwardType.CEE4, 0 );
				user.setIsCardTo10( (byte) 0 );
				
				// 如果有月卡
				boolean isM0	= user.handleMonthCardFate( 1 );
//				if( isM0 )
//					user.getAwardManager().record( SAwardType.LAMC, 1 );
//				else
//					user.getAwardManager().record( SAwardType.LAMC, -1 );
//				// 如果有月卡
				boolean isM1	= user.handleMonthCardFate( 2 );
//				if( isM1 )
//					user.getAwardManager().record( SAwardType.LAMC1, 1 );
//				else
//					user.getAwardManager().record( SAwardType.LAMC1, -1 );
//				// 如果有月卡
				boolean isM2	= user.handleMonthCardFate( 3 );
//				if( isM2 )
//					user.getAwardManager().record( SAwardType.LAMC2, 1 );
//				else
//					user.getAwardManager().record( SAwardType.LAMC2, -1 );
				
				
				// 重置购买次数
				for( int i = 0; i < user.allBuyTimes.length ; i++ )
					user.allBuyTimes[i] = 0;
				
				// 刷新玩家连续登陆奖励
				user.updateContrillerLoginToDay();
				
				// 初始挑战圣诞节活动副本次数
				user.christmasTimes = DefaultCfg.INIT_CHRISTMASTIMES;
				user.isGetChristmasDungeonAward = false;
				user.christmasRechargeToDay = false;
				user.christmasRecharge = 0;
				user.setRechargeMoney2( 0 );
				
				// 初始最强战神充值记录
				user.strongestAres = 0;
				
				// 初始购买体力次数
				user.setBuyStrCount( (byte) 0 );
				
				// 体力回满
				user.fullStrengthBack();
				
				// 初始匹配数据
				user.getDanGradingManager().updataTime();
				
				// 初始每日队长技能免费次数
				user.getCaptainSkillManager().init();
				
				// 初始精英副本次数
				user.getEliteEctypeCountManager().updataTime();
				
				// 初始排位和发放奖励
				user.getQualifyingManager().getQualifying().init();
				int rank		= GameData.ranking.indexOf( user.getUID() );
				if( rank != -1 && user.getLevel() >= DefaultCfg.NEED_RANKING_LEVEL ){
					
					LuaProxy lua 	= Lua.createLuaState( "gameData.lua" );
					Object[] ret 	= lua.retArray( 2, "getQualifyingRankingAward", rank + 1 );
					lua.close();
					int[] gold		= new int[2];
					gold[0]			= (int)(double)ret[0];
					gold[1]			= (int)(double)ret[1];
					
//					int[] gold 		= user.getQualifyingManager().getQualifying().startGetAwardToGold( rank + 1 );
					MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 210 + "|" + UtilBase.secondsToDate(SystemTimer.currentTimeSecond(), "yyyy.MM.dd" )
							 + "," + (rank + 1) + "," + gold[1] + "," + gold[0] + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
					user.getMailManager().addMail( mail );
					
					AwardInfo a 	= new AwardInfo( AwardType.GOLD, gold[1] );
					mail 	= new MailBase( a, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
					
					a				= new AwardInfo( AwardType.CASH, gold[0] );
					mail 			= new MailBase( a, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
//					user.getQualifyingManager().getQualifying().setIsGetAward( true );
					// 记录日志
					DataLogDataProvider.getInstance().add( user, ConsumelogF.RANK_LIST, gold[1] );
				}
				
				// 圣诞 每日通过邮件 免费 发放2次100点体力
				if( SetActivityEevet.isOpen10 ){
					AwardInfo a 	= new AwardInfo( AwardType.STRENGTH, 100 );
					MailBase mail 	= new MailBase( a, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
					a 				= new AwardInfo( AwardType.STRENGTH, 100 );
					mail 			= new MailBase( a, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
				}
				
				if ( user.isOnline() ){
					UpdateManager.instance.update( user, UpdateType.U_2 );
					UpdateManager.instance.update( user, UpdateType.U_14 );
					UpdateManager.instance.update( user, UpdateType.U_12 );
					UpdateManager.instance.update( user, UpdateType.U_13 );
					UpdateManager.instance.update( user, UpdateType.U_16 );
					UpdateManager.instance.update( user, UpdateType.U_25 );
					UpdateManager.instance.update( user, UpdateType.U_34 );
					UpdateManager.instance.update( user, UpdateType.U_35 );
					if( isM0 )
						UpdateManager.instance.update( user, UpdateType.U_28 );
					if( isM1 )
						UpdateManager.instance.update( user, UpdateType.U_31 );
					if( isM2 )
						UpdateManager.instance.update( user, UpdateType.U_32 );
				}
				
				// 记录可学习队长技能
				user.getCaptainSkillManager().updata();
				// 记录 系统奖励
				user.getAwardManager().updata();
				// 匹配信息
				user.getDanGradingManager().updata();
				// 精英副本次数信息
				user.getEliteEctypeCountManager().updataAll();
				// 玩家信息
				UserManager.getInstance().updata( user );
				// 玩家排位
				user.getQualifyingManager().update();
			}
			
		}
		
		// ----------------记录流失率
		Logs.log( L.L_015, turnover + "" );
		
		//		CLog.error( "每日执行更新 完成！用时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
	}
	
	public static void main( String[] args ) throws IOException{
		
	}

}
