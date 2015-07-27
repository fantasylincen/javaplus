package org.javaplus.game.common.animation;

import org.javaplus.game.common.components.plist.Frame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface AnimationFrame {

	/**
	 * 帧高
	 */
	int getHeight();

	/**
	 * 帧宽
	 */
	int getWidth();

	/**
	 * 是否旋转
	 */
	boolean isRotated();

	TextureRegion getTextureRegion();

	Frame getFrameConfig();

	TextureRegion getTextureRegionBackground();

}
