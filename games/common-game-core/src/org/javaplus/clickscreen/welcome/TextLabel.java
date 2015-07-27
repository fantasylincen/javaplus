package org.javaplus.clickscreen.welcome;

import org.javaplus.game.common.util.Space;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextLabel extends Actor {
	public class ChangeText implements Runnable {

		@Override
		public void run() {
			textIndex++;
			if (textIndex >= texts.length) {
				textIndex = 0;
			}
		}

	}

	private BitmapFont font;
	private Space space = new Space(0.3f);
	private int textIndex;

	private String[] texts;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (font == null)
			return;
		batch.setColor(Color.WHITE);
		float y = getY();
		float x = getX();
		try {
			font.draw(batch, getText(), x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CharSequence getText() {
		if(textIndex > texts.length - 1) 
			textIndex = texts.length - 1;
		return texts[textIndex];
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (space != null)
			space.update(delta);
	}

	public void setSpace(double space) {
		this.space = new Space(space);
		this.space.addAction(new ChangeText());
	}

	public void setText(String... s) {
		texts = s;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
}
