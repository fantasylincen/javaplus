package cn.javaplus.jigsaw.game;

import java.util.List;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Lists;

import cn.javaplus.jigsaw.define.D;
import cn.javaplus.jigsaw.gameover.MarskingListener;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Marsking extends Group {

	private final class RemoveSelfListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			Marsking.this.remove();
			dispatchCloseEvent();
			return true;
		}

		private void dispatchCloseEvent() {
			for (MarskingListener l : listeners) {
				l.onClose();
			}
		}
	}

	public static class Mask extends Actor {
		private static AtlasRegion texture;

		public Mask() {
			if (texture == null)
				texture = Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("mask");
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(texture, getX(), getY(), getWidth(), getHeight());
		}
	}

	private List<MarskingListener> listeners = Lists.newArrayList();
	private Actor actor;

	public Marsking(Actor actor) {
		this.actor = actor;
		setSize(D.STAGE_W, D.STAGE_H);
		addMask();
		addActor(actor);
	}

	private void addMask() {
		Actor lb = new Mask();
		lb.addListener(new RemoveSelfListener());
		actor.addListener(new RemoveSelfListener());
		lb.setSize(getWidth(), getHeight());
		addActor(lb);
	}

	public void addListener(MarskingListener listener) {
		listeners.add(listener);
	}

}
