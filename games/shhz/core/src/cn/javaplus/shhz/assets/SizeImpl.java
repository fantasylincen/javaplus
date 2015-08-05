package cn.javaplus.shhz.assets;

import cn.javaplus.shhz.components.plist.Size;

class SizeImpl implements Size {

	private int w;
	private int h;

	SizeImpl(int w, int h) {
		this.w = w;
		this.h = h;
	}

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}

}
