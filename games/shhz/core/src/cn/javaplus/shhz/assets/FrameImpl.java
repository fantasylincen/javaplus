package cn.javaplus.shhz.assets;

import cn.javaplus.shhz.components.plist.Frame;
import cn.javaplus.shhz.components.plist.FrameOffset;
import cn.javaplus.shhz.components.plist.FrameRect;
import cn.javaplus.shhz.components.plist.Size;

class FrameImpl implements Frame {

	private String key;
	private FrameRect rect;
	private FrameOffset offset;
	private boolean isRotated;
	private FrameRect sourceColorRect;
	private Size sourceSize;

	FrameImpl(String key, FrameRect rect, FrameOffset offset,
			boolean isRotated, FrameRect sourceColorRect, Size sourceSize) {
		this.key = key;
		this.rect = rect;
		this.offset = offset;
		this.isRotated = isRotated;
		this.sourceColorRect = sourceColorRect;
		this.sourceSize = sourceSize;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public FrameRect getRect() {
		return rect;
	}

	@Override
	public FrameOffset getOffset() {
		return offset;
	}

	@Override
	public boolean isRotated() {
		return isRotated;
	}

	@Override
	public FrameRect getSourceColorRect() {
		return sourceColorRect;
	}

	@Override
	public Size getSourceSize() {
		return sourceSize;
	}

}
