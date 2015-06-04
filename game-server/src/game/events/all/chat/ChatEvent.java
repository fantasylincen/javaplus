package game.events.all.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import deng.xxoo.utils.XOTime;

import notice.ChatContent;
import notice.NoticeManager;

import user.UserInfo;
import user.UserManager;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.fighter.Hero;
import game.util.fighting.FightingFormula;

@EventDescrip(desc = "聊天系统")
public class ChatEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte tag 			= buf.get();
		ByteBuffer response = buildEmptyPackage( 51200 );
		response.put( tag );
		
		switch( tag )
		{
		case 1: // 发送聊天信息
		{
			// 先检测是否连续发
			if( chekTime( user ) ){
				response.putShort( (short) -1 );
				sendPackage( user.getCon(), response );
				return;
			}
			
			user.chatTime	= XOTime.currentTimeSecond();
			//[玩家UID,玩家名字,队长英雄表格ID,品质颜色,品质强等,聊天内容]
			String UID 		= String.valueOf( user.getUID() );
			String nickname	= user.getNickName();
			String heroNID	= String.valueOf( user.getTeamManager().getCaptain().getNid() );
			String heroQ	= String.valueOf( user.getTeamManager().getCaptain().getQuality().toContentNew() );
			String content 	= UtilBase.decodeString(buf);
			NoticeManager.getInstance().addTimely( -2, UID, nickname, heroNID, heroQ, content );
			
			response.putShort( (short) 0 );
			sendPackage( user.getCon(), response );
		}
		return;
		case 2: // 申请聊天面板内容
		{
			List<ChatContent> list = NoticeManager.getInstance().getChatContent();
			response.putShort( (short) list.size() );
//			Logs.error( user, "申请聊天面板内容 一共" + list.size() );
			for( ChatContent chat : list ){
				response.putInt( chat.getTime() );
				
				byte at = (byte) (chat.getId() == -2 ? 1 : 2) ;
				response.put( at );
				if( at == 1 ){// 玩家聊天
					
					response.putInt( Integer.parseInt( chat.getArgs()[0] ) );
					UtilBase.encodeString( response, chat.getArgs()[1] );
					response.putInt( Integer.parseInt( chat.getArgs()[2] ) );
					String[] quality = chat.getArgs()[3].split( "," );
					response.put( Byte.parseByte( quality[0] ) );
					response.put( Byte.parseByte( quality[1] ) );
					UtilBase.encodeString( response, chat.getArgs()[4] );
					
				}else if( at == 2 ){// 系统消息
					
					response.putShort( (short) chat.getId() );
					if( chat.getId() == -1 ){
						UtilBase.encodeString( response, chat.getArgs()[0] );
					}else{
						response.put( (byte) chat.getArgs().length );
						for( int i = 0; i < chat.getArgs().length; i++ )
							UtilBase.encodeString( response, chat.getArgs()[i] );
					}
				}
				
//				response.putShort( (short) chat.getId() );
//				response.put( (byte) chat.getArgs().length );
//				for( int i = 0; i < chat.getArgs().length; i++ )
//					UtilBase.encodeString( response, chat.getArgs()[i] );
			}
			
			sendPackage( user.getCon(), response );
		}
		return;
		case 3: // 申请玩家信息
		{
			int UID 	= buf.getInt();
			UserInfo x 	= UserManager.getInstance().getByName(UID);
			
			if( x == null ){
				response.putShort( (short) -1 );
				sendPackage( user.getCon(), response );
				return;
			}

			response.putShort( (short) 0 );
			
			response.putInt( x.getUID() );
			UtilBase.encodeString( response, x.getNickName() );
			
			response.putShort( x.getLevel() );
			response.put( x.getDanGradingManager().getInfo().danGrad().toNumber() );
			response.put( x.getVipLevel() );
			response.putInt( FightingFormula.run( x ) );
			List<Hero> our 	= x.getTeamManager().getToHero();
			response.put( (byte) our.size() );
			for( Hero h : our ){
				response.putInt( h.getNid() );
				h.getQuality().toByte(response);
			}
			
			sendPackage( user.getCon(), response );
		}
		return;
		}
		
		
	}

	private boolean chekTime( UserInfo user ) {
		if( user.chatTime == 0 ) return false;
		if( user.chatTime == -1 ) return true;
		return (XOTime.currentTimeSecond() - user.chatTime) <= 8;
	}
	
}
