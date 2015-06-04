package cn.mxz.czfk2;

import cn.javaplus.log.Debuger;

public class FeedBack2ItemImpl implements FeedBack2Item {

	private FeedBack2 feedBack;

	public FeedBack2ItemImpl(FeedBack2 feedBack) {
		this.feedBack = feedBack;
	}

	@Override
	public int getId() {
		return feedBack.getId();
	}

	@Override
	public boolean getCanReceive() {
		boolean canReceive = feedBack.canReceive();
		Debuger.debug("是否可领取：" + canReceive);
		return canReceive;
	}

	@Override
	public boolean getHasReceiveAll() {
		boolean hasReceiveAll = feedBack.hasReceiveAll();
//		Debuger.debug("是否已领取：" + hasReceiveAll);
		return hasReceiveAll;
	}

	@Override
	public int getReceiveTimes() {
		return feedBack.getReceiveTimes();
	}

	@Override
	public int getTimes() {
		int times = feedBack.getTimes();
		System.out.println(times);
		return times;
	}

	@Override
	public int getMoney() {
		return feedBack.getMoney();
	}

	@Override
	public String getAward() {
		return feedBack.getReward();
	}

	@Override
	public int getBagId() {
		return feedBack.getBagId();
	}

	@Override
	public String getName() {
		return feedBack.getName();
	}

	@Override
	public int getRechargeNeed() {
		return feedBack.getRechargeNeed();
	}

	@Override
	public boolean getHasReceive() {
		return getHasReceiveAll();
	}

	@Override
	public int getRemainTimes() {
		if(getTimes() != -1) {
			return getTimes();
		}
		return feedBack.getRemainTimes2();
	}

}
