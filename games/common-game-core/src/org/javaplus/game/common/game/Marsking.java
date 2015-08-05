package org.javaplus.game.common.game;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Marsking extends Group {
	public static class Mask extends Actor {
		private Loader loader = Assets.getInternal();
		
		public void setLoader(Loader loader) {
			this.loader = loader;
		}
		private static AtlasRegion region;

		public Mask() {
			if (region == null)
				region = loader.getTextureAtlas("data/robot.txt").findRegion(
						"mask");
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			 batch.draw(region, getX(), getY(), getWidth(),
			 getHeight());
		}
	}

	public Marsking(Actor actor) {
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
