package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Util;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class CloudGroup extends Actor {
	private AtlasRegion cloud1;
	private AtlasRegion cloud2;
	private AtlasRegion cloud3;
	private Camera camera;
	private float y1 = Util.Random.get(200, 400);
	private float y2 = Util.Random.get(200, 400);
	private float y3 = Util.Random.get(200, 400);
	private float x1 = Util.Random.get(-500, -100);
	private float x2 = Util.Random.get(-100, 300);
	private float x3 = Util.Random.get(300, 600);

	public CloudGroup(Camera camera) {
		this.camera = camera;
		cloud1 = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("cloud1");
		cloud2 = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("cloud2");
		cloud3 = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("cloud3");
		setTouchable(Touchable.disabled);
		setWidth(D.STAGE_W);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		int w = (int) (getWidth() - 20);

		float x = camera.position.x * 0.95f;

		drawCloud(batch, x1 + x - w, y1, cloud1);
		drawCloud(batch, x1 + x, y1, cloud1);
		drawCloud(batch, x1 + x + w, y1, cloud1);

		drawCloud(batch, x2 + x - w, y2, cloud2);
		drawCloud(batch, x2 + x, y2, cloud2);
		drawCloud(batch, x2 + x + w, y2, cloud2);

		drawCloud(batch, x3 + x - w, y3, cloud3);
		drawCloud(batch, x3 + x, y3, cloud3);
		drawCloud(batch, x3 + x + w, y3, cloud3);
	}


	private void drawCloud(Batch batch, float x, float y, AtlasRegion c) {
		batch.draw(c, x, y, c.getRegionWidth() / 2, c.getRegionHeight() / 2);
	}
}
