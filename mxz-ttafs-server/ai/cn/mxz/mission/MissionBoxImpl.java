package cn.mxz.mission;

import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class MissionBoxImpl implements MissionBox {

	private String award;
	private Completeness completeness;
	private City user;
	private int chapterId;
	private int percentNeed;

	public MissionBoxImpl(City user, String award, Completeness completeness,
			int chapterId, int percentNeed) {
		this.user = user;
		this.award = award;
		this.completeness = completeness;
		this.chapterId = chapterId;
		this.percentNeed = percentNeed;
	}

	@Override
	public boolean getHasReceived() {
		UserCounter his = user.getUserCounterHistory();
		return his.isMark(CounterKey.STAR_REWARD_RECEIVE, chapterId,
				percentNeed);
	}

	@Override
	public boolean getCanReceived() {
		if (getHasReceived()) {
			return false;
		}

		if (!complet()) {
			return false;
		}
		return true;
	}

	/**
	 * 是否完成了
	 *
	 * @return
	 */
	private boolean complet() {
		float n = completeness.getN();
		float d = completeness.getD();

		float percent = n / d;

		return (int) (percent * 100) >= percentNeed;
	}

	@Override
	public void receive() {
		if (getCanReceived()) {
			sendReward();
			markReceive();
		}
	}

	private void sendReward() {
		PrizeSenderFactory.getPrizeSender().send(user.getPlayer(), award);
	}

	private void markReceive() {
		UserCounter his = user.getUserCounterHistory();
		his.mark(CounterKey.STAR_REWARD_RECEIVE, chapterId, percentNeed);
	}
}
