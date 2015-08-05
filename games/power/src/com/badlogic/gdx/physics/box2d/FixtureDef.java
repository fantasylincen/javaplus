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

/** A fixture definition is used to create a fixture. This class defines an abstract fixture definition. You can reuse fixture
 * definitions safely.
 * @author mzechner */
public class FixtureDef {
	/** The shape, this must be set. The shape will be cloned, so you can create the shape on the stack. */
	private Shape shape;

	/** The friction coefficient, usually in the range [0,1]. **/
	private float friction = 0.2f;

	/** The restitution (elasticity) usually in the range [0,1]. **/
	private float restitution = 0;

	/** The density, usually in kg/m^2. **/
	private float density = 0;

	/** A sensor shape collects contact information but never generates a collision response. */
	private boolean isSensor = false;

	/** Contact filtering data. **/
	private final Filter filter = new Filter();

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public boolean isSensor() {
		return isSensor;
	}

	public void setSensor(boolean isSensor) {
		this.isSensor = isSensor;
	}

	public Filter getFilter() {
		return filter;
	}
}
