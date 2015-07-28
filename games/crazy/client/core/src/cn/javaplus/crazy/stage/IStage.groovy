package cn.javaplus.crazy.stage;

import cn.javaplus.crazy.R.CocosUI;
import cn.javaplus.crazy.login.MessageBox;

import com.badlogic.gdx.InputProcessor;

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

	CocosUI getCocosUI();

}
