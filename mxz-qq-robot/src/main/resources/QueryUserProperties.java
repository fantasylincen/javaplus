import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Sets;

import db.domain.NewFighter;

public class QueryUserProperties {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置玩家某项属性
	 */
	public String query(String user, String type) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		type = type.toLowerCase();
		if (type.equals("元宝")) {

			return city.getPlayer().getGoldAndJinDing() + "";
		} else if (Sets.newHashSet("通关id", "副本id", "关卡id").contains(type)) {
			return city.getUserCounterHistory().get(CounterKey.MAX_MISSION_ID) + "";
		} else if (type.equals("金币") || type.equals("铜钱")) {
			return "" + city.getPlayer().get(PlayerProperty.CASH);
		} else if (type.equals("体力")) {
			return "" + city.getPlayer().get(PlayerProperty.PHYSICAL);
		} else if (type.equals("修行")) {
			return "" + city.getPlayer().get(PlayerProperty.CULTIVATION);
		} else if (type.equals("声望")) {
			return "" + city.getPlayer().get(PlayerProperty.REPUTATION);
		} else if (type.equals("精力")) {
			return "" + city.getPlayer().get(PlayerProperty.POWER);
		} else if (type.equals("聚魂")) {
			return "" + city.getPlayer().get(PlayerProperty.JU_HUN);
		} else if (type.equals("荣誉")) {
			return "" + city.getPlayer().get(PlayerProperty.RONG_YU);
		} else if (type.equals("经验")) {

			NewFighter dto = city.getTeam().getPlayer().getDto();
			return "" + dto.getExp();
		} else if (type.equals("等级")) {

			NewFighter dto = city.getTeam().getPlayer().getDto();
			return "" + dto.getLevel();
		} else if (type.equals("vip等级") || type.equals("vip")) {
			return "" + city.getPlayer().getVipLevel();

		} else if (type.equals("兽魂")) {
			return "" + city.getPlayer().get(PlayerProperty.SHOU_HUN);
		} else if (type.equals("金锭") || type.equals("金贝壳") || type.equals("贝壳")) {
			return "" + city.getPlayer().get(PlayerProperty.NEW_GOLD);

		} else {
			String cc = type.toLowerCase();
			HashSet<String> newHashSet = Sets.newHashSet("段位id", "段位", "dan",
					"danid");
			if (newHashSet.contains(cc)) {
				PvpManager p = city.getNewPvpManager();
				PvpPlayer player = p.getPlayer();
				return "" + player.getDan();
			} else {
				throw new RuntimeException("无法识别: [" + type + "]");
			}
		}
	}


	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		
		
		
		String id = all.get(user);
		if (id == null) {
			id = findNick(user, all);
			if (id == null) {
				return null;
			}
		}

		return getCity(id);
	}

	private String findNick(String user, Map<String, String> all) {
		ArrayList<String> ls = new ArrayList<String>(all.keySet());
		
		for (String s : ls) {
			if(Util.Chinese.getPinYinHump(s).equals(Util.Chinese.getPinYinHump(user))) {
				String id = all.get(s);
				return id;
			}
		}
		return null;
	}
}