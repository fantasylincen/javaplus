package cn.mxz.pvp;

import message.S;
import cn.mxz.ArenaConvertTemplet;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class PvpDHItemImpl implements PvpDHItem {

	private ArenaConvertTemplet temp;
	private City city;

	public PvpDHItemImpl(ArenaConvertTemplet temp, City city) {
		this.temp = temp;
		this.city = city;
	}

	@Override
	public int getTypeId() {
		return temp.getTypeId();
	}

	@Override
	public int getRongYuNeed() {
		return temp.getHonor();
	}

	@Override
	public int getChangeTimes() {
		return getRemainDuiHuanTimes();
	}

	@Override
	public boolean getCanDuiHuan() {
		return getRemainSec() <= 0 && getChangeTimes() > 0;
	}

	@Override
	public int getRemainSec() {
		UserCounter his = city.getUserCounterHistory();
		long millis = his.get(CounterKey.LAST_PVP_DUIHUAN_SEC, getTypeId());
		millis *= 1000;
		
		long chiXu = System.currentTimeMillis() - millis;
		chiXu /= 1000; // 持续时间转换为秒

		int coolTime = temp.getCoolTime();
		int remain = (int) (coolTime - chiXu);
		if(remain < 0) {
			remain = 0;
		}
		return remain;
	}

	@Override
	public void duiHuan() {
		checkCd();
		reduceRongYu();
		sendReward();
		markTimes();
		if(!hasRemainDuiHuanTimes()) { //如果没有剩余兑换次数
			markCd();
			resetTimes();
		}
	}

	private void reduceRongYu() {
		city.getPlayer().reduce(PlayerProperty.RONG_YU, temp.getHonor());
	}

	private boolean hasRemainDuiHuanTimes() {
		return getRemainDuiHuanTimes() > 0;
	}

	private int getRemainDuiHuanTimes() {
		int max = temp.getConvertMax();
		UserCounter his = city.getUserCounterHistory();
		
		int now = his.get(CounterKey.PVP_DUIHUAN_TIMES, getTypeId());
		int remain = max - now;
		if(remain < 0) {
			remain = 0;
		}
		return remain;
	}

	private void resetTimes() {
		city.getUserCounterHistory().set(CounterKey.PVP_DUIHUAN_TIMES, 0, getTypeId());
	}

	private void checkCd() {
		if(getRemainSec() > 0) {
			throw new OperationFaildException(S.S10086);
		}
	}

	private void markCd() {
		UserCounter his = city.getUserCounterHistory();
		long time = System.currentTimeMillis();
		time /= 1000;
		his.set(CounterKey.LAST_PVP_DUIHUAN_SEC, (int) time, getTypeId());
	}

	private void markTimes() {
		city.getUserCounterHistory().add(CounterKey.PVP_DUIHUAN_TIMES, 1, getTypeId());
	}

	private void sendReward() {
		String prize = getPrize();
		city.getPrizeSender1().send(prize);
	}

	private String getPrize() {
		return temp.getTypeId() + "," + temp.getConvertnumber();
	}


}
