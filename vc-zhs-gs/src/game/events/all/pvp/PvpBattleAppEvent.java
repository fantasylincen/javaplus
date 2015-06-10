package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import user.UserManager;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;
import game.pvp.VideoBase;
import game.pvp.VideoRecording;

@EventDescrip(desc = "申请录像")
public class PvpBattleAppEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
//		byte at 	= buf.get();
		
		ByteBuffer response = buildEmptyPackage( 4098 );
//		response.put(at);
		
		byte at 	= (byte)1;
		switch( at )
		{
		case 1: // 申请匹配录像信息
			
			VideoRecording video				= user.getDanGradingManager().getVideoRecording();
				
			response.put( (byte) video.getList().size() );
			for( VideoBase v : video.getList() ){
				
				response.putInt( v.mID );
				response.putInt( v.mTouserID );
				String name = UserManager.getInstance().getNickName( v.mTouserID );
				UtilBase.encodeString( response, name );
				response.putInt( v.mTime );
				response.put( (byte) (v.mIsRevenge ? 1 : 0) );
				response.put( (byte) (v.mIsComplex ? 1 : 0) );
				response.put( (byte) (v.mIsWin ? 1 : 0) );
				response.putInt( v.mLootCash );
			}
			response.put( video.getRevengeCount() );
			
			sendPackage( user.getCon(), response );
			return;
			
		case 2: // 查看单条录像
			
			int id		= buf.getInt();
			
			VideoBase v	= user.getDanGradingManager().get( id );
			if( v == null ){
				Logs.error( user, "申请单条战报出错 没有该战报  id=" + id );
				return;
			}
			
			v.putHeroData( response, v.mALists , (byte) 0);
			v.putHeroData( response, v.mDLists , (byte) 6);
			
			ByteBuffer content 	= v.mData.asReadOnlyBuffer();
			response.put( content );
			
			sendPackage( user.getCon(), response );
			return;
		}
		
	}

}
