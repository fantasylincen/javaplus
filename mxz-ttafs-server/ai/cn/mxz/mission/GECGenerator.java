//package cn.mxz.mission;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//import cn.javaplus.common.util.Util;
//import cn.javaplus.common.util.Util.Random;
//import cn.javaplus.common.util.random.WeightFetcher;
//import cn.mxz.DropOutLibraryTemplet;
//import cn.mxz.DropOutLibraryTempletConfig;
//import cn.mxz.DropOutTypeTemplet;
//import cn.mxz.DropOutTypeTempletConfig;
//import cn.mxz.user.City;
//
//import com.google.common.base.Predicate;
//import com.google.common.collect.Collections2;
//import com.google.common.collect.ImmutableSet;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Sets;
//import com.google.common.collect.Sets.SetView;
//
//public class GECGenerator implements Generator {
//
//	private static final ImmutableSet<Integer> 	SOULS;
//	/**
//	 * 魂魄类型
//	 */
//	private static final int					SOUL_TYPE	= 6;
//
//	static{
//		Set<Integer> temp = Sets.newHashSet();
//		for( DropOutLibraryTemplet t : DropOutLibraryTempletConfig.getAll() ){
//			if( t.getType() == SOUL_TYPE ){
//				temp.add( t.getId() );
//			}
//		}
//
//		SOULS = ImmutableSet.copyOf( temp );
//	}
//
//	private City city;
//	private int	type;
//
//	public GECGenerator(City city, int type) {
//		this.city = city;
//		this.type = type;
//	}
//
//	/**
//	 * 生成随机类型
//	 * @return
//	 */
//	private int getType( ){
//
//		Collection<DropOutTypeTemplet>  filterCollection =
//		        Collections2.filter( DropOutTypeTempletConfig.getAll(), new Predicate<DropOutTypeTemplet>(){
//		    @Override
//		    public boolean apply(DropOutTypeTemplet templet) {
//
//		        return cn.javaplus.common.util.Util.Collection.getIntegers( templet.getRange()).contains( type);
//		}});
//
//		List<DropOutTypeTemplet> list = Lists.newArrayList( filterCollection );
//
//		DropOutTypeTemplet r = Util.Random.getRandomOneByWeight( list, new WeightFetcher<DropOutTypeTemplet>() {
//			@Override
//			public Integer get(DropOutTypeTemplet t) {
//				return t.getWeight();
//			}
//		});
//		return r.getId();
//	}
//
//
//	@Override
//	public int generate() {
//
//		int typeId = getType();
//		return calc(typeId);
//	}
//
//	public int calc( final int type ){
//
//		Collection<DropOutLibraryTemplet>  filterCollection =
//		        Collections2.filter( DropOutLibraryTempletConfig.getAll(), new Predicate<DropOutLibraryTemplet>(){
//		    @Override
//		    public boolean apply(DropOutLibraryTemplet templet) {
//		        return templet.getType() == type;
//		}});
//
//		List<DropOutLibraryTemplet> list = Lists.newArrayList( filterCollection );
////		list.addAll( filterCollection );
//
//		if( type != SOUL_TYPE ){
//			return calcNormal(list);
//		}
//		else{
//			return calcSoul( list );
//		}
//	}
//
//	private Set<Integer> buildFighterSets( List<DropOutLibraryTemplet> list ){
//		ImmutableSet<Integer> fighters = ImmutableSet.copyOf( city.getTeam().getFightersAll().keySet() );
//		if(fighters.isEmpty()) {
//			throw new IllegalArgumentException("找不到需要兽魂的神将!");
//		}
//		return fighters;
//	}
//
//	/**
//	 * 获取魂魄
//	 * @param list
//	 * @return
//	 */
//	private int calcSoul( List<DropOutLibraryTemplet> list ) {
//		SetView<Integer> intersection = Sets.intersection( buildFighterSets(list), SOULS );
//		if( intersection.size() == 0 ){
//			System.err.println("找不到需要兽魂的神将!");
//
//			return 0;
//		}
//		return calcSoul(intersection);
//	}
//
//	/**
//	 * 在(关卡)通用掉落库_DropOutLibrary.xls表中 从指定id的set中根据权重随机一个出来
//	 * @param col
//	 * @return
//	 */
//	int calcSoul( Set<Integer> col ){
//		List<DropOutLibraryTemplet> temp = Lists.newArrayList();
//		int[] weights = new int[col.size()];
//		int i = 0;
//		for( int id : col ){
//			DropOutLibraryTemplet templet = DropOutLibraryTempletConfig.get( id );
//			weights[i++] = templet.getWeight();
//			temp.add( templet );
//		}
//
//		int index = Random.getRandomIndex( weights );
//		return temp.get(index).getId();
//	}
//
//	private int calcNormal(List<DropOutLibraryTemplet> list) {
//		DropOutLibraryTemplet r = Util.Random.getRandomOneByWeight( list, new WeightFetcher<DropOutLibraryTemplet>() {
//
//			@Override
//			public Integer get(DropOutLibraryTemplet t) {
//				return t.getWeight();
//			}
//		});
//		return r.getId();
//	}
//}
