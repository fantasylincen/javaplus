/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pools;

/** 2D scene graph node. An actor has a position, rectangular size, origin, scale, rotation, Z index, and color. The position
 * corresponds to the unrotated, unscaled bottom left corner of the actor. The position is relative to the actor's parent. The
 * origin is relative to the position and is used for scale and rotation.
 * <p>
 * An actor has a list of in-progress {@link Action actions} that are applied to the actor (over time). These are generally used to
 * change the presentation of the actor (moving it, resizing it, etc). See {@link #act(float)} and {@link Action}.
 * <p>
 * An actor has two kinds of listeners associated with it: "capture" and regular. The listeners are notified of events the actor
 * or its children receive. The capture listeners are designed to allow a parent or container actor to hide events from child
 * actors. The regular listeners are designed to allow an actor to respond to events that have been delivered. See {@link #fire}
 * for more details.
 * <p>
 * An {@link InputListener} can receive all the basic input events, and more complex listeners (like {@link ClickListener} and
 * {@link ActorGestureListener}) can listen for and combine primitive events and recognize complex interactions like multi-click
 * or pinch.
 * 
 * @author mzechner
 * @author Nathan Sweet */

//重构此类时， 将所有字段全设置为私有的， 
//将该类的所有.parent 替换成.getParent
public class Actor implements IActor {
	private IStage stage;
	private Group parent;
	private final DelayedRemovalArray<EventListener> listeners = new DelayedRemovalArray(0);
	private final DelayedRemovalArray<EventListener> captureListeners = new DelayedRemovalArray(0);
	private final Array<Action> actions = new Array(0);

