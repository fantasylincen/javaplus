package game.events.all.equip;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.ErrorCode;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "装备系统-装和卸")
public class EquipmentCDEvent extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		int heroID		= buf.getInt();
		int equID		= buf.getInt();
		byte type		= buf.get();
		
		ErrorCode code 	= user.getEquipmentManager().handle( heroID, equID, type );
		
		ByteBuffer response = buildEmptyPackage( 18 );
		response.putShort( (short) code.ordinal() );
		sendPackage( user.getCon(), response );
		
		if( code == ErrorCode.SUCCESS ){
			user.getHeroManager().update( heroID );
			user.getEquipmentManager().updata( equID );
		}
		
	}

}
