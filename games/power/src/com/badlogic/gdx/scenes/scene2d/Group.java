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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.IRectangle;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

/** 2D scene graph node that may contain other actors.
 * <p>
 * Actors have a z-order equal to the order they were inserted into the group. Actors inserted later will be drawn on top of
 * actors added earlier. Touch events that hit more than one actor are distributed to topmost actors first.
 * @author mzechner
 * @author Nathan Sweet */
public class Group extends Actor implements Cullable, IGroup {
	private final SnapshotArray<IActor> children = new SnapshotArray(true, 4, IActor.class);
	private final Matrix3 localTransform = new Matrix3();
	private final Matrix3 worldTransform = new Matrix3();
	private final Matrix4 batchTransform = new Matrix4();
	private final Matrix4 oldBatchTransform = new Matrix4();
	private boolean transform = true;
	private IRectangle cullingArea;
	private final Vector2 point = new Vector2();

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#act(float)
	 */
	@Override
	public void act (float delta) {
		super.act(delta);
		IActor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++)
			actors[i].act(delta);
		children.end();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	@Override
	public void draw (Batch batch, float parentAlpha) {
		if (transform) applyTransform(batch, computeTransform());
		drawChildren(batch, parentAlpha);
		if (transform) resetTransform(batch);
	}

	/** Draws all children. {@link #applyTransform(Batch, Matrix4)} should be called before and {@link #resetTransform(Batch)} after
	 * this method if {@link #setTransform(boolean) transform} is true. If {@link #setTransform(boolean) transform} is false these
	 * methods don't need to be called, children positions are temporarily offset by the group position when drawn. This method
	 * avoids drawing children completely outside the {@link #setCullingArea(Rectangle) culling area}, if set. */
	protected void drawChildren (Batch batch, float parentAlpha) {
		parentAlpha *= this.color.a;
		SnapshotArray<IActor> children = this.children;
		IActor[] actors = children.begin();
		IRectangle cullingArea = this.cullingArea;
		if (cullingArea != null) {
			// Draw children only if inside culling area.
			float cullLeft = cullingArea.getX();
			float cullRight = cullLeft + cullingArea.getWidth();
			float cullBottom = cullingArea.getY();
			float cullTop = cullBottom + cullingArea.getHeight();
			if (transform) {
				for (int i = 0, n = children.size; i < n; i++) {
					IActor child = actors[i];
					if (!child.isVisible()) continue;
					float cx = child.getX(), cy = child.getY();
					if (cx <= cullRight && cy <= cullTop && cx + child.getWidth() >= cullLeft && cy + child.getHeight() >= cullBottom)
						child.draw(batch, parentAlpha);
				}
				batch.flush();
			} else {
				// No transform for this group, offset each child.
				float offsetX = getX(), offsetY = getY();
				setPosition(0, 0);
				for (int i = 0, n = children.size; i < n; i++) {
					IActor child = actors[i];
					if (!child.isVisible()) continue;
					float cx = child.getX(), cy = child.getY();
					if (cx <= cullRight && cy <= cullTop && cx + child.getWidth() >= cullLeft && cy + child.getHeight() >= cullBottom) {
						child.setX(cx + offsetX);
						child.setY(cy + offsetY);
						child.draw(batch, parentAlpha);
						child.setX(cx);
						child.setY(cy);
					}
				}
				setPosition(offsetX, offsetY);
			}
		} else {
			// No culling, draw all children.
			if (transform) {
				for (int i = 0, n = children.size; i < n; i++) {
					IActor child = actors[i];
					if (!child.isVisible()) continue;
					child.draw(batch, parentAlpha);
				}
				batch.flush();
			} else {
				// No transform for this group, offset each child.
				float offsetX = getX(), offsetY = getY();
				setPosition(0, 0);
				for (int i = 0, n = children.size; i < n; i++) {
					IActor child = actors[i];
					if (!child.isVisible()) continue;
					float cx = child.getX(), cy = child.getY();
					child.setX(cx + offsetX);
					child.setY(cy + offsetY);
					child.draw(batch, parentAlpha);
					child.setX(cx);
					child.setY(cy);
				}
				setPosition(offsetX, offsetY);
			}
		}
		children.end();
	}

