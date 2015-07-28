package cn.javaplus.jigsaw.gameover;

import org.mxz.events.Listener;

import cn.javaplus.jigsaw.game.GameOverEvent;
import cn.javaplus.jigsaw.game.GameStage;
import cn.javaplus.jigsaw.game.Marsking;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;

public class GameOverShowScore implements Listener<GameOverEvent> {

	public class RestartListener implements MarskingListener {

		private GameStage gameStage;

		public RestartListener(GameStage gameStage) {
			this.gameStage = gameStage;
		}

		@Override
		public void onClose() {
			gameStage.restart();
		}

	}

	@Override
	public void onEvent(GameOverEvent e) {
		final ScorePanel s = new ScorePanel(e.getCubes().getTime());
		s.setScale(0.1f, 0.1f);
		s.setPosition(180, 200);
		s.setColor(new Color(1, 1, 1, 0));
		System.out.println("GameOverShowScore.onEvent()" + s.getWidth() + ","
				+ s.getHeight());
		s.addAction(new PopAction(s) {
			@Override
			public void end() {
				s.showScore();
			}
		});
		AlphaAction aa = new AlphaAction();
		aa.setDuration(0.1f);
		aa.setAlpha(1);
		s.addAction(aa);
		Marsking m = new Marsking(s);
		m.addListener(new RestartListener(e.getStage()));
		e.getStage().addActor(m);
	}

}
