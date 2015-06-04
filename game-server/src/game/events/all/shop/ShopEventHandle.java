package game.events.all.shop;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;
import game.log.Logs;
import game.luckydraw.LuckydrawManager;
import game.luckydraw.SweepstakeType;
import game.mail.MailBase;
import game.mail.MailType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import notice.NoticeManager;

import lua.Lua;
import lua.LuaProxy;
import manager.DWType;

import config.luckydraw.LuckydrawTemplet;
import config.saward.SAwardType;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;
import define.GameData;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.RandomUtil;
import util.SystemTimer;
import util.UtilBase;

public enum ShopEventHandle {
	// 祈福
	BLESSING( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			SweepstakeType type = SweepstakeType.fromNumber( data.get() );
			if( type == null ) return true;
			
			
			ErrorCode code				= user.getHeroManager().getIsBagFull();
			
			
			// 这里先扣钱
			if( code == ErrorCode.SUCCESS )
				code					= type.checkGold( user );
			
			boolean isx 	= false;
			boolean isox	= false;
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				
				user.getAwardManager().record( SAwardType.STC, 1 );
				
				// 这里先看是否今日第一次 抽10连抽
				if( type == SweepstakeType.TENTIMES && SetActivityEevet.isOpen3 && user.getIsCardTo10() == 0 ){
					user.setIsCardTo10( (byte) 1 );
					isx = true;
				}
				
				// 英雄主题周
				if( type == SweepstakeType.TENTIMES && SetActivityEevet.isOpen4 > 0 && user.getIsHthemeWeek() != SetActivityEevet.isOpen4 ){
					user.setIsHthemeWeek( SetActivityEevet.isOpen4 );
					isox = true;
				}
				
				List<LuckydrawTemplet> list = null;
				
				// 这里写死
				if( user.isFirsttimeThatcard && type == SweepstakeType.ONCE ){
					user.isFirsttimeThatcard		= false;
					int awardSeven[]				= { 10003,10007,10009,10010,10016,10017,10019,10022,10025,10032,10039,10043,10046,10047,10048
							,10050,10052,10054,10056,10057,10060,10063,10084,10086,10091,10092,10095,10097,10098,10100,10105,10107,10108,10109,10110
							,10111,10112,10103 };
					
					int nid							= awardSeven[ RandomUtil.getRandomInt( 0, awardSeven.length -1 ) ];
					Quality quality					= new Quality( Colour.PURPLE, Colour.PURPLE, (byte) 0 );
					LuckydrawTemplet temp			= new LuckydrawTemplet(nid, quality, 0);
					list							= new ArrayList<LuckydrawTemplet>();
					list.add( temp );
				}else
					list = LuckydrawManager.instance.startSweepstake( type, user.getUID() );
				
				if( list.isEmpty() ){
					Logs.error( user, "祈福出错 没有抽到一个英雄!" );
					return true; 
				}
				
//				user.getHeroManager().create( list );
				
				try {
					response.put( (byte)list.size() );
					for( LuckydrawTemplet templet : list ){
						response.putInt( templet.getNid() );
						templet.getQuality().toByte( response );
					}
				} catch (Exception e) {
				}finally{// 添加到英雄列表
					
					List<Hero> lists = new ArrayList<Hero>();
					
					for( LuckydrawTemplet templet : list ){
						int id = user.getHeroManager().create( templet.getNid(), templet.getQuality().getColour(),(short)1, templet.getQuality().getLevel(), false );
						if( id != -1 )
							lists.add( user.getHeroManager().getHero(id) );
					}
					
					// 更新前端
					UpdateManager.instance.update( user, UpdateType.U_4 );
					UpdateManager.instance.update( user, UpdateType.U_100, lists );
					
					// 发邮件  赠送4张紫色经验大龙30129，4张经验小龙30081
					if( isx ){
						MailBase mail	= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 215 + "|4|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
						user.getMailManager().addMail( mail );
						AwardInfo award_m	= new AwardInfo( AwardType.HERO, 30129, 4, 1, 2, 0 );
						mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
						user.getMailManager().addMail( mail );
						award_m			= new AwardInfo( AwardType.HERO, 30081, 4, 1, 1, 0 );
						mail 			= new MailBase( award_m, MailType.SYSTEM_PRESENT );
						user.getMailManager().addMail( mail );
					}
					// 英雄主题周
					if( isox ){
						AwardInfo award_m	= new AwardInfo( AwardType.HERO, GameData.hthemeWeekData[SetActivityEevet.isOpen4-1], 1, 1, 2, 0 );
						MailBase mail 		= new MailBase( award_m, MailType.SYSTEM_PRESENT );
						user.getMailManager().addMail( mail );
					}
				}
			}
			
