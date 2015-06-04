package cn.mxz.prop;

import java.util.List;

import cn.mxz.base.prize.AwardAble;
import cn.mxz.bossbattle.Prize;
import cn.mxz.mission.old.PrizeImpl.PrizeBuilder;
import cn.mxz.protocols.user.mission.BoxP.BoxPro;
import cn.mxz.user.Player;

public class BoxBuilder {

	public BoxPro build(List<? extends AwardAble> prize, Player player, int propType) {

		BoxPro.Builder b = BoxPro.newBuilder();

		b.setPropType(propType);

		for (AwardAble awardAble : prize) {

			if (awardAble instanceof Prize) {

				Prize p = (Prize) awardAble;

				b.addPrizes(new PrizeBuilder().build(player, p));
			}
		}

		return b.build();
	}



}
