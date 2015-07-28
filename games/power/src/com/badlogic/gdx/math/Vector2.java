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

package com.badlogic.gdx.math;

import java.io.Serializable;

import com.badlogic.gdx.utils.NumberUtils;

/** Encapsulates a 2D vector. Allows chaining methods by returning a reference to itself
 * @author badlogicgames@gmail.com */
public class Vector2 implements Serializable, Vector<Vector2> {
	private static final long serialVersionUID = 913902788239530931L;

	public final static Vector2 X = new Vector2(1, 0);
	public final static Vector2 Y = new Vector2(0, 1);
	public final static Vector2 Zero = new Vector2(0, 0);

	/** the x-component of this vector **/
	private float x;
	/** the y-component of this vector **/
	private float y;

	/** Constructs a new vector at (0,0) */
	public Vector2 () {
	}

	/** Constructs a vector with the given components
	 * @param x The x-component
	 * @param y The y-component */
	public Vector2 (float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	/** Constructs a vector from the given vector
	 * @param v The vector */
	public Vector2 (Vector2 v) {
		set(v);
	}

	/** @return a copy of this vector */
	public Vector2 cpy () {
		return new Vector2(this);
	}

	/** @return The euclidian length */
	public float len () {
		return (float)Math.sqrt(getX() * getX() + getY() * getY());
	}

	/** @return The squared euclidian length */
	public float len2 () {
		return getX() * getX() + getY() * getY();
	}

	/** Sets this vector from the given vector
	 * @param v The vector
	 * @return This vector for chaining */
	public Vector2 set (Vector2 v) {
		setX(v.getX());
		setY(v.getY());
		return this;
	}

	/** Sets the components of this vector
	 * @param x The x-component
	 * @param y The y-component
	 * @return This vector for chaining */
	public Vector2 set (float x, float y) {
		this.setX(x);
		this.setY(y);
		return this;
	}

	/** Subtracts the given vector from this vector.
	 * @param v The vector
	 * @return This vector for chaining */
	public Vector2 sub (Vector2 v) {
		setX(getX() - v.getX());
		setY(getY() - v.getY());
		return this;
	}

	/** Normalizes this vector. Does nothing if it is zero.
	 * @return This vector for chaining */
	public Vector2 nor () {
		float len = len();
		if (len != 0) {
			setX(getX() / len);
			setY(getY() / len);
		}
		return this;
	}

	/** Adds the given vector to this vector
	 * @param v The vector
	 * @return This vector for chaining */
	public Vector2 add (Vector2 v) {
		setX(getX() + v.getX());
		setY(getY() + v.getY());
		return this;
	}

	/** Adds the given components to this vector
	 * @param x The x-component
	 * @param y The y-component
	 * @return This vector for chaining */
	public Vector2 add (float x, float y) {
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
		return this;
	}

	/** @param v The other vector
	 * @return The dot product between this and the other vector */
	public float dot (Vector2 v) {
		return getX() * v.getX() + getY() * v.getY();
	}

	/** Multiplies this vector by a scalar
	 * @param scalar The scalar
	 * @return This vector for chaining */
	public Vector2 scl (float scalar) {
		setX(getX() * scalar);
		setY(getY() * scalar);
		return this;
	}

	/** @deprecated Use {@link #scl(float)} instead. */
	public Vector2 mul (float scalar) {
		return scl(scalar);
	}

	/** Multiplies this vector by a scalar
	 * @return This vector for chaining */
	public Vector2 scl (float x, float y) {
		this.setX(this.getX() * x);
		this.setY(this.getY() * y);
		return this;
	}

	/** @deprecated Use {@link #scl(float, float)} instead. */
	public Vector2 mul (float x, float y) {
		return scl(x, y);
	}

	/** Multiplies this vector by a vector
	 * @return This vector for chaining */
	public Vector2 scl (Vector2 v) {
		this.setX(this.getX() * v.getX());
		this.setY(this.getY() * v.getY());
		return this;
	}

	/** @deprecated Use {@link #scl(Vector2)} instead. */
	public Vector2 mul (Vector2 v) {
		return scl(v);
	}

	public Vector2 div (float value) {
		return this.scl(1 / value);
	}

	public Vector2 div (float vx, float vy) {
		return this.scl(1 / vx, 1 / vy);
	}

	public Vector2 div (Vector2 other) {
		return this.scl(1 / other.getX(), 1 / other.getY());
	}

	/** @param v The other vector
	 * @return the distance between this and the other vector */
	public float dst (Vector2 v) {
		final float x_d = v.getX() - getX();
		final float y_d = v.getY() - getY();
		return (float)Math.sqrt(x_d * x_d + y_d * y_d);
	}

	/** @param x The x-component of the other vector
	 * @param y The y-component of the other vector
	 * @return the distance between this and the other vector */
	public float dst (float x, float y) {
		final float x_d = x - this.getX();
		final float y_d = y - this.getY();
		return (float)Math.sqrt(x_d * x_d + y_d * y_d);
	}

	/** @param v The other vector
	 * @return the squared distance between this and the other vector */
	public float dst2 (Vector2 v) {
		final float x_d = v.getX() - getX();
		final float y_d = v.getY() - getY();
		return x_d * x_d + y_d * y_d;
	}

	/** @param x The x-component of the other vector
	 * @param y The y-component of the other vector
	 * @return the squared distance between this and the other vector */
	public float dst2 (float x, float y) {
		final float x_d = x - this.getX();
		final float y_d = y - this.getY();
		return x_d * x_d + y_d * y_d;
	}

	/** Limits this vector's length to given value
	 * @param limit Max length
	 * @return This vector for chaining */
	public Vector2 limit (float limit) {
		if (len2() > limit * limit) {
			nor();
			scl(limit);
		}
		return this;
	}

	/** Clamps this vector's length to given value
	 * @param min Min length
	 * @param max Max length
	 * @return This vector for chaining */
	public Vector2 clamp (float min, float max) {
		final float l2 = len2();
		if (l2 == 0f) return this;
		if (l2 > max * max) return nor().scl(max);
		if (l2 < min * min) return nor().scl(min);
		return this;
	}

	public String toString () {
		return "[" + getX() + ":" + getY() + "]";
	}

	/** Substracts the other vector from this vector.
	 * @param x The x-component of the other vector
	 * @param y The y-component of the other vector
	 * @return This vector for chaining */
	public Vector2 sub (float x, float y) {
		this.setX(this.getX() - x);
		this.setY(this.getY() - y);
		return this;
	}

	/** Left-multiplies this vector by the given matrix
	 * @param mat the matrix
	 * @return this vector */
	public Vector2 mul (Matrix3 mat) {
		float x = this.getX() * mat.val[0] + this.getY() * mat.val[3] + mat.val[6];
		float y = this.getX() * mat.val[1] + this.getY() * mat.val[4] + mat.val[7];
		this.setX(x);
		this.setY(y);
		return this;
	}

	/** Calculates the 2D cross product between this and the given vector.
	 * @param v the other vector
	 * @return the cross product */
	public float crs (Vector2 v) {
		return this.getX() * v.getY() - this.getY() * v.getX();
	}

	/** Calculates the 2D cross product between this and the given vector.
	 * @param x the x-coordinate of the other vector
	 * @param y the y-coordinate of the other vector
	 * @return the cross product */
	public float crs (float x, float y) {
		return this.getX() * y - this.getY() * x;
	}

	/** @return the angle in degrees of this vector (point) relative to the x-axis. Angles are towards the positive y-axis (typically
	 *         counter-clockwise) and between 0 and 360. */
	public float angle () {
		float angle = (float)Math.atan2(getY(), getX()) * MathUtils.radiansToDegrees;
		if (angle < 0) angle += 360;
		return angle;
	}

	/** Sets the angle of the vector in degrees relative to the x-axis, towards the positive y-axis (typically counter-clockwise).
	 * @param degrees The angle to set. */
	public Vector2 setAngle (float degrees) {
		this.set(len(), 0f);
		this.rotate(degrees);

		return this;
	}

	/** Rotates the Vector2 by the given angle, counter-clockwise assuming the y-axis points up.
	 * @param degrees the angle in degrees */
	public Vector2 rotate (float degrees) {
		float rad = degrees * MathUtils.degreesToRadians;
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);

		float newX = this.getX() * cos - this.getY() * sin;
		float newY = this.getX() * sin + this.getY() * cos;

		this.setX(newX);
		this.setY(newY);

		return this;
	}

