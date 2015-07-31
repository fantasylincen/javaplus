package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.core.CoinStatus;
import org.hhhhhh.fqzs.core.GameStage;
import org.hhhhhh.fqzs.result.PlayResult.Result;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.log.Log;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class PlayResultAction extends RunnableAction {

	private final class PlayResult implements Runnable {
		private final Result next;

		private PlayResult(Result next) {
			this.next = next;
		}

		public void run() {
			String name=  App.getTranslator().translate(next.getType());
			Log.d("play result", next.getId(), next.getType(), name);
			GameStage stage = App.getApp().getStage();
			Group group = stage.getGameGroup();
			group.addActor(new AnimalSpecial());
		}
	}

	private Result next;

	public PlayResultAction(final Result next) {
		this.next = next;
		setRunnable(new PlayResult(next));
	}

}
