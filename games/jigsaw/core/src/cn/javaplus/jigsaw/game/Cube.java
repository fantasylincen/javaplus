package cn.javaplus.jigsaw.game;

import java.util.List;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Lists;

import cn.javaplus.jigsaw.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Cube extends Image {

	private static List<Color> colors;
	private int number;
	private static FreeTypeFontGenerator generator;
	private static BitmapFont font;
	private int index;
	private CubeBox box;
	private static AtlasRegion texture;

	public Cube(int number, float cubeWidth) {
		super(getTexture());
		this.number = number;
		setSize(cubeWidth, cubeWidth);
		if (number == 0) {
			setColor(Color.BLACK);
			setVisible(false);
		}
	}

	private static AtlasRegion getTexture() {
		if (texture == null) {
			texture = Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("0");
			init();
			createFont();
		}
		return texture;
	}

	private static void createFont() {
		generator = App.getAssets().getGenerator();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "1234567890.";
		p.size = 56;
		font = generator.generateFont(p);
	}

	public int getNumber() {
		return number;
	}

	private static void init() {
		colors = Lists.newArrayList();
		colors.add(create(0.5, 0, 0));
		colors.add(create(0, 0.5, 0));
		colors.add(create(0, 0, 0.5));
		colors.add(create(0.5, 0.5, 0));
		colors.add(create(0, 0.5, 0.5));
		colors.add(create(0.5, 0, 0.5));

		colors.add(create(1, 0, 0));
		colors.add(create(0, 1, 0));
		colors.add(create(0, 0, 1));
		colors.add(create(1, 1, 0));
		colors.add(create(0, 1, 1));
		colors.add(create(1, 0, 1));

		colors.add(create(1, 0.5, 0));
		colors.add(create(0, 0.5, 1));
		colors.add(create(0.5, 0, 1));

		colors.add(create(0.5, 1, 0));
		colors.add(create(0, 1, 0.5));
		colors.add(create(1, 0, 0.5));
	}

	private static Color create(double r, double g, double b) {
		return new Color((float) r, (float) g, (float) b, 1);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (number != 0) {
			float x = getX() + getWidth() / 2 - 18;
			float y = getY() + getHeight() / 2 + 10;
			
			if(number >= 10 ) {
				x -= 10;
			}
			
			font.setColor(Color.BLACK);
			font.draw(batch, number + "", x + 2, y - 2);
			font.setColor(Color.WHITE);
			font.draw(batch, number + "", x, y);
		}
		// setColor(Color.WHITE);
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

//	public boolean isBehind(Cube b) {
//		int y = (int) getY();
//		int yb = (int) b.getY();
//		if (y == yb) {
//			float x = getX();
//			float xb = b.getX();
//			return x > xb;
//		}
//		return y < yb;
//
//	}

	@Override
	public String toString() {
		return number + "";
	}

	public CubeBox getBox() {
		return box;
	}

	public void setBox(CubeBox box) {
		this.box = box;
	}
}
