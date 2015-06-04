package cn.mxz.mission.old.builder;
//package cn.mxz.mission.builder;
//
//import java.util.List;
//
//import cn.mxz.mission.Location;
//import cn.mxz.mission.MissionMap;
//import cn.mxz.mission.NormalMission;
//import cn.mxz.mission.Person;
//import cn.mxz.mission.demon.MapDemonImpl;
//import cn.mxz.protocols.user.mission.MissionP.MissionMapPro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPro;
//import cn.mxz.user.mission.Mission;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.util.debuger.Debuger;
//
//public class MissionBuilder {
//
//	public MissionPro build(Mission mission) {
//
//		MissionPro.Builder b = MissionPro.newBuilder();
//
//		MissionMark mark = mission.getMark();
//
//		int mid = mark.getMapId();
//
//		Debuger.debug("MissionBuilder.build() 可打最大地图:" + mark);
//
//		b.setStoryId(mid);
//
//		b.setMissionId(mark.getMissionId());
//
//		b.setCopyId(mark.getCopyId());
//
//		if(mission.hasMap()) {
//
//			b.setMap(buildMissionMap(mission));
//		}
//
//		return b.build();
//	}
//
//	/**
//	 * 构建副本地图信息
//	 * @param mission
//	 * @return
//	 */
//	private MissionMapPro buildMissionMap(Mission mission) {
//
//		MissionMap map = mission.getCurrentMap();
//
//		MissionMapPro.Builder b = MissionMapPro.newBuilder();
//
//		b.setCompleteness(((int)mission.getCurrentMap().getCompleteness() * 100));
//
//		b.setMapId(map.getId());
//
//		MissionMark mark = map.getMark();
//
//		b.setCopyId(mark.getCopyId());
//
//		b.setMissionId(mark.getMissionId());
//
//		Person person = mission.getCurrentMap().getPerson();
//
//		Location l = person.getLocation();
//
//		b.setPersonPath(l.getPath());
//
//		b.setPersonIndex(l.getIndex());
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 动态小怪:");
//
//
//		List<MapDemonImpl> dynamicDemons = map.getDynamicDemons();
//
//		b.addAllDemons(new DemonBuilder().build(dynamicDemons));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 静态小怪:");
//
//		b.addAllStaticDemons(new DemonBuilder().build(map.getStaticDemons()));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 动态宝箱:");
//
//		b.addAllBoxes(new BoxDataBuilder().build(map.getDynamicBoxes()));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 静态宝箱:");
//
//		b.addAllStaticBoxes(new BoxDataBuilder().build(map.getStaticBoxes()));
//
//
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 动态随机事件:");
//
//		b.addAllRandoms(new RandomMessageBuilder().buildList(map.getDynamicEvents()));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 静态随机事件:");
//
//		b.addAllStaticRandoms(new RandomMessageBuilder().buildList(map.getStaticEvents()));
//
//
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 动态送神将:");
//
//		b.addAllGod(new DemonBuilder().build(map.getDynamicGod()));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 静态送神将:");
//
//		b.addAllStaticGod(new DemonBuilder().build(map.getStaticGod()));
//
//
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 动态钱袋:");
//
//		b.addAllMoneyBox(new MoneyBoxBuilder().build(map.getDynamicMapMoney()));
//
////		Debuger.debug("MissionBuilder.buildMissionMap() 静态钱袋:");
//
//		b.addAllStaticMoneyBox(new MoneyBoxBuilder().build(map.getStaticMapMoney()));
//
//		
//		if(mission instanceof NormalMission) {
//
//			b.addAllStaticStory(new StoryBuilder().build(map.getStaticStory()));
//		}
//
//
//
//
//		Debuger.debug("MissionBuilder.buildMissionMap() 当前所在地图:" + mark);
//
//		Debuger.debug("MissionBuilder.buildMissionMap() 人物位置:" + l);
//
//		return b.build();
//	}
//
//}
