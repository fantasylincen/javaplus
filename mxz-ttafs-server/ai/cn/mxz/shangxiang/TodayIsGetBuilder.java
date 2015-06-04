package cn.mxz.shangxiang;

import cn.javaplus.util.Util;
import cn.mxz.protocols.user.UserShangXiangP.TodayIsGetPro;

public class TodayIsGetBuilder {

	public TodayIsGetPro build(ShangXiangPlayer player) {
		TodayIsGetPro.Builder b = TodayIsGetPro.newBuilder();
		b.setCountDown(player.getRemainSec());
		boolean night = player.isNight();
		b.setNight(night);
		boolean noon = player.isNoon();
		b.setNoon(noon);
		b.setHasReceiveNoon(player.hasReceiveNoon());
		b.setHasReceiveNight(player.hasReceiveNight());
		b.setHour(Util.Time.getCurrentHour());
		return b.build();
	}

}
