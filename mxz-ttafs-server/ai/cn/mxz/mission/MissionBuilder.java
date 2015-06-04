package cn.mxz.mission;

import cn.mxz.protocols.user.mission.MissionP.MissionPro;

public class MissionBuilder {

	public MissionPro build(IMissionManager m) {
		MissionPro.Builder b = MissionPro.newBuilder();
		b.setMissionId(m.getCurMissionId());
		//b.setMap(new MissionMapBuilder().build(m));
		return b.build();
	}

}
