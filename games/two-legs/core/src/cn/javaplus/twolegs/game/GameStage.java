package cn.javaplus.twolegs.game;

import java.util.Set;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Sets;

import aurelienribon.bodyeditor.BodyEditorLoader;
import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.define.Events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class GameStage extends AbstractStage {

	private boolean isDebug = false;
	// private boolean isDebug = true;
	protected Box2DDebugRenderer render; // 测试用绘制器
	private World world;

	BodyEditorLoader loader;

	private Body ground;
	private Robot robot;

	private CameraController cameraController;
	private ControllerPanel controllerPanel;
	private GamePanel gamePanel;

	Label2 label;
	private Image deathImage;
	private AtlasRegion region;
	private Image guide;

	private final class GameUIImpl implements GameUI {
		@Override
		public void unload() {
		}

		@Override
		public void load() {
		}

		@Override
		public void buildComponents() {
		}
	}

	@Override
	public void onLoadingOver() {
		restart();
	}

	public void restart() {
		world = createWorld();

		FileHandle file = Assets.getInternal().getFile("data/bodys.json");
		loader = new BodyEditorLoader(file);
		addGround();
		robot = new Robot(this, world, loader);

		Camera camera = getCamera();
		cameraController = new CameraController(robot, camera);

		gamePanel = new GamePanel();
		if (!isDebug) {
			gamePanel.addActor(new Sky(camera));
			gamePanel.addActor(new Building(camera));
			gamePanel.addActor(new CloudGroup(camera));
			gamePanel.addActor(new SignGroup(camera, robot));
			gamePanel.addActor(new RobotActor(robot));
			gamePanel.addActor(new Ground(camera));
			gamePanel.addActor(new SkyMask(camera));
		}
		addActor(gamePanel);

		label = new Label2();
		addControllerPanel();
		createBox2dDebuger();

		region = Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion("face");
		deathImage = new Image(region);
		deathImage.setSize(0, 0);

		if (!isDebug) {
			addGuideImage();
			if (getGuideConfig().contains(getPlayTimes())) {
				showGuide();
			}
		}
	}

	private Set<Integer> getGuideConfig() {
		String value = App.getConfigs().getConfig("GUIDE_SHOW_ROUNDS", "0,3,9");
		String[] split = value.split(",");
		Set<Integer> set = Sets.newHashSet();
		for (String string : split) {
			if (!string.isEmpty())
				set.add(new Integer(string));
		}
		return set;
	}

	private void addGuideImage() {
		guide = new Image(Assets.getInternal().getTextureAtlas("data/robot.txt").findRegion(
				"guide"));
		guide.setPosition(250, 350);
		gamePanel.addActor(guide);
		controllerPanel.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				guide.setVisible(false);
				return true;
			}
		});
		guide.setVisible(false);
	}

	private int getPlayTimes() {
		IPreferences p = App.getPreferences();
		return p.getInt("play-times");
	}

	private void showGuide() {
		guide.setVisible(true);
		App.getPreferences().put("is-first-play", true);
	}

	private void addControllerPanel() {
		controllerPanel = new ControllerPanel(robot, this);
		addActor(controllerPanel);
	}

	private void createBox2dDebuger() {
		render = new Box2DDebugRenderer();
		render.setDrawJoints(true);
		render.setDrawContacts(true);
		render.setDrawInactiveBodies(true);
	}

	public GameStage() {
		super(new ScalingViewport(Scaling.stretch, D.STAGE_W, D.STAGE_H,
				new OrthographicCamera()));

	}

	@Override
	public boolean keyTyped(char c) {

		Log.d(0 + c);
		
		if (robot.isDeath() && c == 0) {
			App.getApp().restart();
			return true;
		}

		if (controllerPanel.isRankingListShowing()) {
			if (c == 27 || c == 0) {
				controllerPanel.hideRankingList();
				return true;
			}
		}

		if (c == 27 || c == 0) {
			Events.dispatch(new ExitEvent());
			return true;
		}

		return true;
	}

	@Override
	public void draw() {
		// if (isDebug)
		// Gdx.gl20.glClearColor(1, 1, 1, 1);
		if (cameraController != null) {
			cameraController.step();
			Vector3 position = getCamera().position;
			controllerPanel.setPosition(position.x, position.y);

			robot.step();
		}
		super.draw();
		if (world != null) {
			world.step(Gdx.graphics.getDeltaTime(), 30, 30);
			if (isDebug) {
				// renderBox2dDebuger();
			}
		}
	}

	void renderBox2dDebuger() {
		float scale = D.BOX_2D_STAGE_SCALE;
		Matrix4 combined;
		combined = new Matrix4(getCamera().combined.scale(scale, scale, scale));
		render.render(world, combined);
	}

	private World createWorld() {
		return new World(new Vector2(0, 0), true);
	}

	private void addGround() {
		FileHandle file = Assets.getInternal().getFile("data/bodys.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = D.GROUND_FRICTION;
		fd.restitution = D.GROUND_RESTITUTION;
		Categroys.GROUND.bind(fd);

		ground = world.createBody(bd);

		loader.attachFixture(ground, D.GROUND, fd, 1);
	}

	@Override
	public void dispose() {
		super.dispose();
		render.dispose();
		world.dispose();
		render = null;
		world = null;
	}

	@Override
	public GameUI getGameUI() {
		return new GameUIImpl();
	}

	public void popScore(String scoreText) {

		Camera camera = getCamera();
		Vector3 position = camera.position;
		deathImage.setPosition(position.x, position.y - 170);
		deathImage.addAction(new ShowScoreAction(scoreText, position.x,
				position.y - 170));
		gamePanel.addActor(deathImage);
	}

	class ShowScoreAction extends SequenceAction {

		private final class SizeToActionImpl extends SizeToAction {
			@Override
			protected void end() {
				super.end();
				gamePanel.addActor(label);
			}
		}

		public ShowScoreAction(String scoreText, float imageX, float imageY) {

			label.setPosition(imageX + 170, imageY + 55);

			SizeToAction to = new SizeToActionImpl();

			to.setSize(region.getRegionWidth(), region.getRegionHeight());
			to.setDuration(0.2f);
			addAction(to);

			String text = "Ah... " + scoreText + "m";
			label.showOneByOne(text);
		}

	}

	public World getWorld() {
		return world;
	}
}
