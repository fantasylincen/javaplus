package cn.javaplus.clickscreen.game;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HpLabel extends Actor {
	public interface Hpable {

		double getHp();

		float getWidth();

		float getX();

	}

	private static BitmapFont font;
	private String text = "";
	private AtlasRegion blood01;
	private AtlasRegion blood02;
	private double hpMax;
	private Hpable hpable;
	private double hpLast;

	public HpLabel(Hpable hpable) {
		this.hpable = hpable;
		if (font == null)
			generateFont();

		hpMax = hpable.getHp();

		Loader ext = Assets.getSd();
		TextureAtlas ats = ext.getTextureAtlas("data/game.txt");
		blood01 = ats.findRegion("menublood1");
		blood02 = ats.findRegion("menublood2");

		setSize(blood01.getRegionWidth(), blood01.getRegionHeight());

		setScale(0.3f);
		setPosition(-5, -20);
	}

	private void generateFont() {
		FreeTypeFontGenerator generator = App.getAssets().getGenerator();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "0.123456789KMGTPEZYe";
		p.size = 32;
		font = generator.generateFont(p);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawFont(batch);
	}

	private void drawFont(Batch batch) {
		updateText();
		float w = getWidth();
		float h = getHeight();
		float s = (float) ((hpMax - hpable.getHp()) / hpMax);
		batch.draw(blood01, getX(), getY(), 0, 0, w, h, getScaleX(),
				getScaleY(), 0);
		float sx = s * getScaleX();
		batch.draw(blood02, getX() - w + w * getScaleX(), getY(), w, 0, w, h,
				sx, getScaleY(), 0);

		font.setColor(Color.BLACK);
		font.draw(batch, text, getX(), getY());
		font.setColor(Color.WHITE);
		font.draw(batch, text, getX() + 2, getY() - 2);
	}

	private void updateText() {
		double hp = hpable.getHp();
		if (hp != hpLast) {
			hpLast = hp;
			text = Util.Math.getFormatText(hp);
		}
	}
}
