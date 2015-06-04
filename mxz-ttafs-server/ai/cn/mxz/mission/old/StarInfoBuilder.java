package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import cn.mxz.protocols.user.mission.MissionP.StarPro;
//import cn.mxz.user.City;
//import cn.mxz.util.debuger.Debuger;
//
//public class StarInfoBuilder {
//
//	public StarPro build(City city, int id) {
//		StarPro.Builder b = StarPro.newBuilder();
//		b.setMapId(id);
//		MissionStarManager manager = MissionStarFactory.getMissionStarManager(city.getId());
//		int star = manager.getStar(id);
//		int starMax = manager.getStarMax(id);
//
//		Debuger.debug("StarInfoBuilder.build() star:" + star + "/" + starMax + ", id = " + id);
//
//		b.setStar(star);
//		b.setStarMax(starMax);
//		return b.build();
//	}
//}
