package org.javaplus.game.common.stage;

import org.javaplus.game.common.messagebox.MessageBox;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface IStage extends InputProcessor {

	void draw();

	void act(float delta);

	void show();

	void resize(int width, int height);

	void loadAssets();

	void hide();

	void unloadAssets();

	void dispose();

	void onLoading(float progress);

	void onLoadingOver();

	void pause();

	void resume();
	
	MessageBox getMessageBox();

	GameUI getGameUI();

	boolean contains(Actor actor);

}
