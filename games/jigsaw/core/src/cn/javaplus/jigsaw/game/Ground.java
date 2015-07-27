package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Ground extends Actor {
	private AtlasRegion region;
	private Camera camera;

	public Ground(Camera camera) {
		this.camera = camera;
		region = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("ground");
		setSize(region.getRegionWidth(), region.getRegionHeight());
		setTouchable(Touchable.disabled);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		int w = (int) (getWidth() - 1);
		int k = ((int) camera.position.x) / w;

		int x = k * w;

		drawGround(batch, x - 2 * w);
		drawGround(batch, x - w);
		drawGround(batch, x);
		drawGround(batch, x + w);
		drawGround(batch, x + 2 * w);
	}

	private void drawGround(Batch batch, int x) {
		batch.draw(region, x, getY() - 76, getWidth(), getHeight());
	}
}
