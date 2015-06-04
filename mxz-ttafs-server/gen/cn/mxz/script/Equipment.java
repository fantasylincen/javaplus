package cn.mxz.script;import static define.D.SHEN_JIA_OFFSET;import java.util.List;import java.util.Map;import cn.javaplus.math.Scope;import cn.javaplus.util.Util;import cn.mxz.city.City;import com.google.common.collect.Maps;/** 装备 equipment */public class Equipment {	/** 夺宝规则 */
	/** 夺宝规则 */
	public void snatchFilter(List<City> unrobots, int shenJia, int count) {
	
		Map<String, City> map = Maps.newHashMap();
	
		for (int i = 0; i < 200; i++) { // 循环200次到所有用户里面随机 身价在 600 误差范围内的用户
			if (map.size() >= count) {
				break;
			}
	
			City random = Util.Random.getRandomOne(unrobots);
			unrobots.remove(random);
	
			int sj = random.getFormation().getShenJia();
	
			if (Math.abs(shenJia - sj) < SHEN_JIA_OFFSET) { // 身价绝对值的差值 小于600 即满足条件
				map.put(random.getId(), random);
			}
		}
	
		unrobots.clear();
	
		unrobots.addAll(map.values());
	}
	/** 夺宝随机玩家出现规则 */
	/**
	 * 根据自己的身价, 确定 夺宝时随机玩家的身价范围
	 * @param shenJia 自己的身价
	 * @return
	 */
	public cn.javaplus.math.Scope getSnatchScope(int shenJia) {
	
		int min =(int)(shenJia*0.5);
		int max =(int)(shenJia*0.8);
	
		return new Scope(min, max);
	}
}