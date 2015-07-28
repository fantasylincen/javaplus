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
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

/** A rigid body. These are created via World.CreateBody.
 * @author mzechner */
public class Body implements IBody {
	// @off
	/*JNI
#include <Box2D/Box2D.h>
	 */
	
	/** the address of the body **/
	protected long addr;

	/** temporary float array **/
	private final float[] tmp = new float[4];

	/** World **/
	private final World world;

	/** Fixtures of this body **/
	private Array<Fixture> fixtures = new Array<Fixture>(2);

	/** Joints of this body **/
	protected Array<JointEdge> joints = new Array<JointEdge>(2);

	/** user data **/
	private Object userData;

	/** Constructs a new body with the given address
	 * @param world the world
	 * @param addr the address */
	protected Body (World world, long addr) {
		this.world = world;
		this.addr = addr;
	}

	/** Resets this body after fetching it from the {@link World#freeBodies} Pool. */
	protected void reset (long addr) {
		this.addr = addr;
		this.userData = null;
		for (int i = 0; i < fixtures.size; i++)
			this.world.freeFixtures.free(fixtures.get(i));
		fixtures.clear();
		this.joints.clear();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#createFixture(com.badlogic.gdx.physics.box2d.FixtureDef)
	 */
	@Override
	public Fixture createFixture (FixtureDef def) {
		long fixtureAddr = jniCreateFixture(addr, def.getShape().addr, def.getFriction(), def.getRestitution(), def.getDensity(), def.isSensor(),
			def.getFilter().categoryBits, def.getFilter().maskBits, def.getFilter().groupIndex);
		Fixture fixture = this.world.freeFixtures.obtain();
		fixture.reset(this, fixtureAddr);
		this.world.fixtures.put(fixture.addr, fixture);
		this.fixtures.add(fixture);
		return fixture;
	}

	private native long jniCreateFixture (long addr, long shapeAddr, float friction, float restitution, float density,
		boolean isSensor, short filterCategoryBits, short filterMaskBits, short filterGroupIndex); /*
	b2Body* body = (b2Body*)addr;
	b2Shape* shape = (b2Shape*)shapeAddr;
	b2FixtureDef fixtureDef;

	fixtureDef.shape = shape;
	fixtureDef.friction = friction;
	fixtureDef.restitution = restitution;
	fixtureDef.density = density;
	fixtureDef.isSensor = isSensor;
	fixtureDef.filter.maskBits = filterMaskBits;
	fixtureDef.filter.categoryBits = filterCategoryBits;
	fixtureDef.filter.groupIndex = filterGroupIndex;

	return (jlong)body->CreateFixture( &fixtureDef );
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#createFixture(com.badlogic.gdx.physics.box2d.Shape, float)
	 */
	@Override
	public Fixture createFixture (Shape shape, float density) {
		long fixtureAddr = jniCreateFixture(addr, shape.addr, density);
		Fixture fixture = this.world.freeFixtures.obtain();
		fixture.reset(this, fixtureAddr);
		this.world.fixtures.put(fixture.addr, fixture);
		this.fixtures.add(fixture);
		return fixture;
	}

	private native long jniCreateFixture (long addr, long shapeAddr, float density); /*
		b2Body* body = (b2Body*)addr;
		b2Shape* shape = (b2Shape*)shapeAddr;
		return (jlong)body->CreateFixture( shape, density );
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#destroyFixture(com.badlogic.gdx.physics.box2d.Fixture)
	 */
	@Override
	public void destroyFixture (Fixture fixture) {
		jniDestroyFixture(addr, fixture.addr);
		this.world.fixtures.remove(fixture.addr);
		this.fixtures.removeValue(fixture, true);
		this.world.freeFixtures.free(fixture);
	}

	private native void jniDestroyFixture (long addr, long fixtureAddr); /*
		b2Body* body = (b2Body*)addr;
		b2Fixture* fixture = (b2Fixture*)fixtureAddr;
		body->DestroyFixture(fixture);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setTransform(com.badlogic.gdx.math.Vector2, float)
	 */
	@Override
	public void setTransform (Vector2 position, float angle) {
		jniSetTransform(addr, position.getX(), position.getY(), angle);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setTransform(float, float, float)
	 */
	@Override
	public void setTransform (float x, float y, float angle) {
		jniSetTransform(addr, x, y, angle);
	}

	private native void jniSetTransform (long addr, float positionX, float positionY, float angle); /*
		b2Body* body = (b2Body*)addr;
		body->SetTransform(b2Vec2(positionX, positionY), angle);
	*/

	private final Transform transform = new Transform();
	
	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getTransform()
	 */
	@Override
	public Transform getTransform () {
		jniGetTransform(addr, transform.vals);
		return transform;
	}

	private native void jniGetTransform (long addr, float[] vals); /*
		b2Body* body = (b2Body*)addr;
		b2Transform t = body->GetTransform();
		vals[0] = t.p.x;
		vals[1] = t.p.y;
		vals[2] = t.q.c;
		vals[3] = t.q.s;
	*/

	private final Vector2 position = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getPosition()
	 */
	@Override
	public Vector2 getPosition () {
		jniGetPosition(addr, tmp);
		position.setX(tmp[0]);
		position.setY(tmp[1]);
		return position;
	}

	private native void jniGetPosition (long addr, float[] position); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 p = body->GetPosition();
		position[0] = p.x;
		position[1] = p.y;
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getAngle()
	 */
	@Override
	public float getAngle () {
		return jniGetAngle(addr);
	}

	private native float jniGetAngle (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetAngle();
	*/

	private final Vector2 worldCenter = new Vector2();
	
	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getWorldCenter()
	 */
	@Override
	public Vector2 getWorldCenter () {
		jniGetWorldCenter(addr, tmp);
		worldCenter.setX(tmp[0]);
		worldCenter.setY(tmp[1]);
		return worldCenter;
	}

	private native void jniGetWorldCenter (long addr, float[] worldCenter); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetWorldCenter();
		worldCenter[0] = w.x;
		worldCenter[1] = w.y;
	*/

	private final Vector2 localCenter = new Vector2();
	
	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLocalCenter()
	 */
	@Override
	public Vector2 getLocalCenter () {
		jniGetLocalCenter(addr, tmp);
		localCenter.setX(tmp[0]);
		localCenter.setY(tmp[1]);
		return localCenter;
	}

	private native void jniGetLocalCenter (long addr, float[] localCenter); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetLocalCenter();
		localCenter[0] = w.x;
		localCenter[1] = w.y;
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setLinearVelocity(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public void setLinearVelocity (Vector2 v) {
		jniSetLinearVelocity(addr, v.getX(), v.getY());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setLinearVelocity(float, float)
	 */
	@Override
	public void setLinearVelocity (float vX, float vY) {
		jniSetLinearVelocity(addr, vX, vY);
	}

	private native void jniSetLinearVelocity (long addr, float x, float y); /*
		b2Body* body = (b2Body*)addr;
		body->SetLinearVelocity(b2Vec2(x, y));
	*/

	private final Vector2 linearVelocity = new Vector2();
	
	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinearVelocity()
	 */
	@Override
	public Vector2 getLinearVelocity () {
		jniGetLinearVelocity(addr, tmp);
		linearVelocity.setX(tmp[0]);
		linearVelocity.setY(tmp[1]);
		return linearVelocity;
	}

	private native void jniGetLinearVelocity (long addr, float[] linearVelocity); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 l = body->GetLinearVelocity();
		linearVelocity[0] = l.x;
		linearVelocity[1] = l.y;
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setAngularVelocity(float)
	 */
	@Override
	public void setAngularVelocity (float omega) {
		jniSetAngularVelocity(addr, omega);
	}

	private native void jniSetAngularVelocity (long addr, float omega); /*
		b2Body* body = (b2Body*)addr;
		body->SetAngularVelocity(omega);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getAngularVelocity()
	 */
	@Override
	public float getAngularVelocity () {
		return jniGetAngularVelocity(addr);
	}

	private native float jniGetAngularVelocity (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetAngularVelocity();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyForce(com.badlogic.gdx.math.Vector2, com.badlogic.gdx.math.Vector2, boolean)
	 */
	@Override
	public void applyForce (Vector2 force, Vector2 point, boolean wake) {
		jniApplyForce(addr, force.getX(), force.getY(), point.getX(), point.getY(), wake);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyForce(float, float, float, float, boolean)
	 */
	@Override
	public void applyForce (float forceX, float forceY, float pointX, float pointY, boolean wake) {
		jniApplyForce(addr, forceX, forceY, pointX, pointY, wake);
	}

	private native void jniApplyForce (long addr, float forceX, float forceY, float pointX, float pointY, boolean wake); /*
		b2Body* body = (b2Body*)addr;
		body->ApplyForce(b2Vec2(forceX, forceY), b2Vec2(pointX, pointY), wake);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyForceToCenter(com.badlogic.gdx.math.Vector2, boolean)
	 */
	@Override
	public void applyForceToCenter (Vector2 force, boolean wake) {
		jniApplyForceToCenter(addr, force.getX(), force.getY(), wake);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyForceToCenter(float, float, boolean)
	 */
	@Override
	public void applyForceToCenter (float forceX, float forceY, boolean wake) {
		jniApplyForceToCenter(addr, forceX, forceY, wake);
	}

	private native void jniApplyForceToCenter (long addr, float forceX, float forceY, boolean wake); /*
		b2Body* body = (b2Body*)addr;
		body->ApplyForceToCenter(b2Vec2(forceX, forceY), wake);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyTorque(float, boolean)
	 */
	@Override
	public void applyTorque (float torque, boolean wake) {
		jniApplyTorque(addr, torque, wake);
	}

	private native void jniApplyTorque (long addr, float torque, boolean wake); /*
		b2Body* body = (b2Body*)addr;
		body->ApplyTorque(torque, wake);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyLinearImpulse(com.badlogic.gdx.math.Vector2, com.badlogic.gdx.math.Vector2, boolean)
	 */
	@Override
	public void applyLinearImpulse (Vector2 impulse, Vector2 point, boolean wake) {
		jniApplyLinearImpulse(addr, impulse.getX(), impulse.getY(), point.getX(), point.getY(), wake);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyLinearImpulse(float, float, float, float, boolean)
	 */
	@Override
	public void applyLinearImpulse (float impulseX, float impulseY, float pointX, float pointY, boolean wake) {
		jniApplyLinearImpulse(addr, impulseX, impulseY, pointX, pointY, wake);
	}

	private native void jniApplyLinearImpulse (long addr, float impulseX, float impulseY, float pointX, float pointY, boolean wake); /*
		b2Body* body = (b2Body*)addr;
		body->ApplyLinearImpulse( b2Vec2( impulseX, impulseY ), b2Vec2( pointX, pointY ), wake);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#applyAngularImpulse(float, boolean)
	 */
	@Override
	public void applyAngularImpulse (float impulse, boolean wake) {
		jniApplyAngularImpulse(addr, impulse, wake);
	}

	private native void jniApplyAngularImpulse (long addr, float impulse, boolean wake); /*
		b2Body* body = (b2Body*)addr;
		body->ApplyAngularImpulse(impulse, wake);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getMass()
	 */
	@Override
	public float getMass () {
		return jniGetMass(addr);
	}

	private native float jniGetMass (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetMass();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getInertia()
	 */
	@Override
	public float getInertia () {
		return jniGetInertia(addr);
	}

	private native float jniGetInertia (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetInertia();
	*/

	private final MassData massData = new MassData();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getMassData()
	 */
	@Override
	public MassData getMassData () {
		jniGetMassData(addr, tmp);
		massData.setMass(tmp[0]);
		massData.getCenter().setX(tmp[1]);
		massData.getCenter().setY(tmp[2]);
		massData.setI(tmp[3]);
		return massData;
	}

	private native void jniGetMassData (long addr, float[] massData); /*
		b2Body* body = (b2Body*)addr;
		b2MassData m;
		body->GetMassData(&m);
		massData[0] = m.mass;
		massData[1] = m.center.x;
		massData[2] = m.center.y;
		massData[3] = m.I;
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setMassData(com.badlogic.gdx.physics.box2d.MassData)
	 */
	@Override
	public void setMassData (MassData data) {
		jniSetMassData(addr, data.getMass(), data.getCenter().getX(), data.getCenter().getY(), data.getI());
	}

	private native void jniSetMassData (long addr, float mass, float centerX, float centerY, float I); /*
		b2Body* body = (b2Body*)addr;
		b2MassData m;
		m.mass = mass;
		m.center.x = centerX;
		m.center.y = centerY;
		m.I = I;
		body->SetMassData(&m);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#resetMassData()
	 */
	@Override
	public void resetMassData () {
		jniResetMassData(addr);
	}

	private native void jniResetMassData (long addr); /*
		b2Body* body = (b2Body*)addr;
		body->ResetMassData();
	*/

	private final Vector2 localPoint = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getWorldPoint(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getWorldPoint (Vector2 localPoint) {
		jniGetWorldPoint(addr, localPoint.getX(), localPoint.getY(), tmp);
		this.localPoint.setX(tmp[0]);
		this.localPoint.setY(tmp[1]);
		return this.localPoint;
	}

	private native void jniGetWorldPoint (long addr, float localPointX, float localPointY, float[] worldPoint); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetWorldPoint( b2Vec2( localPointX, localPointY ) );
		worldPoint[0] = w.x;
		worldPoint[1] = w.y;
	*/

	private final Vector2 worldVector = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getWorldVector(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getWorldVector (Vector2 localVector) {
		jniGetWorldVector(addr, localVector.getX(), localVector.getY(), tmp);
		worldVector.setX(tmp[0]);
		worldVector.setY(tmp[1]);
		return worldVector;
	}

	private native void jniGetWorldVector (long addr, float localVectorX, float localVectorY, float[] worldVector); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetWorldVector( b2Vec2( localVectorX, localVectorY ) );
		worldVector[0] = w.x;
		worldVector[1] = w.y;
	*/

	private final Vector2 localPoint2 = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLocalPoint(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getLocalPoint (Vector2 worldPoint) {
		jniGetLocalPoint(addr, worldPoint.getX(), worldPoint.getY(), tmp);
		getLocalPoint2().setX(tmp[0]);
		getLocalPoint2().setY(tmp[1]);
		return getLocalPoint2();
	}

	private native void jniGetLocalPoint (long addr, float worldPointX, float worldPointY, float[] localPoint); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetLocalPoint( b2Vec2( worldPointX, worldPointY ) );
		localPoint[0] = w.x;
		localPoint[1] = w.y;
	*/

	private final Vector2 localVector = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLocalVector(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getLocalVector (Vector2 worldVector) {
		jniGetLocalVector(addr, worldVector.getX(), worldVector.getY(), tmp);
		localVector.setX(tmp[0]);
		localVector.setY(tmp[1]);
		return localVector;
	}

	private native void jniGetLocalVector (long addr, float worldVectorX, float worldVectorY, float[] worldVector); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetLocalVector( b2Vec2( worldVectorX, worldVectorY ) );
		worldVector[0] = w.x;
		worldVector[1] = w.y;
	*/

	private final Vector2 linVelWorld = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinearVelocityFromWorldPoint(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getLinearVelocityFromWorldPoint (Vector2 worldPoint) {
		jniGetLinearVelocityFromWorldPoint(addr, worldPoint.getX(), worldPoint.getY(), tmp);
		getLinVelWorld().setX(tmp[0]);
		getLinVelWorld().setY(tmp[1]);
		return getLinVelWorld();
	}

	private native void jniGetLinearVelocityFromWorldPoint (long addr, float worldPointX, float worldPointY, float[] linVelWorld); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetLinearVelocityFromWorldPoint( b2Vec2( worldPointX, worldPointY ) );
		linVelWorld[0] = w.x;
		linVelWorld[1] = w.y;
	*/

	private final Vector2 linVelLoc = new Vector2();

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinearVelocityFromLocalPoint(com.badlogic.gdx.math.Vector2)
	 */
	@Override
	public Vector2 getLinearVelocityFromLocalPoint (Vector2 localPoint) {
		jniGetLinearVelocityFromLocalPoint(addr, localPoint.getX(), localPoint.getY(), tmp);
		getLinVelLoc().setX(tmp[0]);
		getLinVelLoc().setY(tmp[1]);
		return getLinVelLoc();
	}

	private native void jniGetLinearVelocityFromLocalPoint (long addr, float localPointX, float localPointY, float[] linVelLoc); /*
		b2Body* body = (b2Body*)addr;
		b2Vec2 w = body->GetLinearVelocityFromLocalPoint( b2Vec2( localPointX, localPointY ) );
		linVelLoc[0] = w.x;
		linVelLoc[1] = w.y;
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinearDamping()
	 */
	@Override
	public float getLinearDamping () {
		return jniGetLinearDamping(addr);
	}

	private native float jniGetLinearDamping (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetLinearDamping();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setLinearDamping(float)
	 */
	@Override
	public void setLinearDamping (float linearDamping) {
		jniSetLinearDamping(addr, linearDamping);
	}

	private native void jniSetLinearDamping (long addr, float linearDamping); /*
		b2Body* body = (b2Body*)addr;
		body->SetLinearDamping(linearDamping);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getAngularDamping()
	 */
	@Override
	public float getAngularDamping () {
		return jniGetAngularDamping(addr);
	}

	private native float jniGetAngularDamping (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetAngularDamping();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setAngularDamping(float)
	 */
	@Override
	public void setAngularDamping (float angularDamping) {
		jniSetAngularDamping(addr, angularDamping);
	}

	private native void jniSetAngularDamping (long addr, float angularDamping); /*
		b2Body* body = (b2Body*)addr;
		body->SetAngularDamping(angularDamping);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setType(com.badlogic.gdx.physics.box2d.BodyDef.BodyType)
	 */
	@Override
	public void setType (BodyType type) {
		jniSetType(addr, type.getValue());
	}
	
	// @off
	/*JNI
inline b2BodyType getBodyType( int type )
{
	switch( type )
	{
	case 0: return b2_staticBody;
	case 1: return b2_kinematicBody;
	case 2: return b2_dynamicBody;
	default:
		return b2_staticBody;
	}
}	 
*/

	private native void jniSetType (long addr, int type); /*
		b2Body* body = (b2Body*)addr;
		body->SetType(getBodyType(type));
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getType()
	 */
	@Override
	public BodyType getType () {
		int type = jniGetType(addr);
		if (type == 0) return BodyType.StaticBody;
		if (type == 1) return BodyType.KinematicBody;
		if (type == 2) return BodyType.DynamicBody;
		return BodyType.StaticBody;
	}

	private native int jniGetType (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetType();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setBullet(boolean)
	 */
	@Override
	public void setBullet (boolean flag) {
		jniSetBullet(addr, flag);
	}

	private native void jniSetBullet (long addr, boolean flag); /*
		b2Body* body = (b2Body*)addr;
		body->SetBullet(flag);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#isBullet()
	 */
	@Override
	public boolean isBullet () {
		return jniIsBullet(addr);
	}

	private native boolean jniIsBullet (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->IsBullet();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setSleepingAllowed(boolean)
	 */
	@Override
	public void setSleepingAllowed (boolean flag) {
		jniSetSleepingAllowed(addr, flag);
	}

	private native void jniSetSleepingAllowed (long addr, boolean flag); /*
		b2Body* body = (b2Body*)addr;
		body->SetSleepingAllowed(flag);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#isSleepingAllowed()
	 */
	@Override
	public boolean isSleepingAllowed () {
		return jniIsSleepingAllowed(addr);
	}

	private native boolean jniIsSleepingAllowed (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->IsSleepingAllowed();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setAwake(boolean)
	 */
	@Override
	public void setAwake (boolean flag) {
		jniSetAwake(addr, flag);
	}

	private native void jniSetAwake (long addr, boolean flag); /*
		b2Body* body = (b2Body*)addr;
		body->SetAwake(flag);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#isAwake()
	 */
	@Override
	public boolean isAwake () {
		return jniIsAwake(addr);
	}

	private native boolean jniIsAwake (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->IsAwake();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setActive(boolean)
	 */
	@Override
	public void setActive (boolean flag) {
		jniSetActive(addr, flag);
	}

	private native void jniSetActive (long addr, boolean flag); /*
		b2Body* body = (b2Body*)addr;
		body->SetActive(flag);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#isActive()
	 */
	@Override
	public boolean isActive () {
		return jniIsActive(addr);
	}

	private native boolean jniIsActive (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->IsActive();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setFixedRotation(boolean)
	 */
	@Override
	public void setFixedRotation (boolean flag) {
		jniSetFixedRotation(addr, flag);
	}

	private native void jniSetFixedRotation (long addr, boolean flag); /*
		b2Body* body = (b2Body*)addr;
		body->SetFixedRotation(flag);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#isFixedRotation()
	 */
	@Override
	public boolean isFixedRotation () {
		return jniIsFixedRotation(addr);
	}

	private native boolean jniIsFixedRotation (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->IsFixedRotation();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getFixtureList()
	 */
	@Override
	public Array<Fixture> getFixtureList () {
		return fixtures;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getJointList()
	 */
	@Override
	public Array<JointEdge> getJointList () {
		return joints;
	}

	/** Get the list of all contacts attached to this body.
	 * @warning this list changes during the time step and you may miss some collisions if you don't use b2ContactListener. Do not
	 *          modify the returned list! */
// Array<ContactEdge> getContactList()
// {
// return contacts;
// }

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getGravityScale()
	 */
	@Override
	public float getGravityScale () {
		return jniGetGravityScale(addr);
	}

	private native float jniGetGravityScale (long addr); /*
		b2Body* body = (b2Body*)addr;
		return body->GetGravityScale();
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setGravityScale(float)
	 */
	@Override
	public void setGravityScale (float scale) {
		jniSetGravityScale(addr, scale);
	}

	private native void jniSetGravityScale (long addr, float scale); /*
		b2Body* body = (b2Body*)addr;
		body->SetGravityScale(scale);
	*/

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getWorld()
	 */
	@Override
	public World getWorld () {
		return world;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getUserData()
	 */
	@Override
	public Object getUserData () {
		return userData;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#setUserData(java.lang.Object)
	 */
	@Override
	public void setUserData (Object userData) {
		this.userData = userData;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinVelLoc()
	 */
	@Override
	public Vector2 getLinVelLoc() {
		return linVelLoc;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLinVelWorld()
	 */
	@Override
	public Vector2 getLinVelWorld() {
		return linVelWorld;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLocalPoint2()
	 */
	@Override
	public Vector2 getLocalPoint2() {
		return localPoint2;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.physics.box2d.IBody#getLocalVector()
	 */
	@Override
	public Vector2 getLocalVector() {
		return localVector;
	}
}
