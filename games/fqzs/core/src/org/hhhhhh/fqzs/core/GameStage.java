package org.hhhhhh.fqzs.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.event.Events;
import org.hhhhhh.fqzs.event.ExitEvent;
import org.hhhhhh.fqzs.http.FqzsClient;
import org.hhhhhh.fqzs.result.GetResultRequest;
import org.hhhhhh.fqzs.result.PlayResult;
import org.hhhhhh.fqzs.result.RollEndEvent;
import org.hhhhhh.fqzs.result.Roller;
import org.hhhhhh.fqzs.result.RollerLitener;
import org.javaplus.clickscreen.button.IButtonListener;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.animation.AnimationCreator;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Space;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.google.common.collect.Maps;

public class GameStage extends AbstractStage {

	public class UpdateUserCoinListener implements RollerLitener {

		@Override
		public void onRollEnd(RollEndEvent e) {
			long coin = playResult.getCoin();
			App.getRoleData().setCoin(coin);
		}

	}

	public class MyCoin extends Actor {
		
		protected BitmapFont font;
		
		public MyCoin() {
			GameAssets s = App.getAssets();
			FreeTypeFontGenerator g = s.getGenerator();
			FreeTypeFontParameter data = new FreeTypeFontParameter();
			data.color = Color.WHITE;
			data.size = 32;
			data.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
			font = g.generateFont(data);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if(font != null ) {
				font.draw(batch, App.getRoleData().getCoin() + "", getX(), getY());
			}
		}
	}

	public class RestartNewRoundLitener implements RollerLitener {

		@Override
		public void onRollEnd(RollEndEvent e) {
			synchronizer.start();
			timer.show();
			e.getRoller().remove();
		}
	}

	public class GetResultCallBack extends CallBackJsonAdaptor {

		@Override
		public void completed(JsonResult result) {
			String text = result.toString();
			Log.d("back result", result);
			playResult = JSON.parseObject(text, PlayResult.class);
			startRoll();
		}

		private void startRoll() {
			Log.d("start roll");
			Roller roller = getRoller();
			gameGroup.addActor(roller);
			roller.startRoll(playResult, lights);
		}
	}

	private Roller roller;

	public class UpdateTimerSecListener implements SynchronizeListener {

		@Override
		public void onSynchronize(SynchronizeEvent e) {
			TableData data = e.getTableData();
			long cd = data.getCd();
			float sec = cd / 1000f;
			timer.setSec(sec);
		}

	}

	public Roller getRoller() {
		if (roller == null) {
			roller = new Roller();
			roller.addListener(new RestartNewRoundLitener());
			roller.addListener(new UpdateUserCoinListener());
		}
		return roller;
	}

	public class Timer extends Actor {

		private BitmapFont font;

		private float sec;

		public Timer() {
			GameAssets s = App.getAssets();
			FreeTypeFontGenerator g = s.getGenerator();
			FreeTypeFontParameter data = new FreeTypeFontParameter();
			data.color = Color.WHITE;
			data.size = 108;
			data.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
			font = g.generateFont(data);
		}

		public void show() {
			setVisible(true);
		}

		List<TimerListener> liteners = Lists.newArrayList();

		public void addListener(TimerListener l) {
			this.liteners.add(l);
		}

		public void setSec(float sec) {
			if (sec <= 0) {
				sec = 0;
			}

			int oldSec = (int) this.sec;
			int newSec = (int) sec;
			if (oldSec != newSec) {
				for (TimerListener l : liteners) {
					l.onSecChange(new SecChangeEvent(oldSec, newSec));
				}
				Log.d("sec changed", oldSec, newSec);
			}

			this.sec = sec;
		}

		public void update(float delta) {
			setSec(sec - delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			font.draw(batch, getSec() + "", getX(), getY());
		}

		private int getSec() {
			return (int) sec;
		}

		public void hide() {
			setVisible(false);
		}

	}

	public class SynchornizeDataRequest implements Request {

		@Override
		public String getUrl() {
			String path = "turntable/synchronizeData";
			return buildFullPath(path);
		}

		private String buildFullPath(String path) {
			GameConfig config = App.getConfig();
			String rootPath = config.getRootPath();
			String url = rootPath + path;
			return url;
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			for (String id : BUTTON_IDS) {
				p.put(id, buttons.get(id).getValue());
			}
			return p;
		}

	}

	static List<String> BUTTON_IDS = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");

	public class Synchronizer {

		public class UpdateTableDataAction implements Runnable {

