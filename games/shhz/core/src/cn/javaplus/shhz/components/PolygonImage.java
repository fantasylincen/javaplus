package cn.javaplus.shhz.components;

import java.util.List;

import cn.javaplus.game.shhz.R;
import cn.javaplus.game.shhz.R.TextureShape;
import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.shape.PolygonImpl;
import cn.javaplus.shhz.util.Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PolygonImage extends Image {

	private PolygonImpl polygon;
	private boolean isRectBound;
	private Texture boundPoint;
	protected TextureShape shape;

	public PolygonImage(TextureShape shape) {
		super(shape.getTexture());
		this.shape = shape;

		this.polygon = new PolygonImpl();
		for (Vector2 p : shape.getPolygon().getPoints()) {
			float h = getHeight();
			float w = getWidth();
			this.polygon.addPoint(p.x * w, p.y * h);
		}

		if (D.IS_SHOW_ACTOR_BOUNDS) {
			boundPoint = Assets.getTexture(R.PointPng);
		}
	}

	public void toRectBound() {
		isRectBound = true;
	}

	public void toPolygonBound() {
		isRectBound = false;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (D.IS_SHOW_ACTOR_BOUNDS) {
			drawBound(batch, parentAlpha);
		}
	}

	private void drawBound(Batch batch, float parentAlpha) {
		List<Vector2> points = polygon.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Vector2 p1 = points.get(i);
			Vector2 p2 = points.get(i + 1);
			drawLine(batch, p1, p2, parentAlpha);
		}
	}

	private void drawLine(Batch batch, Vector2 p1, Vector2 p2, float parentAlpha) {
		float x2 = p2.x;
		float y2 = p2.y;
		float x1 = p1.x;
		float y1 = p1.y;
		int ss = 40;
		float f = (x2 - x1) / ss;
		float g = (y2 - y1) / ss;
		int hh = boundPoint.getHeight() / 2;
		int hw = boundPoint.getWidth() / 2;

		for (int i = 0; i < ss; i++) {
			float x = f * i + x1;
			float y = g * i + y1;
			batch.draw(boundPoint, getX() + x - hw, getY() - hh + y);
		}
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (isRectBound) {
			return super.hit(x, y, touchable);
		}
		if (touchable && getTouchable() != Touchable.enabled)
			return null;
		if (Util.Shape.Polygon.isPointInPolygon(x, y, polygon)) {
			// Log.d("PolygonImage.hit ", x, y);
			return this;
		} else {
			return null;
		}
	}
}
