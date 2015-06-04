package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.ErrorCode;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "匹配战斗-级位提升")
public class PvpMateUpgradeEvent extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte at 	= buf.get();
		
		ByteBuffer response = buildEmptyPackage( 32 );
		response.put(at);
		
		switch( at )
		{
		case 1: // 申请级位
			
			response.put( (byte) (user.getDanGradingManager().getInfo().isGetWelfare() ? 1 : 0) );
			sendPackage( user.getCon(), response );
			
			return;
		case 2: // 申请提升级位
			
			ErrorCode code = user.getDanGradingManager().startUpgrade();
			
			response.putShort( (short)code.ordinal() );
			if( code == ErrorCode.SUCCESS )
			{
				response.put( user.getDanGradingManager().getInfo().danGrad().toNumber() );
			}
			
			sendPackage( user.getCon(), response );
			
			if( code == ErrorCode.SUCCESS )
				user.getDanGradingManager().updata();
			
			return;
		
		case 3:// 领取福利
			
			code 		= user.getDanGradingManager().getWelfare();
			
			response.putShort( (short)code.ordinal() );
			
			sendPackage( user.getCon(), response );
			
			if( code == ErrorCode.SUCCESS )
				user.getDanGradingManager().updata();
			
			return;
		}
		
		
	}

}
