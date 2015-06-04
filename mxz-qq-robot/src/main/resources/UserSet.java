import java.util.HashSet;
import java.util.Map;

import com.google.common.collect.Sets;

import cn.javaplus.util.Util;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDataDao2;
import db.domain.NewFighter;
import db.domain.UserData;

public class UserSet {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置玩家某项属性
	 */
	public void set(String user, String type, String cnt) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		int count = trim(cnt);
		if (count < 0) {
			throw new RuntimeException("数量必须大于0");
		}
		type = type.toLowerCase();
		if (type.equals("元宝")) {

			UserDataDao2 dao = DaoFactory.getUserDataDao();
			UserData dto = dao.get(city.getId());
			dto.setCoupon(count);
			dto.setGold(0);
			dao.save(dto);
			city.reloadPlayer();

		} else if (Sets.newHashSet("通关id", "副本id", "关卡id").contains(type)) {
			city.getUserCounterHistory().set(CounterKey.MAX_MISSION_ID, count);
			WorldFactory.getWorld().reload();
		} else if (type.equals("金币") || type.equals("铜钱")) {
			city.getPlayer().set(PlayerProperty.CASH, count);
		} else if (type.equals("体力")) {
			city.getPlayer().set(PlayerProperty.PHYSICAL, count);
		} else if (type.equals("修行")) {
			city.getPlayer().set(PlayerProperty.CULTIVATION, count);
		} else if (type.equals("声望")) {
			city.getPlayer().set(PlayerProperty.REPUTATION, count);
		} else if (type.equals("精力")) {
			city.getPlayer().set(PlayerProperty.POWER, count);
		} else if (type.equals("聚魂")) {
			city.getPlayer().set(PlayerProperty.JU_HUN, count);
		} else if (type.equals("荣誉")) {
			city.getPlayer().set(PlayerProperty.RONG_YU, count);
		} else if (type.equals("经验")) {

			NewFighter dto = city.getTeam().getPlayer().getDto();
			dto.setExp(count);
			DaoFactory.getNewFighterDao().save(dto);
			city.reloadTeam();
		} else if (type.equals("等级")) {

			NewFighter dto = city.getTeam().getPlayer().getDto();
			dto.setLevel(count);
			DaoFactory.getNewFighterDao().save(dto);

			city.reloadTeam();
		} else if (type.equals("vip等级") || type.equals("vip")) {
			UserCounter g = city.getUserCounterHistory();
			g.set(CounterKey.VIP_GROWTH, getVipGrowth(count));

		} else if (type.equals("兽魂")) {
			city.getPlayer().set(PlayerProperty.SHOU_HUN, count);
		} else if (type.equals("金锭") || type.equals("金贝壳") || type.equals("贝壳")) {
			city.getPlayer().set(PlayerProperty.NEW_GOLD, count);

		} else {
			String cc = type.toLowerCase();
			HashSet<String> newHashSet = Sets.newHashSet("段位id", "段位", "dan",
					"danid");
			if (newHashSet.contains(cc)) {
				PvpManager p = city.getNewPvpManager();
				PvpPlayer player = p.getPlayer();
				player.getDto().setDanId(count);
				city.reloadNewPvpManager();
			} else {
				throw new RuntimeException("无法识别: [" + type + "]");
			}
		}
		update(city);
	}

	private int getVipGrowth(int level) {
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) level);
		if (temp == null) {
			return 0;
		}

		return temp.getGrowth();
	}

	/**
	 * 更新客户端数据
	 * 
	 * @param city
	 */
	private void update(City city) {
		UserBuilder bd = new UserBuilder();
		UserPro d = bd.build(city);
		MessageFactory.getUser().onUpdateUserList(city.getSocket(), d);
	}

	private int trim(String cnt) {
		cnt = cnt.replaceAll("个", "");
		cnt = cnt.replaceAll("点", "");
		cnt = cnt.replaceAll("枚", "");
		cnt = cnt.replaceAll("颗", "");
		cnt = cnt.replaceAll("匹", "");
		cnt = cnt.replaceAll("张", "");
		cnt = cnt.trim();
		if (!cnt.matches("[0-9]+")) {
			cnt = "" + Util.Chinese.chineseToDigit(cnt);
		}
		return new Integer(cnt);
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
			return null;
		}

		return getCity(id);
	}
}