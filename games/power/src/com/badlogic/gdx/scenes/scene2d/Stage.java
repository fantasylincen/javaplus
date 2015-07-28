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

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

/** A 2D scene graph containing hierarchies of {@link Actor actors}. Stage handles the viewport and distributes input events.
 * <p>
 * A stage fills the whole screen. {@link #setViewport} controls the coordinates used within the stage and sets up the camera used
 * to convert between stage coordinates and screen coordinates.
 * <p>
 * A stage must receive input events so it can distribute them to actors. This is typically done by passing the stage to
 * {@link Input#setInputProcessor(com.badlogic.gdx.InputProcessor) Gdx.input.setInputProcessor}. An {@link InputMultiplexer} may be
 * used to handle input events before or after the stage does. If an actor handles an event by returning true from the input
 * method, then the stage's input method will also return true, causing subsequent InputProcessors to not receive the event.
 * <p>
 * The Stage and its constituents (like Actors and Listeners) are not thread-safe and should only be updated and queried from a
 * single thread (presumably the main render thread). Methods should be reentrant, so you can update Actors and Stages from within
 * callbacks and handlers.
 * 
 * @author mzechner
 * @author Nathan Sweet */
public class Stage extends InputAdapter implements Disposable, IStage {
	static private final Vector2 actorCoords = new Vector2();
	static private final Vector3 cameraCoords = new Vector3();

	private float viewportX, viewportY, viewportWidth, viewportHeight;
	private float width, height;
	private float gutterWidth, gutterHeight;
	private ICamera camera;
	private final Batch batch;
	private final boolean ownsBatch;
	private final Group root;
	private final Vector2 stageCoords = new Vector2();
	private final IActor[] pointerOverActors = new IActor[20];
	private final boolean[] pointerTouched = new boolean[20];
	private final int[] pointerScreenX = new int[20];
	private final int[] pointerScreenY = new int[20];
	private int mouseScreenX, mouseScreenY;
	private IActor mouseOverActor;
	private IActor keyboardFocus, scrollFocus;
	private final SnapshotArray<TouchFocus> touchFocuses = new SnapshotArray(true, 4, TouchFocus.class);

	/** Creates a stage with a {@link #setViewport(float, float, boolean) viewport} equal to the device screen resolution. The stage
	 * will use its own {@link Batch}. */
	public Stage () {
		this(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, null);
	}

	/** Creates a stage with the specified {@link #setViewport(float, float, boolean) viewport} that doesn't keep the aspect ratio.
	 * The stage will use its own {@link Batch}, which will be disposed when the stage is disposed. */
	public Stage (float width, float height) {
		this(width, height, false, null);
	}

	/** Creates a stage with the specified {@link #setViewport(float, float, boolean) viewport}. The stage will use its own
	 * {@link Batch}, which will be disposed when the stage is disposed. */
	public Stage (float width, float height, boolean keepAspectRatio) {
		this(width, height, keepAspectRatio, null);
	}

