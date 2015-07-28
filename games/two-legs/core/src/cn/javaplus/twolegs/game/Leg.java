package cn.javaplus.twolegs.game;

import org.javaplus.game.common.util.Util;

import aurelienribon.bodyeditor.BodyEditorLoader;
import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.game.Categroys.Categroy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Leg {

	private RevoluteJoint joint;
	private RevoluteJoint xiGai;
	private Body up;
	private Body down;
	private double x;
	private double y;
	private BodyEditorLoader loader;
	private boolean isFrontMoving;

	public Leg(World world, BodyEditorLoader loader, double x, double y,
			Categroy upC, Categroy downC) {
		this.loader = loader;
		this.x = x;
		this.y = y;

		up = createLeg("legUp", world, upC);
		down = createLeg("legDown", world, downC);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.collideConnected = false;
		Vector2 a;

		jd.enableLimit = true;
		jd.enableMotor = true;

		a = new Vector2((float) x, (float) y);
		jd.initialize(up, down, a);

		xiGai = (RevoluteJoint) world.createJoint(jd);
	}

	public Body getUp() {
		return up;
	}

	public Body getDown() {
		return down;
	}

	public RevoluteJoint getJoint() {
		return joint;
	}

	public void setJoint(RevoluteJoint joint) {
		this.joint = joint;
	}

	public RevoluteJoint getXiGai() {
		return xiGai;
	}

	public void setXiGai(RevoluteJoint xiGai) {
		this.xiGai = xiGai;
	}

	private Body createLeg(String name, World world, Categroy c) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set((float) x, (float) y);
		bd.angularDamping = 0;

		FixtureDef fd = new FixtureDef();
		fd.density = D.LEG_DENSITY;
		fd.friction = D.LEG_FRICTION;
		fd.restitution = 0.0f;
		c.bind(fd);

		Body body = world.createBody(bd);

		loader.attachFixture(body, name, fd, 1);
		return body;
	}

	public float getX() {
		return (float) x;
	}

	public void step() {
		if (isFrontMoving) {
			if (getJointDegress() < D.MIN_ANGLE) {
				stop();
				// Log.d("stop frontMoving");
			}
		} else {
			if (getJointDegress() > D.MAX_ANGLE) {
				stop();
				// Log.d("stop behindMoving" + getJoint().getJointSpeed());
			}
		}

		if (isDaZhi()) {
			stopXiGai();
		}
	}

	private void stopXiGai() {
		if (!isXiGaiStop()) {
			xiGai.setMaxMotorTorque(0);
			xiGai.setMotorSpeed(0);
			// Log.d("停止膝盖转动");
		}
	}

	private boolean isXiGaiStop() {
		float abs = Math.abs(xiGai.getMaxMotorTorque());
		return abs < 0.01;
	}

	/**
	 * 腿是否打直了
	 * 
	 * @return
	 */
	private boolean isDaZhi() {
		double angle = xiGai.getJointAngle();
		angle = Math.toDegrees(angle);
		return angle > -1;
	}

	public double getJointDegress() {
		return Math.toDegrees(joint.getJointAngle());
	}

	public void fangSong() {
		joint.enableLimit(true);
		joint.setLimits((float) Math.toRadians(-90), (float) Math.toRadians(90));
		joint.enableMotor(true);
		joint.setMaxMotorTorque(0);
		joint.setMotorSpeed(0);

		xiGai.enableLimit(true);
		xiGai.setLimits((float) Math.toRadians(-35), 0);
		xiGai.enableMotor(true);
		stopXiGai();
	}

	public void moveBehind() {
		move(D.MOVE_BIHIND_F + Util.Random.get(-0.2f, 0.2f), D.MOVE_BIHIND_S
				+ Util.Random.get(-0.2f, 0.2f));
		daZhi();
		isFrontMoving = false;
	}

	/**
	 * 把脚打直
	 */
	private void daZhi() {
		xiGai.setMaxMotorTorque(D.XI_GAI_F);
		xiGai.setMotorSpeed(D.XI_GAI_S);
		// Log.d("打直腿");
	}

	private void move(float f, float s) {
		joint.setMaxMotorTorque(f);
		joint.setMotorSpeed(s);
	}

	public void moveFront() {
		move(D.MOVE_FRONT_F + Util.Random.get(-0.2f, 0.2f), -D.MOVE_FRONT_S
				+ Util.Random.get(-0.2f, 0.2f));
		isFrontMoving = true;
	}

	public void change() {
		if (isFrontMoving) {
			moveBehind();
		} else {
			moveFront();
		}
	}

	/**
	 * 腿腿是否在前方
	 */
	public boolean isInFront() {
		return getJointDegress() < 0;
	}

	public void stop() {
		move(D.STOP_FORCE, 0);
	}

	public void setReadyMoveFront(boolean isFrontMoving) {
		this.isFrontMoving = isFrontMoving;
	}

}