	/** Set the Batch's transformation matrix, often with the result of {@link #computeTransform()}. Note this causes the batch to
	 * be flushed. {@link #resetTransform(Batch)} will restore the transform to what it was before this call. */
	protected void applyTransform (Batch batch, Matrix4 transform) {
		oldBatchTransform.set(batch.getTransformMatrix());
		batch.setTransformMatrix(transform);
	}

	/** Returns the transform for this group's coordinate system. */
	protected Matrix4 computeTransform () {
		Matrix3 temp = worldTransform;

		float originX = this.originX;
		float originY = this.originY;
		float rotation = this.rotation;
		float scaleX = this.scaleX;
		float scaleY = this.scaleY;

		if (originX != 0 || originY != 0)
			localTransform.setToTranslation(originX, originY);
		else
			localTransform.idt();
		if (rotation != 0) localTransform.rotate(rotation);
		if (scaleX != 1 || scaleY != 1) localTransform.scale(scaleX, scaleY);
		if (originX != 0 || originY != 0) localTransform.translate(-originX, -originY);
		localTransform.trn(getX(), getY());

		// Find the first parent that transforms.
		Group parentGroup = getParent();
		while (parentGroup != null) {
			if (parentGroup.transform) break;
			parentGroup = parentGroup.getParent();
		}

		if (parentGroup != null) {
			worldTransform.set(parentGroup.worldTransform);
			worldTransform.mul(localTransform);
		} else {
			worldTransform.set(localTransform);
		}

		batchTransform.set(worldTransform);
		return batchTransform;
	}

