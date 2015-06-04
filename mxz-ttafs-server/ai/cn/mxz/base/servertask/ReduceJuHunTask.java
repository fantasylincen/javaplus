package cn.mxz.base.servertask;

import java.util.Collection;

import cn.javaplus.time.Time;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

import define.D;

/**
 * 聚魂减少任务
 * 
 * @author 林岑
 * 
 */
public class ReduceJuHunTask extends TaskSafetyLogToFile {

	public static final long	RATE	= 10 * Time.MILES_ONE_MIN;
	
	private static ReduceJuHunTask	instance;

	private long	lastTime;

	private ReduceJuHunTask() {
		lastTime = System.currentTimeMillis();
	}

	public static final ReduceJuHunTask getInstance() {
		if (instance == null) {
			instance = new ReduceJuHunTask();
		}
		return instance;
	}

	@Override
	public void runSafty() {
		
		Collection<City> all = Lists.newArrayList(getNearests());
		for (City city : all) {
			Player p = city.getPlayer();
			int now = p.get(PlayerProperty.JU_HUN);
			if(now <= D.JU_HUN_REDUCE_10_MIN) {
				p.reduce(PlayerProperty.JU_HUN, now);
			} else {
				p.reduce(PlayerProperty.JU_HUN, D.JU_HUN_REDUCE_10_MIN);
			}
		}
		lastTime = System.currentTimeMillis();
	}

	public int getRemainingSec() {
		
		long end = lastTime + RATE;
		
		return (int) ((end - System.currentTimeMillis()) / 1000);
	}

}
