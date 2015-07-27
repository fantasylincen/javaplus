package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class SignGroup extends Actor {

	private AtlasRegion region;
	private Camera camera;
	private Robot robot;
	private float xStart;
	private float xStartCamera;
	private BitmapFont font;

	public SignGroup(Camera camera, Robot robot) {
		this.camera = camera;
		this.robot = robot;
		region = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("sign");
		setSize(region.getRegionWidth(), region.getRegionHeight());
		setTouchable(Touchable.disabled);

		xStart = robot.getPosition().x;
		xStartCamera = camera.position.x;

		font = Assets.getInternal().getBitmapFont("data/font.fnt");
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		float x = robot.getPosition().x - xStart;
		int robotMovePix = (int) (x * D.BOX_2D_STAGE_SCALE); // 机器人走了多少个像素
		draw(robotMovePix, -1, batch);
		draw(robotMovePix, 0, batch);
		draw(robotMovePix, 1, batch);
	}

	
	private void draw(int robotMovePix, int i, Batch batch) {
		int S = 10;
		int m = (int) (robotMovePix / (D.BOX_2D_STAGE_SCALE * S));
		m += i;
		m *= S;
		float x = m * D.BOX_2D_STAGE_SCALE;
		// Log.d("m = " + m);
		if (m != 0) {
			font.draw(batch, m + "", x + 210, 90);
			batch.draw(region, x + 150, 0, getWidth(), getHeight());
		}
	}

}
