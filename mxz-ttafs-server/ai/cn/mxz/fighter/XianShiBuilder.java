package cn.mxz.fighter;

import cn.mxz.base.servertask.ReduceJuHunTask;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.protocols.user.god.FighterP.XianShiPro;
import cn.mxz.protocols.user.god.FighterP.XianShiPro.Builder;
import cn.mxz.user.Player;
import cn.mxz.xianshi.Recruitor;
import cn.mxz.xianshi.RecruitorFactory;

public class XianShiBuilder {

	public XianShiPro build(City city) {
		Builder b = XianShiPro.newBuilder();
//		CDManager manager = city.getCDManager();

		Recruitor r1 = RecruitorFactory.create(1, city, true);
		Recruitor r2 = RecruitorFactory.create(2, city, true);
		Recruitor r3 = RecruitorFactory.create(3, city, true);

		b.setRemainingSec1(r1.getRemainingSec());
		b.setRemainingSec2(r2.getRemainingSec());
		b.setRemainingSec3(r3.getRemainingSec());

		int freeTimes1 = r1.getFreeTimes();
		int freeTimes2 = r2.getFreeTimes();
		int freeTimes3 = r3.getFreeTimes();

//		Debuger.debug("XianShiBuilder.build() 十里挑一免费次数:" + freeTimes1);
//		Debuger.debug("XianShiBuilder.build() 百里挑一免费次数:" + freeTimes2);
//		Debuger.debug("XianShiBuilder.build() 万里挑一免费次数:" + freeTimes3);

		b.setFreeTimes1(freeTimes1);
		b.setFreeTimes2(freeTimes2);
		b.setFreeTimes3(freeTimes3);

		b.setTimes1(r1.getTimes());
		b.setTimes2(r2.getTimes());
		b.setTimes3(r3.getTimes());

		b.setRemainingSecJuHun(ReduceJuHunTask.getInstance().getRemainingSec());

		Player player = city.getPlayer();

		b.setJuHun(player.get(PlayerProperty.JU_HUN));

		return b.build();
	}

}
