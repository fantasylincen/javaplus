package cn.javaplus.shhz.components;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.events.components.ButtonTapEvent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Button extends Image {

	public class TapListener extends ActorGestureListener {

		@Override
		public void tap(InputEvent event, float x, float y, int count,
				int button) {
			Events.dispatch(new ButtonTapEvent(id, Button.this));
		}

		@Override
		public void touchDown(InputEvent event, float x, float y, int pointer,
				int button) {
			setColor(Color.GRAY);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			setColor(Color.WHITE);
		}
	}

	private String id;

	public Button(String png) {
		super(Assets.getTexture(png));
		this.id = png;
		addListener(new TapListener());
	}

	public Button(String name, String png) {
		super(Assets.getTexture(png));
		this.id = name;
		addListener(new TapListener());
	}

	public String getId() {
		return id;
	}

}
