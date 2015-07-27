package cn.javaplus.clickscreen.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.define.D;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Marsking extends Group {

	public static class Mask extends Actor {
		private static AtlasRegion region;

		public Mask() {
			if (region == null)
				region = Assets.getSd().getTextureAtlas("data/game.txt").findRegion(
						"mask");
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			 batch.draw(region, getX(), getY(), getWidth(),
			 getHeight());
		}
	}

	public Marsking(Actor actor) {
		setSize(D.STAGE_W, D.STAGE_H);
//		setPosition(-D.STAGE_W / 2, -D.STAGE_H / 2);
		addMask();
		addActor(actor);
	}

	private void addMask() {
		Actor lb = new Mask();
		lb.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Marsking.this.remove();
				return true;
			}
		});
		lb.setSize(getWidth(), getHeight());
		addActor(lb);
	}

}