	private String name;
	private Touchable touchable = Touchable.enabled;
	private boolean visible = true;
//	float x, y;
	float width, height;
	float originX, originY;
	float scaleX = 1, scaleY = 1;
	float rotation;
	final Color color = new Color(1, 1, 1, 1);
	private Object userObject;
	private float x;
	private float y;

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	@Override
	public void draw (Batch batch, float parentAlpha) {
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#act(float)
	 */
	@Override
	public void act (float delta) {
		for (int i = 0; i < actions.size; i++) {
			Action action = actions.get(i);
			if (action.act(delta) && i < actions.size) {
				actions.removeIndex(i);
				action.setActor(null);
				i--;
			}
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#fire(com.badlogic.gdx.scenes.scene2d.Event)
	 */
	@Override
	public boolean fire (Event event) {
		if (event.getStage() == null) event.setStage(getStage());
		event.setTarget(this);

		// Collect ancestors so event propagation is unaffected by hierarchy changes.
		Array<Group> ancestors = Pools.obtain(Array.class);
		Group parent = getParent();
		while (parent != null) {
			ancestors.add(parent);
			parent = parent.getParent();
		}

		try {
			// Notify all parent capture listeners, starting at the root. Ancestors may stop an event before children receive it.
			for (int i = ancestors.size - 1; i >= 0; i--) {
				Group currentTarget = ancestors.get(i);
				currentTarget.notify(event, true);
				if (event.isStopped()) return event.isCancelled();
			}

			// Notify the target capture listeners.
			notify(event, true);
			if (event.isStopped()) return event.isCancelled();

			// Notify the target listeners.
			notify(event, false);
			if (!event.getBubbles()) return event.isCancelled();
			if (event.isStopped()) return event.isCancelled();

			// Notify all parent listeners, starting at the target. Children may stop an event before ancestors receive it.
			for (int i = 0, n = ancestors.size; i < n; i++) {
				ancestors.get(i).notify(event, false);
				if (event.isStopped()) return event.isCancelled();
			}

			return event.isCancelled();
		} finally {
			ancestors.clear();
			Pools.free(ancestors);
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#notify(com.badlogic.gdx.scenes.scene2d.Event, boolean)
	 */
	@Override
	public boolean notify (Event event, boolean capture) {
		if (event.getTarget() == null) throw new IllegalArgumentException("The event target cannot be null.");

		DelayedRemovalArray<EventListener> listeners = capture ? captureListeners : this.listeners;
		if (listeners.size == 0) return event.isCancelled();

		event.setListenerActor(this);
		event.setCapture(capture);
		if (event.getStage() == null) event.setStage(stage);

		listeners.begin();
		for (int i = 0, n = listeners.size; i < n; i++) {
			EventListener listener = listeners.get(i);
			if(listener == null) {
				throw new NullPointerException();
			}
			if (listener.handle(event)) {
				event.handle();
				if (event instanceof InputEvent) {
					InputEvent inputEvent = (InputEvent)event;
					if (inputEvent.getType() == Type.touchDown) {
						event.getStage().addTouchFocus(listener, this, inputEvent.getTarget(), inputEvent.getPointer(),
							inputEvent.getButton());
					}
				}
			}
		}
		listeners.end();

		return event.isCancelled();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#hit(float, float, boolean)
	 */
	@Override
	public IActor hit (float x, float y, boolean touchable) {
		if (touchable && this.touchable != Touchable.enabled) return null;
		return x >= 0 && x < width && y >= 0 && y < height ? this : null;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#remove()
	 */
	@Override
	public boolean remove () {
		if (parent != null) return parent.removeActor(this);
		return false;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#addListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean addListener (EventListener listener) {
		if (!listeners.contains(listener, true)) {
			listeners.add(listener);
			return true;
		}
		return false;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#removeListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean removeListener (EventListener listener) {
		return listeners.removeValue(listener, true);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getListeners()
	 */
	@Override
	public Array<EventListener> getListeners () {
		return listeners;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#addCaptureListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean addCaptureListener (EventListener listener) {
		if (!captureListeners.contains(listener, true)) captureListeners.add(listener);
		return true;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#removeCaptureListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean removeCaptureListener (EventListener listener) {
		return captureListeners.removeValue(listener, true);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getCaptureListeners()
	 */
	@Override
	public Array<EventListener> getCaptureListeners () {
		return captureListeners;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#addAction(com.badlogic.gdx.scenes.scene2d.Action)
	 */
	@Override
	public void addAction (Action action) {
		action.setActor(this);
		actions.add(action);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#removeAction(com.badlogic.gdx.scenes.scene2d.Action)
	 */
	@Override
	public void removeAction (Action action) {
		if (actions.removeValue(action, true)) action.setActor(null);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getActions()
	 */
	@Override
	public Array<Action> getActions () {
		return actions;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clearActions()
	 */
	@Override
	public void clearActions () {
		for (int i = actions.size - 1; i >= 0; i--)
			actions.get(i).setActor(null);
		actions.clear();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clearListeners()
	 */
	@Override
	public void clearListeners () {
		listeners.clear();
		captureListeners.clear();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clear()
	 */
	@Override
	public void clear () {
		clearActions();
		clearListeners();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getStage()
	 */
	@Override
	public IStage getStage () {
		return stage;
	}

	/** Called by the framework when this actor or any parent is added to a group that is in the stage.
	 * @param stage May be null if the actor or any parent is no longer in a stage. */
	public void setStage (IStage stage) {
		this.stage = stage;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#isDescendantOf(com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public boolean isDescendantOf (IActor actor) {
		if (actor == null) throw new IllegalArgumentException("actor cannot be null.");
		IActor parent = this;
		while (true) {
			if (parent == null) return false;
			if (parent == actor) return true;
			parent = parent.getParent();
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#isAscendantOf(com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public boolean isAscendantOf (IActor actor) {
		if (actor == null) throw new IllegalArgumentException("actor cannot be null.");
		while (true) {
			if (actor == null) return false;
			if (actor == this) return true;
			actor = actor.getParent();
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#hasParent()
	 */
	@Override
	public boolean hasParent () {
		return parent != null;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getParent()
	 */
	@Override
	public Group getParent () {
		return parent;
	}

	/** Called by the framework when an actor is added to or removed from a group.
	 * @param parent May be null if the actor has been removed from the parent. */
	public void setParent (Group parent) {
		this.parent = parent;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#isTouchable()
	 */
	@Override
	public boolean isTouchable () {
		return touchable == Touchable.enabled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getTouchable()
	 */
	@Override
	public Touchable getTouchable () {
		return touchable;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setTouchable(com.badlogic.gdx.scenes.scene2d.Touchable)
	 */
	@Override
	public void setTouchable (Touchable touchable) {
		this.touchable = touchable;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#isVisible()
	 */
	@Override
	public boolean isVisible () {
		return visible;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setVisible(boolean)
	 */
	@Override
	public void setVisible (boolean visible) {
		this.visible = visible;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getUserObject()
	 */
	@Override
	public Object getUserObject () {
		return userObject;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setUserObject(java.lang.Object)
	 */
	@Override
	public void setUserObject (Object userObject) {
		this.userObject = userObject;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getX()
	 */
	@Override
	public float getX () {
		return x;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setX(float)
	 */
	@Override
	public void setX (float x) {
		this.x = x;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getY()
	 */
	@Override
	public float getY () {
		return y;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setY(float)
	 */
	@Override
	public void setY (float y) {
		this.y = y;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setPosition(float, float)
	 */
	@Override
	public void setPosition (float x, float y) {
		this.x = x;
		this.y = y;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#translate(float, float)
	 */
	@Override
	public void translate (float x, float y) {
		this.x += x;
		this.y += y;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getWidth()
	 */
	@Override
	public float getWidth () {
		return width;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setWidth(float)
	 */
	@Override
	public void setWidth (float width) {
		float oldWidth = this.width;
		this.width = width;
		if (width != oldWidth) sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getHeight()
	 */
	@Override
	public float getHeight () {
		return height;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setHeight(float)
	 */
	@Override
	public void setHeight (float height) {
		float oldHeight = this.height;
		this.height = height;
		if (height != oldHeight) sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getTop()
	 */
	@Override
	public float getTop () {
		return getY() + height;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getRight()
	 */
	@Override
	public float getRight () {
		return getX() + width;
	}

	/** Called when the actor's size has been changed. */
	protected void sizeChanged () {
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setSize(float, float)
	 */
	@Override
	public void setSize (float width, float height) {
		float oldWidth = this.width;
		float oldHeight = this.height;
		this.width = width;
		this.height = height;
		if (width != oldWidth || height != oldHeight) sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#size(float)
	 */
	@Override
	public void size (float size) {
		width += size;
		height += size;
		sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#size(float, float)
	 */
	@Override
	public void size (float width, float height) {
		this.width += width;
		this.height += height;
		sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setBounds(float, float, float, float)
	 */
	@Override
	public void setBounds (float x, float y, float width, float height) {
		float oldWidth = this.width;
		float oldHeight = this.height;
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		if (width != oldWidth || height != oldHeight) sizeChanged();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getOriginX()
	 */
	@Override
	public float getOriginX () {
		return originX;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setOriginX(float)
	 */
	@Override
	public void setOriginX (float originX) {
		this.originX = originX;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getOriginY()
	 */
	@Override
	public float getOriginY () {
		return originY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setOriginY(float)
	 */
	@Override
	public void setOriginY (float originY) {
		this.originY = originY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setOrigin(float, float)
	 */
	@Override
	public void setOrigin (float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getScaleX()
	 */
	@Override
	public float getScaleX () {
		return scaleX;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setScaleX(float)
	 */
	@Override
	public void setScaleX (float scaleX) {
		this.scaleX = scaleX;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getScaleY()
	 */
	@Override
	public float getScaleY () {
		return scaleY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setScaleY(float)
	 */
	@Override
	public void setScaleY (float scaleY) {
		this.scaleY = scaleY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setScale(float)
	 */
	@Override
	public void setScale (float scale) {
		this.scaleX = scale;
		this.scaleY = scale;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setScale(float, float)
	 */
	@Override
	public void setScale (float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#scale(float)
	 */
	@Override
	public void scale (float scale) {
		scaleX += scale;
		scaleY += scale;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#scale(float, float)
	 */
	@Override
	public void scale (float scaleX, float scaleY) {
		this.scaleX += scaleX;
		this.scaleY += scaleY;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getRotation()
	 */
	@Override
	public float getRotation () {
		return rotation;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setRotation(float)
	 */
	@Override
	public void setRotation (float degrees) {
		this.rotation = degrees;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#rotate(float)
	 */
	@Override
	public void rotate (float amountInDegrees) {
		rotation += amountInDegrees;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setColor(com.badlogic.gdx.graphics.Color)
	 */
	@Override
	public void setColor (Color color) {
		this.color.set(color);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setColor(float, float, float, float)
	 */
	@Override
	public void setColor (float r, float g, float b, float a) {
		color.set(r, g, b, a);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getColor()
	 */
	@Override
	public Color getColor () {
		return color;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getName()
	 */
	@Override
	public String getName () {
		return name;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setName(java.lang.String)
	 */
	@Override
	public void setName (String name) {
		this.name = name;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#toFront()
	 */
	@Override
	public void toFront () {
		setZIndex(Integer.MAX_VALUE);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#toBack()
	 */
	@Override
	public void toBack () {
		setZIndex(0);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#setZIndex(int)
	 */
	@Override
	public void setZIndex (int index) {
		if (index < 0) throw new IllegalArgumentException("ZIndex cannot be < 0.");
		IGroup parent = this.parent;
		if (parent == null) return;
		Array<IActor> children = parent.getChildren();
		if (children.size == 1) return;
		if (!children.removeValue(this, true)) return;
		if (index >= children.size)
			children.add(this);
		else
			children.insert(index, this);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#getZIndex()
	 */
	@Override
	public int getZIndex () {
		IGroup parent = this.parent;
		if (parent == null) return -1;
		return parent.getChildren().indexOf(this, true);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clipBegin()
	 */
	@Override
	public boolean clipBegin () {
		return clipBegin(getX(), getY(), width, height);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clipBegin(float, float, float, float)
	 */
	@Override
	public boolean clipBegin (float x, float y, float width, float height) {
		Rectangle tableBounds = Rectangle.tmp;
		tableBounds.x = x;
		tableBounds.y = y;
		tableBounds.width = width;
		tableBounds.height = height;
		IStage stage = this.stage;
		Rectangle scissorBounds = Pools.obtain(Rectangle.class);
		stage.calculateScissors(tableBounds, scissorBounds);
		if (ScissorStack.pushScissors(scissorBounds)) return true;
		Pools.free(scissorBounds);
		return false;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#clipEnd()
	 */
	@Override
	public void clipEnd () {
		Pools.free(ScissorStack.popScissors());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#screenToLocalCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 screenToLocalCoordinates (Vector2 screenCoords) {
		IStage stage = this.stage;
		if (stage == null) return screenCoords;
		return stageToLocalCoordinates(stage.screenToStageCoordinates(screenCoords));
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#stageToLocalCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 stageToLocalCoordinates (Vector2 stageCoords) {
		if (parent == null) return stageCoords;
		parent.stageToLocalCoordinates(stageCoords);
		parentToLocalCoordinates(stageCoords);
		return stageCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#localToStageCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 localToStageCoordinates (Vector2 localCoords) {
		return localToAscendantCoordinates(null, localCoords);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#localToParentCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 localToParentCoordinates (Vector2 localCoords) {
		final float rotation = -this.rotation;
		final float scaleX = this.scaleX;
		final float scaleY = this.scaleY;
		final float x = this.getX();
		final float y = this.getY();
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				localCoords.setX(localCoords.getX() + x);
				localCoords.setY(localCoords.getY() + y);
			} else {
				final float originX = this.originX;
				final float originY = this.originY;
				localCoords.setX((localCoords.getX() - originX) * scaleX + originX + x);
				localCoords.setY((localCoords.getY() - originY) * scaleY + originY + y);
			}
		} else {
			final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
			final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
			final float originX = this.originX;
			final float originY = this.originY;
			final float tox = localCoords.getX() - originX;
			final float toy = localCoords.getY() - originY;
			localCoords.setX((tox * cos + toy * sin) * scaleX + originX + x);
			localCoords.setY((tox * -sin + toy * cos) * scaleY + originY + y);
		}
		return localCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#localToAscendantCoordinates(com.badlogic.gdx.scenes.scene2d.IActor, com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 localToAscendantCoordinates (IActor ascendant, Vector2 localCoords) {
		IActor actor = this;
		while (actor.getParent() != null) {
			actor.localToParentCoordinates(localCoords);
			actor = actor.getParent();
			if (actor == ascendant) break;
		}
		return localCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#parentToLocalCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 parentToLocalCoordinates (Vector2 parentCoords) {
		final float rotation = this.rotation;
		final float scaleX = this.scaleX;
		final float scaleY = this.scaleY;
		final float childX = getX();
		final float childY = getY();
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				parentCoords.setX(parentCoords.getX() - childX);
				parentCoords.setY(parentCoords.getY() - childY);
			} else {
				final float originX = this.originX;
				final float originY = this.originY;
				parentCoords.setX((parentCoords.getX() - childX - originX) / scaleX + originX);
				parentCoords.setY((parentCoords.getY() - childY - originY) / scaleY + originY);
			}
		} else {
			final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
			final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
			final float originX = this.originX;
			final float originY = this.originY;
			final float tox = parentCoords.getX() - childX - originX;
			final float toy = parentCoords.getY() - childY - originY;
			parentCoords.setX((tox * cos + toy * sin) / scaleX + originX);
			parentCoords.setY((tox * -sin + toy * cos) / scaleY + originY);
		}
		return parentCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IActor#toString()
	 */
	@Override
	public String toString () {
		String name = this.name;
		if (name == null) {
			name = getClass().getName();
			int dotIndex = name.lastIndexOf('.');
			if (dotIndex != -1) name = name.substring(dotIndex + 1);
		}
		return name;
	}
}
