package cn.javaplus.shhz.actor;

import cn.javaplus.game.shhz.R;
import cn.javaplus.shhz.components.itemlist.Item;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class CancelItem extends Item {
	public CancelItem() {
		super(R.PathbuttonPng);
		addCaptureListener(new ActorGestureListener() {

			@Override
			public void tap(InputEvent event, float x, float y, int count,
					int button) {
				getParent().hide();
			}
		});
	}

	@Override
	public ActorMenu getParent() {
		return (ActorMenu) super.getParent();
	}
}
