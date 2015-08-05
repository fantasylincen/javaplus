package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.IRectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;

public interface IActor extends IRectangle {

	/** Draws the actor. The Batch is configured to draw in the parent's coordinate system.
	 * {@link Batch#draw(com.badlogic.gdx.graphics.g2d.TextureRegion, float, float, float, float, float, float, float, float, float)
	 * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link Batch#begin()} has already been called on
	 * the Batch. If {@link Batch#end()} is called to draw without the Batch then {@link Batch#begin()} must be called before the
	 * method returns.
	 * <p>
	 * The default implementation does nothing.
	 * @param parentAlpha Should be multiplied with the actor's alpha, allowing a parent's alpha to affect all children. */
	public abstract void draw(Batch batch, float parentAlpha);

	/** Updates the actor based on time. Typically this is called each frame by {@link Stage#act(float)}.
	 * <p>
	 * The default implementation calls {@link Action#act(float)} on each action and removes actions that are complete.
	 * @param delta Time in seconds since the last frame. */
	public abstract void act(float delta);

	/** Sets this actor as the event {@link Event#setTarget(Actor) target} and propagates the event to this actor and ancestor
	 * actors as necessary. If this actor is not in the stage, the stage must be set before calling this method.
	 * <p>
	 * Events are fired in 2 phases.
	 * <ol>
	 * <li>The first phase (the "capture" phase) notifies listeners on each actor starting at the root and propagating downward to
	 * (and including) this actor.</li>
	 * <li>The second phase notifies listeners on each actor starting at this actor and, if {@link Event#getBubbles()} is true,
	 * propagating upward to the root.</li>
	 * </ol>
	 * If the event is {@link Event#stop() stopped} at any time, it will not propagate to the next actor.
	 * @return true if the event was {@link Event#cancel() cancelled}. */
	public abstract boolean fire(Event event);

	/** Notifies this actor's listeners of the event. The event is not propagated to any parents. Before notifying the listeners,
	 * this actor is set as the {@link Event#getListenerActor() listener actor}. The event {@link Event#setTarget(Actor) target}
	 * must be set before calling this method. If this actor is not in the stage, the stage must be set before calling this method.
	 * @param capture If true, the capture listeners will be notified instead of the regular listeners.
	 * @return true of the event was {@link Event#cancel() cancelled}. */
	public abstract boolean notify(Event event, boolean capture);

	/** Returns the deepest actor that contains the specified point and is {@link #getTouchable() touchable} and
	 * {@link #isVisible() visible}, or null if no actor was hit. The point is specified in the actor's local coordinate system (0,0
	 * is the bottom left of the actor and width,height is the upper right).
	 * <p>
	 * This method is used to delegate touchDown, mouse, and enter/exit events. If this method returns null, those events will not
	 * occur on this Actor.
	 * <p>
	 * The default implementation returns this actor if the point is within this actor's bounds.
	 * 
	 * @param touchable If true, the hit detection will respect the {@link #setTouchable(Touchable) touchability}.
	 * @see Touchable */
	public abstract IActor hit(float x, float y, boolean touchable);

	/** Removes this actor from its parent, if it has a parent.
	 * @see Group#removeActor(Actor) */
	public abstract boolean remove();

	/** Add a listener to receive events that {@link #hit(float, float, boolean) hit} this actor. See {@link #fire(Event)}.
	 * 
	 * @see InputListener
	 * @see ClickListener */
	public abstract boolean addListener(EventListener listener);

	public abstract boolean removeListener(EventListener listener);

	public abstract Array<EventListener> getListeners();

	/** Adds a listener that is only notified during the capture phase.
	 * @see #fire(Event) */
	public abstract boolean addCaptureListener(EventListener listener);

	public abstract boolean removeCaptureListener(EventListener listener);

	public abstract Array<EventListener> getCaptureListeners();

	public abstract void addAction(Action action);

	public abstract void removeAction(Action action);

	public abstract Array<Action> getActions();

	/** Removes all actions on this actor. */
	public abstract void clearActions();

	/** Removes all listeners on this actor. */
	public abstract void clearListeners();

	/** Removes all actions and listeners on this actor. */
	public abstract void clear();

	/** Returns the stage that this actor is currently in, or null if not in a stage. */
	public abstract IStage getStage();

	/** Returns true if this actor is the same as or is the descendant of the specified actor. */
	public abstract boolean isDescendantOf(IActor actor);

	/** Returns true if this actor is the same as or is the ascendant of the specified actor. */
	public abstract boolean isAscendantOf(IActor actor);

	/** Returns true if the actor's parent is not null. */
	public abstract boolean hasParent();

	/** Returns the parent actor, or null if not in a group. */
	public abstract Group getParent();

	/** Returns true if input events are processed by this actor. */
	public abstract boolean isTouchable();

	public abstract Touchable getTouchable();

	/** Determines how touch events are distributed to this actor. Default is {@link Touchable#enabled}. */
	public abstract void setTouchable(Touchable touchable);