	/** Creates a stage with the specified {@link #setViewport(float, float, boolean) viewport} and {@link Batch}. This can be used
	 * to avoid creating a new Batch (which can be somewhat slow) if multiple stages are used during an applications life time.
	 * @param batch Will not be disposed if {@link #dispose()} is called. Handle disposal yourself. */
	public Stage (float width, float height, boolean keepAspectRatio, Batch batch) {
		ownsBatch = batch == null;
		this.batch = ownsBatch ? new SpriteBatch() : batch;

		this.width = width;
		this.height = height;

		root = new Group();
		root.setStage(this);

		camera = new OrthographicCamera();
		setViewport(width, height, keepAspectRatio);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setViewport(float, float)
	 */
	@Override
	public void setViewport (float width, float height) {
		setViewport(width, height, false, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setViewport(float, float, boolean)
	 */
	@Override
	public void setViewport (float width, float height, boolean keepAspectRatio) {
		setViewport(width, height, keepAspectRatio, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setViewport(float, float, boolean, float, float, float, float)
	 */
	@Override
	public void setViewport (float stageWidth, float stageHeight, boolean keepAspectRatio, float viewportX, float viewportY,
		float viewportWidth, float viewportHeight) {
		this.viewportX = viewportX;
		this.viewportY = viewportY;
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
		if (keepAspectRatio) {
			if (viewportHeight / viewportWidth < stageHeight / stageWidth) {
				float toViewportSpace = viewportHeight / stageHeight;
				float toStageSpace = stageHeight / viewportHeight;
				float deviceWidth = stageWidth * toViewportSpace;
				float lengthen = (viewportWidth - deviceWidth) * toStageSpace;
				this.width = stageWidth + lengthen;
				this.height = stageHeight;
				gutterWidth = lengthen / 2;
				gutterHeight = 0;
			} else {
				float toViewportSpace = viewportWidth / stageWidth;
				float toStageSpace = stageWidth / viewportWidth;
				float deviceHeight = stageHeight * toViewportSpace;
				float lengthen = (viewportHeight - deviceHeight) * toStageSpace;
				this.height = stageHeight + lengthen;
				this.width = stageWidth;
				gutterWidth = 0;
				gutterHeight = lengthen / 2;
			}
		} else {
			this.width = stageWidth;
			this.height = stageHeight;
			gutterWidth = 0;
			gutterHeight = 0;
		}

		float centerX = this.width / 2;
		float centerY = this.height / 2;
		camera.setPosition(centerX, centerY, 0);
		camera.setViewportWidth(this.width);
		camera.setViewportHeight(this.height);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#draw()
	 */
	@Override
	public void draw () {
		camera.update();
		if (!root.isVisible()) return;
		batch.setProjectionMatrix(camera.getCombined());
		batch.begin();
		root.draw(batch, 1);
		batch.end();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#act()
	 */
	@Override
	public void act () {
		act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#act(float)
	 */
	@Override
	public void act (float delta) {
		// Update over actors. Done in act() because actors may change position, which can fire enter/exit without an input event.
		for (int pointer = 0, n = pointerOverActors.length; pointer < n; pointer++) {
			IActor overLast = pointerOverActors[pointer];
			// Check if pointer is gone.
			if (!pointerTouched[pointer]) {
				if (overLast != null) {
					pointerOverActors[pointer] = null;
					screenToStageCoordinates(stageCoords.set(pointerScreenX[pointer], pointerScreenY[pointer]));
					// Exit over last.
					InputEvent event = Pools.obtain(InputEvent.class);
					event.setType(InputEvent.Type.exit);
					event.setStage(this);
					event.setStageX(stageCoords.getX());
					event.setStageY(stageCoords.getY());
					event.setRelatedActor(overLast);
					event.setPointer(pointer);
					overLast.fire(event);
					Pools.free(event);
				}
				continue;
			}
			// Update over actor for the pointer.
			pointerOverActors[pointer] = fireEnterAndExit(overLast, pointerScreenX[pointer], pointerScreenY[pointer], pointer);
		}
		// Update over actor for the mouse on the desktop.
		ApplicationType type = Gdx.app.getType();
		if (type == ApplicationType.Desktop || type == ApplicationType.Applet || type == ApplicationType.WebGL)
			mouseOverActor = fireEnterAndExit(mouseOverActor, mouseScreenX, mouseScreenY, -1);

		root.act(delta);
	}

	private IActor fireEnterAndExit (IActor overLast, int screenX, int screenY, int pointer) {
		// Find the actor under the point.
		screenToStageCoordinates(stageCoords.set(screenX, screenY));
		IActor over = hit(stageCoords.getX(), stageCoords.getY(), true);
		if (over == overLast) return overLast;

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());
		event.setPointer(pointer);
		// Exit overLast.
		if (overLast != null) {
			event.setType(InputEvent.Type.exit);
			event.setRelatedActor(over);
			overLast.fire(event);
		}
		// Enter over.
		if (over != null) {
			event.setType(InputEvent.Type.enter);
			event.setRelatedActor(overLast);
			over.fire(event);
		}
		Pools.free(event);
		return over;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (screenX < viewportX || screenX >= viewportX + viewportWidth) return false;
		if (screenY < viewportY || screenY >= viewportY + viewportHeight) return false;

		pointerTouched[pointer] = true;
		pointerScreenX[pointer] = screenX;
		pointerScreenY[pointer] = screenY;

		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setType(Type.touchDown);
		event.setStage(this);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());
		event.setPointer(pointer);
		event.setButton(button);

		IActor target = hit(stageCoords.getX(), stageCoords.getY(), true);
		if (target == null) target = root;

		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#touchDragged(int, int, int)
	 */
	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		pointerScreenX[pointer] = screenX;
		pointerScreenY[pointer] = screenY;
		mouseScreenX = screenX;
		mouseScreenY = screenY;

		if (touchFocuses.size == 0) return false;

		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setType(Type.touchDragged);
		event.setStage(this);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());
		event.setPointer(pointer);

		SnapshotArray<TouchFocus> touchFocuses = this.touchFocuses;
		TouchFocus[] focuses = touchFocuses.begin();
		for (int i = 0, n = touchFocuses.size; i < n; i++) {
			TouchFocus focus = focuses[i];
			if (focus.pointer != pointer) continue;
			event.setTarget(focus.target);
			event.setListenerActor(focus.listenerActor);
			if (focus.listener.handle(event)) event.handle();
		}
		touchFocuses.end();

		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#touchUp(int, int, int, int)
	 */
	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		pointerTouched[pointer] = false;
		pointerScreenX[pointer] = screenX;
		pointerScreenY[pointer] = screenY;

		if (touchFocuses.size == 0) return false;

		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setType(Type.touchUp);
		event.setStage(this);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());
		event.setPointer(pointer);
		event.setButton(button);

		SnapshotArray<TouchFocus> touchFocuses = this.touchFocuses;
		TouchFocus[] focuses = touchFocuses.begin();
		for (int i = 0, n = touchFocuses.size; i < n; i++) {
			TouchFocus focus = focuses[i];
			if (focus.pointer != pointer || focus.button != button) continue;
			if (!touchFocuses.removeValue(focus, true)) continue; // Touch focus already gone.
			event.setTarget(focus.target);
			event.setListenerActor(focus.listenerActor);
			if (focus.listener.handle(event)) event.handle();
			Pools.free(focus);
		}
		touchFocuses.end();

		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		if (screenX < viewportX || screenX >= viewportX + viewportWidth) return false;
		if (screenY < viewportY || screenY >= viewportY + viewportHeight) return false;

		mouseScreenX = screenX;
		mouseScreenY = screenY;

		screenToStageCoordinates(stageCoords.set(screenX, screenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(Type.mouseMoved);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());

		IActor target = hit(stageCoords.getX(), stageCoords.getY(), true);
		if (target == null) target = root;

//		try {
			target.fire(event);
//		} catch (Exception e) {
//			throw Util.toRuntimeException(e);
//		}
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#scrolled(int)
	 */
	@Override
	public boolean scrolled (int amount) {
		IActor target = scrollFocus == null ? root : scrollFocus;

		screenToStageCoordinates(stageCoords.set(mouseScreenX, mouseScreenY));

		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(InputEvent.Type.scrolled);
		event.setScrollAmount(amount);
		event.setStageX(stageCoords.getX());
		event.setStageY(stageCoords.getY());
		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#keyDown(int)
	 */
	@Override
	public boolean keyDown (int keyCode) {
		IActor target = keyboardFocus == null ? root : keyboardFocus;
		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(InputEvent.Type.keyDown);
		event.setKeyCode(keyCode);
		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#keyUp(int)
	 */
	@Override
	public boolean keyUp (int keyCode) {
		IActor target = keyboardFocus == null ? root : keyboardFocus;
		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(InputEvent.Type.keyUp);
		event.setKeyCode(keyCode);
		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#keyTyped(char)
	 */
	@Override
	public boolean keyTyped (char character) {
		IActor target = keyboardFocus == null ? root : keyboardFocus;
		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(InputEvent.Type.keyTyped);
		event.setCharacter(character);
		target.fire(event);
		boolean handled = event.isHandled();
		Pools.free(event);
		return handled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#addTouchFocus(com.badlogic.gdx.scenes.scene2d.EventListener, com.badlogic.gdx.scenes.scene2d.Actor, com.badlogic.gdx.scenes.scene2d.Actor, int, int)
	 */
	@Override
	public void addTouchFocus (EventListener listener, IActor listenerActor, IActor target, int pointer, int button) {
		TouchFocus focus = Pools.obtain(TouchFocus.class);
		focus.listenerActor = listenerActor;
		focus.target = target;
		focus.listener = listener;
		focus.pointer = pointer;
		focus.button = button;
		touchFocuses.add(focus);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#removeTouchFocus(com.badlogic.gdx.scenes.scene2d.EventListener, com.badlogic.gdx.scenes.scene2d.IActor, com.badlogic.gdx.scenes.scene2d.IActor, int, int)
	 */
	@Override
	public void removeTouchFocus (EventListener listener, IActor listenerActor, IActor target, int pointer, int button) {
		SnapshotArray<TouchFocus> touchFocuses = this.touchFocuses;
		for (int i = touchFocuses.size - 1; i >= 0; i--) {
			TouchFocus focus = touchFocuses.get(i);
			if (focus.listener == listener && focus.listenerActor == listenerActor && focus.target == target
				&& focus.pointer == pointer && focus.button == button) {
				touchFocuses.removeIndex(i);
				Pools.free(focus);
			}
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#cancelTouchFocus()
	 */
	@Override
	public void cancelTouchFocus () {
		cancelTouchFocus(null, null);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#cancelTouchFocus(com.badlogic.gdx.scenes.scene2d.EventListener, com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public void cancelTouchFocus (EventListener listener, IActor actor) {
		InputEvent event = Pools.obtain(InputEvent.class);
		event.setStage(this);
		event.setType(InputEvent.Type.touchUp);
		event.setStageX(Integer.MIN_VALUE);
		event.setStageY(Integer.MIN_VALUE);

		// Cancel all current touch focuses except for the specified listener, allowing for concurrent modification, and never
		// cancel the same focus twice.
		SnapshotArray<TouchFocus> touchFocuses = this.touchFocuses;
		TouchFocus[] items = touchFocuses.begin();
		for (int i = 0, n = touchFocuses.size; i < n; i++) {
			TouchFocus focus = items[i];
			if (focus.listener == listener && focus.listenerActor == actor) continue;
			if (!touchFocuses.removeValue(focus, true)) continue; // Touch focus already gone.
			event.setTarget(focus.target);
			event.setListenerActor(focus.listenerActor);
			event.setPointer(focus.pointer);
			event.setButton(focus.button);
			focus.listener.handle(event);
			// Cannot return TouchFocus to pool, as it may still be in use (eg if cancelTouchFocus is called from touchDragged).
		}
		touchFocuses.end();

		Pools.free(event);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#addActor(com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void addActor (IActor actor) {
		root.addActor(actor);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#addAction(com.badlogic.gdx.scenes.scene2d.Action)
	 */
	@Override
	public void addAction (Action action) {
		root.addAction(action);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getActors()
	 */
	@Override
	public Array<IActor> getActors () {
		return root.getChildren();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#addListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean addListener (EventListener listener) {
		return root.addListener(listener);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#removeListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean removeListener (EventListener listener) {
		return root.removeListener(listener);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#addCaptureListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean addCaptureListener (EventListener listener) {
		return root.addCaptureListener(listener);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#removeCaptureListener(com.badlogic.gdx.scenes.scene2d.EventListener)
	 */
	@Override
	public boolean removeCaptureListener (EventListener listener) {
		return root.removeCaptureListener(listener);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#clear()
	 */
	@Override
	public void clear () {
		unfocusAll();
		root.clear();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#unfocusAll()
	 */
	@Override
	public void unfocusAll () {
		scrollFocus = null;
		keyboardFocus = null;
		cancelTouchFocus();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#unfocus(com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public void unfocus (IActor actor) {
		if (scrollFocus != null && scrollFocus.isDescendantOf(actor)) scrollFocus = null;
		if (keyboardFocus != null && keyboardFocus.isDescendantOf(actor)) keyboardFocus = null;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setKeyboardFocus(com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void setKeyboardFocus (IActor actor) {
		if (keyboardFocus == actor) return;
		FocusEvent event = Pools.obtain(FocusEvent.class);
		event.setStage(this);
		event.setType(FocusEvent.Type.keyboard);
		IActor oldKeyboardFocus = keyboardFocus;
		if (oldKeyboardFocus != null) {
			event.setFocused(false);
			event.setRelatedActor(actor);
			oldKeyboardFocus.fire(event);
		}
		if (!event.isCancelled()) {
			keyboardFocus = actor;
			if (actor != null) {
				event.setFocused(true);
				event.setRelatedActor(oldKeyboardFocus);
				actor.fire(event);
				if (event.isCancelled()) setKeyboardFocus(oldKeyboardFocus);
			}
		}
		Pools.free(event);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getKeyboardFocus()
	 */
	@Override
	public IActor getKeyboardFocus () {
		return keyboardFocus;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setScrollFocus(com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void setScrollFocus (IActor actor) {
		if (scrollFocus == actor) return;
		FocusEvent event = Pools.obtain(FocusEvent.class);
		event.setStage(this);
		event.setType(FocusEvent.Type.scroll);
		IActor oldScrollFocus = keyboardFocus;
		if (oldScrollFocus != null) {
			event.setFocused(false);
			event.setRelatedActor(actor);
			oldScrollFocus.fire(event);
		}
		if (!event.isCancelled()) {
			scrollFocus = actor;
			if (actor != null) {
				event.setFocused(true);
				event.setRelatedActor(oldScrollFocus);
				actor.fire(event);
				if (event.isCancelled()) setScrollFocus(oldScrollFocus);
			}
		}
		Pools.free(event);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getScrollFocus()
	 */
	@Override
	public IActor getScrollFocus () {
		return scrollFocus;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getWidth()
	 */
	@Override
	public float getWidth () {
		return width;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getHeight()
	 */
	@Override
	public float getHeight () {
		return height;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getGutterWidth()
	 */
	@Override
	public float getGutterWidth () {
		return gutterWidth;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getGutterHeight()
	 */
	@Override
	public float getGutterHeight () {
		return gutterHeight;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getSpriteBatch()
	 */
	@Override
	public Batch getSpriteBatch () {
		return batch;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getCamera()
	 */
	@Override
	public ICamera getCamera () {
		return camera;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#setCamera(com.badlogic.gdx.graphics.Camera)
	 */
	@Override
	public void setCamera (ICamera camera) {
		this.camera = camera;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#getRoot()
	 */
	@Override
	public IGroup getRoot () {
		return root;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#hit(float, float, boolean)
	 */
	@Override
	public IActor hit (float stageX, float stageY, boolean touchable) {
		root.parentToLocalCoordinates(actorCoords.set(stageX, stageY));
		return root.hit(actorCoords.getX(), actorCoords.getY(), touchable);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#screenToStageCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 screenToStageCoordinates (Vector2 screenCoords) {
		camera.unproject(cameraCoords.set(screenCoords.getX(), screenCoords.getY(), 0), viewportX, viewportY, viewportWidth, viewportHeight);
		screenCoords.setX(cameraCoords.x);
		screenCoords.setY(cameraCoords.y);
		return screenCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#stageToScreenCoordinates(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 stageToScreenCoordinates (Vector2 stageCoords) {
		camera.project(cameraCoords.set(stageCoords.getX(), stageCoords.getY(), 0), viewportX, viewportY, viewportWidth, viewportHeight);
		stageCoords.setX(cameraCoords.x);
		stageCoords.setY(viewportHeight - cameraCoords.y);
		return stageCoords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#toScreenCoordinates(com.badlogic.gdx.math.Vector2, com.badlogic.gdx.math.Matrix4)
	 */
	@Override
	public Vector2 toScreenCoordinates (Vector2 coords, Matrix4 transformMatrix) {
		ScissorStack.toWindowCoordinates(camera, transformMatrix, coords);
		return coords;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#calculateScissors(com.badlogic.gdx.math.Rectangle, com.badlogic.gdx.math.Rectangle)
	 */
	@Override
	public void calculateScissors (Rectangle area, Rectangle scissor) {
		ScissorStack.calculateScissors(camera, viewportX, viewportY, viewportWidth, viewportHeight, batch.getTransformMatrix(),
			area, scissor);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.IStage#dispose()
	 */
	@Override
	public void dispose () {
		clear();
		if (ownsBatch) batch.dispose();
	}

	/** Internal class for managing touch focus. Public only for GWT.
	 * @author Nathan Sweet */
	public static final class TouchFocus implements Poolable {
		EventListener listener;
		IActor listenerActor, target;
		int pointer, button;

		public void reset () {
			listenerActor = null;
			listener = null;
		}
	}
}
