package cn.mxz.chengzhang;

import cn.mxz.PmtpTemplet;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class ChengZhangBox {

	private PmtpTemplet b;
	private City city;

	public ChengZhangBox(PmtpTemplet b, City city) {
		this.b = b;
		this.city = city;
	}

	public int getId() {
		return b.getId();
	}

	public void receive() {
		checkRecieve();
		markReceive();
		sendReward();
	}

	private void sendReward() {
		int rebate = b.getRebate();
		city.getPlayer().addGiftGold(rebate);
	}

	private void markReceive() {
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.HAS_RECEIVE_CHENG_ZHANG_BOX, getId());
	}

	private void checkRecieve() {
		if (hasReceive()) {
			throw new SureIllegalOperationException("已经领取了");
		}
	}

	public boolean hasReceive() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.HAS_RECEIVE_CHENG_ZHANG_BOX, getId());
	}

	public boolean canReceive() {
		return city.getLevel() >= getLevel();
	}

	public int getGold() {
		return b.getRebate();
	}

	public int getLevel() {
		return b.getLevel();
	}
}
