package cn.mxz.events.init;

import cn.mxz.city.City;

/**
 * 玩家进入游戏事件 (老用户直接登入进游戏, 或者新账号创建角色)
 * 
 * @author 林岑
 * 
 */
public class EnterGameEvent {

	private City city;
	private String gameId;

	public EnterGameEvent(City city, String gameId) {
		this.city = city;
		this.gameId = gameId;
	}

	public City getCity() {
		return city;
	}

	/**
	 * 可能为null
	 * @return
	 */
	public String getGameId() {
		return gameId;
	}

}
