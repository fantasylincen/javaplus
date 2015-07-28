package cn.javaplus.game.defender.listeners;

import cn.javaplus.game.defender.camera.GameCamera;
import cn.javaplus.game.defender.stage.GameStage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.IGroup;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class DragCameraListener extends InputListener {

	private GameCamera camera;
	private GameStage stage;
	private Vector2 pointerPositionStart;
	private Vector2 cameraPositionStart;

	public DragCameraListener(GameStage stage) {
		this.stage = stage;
		camera = stage.getCamera();
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		Vector2 draging = stage.stageToScreenCoordinates(new Vector2(x, y));
		int z = (int) camera.getPosition().z;
		Vector2 move = draging.sub(pointerPositionStart);
		move.set(move.getX(), -move.getY());
		Vector2 start = new Vector2(cameraPositionStart);
		Vector2 result = start.sub(move);
		camera.setPosition(result.getX(), result.getY(), z);
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		pointerPositionStart = stage
				.stageToScreenCoordinates(new Vector2(x, y));
		cameraPositionStart = new Vector2(camera.getX(), camera.getY());
		IGroup root = stage.getRoot();
		return event.getTarget() == root;
	}
}
