package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Label2 extends Actor {

	private BitmapFont font;
	private String str = "sssss";
	private String text;
	private float time;
	private boolean isOver;
	private float every;
	private float endTime = 0.5f;
	private int length;

	public Label2() {
		font = Assets.getInternal().getBitmapFont("data/font.fnt");
	}

	public void showOneByOne(String text) {
		this.text = text;
		time = 0;
		every = endTime / text.length();
		length = text.length();
	}

	private void update(float dt) {
		time += dt;
		int len = getLen();
		str = text.substring(0, len);
		if (len >= length) {
			isOver = true;
		}
	}

	private int getLen() {
		float t = endTime - time;
		float remain = t / every;
		int i = (int) (length - remain);
		if (i < 0)
			i = 0;
		if(i > length)
			i = length;
		return i;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (!isVisible()) {
			return;
		}
		float dt = Gdx.graphics.getDeltaTime();
		draw(batch, parentAlpha, dt);
	}

	private void draw(Batch batch, float parentAlpha, float dt) {
		if (isOver) {
			font.draw(batch, text, getX(), getY());
		} else {
			update(dt);
			font.draw(batch, str, getX(), getY());
		}
	}
}
