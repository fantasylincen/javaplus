package org.javaplus.game.common.components.plist;

public interface Frame {

	public abstract String getKey();

	public abstract FrameRect getRect();

	public abstract FrameOffset getOffset();

	public abstract boolean isRotated();

	public abstract FrameRect getSourceColorRect();

	public abstract Size getSourceSize();

}