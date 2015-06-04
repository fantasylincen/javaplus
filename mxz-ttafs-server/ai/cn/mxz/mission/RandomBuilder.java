package cn.mxz.mission;

import cn.mxz.mission.old.MapRandomEvent;
import cn.mxz.protocols.user.mission.MissionP.RandomPro;

public class RandomBuilder {

	public RandomPro build(MapRandomEvent me) {
		RandomPro.Builder b = RandomPro.newBuilder();
		b.setId(me.getId());
		b.setIndex(me.getIndex());
		b.setPath(me.getPath());
		return b.build();
	}

}
