package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.mxz.MissionMapTemplet;
//import cn.mxz.MissionMapTempletConfig;
//import cn.mxz.protocols.user.mission.MissionP.MissionStarPro;
//import cn.mxz.user.City;
//
//public class MissionStarBuilder {
//
//	public MissionStarPro build(City city, int captureId) {
//
//		MissionStarPro.Builder b = MissionStarPro.newBuilder();
//		List<MissionMapTemplet> all = MissionMapTempletConfig.findByChapterId(captureId);
//		for (MissionMapTemplet map : all) {
//			int id = map.getId();
//			b.addStarInfo(new StarInfoBuilder().build(city, id));
//		}
//		return b.build();
//	}
//
//}
