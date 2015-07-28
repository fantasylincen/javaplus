package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.camera.CameraController;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.events.CreateBackgroundEvent;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.input.GameInputProcessor;

import com.badlogic.gdx.input.GestureDetector;

/**
 * 初始化摄像机控制器
 */
public class CreateCameraController implements Listener<CreateBackgroundEvent> {

	@Override
	public void onEvent(CreateBackgroundEvent e) {
		GameInputProcessor processor = Game.getProcessor();
		Background background = e.getBackground();
		CameraController controller = new CameraController(e.getStage(),
				background);
		GestureDetector detector = new GestureDetector(controller);
		processor.add(detector);
		e.getScreen().addRenderListener(controller);
	}
}
