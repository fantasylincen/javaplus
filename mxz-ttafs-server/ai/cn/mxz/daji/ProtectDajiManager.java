package cn.mxz.daji;

import java.util.concurrent.ConcurrentHashMap;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;

public enum ProtectDajiManager {
	INSTANCE;

	/**
	 * 临时保存玩家数据
	 */
	ConcurrentHashMap<String, DajiUserData> userDatas = new ConcurrentHashMap<String, DajiUserData>();

	/**
	 * 
	 * @param user
	 * @return
	 * 
	 * @NotNull
	 */
	public DajiUserData getUserData(City user) {

		DajiUserData data = userDatas.get(user.getId());
		if (data == null) {
			data = new DajiUserData(user);
			DajiUserData temp = userDatas.putIfAbsent(user.getId(), data);
			if (temp != null) {
				data = temp;
			}
		}
		return data;
	}

	/**
	 * 点击保护按钮
	 * 
	 * @param user
	 * @return
	 */
	public int protect(City user) {
		DajiUserData data = getUserData(user);

		return data.protect();

	}

	/**
	 * 点击驱赶按钮,如果胜利，生成奖励信息
	 * 
	 * @param user
	 * @return
	 */
	public Battle drift(City user) {
		DajiUserData data = getUserData(user);
		return data.drift();
	}

	// /**
	// * 逢三或者逢八领取奖励
	// * @param signCount
	// */
	//
	// public void getPrize( City user ) {
	// DajiUserData data = getUserData( user );
	// data.getProtectPrize();
	// }

	/**
	 * 驱赶奖励
	 * 
	 * @param signCount
	 */
	public void getDriftPrize(City user) {
		DajiUserData data = getUserData(user);
		data.getDriftPrize();
	}

	public int getProtectPrize(City user) {
		DajiUserData data = getUserData(user);
		return data.getProtectPrize();

	}

}
