//package cn.mxz.mission.old.builder;
//
//import java.util.List;
//
//import cn.mxz.mission.old.MapDemon;
//import cn.mxz.mission.old.demon.MapDemonImpl;
//import cn.mxz.protocols.user.mission.MissionP.DemonPro;
//
//import com.google.common.collect.Lists;
//
//public class DemonBuilder {
//
//	public Iterable<? extends DemonPro> build(List<MapDemonImpl> demons) {
//
//		List<DemonPro> all = Lists.newArrayList();
//
//		for (MapDemon d : demons) {
//
//			if(d == null) {
//
//				new Exception().printStackTrace();
//
//				continue;
//			}
//
//			all.add(build(d));
//
//		}
//
//		return all;
//	}
//
//	private DemonPro build(MapDemon d) {
//
//		DemonPro.Builder b = DemonPro.newBuilder();
//
//		b.setId(d.getId());
//
//		b.setPath(d.getLocation().getPath());
//
//		b.setIndex(d.getLocation().getIndex());
//
//		b.setIsBoss(d.isBoss());
//
//		b.setIsFirstDemon(d.isFirstDemon());
//
//		b.setIsLastDemon(d.isLastDemon());
//
//		b.setStoryIdStart(d.getFirstStoryId());
//
//		b.setStoryIdEnd(d.getSecondStoryId());
//
////		Debuger.debug("DemonBuilder.build() 小怪:" + d.getLocation() );
//
//		return b.build();
//
//	}
//
//}