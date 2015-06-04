package game.events.all.update;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import lua.Lua;
import lua.LuaProxy;

import deng.xxoo.utils.XOTime;

import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.UtilBase;
import game.activity.ActivityManager;
import game.equipment.EquipmentInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.fighter.Hero;
import game.friend.FriendBase;
import game.invitingfriends.InvitedBase;
import game.invitingfriends.InvitingFriendsBase;
import game.log.Logs;
import game.team.TeamHero;
import game.util.fighting.FightingFormula;

/**
 * 服务器主动 发送的 更新信息
 * @author DXF
 *
 */
@EventDescrip(desc = "主界面-主动更新包")
public class ActiveUpdateEvent extends EventBase{
	
	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte at 		= buf.get();
		
		UpdateType type = UpdateType.fromNumber(at);
		
		if( type == null ){
			Logs.error( user, "主界面更新包出错！at=" + at );
			return;
		}
		
		run( user, type, null, null, -1 );
	}
	
	public void run( UserInfo user, UpdateType type, List<?> lists , InvitingFriendsBase invited, int id, String ... args ) throws IOException{
		
		if( user.getCon() == null || !user.getCon().isOpen() )
			return;
		
		ByteBuffer response = buildEmptyPackage( type.PACK_LEN() );
		response.put( (byte)type.toNumber() );
		
		UserInfo u = null;
		
		switch( type )
		{
		case U_1:
			response.putShort( user.getLevel() );					//当前等级
			response.putInt( user.getCurExp() );					//当前经验
			break;
		case U_2:
			int replyTime = user.getStrReplyTimeToSecond( 1 );		//这里先获得 是因为要换算
			response.putShort( user.getStrength() );				//当前体力
			response.putShort( user.getStrengthMax() );				//体力上限
			response.putInt( replyTime );							//体力回复时间
			break;
		case U_3:
			response.putInt( user.getCash() );						//金币
			break;
		case U_4:
			response.putInt( user.getGold() );						//水晶
			break;
		case U_5:
			response.putInt( FightingFormula.run( user ) );			//战斗力
			break;
		case U_6:
			response.putInt( user.getFriendshipValue() );
			break;
		case U_7:
			response.put( user.getMailManager().isHaveRead() );
			break;
		case U_8:
			response.put( user.getDanGradingManager().getInfo().danGrad().toNumber() );
			break;
		case U_9:
			response.putInt( user.getDanGradingManager().getInfo().grade() );			
			break;
		case U_10:
			response.putShort( user.getBagCapacity() );				//英雄背包上限
			break;
		case U_11:
			response.putShort( user.getFriendCapacity() );			//好友上限
			break;
		case U_12:
			response.put( user.getDanGradingManager().getInfo().todayMateCount() ); 	//今天匹配次数
			break;
		case U_13:
			response.put( (byte) (user.getDanGradingManager().getInfo().todayCount() + user.getDanGradingManager().getInfo().todayMateCount()) );			//剩余匹配次数
			break;
		case U_14:
			response.put( user.getBuyStrCount() );		//可购买体力次数
			break;
		case U_15:
			byte str = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? user.getBuyStrCountMax() + 1 : user.getBuyStrCountMax());
			if( user.getBuyStrCount() > str ) str = user.getBuyStrCount();
			response.put( str );//最大可购买次数
			break;
		case U_16:
			response.put( user.getDanGradingManager().getInfo().buyCount() );			//已购买PVP次数
			break;
		case U_17:
			response.put( user.getPvpMateBuyCount() );									//购买PVP次数上限
			break;
		case U_20:
			response.put( user.getTalentManager().reminder() );
			break;
		case U_21:
			response.put( user.getAwardManager().reminder1() );
			response.put( user.getAwardManager().reminder() );
			break;
		case U_22:
			response.put( user.getDanGradingManager().reminder_a() );
			break;
		case U_23:
			response.put( user.getDanGradingManager().reminder_l() );
			break;
		case U_24:// 发空包提示下就行了
			break;
		case U_25:
			response.put( user.getCaptainSkillManager().getCount() );
			break;
		case U_26:
			response.put( user.getVipLevel() );
		case U_27:
			response.putShort( user.getEquipbagCapacity() );
			break;
		case U_28:
			response.putShort( user.getMonthCardFate()[0] );
			break;
		case U_29:
			response.putInt( user.getTrophyNumer() );
			break;
		case U_30:// 发空包提示下就行了
			break;
		case U_31://
			response.putShort( user.getMonthCardFate()[1] );
			break;
		case U_32://
			response.putShort( user.getMonthCardFate()[2] );
			break;
		case U_33://
			response.putShort( (short) id );
			break;
		case U_34://
			response.put( user.getQualifyingManager().getQualifying().getResidueDegree() );
			response.put( user.getQualifyingManager().getQualifying().getFrequency() );
			break;
		case U_35://
//			1、第一个，体力
//			2、好友上限
//			3，背包上限
//			4、装备背包上限
//			5、pvp次数
//			6、rank次数
//			7、金币 
			LuaProxy lua = Lua.createLuaState( "gameData.lua" );
			Object[] ret = lua.retArray( 7, "getAllBuyNeedCrystal", 
					user.allBuyTimes[0], 
					user.allBuyTimes[1], 
					user.allBuyTimes[2], 
					user.allBuyTimes[3],
					user.allBuyTimes[4],
					user.allBuyTimes[5],
					user.allBuyTimes[6]
					);
			for( int i = 0; i < ret.length; i++ ){
				response.putShort( (short)(double)ret[i] );
			}
			break;
		case U_36:
			response.putInt( user.RMB );
			break;
		case U_90:
		case U_92:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short) lists.size() );
			for( Object o : lists ){
				EquipmentInfo x  = (EquipmentInfo)o;
//				EquipmentInfo e	 = user.getEquipmentManager().get( x.getUID() );
				
				response.putInt( x.getUID() );
				response.putInt( x.getTemplet().getNId() );
				response.putInt( x.getTheirHeroID() );
			}
			break;
		case U_91:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short) lists.size() );
			for( Object o : lists ){
				EquipmentInfo e	 = (EquipmentInfo)o;
				response.putInt( e.getUID() );
			}
			break;
		case U_100:
		case U_102:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short) lists.size() );
			for( Object o : lists ){
				Hero h	 	= (Hero)o;
				
				response.putInt( h.getUID() );
				response.putInt( h.getNid() );
				response.putShort( h.getLevel() );
				response.putInt( h.getExp() );
				h.getQuality().toByte( response );
				
				byte pos			= -1;
				byte isCaptain		= 0;
				byte isDie			= 0;
				TeamHero teamhero 	= user.getTeamManager().get( h.getUID() );
				if( teamhero != null ){
					pos				= teamhero.getPosition();
					isCaptain		= (byte) (user.getTeamManager().getCaptain().getUID() == h.getUID() ? 1 : 0);
//					isDie			= (byte) (teamhero.IsAbsoluteDie() ? 1 : 0) ;
					isDie			= (byte) (teamhero.getIsDie() ? 1 : 0) ;
				}
				response.put( pos );
				response.putInt( h.getHpMax() );
				response.putInt( h.getAttack() );
				response.put( isDie );
				response.put( isCaptain );
				response.put( h.getSkillAttack().getLevel() );
				response.putInt( h.getCaptainSkill().getID() );
			}
			break;
		case U_101:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short) lists.size() );
			for( Object o : lists ){
				Hero h	 	= (Hero)o;
				response.putInt( h.getUID() );
			}
			break;
		case U_110:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short)lists.size() );
			for( Object o : lists ){
				FriendBase f = (FriendBase)o;
				
				u = UserManager.getInstance().getByName( f.getUid() );
				if( u == null ){
					Logs.error( user, "更新好友出错  好友信息为NULL  UID=" + f.getUid() );
					continue;
				}
				
				response.putInt( u.getUID() );
				UtilBase.encodeString(response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run( u ) );
				// 算出离线时间
				int lastTime = u.isOnline() ? 0 : Math.max( (SystemTimer.currentTimeSecond() - u.getLastLogoutTime())/60, 1 );
				response.putInt( lastTime );
				response.put( f.gvStatusToByte() );
//				System.out.println( f.gvStatusToByte() );
				response.put( f.getStatusToByte() );
//				System.out.println( f.getStatusToByte() );
				// 获得队长信息
				Hero captain = u.getTeamManager().getCaptain();
				if( captain == null ){
					Logs.error( user, "更新好友出错  英雄队长信息为NULL " );
					return ;
				}
				response.putInt( captain.getUID() );
				response.putInt( captain.getNid() );
				response.putInt( captain.getHpMax() );
				response.putInt( captain.getAttack() );
				response.putShort( captain.getLevel() );
				captain.getQuality().toByte( response );
				response.putInt( captain.getCaptainSkill().getID() );
				response.put( captain.getSkillAttack().getLevel() );
			}
			break;
		case U_111:
			if( lists == null || lists.isEmpty() ) return;
			FriendBase f = (FriendBase)lists.get(0);
			response.putInt( f.getUid() );
			break;
		case U_112:
			if( lists == null || lists.isEmpty() ) return;
			f 			= (FriendBase)lists.get(0);
			u 			= UserManager.getInstance().getByName( f.getUid() );
			response.putInt( u.getUID() );
			response.putShort( u.getLevel() );
			response.putInt( FightingFormula.run( u ) );
			// 算出离线时间
			int lastTime = u.isOnline() ? 0 : Math.max( (SystemTimer.currentTimeSecond() - u.getLastLogoutTime())/60, 1 );
			response.putInt( lastTime );
			response.put( f.gvStatusToByte() );
			response.put( f.getStatusToByte() );
			break;
		case U_115:
			if( lists == null || lists.isEmpty() ) return;
			
			response.putShort( (short)lists.size() );
			for( Object o : lists ){
				int uid = (int)o;
				
				u 		= UserManager.getInstance().getByName( uid );
				if( u == null ){
					Logs.error( user, "更新添加列表用户出错  用户ID=" + uid + " 不存在！" );
					continue;
				}
				
				response.putInt( u.getUID() );
				UtilBase.encodeString(response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run( u ) );
				// 算出离线时间
				lastTime = u.isOnline() ? 0 : (SystemTimer.currentTimeSecond() - u.getLastLogoutTime())/60;
				response.putInt( lastTime );
				// 获得队长信息
				Hero captain = u.getTeamManager().getCaptain();
				if( captain == null ){
					Logs.error( user, "更新添加列表用户出错  英雄队长信息为NULL  用户ID=" + uid  );
					return ;
				}
				response.putInt( captain.getUID() );
				response.putInt( captain.getNid() );
				response.putInt( captain.getHpMax() );
				response.putInt( captain.getAttack() );
				response.putShort( captain.getLevel() );
				captain.getQuality().toByte( response );
				response.putInt( captain.getCaptainSkill().getID() );
				response.put( captain.getSkillAttack().getLevel() );
			}
			break;
		case U_118:
		case U_116:
			if( invited == null ) return;
			
			u 		= UserManager.getInstance().getByName( invited.getIMyID() );
			if( u == null ){
				Logs.error( user, "更新添加邀请好友列表用户出错  用户ID=" + invited.getIMyID() + " 不存在！" );
				return ;
			}
			
			response.putInt( u.getUID() );
			UtilBase.encodeString( response, u.getNickName() );
			response.putShort( u.getLevel() );
			response.putInt( FightingFormula.run(user) );
			
			Hero h				= u.getTeamManager().getCaptain();
			response.putInt( h.getUID() );
			response.putInt( h.getNid() );
			response.putShort( h.getLevel() );
			h.getQuality().toByte( response );
			response.put( h.getSkillAttack().getLevel() );
			response.putInt( h.getCaptainSkill().getID() );
			response.putInt( 0 );
			break;
		case U_117:
			if( invited == null ) return;
			
			response.putShort( (short)invited.getList().size() );
			
			for( InvitedBase base : invited.getList() ){
				
				u 		= UserManager.getInstance().getByName( base.getUID() );
				
				response.putInt( u.getUID() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run(user) );
				response.putInt( base.getGottenGold() );
			}
			break;
		case U_120:
			response.putInt( XOTime.currentTimeSecond() );
			byte at = (byte) (id == -2 ? 1 : 2) ;
			response.put( at );
			if( at == 1 ){// 玩家聊天
				
				response.putInt( Integer.parseInt( args[0] ) );
				UtilBase.encodeString( response, args[1] );
				response.putInt( Integer.parseInt( args[2] ) );
				String[] quality = args[3].split( "," );
				response.put( Byte.parseByte( quality[0] ) );
				response.put( Byte.parseByte( quality[1] ) );
				UtilBase.encodeString( response, args[4] );
				
			}else if( at == 2 ){// 系统消息
				
				response.putShort( (short) id );
				if( id == -1 ){
					UtilBase.encodeString( response, args[0] );
				}else{
					response.put( (byte) args.length );
					for( int i = 0; i < args.length; i++ )
						UtilBase.encodeString( response, args[i] );
				}
			}
			
//			response.putShort( (short) id );
//			response.put( (byte) args.length );
//			for( int i = 0; i < args.length; i++ )
//				UtilBase.encodeString( response, args[i] );
//			Logs.error( user, "收到一条信息" );
			break;
		}
		sendPackage( user.getCon(), response );
	}
	
}


