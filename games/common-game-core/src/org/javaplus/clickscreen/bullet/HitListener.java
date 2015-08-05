package org.javaplus.clickscreen.bullet;

import org.javaplus.clickscreen.tank.Bullet;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface HitListener {

	public interface Target {

		void reduceHp(double damage);

		void shake();

		Actor toActor();

		double getHpMax();

		boolean isDeath();

	}

	void onHit(Bullet bullet, Target target);

}
