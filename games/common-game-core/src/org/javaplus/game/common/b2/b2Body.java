package org.javaplus.game.common.b2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class b2Body {

	private Body body;

	public b2Body(Body body) {
		this.body = body;
	}

	public void CreateShape(b2PolygonDef polygonDef) {
		polygonDef.fixCount();

		FixtureDef f = new FixtureDef();
		f.friction = (float) polygonDef.friction;
		f.restitution = (float) polygonDef.restitution;
		f.density = polygonDef.density;
		f.filter.groupIndex = (short) polygonDef.filter.groupIndex;
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.set(polygonDef.getVs());
		f.shape = polygonShape;

		body.createFixture(f);
	}

	public void SetMassFromShapes() {
	}

	public Body getBody() {
		return body;
	}

}
