package cn.javaplus.twolegs.game;

import org.javaplus.game.common.log.Log;

import aurelienribon.bodyeditor.BodyEditorLoader;
import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.define.Events;
import cn.javaplus.twolegs.game.Categroys.Categroy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Robot {

	private World world;
	private BodyEditorLoader loader;
	private Body head;
	private Leg left;
	private Leg right;
	private boolean fangSong;
	private boolean isDeath;
	private GameStage stage;
	private int touchTimes;

	private void fangSongJoints() {
		left.fangSong();
		right.fangSong();
	}

	public Body getHead() {
		return head;
	}

	public Leg getLeft() {
		return left;
	}

	public Leg getRight() {
		return right;
	}

	public Robot(GameStage stage, World world, BodyEditorLoader loader) {
		this.stage = stage;
		this.world = world;
		this.loader = loader;

		head = createHead(1.8255355f, 2.4501176f, Categroys.HEAD);

		double y = 1.2962812;

		double x = 1.8649046;

		left = new Leg(world, loader, x, y, Categroys.LEFT_LEG_UP,
				Categroys.LEFT_LEG_DOWN);
		right = new Leg(world, loader, 2.122907f, y + 0.00f,
				Categroys.RIGHT_LEG_UP, Categroys.RIGHT_LEG_DOWN);

		left.setReadyMoveFront(false);
		right.setReadyMoveFront(true);

		createJoint(left);
		createJoint(right);
	}

	private void createJoint(Leg leg) {
		RevoluteJointDef jd = new RevoluteJointDef();
		jd.enableLimit = true;
		Vector2 a = new Vector2(leg.getX(), 2.4501176f);
		jd.initialize(leg.getUp(), head, a);
		leg.setJoint((RevoluteJoint) world.createJoint(jd));
	}

	private Body createHead(double x, double y, Categroy c) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set((float) x, (float) y);
		FixtureDef fd = new FixtureDef();
		fd.density = D.HEAD_DENSITY;
		fd.friction = 0.7f;
		fd.restitution = 0f;
		c.bind(fd);

		Body head = world.createBody(bd);

		loader.attachFixture(head, "head", fd, 1);
		return head;
	}

	public void legOut() {
		if (!fangSong) {
			fangSongJoints();
			fangSong = true;
		}
	}

	public void step() {
		right.step();
		left.step();

		float angle = head.getAngle();
//		head.applyAngularImpulse(-angle * 20, false);
		head.setAngularVelocity(-angle * 10);

		if (!isDeath && getPosition().y < 0.35f) {
			isDeath = true;
			Log.d("game over");
			Events.dispatch(new GameOverEvent(stage, getScore(),
					getScoreText(), touchTimes));
		}
	}

	public Vector2 getPosition() {
		return head.getPosition();
	}

	public float getScore() {
		float f = head.getPosition().x - 1.83f;
		if (f < 0)
			return 0;
		return f;
	}

	/**
	 * 截取2位小数的成绩
	 * 
	 * @return
	 */
	public String getScoreText() {
		float score = getScore();
		if (score <= 0) {
			return "0.00";
		}
		return String.format("%.2f", score);
	}

	public boolean isDeath() {
		return isDeath;
	}

	public void addTouchTimes() {
		touchTimes++;
	}

}
