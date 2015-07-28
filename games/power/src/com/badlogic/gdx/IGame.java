package com.badlogic.gdx;

public interface IGame {

	public abstract void dispose();

	public abstract void pause();

	public abstract void resume();

	public abstract void render();

	public abstract void resize(int width, int height);

	/** Sets the current screen. {@link Screen#hide()} is called on any old screen, and {@link Screen#show()} is called on the new
	 * screen, if any.
	 * @param screen may be {@code null}
	 */
	public abstract void setScreen(Screen screen);

	/** @return the currently active {@link Screen}. */
	public abstract Screen getScreen();

}