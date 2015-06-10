package game.events.all.friend;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.friend.FriendBase;
import game.log.Logs;
import game.util.fighting.FightingFormula;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import define.DefaultCfg;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;


/**
 * 好友系统 消息 处理
 * @author DXF
 *
 */
public enum FriendEventHandle {
	
	// 申请好友列表
	SEND_LIST( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			List<FriendBase> list 	= user.getFriendManager().getListFriend();
			
			response.putShort( user.getFriendCapacity() );
			response.putShort( (short)list.size() );
			for( FriendBase friend : list )
			{
				UserInfo u = UserManager.getInstance().getByName( friend.getUid() );
				if( u == null ){
					Logs.error( user, "申请好友列表出错  好友信息为NULL  UID=" + friend.getUid() );
					return true;
				}
				
				response.putInt( u.getUID() );
				UtilBase.encodeString( response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run( u ) );
				// 算出离线时间
				int lastTime = u.isOnline() ? 0 : Math.max( (SystemTimer.currentTimeSecond() - u.getLastLogoutTime())/60, 1 );
				response.putInt( lastTime );
				response.put( friend.gvStatusToByte() );
				response.put( friend.getStatusToByte() );
				// 获得队长信息
				Hero captain = u.getTeamManager().getCaptain();
				if( captain == null ){
					Logs.error( user, "申请好友列表出错  英雄队长信息为NULL " );
					return true;
				}
				response.putInt( captain.getUID() );
				response.putInt( captain.getNid() );
				response.putShort( captain.getLevel() );
				captain.getQuality().toByte( response );
				response.putInt( captain.getCaptainSkill().getID() );
			}
			
			return false;
		}
	},
	
	// 赠送体力
	GV_STRENGTH( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 			= data.getInt();
			
			// 先获取 已经 用过得次数
			short beforeTimes 	= user.getFriendManager().getGvTimes();
			
			ErrorCode code 		= user.getFriendManager().gvStrength( uID );
			
			response.putInt( uID );
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			short curTimes		= 0;
			if( code == ErrorCode.FRIEND_GVTIMES_BEYOND )
				curTimes		= -1;
			else
				curTimes		= (short) (user.getFriendManager().getGvTimes() - beforeTimes);
			response.putShort( curTimes );
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// 如果是批量 那么获得当前用掉次数
			if( uID == -1 ){
				
//				short curTimes		= 0;
//				if( code == ErrorCode.FRIEND_GVTIMES_BEYOND )
//					curTimes		= -1;
//				else
//					curTimes		= (short) (user.getFriendManager().getGvTimes() - beforeTimes);
//				response.putShort( curTimes );
				
				if( code != ErrorCode.UNKNOW_ERROR ){
					// 获得可以已经赠送列表
					List<Integer> list	= user.getFriendManager().hasPresentedList();
					response.put( (byte) list.size() );
					for( int i : list ){
						response.putInt( i );
					}
				}else{
					response.put( (byte)0 );
				}
				
			}
			// 剩余次数
			short surplusTimes 		= (short) (DefaultCfg.FRIEND_GV_TIMES - user.getFriendManager().getGvTimes());
			response.putShort( surplusTimes );
			
			return false;
		}
	},
		
	// 领取体力
	GET_STRENGTH( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 			= data.getInt();
			
			// 先获取 已经 用过得次数
			short beforeTimes 	= user.getFriendManager().getGetTimes();
						
			ErrorCode code		= user.getFriendManager().getStrength( uID );
			
			response.putInt( uID );
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			short curTimes		= 0;
			if( code == ErrorCode.FRIEND_GETTIMES_BEYOND )
				curTimes		= -1;
			else
				curTimes		= (short) (user.getFriendManager().getGetTimes() - beforeTimes);
			response.putShort( curTimes );
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// 如果是批量 那么获得当前用掉次数
			if( uID == -1 ){
				
//				short curTimes		= 0;
//				if( code == ErrorCode.FRIEND_GETTIMES_BEYOND )
//					curTimes		= -1;
//				else
//					curTimes		= (short) (user.getFriendManager().getGetTimes() - beforeTimes);
//				response.putShort( curTimes );
				
				if( code != ErrorCode.UNKNOW_ERROR ){
					// 获得已领取列表
					List<Integer> list	= user.getFriendManager().alreadyGettheList();
					response.put( (byte) list.size() );
					for( int i : list ){
						response.putInt( i );
					}
				}else{
					response.put( (byte)0 );
				}
			}
			
			// 剩余次数
			short surplusTimes 	= (short) (DefaultCfg.FRIEND_GET_TIMES - user.getFriendManager().getGetTimes());
			response.putShort( surplusTimes );
			
			return false;
		}
	},
	
	// 邀请列表
	GET_BEG_LIST( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			List<Integer> list 		= user.getFriendManager().getListBeg();
			
			response.putShort( (short)list.size() );
			for( int uId : list ){
				UserInfo u 		= UserManager.getInstance().getByName( uId );
				if( u == null ){
					Logs.error( user, "查找用户出错  用户ID=" + uId + " 不存在！" );
				}
				
				response.putInt( u.getUID() );
				UtilBase.encodeString(response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run( u ) );
				// 算出离线时间
				int lastTime = u.isOnline() ? 0 : (SystemTimer.currentTimeSecond() - u.getLastLogoutTime())/60;
				response.putInt( lastTime );
				response.put( (byte)0 );
				response.put( (byte)0 );
				// 获得队长信息
				Hero captain = u.getTeamManager().getCaptain();
				if( captain == null ){
					Logs.error( user, "查找用户出错  英雄队长信息为NULL  用户ID=" + uId  );
					return true;
				}
				response.putInt( captain.getUID() );
				response.putInt( captain.getNid() );
				response.putShort( captain.getLevel() );
				captain.getQuality().toByte( response );
				response.putInt( captain.getCaptainSkill().getID() );
			}
			
			return false;
		}
	},
	
	// 查找
	FIND_USER( 5 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 		= data.getInt();
			
			UserInfo u 		= UserManager.getInstance().getByName( uID );
			if( u == null ){
				Logs.error( user, "查找用户出错  用户ID=" + uID + " 不存在！" );
				uID = -1;
			}
			
			response.putInt( uID );
			
			if( uID > 0 ){
				UtilBase.encodeString(response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run( u ) );
				// 获得队长信息
				Hero captain = u.getTeamManager().getCaptain();
				if( captain == null ){
					Logs.error( user, "查找用户出错  英雄队长信息为NULL  用户ID=" + uID  );
					return true;
				}
				response.putInt( captain.getUID() );
				response.putInt( captain.getNid() );
				response.putShort( captain.getLevel() );
				captain.getQuality().toByte( response );
				response.putInt( captain.getCaptainSkill().getID() );
				response.put( captain.getSkillAttack().getLevel() );
				
				response.put( (byte)(user.getFriendManager().isFriend( uID) ? 1 : 0) );
			}
			
			return false;
		}
	},
	
	// 添加好友
	ADD_FRIEND( 6 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 		= data.getInt();

			if( uID == user.getUID() ){
				Logs.error( user, "不能添加自己为好友" );
				return true;
			}
			
			// 先判断一下 这个uID是否存在
			UserInfo u 		= UserManager.getInstance().getByName( uID );
			if( u == null ){
				Logs.error( user, "添加好友  用户信息为NULL  UID=" + uID );
				return true;
			}
			
			ErrorCode code 	= user.getFriendManager().addFriend( u );
			
			response.putShort( (short)code.ordinal() );
			
			if( code == ErrorCode.FRIEND_ADD_OK ){
				response.putInt( uID );
			}
			
			return false;
		}
	},
	
	// 同意邀请
	AGREE_BEG_FRIEND( 7 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 		= data.getInt();
			boolean isAgree	= data.get() == 1;
			
			if( uID == user.getUID() ){
				Logs.error( user, "不能添加自己为好友" );
				return true;
			}
			
			// 先判断一下 这个uID是否存在
			UserInfo u 		= UserManager.getInstance().getByName( uID );
			if( u == null ){
				Logs.error( user, "同意邀请  用户信息为NULL  UID=" + uID );
				return true;
			}
			
			ErrorCode code 	= user.getFriendManager().begFriendHandle( u , isAgree );
			
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.FRIEND_REMOVE_BEG || code == ErrorCode.SUCCESS )
				response.putInt( uID );
			
			return false;
		}
	},
	
	// 删除好友
	REMOVE_FRIEND( 8 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 		= data.getInt();
			
			// 先判断一下 这个uID是否存在
			UserInfo u 		= UserManager.getInstance().getByName( uID );
			if( u == null ){
				Logs.error( user, "删除好友  用户信息为NULL  UID=" + uID );
				return true;
			}
			
			ErrorCode code 	= user.getFriendManager().removeFriend( u );
			
			// 如果删除成那么也要从对方列表中将自己删除
			if( code == ErrorCode.SUCCESS ){
				u.getFriendManager().removeFriend( user );
				if( u.isOnline() ){
					FriendBase f = new FriendBase();
					f.setUid( user.getUID() );
					List<FriendBase> l = new ArrayList<FriendBase>();
					l.add(f);
					UpdateManager.instance.update( u, UpdateType.U_111, l );
				}
			}else{
				uID			= 0;
			}
			
			response.putInt( uID );
			
			return false;
		}
	},
	// 提示服务器更新时间
	TIP_THE_UPDATA_TIME( 9 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			// 先根据时间刷新一下  好友数据
			boolean isUpdate = user.getFriendManager().updataTime();
			
			response.put( (byte) (isUpdate ? 1 : 0) );
			
			return false;
		}
	}
	
	;
	
	
	private final byte 				number;
	
	FriendEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, FriendEventHandle> numToEnum = new HashMap<Byte, FriendEventHandle>();
	static{
		for( FriendEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static FriendEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
