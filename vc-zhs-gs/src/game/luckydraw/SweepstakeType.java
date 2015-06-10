package game.luckydraw;

import game.award.AwardType;

import java.util.HashMap;
import java.util.Map;

import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import manager.DWType;
import user.UserInfo;
import util.ErrorCode;

public enum SweepstakeType {
	
	/** 一次 */
	ONCE(1) {
		
		@Override
		public ErrorCode checkGold( UserInfo user ) {
			int needGold 			= 280;
//			if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold = needGold * DefaultCfg.DZ_CONSUMPTION_RATIO;
			if( user.changeAward( AwardType.GOLD, -needGold, "祈福 购买一次 消耗水晶", DWType.PRAYERS_FOR_A_SINGLE ) == -1 )
				return ErrorCode.USER_GOLD_NOT_ENOUTH;
			DataLogDataProvider.getInstance().add( user, ConsumelogF.SINGLE_PUMP, needGold );
			return ErrorCode.SUCCESS;
		}

	},
	
	/** 十次 */
	TENTIMES(2) {
		
		@Override
		public ErrorCode checkGold( UserInfo user ) {
			int needGold 			= 2500;
//			if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold = needGold * DefaultCfg.DZ_CONSUMPTION_RATIO;
			if( user.changeAward(AwardType.GOLD, -needGold, "祈福 购买十次 消耗水晶", DWType.PRAYERS_FOR_TEN_TIMES) == -1 )
				return ErrorCode.USER_GOLD_NOT_ENOUTH;
			DataLogDataProvider.getInstance().add( user, ConsumelogF.TENEVEN_SMOKE, needGold );
			return ErrorCode.SUCCESS;
		}

	},
	
	/** 友情抽奖一次 */
	FD_ONCE(3) {
		
		// 需要友情点
		private final int needGold 			= 100;
		
		@Override
		public ErrorCode checkGold( UserInfo user ) {
			if( user.changeAward( AwardType.FD_VALUE, -needGold, "友情抽奖 抽奖一次 消耗友情点", DWType.MISCELLANEOUS ) == -1 )
				return ErrorCode.USER_FRIENDSHIP_NOT_ENOUTH;
			return ErrorCode.SUCCESS;
		}

	},
	
	/** 友情抽奖十次 */
	FD_TENTIMES(4) {
		
		// 需要友情点
		private final int needGold 			= 1000;
		
		@Override
		public ErrorCode checkGold( UserInfo user ) {
			if( user.changeAward( AwardType.FD_VALUE, -needGold, "友情抽奖 抽奖十次 消耗水晶", DWType.MISCELLANEOUS ) == -1 )
				return ErrorCode.USER_FRIENDSHIP_NOT_ENOUTH;
			return ErrorCode.SUCCESS;
		}
	},
	
	/** 免费一次 */
	GRATIS_GRATIS(5) {
		
		@Override
		public ErrorCode checkGold( UserInfo user ) {
			return ErrorCode.SUCCESS;
		}
	};
	
	private final byte number;
	
	SweepstakeType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, SweepstakeType> numToEnum = new HashMap<Byte, SweepstakeType>();
	static{
		for( SweepstakeType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static SweepstakeType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	/**
	 * 检查是否足够
	 * @param user
	 * @return
	 */
	public abstract ErrorCode checkGold( UserInfo user );
}
