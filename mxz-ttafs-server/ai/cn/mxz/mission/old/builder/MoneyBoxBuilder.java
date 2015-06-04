//package cn.mxz.mission.old.builder;
//
//import java.util.List;
//
//import cn.mxz.mission.old.MapMoney;
//import cn.mxz.mission.old.MapMoneyImpl;
//import cn.mxz.protocols.user.mission.MissionP.MoneyBoxPro;
//import cn.mxz.util.debuger.Debuger;
//
//import com.google.common.collect.Lists;
//
//public class MoneyBoxBuilder {
//
//	public Iterable<? extends MoneyBoxPro> build(List<MapMoneyImpl> list) {
//
//		List<MoneyBoxPro> all = Lists.newArrayList();
//
//		MoneyBoxPro.Builder b = null;
//
//		for (MapMoney mBox : list) {
//
//			b = MoneyBoxPro.newBuilder();
//
//			b.setId(mBox.getId());
//
//			b.setIndex(mBox.getLocation().getIndex());
//
//			b.setPath(mBox.getLocation().getPath());
//
//			b.setStoryIdEnd(mBox.getEndStory());
//
//			b.setStoryIdStart(mBox.getStartStory());
//
//			Debuger.debug("MoneyBoxBuilder.build() 宝箱:" + mBox.getLocation());
//
//			all.add(b.buildTest());
//		}
//
//		return all;
//	}
//}
