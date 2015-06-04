package cn.mxz.invite;

import java.util.List;

import message.S;
import cn.mxz.FriendsFeedbackTemplet;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class InviteReward {

	private FriendsFeedbackTemplet temp;
	private City city;

	public InviteReward(City city, FriendsFeedbackTemplet temp) {
		this.city = city;
		this.temp = temp;
	}

	public void receive() {
		checkHasReceive();
		checkCanReceive();
		receiveReward();
		markReceive();
	}

	private void receiveReward() {
		city.getPrizeSender1().send(temp.getAward());
	}

	private void markReceive() {
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.INVITE_REWARD, temp.getId());
	}

	private void checkCanReceive() {
		if (!canReceive()) {
			throw new IllegalOperationException("未达领取条件");
		}
	}

	public boolean canReceive() {
		if (hasReceive()) {
			return false;
		}

//		InviteManager m = city.getInviteManager();
//		int number = m.getNumber();
//		if (number < temp.getNumber()) {
//			return false;
//		}

		List<City> users = city.getInviteManager().getInvites();
		return isLevelUpperThan(users, temp.getLevel());
//		int level = city.getLevel();
//		return level >= temp.getLevel();
	}

	private boolean isLevelUpperThan(List<City> users, int level) {
		int count = 0;
		for (City city : users) {
			if(city.getLevel() >= level) {
				count ++;
			}
		}
		return count >= temp.getNumber();
	}

	private void checkHasReceive() {
		if (hasReceive()) {
			throw new OperationFaildException(S.S10082);
		}
	}

	public boolean hasReceive() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.INVITE_REWARD, temp.getId());
	}

	public String getContent() {
		return temp.getAward();
	}

	public int getId() {
		return temp.getId();
	}

	public String getDescription() {
		return temp.getDescription();
	}

}
