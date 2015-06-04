package cn.mxz.city;

import java.util.List;

import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.bossbattle.Prize;

public class PrizeSender1 implements UserPrizeSender {

	private City	city;

	public PrizeSender1(City city) {
		this.city = city;
	}

	@Override
	public void send(String prize) {
		PrizeSenderFactory.getPrizeSender().send(city.getPlayer(), prize);
	}

	@Override
	public List<Prize> buildPrizes(String prize) {
		return PrizeSenderFactory.getPrizeSender().buildPrizes(city.getPlayer(), prize);
	}

}
