package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.bullet.HitListener.Target;
import org.javaplus.clickscreen.special.Effect;
import org.javaplus.clickscreen.tank.Bullet;
import org.javaplus.clickscreen.tank.TargetFetcher;
import org.javaplus.game.common.actions.ShakeAction;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.game.HpLabel.Hpable;
import cn.javaplus.clickscreen.hp.Hp;
import cn.javaplus.clickscreen.hp.Hp.HpListener;
import cn.javaplus.clickscreen.tank.TankConfig;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class EnemyTank extends Group implements Target, Hpable {

	private static final int HP_MAX = 30;

	public class EnemyBullet extends Bullet {

		public EnemyBullet(String assetsPath, double dt, String... animations) {
			super(assetsPath, dt, animations);
		}
	}

	public class StoneC implements TargetFetcher {

		@Override
		public Target getTarget() {
			if (stone.isDeath()) {
				return home;
			}
			return stone;
		}

	}

	public class FireAction implements Runnable {

		@Override
		public void run() {
			fire();
		}

	}

	private TankBody body;
	private HpLabel hpLabel;
	private Barrel barrel;
//	private Space fireSpace;
	private Stone stone;
	private Home home;
	private float xStart;
	private float yStart;
	private Hp hp;
	private double attack;
	private double coin;
	private double bulletSpeed;

	public EnemyTank(Stone stone, Home home, float x, float y, TankConfig config) {
		hp = new Hp(config.getHp());
		this.home = home;
		this.stone = stone;
		xStart = x;
		yStart = y;
		int tankId = config.getTankId();
		attack = config.getAttack();
		coin = config.getCoin();
		body = new TankBody(tankId);
		barrel = new Barrel(tankId);
		hpLabel = new HpLabel(this);
		addActor(body);
		addActor(barrel);
		addActor(hpLabel);
		setSize(body.getWidth(), body.getHeight());
//		fireSpace = new Space(config.getFireSpace());
//		fireSpace.addAction(new FireAction());
		setPosition(x, y);
		bulletSpeed = config.getBulletSpeed();
	}

	/**
	 * 旋转炮杆
	 * 
	 * @param degress
	 */
	public void rotationBarrel(int degress) {
		barrel.setRotation(barrel.getRotation() + degress);
	}

	public void fire() {
		showFireSpecial();
		String a = "bulletRed_outline";
		String d = "data/game.txt";
		Bullet bullet = new EnemyBullet(d, 0.3, a);
		bullet.setTargetFetcher(new StoneC());
		bullet.setRotation(90);
		bullet.setPosition(getX() + 5, getY() + 32);
		bullet.setSpeed(bulletSpeed);
		bullet.setDamage(attack);
		GameStage stage = App.getApp().getStage();
		stage.getGameGroup().addActor(bullet);
		bullet.setBombSpecial(getBombSpecial());
	}

	private GameAnimation getBombSpecial() {
		GameAnimation f = AnimationCreator.create("data/game.txt", 0.15f,
				"smokeGrey0", "smokeGrey2", "smokeGrey4");
		return f;
	}

	private void showFireSpecial() {
		barrel.shake();
		showFireSmoke();
	}

	private void showFireSmoke() {
		GameAnimation f = AnimationCreator.create("data/game.txt", 0.1f,
				"smokeWhite0", "smokeWhite2", "smokeWhite4");
		Effect s = new Effect(f);
		s.setPosition(getX() - 10, getY() + getHeight() / 2);
		

		GameStage stage = App.getApp().getStage();
		stage.getGameGroup().addActor(s);
	}

	public double getHp() {
		return hp.getValue();
	}

	public void reduceHp(double damage) {
		hp.reduce(damage);
	}

	public void addListener(HpListener l) {
		hp.addListener(l);
	}

	public boolean isDeath() {
		return hp.isDeath();
	}

	public String getColorText() {
		return "Orange";
	}

//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		fireSpace.update(delta);
//	}

	@Override
	public void shake() {

		addAction(new ShakeAction(this, xStart, yStart));
	}

	@Override
	public Actor toActor() {
		return this;
	}

	@Override
	public double getHpMax() {
		return HP_MAX;
	}

	public double getCoin() {
		return coin;
	}
}
