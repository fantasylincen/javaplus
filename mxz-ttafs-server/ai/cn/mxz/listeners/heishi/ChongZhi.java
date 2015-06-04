package cn.mxz.listeners.heishi;

import java.util.Date;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import define.D;

/**
 * 当玩家元宝发生变化时 执行
 * 
 * @author 林岑
 * 
 */
public class ChongZhi {
	private static int	needGold	= Integer.parseInt(D.HEI_SHI_SHUI_JING_JIANG_LI_3.split(":")[0]);	// 得奖门槛

	/**
	 * 玩家金币发生变化时
	 * 
	 * @param user
	 * @param oldValue
	 *            消耗钱金币
	 */
	private void onGoldChange(City user, int rechargeGold) {

		int remainGold = user.getUserCounter().get(CounterKey.HEISHI_RECHARGE_REMAIN_GOLD);// 上次充值元宝后，黑市活动兑换奖励之后，剩余的元宝数

		user.getUserCounter().add(CounterKey.HEISHI_RECHARGE_REMAIN_GOLD, rechargeGold);// 更新每日充值记录
		int allRemainGold = rechargeGold + remainGold;// 今日充值的总的元宝数

		if (allRemainGold >= needGold && !user.getUserCounter().isMark(CounterKey.HEISHI_RECHARGE_GOT_PRIZE)) {// 兑换门槛

			String str = D.HEI_SHI_SHUI_JING_JIANG_LI_3.split(":")[1];

			int templet_propId = Integer.parseInt(str.split(",")[0]);
			int templet_count = Integer.parseInt(str.split(",")[1]);

			// int count = allRemainGold / needGold;//达到兑换条件多少次
			user.getPrizeCenter().addPrize(5, templet_propId + "," + templet_count, S.STR60297, S.STR60296);

			// remainGold = allRemainGold - needGold;//扣除已经兑换的元宝
			user.getUserCounter().mark(CounterKey.HEISHI_RECHARGE_GOT_PRIZE);

		}

	}

	public static void main(String[] args) {
		System.out.println(new Date(1418053719 * 1000l));
		System.out.println(new Date(1418090851 * 1000l));
	}

	public void onXXX(City user, int count) {

		if (D.LANGUAGE != 2) {// 仅限于台湾版本
			return;
		}
		ActivityTemplet temp = ActivityTempletConfig.get(ActivityIds.XianShiHeiShi_14);

		boolean in = Util.Time.isIn(temp.getTime());
		if (!in) {// 不在活动中
			return;
		}
		onGoldChange(user, count);
	}

}
