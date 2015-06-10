package game.events.all.invitingfriends;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.invitingfriends.InvitedBase;
import game.invitingfriends.InvitingFriendsBase;
import game.util.fighting.FightingFormula;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.UtilBase;

public enum InvitingFriendsEventHandle {

	// 申请 我邀请的 列表
	APPLY_FOR_MY_INVITATION( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			List<InvitedBase> list		= user.getInvitingFriendsManager().getList();
			
			response.putShort( (short)list.size() );
			for( InvitedBase i : list ){
				UserInfo u 			= UserManager.getInstance().getByName( i.getUID() );
				
				response.putInt( u.getUID() );
				UtilBase.encodeString( response, u.getNickName() );
				response.putShort( u.getLevel() );
				response.putInt( FightingFormula.run(u) );
				
				Hero h				= u.getTeamManager().getCaptain();
				response.putInt( h.getUID() );
				response.putInt( h.getNid() );
				response.putShort( h.getLevel() );
				h.getQuality().toByte( response );
				response.put( h.getSkillAttack().getLevel() );
				response.putInt( h.getCaptainSkill().getID() );
				response.putInt( i.getGottenGold() );
			}
			
			return false;
		}
	},
	
	// 申请 邀请我的
	APPLY_INVITE_MY( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int id				= user.getInvitingFriendsManager().getIMyID();
			UserInfo u			= null;
			
			if( id != -1 ){
				u 				= UserManager.getInstance().getByName( id );
			}
			
			response.putInt( u == null ? -1 : u.getUID() );
			if( u == null ) return false;
			
			UtilBase.encodeString( response, u.getNickName() );
			response.putShort( u.getLevel() );
			response.putInt( FightingFormula.run(u) );
			
			Hero h				= u.getTeamManager().getCaptain();
			response.putInt( h.getUID() );
			response.putInt( h.getNid() );
			response.putShort( h.getLevel() );
			h.getQuality().toByte( response );
			response.put( h.getSkillAttack().getLevel() );
			response.putInt( h.getCaptainSkill().getID() );
			
			return false;
		}
	},
	
	// 查找
	FIND( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int id				= data.getInt();
			UserInfo u			= null;
			
			if( id != -1 ){
				u 				= UserManager.getInstance().getByName( id );
			}
			
			response.putInt( u == null ? -1 : u.getUID() );
			if( u == null ) return false;
			
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
			
			return false;
		}
	},
	
	// 确认添加
	CONFIRM_TO_ADD( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int id				= data.getInt();
			UserInfo u			= null;
			if( id != -1 )
				u 				= UserManager.getInstance().getByName( id );
			
			ErrorCode code 		= user.getInvitingFriendsManager().toAdd( u );
			
			response.putInt( (short) code.ordinal() );
			
			if( code == ErrorCode.SUCCESS ){
//				InvitingFriendsEvent p = (InvitingFriendsEvent) Event.FRIEND_INVITE_SYSTEM.getEventInstance();
//				p.run( user, APPLY_INVITE_MY );
				UpdateManager.instance.update( user, UpdateType.U_118, user.getInvitingFriendsManager().getInvitingFriends() );
				
				if( u.isOnline() ){
					InvitingFriendsBase ifb = new InvitingFriendsBase();
					ifb.setIMyID( user.getUID() );
					UpdateManager.instance.update( u, UpdateType.U_116, ifb );
				}
			}
			
			return false;
		}
	},
	
	// 领取水晶
	GET_THE_CRYSTAL( 5 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int id				= data.getInt();
			UserInfo u			= null;
			if( id != -1 )
				u 				= UserManager.getInstance().getByName( id );
			
			ErrorCode code 		= user.getInvitingFriendsManager().getTheCrystal( u );
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				response.putInt( id );
			}
			
			return false;
		}
	}
	
	;
	
	
	private final byte 				number;
	
	InvitingFriendsEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, InvitingFriendsEventHandle> numToEnum = new HashMap<Byte, InvitingFriendsEventHandle>();
	static{
		for( InvitingFriendsEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static InvitingFriendsEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
