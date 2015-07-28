package cn.javaplus.twolegs.score;

import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.mxz.events.Listener;

import cn.javaplus.twolegs.game.GetBestScoreEvent;
import cn.javaplus.twolegs.game.ScoreCommit;
import cn.javaplus.twolegs.regist.Register;

public class CommitScore implements Listener<GetBestScoreEvent> {

	@Override
	public void onEvent(GetBestScoreEvent e) {
		final float oldS = e.getOldScore();
		final float newS = e.getNewScore();

		Register r = new Register();
		if (!r.hasRegist()) {
			r.regist(new CallBackJsonAdaptor() {
				@Override
				public void completed(JsonResult result) {
					commit(oldS, newS);
				}

			});
		} else {
			commit(oldS, newS);
		}

	}

	void commit(final float oldS, final float newS) {
		if (newS > oldS) {
			new ScoreCommit().commit(sub(newS));
		}
	}

	/**
	 * 保留2位小数
	 * 
	 * @param score
	 * @return
	 */
	public String sub(float score) {
		if (score <= 0) {
			return "0.00";
		}
		return String.format("%.2f", score);
	}

}
