package cn.javaplus.shhz.shape;

import java.util.List;

import cn.javaplus.shhz.collections.Lists;

import com.badlogic.gdx.math.Vector2;

public class PolygonImpl implements Polygon {

	private List<Vector2> points = Lists.newArrayList();

	public void addPoint(double x, double y) {
		points.add(new Vector2((float) x, (float) y));
	}

	@Override
	public List<Vector2> getPoints() {
		return points;
	}

}
