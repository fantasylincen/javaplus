package cn.mxz.city;

import java.util.List;

import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.bossbattle.Prize;

public class PrizeSender2 implements UserPrizeSender {

	private City	city;

	public PrizeSender2(City city) {
		this.city = city;
	}

	@Override
	public void send(String prize) {
		PrizeSenderFactory.getPrizeSender2().send(city.getPlayer(), prize);
	}

	@Override
	public List<Prize> buildPrizes(String prize) {
		return PrizeSenderFactory.getPrizeSender2().buildPrizes(city.getPlayer(), prize);
	}
}
