package cn.javaplus.game.power.screen;

import cn.javaplus.game.power.stage.GameStage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface GameScreen extends Screen {

	int getWidth();

	int getHeight();

	GameStage getStage();

	boolean addListener(EventListener listener);
}