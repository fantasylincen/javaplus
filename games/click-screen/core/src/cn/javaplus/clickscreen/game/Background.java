package cn.javaplus.clickscreen.game;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Background extends Group {
	private TextureRegion region1;
	private TextureRegion region2;
	private TextureRegion region3;

	public Background() {
		Loader e = Assets.getSd();
		region1 = e.findRegion("data/game.txt", "grass");
		region2 = e.findRegion("data/game.txt", "dirt");
		region3 = e.findRegion("data/game.txt", "sand");
	}

	public void add() {
		int y = 0;
		for (int row = 0; row <= 6; row++) {
			addImage(row, y);
			y += 96;
		}
	}

	private void addImage(int row, int y) {
		int x = 0;
		for (int i = 0; i <= 11; i++) {
			addImage(getRegion(i), x, y);
			x += 96;
		}
	}

	private void addImage(TextureRegion region, int x, int y) {
		Image i = new Image(region);
		i.setPosition(x, y);
		addActor(i);
	}

	private TextureRegion getRegion(int i) {
		if (i < 5) {
			return region1;
		}
		if (i < 7) {
			return region2;
		}
		return region3;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(getColor());
		int y = 0;
		for (int row = 0; row <= 6; row++) {
			drawRow(batch, row, y);
			y += 96;
		}
	}

	private void drawRow(Batch batch, int row, int y) {
		int x = 0;
		for (int i = 0; i <= 11; i++) {
			batch.draw(getRegion(i), x, y);
			x += 96;
		}
	}

}
