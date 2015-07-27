package org.javaplus.game.common.b2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class b2RevoluteJointDef {

	public boolean enableLimit;
	public int lowerAngle;
	public int upperAngle;
	public Vector2 anchor;
	public Body bodyB;
	public Body bodyA;
	public void Initialize(b2Body bodyA, b2Body bodyB, b2Vec2 anchor) {
		this.anchor  = anchor.getValue();
		this.bodyA = bodyA.getBody();
		this.bodyB = bodyB.getBody();
	}

}
