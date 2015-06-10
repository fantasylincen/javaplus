package game.events.all.hero;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.ErrorCode;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.log.Logs;
import game.team.TeamHero;

@EventDescrip(desc = "玩家英雄属性更新包")
public class UpDataUserHeroProp extends EventBase{

	private static final int PACK_LEN = 1024;
	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		byte pNo 			= buf.get();
		
		ByteBuffer response = buildEmptyPackage( PACK_LEN );
		response.put( (byte)pNo );
		
		ErrorCode code;
		
		switch( pNo )
		{
		case 1: // 出售英雄
			short num		= buf.getShort();
			
			code			= ErrorCode.SUCCESS;
			List<Hero> list	= new ArrayList<Hero>();
			for( int i = 0; i < num; i++ ){
				int suid		= buf.getInt();
				Hero shero 		= user.getHeroManager().getHero( suid );
				if( shero == null ){
					Logs.error( user, "出售英雄出错 没有ID=" + suid + "这个英雄!" );
					code		= ErrorCode.UNKNOW_ERROR;
					break ;
				}
				
				// 这里检查是否在出战中
				if( user.getTeamManager().get( suid ) != null ){
					Logs.error( user, "出售英雄出错 ID=" + suid + "出战中!" );
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				list.add( shero );
			}
			
			if( list.isEmpty() ){
				Logs.error( user, "出售列表为NULL" );
				code	= ErrorCode.UNKNOW_ERROR;
			}
			
			if( code == ErrorCode.SUCCESS )
				code 	= user.getHeroManager().sell( list );
			
			if( code == ErrorCode.SUCCESS )
			{
				UpdateManager.instance.update( user, UpdateType.U_3 );
				UpdateManager.instance.update( user, UpdateType.U_101, list );
			}
			
			// 这里不管怎样 都要等于SUCCESS 为了让前端删除
			code		= ErrorCode.SUCCESS;
			
			response.putShort( (short) code.ordinal() );
			
			sendPackage( user.getCon(), response );
			
			return;
		case 2: // 改变团队阵型
			
			List<TeamHero> 		teamHeroList 	= new ArrayList<TeamHero>();
			
			code				= ErrorCode.SUCCESS;
			byte count			= buf.get();
			List<Hero> xx 		= user.getTeamManager().getToHero();
			
			for( int i = 0; i < count; i++ )
			{
				int uID			= buf.getInt();
				byte pos		= buf.get();
				
				if( user.getHeroManager().getHero( uID ) == null ){
					Logs.error( user, "改变阵型失败   玩家 没有该英雄 UID=" + uID );
					code 		= ErrorCode.TEAM_NOT_HERO;
					break;
				}
				
				if( !user.getHeroManager().isCarryLevel( uID ) ){
					Logs.error( user, "改变阵型失败   玩家携带等级不足 UID=" + uID );
					code 		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				// 这里检查 看是否有重复
				for( TeamHero thero : teamHeroList ){
					if( thero.getPosition() == pos ){
						Logs.error( user, "改变阵型失败   位置重复" );
						code 		= ErrorCode.UNKNOW_ERROR;
						break;
					}
					if( thero.getUId() == uID ){
						Logs.error( user, "改变阵型失败   英雄重复" );
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
				}
				if( code != ErrorCode.SUCCESS ) break;
				
				// 这里 如果是改变阵型 那么 就不会是死的
				TeamHero thero 	= new TeamHero( uID, pos, false );
				teamHeroList.add( thero );
				
				Hero h			= user.getHeroManager().getHero( uID );
				if( xx.indexOf( h ) == -1 )
					xx.add( h );
			}
			
			if( code == ErrorCode.SUCCESS ) {
				user.getTeamManager().changeTeam( teamHeroList );
				UpdateManager.instance.update( user, UpdateType.U_102, xx );
				UpdateManager.instance.update( user, UpdateType.U_5 );
			}
			
			response.putShort( (short)code.ordinal() );
			
			sendPackage( user.getCon(), response );
			
			return;
		}
		
		
	}

}
