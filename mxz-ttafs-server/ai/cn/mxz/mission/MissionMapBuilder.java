//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.mxz.mission.old.MapBox;
//import cn.mxz.mission.old.MapRandomEvent;
//import cn.mxz.protocols.user.mission.MissionP.MissionMapPro;
//
//public class MissionMapBuilder {
//
//
//	public MissionMapPro build(IMissionManager m) {
//		
//		MissionMapPro.Builder b = MissionMapPro.newBuilder();
//		
//		b.setMissionId(m.getMissionId());
//
//		b.setPersonIndex(m.getPerson().getIndex());
//		b.setPersonPath(m.getPerson().getPath());
//		
//		
//		List<MapDemon> demons = m.getDemons();
//		
//		for (MapDemon d : demons) {
//			b.addDemons(new DemonBuilder().build(d));
//		}
//		
//		List<MapBox> boxes = m.getBoxes();
//		
//		for (MapBox mapBox : boxes) {
//			b.addBoxes(new BoxDataBuilder().build(mapBox));
//		}
//		
//		List<MapRandomEvent> events = m.getMapRandomEvents();
//		
//		for (MapRandomEvent me : events) {
//			b.addRandoms(new RandomBuilder().build(me));
//		}
//		
//		return b.build();
//	}
//
//}
