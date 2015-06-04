package game.events.all.mail;

import game.events.all.tickling.TicklingDB;
import game.log.Logs;
import game.mail.MailBase;
import game.mail.MailType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.UtilBase;

/**
 * 邮件系统 消息 处理
 * @author DXF
 *
 */
public enum MailEventHandle {

	// 申请邮件列表
	APPLY_LIST( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			byte index 		= data.get();
			
			List<MailBase> mails 	= user.getMailManager().get( index );
			
			response.put( user.getMailManager().getPageControl().getCurPage() );
			response.put( user.getMailManager().getPageControl().getMaxPage() );
			response.putShort( user.getMailManager().getPageControl().getCurNum() );
			response.put( (byte)mails.size() );
			for( MailBase mail : mails ){
				
				response.putInt( mail.getMailID() );
				response.put( mail.getType().toNumber() );
				UtilBase.encodeString( response, mail.getTitle() );
				response.put( (byte) (mail.getIsRead() ? 1 : 0) );
				response.putShort( mail.getSurplusTime() );
			}
			
			return false;
		}
	},
	
	// 读取邮件内容 
	READ_MAIL( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int mailID 				= data.getInt();
			
			MailBase mail			= user.getMailManager().readMail( mailID );
			
			int mailId				= mail != null ? mail.getMailID() : -1;
			response.putInt( mailId );
			if( mailId > 0 )
			{
				response.put( mail.getType().toNumber() );
				UtilBase.encodeString( response, mail.getTitle() );
				UtilBase.encodeString( response, mail.getContent() );
			}
			
			return false;
		}
	},
	
	// 领取（限 邮件类型为系统赠送 ） 
	PULL_ITEM( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int mailID 				= data.getInt();
			
			MailBase mail			= user.getMailManager().receiveItem( mailID );
			
			ErrorCode code			= mail != null ? ErrorCode.SUCCESS : ErrorCode.UNKNOW_ERROR;
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.SUCCESS )
			{
//				response.put( mail.getItem().getAward().toNumber() );
//				response.putShort( (short)mail.getItem().getNumber() );
			}
			
			return false;
		}
	},
	
	// 删除 已读 邮件
	REMOVE_MAIL( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int mailID 				= data.getInt();
			
			ErrorCode code			= user.getMailManager().removeReadMail( mailID );
			
			response.putShort( (short)code.ordinal() );
			
			return false;
		}
	},
	
	// 发送邮件
	SEND_MSG( 5 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			int uID 		= data.getInt();
			String msg		= UtilBase.decodeString( data );
			
			// 先判断一下 这个uID是否存在
			UserInfo u 		= UserManager.getInstance().getByName( uID );
			if( u == null ){
				Logs.error( user, "发送消息  用户信息为NULL  UID=" + uID );
				return true;
			}
			
			MailBase mail = new MailBase( user.getNickName(), MailType.FRIEND_MSG, -1 + "|" + msg );
			
			ErrorCode code 	= u.getMailManager().addMail( mail );
			
			response.putShort( (short)code.ordinal() );
			
			return false;
		}
	},
	
	// 发送反馈
	SEND_TICKLING( 6 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			String content = UtilBase.decodeString(data);
			TicklingDB.put( content, user );
			response.putShort( (short) 0 );
			return false;
		}
	},
	
	// 回复
	REPLY( 7 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			String name		= UtilBase.decodeString( data );
			String msg		= UtilBase.decodeString( data );
			
			// 先判断一下 这个uID是否存在
			UserInfo u 		= UserManager.getInstance().getByNickName( name );
			if( u == null ){
				Logs.error( user, "发送消息  用户信息为NULL  name=" + name );
				response.putShort( (short)ErrorCode.UNKNOW_ERROR.ordinal() );
				return false;
			}
			
			MailBase mail = new MailBase( user.getNickName(), MailType.FRIEND_MSG, -1 + "|" + msg );
			
			ErrorCode code 	= u.getMailManager().addMail( mail );
			
			response.putShort( (short)code.ordinal() );
			return false;
		}
	};
	
	
	private final byte 				number;
	
	MailEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, MailEventHandle> numToEnum = new HashMap<Byte, MailEventHandle>();
	static{
		for( MailEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static MailEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
