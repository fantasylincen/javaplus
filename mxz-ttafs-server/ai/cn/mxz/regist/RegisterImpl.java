//package cn.mxz.regist;
//
//import java.sql.Date;
//import java.util.Calendar;
//import java.util.List;
//
//import message.S;
//import cn.javaplus.common.util.Util;
//import cn.mxz.RegistFillCheckTempletConfig;
//import cn.mxz.RegistRewardLibraryTemplet;
//import cn.mxz.RegistRewardLibraryTempletConfig;
//import cn.mxz.RegistTemplet;
//import cn.mxz.RegistTempletConfig;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.user.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
///**
// *
// * 每日签到
// *
// * @author 林岑
// * @since 2012年11月30日 15:34:18
// */
//class RegisterImpl implements Register {
//
//	private City	city;
//
//	/**
//	 * (时间:0或1逗号连接, 0表示没领取, 1表示已经领取)
//	 * 138812312311:0,1,0,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,0..
//	 */
//	private RegisterDto	registerDto;
//
//	RegisterImpl(City city, String value) {
//		this.city = city;
//		this.registerDto = new RegisterDtoImpl(city, value);
//	}
//
//	@Override
//	public void regist() {
//		int dayNow = Util.Time.getDayOfMonthNow();
//		RegistRecord r = get(dayNow);
//		if (r.hasReceived()) {
//			throw new OperationFaildException(S.S10092);
//		}
//		sendReward(r); // 发奖
//		mark(r); // 标记已经签过到了
//	}
//
//	/**
//	 * 获得指定某天的签到记录
//	 *
//	 * @param dayNow
//	 * @return
//	 */
//	private RegistRecord get(int dayNow) {
//		List<RegistRecord> records = getRecords();
//
//		for (RegistRecord r : records) {
//			if (r.getDayOfMonth() == dayNow) {
//				return r;
//			}
//		}
//		throw new IllegalArgumentException("dayNow = " + dayNow);
//	}
//
//	/**
//	 * 根据当前连续签到次数发奖
//	 *
//	 * @param r
//	 */
//	private void sendReward(RegistRecord r) {
//		// 连续签到奖励
//		Integer dTemp = getDaysContinues(r.getDayOfMonth() - 1);// 获得前一天的连续签到次数,
//																// 因为今天此时还没有签,
//																// 如果获取今日的话,
//																// 会返回0
//		Integer daysContinues = dTemp + 1;// 如果今天是 15 日, 11:√ 12:× 13:√ 14:√
//											// 15:√... 那么返回3/
//		if (isMaxDaysInMonth(daysContinues)) { // 如果是当月, 最长的连续次数
//			RegistTemplet temp = RegistTempletConfig.get(daysContinues); // 连续签到奖励
//			if (temp != null) {
//				PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//				ps.send(city.getPlayer(), temp.getAwards());
//			}
//		}
//		sendBaseReward(r);
//	}
//
//	/**
//	 * 这个连续签到次数, 是不是这个月最大的连续签到次数
//	 *
//	 * @param daysContinues
//	 * @return
//	 */
//	private boolean isMaxDaysInMonth(Integer daysContinues) {
//		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//		for (int i = 1; i <= day; i++) {
//			Integer c = getDaysContinues(i);
//			if (daysContinues < c) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * 获得今日的连续签到次数
//	 *
//	 * @param d
//	 *            当月第几天 (从1开始) 当月几号
//	 * @return
//	 */
//	private Integer getDaysContinues(int d) {
//		List<RegistRecord> records = getRecords();
//		int continues = 0;
//		for (RegistRecord ccc : records) {
//			if (ccc.hasReceived()) {
//				continues++;
//			} else {
//				continues = 0;
//			}
//			if (d == ccc.getDayOfMonth()) {
//				break;
//			}
//		}
//		return continues;
//	}
//
//	/**
//	 * 签到奖励
//	 *
//	 * @param r
//	 */
//	private void sendBaseReward(RegistRecord r) {
//		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//		RegistRewardLibraryTemplet reward = RegistRewardLibraryTempletConfig.get(r.getRewardId());// 签到奖励
//		if (reward != null) {
//			ps.send(city.getPlayer(), reward.getAwards());
//		}
//	}
//
//	/**
//	 * 标记该用户已经签过到了
//	 *
//	 * @param r
//	 */
//	private void mark(RegistRecord r) {
//		r.markSignIn();
//	}
//
//	@Override
//	public List<RegistRecord> getRecords() {
//		return registerDto.getRecords();
//	}
//
//	@Override
//	public void remedy() {
//		RegistRecord r = getFirstHasNotRegist();// 第一个没有签到的记录
//		if (r == null) {
//			throw new OperationFaildException(S.S10093);
//		}
//		reduceRemedyCash();
//		sendReward(r); // 发奖
//		markRemedy(r);
//	}
//
//	/**
//	 * 第一个忘记被签到的记录
//	 *
//	 * @return
//	 */
//	private RegistRecord getFirstHasNotRegist() {
//		for (RegistRecord r : getRecords()) {
//			if (r.isPast() && !r.hasReceived()) {
//				return r;
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 扣除补签所需金币
//	 */
//	private void reduceRemedyCash() {
//		UserCounter c = city.getUserCounterHistory();
//		int times = c.get(CounterKey.REGIST_REMEDY_TIMES) + 1;
//		int need = RegistFillCheckTempletConfig.get(times).getGoldNeed();
//		city.getPlayer().reduce(PlayerProperty.GOLD, need);
//	}
//
//	/**
//	 * 标记补签了
//	 *
//	 * @param r
//	 */
//	private void markRemedy(RegistRecord r) {
//		r.markSignIn();
//		city.getUserCounterHistory().add(CounterKey.REGIST_REMEDY_TIMES, 1);
//	}
//
//	@Override
//	public boolean isRegistToday() {
//		int dayNow = Util.Time.getDayOfMonthNow();
//		RegistRecord r = get(dayNow);
//		return r.hasReceived();
//	}
//
//	/**
//	 * 该签到是否已经过期了
//	 * @return
//	 */
//	public boolean isTimeOut() {
//
//		long time = registerDto.getCreateTime();
//
//		Calendar c = Calendar.getInstance();
//
//		//当前月份
//		int mNow = c.get(Calendar.MONTH);
//
//		c.setTime(new Date(time));
//
//		//创建时月份
//		int mCreate = c.get(Calendar.MONTH);
//
//		return mNow != mCreate;
//	}
//}
