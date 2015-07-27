package cn.javaplus.clickscreen.game;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.tank.Tank;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class TankController extends ActorGestureListener implements
		EventListener {

	private Tank tank;

	public TankController(Tank tank) {
		this.tank = tank;
	}

	@Override
	public void tap(InputEvent event, float x, float y, int count, int button) {
		TankMenu menu = new TankMenu(tank);
		GameStage stage = App.getApp().getStage();
		stage.getControllerGroup().addActor(new Marsking(menu));
	}
}
