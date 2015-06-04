package cn.mxz.equipment;

import cn.mxz.activity.PropBuilder;
import cn.mxz.battle.WarSituation;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.city.City;
import cn.mxz.newpvp.CardRewardBuilder;
import cn.mxz.newpvp.RandomRewardSender;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.protocols.user.equipment.EquipmentP.SnatchResultPro;
import cn.mxz.util.debuger.Debuger;
import define.D;

class SnatchResultBuilder {

	public SnatchResultBuilder() {
	}

	public SnatchResultPro build(RandomRewardSender s, boolean isSuccess, int stuffTempletId,
			WarSituation warSituation, City city) {

		SnatchResultPro.Builder b = SnatchResultPro.newBuilder();

		WarSituationPro w = new WarSituationBuilder().build(city, warSituation);

		b.setSituation(w);

//		Debuger.debug("SnatchResultBuilder.build() 构造夺宝结果:");

		if (isSuccess) {

			Debuger.debug("SnatchResultBuilder.build() 夺宝得到了奖励");
			int count = D.EQUIPMENT_SNATCH_COUNT;
			PropBuilder bd = new PropBuilder();
			b.addProps(bd.build(stuffTempletId, count));
		}

		b.setReward(new CardRewardBuilder().build(s));

		return b.build();
	}

}
