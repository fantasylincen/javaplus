//package cn.mxz.mission.old.builder;
//
//import java.util.List;
//
//import cn.mxz.mission.old.MapRandomEvent;
//import cn.mxz.mission.old.MapRandomEventImpl;
//import cn.mxz.protocols.user.mission.MissionP.RandomPro;
//import cn.mxz.protocols.user.mission.RandomMessageP.RandomMessagePro;
//
//import com.google.common.collect.Lists;
//
//public class RandomMessageBuilder {
//
//	public RandomMessagePro build(String str) {
//
//		RandomMessagePro.Builder b = RandomMessagePro.newBuilder();
//
//		b.setMessage(str);
//
//	    return b.build();
//	}
//
//	public List<? extends RandomPro> buildList(List<MapRandomEventImpl> evnets) {
//
//		List<RandomPro> all = Lists.newArrayList();
//
//	    RandomPro.Builder b = RandomPro.newBuilder();
//
//		for (MapRandomEvent e : evnets) {
//
//			b.setId(e.getId());
//
//			b.setIndex(e.getLocation().getIndex());
//
//			b.setPath(e.getLocation().getPath());
//
//			b.setPlot(e.getPlot());
//
//			b.setStoryIdEnd(e.getEndStory());
//
//			b.setStoryIdStart(e.getStartStory());
//
////			Debuger.debug("RandomMessageBuilder.buildList() 随机事件:" + e.getLocation());
//
//			all.add(b.build());
//		}
//
//		return all;
//	}
//}
