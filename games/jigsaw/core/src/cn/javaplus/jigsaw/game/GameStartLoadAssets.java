package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;
import org.mxz.events.Listener;

import cn.javaplus.jigsaw.App;

public class GameStartLoadAssets implements Listener<GameStartEvent> {

	@Override
	public void onEvent(GameStartEvent e) {
		App.getAssets().loadFontInternal("data/Wild honey.ttf");
		App.getAssets().setSystemFont("data/Wild honey.ttf");
		Assets.getInternal().loadTextureAtlas("data/game.txt");
	}
}
