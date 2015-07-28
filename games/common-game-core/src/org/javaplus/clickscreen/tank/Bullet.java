package org.javaplus.clickscreen.tank;

import java.util.List;

import org.javaplus.clickscreen.bullet.HitListener;
import org.javaplus.clickscreen.bullet.HitListener.Target;
import org.javaplus.clickscreen.special.Effect;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.util.CollisionDetector;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.RectFloat;
import org.javaplus.game.common.util.RectImpl;
import org.javaplus.game.common.util.Space;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
	public class MoveAction implements Runnable {

		@Override
		public void run() {
			move();
		}

	}

	private GameAnimation animation;
	Space space = new Space(0.05);
	private double speed = 10;
	RectFloat liveArea = new RectImpl(-50, -50, 5000, 5000);
	protected CollisionDetector detector = new CollisionDetector();
	protected List<HitListener> listeners = Lists.newArrayList();
	protected TargetFetcher targetFetcher;
	private GameAnimation bombSpecial;
	private double damage;

	public Bullet(String assetsPath, double dt, String... animations) {
		animation = AnimationCreator.create(assetsPath, dt, animations);
		animation.loop();
		space.addAction(new MoveAction());
		setSize(10, 10);
		Util.Actor.setOriginToCenter(this);
	}

	public Bullet(final Target target, String assetsPath, double dt,
			String... animations) {
		this(assetsPath, dt, animations);
		setTargetFetcher(new TargetFetcher() {

			@Override
			public Target getTarget() {
				return target;
			}
		});
	}

	public void setTargetFetcher(TargetFetcher targetFetcher) {
		this.targetFetcher = targetFetcher;
	}

	public TargetFetcher getTargetFetcher() {
		return targetFetcher;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move() {
		float dx = (float) (Math.cos(Math.toRadians(getRotation() + 90)) * speed);
		float dy = (float) (Math.sin(Math.toRadians(getRotation() + 90)) * speed);
		float x = getX();
		float y = getY();
		x += dx;
		y += dy;
		setPosition(x, y);
		if (!detector.contains(liveArea, x, y)) {
			this.remove();
		}

		checkCollision();
	}

	protected void checkCollision() {
		Target target = targetFetcher.getTarget();

		if (detector.isCollideWith(target.toActor(), this)) {
			showBombSpecial();
			remove();
			target.reduceHp(getDamage());
			target.shake();
			for (HitListener h : listeners) {
				h.onHit(this, target);
			}
		}
	}

	protected void showBombSpecial() {
		if (bombSpecial == null)
			return;
		Effect s = new Effect(bombSpecial);
		s.setPosition(getX() + 50, getY() + 0);

		getParent().addActor(s);
	}

	public void setBombSpecial(GameAnimation bombSpecial) {
		this.bombSpecial = bombSpecial;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		animation.draw(batch, parentAlpha, this);
	}

	@Override
	public void act(float delta) {
		space.update(delta);
	}

	public void addHitListener(HitListener listener) {
		if (listener != null)
			listeners.add(listener);
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
}
