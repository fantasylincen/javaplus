package cn.javaplus.clickscreen.tank;

import org.javaplus.clickscreen.bullet.HitListener;
import org.javaplus.clickscreen.tank.Bullet;
import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.game.EnemyTank;
import cn.javaplus.clickscreen.game.GameStage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ShowDamage implements HitListener {

	public class JumpAction extends SequenceAction {

		private float x;
		private float y;

		public JumpAction(final Actor ac) {
			this.x = ac.getX();
			this.y = ac.getY();

			add(10.0f, 10.0f);
			add(20.0f, 17.0f);
			add(30.0f, 21.0f);
			add(40.0f, 22.0f);
			add(50.0f, 20.0f);
			add(60.0f, 15.0f);
			add(70.0f, 7.0f);
			add(80.0f, -4.0f);

			AlphaAction a = new AlphaAction() {
				@Override
				protected void end() {
					ac.remove();
				}
			};
			a.setAlpha(0);
			a.setDuration(0.3f);
			addAction(a);
		}

		private void add(float x, float y) {
			MoveToAction m;
			m = Actions.moveTo(x * 2 + this.x, y * 5 + this.y);
			m.setDuration(0.1f);
			addAction(m);
		}

	}

	@Override
	public void onHit(Bullet bullet, Target target) {
		EnemyTank tank = (EnemyTank) target;
		Label l = new Label(Util.Math.getFormatText(bullet.getDamage()), App.getAssets().getLabelStyle2());
		float x = tank.getX();
		float y = tank.getY();
		l.setPosition(x, y);

		GameStage stage = App.getApp().getStage();
		stage.getGameGroup().addActor(l);
		l.addAction(new JumpAction(l));
	}

	public static void main(String[] args) {
		float vx = 10;
		float vy = 10;
		float a = -3;
		float x = 0;
		float y = 0;
		for (int i = 0; i < 9; i++) {
			System.out.println("add(" + x + "f, " + y + "f);");
			x += vx;
			y += vy;
			vy += a;

		}
	}
}
