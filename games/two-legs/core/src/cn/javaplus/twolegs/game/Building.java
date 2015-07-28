package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Building extends Actor {
	private AtlasRegion region;
	private Camera camera;

	public Building(Camera camera) {
		this.camera = camera;
		region = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("building");
		setSize(region.getRegionWidth(), region.getRegionHeight());
		setTouchable(Touchable.disabled);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		int w = (int) (getWidth() - 20);
//		int k = ((int) camera.position.x) / w;
//
//		int x = k * w;
		
		float x = camera.position.x  * 0.9f;

		drawBuilding(batch, x - w);
		drawBuilding(batch, x);
		drawBuilding(batch, x + w);
	}

	private void drawBuilding(Batch batch, float x) {
		batch.draw(region, x, getY() , getWidth(), getHeight());
	}
}
