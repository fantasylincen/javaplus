package cn.javaplus.game.power.stage;

import cn.javaplus.game.power.camera.GameCamera;

import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.utils.Array;


public interface GameStage extends com.badlogic.gdx.scenes.scene2d.IStage {

	Array<IActor> getActors();
	
	@Override
	public GameCamera getCamera();
}