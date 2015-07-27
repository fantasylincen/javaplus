package org.javaplus.game.common.assets;

import org.javaplus.game.common.components.plist.FrameRect;

class FrameRectImpl implements FrameRect {

	private int x;
	private int y;
	private int w;
	private int h;

	FrameRectImpl(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getW() {
		return w;
	}

	@Override
	public int getH() {
		return h;
	}

}
