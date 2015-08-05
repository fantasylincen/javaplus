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

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

/** Base class for {@link OrthographicCamera} and {@link PerspectiveCamera}.
 * @author mzechner */
public abstract class Camera implements ICamera {
	/** the position of the camera **/
	public final Vector3 position = new Vector3();
	/** the unit length direction vector of the camera **/
	public final Vector3 direction = new Vector3(0, 0, -1);
	/** the unit length up vector of the camera **/
	public final Vector3 up = new Vector3(0, 1, 0);

	/** the projection matrix **/
	public final Matrix4 projection = new Matrix4();
	/** the view matrix **/
	public final Matrix4 view = new Matrix4();
	/** the combined projection and view matrix **/
	public final Matrix4 combined = new Matrix4();
	/** the inverse combined projection and view matrix **/
	public final Matrix4 invProjectionView = new Matrix4();

	/** the near clipping plane distance, has to be positive **/
	public float near = 1;
	/** the far clipping plane distance, has to be positive **/
	public float far = 100;

	/** the viewport width **/
	public float viewportWidth = 0;
	/** the viewport height **/
	public float viewportHeight = 0;

	/** the frustum **/
	public final Frustum frustum = new Frustum();

	private final Vector3 tmpVec = new Vector3();

	@Override
	public Vector3 getPosition() {
		return position;
	}
	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#update()
	 */
	@Override
	public abstract void update ();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#update(boolean)
	 */
	@Override
	public abstract void update (boolean updateFrustum);

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#apply(com.badlogic.gdx.graphics.GL10)
	 */
	@Override
	public void apply (GL10 gl) {
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadMatrixf(projection.val, 0);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadMatrixf(view.val, 0);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#lookAt(float, float, float)
	 */
	@Override
	public void lookAt (float x, float y, float z) {
		direction.set(x, y, z).sub(position).nor();
		normalizeUp();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#lookAt(com.badlogic.gdx.math.Vector3)
	 */
	@Override
	public void lookAt (Vector3 target) {
		direction.set(target).sub(position).nor();
		normalizeUp();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#normalizeUp()
	 */
	@Override
	public void normalizeUp () {
		tmpVec.set(direction).crs(up).nor();
		up.set(tmpVec).crs(direction).nor();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#rotate(float, float, float, float)
	 */
	@Override
	public void rotate (float angle, float axisX, float axisY, float axisZ) {
		direction.rotate(angle, axisX, axisY, axisZ);
		up.rotate(angle, axisX, axisY, axisZ);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#rotate(com.badlogic.gdx.math.Vector3, float)
	 */
	@Override
	public void rotate (Vector3 axis, float angle) {
		direction.rotate(axis, angle);
		up.rotate(axis, angle);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#rotate(com.badlogic.gdx.math.Matrix4)
	 */
	@Override
	public void rotate (final Matrix4 transform) {
		direction.rot(transform);
		up.rot(transform);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#rotate(com.badlogic.gdx.math.Quaternion)
	 */
	@Override
	public void rotate (final Quaternion quat) {
		quat.transform(direction);
		quat.transform(up);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#rotateAround(com.badlogic.gdx.math.Vector3, com.badlogic.gdx.math.Vector3, float)
	 */
	@Override
	public void rotateAround (Vector3 point, Vector3 axis, float angle) {
		tmpVec.set(point);
		tmpVec.sub(position);
		translate(tmpVec);
		rotate(axis, angle);
		tmpVec.rotate(axis, angle);
		translate(-tmpVec.x, -tmpVec.y, -tmpVec.z);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#transform(com.badlogic.gdx.math.Matrix4)
	 */
	@Override
	public void transform (final Matrix4 transform) {
		position.mul(transform);
		rotate(transform);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#translate(float, float, float)
	 */
	@Override
	public void translate (float x, float y, float z) {
		position.add(x, y, z);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#translate(com.badlogic.gdx.math.Vector3)
	 */
	@Override
	public void translate (Vector3 vec) {
		position.add(vec);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#unproject(com.badlogic.gdx.math.Vector3, float, float, float, float)
	 */
	@Override
	public void unproject (Vector3 vec, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
		float x = vec.x, y = vec.y;
		x = x - viewportX;
		y = Gdx.graphics.getHeight() - y - 1;
		y = y - viewportY;
		vec.x = (2 * x) / viewportWidth - 1;
		vec.y = (2 * y) / viewportHeight - 1;
		vec.z = 2 * vec.z - 1;
		vec.prj(invProjectionView);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#unproject(com.badlogic.gdx.math.Vector3)
	 */
	@Override
	public void unproject (Vector3 vec) {
		unproject(vec, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#project(com.badlogic.gdx.math.Vector3)
	 */
	@Override
	public void project (Vector3 vec) {
		project(vec, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#project(com.badlogic.gdx.math.Vector3, float, float, float, float)
	 */
	@Override
	public void project (Vector3 vec, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
		vec.prj(combined);
		vec.x = viewportWidth * (vec.x + 1) / 2 + viewportX;
		vec.y = viewportHeight * (vec.y + 1) / 2 + viewportY;
		vec.z = (vec.z + 1) / 2;
	}

	final Ray ray = new Ray(new Vector3(), new Vector3());

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#getPickRay(float, float, float, float, float, float)
	 */
	@Override
	public Ray getPickRay (float x, float y, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
		unproject(ray.origin.set(x, y, 0), viewportX, viewportY, viewportWidth, viewportHeight);
		unproject(ray.direction.set(x, y, 1), viewportX, viewportY, viewportWidth, viewportHeight);
		ray.direction.sub(ray.origin).nor();
		return ray;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.graphics.ICamera#getPickRay(float, float)
	 */
	@Override
	public Ray getPickRay (float x, float y) {
		return getPickRay(x, y, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public Matrix4 getCombined() {
		return combined;
	}

	public void setViewportHeight(float height) {
		this.viewportHeight = height;
	}

	public void setViewportWidth(float width) {
		this.viewportWidth = width;
	}

	public void setPosition(float centerX, float centerY, int z) {
		this.position.set(centerX, centerY, z);
	}
	
	@Override
	public float getViewPortHeight() {
		return viewportHeight;
	}
}
