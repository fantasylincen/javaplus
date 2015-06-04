package cn.mxz.monthcard;

import message.S;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.listeners.mouthcard.SendMouthCardRewardToAllUser;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.linekong.platform.protocol.erating.server.MonthRechargeKeyBuilder;

import define.D;

public class MonthCard {
	/**
	 * 购买月卡扣除的元宝数
	 */
	private static final int	MONTH_CARD_NEED_GOLD	= 300;
	/**
	 * 当月充值必须超过这个值，才能购买月卡
	 */
	private final City user;
	/**
	 * 月卡结束日期，这天之后意味着正式结束
	 */
	private LocalDate end;

	public MonthCard(City user) {
		this.user = user;
		int endSecond = user.getUserCounterHistory().get(
				CounterKey.MONTH_CARD_END_SECOND);

		// user.getUserCounterHistory().set(CounterKey.MONTH_CARD_END_SECOND,
		// 0);
		end = new LocalDate(endSecond * 1000l);
		if (end.isBefore(new LocalDate())) {
			end = new LocalDate(0);
		}
	}
	
	/**
	 * 本月是否已经购买
	 * @return
	 */
	public boolean isBuy(){
		int currentMonthKey = new MonthRechargeKeyBuilder().getCurrentMonthKeyNum();
		return (user.getUserCounterHistory().get(CounterKey.MONTH_CARD_BUY) == currentMonthKey);
	}

	/**
	 * 设置月卡
	 */
	public void add() {
		// if (new LocalDate().plusDays(7).isAfter(end)) {
		// throw new IllegalOperationException("月卡未到期，不能提前购买");
		// }

		if (getCurrentMonthRecharge() < D.VIP_CARD_BUY_PRICE ) {
			throw new OperationFaildException(S.S10321);
		}
		 
		if( isBuy() ){//当月已经购买月卡
			throw new OperationFaildException(S.S10322);
		}
		
		user.getPlayer().reduceGold( D.MONTH_CARD_NEED_GOLD );

		boolean isNew = false;
		if (end.isBefore(new LocalDate())) {
			end = new LocalDate();// 当前时间
			isNew = true;
		}
		end = end.plusDays(30);

		if (isNew) {// 非月卡用户第一次购买月卡，则立即发送今日奖励
			SendMouthCardRewardToAllUser.sendPrize(user);// 立即送一个奖励
			end = end.plusDays(-1);
		}

		int second = (int) (end.toDateMidnight().getMillis() / 1000);
		user.getUserCounterHistory().set(CounterKey.MONTH_CARD_END_SECOND,
				second);
		
		int currentMonthKey = new MonthRechargeKeyBuilder().getCurrentMonthKeyNum();
		user.getUserCounterHistory().set(CounterKey.MONTH_CARD_BUY, currentMonthKey);//设置最后一次购买月卡的月份时间
		
	}

	/**
	 * 月卡是否有效
	 * 
	 * @return
	 */
	public boolean isValid() {

		return end.isAfter(new LocalDate());
		// return new LocalDate().isAfter(end);
	}

	public static void main(String[] args) {
		DateTime now = new DateTime(2012, 6, 18, 0, 1, 40);
		System.out.println(now.getMillis());
		System.out.println(now);
		DateTime now1 = new DateTime(1403107200000l);
		System.out.println(now1);
		LocalDate nowDate = new LocalDate(now);
		System.out.println(nowDate);
		System.out.println(new LocalDate().plusDays(0).isAfter(nowDate));
		System.out.println(nowDate.toDateMidnight().getMillis());

		// System.out.println( 24*);

		LocalDate end = new LocalDate(0);
		System.out.println(end);
	}

	/**
	 * 本月累计充值数
	 * @return
	 */
	public int getCurrentMonthRecharge() {
		UserCounter his = user.getUserCounterHistory();
		String key = new MonthRechargeKeyBuilder().getCurrentMonthKey();
//		return 5000;
		return his.get(CounterKey.MONTH_RECHARGE, key);
	}
	
	/**
	 * 获取vip月卡剩余秒数
	 * @return
	 */
	public int getRemainSecond(){
		int second = user.getUserCounterHistory().get(
				CounterKey.MONTH_CARD_END_SECOND);
		second -= (System.currentTimeMillis() / 1000);
		return Math.max( 0, second);
	}
}
