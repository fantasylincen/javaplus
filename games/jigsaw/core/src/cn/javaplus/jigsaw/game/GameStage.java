package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;

import cn.javaplus.jigsaw.App;
import cn.javaplus.jigsaw.define.D;
import cn.javaplus.jigsaw.events.Events;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class GameStage extends AbstractStage {

	ControllerPanel panel;
	private BlackGround blackGround;
	private Cubes cubes;

	public GameStage() {
		super(create());
	}

	private static ScalingViewport create() {
		OrthographicCamera camera = new OrthographicCamera();
		return new ScalingViewport(Scaling.stretch, D.STAGE_W, D.STAGE_H, camera);
	}

	@Override
	public void onLoadingOver() {
		restart();
	}

	private void addCubes() {
		cubes = new Cubes(blackGround.getWidth(), this, getC());
		cubes.setPosition(blackGround.getX(), blackGround.getY());
		addActor(cubes);
	}

	private int getC() {
		int count = App.getPreferences().getInt("count");
		if (count == 0) {
			count = 3;
			App.getPreferences().put("count", count);
		}
		return count;
	}

	private void addBlackGround() {
		blackGround = new BlackGround();
		float width = D.STAGE_W / 5 * 4;
		float height = D.STAGE_W / 5 * 4;
		blackGround.setSize(width, height);
		float x = D.STAGE_W / 2 - width / 2;
		float y = D.STAGE_H / 2 - height / 2;
		blackGround.setPosition(x, y);
		// addActor(blackGround);
	}

	private void addBackGround() {
		BackGround actor = new BackGround();
		addActor(actor);
	}

	@Override
	public boolean keyTyped(char c) {

		if (c == 27 || c == 0) {
			Events.dispatch(new ExitEvent());
			return true;
		}

		return true;
	}
	
	

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}

	public void restart() {
		clear();
		panel = new ControllerPanel();
		addBackGround();
		addBlackGround();
		addCubes();
		addActor(panel);
		addScoreLabel();
		addButtons();
	}

	private void addButtons() {
		RestartButton b = new RestartButton(this);
		b.setY(30);
		b.setX(250 + 37);
		addActor(b);

		ChangeMapButton bb = new ChangeMapButton(this);
		bb.setY(30);
		bb.setX(75);
		addActor(bb);

		HelpButton bc = new HelpButton(this);
		bc.setY(30);
		bc.setX(500);
		addActor(bc);
	}

	private void addScoreLabel() {
		ScoreLabel sp = new ScoreLabel(cubes);
		sp.setPosition(200, 900);
		addActor(sp);

		long best = App.getPreferences().getLong("best-score-" + cubes.getC());
		BestScoreLabel m = new BestScoreLabel(best);
		m.setPosition(200, 940);
		addActor(m);
	}

	public void changeMap() {
		IPreferences p = App.getPreferences();
		int count = p.getInt("count");
		if (count == 3) {
			p.put("count", 4);
		} else {
			p.put("count", 3);
		}
		restart();
	}

}
