package cn.javaplus.twolegs.game;

import java.util.HashMap;
import java.util.Map;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.log.Log;
import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.Events;

public class SaveBestScore implements Listener<GameOverEvent> {

	private static final String KEY = "best-score";

	@Override
	public void onEvent(GameOverEvent e) {
		IPreferences store = App.getPreferences();
		String string = store.getString(KEY);
		if(string.isEmpty()) {
			string = "0.00";
		}
		float best = new Float(string);
		String text = e.getScoreText();
		float newScore = new Float(text);
		Log.d("SaveBestScore.onEvent " + best + ", " + newScore);
		
		Map<String, String > kvs = new HashMap<String, String>();
		kvs.put("score", newScore + "");
		App.getLogger().onEvent("score", kvs);
		if (newScore > best) {
			Events.dispatch(new GetBestScoreEvent(newScore, best));
			store.put(KEY, text);
		}
	}

}
