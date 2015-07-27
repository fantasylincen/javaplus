package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;
import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;

public class GameStartLoadAssets implements Listener<GameStartEvent>{

	@Override
	public void onEvent(GameStartEvent e) {
		App.getAssets().loadFontInternal("data/simhei.ttf");
		App.getAssets().setSystemFont("data/simhei.ttf");
		Assets.getInternal().loadTextureAtlas("data/robot.txt");
		Assets.getInternal().loadBitmapFont("data/font.fnt");
		Assets.getInternal().loadTextureAtlas("data/ui-green.atlas");
	}

}
