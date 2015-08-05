package cn.javaplus.game.defender.listeners;

import android.view.KeyEvent;
import cn.javaplus.game.defender.App;

import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IStage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MoveCameraListener extends InputListener implements EventListener {
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		IActor target = event.getTarget();
		IStage stage = target.getStage();
		ICamera camera = stage.getCamera();

		float h = App.getStageHeight();
		float w = App.getStageWidth();
		
//		System.out.println("keycode:" + keycode);
		
		int v;
		if(keycode == KeyEvent.KEYCODE_W) {
			v = +205;
		} else {
			v = -205;
		}
		
		camera.setViewportHeight(camera.getViewPortHeight() + v);
		float vh = camera.getViewPortHeight();
		
		camera.setViewportWidth(vh / h * w);
		
		return true;
	}
}
