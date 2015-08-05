package org.javaplus.clickscreen.special;

import java.util.List;

import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.animation.PlayOverCallBack;
import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 特效
 * @author 林岑
 *
 */
public class Effect extends Actor {

	public interface SpecialListener {

		void onPlayOver();

	}

	List<SpecialListener> listeners = Lists.newArrayList();

	public class RemoveOnPlayOver implements PlayOverCallBack {

		@Override
		public void onPlayOver() {
			Effect.this.remove();
			for (SpecialListener s : listeners) {
				try {
					s.onPlayOver();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void addListener(SpecialListener l) {
		listeners.add(l);
	}

	protected GameAnimation animation;

	public Effect(GameAnimation animation) {
		this.animation = animation;
		animation.playOnce(new RemoveOnPlayOver());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		animation.draw(batch, parentAlpha, this);
	}

}
