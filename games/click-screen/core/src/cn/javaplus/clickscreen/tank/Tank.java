package cn.javaplus.clickscreen.tank;

import java.util.ArrayList;
import java.util.List;

import org.javaplus.clickscreen.bullet.HitListener;
import org.javaplus.clickscreen.bullet.HitListener.Target;
import org.javaplus.clickscreen.excel.Row;
import org.javaplus.clickscreen.special.Effect;
import org.javaplus.clickscreen.tank.Bullet;
import org.javaplus.clickscreen.tank.TargetFetcher;
import org.javaplus.game.common.actor.GroupMap;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Space;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.button.GameButton;
import cn.javaplus.clickscreen.game.Barrel;
import cn.javaplus.clickscreen.game.GameStage;
import cn.javaplus.clickscreen.game.TankBody;
import cn.javaplus.clickscreen.game.TankController;
import cn.javaplus.clickscreen.map.EnemyTankGround;
import cn.javaplus.clickscreen.script.Scripts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Tank extends GroupMap {

	private final class TargetFetcherImpl implements TargetFetcher {
		@Override
		public Target getTarget() {
			return map.getTank();
		}
	}

	public class ButtonManager {

		public List<GameButton> getButtons() {
			ArrayList<GameButton> ls = Lists.newArrayList();

			if (!isOpen()) {
				ls.add(new OpenTankButton(Tank.this));
			} else {
				ls.add(new LevelUpButton(Tank.this));
			}

			return ls;
		}

	}

	public class HitMonstorListenerImpl implements HitListener {

		@Override
		public void onHit(Bullet bullet, Target target) {
			showSpecial(bullet);
		}

		private void showSpecial(Bullet bullet) {
			GameAnimation a = AnimationCreator.create("data/TBottle-hd.txt", 0.1, "PBottle01", "PBottle02");
			Effect bombSpecial = new Effect(a);
			bombSpecial.setPosition(bullet.getX(), bullet.getY());
			getStage().addActor(bombSpecial);
		}

	}

	public class FireAction implements Runnable {

		@Override
		public void run() {

			if (!dto.isFireByTap())
				fire();
		}

	}

	Space fireSpace;
	EnemyTankGround map;
	private ButtonManager buttonManager;
	private TankDto dto;

	public Tank(EnemyTankGround map, TankDto dto) {
		this.map = map;
		this.dto = dto;
		float w = 60;
		float h = 60;
		setSize(w, h);

		TankBody body = new TankBody(dto.getTankId());
		Barrel barrel = new Barrel(dto.getTankId());
		Actor background = createBackground(dto);
		background.setVisible(false);

		putActor(body, "body");
		putActor(barrel, "barrel");
		putActor(background, "background");

		fireSpace = new Space(dto.getFireSpace());
		fireSpace.addAction(new FireAction());
		buttonManager = new ButtonManager();

		updateAttack();
		setOpen(dto.isOpen());
		setOrigin(getBody().getWidth() / 2, getBody().getHeight() / 2);

		addListener(new TankController(this));

		// addMoveListener();
	}

	private Row getTanksSheet() {
		return App.getStaticData().get("tanks").get(getId());
	}

	void addMoveListener() {
		addListener(new ActorGestureListener() {
			@Override
			public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
				Tank.this.moveBy(deltaX, deltaY);
				Log.d(getId());
			}
		});
	}

	public boolean isFireByTap() {
		return dto.isFireByTap();
	}

	private Image createBackground(TankDto t) {
		Loader ext = Assets.getSd();
		TextureRegion rg = ext.findRegion("data/game.txt", "oil");
		return new Image(rg);
	}

	private void updateAttack() {
		String script = Scripts.get("my-tank.js");
		App.getScript().call(script, "updateTankAttack", this);
	}

	public void setAttack(double attack) {
		dto.setAttack(attack);
	}

	public int getLevel() {
		return dto.getLevel();
	}

	private Vector2 calcPosition() {
		float x = getX() + getOriginX();
		float y = getY() + getOriginY();
		float halfH = 140;
		float dx = (float) (Math.cos(Math.toRadians(getRotation() + 90)) * halfH);
		float dy = (float) (Math.sin(Math.toRadians(getRotation() + 90)) * halfH);
		return new Vector2(x + dx, y + dy);
	}

	public void fire() {
		if (!dto.isOpen())
			return;

		String a = "bulletRed_outline";
		String d = "data/game.txt";
		Bullet bullet = new Bullet(d, 100, a);
		bullet.setTargetFetcher(new TargetFetcherImpl());

		Vector2 p = calcPosition();

		bullet.setRotation(getRotation());
		bullet.setPosition(p.x, p.y);
		double speed = dto.getSpeed();
		bullet.setSpeed(speed);
		bullet.setDamage(getAttack());

		GameStage stage = App.getApp().getStage();
		stage.getGameGroup().addActor(bullet);
		bullet.setBombSpecial(getBombSpecial());

		bullet.addHitListener(new ShowDamage());
		showFireSpecial();
	}

	public double getAttack() {
		return dto.getAttack();
	}

	private GameAnimation getBombSpecial() {
		GameAnimation f = AnimationCreator.create("data/game.txt", 0.15f, "smokeGrey0", "smokeGrey2", "smokeGrey4");
		return f;
	}

	private void showFireSpecial() {
		getBarrel().shake();
		showFireSmoke();
	}

	private void showFireSmoke() {
		GameAnimation f = AnimationCreator.create("data/game.txt", 0.1f, "smokeWhite0", "smokeWhite2", "smokeWhite4");
		Effect s = new Effect(f);
		double l = 50;
		s.setPosition(getBody().getWidth() / 2, (float) (getHeight() + l));
		addActor(s);
	}

	public int getId() {
		return dto.getTankId();
	}

	public int getBulletId() {
		return dto.getTankId();
	}

	public boolean isOpen() {
		return dto.isOpen();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		fireSpace.update(delta);
	}

	/**
	 * 瞄准b的中心
	 * 
	 * @param target
	 */
	public void aimAt(Actor target) {
		float x = target.getX() + target.getWidth() / 2;
		float y = target.getY() + target.getHeight() / 2;
		float x0 = getX() + getOriginX();
		float y0 = getY() + getOriginY();
		float dx = x - x0;
		float dy = y - y0;
		double r = Math.atan(dy / dx);
		float degrees = (float) Math.toDegrees(r) - 90;
		setRotation(degrees);
	}

	/**
	 * 旋转炮杆
	 * 
	 * @param degress
	 */
	public void rotationBarrel(int degress) {
		Barrel actor = getBarrel();
		actor.setRotation(actor.getRotation() + degress);
	}

	public Barrel getBarrel() {
		Actor actor = getActor("barrel");
		return (Barrel) actor;
	}

	public ButtonManager getButtonManager() {
		return buttonManager;
	}

	public double getOpenNeed() {
		Row row = getTanksSheet();
		return row.getDouble("openNeed");
	}

	public void open() {
		setOpen(true);
	}

	private void updateBodyStatus() {
		getBody().setVisible(isOpen());
		getBarrel().setVisible(isOpen());
		getActor("background").setVisible(!isOpen());
	}

	private TankBody getBody() {
		return (TankBody) getActor("body");
	}

	public void setOpen(boolean open) {
		dto.setOpen(open);
		updateBodyStatus();
	}

	public void showLevelUpEffect() {

		GameAnimation f = AnimationCreator.create("data/game.txt", 0.3f, "sj01", "sj02", "sj03", "sj04", "sj05",
				"sj06", "sj07", "sj08", "sj09", "sj10", "sj11", "sj12");
		Effect s = new Effect(f);
		s.setPosition(getX() + 23, getY() + getHeight() / 2 + 30);

		GameStage stage = App.getApp().getStage();
		stage.getGameGroup().addActor(s);
	}

	public void levelUp() {
		dto.setLevel(dto.getLevel() + 1);
		updateAttack();
	}
}
