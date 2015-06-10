package game.events.all.equip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.ErrorCode;
import game.equipment.EquipmentInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "装备系统-出售")
public class EquipmentSellEvent extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		ErrorCode code				= ErrorCode.SUCCESS;
		List<EquipmentInfo> list 	= new ArrayList<EquipmentInfo>();
		short size 					= buf.getShort();
		for( int i = 0; i < size; i++ ){
			int id			= buf.getInt();
			EquipmentInfo e = user.getEquipmentManager().get(id);
			if( e == null ){
				code		= ErrorCode.UNKNOW_ERROR;
				Logs.error( user, "出售装备出错 没有装备=" + id );
				break;
			}
			
			list.add( e );
		}
		
		if( code == ErrorCode.SUCCESS )
			code			= user.getEquipmentManager().sell( list );
		
		ByteBuffer response = buildEmptyPackage( 18 );
		response.putShort( (short) code.ordinal() );
		sendPackage( user.getCon(), response );
		
		if( code == ErrorCode.SUCCESS )
			user.getEquipmentManager().remove( list );
		
	}

}
