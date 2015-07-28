package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public interface IBody {

	/** Creates a fixture and attach it to this body. Use this function if you need to set some fixture parameters, like friction.
	 * Otherwise you can create the fixture directly from a shape. If the density is non-zero, this function automatically updates
	 * the mass of the body. Contacts are not created until the next time step.
	 * @param def the fixture definition.
	 * @warning This function is locked during callbacks. */
	public abstract Fixture createFixture(FixtureDef def);

	/** Creates a fixture from a shape and attach it to this body. This is a convenience function. Use b2FixtureDef if you need to
	 * set parameters like friction, restitution, user data, or filtering. If the density is non-zero, this function automatically
	 * updates the mass of the body.
	 * @param shape the shape to be cloned.
	 * @param density the shape density (set to zero for static bodies).
	 * @warning This function is locked during callbacks. */
	public abstract Fixture createFixture(Shape shape, float density);

	/** Destroy a fixture. This removes the fixture from the broad-phase and destroys all contacts associated with this fixture.
	 * This will automatically adjust the mass of the body if the body is dynamic and the fixture has positive density. All
	 * fixtures attached to a body are implicitly destroyed when the body is destroyed.
	 * @param fixture the fixture to be removed.
	 * @warning This function is locked during callbacks. */
	public abstract void destroyFixture(Fixture fixture);

	/** Set the position of the body's origin and rotation. This breaks any contacts and wakes the other bodies. Manipulating a
	 * body's transform may cause non-physical behavior.
	 * @param position the world position of the body's local origin.
	 * @param angle the world rotation in radians. */
	public abstract void setTransform(Vector2 position, float angle);

	/** Set the position of the body's origin and rotation. This breaks any contacts and wakes the other bodies. Manipulating a
	 * body's transform may cause non-physical behavior.
	 * @param x the world position on the x-axis
	 * @param y the world position on the y-axis
	 * @param angle the world rotation in radians. */
	public abstract void setTransform(float x, float y, float angle);

	/** Get the body transform for the body's origin. */
	public abstract Transform getTransform();

	/** Get the world body origin position.
	 * @return the world position of the body's origin. */
	public abstract Vector2 getPosition();

	/** Get the angle in radians.
	 * @return the current world rotation angle in radians. */
	public abstract float getAngle();

	/** Get the world position of the center of mass. */
	public abstract Vector2 getWorldCenter();

	/** Get the local position of the center of mass. */
	public abstract Vector2 getLocalCenter();

	/** Set the linear velocity of the center of mass. */
	public abstract void setLinearVelocity(Vector2 v);

	/** Set the linear velocity of the center of mass. */
	public abstract void setLinearVelocity(float vX, float vY);

	/** Get the linear velocity of the center of mass. */
	public abstract Vector2 getLinearVelocity();

	/** Set the angular velocity. */
	public abstract void setAngularVelocity(float omega);

	/** Get the angular velocity. */
	public abstract float getAngularVelocity();

	/** Apply a force at a world point. If the force is not applied at the center of mass, it will generate a torque and affect the
	 * angular velocity. This wakes up the body.
	 * @param force the world force vector, usually in Newtons (N).
	 * @param point the world position of the point of application.
	 * @param wake up the body */
	public abstract void applyForce(Vector2 force, Vector2 point, boolean wake);

	/** Apply a force at a world point. If the force is not applied at the center of mass, it will generate a torque and affect the
	 * angular velocity. This wakes up the body.
	 * @param forceX the world force vector on x, usually in Newtons (N).
	 * @param forceY the world force vector on y, usually in Newtons (N).
	 * @param pointX the world position of the point of application on x.
	 * @param pointY the world position of the point of application on y. 
	 * @param wake up the body*/
	public abstract void applyForce(float forceX, float forceY, float pointX,
			float pointY, boolean wake);

	/** Apply a force to the center of mass. This wakes up the body.
	 * @param force the world force vector, usually in Newtons (N). */
	public abstract void applyForceToCenter(Vector2 force, boolean wake);

	/** Apply a force to the center of mass. This wakes up the body.
	 * @param forceX the world force vector, usually in Newtons (N).
	 * @param forceY the world force vector, usually in Newtons (N). */
	public abstract void applyForceToCenter(float forceX, float forceY,
			boolean wake);

	/** Apply a torque. This affects the angular velocity without affecting the linear velocity of the center of mass. This wakes up
	 * the body.
	 * @param torque about the z-axis (out of the screen), usually in N-m.
	 * @param wake up the body */
	public abstract void applyTorque(float torque, boolean wake);

	/** Apply an impulse at a point. This immediately modifies the velocity. It also modifies the angular velocity if the point of
	 * application is not at the center of mass. This wakes up the body.
	 * @param impulse the world impulse vector, usually in N-seconds or kg-m/s.
	 * @param point the world position of the point of application. 
	 * @param wake up the body*/
	public abstract void applyLinearImpulse(Vector2 impulse, Vector2 point,
			boolean wake);

	/** Apply an impulse at a point. This immediately modifies the velocity. It also modifies the angular velocity if the point of
	 * application is not at the center of mass. This wakes up the body.
	 * @param impulseX the world impulse vector on the x-axis, usually in N-seconds or kg-m/s.
	 * @param impulseY the world impulse vector on the y-axis, usually in N-seconds or kg-m/s.
	 * @param pointX the world position of the point of application on the x-axis.
	 * @param pointY the world position of the point of application on the y-axis. 
	 * @param wake up the body*/
	public abstract void applyLinearImpulse(float impulseX, float impulseY,
			float pointX, float pointY, boolean wake);

	/** Apply an angular impulse.
	 * @param impulse the angular impulse in units of kg*m*m/s */
	public abstract void applyAngularImpulse(float impulse, boolean wake);

	/** Get the total mass of the body.
	 * @return the mass, usually in kilograms (kg). */
	public abstract float getMass();

	/** Get the rotational inertia of the body about the local origin.
	 * @return the rotational inertia, usually in kg-m^2. */
	public abstract float getInertia();

	/** Get the mass data of the body.
	 * @return a struct containing the mass, inertia and center of the body. */
	public abstract MassData getMassData();

	/** Set the mass properties to override the mass properties of the fixtures. Note that this changes the center of mass position.
	 * Note that creating or destroying fixtures can also alter the mass. This function has no effect if the body isn't dynamic.
	 * @param data the mass properties. */
	public abstract void setMassData(MassData data);

	/** This resets the mass properties to the sum of the mass properties of the fixtures. This normally does not need to be called
	 * unless you called SetMassData to override the mass and you later want to reset the mass. */
	public abstract void resetMassData();

	/** Get the world coordinates of a point given the local coordinates.
	 * @param localPoint a point on the body measured relative the the body's origin.
	 * @return the same point expressed in world coordinates. */
	public abstract Vector2 getWorldPoint(Vector2 localPoint);

	/** Get the world coordinates of a vector given the local coordinates.
	 * @param localVector a vector fixed in the body.
	 * @return the same vector expressed in world coordinates. */
	public abstract Vector2 getWorldVector(Vector2 localVector);

	/** Gets a local point relative to the body's origin given a world point.
	 * @param worldPoint a point in world coordinates.
	 * @return the corresponding local point relative to the body's origin. */
	public abstract Vector2 getLocalPoint(Vector2 worldPoint);

	/** Gets a local vector given a world vector.
	 * @param worldVector a vector in world coordinates.
	 * @return the corresponding local vector. */
	public abstract Vector2 getLocalVector(Vector2 worldVector);

	/** Get the world linear velocity of a world point attached to this body.
	 * @param worldPoint a point in world coordinates.
	 * @return the world velocity of a point. */
	public abstract Vector2 getLinearVelocityFromWorldPoint(Vector2 worldPoint);

	/** Get the world velocity of a local point.
	 * @param localPoint a point in local coordinates.
	 * @return the world velocity of a point. */
	public abstract Vector2 getLinearVelocityFromLocalPoint(Vector2 localPoint);

	/** Get the linear damping of the body. */
	public abstract float getLinearDamping();

	/** Set the linear damping of the body. */
	public abstract void setLinearDamping(float linearDamping);

	/** Get the angular damping of the body. */
	public abstract float getAngularDamping();

	/** Set the angular damping of the body. */
	public abstract void setAngularDamping(float angularDamping);

	/** Set the type of this body. This may alter the mass and velocity. */
	public abstract void setType(BodyType type);

	/** Get the type of this body. */
	public abstract BodyType getType();

	/** Should this body be treated like a bullet for continuous collision detection? */
	public abstract void setBullet(boolean flag);

	/** Is this body treated like a bullet for continuous collision detection? */
	public abstract boolean isBullet();

	/** You can disable sleeping on this body. If you disable sleeping, the */
	public abstract void setSleepingAllowed(boolean flag);

	/** Is this body allowed to sleep */
	public abstract boolean isSleepingAllowed();

	/** Set the sleep state of the body. A sleeping body has very low CPU cost.
	 * @param flag set to true to put body to sleep, false to wake it. */
	public abstract void setAwake(boolean flag);

	/** Get the sleeping state of this body.
	 * @return true if the body is sleeping. */
	public abstract boolean isAwake();

	/** Set the active state of the body. An inactive body is not simulated and cannot be collided with or woken up. If you pass a
	 * flag of true, all fixtures will be added to the broad-phase. If you pass a flag of false, all fixtures will be removed from
	 * the broad-phase and all contacts will be destroyed. Fixtures and joints are otherwise unaffected. You may continue to
	 * create/destroy fixtures and joints on inactive bodies. Fixtures on an inactive body are implicitly inactive and will not
	 * participate in collisions, ray-casts, or queries. Joints connected to an inactive body are implicitly inactive. An inactive
	 * body is still owned by a b2World object and remains in the body list. */
	public abstract void setActive(boolean flag);

	/** Get the active state of the body. */
	public abstract boolean isActive();

	/** Set this body to have fixed rotation. This causes the mass to be reset. */
	public abstract void setFixedRotation(boolean flag);

	/** Does this body have fixed rotation? */
	public abstract boolean isFixedRotation();

	/** Get the list of all fixtures attached to this body. Do not modify the list! */
	public abstract Array<Fixture> getFixtureList();

	/** Get the list of all joints attached to this body. Do not modify the list! */
	public abstract Array<JointEdge> getJointList();

	/** @return Get the gravity scale of the body. */
	public abstract float getGravityScale();

	/** Sets the gravity scale of the body */
	public abstract void setGravityScale(float scale);

	/** Get the parent world of this body. */
	public abstract World getWorld();

	/** Get the user data */
	public abstract Object getUserData();

	/** Set the user data */
	public abstract void setUserData(Object userData);

	public abstract Vector2 getLinVelLoc();

	public abstract Vector2 getLinVelWorld();

	public abstract Vector2 getLocalPoint2();

	public abstract Vector2 getLocalVector();

}