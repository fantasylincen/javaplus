package org.javaplus.game.common.b2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class b2World {
	private World world;

	public b2World(World world) {
		this.world = world;
	}

	public b2Body CreateBody(b2BodyDef bodyDef) {
		BodyDef def = new BodyDef();
		def.position
				.set((float) bodyDef.position.x, (float) bodyDef.position.y);
		def.angle = (float) bodyDef.angle;
		Body body = world.createBody(def);
		return new b2Body(body);
	}

	public void CreateJoint(b2RevoluteJointDef r) {
		RevoluteJointDef def = new RevoluteJointDef();
		def.initialize(r.bodyA, r.bodyB, r.anchor);
		def.enableLimit = r.enableLimit;
		def.lowerAngle = r.lowerAngle;
		def.upperAngle = r.upperAngle;
		world.createJoint(def);
	}

}
