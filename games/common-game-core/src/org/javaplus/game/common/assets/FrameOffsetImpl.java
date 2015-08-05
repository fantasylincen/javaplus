package org.javaplus.game.common.assets;

import org.javaplus.game.common.components.plist.FrameOffset;

class FrameOffsetImpl implements FrameOffset {

	private int x;
	private int y;

	FrameOffsetImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
}
