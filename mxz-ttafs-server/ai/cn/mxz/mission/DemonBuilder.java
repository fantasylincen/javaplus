package cn.mxz.mission;

import cn.mxz.protocols.user.mission.MissionP.DemonPro;

public class DemonBuilder {

	public DemonPro build(MapDemon d) {
		DemonPro.Builder b = DemonPro.newBuilder();
		b.setId(d.getId());
		b.setIndex(d.getIndex());
		b.setPath(d.getPath());
		return b.build();
	}

}
