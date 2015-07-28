package cn.javaplus.twolegs.game;

import org.javaplus.game.common.IPreferences;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BestScorePanel extends Label {

	private String best;
	private float bestScore;
	private Robot robot;
	private ScorePanel scorePanel;

	public BestScorePanel(Robot robot, ScorePanel scorePanel) {
		super("", createStyle());
		this.robot = robot;
		this.scorePanel = scorePanel;
		setSize(500, 100);
		setPosition(-D.STAGE_W / 2 + 80, D.STAGE_H / 2 - getHeight() - 400);

		IPreferences store = App.getPreferences();

		best = store.getString("best-score");

		if (best.isEmpty()) {
			best = "0.00";
		}

		bestScore = new Float(best);
		setText(best);

	}

	static LabelStyle createStyle() {
		LabelStyle l = new LabelStyle();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "1234567890.";
		p.size = 24;
		l.font = App.getAssets().getGenerator().generateFont(p);
		l.fontColor = new Color(Color.BLACK);
		return l;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (robot.getScore() > bestScore) {
			setText(scorePanel.getText());
		}
	}

}
