package org.hhhhhh.fqzs.core;

import org.hhhhhh.fqzs.event.Events;
import org.hhhhhh.fqzs.event.ExitEvent;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameStage extends AbstractStage {

	Group gameGroup;
	Group controllerGroup;
	private Background background;

	public GameStage() {
		super(D.STAGE_W, D.STAGE_H);
		gameGroup = new Group();
		controllerGroup = new Group();
		addActor(gameGroup);
		addActor(controllerGroup);
	}

	@Override
	public void loadAssets() {

		App.getAssets().loadFontInternal("data/Entsani.ttf");
		App.getAssets().loadFontInternal("data/WildHoney.ttf");
		App.getAssets().setSystemFont("data/WildHoney.ttf");

		Loader loader = Assets.getDefaultLoader();
		
		AnimationCreator.setLoader(loader);

		loader.loadTextureAtlas("data/game.txt");

		loader.loadBitmapFont("data/font.fnt");
		loader.loadTextureAtlas("data/ui-green.atlas");
		loader.loadTexture("data/PetNest03.png");
		loader.loadTexture("data/fat_oldboss02.png");
		loader.loadTextureAtlas("data/TBottle-hd.txt");
		loader.loadTexture("data/coin-sprite-sheet.png");
	}

	@Override
	public void onLoadingOver() {
		addBackground();
		Actor panel = new Actor();
		gameGroup.addActor(panel);
	}

	private void addBackground() {
		background = new Background();
		gameGroup.addActor(background);
	}

	@Override
	public boolean keyTyped(char c) {

		if (c == 27 || c == 0) {
			Events.dispatch(new ExitEvent());
			return true;
		}
		if ('d' == c) {
			App.getPreferences().clear();
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 v = screenToStageCoordinates(new Vector2(screenX, screenY));
		System.out.println(v.x + " " + v.y);
		return super.touchDown(screenX, screenY, pointer, button);
	}

	public Group getGameGroup() {
		return gameGroup;
	}

	public Group getControllerGroup() {
		return controllerGroup;
	}
}
