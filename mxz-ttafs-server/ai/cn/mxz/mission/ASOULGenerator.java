//package cn.mxz.mission;
//
//import java.util.List;
//import java.util.Set;
//
//import cn.javaplus.common.util.Util.Random;
//import cn.mxz.DropOutASoulTemplet;
//import cn.mxz.DropOutASoulTempletConfig;
//import cn.mxz.user.City;
//
//import com.google.common.collect.ImmutableSet;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Sets;
//import com.google.common.collect.Sets.SetView;
//
//public class ASOULGenerator implements Generator {
//
//	private static final ImmutableSet<Integer> souls;
//	static {
//		Set<Integer> temp = Sets.newHashSet();
//		for (int id : DropOutASoulTempletConfig.getArrayById()) {
//			temp.add(id);
//		}
//		souls = ImmutableSet.copyOf(temp);
//	}
//	private City city;
//
//	public ASOULGenerator(City city) {
//		this.city = city;
//	}
//
//	@Override
//	public int generate() {
//
//		ImmutableSet<Integer> fighters = ImmutableSet.copyOf(city.getTeam()
//				.getFightersAll().keySet());
//		SetView<Integer> coll = Sets.intersection(fighters, souls);
//
//		if( coll.size() == 0 ){
//			System.err.println("找不到需要兽魂的神将!");
//
//			return 0;
//		}
//		// for (Integer integer : coll)
//		// System.out.println(integer);
//
//		return calc(coll);
//	}
//
//	/**
//	 * 从指定id的set中根据权重随机一个出来
//	 *
//	 * @param col
//	 * @return
//	 */
//	int calc(Set<Integer> col) {
//		List<DropOutASoulTemplet> temp = Lists.newArrayList();
//		int[] weights = new int[col.size()];
//		int i = 0;
//		for (int id : col) {
//			DropOutASoulTemplet templet = DropOutASoulTempletConfig.get(id);
//			weights[i++] = templet.getWeight();
//			temp.add(templet);
//		}
//
//		int index = Random.getRandomIndex(weights);
//		return temp.get(index).getId();
//
//	}
//
//	public static void main(String[] args) {
//		// ASOULGenerator a = new ASOULGenerator();
//		// System.out.println( a.generate() );
//	}
//
//}
