package cn.javaplus.clickscreen.map;

import org.javaplus.clickscreen.special.Effect;
import org.javaplus.clickscreen.special.Effect.SpecialListener;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.animation.GameAnimation;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.game.EnemyTank;
import cn.javaplus.clickscreen.game.GameStage;
import cn.javaplus.clickscreen.game.Home;
import cn.javaplus.clickscreen.game.Stone;
import cn.javaplus.clickscreen.hp.Hp;
import cn.javaplus.clickscreen.hp.Hp.HpListener;
import cn.javaplus.clickscreen.script.Scripts;
import cn.javaplus.clickscreen.tank.CoinSpecial;
import cn.javaplus.clickscreen.tank.TankConfig;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class EnemyTankGround extends Group {

	public EnemyTankGround() {
		setTouchable(Touchable.disabled);
	}
	
	static String getT() {
		return App.getDb().getSelectedMission() + "";
	}

	class MissionLabel extends Label {

		private int mission;

		public MissionLabel() {
			super(getT(), App.getAssets().getLabelStyle1());
		}

		@Override
		public void act(float delta) {
			int sm = App.getDb().getSecondaryMission();
			if (mission != sm) {
				mission = sm;
				setText(getT());
			}
		}

	}

	static String getProgress() {
		int n = App.getDb().getSecondaryMission();
		int d = App.getDb().getBossMission();
		return n + "/" + d;
	}

	class SecondaryMissionLabel extends Label {

		private int mission;

		public SecondaryMissionLabel() {
			super(getProgress(), App.getAssets().getLabelStyle1());
		}

		@Override
		public void act(float delta) {
			int sm = App.getDb().getSecondaryMission();
			if (mission != sm) {
				mission = sm;
				setText(getProgress());
			}
		}
	}

	public class EnemyTankSelector extends Group {

		public EnemyTankSelector() {
			addMissionLabel();
			addSecondrayMissionLabel();
		}

		private void addSecondrayMissionLabel() {
			SecondaryMissionLabel missionLabel = new SecondaryMissionLabel();
			missionLabel.setPosition(50, 150);
			addActor(missionLabel);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		private void addMissionLabel() {
			MissionLabel missionLabel = new MissionLabel();
			missionLabel.setPosition(50, -60);
			addActor(missionLabel);
		}
	}

	public class CreateTankListener implements SpecialListener {

		@Override
		public void onPlayOver() {
			nextTank();
		}

	}

	public class DeathListener implements HpListener {

		private void showCoinSpecial(EnemyTank tank) {
			showCoin(tank);
			showCoinValue(tank);
		}

		private void showCoinValue(EnemyTank tank) {
			Label l = new Label(getText(tank), App.getAssets().getLabelStyle1());
			float x = tank.getX();
			float y = tank.getY();
			l.setPosition(x, y);

			GameStage stage = App.getApp().getStage();
			stage.getGameGroup().addActor(l);
			l.addAction(new UpAction(l));
		}

		private String getText(EnemyTank tank) {
			return "+" + Util.Math.getFormatText(tank.getCoin());
		}

		private void showCoin(EnemyTank tank) {
			int count = Util.Random.get(4, 10);
			for (int i = 0; i < count; i++) {
				CoinSpecial s = new CoinSpecial();
				s.setPosition(tank.getX(), tank.getY());
				App.getApp().getStage().getGameGroup().addActor(s);
			}
		}

		private void showBombforge() {
			String[] ls = crateList(tank);
			String t = "data/game.txt";
			GameAnimation bombForge = AnimationCreator.create(t, 0.5f, ls);
			Effect s = new Effect(bombForge);
			s.setPosition(tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2);
			s.addListener(new CreateTankListener());
			App.getApp().getStage().getGameGroup().addActor(s);
		}

		private String[] crateList(EnemyTank tank) {
			String color = tank.getColorText();
			return create(color, "5", "4", "3");
		}

		private String[] create(String color, String... a) {
			for (int i = 0; i < a.length; i++) {
				a[i] = "smoke" + color + a[i];
			}
			return a;
		}

		@Override
		public void onDeath(Hp hp) {
			showBombforge();
			App.getDb().addCoin(tank.getCoin());
			showCoinSpecial(tank);
			removeOld();
			App.getDb().passMission();
		}

	}

	private int level;
	private EnemyTank tank;
	private Stone stone;
	private Home home;
	private EnemyTankSelector selector;

	protected void next() {

	}

	public EnemyTankGround(Stone stone, Home home, int level) {
		Image image = new Image(Assets.getSd().findRegion("data/game.txt", "grass"));
		addActor(image);
		this.stone = stone;
		this.home = home;
		this.level = level;
		setSize(200, 200);
		selector = new EnemyTankSelector();
		addActor(selector);
	}

	public EnemyTank getTank() {
		return tank;
	}

	public int getLevel() {
		return level;
	}

	public void nextTank() {
		removeOld();
		createNext();
	}

	private void createNext() {
		TankConfig config = new TankConfig();

		String script = Scripts.get("enemy-tank.js");
		App.getScript().call(script, "createEnemyTank", config);

		tank = new EnemyTank(stone, home, getX() + 60, getY() + 60, config);
		tank.rotationBarrel(90);

		tank.addListener(new DeathListener());
		GameStage s = App.getApp().getStage();
		Group gg = s.getGameGroup();
		gg.addActor(tank);
	}

	private void removeOld() {
		if (tank != null)
			tank.remove();
	}
}
