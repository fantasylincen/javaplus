package cn.javaplus.game.defender.util;

import com.badlogic.gdx.math.IRectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IGroup;
import com.badlogic.gdx.scenes.scene2d.IStage;

public class RectangleOnStage implements IRectangle {

	private float width;
	private float y;
	private float x;
	private float height;

	public RectangleOnStage(IActor actor) {
		IStage stage = actor.getStage();
		IGroup root = stage.getRoot();
		x = actor.getX();
		y = actor.getY();
		width = actor.getWidth();
		height = actor.getHeight();
		Group parent = actor.getParent();
		while (true) {
			if(parent == root) {
				break;
			}
			x += parent.getX();
			y += parent.getY();
			parent = parent.getParent();
		}
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

}
