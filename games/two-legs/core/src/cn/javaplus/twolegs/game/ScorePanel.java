package cn.javaplus.twolegs.game;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ScorePanel extends Label {

	private Robot robot;
//	private float bestScore;

	public ScorePanel(Robot robot) {
		super("", createStyle());
//		bestScore = getBest();

		this.robot = robot;

		setSize(100, 50);
		setPosition(-D.STAGE_W / 2 + 40, D.STAGE_H / 2 - getHeight() - 350);

	}

	static LabelStyle createStyle() {
		LabelStyle l = new LabelStyle();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "1234567890.";
		p.size = 48;
		l.font = App.getAssets().getGenerator().generateFont(p);
		l.fontColor = new Color(Color.BLACK);
		return l;
	}

//	private float getBest() {
//
//		IPreferences p = App.getApp().getPreferences();
//		String score = p.getString("best-score");
//		if (score == null || score.isEmpty()) {
//			return 0;
//		} else {
//			return new Float(score);
//		}
//	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!robot.isDeath()) {
			setText(robot.getScoreText() /*+ " m"*/);
//			if (bestScore > 10) {
//				float scale = robot.getScore() / bestScore;
//				getStyle().fontColor.set(0, scale, 0, 1);
//			}
		}
	}
}
