package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;

import manager.DWType;

import user.UserInfo;
import util.ErrorCode;
import game.activity.ActivityManager;
import game.activity.DragonBase;
import game.award.AwardType;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

@EventDescrip(desc = "申请 开启鼓舞")
public class DragonInspireEvent extends EventBase{

	// 开启鼓舞需要金币
	private final int needCash	= 68888;
	
	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
	
		ErrorCode code 	= ErrorCode.SUCCESS;
		
		do{
			
			// 检查是否已经鼓舞
			DragonBase dragon = ActivityManager.getInstance().getDragon();
			if( dragon.isHaveInspire( user ) ){
				code		= ErrorCode.CHALLENGE_HAVE_INSPIRE;
				break;
			}
			
			// 最后直接扣金币
			if( user.changeAward( AwardType.CASH, -needCash, "挑战大龙 开启鼓舞", DWType.MISCELLANEOUS ) == -1 ){
				code 		= ErrorCode.USER_CASH_NOT_ENOUTH;
				break;
			}
			
			// 然后开启鼓舞
			dragon.startInspire( user );
			
			UpdateManager.instance.update( user, UpdateType.U_3 );
			
		}while( false );
		
		ByteBuffer response 		= buildEmptyPackage( 16 );
		response.putShort( (short) code.ordinal() );
		sendPackage( user.getCon(), response );
	}

}
