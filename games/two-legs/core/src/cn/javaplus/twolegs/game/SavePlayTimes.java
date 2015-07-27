package cn.javaplus.twolegs.game;

import java.util.HashMap;
import java.util.Map;

import org.javaplus.game.common.IPreferences;
import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;

public class SavePlayTimes implements Listener<GameOverEvent> {

	private static final String KEY = "play-times";

	@Override
	public void onEvent(GameOverEvent e) {
		IPreferences store = App.getPreferences();
		int v = store.getInt(KEY);
		System.out.println("save play times:" + (v + 1));
		store.put(KEY, (v + 1));

		Map<String, String > kvs = new HashMap<String, String>();
		kvs.put("times", (v + 1) + "");
		App.getLogger().onEvent("playtimes", kvs);
	}

}