			@Override
			public void run() {
				if (isPause)
					return;
				FqzsClient http = App.getHttp();
				CallBackJson back = new SynchornizeDataCallBack();
				Request request = new SynchornizeDataRequest();
				http.request(request, back);
				Log.d("request synchronizeData");
			}

		}

		public class SynchornizeDataCallBack extends CallBackJsonAdaptor {

			@Override
			public void completed(JsonResult result) {

				String text = result.toString();
				Log.d("back result", result);
				tableData = JSON.parseObject(text, TableData.class);
				for (SynchronizeListener l : listeners) {
					l.onSynchronize(new SynchronizeEvent(tableData));
				}
			}

		}

		Space s = new Space(3);
		private List<SynchronizeListener> listeners = Lists.newArrayList();
		private boolean isPause;

		public Synchronizer() {
			s.addAction(new UpdateTableDataAction());
		}

		public void update(float delta) {
			s.update(delta);
		}

		public void addSynchronizeListener(SynchronizeListener listener) {
			this.listeners.add(listener);
		}

		public void pause() {
			this.isPause = true;
		}

		public void start() {
			this.isPause = false;
		}

	}

	public static class Light extends Image {

		private String id;
		private String type;

		public Light(String type) {
			super(getRegion(type));
		}

		private static AtlasRegion getRegion(String type) {
			Loader d = Assets.getDefaultLoader();
			TextureAtlas a = d.getTextureAtlas("data/game.txt");
			AtlasRegion findRegion = a.findRegion(type);
			return findRegion;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getBehindId() {
			return behindId;
		}

		public void setBehindId(String behindId) {
			this.behindId = behindId;
		}

		private String behindId;
		private Light behind;
		private Light next;

		public void setBehind(Light behind) {
			this.behind = behind;

		}

		public Light getBehind() {
			return behind;
		}

		public void setNext(Light next) {
			this.next = next;
		}

		public Light getNext() {
			return next;
		}

	}

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

		App.getAssets().loadFontInternal("data/DS-DIGI.TTF");
		App.getAssets().setSystemFont("data/DS-DIGI.TTF");

		Loader d = Assets.getDefaultLoader();
		AnimationCreator.setLoader(d);

		d.loadTextureAtlas("data/game.txt");

		d.loadBitmapFont("data/font.fnt");
		d.loadTextureAtlas("data/ui-green.atlas");
		d.loadTexture("data/PetNest03.png");
		d.loadTexture("data/coin-sprite-sheet.png");
		d.loadTexture("data/fat_oldboss02.png");
		d.loadTextureAtlas("data/TBottle-hd.txt");
		d.loadTexture("data/coin-sprite-sheet.png");

	}

	private Map<String, Light> lights = Maps.newHashMap();
	private Map<String, FqzsButton> buttons = Maps.newHashMap();
	Synchronizer synchronizer;
	Timer timer;
	TableData tableData;

	private PlayResult playResult;

	public PlayResult getPlayResult() {
		return playResult;
	}

	@Override
	public void onLoadingOver() {
		addBackground();
		Actor panel = new Actor();
		gameGroup.addActor(panel);
		initGameTable();// 游戏桌面

		initTimer();
		initSynchronizer();

		initControlPanel();
		
		initMyCoinText();
	}

	private void initMyCoinText() {
		MyCoin actor = new MyCoin();
		actor.setPosition(500, 500);
		addActor(actor);
	}

	private void initControlPanel() {
		addButtons();
	}

	private void addButtons() {
		List<ButtonLocation> ls = getButtonLocation();
		for (ButtonLocation bt : ls) {
			final FqzsButton b = new FqzsButton(bt.getId());
			b.setPosition(bt.getX(), bt.getY());

			b.addButtonListener(new IButtonListener() {

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void action(ChangeEvent event, Actor actor) {
					if ("qh".equals(b.getId())) {
						qh();
					} else if ("qx".equals(b.getId())) {
						qx();
					} else if ("xy".equals(b.getId())) {
						xy();
					} else {
						b.addValue(getValueEveryTime());
					}
				}

			});

			addActor(b);

			buttons.put(b.getId(), b);
		}
	}

	private int getValueEveryTime() {
		return buttons.get("qh").getValue();
	}

	private void xy() {
		Log.d("xy");
	}

	private void qx() {
		for (String id : BUTTON_IDS) {
			buttons.get(id).setValue(0);
		}
	}

