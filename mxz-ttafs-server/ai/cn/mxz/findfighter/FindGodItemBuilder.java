//package cn.mxz.findfighter;
//
//import java.util.List;
//
//import cn.mxz.GodTypeTempletConfig;
//import cn.mxz.util.debuger.Debuger;
//
//public class FindGodItemBuilder {
//
//	public FindGodItemPro build(FighterFinder finder) {
//
//		FindGodItemPro.Builder b = FindGodItemPro.newBuilder();
//
//		Debuger.debug("FindGodItemBuilder.build() ---------- START ------------");
//
//		b.setId(finder.getId());
//
//		Debuger.debug("	寻仙方式:" + finder.getId()  + "." + GodTypeTempletConfig.get(finder.getId()).getName());
//
//		Debuger.debug("	剩余时间:" + finder.getRemainSec());
//
//		Debuger.debug("	最多可寻仙次数:" + finder.getFindTimesMax());
//
//		b.setRemainSec(finder.getRemainSec());
//
//		b.setCount(finder.getItemUnderCount());
//
//		b.setFreeTimesToday(finder.getFreeTimesToday());
//
//		List<Integer> list = finder.getTimesSelectedAble();
//
//		while(list.size() < 2) {
//
//			list.add(0);
//		}
//
//		for (Integer timeSelectedAble : list) {			//可以选择的寻仙次数
//
//			Debuger.debug("	可选次数:" + timeSelectedAble);
//
//			b.addTimesSelectedAble(timeSelectedAble);
//		}
//
//		Debuger.debug("FindGodItemBuilder.build() ---------- END ------------");
//
//		return b.build();
//	}
//
//}
