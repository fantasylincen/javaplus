package cn.mxz.onlinereward;

import message.S;
import cn.mxz.OnlineTemplet;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.messagesender.UserMessageSender;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class OnlineRewardBox {

	private OnlineTemplet temp;
	private City user;

	public OnlineRewardBox(OnlineTemplet temp, City user) {
		this.temp = temp;
		this.user = user;
	}

	public int getId() {
		return temp.getId();
	}

	public int getDuration() {
		return temp.getDuration();
	}

	public void receive() {
		check();
		sendReward();
		markReceive();

		try {
			UserPro build = new UserBuilder().build(user);
			UserMessageSender user2 = MessageFactory.getUser();
			user2.onUpdateUserList(user.getSocket(), build);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendReward() {
		user.getPrizeSender1().send(getReward());
	}

	private void markReceive() {
		UserCounter his = user.getUserCounterHistory();
		his.mark(CounterKey.ONLINE_REWARD_MARK, getId());
	}

	private void check() {
		if (!getCanReceive()) {
			throw new OperationFaildException(S.S10327);
		}
	}

	private boolean isFunctionOpen() {
		FunctionOpenManager fm = user.getFunctionOpenManager();
		return fm.isOpen(ModuleType.ZaiXianJiangLi);
	}

	public boolean getCanReceive() {
		if (!isFunctionOpen()) {
			return false;
		}
		if (getHasReceive()) {
			return false;
		}

		return getRemainSec() <= 0;
	}

	public boolean getHasReceive() {
		UserCounter his = user.getUserCounterHistory();
		return his.isMark(CounterKey.ONLINE_REWARD_MARK, getId());
	}

	public int getRemainSec() {
		int sec = getOnlineSec();

		int a = temp.getDuration() - sec;
		if (a <= 0) {
			a = 0;
		}
		return a;
	}

	private int getOnlineSec() {
		UserCounter his = user.getUserCounterHistory();
		int sec = his.get(CounterKey.ONLINE_TIME);

		long lastTime = user.getLastLoginMillis();

		if (lastTime == 0) {

			return sec;
		}

		return sec + getOnlineTime(lastTime);
	}

	/**
	 * 
	 * @param lastTime
	 * @return
	 */
	private int getOnlineTime(long lastTime) {

		long onlineTime = System.currentTimeMillis() - lastTime;

		int i = (int) (onlineTime / 1000);

		return i;
	}

	public String getReward() {
		return temp.getAwards();
	}
}
