package cn.javaplus.shhz.components.itemlist;

import cn.javaplus.shhz.assets.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Item extends Actor {
	private Texture texture;

	public Item(String png) {
		texture = Assets.getTexture(png);
		setSize(texture.getWidth(), texture.getHeight());
		float originX = getWidth() / 2;
		float originY = getHeight() / 2;
		setOrigin(originX, originY);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		float width = getWidth();
		float height = getHeight();
		batch.draw(texture, getX(), getY(), width, height);
		// Debug.debug("xxxxxxxxxxxx " + getWidth() + ", " + getHeight());
	}
}
