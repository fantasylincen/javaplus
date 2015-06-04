package cn.mxz.mission;

import cn.mxz.mission.old.MapBox;
import cn.mxz.protocols.user.mission.MissionP.BoxDataPro;

public class BoxDataBuilder {

	BoxDataPro build(MapBox mapBox) {
		BoxDataPro.Builder b = BoxDataPro.newBuilder();
		b.setId(mapBox.getId());
		b.setIndex(mapBox.getIndex());
		b.setPath(mapBox.getPath());
		return b.build();
	}

}
