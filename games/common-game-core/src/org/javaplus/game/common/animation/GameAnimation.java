package org.javaplus.game.common.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameAnimation {

	private Animation animation;
	private float stateTime;

	// private int id;
	//
	// private static int id2 = 0;

	public GameAnimation(Animation animation) {
		this.animation = animation;
		// this.id = id2++;
	}

	public void loop() {
		animation.setPlayMode(PlayMode.LOOP);
		isShow = true;
	}

	PlayOverCallBack callBack;
	private boolean isShow;

	public void playOnce(PlayOverCallBack callBack) {
		stateTime = 0;
		animation.setPlayMode(PlayMode.NORMAL);
		this.callBack = callBack;
		isShow = true;
	}

	public void draw(Batch batch, float parentAlpha, Actor target) {
		if (!isShow)
			return;
		float rotation = target.getRotation();

		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion r = animation.getKeyFrame(stateTime);

		float x = target.getX();
		float y = target.getY();
		float w = r.getRegionWidth();
		float h = r.getRegionHeight();
		float ox = w / 2;
		float oy = h / 2;
		float sx = target.getScaleX();
		float sy = target.getScaleY();

		batch.setColor(target.getColor());

		batch.draw(r, x - ox, y - oy, ox, oy, w, h, sx, sy, rotation);

		if (callBack == null) {
			return;
		}
		if (animation.isAnimationFinished(stateTime)) {
			callBack.onPlayOver();
			callBack = null;
			isShow = false;
		}
	}

}
