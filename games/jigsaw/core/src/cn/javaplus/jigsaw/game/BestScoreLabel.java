package cn.javaplus.jigsaw.game;

import cn.javaplus.jigsaw.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BestScoreLabel extends Actor {
	private static BitmapFont font;
	private long best;

	public BestScoreLabel(long best) {
		this.best = best;
		if (font == null)
			generateFont();
	}

	private void generateFont() {
		FreeTypeFontGenerator generator = App.getAssets().getGenerator();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "betncord:s0.123456789";
		p.size = 32;
		font = generator.generateFont(p);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawFont(batch);
	}

	private void drawFont(Batch batch) {
		String t;
		if (best != 0) {
			t = "best  " + best / 1000 + "." + best % 1000 / 10;
		} else {
			t = "best  no record";
		}
		font.setColor(Color.BLACK);
		font.draw(batch, t, getX(), getY());
		font.setColor(Color.WHITE);
		font.draw(batch, t, getX() + 2, getY() - 2);
	}

}
