package org.hhhhhh.fqzs.core;

import org.javaplus.clickscreen.button.GameButton;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Sets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FqzsButton extends GameButton {

	private String id;

	public FqzsButton(String id) {
		super(getTexture(id));
		this.id = id;

		GameAssets s = App.getAssets();
		FreeTypeFontGenerator g = s.getGenerator();
		FreeTypeFontParameter data = new FreeTypeFontParameter();
		data.color = Color.WHITE;
		data.size = 32;
		data.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		font = g.generateFont(data);
		setValue(0);

		if (id.equals("qx") || id.equals("xy"))
			setText(null);
		if (id.equals("qh")) {
			String firstText = App.getProperties().get("qh").split(",")[0];
			setValue(new Integer(firstText));
		}
	}

	private static AtlasRegion getTexture(String id) {
		TextureAtlas ats = Assets.getDefaultLoader().getTextureAtlas("data/game.txt");
		return ats.findRegion(id);
	}

	public String getId() {
		return id;
	}

	public int getValue() {
		return value;
	}

	int value;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (Sets.newHashSet("qx", "xy").contains(id)) {
			return;
		}
	}

	public void addValue(int vv) {
		setValue(value + vv);
		int MAX_VALUE = App.getProperties().getInt("MAX_VALUE");
		if (value > MAX_VALUE) {
			setValue(MAX_VALUE);
		}
	}

	public void setValue(int value) {
		this.value = value;
		setText(value + "");
	}

}
