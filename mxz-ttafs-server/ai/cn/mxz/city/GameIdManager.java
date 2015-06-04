package cn.mxz.city;

import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.linekong.platform.protocol.erating.define.D;

public class GameIdManager {

	private City city;

	public GameIdManager(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public void save(String gameId) {
		try {
			UserCounter c = city.getUserCounterHistory();
			if (gameId == null || gameId.trim().isEmpty())
				gameId = "0";
			c.set(CounterKey.GAME_ID, new Integer(gameId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public long getGameId() {
		try {
			UserCounter c = city.getUserCounterHistory();
			int id = c.get(CounterKey.GAME_ID);
			if(id == 0)
				return D.GAME_ID;
			return id;
		} catch (Exception e) {
			return D.GAME_ID;
		}
	}
}
