package org.javaplus.game.common.actor;

import org.javaplus.game.common.animation.AnimationFrame;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.components.plist.Frame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFrameImpl implements AnimationFrame {

	private static final boolean IS_SHOW_ACTOR_TEXTURE_REGION_BACKGROUND = false;
	private TextureRegion region;
	private Frame frame;

	private TextureRegion background;
	
	private Loader loader = Assets.getInternal();

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public AnimationFrameImpl(TextureRegion region, Frame frame) {
		this.region = region;
		this.frame = frame;

		if (IS_SHOW_ACTOR_TEXTURE_REGION_BACKGROUND) {
			background = new TextureRegion(
					loader.getTexture("greenbackground.png"),
					region.getRegionX(), region.getRegionY(),
					region.getRegionWidth(), region.getRegionHeight());
		}
	}

	@Override
	public Frame getFrameConfig() {
		return frame;
	}

	public TextureRegion getTextureRegion() {
		return region;
	}

	public TextureRegion getTextureRegionBackground() {
		return background;
	}

	@Override
	public int getHeight() {
		return region.getRegionHeight();
	}

	@Override
	public int getWidth() {
		return region.getRegionWidth();
	}

	@Override
	public boolean isRotated() {
		return frame.isRotated();
	}
}
