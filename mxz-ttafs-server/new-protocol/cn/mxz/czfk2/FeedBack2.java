package cn.mxz.czfk2;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FeedBack2Dto;
import mongo.gen.MongoGen.ReceivedBox2Dto;
import cn.mxz.FeedBackTwoTemplet;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;

public class FeedBack2 {

	private FeedBackTwoTemplet temp;
	private City user;
	private FeedBack2Dto dto;

	public FeedBack2(FeedBackTwoTemplet temp, City city, FeedBack2Dto dto) {
		this.temp = temp;
		this.user = city;
		this.dto = dto;
	}

	public int getId() {
		return temp.getId();
	}

	public boolean canReceive() {
		int remainSec = user.getFeedBackManager2().getRemainSec();

		if (remainSec <= 0) {
			return false;
		}

		if (hasReceiveAll()) {
			return false;
		}

		int x = getReceiveTimes() + 1;
		int need = getRechargeNeed() * x;

		FeedBackManager2 fm = user.getFeedBackManager2();
		int all = fm.getRechargeAll();
		return all >= need;
	}

	public int getRechargeNeed() {
		return temp.getMoney();
	}

	public boolean hasReceiveAll() {
		if (getTimes() == -1) {
			return false;
		}
		return getRemainTimes() <= 0;
	}

	private int getRemainTimes() {
		return getTimes() - getReceiveTimes();
	}

	public int getReceiveTimes() {
		return getBoxDto().getReceiveTimes();
	}

	public int getTimes() {
		return temp.getLimit();
	}

	public int getMoney() {
		return temp.getMoney();
	}

	public String getReward() {
		return temp.getAward();
	}

	public void receive() {
		check();
		user.getPrizeSender1().send(temp.getAward());
		markReceive();
	}

	private void markReceive() {
		ReceivedBox2Dto dto = getBoxDto();
		dto.setReceiveTimes(dto.getReceiveTimes() + 1);
		Daos.getFeedBack2Dao().save(this.dto);
	}

	private ReceivedBox2Dto getBoxDto() {

		List<ReceivedBox2Dto> receivedIds = dto.getReceivedIds();
		for (ReceivedBox2Dto dto : receivedIds) {
			int id = dto.getBoxId();
			if (id == temp.getId()) {
				return dto;
			}
		}
		ReceivedBox2Dto d = new ReceivedBox2Dto();
		d.setBoxId(temp.getId());
		receivedIds.add(d);
		return d;
	}

	private void check() {
		if (!canReceive()) {
			throw new OperationFaildException(S.S10326);
		}
	}

	public String getName() {
		return temp.getBagName();
	}

	public int getRemainTimes2() {

		int lastReceive = getLastReceive();// 最后一次领取的元宝值
		int all = user.getFeedBackManager2().getRechargeAll();
		int a = (all - lastReceive) / getRechargeNeed();
		int times = getTimes();

		if (times == -1) {
			return a;
		}

		return Math.min(a, times);
	}

	/**
	 * 最后一次领取的元宝值
	 * @return
	 */
	private int getLastReceive() {
		return getReceiveTimes() * getRechargeNeed();
	}

	public int getBagId() {
		return temp.getBagid();
	}
}
