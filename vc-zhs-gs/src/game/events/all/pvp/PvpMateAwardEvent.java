package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.ErrorCode;
import game.events.EventBase;
import game.events.EventDescrip;
import game.pvp.DanGradingBase;

@EventDescrip(desc = "匹配奖励系统")
public class PvpMateAwardEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte at 	= buf.get();
		
		ByteBuffer response = buildEmptyPackage( 32 );
		response.put(at);
		
		switch( at )
		{
		case 1: // 申请奖励列表
			
			DanGradingBase d = user.getDanGradingManager().getInfo();
			
			response.put( (byte)d.inorderToobtain().size() );
			for( byte i : d.inorderToobtain() ){
				response.put( i );
			}
			
			sendPackage( user.getCon(), response );
			
			return;
		case 2: // 领取奖励
			
			byte id = buf.get();
			
			ErrorCode code = user.getDanGradingManager().GetTheRewards( id );
			
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.put( id );
			
			sendPackage( user.getCon(), response );
			
			if( code == ErrorCode.SUCCESS )
				user.getDanGradingManager().updata();
			
			return;
		}
		
	}

	
}
