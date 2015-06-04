package game.events.all.talent;

import game.log.Logs;
import game.talent.TalentBase;
import game.talent.TalentType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserInfo;
import util.ErrorCode;

/**
 * 天赋 信息 管理
 * @author DXF
 *
 */
public enum TalentEventHandle {

	// 申请列表
	APPLY_LIST( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			Map<TalentType,TalentBase> list = user.getTalentManager().getTalents();
			
			response.put( (byte) (list.values().size()) );
			
			for( TalentBase talent : list.values() ){
				response.put( talent.getType().toNumber() );
				response.putShort( talent.getLevel() );
				response.putInt( talent.getRecordingTime() );
			}
			
			return false;
		}
	},
	// 升级
	UPGRADE( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			byte at 		= data.get();
			TalentType type = TalentType.fromNumber( at ); 
			
			if( type == null ){
				Logs.error( user, "天赋升级出错 没有找到ID=" + at);
				return true;
			}
			
			ErrorCode code 	= user.getTalentManager().upgrade( type );
			
			response.put( at );
			response.putShort( (short) (code.ordinal()) );
			if( code == ErrorCode.SUCCESS ){
				response.putShort( user.getTalentManager().get(type).getLevel() );
			}
			if( code == ErrorCode.TALENT_UPGRADE_TIME ){
				response.putInt( user.getTalentManager().get(type).getRecordingTime() );
			}
			
			return false;
		}
	},
	// 立即完成
	OTS_COMPLETED( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			byte at 		= data.get();
			TalentType type = TalentType.fromNumber( at ); 
			if( type == null ){
				Logs.error( user, "天赋升级出错 没有找到ID=" + at);
				return true;
			}
			
			ErrorCode code 	= user.getTalentManager().otsComplete( type );
			
			response.put( at );
			response.putShort( (short) (code.ordinal()) );
			if( code == ErrorCode.SUCCESS || code == ErrorCode.TALENT_TIME_COMPLETE ){
				response.putShort( user.getTalentManager().get(type).getLevel() );
			}
			
			return false;
		}
	},
	// 时间轴跑完
	TIMER_SHAFT( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			byte at 		= data.get();
			TalentType type = TalentType.fromNumber( at ); 
			if( type == null ){
				Logs.error( user, "天赋升级出错 没有找到ID=" + at);
				return true;
			}
			
			ErrorCode code 	= user.getTalentManager().timerShaft( type );
			
			response.put( at );
			response.putShort( (short) (code.ordinal()) );
			if( code == ErrorCode.SUCCESS ){
				response.putShort( user.getTalentManager().get(type).getLevel() );
			}
			if( code == ErrorCode.TALENT_DELAY ){
				response.putInt( user.getTalentManager().get(type).getRecordingTime() + 1 );
			}
			
			return false;
		}
	},
	// 一键升级
	THEKEY_TOUPGRADE( 5 ) {
		@Override
		public boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException {
			
			List<TalentType> list 	= new ArrayList<TalentType>();
			ErrorCode code 			= user.getTalentManager().toUpgrade( list );
			
			response.putShort( (short) (code.ordinal()) );
			if( code == ErrorCode.SUCCESS ){
				response.put( (byte)list.size() );
				for( TalentType type : list ){
					TalentBase t 	= user.getTalentManager().get( type );
					response.put( type.toNumber() );
					response.putShort( t.getLevel() );
					response.putInt( t.getRecordingTime() );
				}
			}
			
			return false;
		}
	};
	
	
	private final byte 				number;
	
	TalentEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, TalentEventHandle> numToEnum = new HashMap<Byte, TalentEventHandle>();
	static{
		for( TalentEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static TalentEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
