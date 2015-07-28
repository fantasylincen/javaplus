package cn.javaplus.game.defender.stage;

import cn.javaplus.game.defender.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Sky extends Image {

	public Sky() {
		super(App.getAssetsManager().getPngTexture("image"));
		setColor(new Color(0xffffff));
		setSize(App.getStageWidth(), App.getStageHeight());
	}
}
