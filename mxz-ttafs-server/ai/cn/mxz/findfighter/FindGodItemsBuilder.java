//package cn.mxz.findfighter;
//
//import java.util.List;
//
//import cn.mxz.GodTypeTemplet;
//import cn.mxz.GodTypeTempletConfig;
//import cn.mxz.protocols.user.god.FindGodP.FindGodItemsPro;
//import cn.mxz.user.City;
//
//public class FindGodItemsBuilder {
//
//	public FindGodItemsPro build(List<FighterFinder> finders, City city) {
//
//		FindGodItemsPro.Builder b = FindGodItemsPro.newBuilder();
//
//		for (FighterFinder finder : finders) {
//
//			GodTypeTemplet temp = GodTypeTempletConfig.get(finder.getId());
//
//			if(temp.getShow() != 1) {
//				continue;
//			}
//
//			if(finder.isDeprecated()) {
//				continue;
//			}
//
//			b.addGods(new FindGodItemBuilder().build(finder));
//		}
//
//		return b.build();
//	}
//
//}
