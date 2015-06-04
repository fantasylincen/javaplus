package cn.mxz.mission.type;

import java.util.HashMap;
import java.util.Map;

import message.S;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.exception.OperationFaildException;


public enum EventType {


	/**
	 * 空点
	 */
	EMPTY(1) {
		@Override
		public
		IEvent create( int missionId ) {
			IEvent event = new EmptyEvent();
			return event;
		}

		@Override
		public IEvent create(String arg, int missionId) {
			return create( 0 );
		}

	},
	/**
	 * 战斗事件
	 */

	BATTLE(2) {
		@Override
		public
		IEvent create( int missionId ) {
			MapTemplet templet = MissionMapTempletConfig.get( missionId );
			IEvent event = new BattleEvent( templet );
			return event;
		}

		@Override
		public IEvent create(String arg, int missionId) {

			return new BattleEvent( arg, missionId );
		}
	},
	/**
	 * 宝箱，铜钱
	 */
	PRIZE(3) {
		@Override
		public
		IEvent create( int missionId ) {
			IEvent event = new PrizeEvent(missionId );
			return event;
		}

		@Override
		public IEvent create(String arg, int missionId) {
			return new PrizeEvent( arg );
		}
	},

	/**
	 * 随机事件
	 */
	RANDOM(4) {
		@Override
		public
		IEvent create( int randomId ) {
			throw new OperationFaildException(S.S10176);
		}

		@Override
		public IEvent create(String arg, int missionId) {
			return new RandomEvent( arg );
		}


	};
	private final int number;

	EventType( int i ){
		this.number = i;
	}
	public abstract IEvent create( int missionId );
	public abstract IEvent create( String arg, int missionId );
	public int toNum() {
		return number;
	}

	private static final Map<Integer, EventType> numToEnum = new HashMap<Integer, EventType>();
    static{
        for( EventType t : values() ){

        	EventType s = numToEnum.put( t.number, t );
            if( s != null ){
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }
    public static EventType fromNum( int n ){
        return numToEnum.get( n );
    }

}
