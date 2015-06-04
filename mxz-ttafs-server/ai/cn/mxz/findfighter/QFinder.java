//package cn.mxz.findfighter;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import cn.mxz.FighterTemplet;
//import cn.mxz.FighterTempletConfig;
//import cn.mxz.GodQurlityTemplet;
//import cn.mxz.GodQurlityTempletConfig;
//import cn.mxz.GodTypeTemplet;
//import cn.mxz.city.City;
//import cn.mxz.user.team.god.Hero;
//
//
//class QFinder extends FinderBase {
//
//	private GodTypeTemplet	temp;
//
//
//	public QFinder(City city, String string, GodTypeTemplet temp) {
//
//		super(city, string);
//
//		this.temp = temp;
//	}
//
//	@Override
//	protected Hero findOneTime() {
//
//		List<Integer> list = getGodBank();
//
//		List<FighterTemplet> q = findBy(type);
//
//		Iterator<FighterTemplet> it = q.iterator();
//
//		while (it.hasNext()) {
//
//			FighterTemplet next = it.next();
//
//			String types = next.getGodType();
//
//			String[] split = types.split(",");
//
//			for (String v : split) {
//
//				if (v.trim().isEmpty()) {
//
//					continue;
//				}
//
//				if (!list.contains(new Integer(v))) {
//
//					it.remove();
//				}
//			}
//		}
//
//		FighterTemplet randomOne = cn.javaplus.util.Util.Collection.getRandomOne(q);
//
//		return city.getTeam().createNewHero(randomOne.getType());
//	}
//
//	/**
//	 * 寻找所有品阶为step的战士
//	 *
//	 * @param step
//	 * @return
//	 */
//	private List<FighterTemplet> findBy(int step) {
//
//		List<FighterTemplet> ls = new ArrayList<FighterTemplet>();
//
//		for (FighterTemplet fighterTemplet : FighterTempletConfig.getAll()) {
//
//			int quality = fighterTemplet.getQuality();
//
//			GodQurlityTemplet t = GodQurlityTempletConfig.get(quality);
//
//			if (step == t.getStep()) {
//
//				ls.add(fighterTemplet);
//			}
//
//		}
//
//		return ls;
//	}
//
//	/**
//	 * 获得当前寻仙方式可获得神将的 库
//	 *
//	 * @return
//	 */
//	private List<Integer> getGodBank() {
//
//		String godBank = temp.getGodBank();
//
//		String[] split = godBank.split(",");
//
//		List<Integer> list = new ArrayList<Integer>();
//
//		for (String v : split) {
//
//			if (v.isEmpty()) {
//
//				continue;
//			}
//
//			list.add(new Integer(v));
//		}
//
//		return list;
//	}
//
//}
