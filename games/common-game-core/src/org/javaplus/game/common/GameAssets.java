package org.javaplus.game.common;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class GameAssets {

	private Map<String, FreeTypeFontGenerator> generators;
	private String systemFont;
	private LabelStyle labelStyle1;
	private LabelStyle labelStyle2;

	public GameAssets() {
		generators = new HashMap<String, FreeTypeFontGenerator>();
	}

	public void setSystemFont(String systemFont) {
		this.systemFont = systemFont;
	}

	public FreeTypeFontGenerator getGenerator() {
		if (systemFont == null) {
			throw new NullPointerException();
		}
		return generators.get(systemFont);
	}

	public FreeTypeFontGenerator getGenerator(String generatorName) {
		return generators.get(generatorName);
	}

	public void loadFontInternal(String string) {
		FileHandle fh = Gdx.files.internal(string);
		generators.put(string, new FreeTypeFontGenerator(fh));
	}

	public void loadFontLocal(String string) {
		FileHandle fh = Gdx.files.local(string);
		generators.put(string, new FreeTypeFontGenerator(fh));
	}

	public void unloadFont(String string) {
		generators.remove(string);
	}

	public LabelStyle getLabelStyle1() {
		if (labelStyle1 != null)
			return labelStyle1;
		labelStyle1 = new LabelStyle();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "1234567890./+";
		p.size = 48;
		labelStyle1.font = getGenerator().generateFont(p);
		labelStyle1.fontColor = new Color(Color.BLACK);
		return labelStyle1;
	}

	public LabelStyle getLabelStyle2() {
		if (labelStyle2 != null)
			return labelStyle2;
		labelStyle2 = new LabelStyle();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm,./?;'\\{}[]_+-=)(*&^%$#@!|\":";
		p.size = 48;

		FreeTypeFontGenerator generator = getGenerator("data/Entsani.ttf");
		labelStyle2.font = generator.generateFont(p);
		labelStyle2.fontColor = new Color(Color.WHITE);
		return labelStyle2;
	}

}
