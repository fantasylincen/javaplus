package cn.javaplus.clickscreen.game;

import java.util.ArrayList;
import java.util.List;

import org.javaplus.clickscreen.excel.Row;
import org.javaplus.clickscreen.excel.Sheet;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.define.D;
import cn.javaplus.clickscreen.events.Events;
import cn.javaplus.clickscreen.label.CoinLabel;
import cn.javaplus.clickscreen.map.EnemyTankGround;
import cn.javaplus.clickscreen.tank.FireByTapListener;
import cn.javaplus.clickscreen.tank.Tank;
import cn.javaplus.clickscreen.tank.TankDto;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class GameStage extends AbstractStage {

	CoinLabel label;
	ControllerPanel panel = new ControllerPanel();
	EnemyTankGround map;
	Stone stone;
	Home home;
	Group gameGroup;
	Group controllerGroup;
	private Background background;

	public GameStage() {
		super(D.STAGE_W, D.STAGE_H);
		gameGroup = new Group();
		controllerGroup = new Group();
		addActor(gameGroup);
		addActor(controllerGroup);
	}

	@Override
	public void loadAssets() {

		App.getAssets().loadFontLocal("data/Entsani.ttf");
		App.getAssets().loadFontLocal("data/WildHoney.ttf");
		App.getAssets().setSystemFont("data/WildHoney.ttf");

		Loader ext = Assets.getSd();
		AnimationCreator.setLoader(ext);

		ext.loadTextureAtlas("data/game.txt");

		ext.loadBitmapFont("data/font.fnt");
		ext.loadTextureAtlas("data/ui-green.atlas");
		ext.loadTexture("data/PetNest03.png");
		ext.loadTexture("data/fat_oldboss02.png");
		ext.loadTextureAtlas("data/TBottle-hd.txt");
		ext.loadTexture("data/coin-sprite-sheet.png");
	}

	@Override
	public void onLoadingOver() {
		addBackground();
		addCoinLabel();
		addEnemyTankGround();
		addTanks();
		gameGroup.addActor(panel);
		addTapController();
	}

	private void addTapController() {
		Actor a = new Actor();
		a.setSize(10000, 10000);
		a.addListener(new FireByTapListener(getTanks()));
		background.addActor(a);
	}

	private List<Tank> getTanks() {
		Array<Actor> actors = gameGroup.getChildren();
		ArrayList<Tank> ls = Lists.newArrayList();
		for (Actor a : actors) {
			if(a instanceof Tank) {
				ls.add((Tank) a);
			}
		}
		return ls;
	}

	private void addTanks() {
		List<TankDto> tanks = App.getDb().getTanks();
		Sheet sheet = App.getStaticData().get("tanks");
		
		for (TankDto t : tanks) {

			Row row = sheet.get(t.getTankId());
			float x = row.getFloat("x");
			float y = row.getFloat("y");
			
			Tank tank = new Tank(map, t);
			tank.setPosition(x, y);
			tank.aimAt(map);
			gameGroup.addActor(tank);
		}
	}

	private void addBackground() {
		background = new Background();
		gameGroup.addActor(background);
	}

	private void addEnemyTankGround() {
		int groundLevel = App.getDb().getEnemyTankLevel();
		map = new EnemyTankGround(stone, home, groundLevel);
		map.setPosition(720, 180);
		gameGroup.addActor(map);
		map.nextTank();
	}

	private void addCoinLabel() {
		label = new CoinLabel();
		label.setSize(200, 50);
		label.setPosition(900, D.STAGE_H - 100);
		panel.addActor(label);
	}

	@Override
	public boolean keyTyped(char c) {

		if (c == 27 || c == 0) {
			Events.dispatch(new ExitEvent());
			return true;
		}
		if ('d' == c) {
			App.getPreferences().clear();
			Log.d("clear preferences");
		}

		return true;
	}

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 v = screenToStageCoordinates(new Vector2(screenX, screenY));
		System.out.println(v.x + " " + v.y);
		return super.touchDown(screenX, screenY, pointer, button);
	}

	public Group getGameGroup() {
		return gameGroup;
	}

	public Group getControllerGroup() {
		return controllerGroup;
	}
}
