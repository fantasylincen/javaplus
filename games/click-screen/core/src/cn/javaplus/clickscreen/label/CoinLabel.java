package cn.javaplus.clickscreen.label;

import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class CoinLabel extends Actor {

	private static BitmapFont font;

	public CoinLabel() {
		if (font == null)
			generateFont();
		setTouchable(Touchable.disabled);
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
		font.setColor(Color.WHITE);
		font.draw(batch, getText(), getX(), getY());
	}

	private String getText() {
		double v = App.getDb().getCoin();
		return Util.Math.getFormatText(v);
	}
}
