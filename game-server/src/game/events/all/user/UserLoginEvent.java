package game.events.all.user;

import game.activity.ActivityManager;
import game.award.AwardInfo;
import game.award.AwardType;
import game.award.ectype.EctypeAward;
import game.battle.dbinfo.BattleInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.friend.FriendBase;
import game.log.Logs;
import game.mail.MailBase;
import game.mail.MailType;
import game.team.AssistBase;
import game.team.TeamManager;
import game.util.fighting.FightingFormula;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import login.server.event.CheckCodeEvent;
import login.server.event.EventList;
import lua.Lua;
import lua.LuaProxy;
import manager.DWType;
import notice.NoticeManager;

import org.xsocket.connection.INonBlockingConnection;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import user.UserStatus;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import util.taskscheduling.task.EveryDaySetTimeTask1;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;
import config.saward.SAwardType;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;
import define.GameData;


@EventDescrip(desc = "玩家登陆包", structure = "short用户名长度,byte[]用户名")
public class UserLoginEvent extends EventBase {
	
	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
	}
	public void run( UserInfo user, ErrorCode code ) throws IOException {
	}
	
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		try {
		
		int index 		= buf.getInt();
		String key 		= UtilBase.decodeString( buf );
		
		// 根据账号获取 玩家
		UserInfo user 	= UserManager.getInstance().getByName( index );
		
		ErrorCode code 	= ErrorCode.SUCCESS;
		if( user == null ){
			code = ErrorCode.USER_NOT_FOUND;
		}else if( user.getStatus() == UserStatus.BAN )
			code = ErrorCode.USER_HAS_BAN;
		
		if( code == ErrorCode.SUCCESS ){
			
			EctypeAward ectypeAward = user.getEctypeReconnectManager().getReadyAwards();
			if( ectypeAward != null ){
				BattleInfo b = user.getBattleInfoManager().getBattleByteData( ectypeAward.getFightId() );
				TollgateTemplet missionTemplet = TollgateTempletCfg.getTempletById( ectypeAward.getId() );
				if( b.getTheLv() < (missionTemplet.getThelvNum() - 1) && ectypeAward.getIsWin() ){
					code = ErrorCode.FIGHTER_STATUS;
//					ectypeAward.setTheLv( (byte) (ectypeAward.getTheLv() + 1) );
				}else{
					// 这里结算如果是邀请了高级玩家 的 花费
					int needmoney = user.getTeamManager().expertNeedMoney();
					if( needmoney != 0 ){
						user.changeAward( AwardType.CASH, -needmoney, "邀请高级玩家消耗金币", DWType.MISCELLANEOUS );
						UpdateManager.instance.update( user, UpdateType.U_3 );
					}
					user.ePropertyType = -1;
					user.getEctypeReconnectManager().remove();
					// 这里把 协助好友 清除掉
					user.getTeamManager().claerAssist();
					user.getTeamManager().updata();
					// 
				}
			}
		}
		
		ByteBuffer buffer 		= buildEmptyPackage( 1024 );
		buffer.putShort( (short) code.ordinal() );
		
		boolean isCL = false;
		boolean isFirstToDay = false;
		
		if( code == ErrorCode.SUCCESS || code == ErrorCode.FIGHTER_STATUS ){
			
			UserManager.getInstance().putOnline( index, user );
			UserManager.getInstance().removeOffline( index );
			
			isFirstToDay = user.isFirstLogin();

			// 刷新连续登陆
			if( isFirstToDay ) isCL = user.updateContrillerLogin();
			
			user.setConLogin( con, index );
			// 获得 终端标识 为了记录日志
//			Logs.log( L.L_002 , 
//					user.getUID() + "," +
//					user.getNickName() + ",vc," + user.isHaveMonthCard() 
//					);
			// 记录登陆时间   不用保存数据库
			user.setLoginTime( SystemTimer.currentTimeSecond() );
			
			///// 下面开始发数据
			buffer.putInt( user.getUID() );							//用户ID
			UtilBase.encodeString( buffer, user.getNickName() );	//昵称
			buffer.putShort( user.getLevel() );						//等级
			buffer.putInt( user.getCurExp() );						//当前经验
			int replyTime = user.getStrReplyTimeToSecond( 1 );		//这里先获得 是因为要换算
			buffer.putShort( user.getStrength() );					//当前体力
			buffer.putShort( user.getStrengthMax() );				//体力上限
			buffer.putInt( replyTime );								//体力回复时间
			buffer.putInt( user.getCash() );						//金币
			buffer.putInt( user.getGold() );						//水晶
			buffer.putInt( FightingFormula.run( user ) );			//战斗力
			buffer.putInt( user.getFriendshipValue() );				//友情值
			buffer.put( user.getMailManager().isHaveRead() );		//是否有未读邮件
			buffer.put( user.getDanGradingManager().getInfo().danGrad().toNumber() );	//匹配段位
			buffer.putInt( user.getDanGradingManager().getInfo().grade() );				//匹配积分
			buffer.putShort( user.getBagCapacity() );				//英雄背包上限
			buffer.putShort( user.getFriendCapacity() );			//好友上限
			buffer.put( user.getDanGradingManager().getInfo().todayMateCount() ); 		//今天匹配次数
			buffer.put( (byte) (user.getDanGradingManager().getInfo().todayCount() + user.getDanGradingManager().getInfo().todayMateCount()) );			//剩余匹配次数
			buffer.put( user.getBuyStrCount() );					//可购买体力次数
			byte str = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? user.getBuyStrCountMax() + 1 : user.getBuyStrCountMax());
			buffer.put( str );//最大可购买次数
			buffer.put( user.getDanGradingManager().getInfo().buyCount() );// 已购买次数
			buffer.put( user.getPvpMateBuyCount() );				// 购买PVP次数上限
			buffer.putShort( user.getNewbieGuideID() );				// 新手引导ID
			buffer.put( (byte) user.getDayLoginCount() );
			buffer.putInt( SystemTimer.currentTimeSecond() );
			user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
			// 如果在副本中 发送副本ID关卡ID和波数 加战报ID
			if( code == ErrorCode.FIGHTER_STATUS ){
				// 设置副本状态
				user.setEctypeStatus( ErrorCode.FIGHTER_STATUS );
				EctypeAward readyAwards	= user.getEctypeReconnectManager().getReadyAwards();
				buffer.putInt( readyAwards.getFightId() );
				BattleInfo b = user.getBattleInfoManager().getBattleByteData( readyAwards.getFightId() );
				buffer.putShort( b.getEctypeId() );
				buffer.putInt( readyAwards.getId() );
				buffer.put( (byte) (b.getTheLv() + 1) );
				TeamManager teamManager = user.getTeamManager();
				// 后面以后加 好友英雄数据
				AssistBase assist 		= teamManager.getAssistFriend();
				buffer.putInt( assist == null ? 0 : assist.getUserUID() );
				if( assist != null ){
					UserInfo u = UserManager.getInstance().getByName( assist.getUserUID() );
					if( u == null ){ Logs.error( user, "登陆进入战斗中 出错 UID=" + assist.getUserUID() + " 错误!" ); return; }
					UtilBase.encodeString( buffer, u.getNickName() );
					buffer.putShort( u.getLevel() );
					buffer.putInt( FightingFormula.run(u) );
					Hero hero = assist.getCaptainHero();
					if( hero == null ){ Logs.error( user, "登陆进入战斗中 出错 队长英雄UID=" + assist.getUId() + " 错误!" ); return; }
					buffer.putInt( user.getTeamManager().getCaptain().getUID() );
//					buffer.putInt( hero.getUID() );
					buffer.putInt( hero.getNid() );
					buffer.putShort( hero.getLevel() );
					hero.getQuality().toByte( buffer );
					buffer.putInt( hero.getAttack() );
					buffer.putInt( hero.getHpMax() );
					buffer.put( assist.getPosition() );
//					buffer.put( (byte) (assist.IsAbsoluteDie() ? 1 : 0) );
					buffer.put( (byte) (assist.getIsDie() ? 1 : 0) );
					buffer.put( (byte) (user.getFriendManager().getFriend( u.getUID() ) != null ? 1 : 0) );
				}
			}
			
			// 更新英雄
			UpdateManager.instance.update( user, UpdateType.U_100, user.getHeroManager().getLists() );
			
			List<FriendBase> friends = user.getFriendManager().getListFriend();
			List<FriendBase> temp = new ArrayList<FriendBase>();
			for( int i = 0; i < friends.size(); i++ ){
				FriendBase f = friends.get(i);
				temp.add( f );
				if( temp.size() >= 10 ){
					UpdateManager.instance.update( user, UpdateType.U_110, temp );
					temp.clear();
				}
			}
			if( !temp.isEmpty() )
				UpdateManager.instance.update( user, UpdateType.U_110, temp );
			
			UpdateManager.instance.update( user, UpdateType.U_115, user.getFriendManager().getListBeg() );
			UpdateManager.instance.update( user, UpdateType.U_90, user.getEquipmentManager().getLists() );
			UpdateManager.instance.update( user, UpdateType.U_26 );
			UpdateManager.instance.update( user, UpdateType.U_27 );
			UpdateManager.instance.update( user, UpdateType.U_28 );
			UpdateManager.instance.update( user, UpdateType.U_29 );
			UpdateManager.instance.update( user, UpdateType.U_31 );
			UpdateManager.instance.update( user, UpdateType.U_32 );
			UpdateManager.instance.update( user, UpdateType.U_34 );
			UpdateManager.instance.update( user, UpdateType.U_35 );
			UpdateManager.instance.update( user, UpdateType.U_36 );
			
//			RechargeEvent_lb lb = (RechargeEvent_lb) Event.RECHARGE_EVENT_LB.getEventInstance();
//			lb.run( user, (short)0, "asdsad", 100 );
		}
		sendPackage( con, buffer );
		
		if(  code == ErrorCode.SUCCESS || code == ErrorCode.FIGHTER_STATUS  ){
			
			UpdateManager.instance.updateMain( user );
			//// --------------------下面做其他事情
			// 处理每日奖励
			if( isFirstToDay ){
				updateToDay( user );
			}
			
			if( !SetActivityEevet.isOpen2 ){
				user.setRechargeMoney( 0 );
				user.xinkai = 0;
			}
			if( !SetActivityEevet.isOpen5 ){
				user.setRechargeMoney1( 0 );
				user.xinkai1 = 0;
			}
			
			// 记录活跃数
			if( GameData.dailyActiveUsers.indexOf( user.getUID() ) == -1 ){
				GameData.dailyActiveUsers.add( user.getUID() );
				
				MailBase mail;
				AwardInfo award;
				if( user.isMonthCardFate( 1 ) ){
					award	= new AwardInfo( AwardType.GOLD, 0, 100 );
					mail 	= new MailBase( award, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
					DataLogDataProvider.getInstance().add( user, ConsumelogF.PREPAID_PHONE, 100 );
				}
				if( user.isMonthCardFate( 2 ) ){
					award	= new AwardInfo( AwardType.GOLD, 0, 200 );
					mail 	= new MailBase( award, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
					DataLogDataProvider.getInstance().add( user, ConsumelogF.PREPAID_PHONE, 200 );
				}
				if( user.isMonthCardFate( 3 ) ){
					award	= new AwardInfo( AwardType.GOLD, 0, 300 );
					mail 	= new MailBase( award, MailType.SYSTEM_PRESENT );
					user.getMailManager().addMail( mail );
					DataLogDataProvider.getInstance().add( user, ConsumelogF.PREPAID_PHONE, 300 );
				}
			}
			
			// 日常
			if( SetActivityEevet.isOpen1 && !user.isLingQvRiCahgn && EveryDaySetTimeTask1.isOpen ){
				user.isLingQvRiCahgn = true;
				MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 211 + "|" + 60 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
				user.getMailManager().addMail( mail );
				AwardInfo award_m	= new AwardInfo( AwardType.STRENGTH, 0, 60 );
				mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
			}
			// 如果以前是流失的 那么转换回来
			if( user.isTurnover ) user.isTurnover = false;
				
			// 如果是第一次登陆 并且 还是开服前3天 那么送水晶
//			if( user.isFirstLogin() && GameData.isHaveMikkaLogin_3() ){
//				MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 212 + "|" + 888 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
//				user.getMailManager().addMail( mail );
//				mail 			= new MailBase( new AwardInfo(AwardType.GOLD, 888), MailType.SYSTEM_PRESENT );
//				user.getMailManager().addMail( mail );
//			}
			
			if( isCL ) UserManager.getInstance().updataToIsCL( user );
			
			// 这里做漏单处理
//			if( SystemCfg.PLATFORM.equals( "91" ) )
//				RechargeManager.getInstance().LeakageSingleProcessing( user );
			
			// 这里 如果是账号登陆 那么就不能提示绑定奖励了
			if( key.equals( "2" ) ){
				user.getAwardManager().relieve( SAwardType.BDA );
			}
			
			// 告诉登录服务器 玩家选择了哪个服务器
			CheckCodeEvent xxx = (CheckCodeEvent)EventList.CHECK_CODEEVENT.getEventInstance();
			xxx.run( user.getUID(), "" );
		}
		
		} catch (Exception e) {
			Logs.error( "登陆问题：" + e.getLocalizedMessage() );
		}
	}

	private void updateToDay( UserInfo user ){
		
		if( user.numerationToDay == GameData.NUMERATIONTODAY )
			return;
		user.numerationToDay = GameData.NUMERATIONTODAY;
		
		if( !user.isTurnover && SystemTimer.currentTimeSecond() - user.getLastLogoutTime() >= 604800 ){
			user.isTurnover = true;
		}
		
		user.getAwardManager().resetRecord( SAwardType.LAG, 1 );
		user.getAwardManager().resetRecord( SAwardType.LAM, 1 );
		user.getAwardManager().resetRecord( SAwardType.LAT, 0 );
//		user.getAwardManager().resetRecord( SAwardType.LAS, 1 );
		user.getAwardManager().resetRecord( SAwardType.STC, 0 );
		user.getAwardManager().resetRecord( SAwardType.YTC, 0 );
		user.getAwardManager().resetRecord( SAwardType.POP, 0 );
		user.getAwardManager().resetRecord( SAwardType.CCE1, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CCE2, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CCE3, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CCE4, 0 );
		user.getAwardManager().resetRecord( SAwardType.CEE1, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CEE2, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CEE3, 0 );
//		user.getAwardManager().resetRecord( SAwardType.CEE4, 0 );
		user.setIsCardTo10( (byte) 0 );
		
		// 如果有月卡
		boolean isM0	= user.handleMonthCardFate( 1 );
//		if( isM0 )
//			user.getAwardManager().record( SAwardType.LAMC, 1 );
//		else
//			user.getAwardManager().record( SAwardType.LAMC, -1 );
//		// 如果有月卡
		boolean isM1	= user.handleMonthCardFate( 2 );
//		if( isM1 )
//			user.getAwardManager().record( SAwardType.LAMC1, 1 );
//		else
//			user.getAwardManager().record( SAwardType.LAMC1, -1 );
//		// 如果有月卡
		boolean isM2	= user.handleMonthCardFate( 3 );
//		if( isM2 )
//			user.getAwardManager().record( SAwardType.LAMC2, 1 );
//		else
//			user.getAwardManager().record( SAwardType.LAMC2, -1 );
		
		// 重置购买次数
		for( int i = 0; i < user.allBuyTimes.length ; i++ )
			user.allBuyTimes[i] = 0;
		
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
			
//			int[] gold 		= user.getQualifyingManager().getQualifying().startGetAwardToGold( rank + 1 );
			
			MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 210 + "|" + UtilBase.secondsToDate(SystemTimer.currentTimeSecond(), "yyyy.MM.dd" )
					 + "," + (rank + 1) + "," + gold[1] + "," + gold[0] + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
			user.getMailManager().addMail( mail );
			
			AwardInfo a 	= new AwardInfo( AwardType.GOLD, gold[1] );
			mail 	= new MailBase( a, MailType.SYSTEM_PRESENT );
			user.getMailManager().addMail( mail );
			
			a				= new AwardInfo( AwardType.CASH, gold[0] );
			mail 			= new MailBase( a, MailType.SYSTEM_PRESENT );
			user.getMailManager().addMail( mail );
//			user.getQualifyingManager().getQualifying().setIsGetAward( true );
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
	
	
	public void run( INonBlockingConnection con, int name ) throws IOException {
		
		UserInfo user 	= UserManager.getInstance().getByName( name );
		
		ErrorCode code 	= ErrorCode.SUCCESS;
		EctypeAward	readyAwards	= user.getEctypeReconnectManager().getReadyAwards();
		if( readyAwards != null )
			code = ErrorCode.FIGHTER_STATUS;
		user.setStatus( UserStatus.LOGIN );
//		user.cetCon( con );
//		user.setConLogin( con, name );
		// 记录登陆时间   不用保存数据库
		user.setLoginTime( SystemTimer.currentTimeSecond() );
		
		///// 下面开始发数据
		ByteBuffer buffer 		= buildEmptyPackage( 1024 );
		buffer.putShort( (short) code.ordinal() );
		buffer.putInt( user.getUID() );							//用户ID
		UtilBase.encodeString( buffer, user.getNickName() );	//昵称
		buffer.putShort( user.getLevel() );						//等级
		buffer.putInt( user.getCurExp() );						//当前经验
		int replyTime = user.getStrReplyTimeToSecond( 1 );		//这里先获得 是因为要换算
		buffer.putShort( user.getStrength() );					//当前体力
		buffer.putShort( user.getStrengthMax() );				//体力上限
		buffer.putInt( replyTime );								//体力回复时间
		buffer.putInt( user.getCash() );						//金币
		buffer.putInt( user.getGold() );						//水晶
		buffer.putInt( FightingFormula.run( user ) );			//战斗力
		buffer.putInt( user.getFriendshipValue() );				//友情值
		buffer.put( user.getMailManager().isHaveRead() );		//是否有未读邮件
		buffer.put( user.getDanGradingManager().getInfo().danGrad().toNumber() );	//匹配段位
		buffer.putInt( user.getDanGradingManager().getInfo().grade() );				//匹配积分
		buffer.putShort( user.getBagCapacity() );				//英雄背包上限
		buffer.putShort( user.getFriendCapacity() );			//好友上限
		buffer.put( user.getDanGradingManager().getInfo().todayMateCount() ); 		//今天匹配次数
		buffer.put( (byte) (user.getDanGradingManager().getInfo().todayCount() + user.getDanGradingManager().getInfo().todayMateCount()) );			//剩余匹配次数
		buffer.put( user.getBuyStrCount() );					//可购买体力次数
		byte str = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? user.getBuyStrCountMax() + 1 : user.getBuyStrCountMax());
		if( user.getBuyStrCount() > str ) str = user.getBuyStrCount();
		buffer.put( str );//最大可购买次数
		buffer.put( user.getDanGradingManager().getInfo().buyCount() );// 已购买次数
		buffer.put( user.getPvpMateBuyCount() );				// 购买PVP次数上限
		buffer.putShort( user.getNewbieGuideID() );				// 新手引导ID
		buffer.put( (byte) user.getDayLoginCount() );
		buffer.putInt( SystemTimer.currentTimeSecond() );
		// 如果在副本中 发送副本ID关卡ID和波数 加战报ID
		if( code == ErrorCode.FIGHTER_STATUS ){
			// 设置副本状态
			user.setEctypeStatus( ErrorCode.FIGHTER_STATUS );
			buffer.putInt( readyAwards.getFightId() );
			buffer.putShort( user.getBattleInfoManager().getBattleByteData( readyAwards.getFightId() ).getEctypeId() );
			buffer.putInt( readyAwards.getId() );
			buffer.put( readyAwards.getTheLv() );
			TeamManager teamManager = user.getTeamManager();
			// 后面以后加 好友英雄数据
			AssistBase assist 		= teamManager.getAssistFriend();
			buffer.putInt( assist == null ? 0 : assist.getUserUID() );
			if( assist != null ){
				UserInfo u = UserManager.getInstance().getByName( assist.getUserUID() );
				if( u == null ){ Logs.error( user, "登陆进入战斗中 出错 UID=" + assist.getUserUID() + " 错误!" ); return; }
				UtilBase.encodeString( buffer, u.getNickName() );
				buffer.putShort( u.getLevel() );
				buffer.putInt( FightingFormula.run(u) );
				Hero hero = assist.getCaptainHero();
				if( hero == null ){ Logs.error( user, "登陆进入战斗中 出错 队长英雄UID=" + assist.getUId() + " 错误!" ); return; }
				buffer.putInt( hero.getUID() );
				buffer.putInt( hero.getNid() );
				buffer.putShort( hero.getLevel() );
				hero.getQuality().toByte( buffer );
				buffer.putInt( hero.getAttack() );
				buffer.putInt( hero.getHpMax() );
				buffer.put( assist.getPosition() );
//				buffer.put( (byte) (assist.IsAbsoluteDie() ? 1 : 0) );
				buffer.put( (byte) (assist.getIsDie() ? 1 : 0) );
				buffer.put( (byte) (user.getFriendManager().getFriend( u.getUID() ) != null ? 1 : 0) );
			}
		}
		sendPackage( con, buffer );
	}
	
}
