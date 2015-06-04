package cn.mxz.chengzhang;

import java.util.ArrayList;
import java.util.List;

import message.S;
import cn.mxz.PmtpTemplet;
import cn.mxz.PmtpTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

import define.D;

public class ChengZhangPlayer {

	private City city;

	public ChengZhangPlayer(City city) {
		this.city = city;
	}
	
	public City getCity() {
		return city;
	}

	public void buy() {
		checkVipLevel();
		if(!hasBought()) {
			city.getPlayer().reduceGold(getNeed());
			getCounter().mark(CounterKey.HAS_BOUGHT_CHENG_ZHANG);
		}
	}

	int getNeed() {
		return D.CHENG_ZHANG_JI_HUA_JIA_GE;
	}

	private UserCounter getCounter() {
		return city.getUserCounterHistory();
	}

	public boolean hasBought() {
		return getCounter().isMark(CounterKey.HAS_BOUGHT_CHENG_ZHANG);
	}

	private void checkVipLevel() {
//		int level = city.getVipPlayer().getLevel();
		UserCounter his = city.getUserCounterHistory();
		int count = his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT);
//		if(level < D.CHENG_ZHANG_JI_HUA) {
		if(count < D.CHENG_ZHANG_JI_HUA){
			throw new OperationFaildException(S.S60239);
		}
	}

	public void receive(int id) {
		if(!hasBought()) {
			throw new OperationFaildException(S.S60240);
		}
		List<ChengZhangBox> boxes = getBoxes();
		for (ChengZhangBox box : boxes) {
			if(box.getId() == id) {
				box.receive();
				break;
			}
		}
	}

	public List<ChengZhangBox> getBoxes() {
		List<PmtpTemplet> all = PmtpTempletConfig.getAll();
		ArrayList<ChengZhangBox> ls = Lists.newArrayList();
		for (PmtpTemplet b : all) {
			ls.add(new ChengZhangBox(b, city));
		}
		return ls;
	}

}