	private void qh() {
		String qh = App.getProperties().get("qh");
		String[] vv = qh.split(",");
		ArrayList<Integer> ls = Lists.newArrayList();
		for (String v : vv) {
			int value = new Integer(v);
			ls.add(value);
		}
		
		FqzsButton bt = buttons.get("qh");
		qh(bt, ls);
	}

	private void qh(FqzsButton bt, ArrayList<Integer> ls) {
		int value = bt.getValue();
		ls.addAll(ls);
		
		int index = ls.indexOf(value);
		
		Integer v = ls.get(index + 1);
		bt.setValue(v);
	}

	private List<ButtonLocation> getButtonLocation() {
		ArrayList<ButtonLocation> ls = Lists.newArrayList();
		FileHandle file = Assets.getDefaultLoader().getFile("data/buttonList.txt");
		String string = file.readString();
		String[] lines = string.split("\r");
		for (String s : lines) {
			if (!s.trim().isEmpty()) {
				String[] ss = s.split(":");
				String id = ss[0].trim();
				String x = ss[1].trim();
				String y = ss[2].trim();

				ButtonLocation e = new ButtonLocation();
				e.setId(id);
				e.setX(new Integer(x));
				e.setY(new Integer(y));
				ls.add(e);
			}
		}

		return ls;
	}

	private void initTimer() {
		timer = new Timer();
		addRequestResultListener();
		timer.setPosition(320, 900);
		addActor(timer);
	}

	private void addRequestResultListener() {
		TimerListener l = new TimerListener() {

			@Override
			public void onSecChange(SecChangeEvent e) {
				int oldSec = e.getOldSec();
				int newSec = e.getNewSec();
				if (oldSec == 1 && newSec == 0 || oldSec < newSec) {
					requestResult();
					timer.hide();
					synchronizer.pause();
				}
			}

			private void requestResult() {
				CallBackJson back = new GetResultCallBack();
				Request request = new GetResultRequest();
				App.getHttp().request(request, back);
			}
		};
		timer.addListener(l);
	}

	private void initSynchronizer() {
		synchronizer = new Synchronizer();
		synchronizer.addSynchronizeListener(new UpdateTimerSecListener());
	}

	private List<LightLocation> getLightLocation() {
		ArrayList<LightLocation> ls = Lists.newArrayList();
		FileHandle file = Assets.getDefaultLoader().getFile("data/lightList.txt");
		String string = file.readString();
		String[] lines = string.split("\r");
		for (String s : lines) {
			if (!s.trim().isEmpty()) {
				String[] ss = s.split(":");
				String id = ss[0].trim();
				String behindId = ss[1].trim();
				String x = ss[2].trim();
				String y = ss[3].trim();
				String type = ss[4].trim();

				LightLocation e = new LightLocation();
				e.setId(id);
				e.setBehindId(behindId);
				e.setType(type);
				e.setX(new Integer(x));
				e.setY(new Integer(y));
				ls.add(e);
			}
		}

		return ls;
	}


	private void initGameTable() {

		List<LightLocation> ls = getLightLocation();
		for (LightLocation location : ls) {
			Light light = new Light(location.getType());
			light.setPosition(location.getX(), location.getY());
			light.setId(location.getId());
			light.setType(location.getType());
			light.setBehindId(location.getBehindId());
			lights.put(location.getId(), light);
		}

		for (Light l : lights.values()) {
			Light behind = lights.get(l.getBehindId());
			l.setBehind(behind);

			behind.setNext(l);
		}

		for (Light l : lights.values()) {
			gameGroup.addActor(l);
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (synchronizer != null)
			synchronizer.update(delta);
		if (timer != null)
			timer.update(delta);

		// try {
		// resetButtonPositions();
		// resetPositions();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private void resetButtonPositions() {
		List<ButtonLocation> ls = getButtonLocation();
		for (ButtonLocation bt : ls) {
			FqzsButton bb = buttons.get(bt.getId());
			if (bb != null)
				bb.setPosition(bt.getX(), bt.getY());
		}
	}

	private void resetPositions() {
		List<LightLocation> ls = getLightLocation();
		for (LightLocation location : ls) {
			Light light = lights.get(location.getId());
			if (light == null)
				return;
			light.setX(location.getX());
			light.setY(location.getY());
		}
	}

	private void addBackground() {
		background = new Background();
		gameGroup.addActor(background);
	}

	@Override
	public boolean keyTyped(char c) {

		if (c == 27 || c == 0) {
			Events.dispatch(new ExitEvent());
			return true;
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
