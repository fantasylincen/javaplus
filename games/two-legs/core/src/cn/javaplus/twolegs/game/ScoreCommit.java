package cn.javaplus.twolegs.game;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.TwoLegsRequest;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Util;

import cn.javaplus.twolegs.App;

public class ScoreCommit {
	
	public void commitBestScore(String old) {
		IPreferences p = App.getPreferences();

		String newS = p.getString("best-score");

		if (newS == null)
			return;
		if (newS.isEmpty())
			return;
		Float newScore = new Float(newS);
		Float oldScore = new Float(old);
		
		if (newScore > oldScore) {
			commit(newS);
		}
	}

	private boolean isRegist() {
		IPreferences p = App.getPreferences();
		return p.getString("roleId") != null;
	}

	public void commit(final String newS) {
		if (!isRegist()) {
			Log.d("not regist can not commit");
			return;
		}
		App.getHttp().excute(new CommitRequest(newS), new CallBackJsonAdaptor() {
			@Override
			public void completed(JsonResult result) {
				Log.d("commit score success");
			}
		});
	}


	public class CommitRequest implements TwoLegsRequest {

		private String best;

		public CommitRequest(String best) {
			this.best = best;
		}

		@Override
		public Parameters getParameters() {
			IPreferences pr = App.getPreferences();

			Parameters p = new Parameters();
			p.put("bestScore", best);
			p.put("roleId", pr.getString("roleId"));
			p.put("passwordMd5", Util.Secure.md5(pr.getString("password")));
			p.put("key", KeyGenerator.generateKey(best));
			return p;
		}

		@Override
		public String getDomain() {
			return "commitBestScore";
		}

	}
}
