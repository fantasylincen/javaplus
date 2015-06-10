/**
 * 
 */
package game.events.all.user;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import game.log.L;
import game.mail.MailBase;
import game.mail.MailType;
import game.util.fighting.FightingFormula;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import login.server.event.CheckCodeEvent;
import login.server.event.EventList;

import notice.NoticeManager;

import config.fighter.HeroTempletCfg;
import config.saward.SAwardType;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.GameData;


import recharge.LTVDataProvider;
import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import user.UserStatus;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import util.taskscheduling.task.EveryDaySetTimeTask1;

/**
 * @author DXF
 * 2013-8-7
 */

@EventDescrip(desc = "创建新玩家", structure = "")

public class UserCreateEvent extends EventBase {

	private static final int PACK_LEN = 1024;
	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {

		
	}

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		int index 		= buf.getInt();// 登陆服 索引
		String key 		= util.UtilBase.decodeString( buf );// 验证码
		int heroID		= buf.getInt();	// 选择的英雄
		String nickName	= util.UtilBase.decodeString( buf ); // 角色名字
		
		ErrorCode code	= ErrorCode.SUCCESS;
		UserInfo user	= null;
		
		do{
			code		= checkHeroId( heroID );
			if( code != ErrorCode.SUCCESS ) break;
			
			// 这里第一个参数必须为null 
			user 		= new UserInfo( null, index, nickName );
			code 		= UserManager.getInstance().create( con, user, heroID );
			if( code != ErrorCode.SUCCESS ) break;
			
			// 记录登陆时间   不用保存数据库
			user.setLoginTime( SystemTimer.currentTimeSecond() );
			user.setStatus( UserStatus.LOGIN );
			// 记录日志
			Logs.log( L.L_001 , user.getUID() + "," + user.getNickName() + ",vc" );
			
			// 记录每日登陆
			int value 		= 1;
			user.setDayLoginCount( value );
			user.getAwardManager().record( SAwardType.LAG, value );
			user.getAwardManager().record( SAwardType.LAM, value );
			
			// 下面主动更新 信息
			UpdateManager.instance.update( user, UpdateType.U_100, user.getHeroManager().getLists() );
			UpdateManager.instance.update( user, UpdateType.U_34 );
			UpdateManager.instance.update( user, UpdateType.U_35 );
			UpdateManager.instance.updateMain( user );
			
		}while( false );
		
		ByteBuffer buffer = buildEmptyPackage( PACK_LEN );
		buffer.putShort( (short) code.ordinal() );			//错误代码
		
		if( code == ErrorCode.SUCCESS ){
			UserManager.getInstance().putOnline( index, user );
			
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
			buffer.put( user.getBuyStrCountMax() );					//最大可购买次数
			buffer.put( user.getDanGradingManager().getInfo().buyCount() );// 已购买次数
			buffer.put( user.getPvpMateBuyCount() );				// 购买PVP次数上限
			buffer.putShort( user.getNewbieGuideID() );				// 新手引导ID
			buffer.put( (byte) 1 );
			buffer.putInt( SystemTimer.currentTimeSecond() );
		}
		sendPackage( con, buffer );
			
