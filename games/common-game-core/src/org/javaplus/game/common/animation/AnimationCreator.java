package org.javaplus.game.common.animation;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationCreator {

	private static Loader loader = Assets.getInternal();

	public static void setLoader(Loader loader) {
		AnimationCreator.loader = loader;
	}

	private AnimationCreator() {
	}

	/**
	 * @param dt
	 *            动画播放完所需时间
	 * @param animations
	 * @return
	 */
	public static GameAnimation create(String assetsPath, double dt,
			String... animations) {
		TextureAtlas atlas = loader.getTextureAtlas(assetsPath);
		Array<TextureRegion> ts = getAtlas(atlas, animations);
		return create(dt, ts);
	}

	public static GameAnimation create(double dt, Array<TextureRegion> ts) {
		float t = (float) (dt / ts.size);
		Animation animation = new Animation(t, ts);
		GameAnimation gameAnimation = new GameAnimation(animation);
		return gameAnimation;
	}

	private static Array<TextureRegion> getAtlas(TextureAtlas atlas,
			String[] animations) {
		Array<TextureRegion> ls = new Array<TextureRegion>();
		for (String a : animations) {
			ls.add(atlas.findRegion(a));
		}
		return ls;
	}

}
