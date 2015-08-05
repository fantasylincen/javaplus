package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.components.Button;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

public class Menu extends HorizontalGroup {

	public Menu() {
		setHeight(D.ITEM_HEIGHT);
		this.center();
	}

	protected void addItem(String png) {
		Button buton = new Button(png);
		buton.setSize(D.ITEM_WIDTH, D.ITEM_HEIGHT);
		this.addActor(buton);
	}
	
}
