package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.fighter.Hero;
import game.log.Logs;
import game.pvp.MatchingType;
import game.team.TeamHero;
import game.util.fighting.FightingFormula;

@EventDescrip(desc = "匹配 - 申请卡片详细信息")
public class PvpMateAppCardInfoEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte at				= buf.get();
		
		ByteBuffer response = buildEmptyPackage( 512 );
		response.put( at );
		
		switch( at ){
		case 1: // 申请上阵英雄数据
			
			byte card 			= buf.get();
			
			MatchingType type 	= MatchingType.fromNumber(card);
			if( type == null )
				type			= user.getTeamManager().getMateType();
			
			List<TeamHero> list = user.getTeamManager().getTeam(type);
			
			response.put( type.toNumber() );
			response.put( (byte) list.size() );
			for( int i = 0; i < list.size(); i++ ){
				
				TeamHero x			= list.get(i);
				Hero hero			= user.getHeroManager().getHero( x.getUId() );
				
				response.putInt( hero.getUID() );
				response.put( x.getPosition() );
				response.put( (byte) (i == 0 ? 1 : 0) );
			}
			
			response.putInt( FightingFormula.run( user, type ) );
			
			sendPackage( user.getCon(), response );
			
			return;
		case 2: // 申请更新战斗力

			byte size 			= buf.get();
			
			List<Hero> lists 	= new ArrayList<Hero>();
			for( int i = 0; i < size; i++ ){
				
				int uid = buf.getInt();
				Hero h 	= user.getHeroManager().getHero(uid);
				if( h == null ){
					Logs.error( user, "匹配 申请更新战斗力出错 UID=" + uid );
					continue;
				}
				
				lists.add( h );
			}
			
			int result 		= FightingFormula.run( user, lists );
			
			response.putInt( result );
			sendPackage( user.getCon(), response );
			
			return;
		}
		
		
	}

}
