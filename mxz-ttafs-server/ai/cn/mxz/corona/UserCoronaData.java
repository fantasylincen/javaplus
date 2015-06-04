//package cn.mxz.corona;
//
//import java.util.List;
//
//import message.S;
//import cn.javaplus.common.util.time.colddown.ColdDown;
//import cn.mxz.DogzTempletConfig;
//import cn.mxz.base.exception.IllegalOperationException;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.bossbattle.Prize;
//import cn.mxz.mission.old.PrizeImpl;
//import cn.mxz.user.City;
//import cn.mxz.util.cd.CDKey;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//import cn.mxz.util.counter.UserCounterSetter;
//import define.D;
//
//
///**
// * 保存玩家的转盘资料
// * @author Administrator
// *
// */
//
//class UserCoronaData {
//
//
//	/**
//	 * 时间戳
//	 */
//	private int timeStamp = Math.abs( (int)System.currentTimeMillis() );
//
//	/**
//	 * 每日免费次数
//	 */
//	private final int FREE_TIMES_PER_DAY = D.CORONA_FREE_NUMBER;
//
//	/**
//	 * 单倍抽奖所需元宝
//	 */
//	private final int NORMAL_NEED_GOLD = D.CORONA_HAPK_PRICE;
//
//	/**
//	 * 双倍抽奖所需元宝
//	 */
//	private final int DOUBLE_NEED_GOLD = D.CORONA_DOUBLE_PRICE;
//
//	/**
//	 * 刷新转盘所需元宝
//	 */
//	private final int REFRESH_CORONA_NEED_GOLD = D.CORONA_FLUSH_PRICE_ADD;
//
//
//
//	private final City	user;
//	private final ColdDown cd; //600;
//	private final UserCounter uc;
//
//	UserCoronaData( Corona publicCorona, City user ) {
//		this.corona = publicCorona;
//		this.user = user;
//		cd = user.getCDManager().get(CDKey.CORONA);
//		uc = user.getUserCounter();
//	}
//
//	/**
//	 * 刷新转盘次数
//	 */
//	public int getRefreshTimes() {
//
//		return uc.get(CounterKey.CORONA_REFRESH_TIMES);
//	}
//
//
//
//	/**
//	 * 今日免费次数
//	 */
//	public int getFreeRunTimes() {
//		return uc.get( CounterKey.CORONA_FREE_RUN_TIMES );
//	}
//
//
//	/**
//	 * 转盘
//	 */
//	private Corona corona;
//
//
//
//
//	/**
//	 * 刷新转盘
//	 */
//	Corona refreshCorona(){
//		uc.add( CounterKey.CORONA_REFRESH_TIMES, 1 );
//		int needGold = REFRESH_CORONA_NEED_GOLD * getRefreshTimes();
//		user.getPlayer().reduceGold( needGold );
//		corona = new Corona();
//
////		timeStamp = (int)System.currentTimeMillis();
//		return corona;
//	}
//
//	/**
//	 * 转动转盘
//	 * @param type
//	 * 					1:免费
//	 * 					2:单倍
//	 * 					3:双倍
//	 *
//	 * @param ts		时间戳
//	 * @return
//	 */
//	public Prize run( int type, int ts ){
//		if( ts != timeStamp ){
//			throw new OperationFaildException(S.S10191);
//		}
//		if( type == 1 ){
//			if( cd.isFreezing() ){
//				throw new IllegalOperationException( "冷却时间不足" );
//			}
//			if( getFreeRunTimes() >= FREE_TIMES_PER_DAY ){
//				throw new IllegalOperationException( "免费抽奖次数已用完" );
//			}
//
//			cd.add();
//			uc.add(  CounterKey.CORONA_FREE_RUN_TIMES, 1 );
//
//		}
//		int needGold = 0;
//
//		switch( type ){
//			case 2: needGold = NORMAL_NEED_GOLD;break;
//			case 3: needGold = DOUBLE_NEED_GOLD;break;
//			default:
//				needGold = 0;break;
//		}
//
//		//System.out.println( "needGold: " + needGold );
//		user.getPlayer().reduceGold( needGold );
//
//
//		Prize temp = corona.run();
//		Prize p;
//
//		if( type == 3 && DogzTempletConfig.get( temp.getId() ) == null){//双倍抽奖，奖品翻翻
//			p = new PrizeImpl( temp.getId(), temp.getCount() * 2 );
//		}
//		else{
//			p = temp;
//		}
//
//		p.award( user.getPlayer() );
//
//		UserCounterSetter c = user.getUserCounterHistory();
//		c.add(CounterKey. CORONA_RUN_TIMES , 1);
//		 c = user.getUserCounter();
//		c.add(CounterKey. CORONA_RUN_TIMES , 1);
//
//
//		return p;
//	}
//
//	public static void main(String[] args) {
//
//		Corona publicCorona = new Corona();
//
//
//		UserCoronaData ucd = new UserCoronaData(publicCorona , null );
//	}
//
//	/**
//	 * 剩余的秒数
//	 * @return
//	 */
//	public int getCd() {
//		return cd.getRemainingSec();
//	}
//
//	/**
//	 * 返回时间戳
//	 * @return
//	 */
//	public int getTimeStamp() {
//		return timeStamp;
//	}
//
//	/**
//	 * 返回转盘的格子数据
//	 * @return
//	 */
//	public List<Prize> getCoronaData() {
//		return corona.getCoronaData();
//	}
//
//
//}
