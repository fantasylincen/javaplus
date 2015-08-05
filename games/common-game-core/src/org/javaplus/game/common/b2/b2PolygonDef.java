package org.javaplus.game.common.b2;

import java.util.Arrays;

import com.badlogic.gdx.math.Vector2;

public class b2PolygonDef {

	public int vertexCount;
	public b2Vec2[] vertices;
	public double friction;
	public double restitution;
	public int density;
	public b2Filter filter = new b2Filter();

	public b2PolygonDef() {
		vertices = new b2Vec2[100];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new b2Vec2(0, 0);
		}
	}

	public void fixCount() {
		vertices = Arrays.copyOf(vertices, vertexCount);
	}

	public Vector2[] getVs() {
		Vector2[] v = new Vector2[vertices.length];
		for (int i = 0; i < v.length; i++) {
			v[i] = vertices[i].getValue();
		}
		return v;
	}

}
