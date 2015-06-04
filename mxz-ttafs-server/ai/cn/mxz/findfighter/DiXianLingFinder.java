//package cn.mxz.findfighter;
//
//import java.util.List;
//
//import cn.mxz.GodTypeTempletConfig;
//import cn.mxz.user.City;
//import cn.mxz.user.team.god.Hero;
//
//public class DiXianLingFinder implements FighterFinder {
//
//	private FighterFinderImpl	finder;
//
//	public DiXianLingFinder(City city) {
//		finder = new FighterFinderImpl(city, GodTypeTempletConfig.get(1));
//	}
//
//	@Override
//	public List<Hero> find(int times) {
//		return finder.find(times);
//	}
//
//	@Override
//	public void check(int times) {
//		finder.check(times);
//	}
//
//	@Override
//	public int hashCode() {
//		return finder.hashCode();
//	}
//
//	@Override
//	public void reduce(int times) {
//		finder.reduce(times);
//	}
//
//	@Override
//	public int getId() {
//		return finder.getId();
//	}
//
//	@Override
//	public int getRemainSec() {
//		return finder.getRemainSec();
//	}
//
//	@Override
//	public boolean isDeprecated() {
//		return finder.isDeprecated();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		return finder.equals(obj);
//	}
//
//	@Override
//	public int getFindTimesMax() {
//		return finder.getFindTimesMax();
//	}
//
//	@Override
//	public List<Integer> getTimesSelectedAble() {
//
//		List<Integer> timesSelectedAble = finder.getTimesSelectedAble();
//
//		return timesSelectedAble;
//	}
//
//	@Override
//	public boolean isSingle() {
//		return finder.isSingle();
//	}
//
//	@Override
//	public int getCount() {
//		return finder.getCount();
//	}
//
//	@Override
//	public String toString() {
//		return finder.toString();
//	}
//
//	@Override
//	public int getItemUnderCount() {
//		return finder.getItemUnderCount();
//	}
//
//	@Override
//	public int getFreeTimesToday() {
//		return finder.getFreeTimesToday();
//	}
//
//}
