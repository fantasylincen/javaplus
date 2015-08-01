package org.hhhhhh.fqzs.result;

import org.javaplus.clickscreen.special.Effect;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.animation.PlayOverCallBack;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Space;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class AnimalEffect extends Effect {

	private static final float PLAY_TIME = 1.5f;

	private float vy;
	private float vx;
	private static float g = -3.8f;

	public class MoveSelf implements Runnable {

		@Override
		public void run() {
			setPosition(getX() + vx, getY() + vy);
			vy += g;
		}

	}
	
	public class RemoveSelf implements PlayOverCallBack {

		@Override
		public void onPlayOver() {
			remove();
		}

	}

	public AnimalEffect() {
		super(AnimationCreator.create(PLAY_TIME, getRegions()));
		animation.playOnce(new RemoveSelf());
		gravitySpace.addAction(new MoveSelf());
		vx = Util.Random.get(-9, 9);
		vy = Util.Random.get(35, 45);

		addActions();
	}

	private void addActions() {
		setScale(0.3f);
		float dt = PLAY_TIME / 3;

		SequenceAction s = new SequenceAction();

		ScaleToAction b = new ScaleToAction();
		b.setScale(0.8f);
		b.setDuration(dt * 1);
		s.addAction(b);

		DelayAction d = new DelayAction();
		d.setDuration(dt * 1);
		s.addAction(d);

		AlphaAction a = new AlphaAction();
		a.setAlpha(0);
		a.setDuration(dt * 1);
		s.addAction(a);

		addAction(s);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		animation.draw(batch, parentAlpha, this);
	}

	Space gravitySpace = new Space(0.05);

	@Override
	public void act(float delta) {
		super.act(delta);
		gravitySpace.update(delta);
	}

	private static Array<TextureRegion> getRegions() {
		Array<TextureRegion> regions;
		Texture texture = Assets.getDefaultLoader().getTexture("data/coin-sprite-sheet.png");
		regions = new Array<TextureRegion>();
		int x = 0;
		for (int i = 0; i < 10; i++) {
			x += 44;
			TextureRegion t = new TextureRegion(texture, x, 0, 44, 40);
			regions.add(t);
		}
		return regions;
	}
}
