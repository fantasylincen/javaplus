package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RobotActor extends Actor {

	private TextureAtlas atlas;
	private AtlasRegion head;
	private AtlasRegion leg_down;
	private AtlasRegion leg_up;
	private Robot robot;

	public RobotActor(Robot robot) {
		this.robot = robot;
		atlas = Assets.getInternal().getTextureAtlas("data/robot.txt");
		head = atlas.findRegion("head");
		leg_up = atlas.findRegion("leg_up");
		leg_down = atlas.findRegion("leg_down");
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawLegs(batch);
		drawHead(batch);
	}

	private void drawLegs(Batch batch) {
		Leg left = robot.getLeft();
		Leg right = robot.getRight();
		drawLeg(batch, left);
		drawLeg(batch, right);
	}

	private void drawLeg(Batch batch, Leg leg) {
		draw(batch, leg.getUp(), this.leg_up, 6, 3);
		draw(batch, leg.getDown(), this.leg_down, 6, 145);
	}

	private void draw(Batch batch, Body b, AtlasRegion ar, float originX,
			float originY) {
		Vector2 p = b.getPosition();
		float angle = b.getAngle();
		angle = (float) Math.toDegrees(angle);
		float x = p.x * D.BOX_2D_STAGE_SCALE;
		float y = p.y * D.BOX_2D_STAGE_SCALE;
		int width = ar.getRegionWidth();
		int height = ar.getRegionHeight();

		batch.draw(ar, x - originX, y - originY, originX, originY, width,
				height, 1, 1, angle);
	}

	private void drawHead(Batch batch) {
		draw(batch, robot.getHead(), this.head, 30, 25);
	}
}
