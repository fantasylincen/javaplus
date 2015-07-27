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

package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;

/** A body definition holds all the data needed to construct a rigid body. You can safely re-use body definitions. Shapes are added
 * to a body after construction.
 * 
 * @author mzechner */
public class BodyDef {
	/** The body type. static: zero mass, zero velocity, may be manually moved kinematic: zero mass, non-zero velocity set by user,
	 * moved by solver dynamic: positive mass, non-zero velocity determined by forces, moved by solver */
	public enum BodyType {
		StaticBody(0), KinematicBody(1), DynamicBody(2);

		private int value;

		private BodyType (int value) {
			this.value = value;
		}

		public int getValue () {
			return value;
		}
	};

	/** The body type: static, kinematic, or dynamic. Note: if a dynamic body would have zero mass, the mass is set to one. **/
	private BodyType type = BodyType.StaticBody;

	/** The world position of the body. Avoid creating bodies at the origin since this can lead to many overlapping shapes. **/
	private final Vector2 position = new Vector2();

	/** The world angle of the body in radians. **/
	private float angle = 0;

	/** The linear velocity of the body's origin in world co-ordinates. **/
	private final Vector2 linearVelocity = new Vector2();

	/** The angular velocity of the body. **/
	private float angularVelocity = 0;

	/** Linear damping is use to reduce the linear velocity. The damping parameter can be larger than 1.0f but the damping effect
	 * becomes sensitive to the time step when the damping parameter is large. **/
	private float linearDamping = 0;

	/** Angular damping is use to reduce the angular velocity. The damping parameter can be larger than 1.0f but the damping effect
	 * becomes sensitive to the time step when the damping parameter is large. **/
	private float angularDamping = 0;

	/** Set this flag to false if this body should never fall asleep. Note that this increases CPU usage. **/
	private boolean allowSleep = true;

	/** Is this body initially awake or sleeping? **/
	private boolean awake = true;

	/** Should this body be prevented from rotating? Useful for characters. **/
	private boolean fixedRotation = false;

	/** Is this a fast moving body that should be prevented from tunneling through other moving bodies? Note that all bodies are
	 * prevented from tunneling through kinematic and static bodies. This setting is only considered on dynamic bodies.
	 * @warning You should use this flag sparingly since it increases processing time. **/
	private boolean bullet = false;

	/** Does this body start out active? **/
	private boolean active = true;

	/** Scale the gravity applied to this body. **/
	private float gravityScale = 1;

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}

	public boolean isBullet() {
		return bullet;
	}

	public void setBullet(boolean bullet) {
		this.bullet = bullet;
	}

	public boolean isFixedRotation() {
		return fixedRotation;
	}

	public void setFixedRotation(boolean fixedRotation) {
		this.fixedRotation = fixedRotation;
	}

	public boolean isAwake() {
		return awake;
	}

	public void setAwake(boolean awake) {
		this.awake = awake;
	}

	public boolean isAllowSleep() {
		return allowSleep;
	}

	public void setAllowSleep(boolean allowSleep) {
		this.allowSleep = allowSleep;
	}

	public float getAngularDamping() {
		return angularDamping;
	}

	public void setAngularDamping(float angularDamping) {
		this.angularDamping = angularDamping;
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public void setLinearDamping(float linearDamping) {
		this.linearDamping = linearDamping;
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getGravityScale() {
		return gravityScale;
	}

	public void setGravityScale(float gravityScale) {
		this.gravityScale = gravityScale;
	}

	public BodyType getType() {
		return type;
	}

	public void setType(BodyType type) {
		this.type = type;
	}
}
