//package cn.mxz.findfighter;
//
//import java.util.List;
//
//import cn.mxz.GodTypeTemplet;
//import cn.mxz.GodTypeTempletConfig;
//import cn.mxz.user.City;
//
//import com.google.common.collect.Lists;
//
//public class FindFighterFactory {
//
//	public static List<FighterFinder> getAllFinders(City city) {
//
//		List<Integer> gts = GodTypeTempletConfig.getKeys();
//
//		List<FighterFinder> finders = Lists.newArrayList();
//
//		for (Integer id : gts) {
//
//			finders.add(getFinder(city, id));
//		}
//
//		return finders;
//	}
//
//	public static FighterFinder getFinder(City city, int type) {
//
//
//		switch (type) {
//
//		case 1:
//			return new DiXianLingFinder(city);
//		}
//
//		GodTypeTemplet temp = GodTypeTempletConfig.get(type);
//
//		FighterFinder finder = new FighterFinderImpl(city, temp);
//
//		return finder;
//	}
//
//}