	/** Restores the Batch transform to what it was before {@link #applyTransform(Batch, Matrix4)}. Note this causes the batch to be
	 * flushed. */
	protected void resetTransform (Batch batch) {
		batch.setTransformMatrix(oldBatchTransform);
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#setCullingArea(com.badlogic.gdx.math.Rectangle)
	 */
	@Override
	public void setCullingArea (IRectangle cullingArea) {
		this.cullingArea = cullingArea;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#hit(float, float, boolean)
	 */
	@Override
	public IActor hit (float x, float y, boolean touchable) {
		if (touchable && getTouchable() == Touchable.disabled) return null;
		Array<IActor> children = this.children;
		for (int i = children.size - 1; i >= 0; i--) {
			IActor child = children.get(i);
			if (!child.isVisible()) continue;
			child.parentToLocalCoordinates(point.set(x, y));
			IActor hit = child.hit(point.getX(), point.getY(), touchable);
			if (hit != null) return hit;
		}
		return super.hit(x, y, touchable);
	}

	/** Called when actors are added to or removed from the group. */
	protected void childrenChanged () {
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#addActor(com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public void addActor (IActor actor) {
		actor.remove();
		children.add(actor);
		actor.setParent(this);
		actor.setStage(getStage());
		childrenChanged();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#addActorAt(int, com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void addActorAt (int index, Actor actor) {
		actor.remove();
		if (index >= children.size)
			children.add(actor);
		else
			children.insert(index, actor);
		actor.setParent(this);
		actor.setStage(getStage());
		childrenChanged();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#addActorBefore(com.badlogic.gdx.scenes.scene2d.Actor, com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void addActorBefore (Actor actorBefore, Actor actor) {
		actor.remove();
		int index = children.indexOf(actorBefore, true);
		children.insert(index, actor);
		actor.setParent(this);
		actor.setStage(getStage());
		childrenChanged();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#addActorAfter(com.badlogic.gdx.scenes.scene2d.Actor, com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void addActorAfter (Actor actorAfter, Actor actor) {
		actor.remove();
		int index = children.indexOf(actorAfter, true);
		if (index == children.size)
			children.add(actor);
		else
			children.insert(index + 1, actor);
		actor.setParent(this);
		actor.setStage(getStage());
		childrenChanged();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#removeActor(com.badlogic.gdx.scenes.scene2d.IActor)
	 */
	@Override
	public boolean removeActor (IActor actor) {
		if (!children.removeValue(actor, true)) return false;
		IStage stage = getStage();
		if (stage != null) stage.unfocus(actor);
		actor.setParent(null);
		actor.setStage(null);
		childrenChanged();
		return true;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#clearChildren()
	 */
	@Override
	public void clearChildren () {
		IActor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			IActor child = actors[i];
			child.setStage(null);
			child.setParent(null);
		}
		children.end();
		children.clear();
		childrenChanged();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#clear()
	 */
	@Override
	public void clear () {
		super.clear();
		clearChildren();
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#findActor(java.lang.String)
	 */
	@Override
	public IActor findActor (String name) {
		Array<IActor> children = this.children;
		for (int i = 0, n = children.size; i < n; i++)
			if (name.equals(children.get(i).getName())) return children.get(i);
		for (int i = 0, n = children.size; i < n; i++) {
			IActor child = children.get(i);
			if (child instanceof Group) {
				IActor actor = ((IGroup)child).findActor(name);
				if (actor != null) return actor;
			}
		}
		return null;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#setStage(com.badlogic.gdx.scenes.scene2d.Stage)
	 */
	@Override
	public void setStage (IStage stage) {
		super.setStage(stage);
		Array<IActor> children = this.children;
		for (int i = 0, n = children.size; i < n; i++)
			children.get(i).setStage(stage);
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#swapActor(int, int)
	 */
	@Override
	public boolean swapActor (int first, int second) {
		int maxIndex = children.size;
		if (first < 0 || first >= maxIndex) return false;
		if (second < 0 || second >= maxIndex) return false;
		children.swap(first, second);
		return true;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#swapActor(com.badlogic.gdx.scenes.scene2d.Actor, com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public boolean swapActor (Actor first, Actor second) {
		int firstIndex = children.indexOf(first, true);
		int secondIndex = children.indexOf(second, true);
		if (firstIndex == -1 || secondIndex == -1) return false;
		children.swap(firstIndex, secondIndex);
		return true;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#getChildren()
	 */
	@Override
	public SnapshotArray<IActor> getChildren () {
		return children;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#hasChildren()
	 */
	@Override
	public boolean hasChildren () {
		return children.size > 0;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#setTransform(boolean)
	 */
	@Override
	public void setTransform (boolean transform) {
		this.transform = transform;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#isTransform()
	 */
	@Override
	public boolean isTransform () {
		return transform;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#localToDescendantCoordinates(com.badlogic.gdx.scenes.scene2d.IActor, com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 localToDescendantCoordinates (IActor descendant, Vector2 localCoords) {
		Group parent = descendant.getParent();
		if (parent == null) throw new IllegalArgumentException("Child is not a descendant: " + descendant);
		// First convert to the actor's parent coordinates.
		if (parent != this) localToDescendantCoordinates(parent, localCoords);
		// Then from each parent down to the descendant.
		descendant.parentToLocalCoordinates(localCoords);
		return localCoords;
	}

	/* 锛堥潪 Javadoc锛�	 * @see com.badlogic.gdx.scenes.scene2d.IGroup#print()
	 */
	@Override
	public void print () {
		print("");
	}

	private void print (String indent) {
		IActor[] actors = children.begin();
		for (int i = 0, n = children.size; i < n; i++) {
			System.out.println(indent + actors[i]);
			if (actors[i] instanceof Group) ((Group)actors[i]).print(indent + "|  ");
		}
		children.end();
	}
}
