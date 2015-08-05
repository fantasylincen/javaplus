package cn.javaplus.clickscreen.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RankingListItem extends Actor {

	private AtlasRegion label_gray;
	private AtlasRegion label_white;
	private ItemModel item;
	private BitmapFont font;
	private boolean isWhite;

	public RankingListItem(ItemModel item, BitmapFont font, boolean isWhite) {
		this.item = item;
		this.font = font;
		this.isWhite = isWhite;
		if (label_gray == null) {
			TextureAtlas atlas = Assets.getSd().getTextureAtlas("data/robot.txt");
			label_gray = atlas.findRegion("label_gray");
			label_white = atlas.findRegion("label_white");
		}
		int w = label_gray.getRegionWidth();
		int h = label_gray.getRegionHeight();
		setSize(w, h);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		float y = getY();
		float x = getX();
		if (isWhite) {
			batch.draw(label_white, x, y);
			drawFont(batch, y, x);
		} else {
			batch.draw(label_gray, x, y);
			drawFont(batch, y, x);
		}
	}

	private void drawFont(Batch batch, float y, float x) {
		y = y + 33;
		draw(batch, item.getRank(), x + 70, y);
		draw(batch, item.getName(), x + 150, y);
		draw(batch, item.getScore(), x + 700, y);
	}

	private void draw(Batch batch, String str, float x, float y) {
		font.setColor(Color.BLACK);
		font.draw(batch, str, x + 2, y - 2);
		font.setColor(item.getFontColor());
		font.draw(batch, str, x, y);
	}
}
