package cn.mxz.base.prize;

import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import define.D;

public class BaiXingCao implements AwardAble {

	@Override
	public void award(Player player) {
		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		StuffTemplet t = StuffTempletConfig.get(D.ID_BAI_XING_CAO);
		s.send(player, t.getAwards());
	}

	public void award(City city) {
		award(city.getPlayer());
	}
}
