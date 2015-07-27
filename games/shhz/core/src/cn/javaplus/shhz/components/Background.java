package cn.javaplus.shhz.components;

import cn.javaplus.game.shhz.R;
//code.csdn.net/lc574907580/games.git
import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.stage.GameStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Background extends Image {

	public class TouchListener extends ActorGestureListener {

		@Override
		public void touchDown(InputEvent e, float x, float y, int p, int b) {
			isTouched = true;
			getStage().cancelSelected();
		}

		@Override
		public void touchUp(InputEvent e, float x, float y, int p, int b) {
			isTouched = false;
		}
	}

	static Texture tieldGrid = Assets
			.getTexture(R.TiledAreaTileGridPng);

	private boolean isTouched;

	private GameStage stage2;
	private Image field;

	private GridManager gridManager;

	public Background(GameStage stage) {
		super(Assets.getTexture("map001.png"));
		stage2 = stage;
		final int scale = 2;
		setSize(getWidth() * scale, getHeight() * scale);
		field = Assets.createImage("ground001.png");
		field.setSize(field.getWidth() * scale, field.getHeight() * scale);
		addListener(new TouchListener());
		gridManager = new GridManager(this);
	}

	public GridManager getGridManager() {
		return gridManager;
	}

//	String key(int x, int y) {
//		return x + ":" + y;
//	}
//
//	String key(MapGrid m) {
//		int x = (int) m.getX();
//		int y = (int) m.getY();
//		return key(x, y);
//	}

	public GameStage getStage() {
		return stage2;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		drawFields(batch);
		super.draw(batch, parentAlpha);
		drawGrid(batch, parentAlpha);
	}

	private void drawGrid(Batch batch, float parentAlpha) {
		if (D.IS_SHOW_GRID) {
			for (MapGrid g : gridManager.getGrids()) {
				g.draw(batch, parentAlpha);
			}
		}
	}

	private void drawFields(Batch batch) {
		int fh = (int) field.getHeight();
		int y = 0;
		while (y < getHeight()) {
			drawRow(batch, y);
			y += fh;
		}
	}

	private void drawRow(Batch batch, int y) {
		int x = 0;
		while (x < getWidth()) {
			field.setPosition(x, y);
			field.draw(batch, 1);
			x += field.getWidth();
		}
	}

	public boolean isTouched() {
		return isTouched;
	}
}
