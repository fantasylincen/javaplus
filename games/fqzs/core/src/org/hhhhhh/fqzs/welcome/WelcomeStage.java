package org.hhhhhh.fqzs.welcome;

import java.util.List;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.core.D;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;

public class WelcomeStage extends AbstractStage {

	public WelcomeStage() {
		super(D.STAGE_W, D.STAGE_H);
	}



	@Override
	public void loadAssets() {
		App.getAssets().loadFontInternal("simhei.ttf");
	}

	@Override
	public void unloadAssets() {
		App.getAssets().unloadFont("simhei.ttf");
	}

	private List<LoadOverListener> listeners = Lists.newArrayList();


	@Override
	public void onLoadingOver() {
		for (LoadOverListener d : listeners) {
			d.onLoadOver();
		}
	}

	@Override
	public boolean keyTyped(char c) {

		if ('d' == c) {
			IPreferences preferences = App.getPreferences();
			preferences.clear();
			Log.d("clear preferences");
		}

		return true;
	}

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}



	public void addLoadOverListener(LoadOverListener loadOverListener) {
		this.listeners.add(loadOverListener);
	}

}
