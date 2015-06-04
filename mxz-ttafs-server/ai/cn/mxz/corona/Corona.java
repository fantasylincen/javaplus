//package cn.mxz.corona;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cn.javaplus.common.util.Util.Random;
//import cn.mxz.CoronaBankTemplet;
//import cn.mxz.CoronaBankTempletConfig;
//import cn.mxz.CoronaCreateTemplet;
//import cn.mxz.CoronaCreateTempletConfig;
//import cn.mxz.bossbattle.Prize;
//import cn.mxz.mission.old.PrizeImpl;
//
//import com.google.common.collect.Lists;
//
//
///**
// * 转盘类
// * @author Administrator
// *
// */
//class Corona {
//
//	/**
//	 * 转盘奖品的数量
//	 */
//	private static final int REWARD_COUNT = 18;
//
//	/**
//	 * 总的奖励类型
//	 * 	1	材料道具
//	 *	2	消耗道具
//	 *	3	元宝
//	 *	4	铜钱
//	 *	5	神将
//	 *	6	神兽
//	 *	7	装备
//	 */
//	private static final int REWARD_TYPE_COUNT = CoronaCreateTempletConfig.getKeys().size();
//
//
//	/**
//	 * 记录各个奖励类型已经出现的次数，避免超限
//	 */
//	private Map<Integer,Integer> rewardTypes = new HashMap<Integer,Integer>();
//
//	/**
//	 * 玩家的转盘奖品
//	 */
//	private List<Prize> data;
//
//
//
//	public Corona() {
//
//		for( int i = 1; i < REWARD_TYPE_COUNT + 1; i++ ){//下标从1开始
//			rewardTypes.put( i, 0 );
//		}
//		build();
//	}
//
//	/**
//	 * 生成转盘数据
//	 */
//	private void build(){
//		data = Lists.newArrayList();
//
//		for( int i = 0; i < REWARD_COUNT; i++ ){
//			int typeId = getPrizeType();
//
//			Prize p = buildPrize( typeId );
//			data.add( p );
//
//		}
//	}
//
//
//	/**
//	 * 获取转盘的格子数据
//	 * @return
//	 */
//	public final List<Prize> getCoronaData(){
//		return data;
//	}
//
//
//	/**
//	 * 检测此奖励的类型是否超限
//	 * @param typeId
//	 * @return
//	 * 		true		超限
//	 * 		false		未超限
//	 *
//	 */
//	private boolean prizeTypeIsOverrun( int typeId ){
//		if( rewardTypes.get( typeId ) >= CoronaCreateTempletConfig.get(typeId).getNumberMax() ) {
//			return true;
//		}
//		int typeCount = rewardTypes.get(typeId);
//		typeCount++;//此类型奖品数量+1
//		rewardTypes.put(typeId,  typeCount );
//		return false;
//	}
//
//	/**
//	 * 获取道具类型,注意不要超限
//	 * @return
//	 */
//	private int getPrizeType(){
//		int ret;
//		int	index;
//		while( true ){
//			index = Random.getRandomIndex( CoronaCreateTempletConfig.getArrayByWeight() );
//
//			ret = CoronaCreateTempletConfig.get( CoronaCreateTempletConfig.getKeys().get(index) ).getTypeid();
//
//			if( prizeTypeIsOverrun( ret ) ){
//				continue;
//			}
//			else{
//				//System.out.println(CoronaCreateTempletConfig.get( CoronaCreateTempletConfig.getKeys().get(index) ).getTypeNeame() );
//				return ret;
//			}
//			//是否需要一个补丁防止死循环？？？？？？？
//
//		}
//
//	}
//
//	/**
//	 *	计算转盘的奖励
//	 * @param prizeTypeId	道具类型
//	 * @return
//	 */
//	private Prize buildPrize( int prizeTypeId ){
//		int id = 0;
//
//		List<CoronaBankTemplet> list = CoronaBankTempletConfig.findByTypeId( prizeTypeId );
//		int index = Random.getRandomIndex( getWeight(list) );
//
//		CoronaBankTemplet t = list.get(index);
//
//		int propId = t.getPropId();
//
//		int count = Random.get( t.getPropMin(), t.getPropMax() );
//		if( prizeTypeId == 3 ){//元宝要取整
//			count /= 10;
//			count *= 10;
//		}
//		Prize p = new PrizeImpl( propId, count );
//
//		return p;
//	}
//
//	/**
//	 * 获取道具出现的权重数组
//	 * @param list
//	 * @return
//	 */
//	private int[] getWeight( List<CoronaBankTemplet> list ){
//		int[] ret = new int[list.size()];
//
//		for( int i = 0; i < list.size(); i++ ){
//			ret[i] = list.get(i).getAriseWeight();
//		}
//
//		return ret;
//
//	}
//
//	/**
//	 * 玩家开始转动转盘
//	 * @return
//	 */
//	public Prize run() {
//		Prize p = null;
//		int index, rewardTypeId;
//		while( true ){
//			index = Random.getRandomIndex( CoronaCreateTempletConfig.getArrayByGetWeight() );
//
//			CoronaCreateTemplet t = CoronaCreateTempletConfig.get( CoronaCreateTempletConfig.getKeys().get(index) );
//			rewardTypeId = t.getTypeid();
//
//			p = getRandomPrizeByTypeId( rewardTypeId );
//			if( p == null ){
//				continue;
//			}
//			else{
//				return p;
//			}
//		}
//
//	}
//
//	/**
//	 * 随机获取一个转盘中某类型的奖励物品
//	 * @param prizeTypeId
//	 * @return
//	 * 			给定类型不存在奖品，返回null
//	 */
//	private Prize getRandomPrizeByTypeId( int prizeTypeId ) {
//		List<Prize> l = Lists.newArrayList();
//		Prize prize;
//		for( Prize p : data ){
//			CoronaBankTemplet t = CoronaBankTempletConfig.findByPropId( p.getId() ).get( 0 );//这里可能出现多个，但应该不会出错，例如铜钱的道具id是相同的，他们的typeId也相同
//
//			if( t.getTypeId() == prizeTypeId ){
//				l.add( p );
//			}
//		}
//		if( l.size() == 0 ){
//			return null;
//		}
//		else{
//			prize = cn.javaplus.common.util.Util.Collection.getRandomOne( l );
//		}
//
//		CoronaBankTemplet t = CoronaBankTempletConfig.findByPropId( prize.getId() ).get( 0 );
//		if( t.getGetMax() > 0 /** && 超过10个 **/ ){
//		}
//
//		return prize;
//
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		for( Prize p : data ){
//
//			sb.append( p.getId() );
//			sb.append( ":" );
//			sb.append( p.getCount() + " " );
//		}
//		return sb.toString();
//	}
//
//	static void test ( int[] a ){
//		a[3] = 10000;
//	}
//	public static void main(String[] args) {
//
////
////		long begin = System.nanoTime();
////		for( int i = 0; i < 500000; i++ ){
////			Corona c = new Corona();
//////			System.out.println( c );
////
//////			System.out.println( c.run() );
////			c.run();
////		}
////		System.out.println( (System.nanoTime() - begin) / 1000000000f );
////		System.out.println( times );
//
//		int[] a = new int[]{3,7,50,40};
////		System.out.println( Arrays.toString(a) );
//		//test(a);
////		System.out.println( Arrays.toString(a) );
//		int[] b = Arrays.copyOf( a, a.length );
//
//		for( int i = 1; i < b.length; i++ ){
//			b[i] = b[i] + b[i-1];
//		}
//		System.out.println( Arrays.toString(b) );
//
//		//new Random().ne
////		for()
//	}
//}
