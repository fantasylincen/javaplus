package cn.javaplus.shhz.listeners.background;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.animation.AnimationFrame;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.components.plist.Frame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFrameImpl implements AnimationFrame {

	private TextureRegion region;
	private Frame frame;

	private TextureRegion background;

	public AnimationFrameImpl(TextureRegion region, Frame frame) {
		this.region = region;
		this.frame = frame;

		if (D.IS_SHOW_ACTOR_TEXTURE_REGION_BACKGROUND) {
			background = new TextureRegion(
					Assets.getTexture("greenbackground.png"),
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
