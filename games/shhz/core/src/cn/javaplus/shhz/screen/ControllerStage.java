package cn.javaplus.shhz.screen;

import cn.javaplus.shhz.actions.MoveUpAction;
import cn.javaplus.shhz.listeners.stage.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ControllerStage extends Stage {

	private Menu menu;

	public void changeMenu(Menu menu) {

		if (this.menu != null) {
			this.menu.clearActions();
			getRoot().removeActor(this.menu);
		}
		if (menu != null) {
			menu.clearActions();
			setPosition(menu);
			addActor(menu);
			menu.addAction(new MoveUpAction(menu));
		}
		this.menu = menu;
	}

	private void setPosition(Menu menu) {
		int w = Gdx.graphics.getWidth();
		float x = w / 2 - menu.getWidth() / 2;
		menu.setX(x);
	}

}
