package cn.javaplus.crazy.user;

import java.util.HashMap;
import java.util.Map;

import cn.javaplus.log.Log;

public class World {

	private Map<Integer, User> onlines;

	public World() {
		onlines = new HashMap<Integer, User>();
	}

	public User getByClientId(int clientId) {
		return onlines.get(clientId);
	}

	/**
	 * 进入
	 * 
	 * @param clientId
	 * @param user
	 */
	public void onEnter(int clientId, User user) {
		onlines.put(clientId, user);
		Log.d("user enter world", user.getNick(), clientId, onlines.size());
	}

	/**
	 * 退出
	 * 
	 * @param id
	 * @return
	 */
	User onExit(int id) {
		User user = onlines.remove(id);
		if (user != null) {
			Log.d("user exit world", user.getNick(), onlines.size());
		}

		return user;
	}
}
