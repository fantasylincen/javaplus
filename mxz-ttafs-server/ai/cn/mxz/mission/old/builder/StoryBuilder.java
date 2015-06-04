//package cn.mxz.mission.old.builder;
//
//import java.util.List;
//
//import cn.mxz.mission.old.MapStoryImpl;
//import cn.mxz.protocols.user.mission.MissionP.StoryPro;
//
//import com.google.common.collect.Lists;
//
//class StoryBuilder {
//
//    public Iterable<? extends StoryPro> build(List<MapStoryImpl> list) {
//
//		List<StoryPro> all = Lists.newArrayList();
//
//		StoryPro.Builder b = null;
//
//		for (MapStoryImpl msl : list) {
//
//			b = StoryPro.newBuilder();
//
//			b.setIndex(msl.getLocation().getIndex());
//
//			b.setPath(msl.getLocation().getPath());
//
//			b.setStoryIdEnd(msl.getEndStory());
//
//			b.setStoryIdStart(msl.getStartStory());
//
//			all.add(b.buildTest());
//		}
//
//		return all;
//	}
//}
