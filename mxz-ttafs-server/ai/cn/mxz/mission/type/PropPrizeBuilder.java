package cn.mxz.mission.type;

import cn.mxz.activity.PropBuilder;
import cn.mxz.mission.old.PropPrize;
import cn.mxz.protocols.user.PropP.PropPro;
import cn.mxz.util.debuger.Debuger;

public class PropPrizeBuilder {

	public PropPro build(PropPrize pr) {

//		Debuger.debug("PropPrizeBuilder.build() id:" + pr.getId() + "," + pr.getCount());

//		Debuger.debug("PropPrizeBuilder.build()" + pr);

		return new PropBuilder().build(pr.getId(), pr.getCount());

	}

}
