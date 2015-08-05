package lincen.javame.game.numrubikcube.canvas;


import org.javaplus.game.common.util.CollisionDetector;
import org.javaplus.game.common.util.RectFloat;

import lincen.javame.util.Pen;

public class ButtonImpl extends com.badlogic.gdx.scenes.scene2d.ui.Button implements Button, RectFloat {

	@Override
	public void paint(Pen g) {
	}

	@Override
	public void setText(String string) {
		super.setName(string);
	}

	@Override
	public void setSize(float w, float h) {
		super.setSize(w, h);
	}

	@Override
	public void setLocation(float x, float y) {
		setPosition(x, y);
	}

	@Override
	public boolean contains(float x, float y) {
		return CollisionDetector.contains(this, x, y);
	}

	@Override
	public float getH() {
		return getHeight();
	}

	@Override
	public float getW() {
		return getWidth();
	}

}