		if( code == ErrorCode.SUCCESS ){
			// 记录活跃数
			if( GameData.dailyActiveUsers.indexOf( user.getUID() ) == -1 )
				GameData.dailyActiveUsers.add( user.getUID() );
			
			// 日常
			if( SetActivityEevet.isOpen1 && !user.isLingQvRiCahgn && EveryDaySetTimeTask1.isOpen ){
				user.isLingQvRiCahgn = true;
				MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 211 + "|" + 60 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
				user.getMailManager().addMail( mail );
				AwardInfo award_m	= new AwardInfo( AwardType.STRENGTH, 0, 60 );
				mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
			}
			
			// 如果是第一次登陆 并且 还是开服前3天 那么送水晶
			if( GameData.isHaveMikkaLogin_3() ){
//				MailBase mail 	= new MailBase( new AwardInfo(AwardType.GOLD, 888), MailType.SYSTEM_PRESENT );
//				mail.setSurplusTime( SystemTimer.currentTimeSecond() + (i++) );
//				user.getMailManager().addMail( mail );
//				mail			= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 212 + "|" + 888 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
//				mail.setSurplusTime( SystemTimer.currentTimeSecond() + (i++) );
//				user.getMailManager().addMail( mail );
			}
			
			// 如果是第一次登陆 并且 还是开服前4天 那么送水晶
			if( GameData.isHaveMikkaLogin_4 ){
//				1：礼包内容：水晶1288，经验小龙*5，金币小龙*5，金币200000，紫色蓝盾*1 5904
				MailBase mail 	= new MailBase( new AwardInfo(AwardType.GOLD, 1288), MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
				mail 			= new MailBase( new AwardInfo( AwardType.HERO, 30081, 5, 1, 1, 0 ), MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
				mail 			= new MailBase( new AwardInfo( AwardType.HERO, 30082, 5, 1, 1, 0 ), MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
				mail 			= new MailBase( new AwardInfo( AwardType.PROP, 5904, 1 ), MailType.SYSTEM_PRESENT );
				user.getMailManager().addMail( mail );
				mail			= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 214 + "|!|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
				user.getMailManager().addMail( mail );
				DataLogDataProvider.getInstance().add( user, ConsumelogF.DISTRIBUTED_SYSTEM, 1288 );
			}
			
			// 这里 如果是账号登陆 那么就不能提示绑定奖励了
			if( key.equals( "2" ) ){
				user.getAwardManager().relieve( SAwardType.BDA );
				user.addGold( 300 );
				DataLogDataProvider.getInstance().add( user, ConsumelogF.DISTRIBUTED_SYSTEM, 300 );
				UpdateManager.instance.update( user, UpdateType.U_4 );
			}
			
			// 记录ltv
			LTVDataProvider.getInstance().addPlayer(user);
			
			// 告诉登录服务器 玩家选择了哪个服务器
			CheckCodeEvent xxx = (CheckCodeEvent)EventList.CHECK_CODEEVENT.getEventInstance();
			xxx.run( user.getUID(), "" );
		}
		
	}
	
	public void run(UserInfo user, ErrorCode code) throws IOException {
		
//		ByteBuffer buffer = buildEmptyPackage( PACK_LEN );
//		buffer.putShort( (short) code.ordinal() );			//错误代码
//		
//		if( code == ErrorCode.SUCCESS ){
//		
//			buffer.putInt( user.getUID() );							//用户ID
//			UtilBase.encodeString( buffer, user.getNickName() );	//昵称
//			
//			buffer.putShort( user.getLevel() );						//等级
//			buffer.putInt( user.getCurExp() );						//当前经验
//			
//			int replyTime = user.getStrReplyTimeToSecond();			//这里先获得 是因为要换算
//			buffer.putShort( user.getStrength() );					//当前体力
//			buffer.putShort( user.getStrengthMax() );				//体力上限
//			buffer.putInt( replyTime );								//体力回复时间
//			buffer.putInt( user.getCash() );						//金币
//			buffer.putInt( user.getGold() );						//水晶
//			buffer.putInt( FightingFormula.run( user ) );			//战斗力
//			buffer.putInt( user.getFriendshipValue() );				//友情值
//			buffer.put( user.getMailManager().isHaveRead() );		//是否有未读邮件
//			buffer.put( user.getDanGradingManager().getInfo().danGrad().toNumber() );	//匹配段位
//			buffer.putInt( user.getDanGradingManager().getInfo().grade() );				//匹配积分
//			buffer.putShort( user.getBagCapacity() );				//英雄背包上限
//			buffer.putShort( user.getFriendCapacity() );			//好友上限
//			buffer.put( user.getDanGradingManager().getInfo().todayMateCount() ); 		//今天匹配次数
//			buffer.put( (byte) (user.getDanGradingManager().getInfo().todayCount() + user.getDanGradingManager().getInfo().todayMateCount()) );			//剩余匹配次数
//			buffer.put( user.getBuyStrCount() );					//可购买体力次数
//			buffer.put( user.getBuyStrCountMax() );					//最大可购买次数
//			buffer.put( user.getDanGradingManager().getInfo().buyCount() );// 已购买次数
//			buffer.put( user.getPvpMateBuyCount() );				// 购买PVP次数上限
//			buffer.putShort( user.getNewbieGuideID() );				// 新手引导ID
//			buffer.put( (byte) 1 );
//			buffer.putInt( SystemTimer.currentTimeSecond() );
//			
//			// 记录活跃数
//			if( GameData.dailyActiveUsers.indexOf( user.getUID() ) == -1 )
//				GameData.dailyActiveUsers.add( user.getUID() );
//			
//			// 日常
//			if( SetActivityEevet.isOpen1 && !user.isLingQvRiCahgn && EveryDaySetTimeTask1.isOpen ){
//				user.isLingQvRiCahgn = true;
//				MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 211 + "|" + 60 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
//				user.getMailManager().addMail( mail );
//				AwardInfo award_m	= new AwardInfo( AwardType.STRENGTH, 0, 60 );
//				mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
//				user.getMailManager().addMail( mail );
//			}
//		}
//		
//		sendPackage( user.getCon(), buffer );
//		
//		if( code != ErrorCode.SUCCESS && code != ErrorCode.FIGHTER_STATUS ){
////			user.setConClose();
//		
//		// 如果是第一次登陆 并且 还是开服前3天 那么送水晶
//		}else if( GameData.isHaveMikkaLogin ){
//			MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 212 + "|" + 888 + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
//			user.getMailManager().addMail( mail );
//			mail 			= new MailBase( new AwardInfo(AwardType.GOLD, 888), MailType.SYSTEM_PRESENT );
//			user.getMailManager().addMail( mail );
//		}
		
	}
	
	// 检查英雄ID 是否正确
	private ErrorCode checkHeroId(int heroID) {
		return HeroTempletCfg.getById(heroID) == null ? ErrorCode.USER_NOT_HEROID : ErrorCode.SUCCESS;
	}



}
