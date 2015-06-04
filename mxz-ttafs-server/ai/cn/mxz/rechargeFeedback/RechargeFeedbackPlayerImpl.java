package cn.mxz.rechargeFeedback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import cn.mxz.RechargeFeedbackTemplet;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
//import cn.mxz.RechargeFeedbackTempletConfig;

class RechargeFeedbackPlayerImpl {

	private static final int	PRIZE_COUNT	= 6;
	private final City 			user;
	private final UserCounter 	counter;
	/**
	 * 是否领取过奖励
	 */
	private boolean[] 			isGetPrize;

	public boolean[] getIsGetPrize() {
		return isGetPrize;
	}

	public void setIsGetPrize(boolean[] isGetPrize) {
		this.isGetPrize = isGetPrize;
	}

	RechargeFeedbackPlayerImpl( String id ) {
		user = CityFactory.getCity(id);
		counter = user.getUserCounterHistory();

		isGetPrize = new boolean[PRIZE_COUNT];
		int i = 0;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK1 ) == 1 ? true : false;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK2 ) == 1 ? true : false;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK3 ) == 1 ? true : false;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK4 ) == 1 ? true : false;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK5 ) == 1 ? true : false;
		isGetPrize[i++] = counter.get( CounterKey.RECHARGE_FEEDBACK6 ) == 1 ? true : false;

	}

	/**
	 * 领奖
	 * @param type
	 */
	void getPrize( int type ){
//		RechargeFeedbackTemplet t = RechargeFeedbackTempletConfig.get( type );
//		if( t == null ){
//			throw new OperationFaildException(S.S10185);
//		}
//
//		if( !check( type, t ) ){
//			throw new IllegalOperationException( "领取充值回馈失败" );
//		}
//
//		PrizeSenderFactory.getPrizeSender().send(user.getPlayer(), t.getAwards());
//
//		switch( type ){
//		case 1:
//			counter.set( CounterKey.RECHARGE_FEEDBACK1, 1 );break;
//		case 2:
//			counter.set( CounterKey.RECHARGE_FEEDBACK2, 1 );break;
//		case 3:
//			counter.set( CounterKey.RECHARGE_FEEDBACK3, 1 );break;
//		case 4:
//			counter.set( CounterKey.RECHARGE_FEEDBACK4, 1 );break;
//		case 5:
//			counter.set( CounterKey.RECHARGE_FEEDBACK5, 1 );break;
//		case 6:
//			counter.set( CounterKey.RECHARGE_FEEDBACK6, 1 );break;
//
//		}
//		isGetPrize[type-1] = true;


	}

	/**
	 * type -= 1;//序号从0开始
	 * @param type 		序号，从1开始
	 * @return
	 */
	private boolean check( int type, RechargeFeedbackTemplet t ){
		int index = type - 1;
		if( isGetPrize[index]){
			System.err.println( "奖品已经领取过了" );
			return false;
		}
		int gold = counter.get( CounterKey.TOTAL_RECHARGE_GOLD_COUNT );
		if( t.getRecharge() > gold  ){
			System.err.println( "充值元宝数不够" );
			return false;
		}
		return true;
	}

	static List<City> getRankList(){

		Map< String, City> map = WorldFactory.getWorld().getNearests();
		List<City> list = new ArrayList<City>( map.values() );

		Collections.sort( list, new Comparator<City>(){

			@Override
			public int compare(City o1, City o2) {
				UserCounterSetter c = o1.getUserCounterHistory();
				return o1.getUserCounterHistory().get( CounterKey.TOTAL_RECHARGE_GOLD_COUNT ) - o2.getUserCounterHistory().get( CounterKey.TOTAL_RECHARGE_GOLD_COUNT );
			}
		});
		return list;

	}



}
