package cn.mxz.invite;

import java.util.List;

import message.S;
import cn.mxz.FriendsFeedbackTemplet;
import cn.mxz.FriendsFeedbackTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import db.domain.InviteUsers;

/**
 * 邀请有礼奖励
 * @author 林岑
 *
 */
public class GiftImpl implements Gift {

	private int	number;
	private List<InviteUsers>	all;
	private City	city;

	public GiftImpl(City city, int number, List<InviteUsers> all) {
		this.city = city;
		this.number = number;
		this.all = all;
	}

	@Override
	public boolean hasReceive() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.INVITE_GIFT, number);
	}

	@Override
	public int getCount() {
		return number;
	}

	@Override
	public void receive() {
		checkCount();
		checkReceive();
		sendReward();
		markReceive();
	}

	private void checkCount() {
		if(all.size() < number) {
			throw new OperationFaildException(S.S10229);
		}
	}

	private void checkReceive() {
		if(hasReceive()) {
			throw new OperationFaildException(S.S10230);
		}
	}

	private void sendReward() {
		FriendsFeedbackTemplet temp = FriendsFeedbackTempletConfig.findByNumber(number).get(0);
		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		s.send(city.getPlayer(), temp.getAward());
	}

	private void markReceive() {
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.INVITE_GIFT, number);
	}

}
