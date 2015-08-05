package cn.javaplus.twolegs.game;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraController {

	private Robot robot;
	private Camera camera;
	private float dx;

	public CameraController(Robot robot, Camera camera) {
		this.robot = robot;
		this.camera = camera;
		dx = 100;
		camera.translate(0, -76, 0);
	}

	public void step() {
//		camera.translate(30, 0, 0);
		Vector2 p = robot.getPosition();
		float cx = dx + p.x * D.BOX_2D_STAGE_SCALE;
		Vector3 pp = camera.position;
		pp.set(cx, pp.y, pp.z);
	}


}
