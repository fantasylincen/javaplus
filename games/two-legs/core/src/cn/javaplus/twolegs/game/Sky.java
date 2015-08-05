package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Sky extends Actor {
	private AtlasRegion region;
	private Camera camera;

	public Sky(Camera camera) {
		this.camera = camera;
		setTouchable(Touchable.disabled);
		region = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("sky");
		setSize(D.STAGE_W, D.STAGE_H);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Vector3 position = camera.position;
		batch.draw(region, position.x - D.STAGE_W / 2, position.y - D.STAGE_H
				/ 2, getWidth(), getHeight());
	}
}