	public abstract boolean isVisible();

	/** If false, the actor will not be drawn and will not receive touch events. Default is true. */
	public abstract void setVisible(boolean visible);

	public abstract Object getUserObject();

	/** Sets an application specific object for convenience. */
	public abstract void setUserObject(Object userObject);

	public abstract float getX();

	public abstract void setX(float x);

	public abstract float getY();

	public abstract void setY(float y);

	/** Sets the x and y. */
	public abstract void setPosition(float x, float y);

	public abstract void translate(float x, float y);

	public abstract float getWidth();

	public abstract void setWidth(float width);

	public abstract float getHeight();

	public abstract void setHeight(float height);

	/** Returns y plus height. */
	public abstract float getTop();

	/** Returns x plus width. */
	public abstract float getRight();

	/** Sets the width and height. */
	public abstract void setSize(float width, float height);

	/** Adds the specified size to the current size. */
	public abstract void size(float size);

	/** Adds the specified size to the current size. */
	public abstract void size(float width, float height);

	/** Set bounds the x, y, width, and height. */
	public abstract void setBounds(float x, float y, float width, float height);

	public abstract float getOriginX();

	public abstract void setOriginX(float originX);

	public abstract float getOriginY();

	public abstract void setOriginY(float originY);

	/** Sets the originx and originy. */
	public abstract void setOrigin(float originX, float originY);

	public abstract float getScaleX();

	public abstract void setScaleX(float scaleX);

	public abstract float getScaleY();

	public abstract void setScaleY(float scaleY);

	/** Sets the scalex and scaley. */
	public abstract void setScale(float scale);

	/** Sets the scalex and scaley. */
	public abstract void setScale(float scaleX, float scaleY);

	/** Adds the specified scale to the current scale. */
	public abstract void scale(float scale);

	/** Adds the specified scale to the current scale. */
	public abstract void scale(float scaleX, float scaleY);

	public abstract float getRotation();

	public abstract void setRotation(float degrees);

	/** Adds the specified rotation to the current rotation. */
	public abstract void rotate(float amountInDegrees);

	public abstract void setColor(Color color);

	public abstract void setColor(float r, float g, float b, float a);

	/** Returns the color the actor will be tinted when drawn. The returned instance can be modified to change the color. */
	public abstract Color getColor();

	public abstract String getName();

	/** Sets a name for easier identification of the actor in application code.
	 * @see Group#findActor(String) */
	public abstract void setName(String name);

	/** Changes the z-order for this actor so it is in front of all siblings. */
	public abstract void toFront();

	/** Changes the z-order for this actor so it is in back of all siblings. */
	public abstract void toBack();

	/** Sets the z-index of this actor. The z-index is the index into the parent's {@link Group#getChildren() children}, where a
	 * lower index is below a higher index. Setting a z-index higher than the number of children will move the child to the front.
	 * Setting a z-index less than zero is invalid. */
	public abstract void setZIndex(int index);

	/** Returns the z-index of this actor.
	 * @see #setZIndex(int) */
	public abstract int getZIndex();

	/** Calls {@link #clipBegin(float, float, float, float)} to clip this actor's bounds. */
	public abstract boolean clipBegin();

	/** Clips the specified screen aligned rectangle, specified relative to the transform matrix of the stage's Batch. The transform
	 * matrix and the stage's camera must not have rotational components. Calling this method must be followed by a call to
	 * {@link #clipEnd()} if true is returned.
	 * @return false if the clipping area is zero and no drawing should occur.
	 * @see ScissorStack */
	public abstract boolean clipBegin(float x, float y, float width,
			float height);

	/** Ends clipping begun by {@link #clipBegin(float, float, float, float)}. */
	public abstract void clipEnd();

	/** Transforms the specified point in screen coordinates to the actor's local coordinate system. */
	public abstract Vector2 screenToLocalCoordinates(Vector2 screenCoords);

	/** Transforms the specified point in the stage's coordinates to the actor's local coordinate system. */
	public abstract Vector2 stageToLocalCoordinates(Vector2 stageCoords);

	/** Transforms the specified point in the actor's coordinates to be in the stage's coordinates.
	 * @see Stage#toScreenCoordinates(Vector2, com.badlogic.gdx.math.Matrix4) */
	public abstract Vector2 localToStageCoordinates(Vector2 localCoords);

	/** Transforms the specified point in the actor's coordinates to be in the parent's coordinates. */
	public abstract Vector2 localToParentCoordinates(Vector2 localCoords);

	/** Converts coordinates for this actor to those of a parent actor. The ascendant does not need to be a direct parent. */
	public abstract Vector2 localToAscendantCoordinates(IActor ascendant,
			Vector2 localCoords);

	/** Converts the coordinates given in the parent's coordinate system to this actor's coordinate system. */
	public abstract Vector2 parentToLocalCoordinates(Vector2 parentCoords);

	public abstract String toString();

	public abstract void setParent(Group group);

	public abstract void setStage(IStage stage);
}