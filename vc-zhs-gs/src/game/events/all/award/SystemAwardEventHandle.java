package game.events.all.award;

import game.award.AwardInfo;
import game.award.system.AwardBase;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.log.Logs;
import game.luckydraw.LuckydrawManager;
import game.luckydraw.SweepstakeType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.DWType;

import config.luckydraw.LuckydrawTemplet;
import config.saward.SAwardType;
import config.saward.SystemAwardTemplet;
import config.saward.SystemAwardTempletCfg;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;

/**
 * 系统奖励信息处理
 * @author DXF
 */
public enum SystemAwardEventHandle {

	// 申请列表
	APPLY_LIST( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			byte type 				= data.get();
			List<AwardBase> list 	= user.getAwardManager().getList( type );
//			List<AwardBase> list 	= user.getAwardManager().getList(  );
			
			response.put( (byte) list.size() );
			
			for( AwardBase award : list ){
//				Logs.error( user, award.m_nID );
				response.putInt( award.m_nID );
				response.putInt( award.m_nCondition.getValue() );
			}
			
			return false;
		}
	},
	
	// 领取
	RECEIVE( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int id						= data.getInt();
			
			SystemAwardTemplet templet 	= SystemAwardTempletCfg.getTempletById( id );
			if( templet == null ){
				Logs.error( user, "领取系统奖励错误 id=" + id );
				return true;
			}
			
			ErrorCode code = user.getAwardManager().execute( templet.m_nType );
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				AwardBase base = user.getAwardManager().get(templet.m_nType);
				
				response.putInt( base.isEveryday() ? 0 : base.m_nID );
				if( !base.isEveryday() )
				{
					response.putInt( base.m_nCondition.getValue() );
				}
			}
			
			UserManager.getInstance().putUpdate( user );
			return false;
		}
	},
	
	// 领取连续登陆奖励
	CONTINUOUS_RECEIVE( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			ErrorCode code = ErrorCode.SUCCESS;
			
			do{
				
				if( user.getDayLoginCount() < 0 ){
					code 					= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				// 第7天 的
				if( user.getDayLoginCount() >= 7 ){
					code 					= ErrorCode.CONTINUOUS_AWARD_SEVEN;
					break;
				}
				
				int id 						= user.getDayLoginCount() + 205000;
				SystemAwardTemplet templet 	= SystemAwardTempletCfg.getTempletById( id );
				if( templet == null ){
					Logs.error( user, "领取系统奖励错误 id=" + id );
					code 					= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				// 发放奖励
				for( AwardInfo a : templet.m_nAward )
					user.changeAward( a, "系统发送奖励  类型：" + a.getAward(), DWType.SYSTEM_IS_PRESENTED );
				
				user.setDayLoginCount( user.getDayLoginCount() * -1 );
			} while( false );
		
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.CONTINUOUS_AWARD_SEVEN ){
				
				List<LuckydrawTemplet> x 		= LuckydrawManager.instance.startSweepstake( SweepstakeType.ONCE, user.getUID() );
				LuckydrawTemplet temp			= x.get(0);
				try {
					response.putInt( temp.getNid() );
					temp.getQuality().toByte( response );
					user.setDayLoginCount( user.getDayLoginCount() * -1 );
					
				} catch (Exception e) {
				}finally {
					int id	= user.getHeroManager().create( temp.getNid(), temp.getQuality().getColour(),(short)1, temp.getQuality().getLevel(), false );
					List<Hero> list 			= new ArrayList<Hero>();
					list.add( user.getHeroManager().getHero(id) );
					UpdateManager.instance.update( user, UpdateType.U_100, list );
				}
			}
			
			UserManager.getInstance().putUpdate( user );
			return false;
		}
	
	},
		
	// 分享微信
	SHARE_MICRO_LETTER( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			if( user.getAwardManager().get( SAwardType.LAT ).m_nCondition.getValue() == -1 )
				return false;
			
			user.getAwardManager().record( SAwardType.LAT, 1 );
			
			return false;
		}
	}
	;
	
	
	private final byte 				number;
	SystemAwardEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, SystemAwardEventHandle> numToEnum = new HashMap<Byte, SystemAwardEventHandle>();
	static{
		for( SystemAwardEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static SystemAwardEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