			return false;
		}
	},
	// 友情抽奖
	FRIENDSHIP_DRAW( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			SweepstakeType type 		= SweepstakeType.fromNumber( data.get() );
			if( type == null ) return true;
			
			ErrorCode code				= user.getHeroManager().getIsBagFull();
			
			// 这里先扣钱
			if( code == ErrorCode.SUCCESS )
				code					= type.checkGold( user );
			
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				
				user.getAwardManager().record( SAwardType.YTC, 1 );
				
				List<LuckydrawTemplet> list = LuckydrawManager.instance.startSweepstake( type, user.getUID() );
				if( list.isEmpty() ){
					Logs.error( user, "友情抽奖出错 没有抽到一个英雄!" );
					return true;
				}
				
				try {
					
					response.put( (byte)list.size() );
					for( LuckydrawTemplet templet : list ){
						response.putInt( templet.getNid() );
						templet.getQuality().toByte( response );
					}
					
				} catch (Exception e) {
				} finally {// 添加到英雄列表
					
					List<Hero> lists = new ArrayList<Hero>();
					for( LuckydrawTemplet templet : list ){
						int id = user.getHeroManager().create( templet.getNid(), templet.getQuality().getColour(),(short)1, templet.getQuality().getLevel(), false );
						if( id != -1 )
							lists.add( user.getHeroManager().getHero(id) );
					}
					
					UpdateManager.instance.update( user, UpdateType.U_6 );
					UpdateManager.instance.update( user, UpdateType.U_100, lists );
				}
//				user.getHeroManager().create( list );
			}
			
			return false;
		}
	},
	
	// 购买体力
	GOLD_BUY( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			do{
				// 先判断 次数 是否足够
				if( user.IsBuyStrCount() ){
					code	= ErrorCode.BUY_STR_NOT_COUNT;
					break;
				}
				
				// 根据次数 获得需要水晶
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 3, "getBuyNeedCrystal_1", user.allBuyTimes[0]++, user.getStrength(), user.getStrengthMax() );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				short changeValue	= (short)(double)ret[2];
				if( user.changeAward(AwardType.GOLD, -needGold, "购买体力 消耗水晶", DWType.PURCHASE_OF_PHYSICAL) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				user.getAwardManager().record( SAwardType.POP, 1 );
				
				// 先扣掉次数
				user.updateBuyStrCount();
				// 将体力回满
				user.changeStrength( changeValue, "购买体力 回满体力");
				
				// 告诉前端 更新
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_2 );
				UpdateManager.instance.update( user, UpdateType.U_14 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_PHYSICAL, needGold );
				
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买英雄背包
	HERO_BAG_BUY( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			do{
				// 先判断 是否 已经 最大了
				if( user.getBagCapacity() >= DefaultCfg.HERO_BAG_MAX ){
					code	= ErrorCode.HAVE_MAX_COUNT;
					break;
				}
				
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 3, "getBuyNeedCrystal_3", user.allBuyTimes[2]++, user.getBagCapacity() );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				short changeValue	= (short)(double)ret[2];
				if( user.changeAward(AwardType.GOLD, -needGold, "购买背包 消耗水晶", DWType.HERO_OF_THE_BACKPACK) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				user.setBagCapacity( (short) (user.getBagCapacity() + changeValue) );
				
				// 告诉前端 更新
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_10 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_HEROBACKPACK, DefaultCfg.HERO_BAG_BUY_GLOD );
					
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买好友上限
	HERO_FRIEND_BUY( 5 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			do{
				// 先判断 是否 已经 最大了
				if( user.getFriendCapacity() >= DefaultCfg.FRIEND_BAG_MAX ){
					code	= ErrorCode.HAVE_MAX_COUNT;
					break;
				}
				
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 3, "getBuyNeedCrystal_2", user.allBuyTimes[1]++, user.getFriendCapacity() );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				short changeValue	= (short)(double)ret[2];
				if( user.changeAward(AwardType.GOLD, -needGold, "购买好友上限 消耗水晶", DWType.BUY_FRIENDS_LIMIT) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				user.setFriendCapacity( (short) (user.getFriendCapacity() + changeValue) );
				
				// 告诉前端 更新
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_11 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_FRIENDPACK, DefaultCfg.FRIEND_BAG_BUY_GLOD );
				UserManager.getInstance().putUpdate( user );
				
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买金币
	BUY_GOLD_COINS( 6 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 		= ErrorCode.SUCCESS;
			
			byte count			= data.get();
			short nextNeedGold	= 0;
			do{
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 3, "getBuyNeedCrystal_7", user.allBuyTimes[6]++ );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				int changeValue		= (int)(double)ret[2];
				if( user.changeAward(AwardType.GOLD, -(needGold*count), "购买金币 消耗水晶", DWType.MISCELLANEOUS) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				// 这里成功后 就加上金币
				user.changeAward(AwardType.CASH, changeValue*count, "购买金币 获得金币", DWType.MISCELLANEOUS );
				
				// 告诉前端 更新
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_3 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_GOLD, needGold );
				UserManager.getInstance().putUpdate( user );
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买PVP
	BUY_PVP( 7 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			do{
				// 判断是否还可以继续购买
				if ( !user.getDanGradingManager().isBuyMateCount() ){
					code 	= ErrorCode.PVP_MATE_BUY_COUNT_MAX;
					break;
				}
				
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 2, "getBuyNeedCrystal_5", user.allBuyTimes[4]++ );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				if( user.changeAward(AwardType.GOLD, -needGold, "购买匹配次数 消耗水晶", DWType.BUY_MATCHING_NUMBER ) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				user.getDanGradingManager().buyMateCount( );
				
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_16 );
				UpdateManager.instance.update( user, UpdateType.U_13 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_PVP, DefaultCfg.PVP_MATE_BUY_COUNT_GOLD );
				UserManager.getInstance().putUpdate( user );
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买装备背包上限
	BUY_EQUIP_BAG_CAP ( 8 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			do{
				
				// 先判断 是否 已经 最大了
				if( user.getEquipbagCapacity() >= DefaultCfg.EQUIP_BAG_MAX ){
					code	= ErrorCode.HAVE_MAX_COUNT;
					break;
				}
				
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 3, "getBuyNeedCrystal_4", user.allBuyTimes[3]++, user.getEquipbagCapacity() );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				short changeValue	= (short)(double)ret[2];
				if( user.changeAward(AwardType.GOLD, -needGold, "购买装备背包 消耗水晶", DWType.MISCELLANEOUS) == -1 ){
					code	= ErrorCode.USER_GOLD_NOT_ENOUTH;
					break;
				}
				
				user.setEquipbagCapacity( (short) (user.getEquipbagCapacity() + changeValue) );
				
				// 告诉前端 更新
				UpdateManager.instance.update( user, UpdateType.U_4 );
				UpdateManager.instance.update( user, UpdateType.U_27 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_EQUIPBACKPACK, DefaultCfg.EQUIP_BAG_BUY_GLOD );
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putShort( nextNeedGold );
			
			return false;
		}
	},
	
	// 购买 限购
	BUY_RESTRICTION  ( 9 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			List<Short> list = user.getRestrictionData();
			response.put( (byte) list.size() );
			for( short i : list )
				response.putShort( i );
			
			return false;
		}
	},
	
	// 购买排位次数
	THE_NUMBER_OF_QUALIFYING  ( 10 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			short nextNeedGold = 0;
			
			do{
				if( !user.getQualifyingManager().getQualifying().isCanBuy() ) { code = ErrorCode.UNKNOW_ERROR; break; }
				
				LuaProxy lua = Lua.createLuaState( "gameData.lua" );
				Object[] ret = lua.retArray( 2, "getBuyNeedCrystal_6", user.allBuyTimes[5]++ );
				int needGold 		= (int)(double)ret[0];
				nextNeedGold 		= (short)(double)ret[1];
				if( user.changeAward( AwardType.GOLD, -needGold, "执行排位立即重置扣取", DWType.MISCELLANEOUS ) == -1 ) { code = ErrorCode.UNKNOW_ERROR; break; }
				
				user.getQualifyingManager().getQualifying().startBuy();
				
				UpdateManager.instance.update( user, UpdateType.U_4 );
				
				// 记录日志
				DataLogDataProvider.getInstance().add( user, ConsumelogF.PURCHASE_RANK, needGold );

			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				response.putShort( nextNeedGold );
				UpdateManager.instance.update( user, UpdateType.U_34 );
			}
			
			return false;
		}
	}
	
	
	;
	
	
	private final byte 				number;
	
	ShopEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, ShopEventHandle> numToEnum = new HashMap<Byte, ShopEventHandle>();
	static{
		for( ShopEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static ShopEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