	/** Rotates the Vector2 by 90 degrees in the specified direction, where >= 0 is counter-clockwise and < 0 is clockwise. */
	public Vector2 rotate90 (int dir) {
		float x = this.getX();
		if (dir >= 0) {
			this.setX(-getY());
			setY(x);
		} else {
			this.setX(getY());
			setY(-x);
		}
		return this;
	}

	/** Linearly interpolates between this vector and the target vector by alpha which is in the range [0,1]. The result is stored
	 * in this vector.
	 * 
	 * @param target The target vector
	 * @param alpha The interpolation coefficient
	 * @return This vector for chaining. */
	public Vector2 lerp (Vector2 target, float alpha) {
		final float invAlpha = 1.0f - alpha;
		this.setX((getX() * invAlpha) + (target.getX() * alpha));
		this.setY((getY() * invAlpha) + (target.getY() * alpha));
		return this;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + NumberUtils.floatToIntBits(getX());
		result = prime * result + NumberUtils.floatToIntBits(getY());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vector2 other = (Vector2)obj;
		if (NumberUtils.floatToIntBits(getX()) != NumberUtils.floatToIntBits(other.getX())) return false;
		if (NumberUtils.floatToIntBits(getY()) != NumberUtils.floatToIntBits(other.getY())) return false;
		return true;
	}

	/** Compares this vector with the other vector, using the supplied epsilon for fuzzy equality testing.
	 * @param obj
	 * @param epsilon
	 * @return whether the vectors are the same. */
	public boolean epsilonEquals (Vector2 obj, float epsilon) {
		if (obj == null) return false;
		if (Math.abs(obj.getX() - getX()) > epsilon) return false;
		if (Math.abs(obj.getY() - getY()) > epsilon) return false;
		return true;
	}

	/** Compares this vector with the other vector, using the supplied epsilon for fuzzy equality testing.
	 * @param x
	 * @param y
	 * @param epsilon
	 * @return whether the vectors are the same. */
	public boolean epsilonEquals (float x, float y, float epsilon) {
		if (Math.abs(x - this.getX()) > epsilon) return false;
		if (Math.abs(y - this.getY()) > epsilon) return false;
		return true;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
