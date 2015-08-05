package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlackGround extends Image {
//
//	private static final float brightness = 0.05f;
//	private static final Color COLOR0 = gray(0.08f + brightness);
//	private static final Color COLOR1 = gray(0.09f + brightness);
//	private static final Color COLOR2 = gray(0.1f + brightness);
//	private static final Color COLOR3 = gray(0.11f + brightness);
//	private static final Color COLOR4 = gray(0.12f + brightness);

//	private ShapeRenderer rend;
//	private static final int D = 10;

//	private Point[] p = new Point[8];

	public BlackGround() {
//		rend = new ShapeRenderer();
//		rend.setProjectionMatrix(combined);
		super(Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("rect"));
	}

//	private static Color gray(float f) {
//		return new Color(f, f, f, 1);
//	}

//	private Point create(float x, float y) {
//		Point point = new Point();
//		point.setLocation(getX() + x, getY() + y);
//		return point;
//	}

//	@Override
//	public void draw(Batch batch, float parentAlpha) {
//		drawBackGround();
//		drawBorder();
		
//	}

//	private void drawBackGround() {
//		rend.setColor(COLOR4);
//		rend.begin(ShapeType.Filled);
//		rend.rect(getX(), getY(), getWidth(), getHeight());
//		rend.end();
//	}
//
//	private void drawBorder() {
//
//		ensureInit();
//
//		draw(0, 1, 2, COLOR0);
//		draw(1, 2, 3, COLOR0);
//
//		draw(2, 3, 4, COLOR1);
//		draw(3, 4, 5, COLOR1);
//
//		draw(4, 5, 6, COLOR3);
//		draw(5, 6, 7, COLOR3);
//
//		draw(6, 7, 0, COLOR2);
//		draw(7, 0, 1, COLOR2);
//	}
//
//	private void ensureInit() {
//		if (p[0] == null) {
//			float W = getWidth();
//			float h = getHeight();
//
//			p[0] = create(+0, +0);
//			p[1] = create(+D, +D);
//			p[2] = create(+W, +0);
//			p[3] = create(+W - D, +D);
//			p[4] = create(+W, +h);
//			p[5] = create(+W - D, +h - D);
//			p[6] = create(+0, +h);
//			p[7] = create(+D, +h - D);
//		}
//	}
//
//	private void draw(int i, int j, int k, Color c) {
//		rend.setColor(c);
//		rend.begin(ShapeType.Filled);
//		Point p1 = p[i];
//		Point p2 = p[j];
//		Point p3 = p[k];
//		rend.triangle((float) p1.getX(), (float) p1.getY(), (float) p2.getX(),
//				(float) p2.getY(), (float) p3.getX(), (float) p3.getY());
//		rend.end();
//	}
}
