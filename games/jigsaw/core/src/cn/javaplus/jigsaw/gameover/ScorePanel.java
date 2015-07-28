package cn.javaplus.jigsaw.gameover;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.jigsaw.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScorePanel extends Actor {
	
	private static final int DX = 120;
	private static final int DY = 400;

	private AtlasRegion score;

	private static BitmapFont font;

	private String time;

	private boolean isShowFont;

	public ScorePanel(long time) {
		this.time = "time  " + time / 1000 + "." + time % 1000 / 10+ "s";
		score = Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("scorepanel");
		setSize(score.getRegionWidth(), score.getRegionHeight());

		if (font == null)
			generateFont();
		
	}

	private void generateFont() {
		FreeTypeFontGenerator generator = App.getAssets().getGenerator();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = ":0.123456789stime";
		p.size = 64;
		font = generator.generateFont(p);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor());
		draw(batch, score, DX, DY);
		if (isShowFont)
			drawFont(batch);
	}

	private void drawFont(Batch batch) {
		font.setColor(Color.BLACK);
		int dx = 60;
		int dy = 370;
		font.draw(batch, time, getX() + DX + 2 +dx, getY() + DY - 2 + dy);
		font.setColor(Color.WHITE);
		font.draw(batch, time, getX() + DX + dx, getY() + DY + dy);
	}

	private void draw(Batch batch, AtlasRegion score, int x, int y) {
		batch.draw(score, getX() + x, getY() + y, 0, 0, score.getRegionWidth(),
				score.getRegionHeight(), getScaleX(), getScaleY(), 0);
	}

	public void showScore() {

		this.isShowFont = true;
	}

}
