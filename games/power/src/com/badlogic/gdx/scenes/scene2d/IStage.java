package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public interface IStage extends Disposable, InputProcessor {

	/** Sets up the stage size using a viewport that fills the entire screen without keeping the aspect ratio.
	 * @see #setViewport(float, float, boolean, float, float, float, float) */
	public abstract void setViewport(float width, float height);

	/** Sets up the stage size using a viewport that fills the entire screen.
	 * @see #setViewport(float, float, boolean, float, float, float, float) */
	public abstract void setViewport(float width, float height,
			boolean keepAspectRatio);

	/** Sets up the stage size and viewport. The viewport is the glViewport position and size, which is the portion of the screen
	 * used by the stage. The stage size determines the units used within the stage, depending on keepAspectRatio:
	 * <p>
	 * If keepAspectRatio is false, the stage is stretched to fill the viewport, which may distort the aspect ratio.
	 * <p>
	 * If keepAspectRatio is true, the stage is first scaled to fit the viewport in the longest dimension. Next the shorter
	 * dimension is lengthened to fill the viewport, which keeps the aspect ratio from changing. The {@link #getGutterWidth()} and
	 * {@link #getGutterHeight()} provide access to the amount that was lengthened.
	 * @param viewportX The top left corner of the viewport in glViewport coordinates (the origin is bottom left).
	 * @param viewportY The top left corner of the viewport in glViewport coordinates (the origin is bottom left).
	 * @param viewportWidth The width of the viewport in pixels.
	 * @param viewportHeight The height of the viewport in pixels. */
	public abstract void setViewport(float stageWidth, float stageHeight,
			boolean keepAspectRatio, float viewportX, float viewportY,
			float viewportWidth, float viewportHeight);

	public abstract void draw();

	/** Calls {@link #act(float)} with {@link Graphics#getDeltaTime()}. */
	public abstract void act();

	/** Calls the {@link Actor#act(float)} method on each actor in the stage. Typically called each frame. This method also fires
	 * enter and exit events.
	 * @param delta Time in seconds since the last frame. */
	public abstract void act(float delta);

	/** Applies a touch down event to the stage and returns true if an actor in the scene {@link Event#handle() handled} the event. */
	public abstract boolean touchDown(int screenX, int screenY, int pointer,
			int button);

	/** Applies a touch moved event to the stage and returns true if an actor in the scene {@link Event#handle() handled} the event.
	 * Only {@link InputListener listeners} that returned true for touchDown will receive this event. */
	public abstract boolean touchDragged(int screenX, int screenY, int pointer);

	/** Applies a touch up event to the stage and returns true if an actor in the scene {@link Event#handle() handled} the event.
	 * Only {@link InputListener listeners} that returned true for touchDown will receive this event. */
	public abstract boolean touchUp(int screenX, int screenY, int pointer,
			int button);

	/** Applies a mouse moved event to the stage and returns true if an actor in the scene {@link Event#handle() handled} the event.
	 * This event only occurs on the desktop. */
	public abstract boolean mouseMoved(int screenX, int screenY);

	/** Applies a mouse scroll event to the stage and returns true if an actor in the scene {@link Event#handle() handled} the
	 * event. This event only occurs on the desktop. */
	public abstract boolean scrolled(int amount);

	/** Applies a key down event to the actor that has {@link Stage#setKeyboardFocus(Actor) keyboard focus}, if any, and returns
	 * true if the event was {@link Event#handle() handled}. */
	public abstract boolean keyDown(int keyCode);

	/** Applies a key up event to the actor that has {@link Stage#setKeyboardFocus(Actor) keyboard focus}, if any, and returns true
	 * if the event was {@link Event#handle() handled}. */
	public abstract boolean keyUp(int keyCode);

	/** Applies a key typed event to the actor that has {@link Stage#setKeyboardFocus(Actor) keyboard focus}, if any, and returns
	 * true if the event was {@link Event#handle() handled}. */
	public abstract boolean keyTyped(char character);

	/** Adds the listener to be notified for all touchDragged and touchUp events for the specified pointer and button. The actor
	 * will be used as the {@link Event#getListenerActor() listener actor} and {@link Event#getTarget() target}. */
	public abstract void addTouchFocus(EventListener listener,
			IActor listenerActor, IActor target, int pointer, int button);

	/** Removes the listener from being notified for all touchDragged and touchUp events for the specified pointer and button. Note
	 * the listener may never receive a touchUp event if this method is used. */
	public abstract void removeTouchFocus(EventListener listener,
			IActor listenerActor, IActor target, int pointer, int button);

	/** Sends a touchUp event to all listeners that are registered to receive touchDragged and touchUp events and removes their
	 * touch focus. This method removes all touch focus listeners, but sends a touchUp event so that the state of the listeners
	 * remains consistent (listeners typically expect to receive touchUp eventually). The location of the touchUp is
	 * {@link Integer#MIN_VALUE}. Listeners can use {@link InputEvent#isTouchFocusCancel()} to ignore this event if needed. */
	public abstract void cancelTouchFocus();

	/** Cancels touch focus for all listeners except the specified listener.
	 * @see #cancelTouchFocus() */
	public abstract void cancelTouchFocus(EventListener listener, IActor actor);

	/** Adds an actor to the root of the stage.
	 * @see Group#addActor(Actor)
	 * @see Actor#remove() */
	public abstract void addActor(IActor actor);

	/** Adds an action to the root of the stage.
	 * @see Group#addAction(Action) */
	public abstract void addAction(Action action);

	/** Returns the root's child actors.
	 * @see Group#getChildren() */
	public abstract Array<IActor> getActors();

	/** Adds a listener to the root.
	 * @see Actor#addListener(EventListener) */
	public abstract boolean addListener(EventListener listener);

	/** Removes a listener from the root.
	 * @see Actor#removeListener(EventListener) */
	public abstract boolean removeListener(EventListener listener);

	/** Adds a capture listener to the root.
	 * @see Actor#addCaptureListener(EventListener) */
	public abstract boolean addCaptureListener(EventListener listener);

	/** Removes a listener from the root.
	 * @see Actor#removeCaptureListener(EventListener) */
	public abstract boolean removeCaptureListener(EventListener listener);

	/** Removes the root's children, actions, and listeners. */
	public abstract void clear();

	/** Removes the touch, keyboard, and scroll focused actors. */
	public abstract void unfocusAll();

	/** Removes the touch, keyboard, and scroll focus for the specified actor and any descendants. */
	public abstract void unfocus(IActor actor);

	/** Sets the actor that will receive key events.
	 * @param actor May be null. */
	public abstract void setKeyboardFocus(IActor actor);

	/** Gets the actor that will receive key events.
	 * @return May be null. */
	public abstract IActor getKeyboardFocus();

	/** Sets the actor that will receive scroll events.
	 * @param actor May be null. */
	public abstract void setScrollFocus(IActor actor);

	/** Gets the actor that will receive scroll events.
	 * @return May be null. */
	public abstract IActor getScrollFocus();

	/** The width of the stage's viewport.
	 * @see #setViewport(float, float, boolean) */
	public abstract float getWidth();

	/** The height of the stage's viewport.
	 * @see #setViewport(float, float, boolean) */
	public abstract float getHeight();

	/** Half the amount in the x direction that the stage's viewport was lengthened to fill the screen.
	 * @see #setViewport(float, float, boolean) */
	public abstract float getGutterWidth();

	/** Half the amount in the y direction that the stage's viewport was lengthened to fill the screen.
	 * @see #setViewport(float, float, boolean) */
	public abstract float getGutterHeight();

	public abstract Batch getSpriteBatch();

	public abstract ICamera getCamera();

	/** Sets the stage's camera. The camera must be configured properly or {@link #setViewport(float, float, boolean)} can be called
	 * after the camera is set. {@link Stage#draw()} will call {@link Camera#update()} and use the {@link Camera#combined} matrix
	 * for the Batch {@link Batch#setProjectionMatrix(com.badlogic.gdx.math.Matrix4) projection matrix}. */
	public abstract void setCamera(ICamera camera);

	/** Returns the root group which holds all actors in the stage. */
	public abstract IGroup getRoot();

	/** Returns the {@link Actor} at the specified location in stage coordinates. Hit testing is performed in the order the actors
	 * were inserted into the stage, last inserted actors being tested first. To get stage coordinates from screen coordinates, use
	 * {@link #screenToStageCoordinates(Vector2)}.
	 * @param touchable If true, the hit detection will respect the {@link Actor#setTouchable(Touchable) touchability}.
	 * @return May be null if no actor was hit. */
	public abstract IActor hit(float stageX, float stageY, boolean touchable);

	/** Transforms the screen coordinates to stage coordinates.
	 * @param screenCoords Input screen coordinates and output for resulting stage coordinates. */
	public abstract Vector2 screenToStageCoordinates(Vector2 screenCoords);

	/** Transforms the stage coordinates to screen coordinates.
	 * @param stageCoords Input stage coordinates and output for resulting screen coordinates. */
	public abstract Vector2 stageToScreenCoordinates(Vector2 stageCoords);

	/** Transforms the coordinates to screen coordinates. The coordinates can be anywhere in the stage since the transform matrix
	 * describes how to convert them. The transform matrix is typically obtained from {@link Batch#getTransformMatrix()} during
	 * {@link Actor#draw(Batch, float)}.
	 * @see Actor#localToStageCoordinates(Vector2) */
	public abstract Vector2 toScreenCoordinates(Vector2 coords,
			Matrix4 transformMatrix);

	public abstract void calculateScissors(Rectangle area, Rectangle scissor);

	public abstract void dispose();

}