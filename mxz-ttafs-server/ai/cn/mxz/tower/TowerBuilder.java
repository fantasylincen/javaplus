//package cn.mxz.tower;
//
//import cn.mxz.protocols.user.tower.TowerP.TowerPro;
//import cn.mxz.user.City;
//
//class TowerBuilder {
//
//	public TowerPro build(City city) {
//
//
//		TowerPro.Builder b = TowerPro.newBuilder();
//
//		TowerBug bug = city.getTowerMission().getBug();
//
//		b.setBug(new TowerBugBuilder().build(bug, city.getId()));
//
//		return b.build();
//	}
//}
