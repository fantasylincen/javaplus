package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.IRectangle;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;

public interface IGroup extends IActor{

	public abstract void act(float delta);

	/** Draws the group and its children. The default implementation calls {@link #applyTransform(Batch, Matrix4)} if needed, then
	 * {@link #drawChildren(Batch, float)}, then {@link #resetTransform(Batch)} if needed. */
	public abstract void draw(Batch batch, float parentAlpha);

	/** Children completely outside of this rectangle will not be drawn. This is only valid for use with unrotated and unscaled
	 * actors! */
	public abstract void setCullingArea(IRectangle cullingArea);

	public abstract IActor hit(float x, float y, boolean touchable);

	/** Adds an actor as a child of this group. The actor is first removed from its parent group, if any.
	 * @see #remove() */
	public abstract void addActor(IActor actor);

	/** Adds an actor as a child of this group, at a specific index. The actor is first removed from its parent group, if any.
	 * @param index May be greater than the number of children. */
	public abstract void addActorAt(int index, Actor actor);

	/** Adds an actor as a child of this group, immediately before another child actor. The actor is first removed from its parent
	 * group, if any. */
	public abstract void addActorBefore(Actor actorBefore, Actor actor);

	/** Adds an actor as a child of this group, immediately after another child actor. The actor is first removed from its parent
	 * group, if any. */
	public abstract void addActorAfter(Actor actorAfter, Actor actor);

	/** Removes an actor from this group. If the actor will not be used again and has actions, they should be
	 * {@link Actor#clearActions() cleared} so the actions will be returned to their
	 * {@link Action#setPool(com.badlogic.gdx.utils.Pool) pool}, if any. This is not done automatically. */
	public abstract boolean removeActor(IActor actor);

	/** Removes all actors from this group. */
	public abstract void clearChildren();

	/** Removes all children, actions, and listeners from this group. */
	public abstract void clear();

	/** Returns the first actor found with the specified name. Note this recursively compares the name of every actor in the group. */
	public abstract IActor findActor(String name);

	public abstract void setStage(IStage stage);

	/** Swaps two actors by index. Returns false if the swap did not occur because the indexes were out of bounds. */
	public abstract boolean swapActor(int first, int second);

	/** Swaps two actors. Returns false if the swap did not occur because the actors are not children of this group. */
	public abstract boolean swapActor(Actor first, Actor second);

	/** Returns an ordered list of child actors in this group. */
	public abstract SnapshotArray<IActor> getChildren();

	public abstract boolean hasChildren();

	/** When true (the default), the Batch is transformed so children are drawn in their parent's coordinate system. This has a
	 * performance impact because {@link Batch#flush()} must be done before and after the transform. If the actors in a group are
	 * not rotated or scaled, then the transform for the group can be set to false. In this case, each child's position will be
	 * offset by the group's position for drawing, causing the children to appear in the correct location even though the Batch has
	 * not been transformed. */
	public abstract void setTransform(boolean transform);

	public abstract boolean isTransform();

	/** Converts coordinates for this group to those of a descendant actor. The descendant does not need to be a direct child.
	 * @throws IllegalArgumentException if the specified actor is not a descendant of this group. */
	public abstract Vector2 localToDescendantCoordinates(IActor descendant,
			Vector2 localCoords);

	/** Prints the actor hierarchy recursively for debugging purposes. */
	public abstract void print();

}