package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.game.Marsking;
import org.javaplus.game.common.log.Log;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.share.ShareButton;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ControllerPanel extends Group {

	private RankingListButton rankingListButton;
	private Button shareButton;
	private BestScorePanel bestScorePanel;
	private Skin rankingListSkin;
	private GameStage stage;
	private Robot robot;
	private RobotController controller;
	private ScorePanel scorePanel;

	static final int WINDOW_W = D.STAGE_W - 200;
	static final int WINDOW_H = D.STAGE_H - 70;
	private Window window;
	private Marsking marsking;

	public ControllerPanel(Robot robot, GameStage stage) {
		this.robot = robot;
		this.stage = stage;
		init();
		addScorePanel();
		addBestScorePanel();
		addRobotTouchPad();
		addRankingListButton();
		addShareButton();
	}

	private void addShareButton() {
		shareButton = new ShareButton();
		shareButton.setSize(100, 75);
		int x = -D.STAGE_W / 2 + 60;
		float y = D.STAGE_H / 2 - shareButton.getHeight() - 142;
		shareButton.setPosition(x, y);
		
		String isShowShareButton = App.getConfigs().getConfig("IS_SHOW_SHARE_BUTTON", "0");
//		if(isShowShareButton.equals("1")) {
			addActor(shareButton);
			
//		}
	}

	private void addScorePanel() {
		scorePanel = new ScorePanel(robot);
		addActor(scorePanel);
	}

	private void addRobotTouchPad() {
		Actor a = new RobotTouchPad(controller);
		a.setSize(getWidth(), getHeight());
		a.setPosition(-getWidth() / 2, -getHeight() / 2);
		addActor(a);
	}

	private void init() {

		this.setSize(D.STAGE_W, D.STAGE_H);

		TextureAtlas textureAtlas = Assets
				.getInternal().getTextureAtlas(("data/ui-green.atlas"));
		rankingListSkin = new Skin(textureAtlas);

		controller = new RobotController(stage, robot);
	}

	private void addBestScorePanel() {
		bestScorePanel = new BestScorePanel(robot, scorePanel);
		addActor(bestScorePanel);
	}

	private void addRankingListButton() {
		rankingListButton = new RankingListButton();
		rankingListButton.setSize(100, 75);
		int x = -D.STAGE_W / 2 + 60;
		float y = D.STAGE_H / 2 - rankingListButton.getHeight() - 60;
		rankingListButton.setPosition(x, y);
		addActor(rankingListButton);
		addShowRankingListListener();
	}

	private void addShowRankingListListener() {
		rankingListButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				showRankingList();
				new RankingListRequest(window).request();
			}

		});
		
		rankingListButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				App.getLogger().onCountEvent("showRankingList");
				Log.d("showRankingList");
			}

		});
	}

	private void showRankingList() {

		WindowStyle style = new WindowStyle();
		style.background = rankingListSkin.getDrawable("window_01");

		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = Messages.getString("ControllerPanel.1");
		p.size = 24;
		style.titleFont = App.getAssets().getGenerator().generateFont(p);

		String title = Messages.getString("ControllerPanel.1");

		window = new Window(title, style);

		window.setSize(WINDOW_W - 70, WINDOW_H - 90);
		window.setMovable(false);
		float x = D.STAGE_W / 2 - window.getWidth() / 2;
		float y = D.STAGE_H / 2 - window.getHeight() / 2;
		window.setPosition(x, y);
		addRankingListAction(window);

		ImageButtonStyle buttonStyle = new ImageButtonStyle();
		buttonStyle.imageUp = rankingListSkin.getDrawable("button_cross");
		buttonStyle.imageDown = buttonStyle.imageUp;
		buttonStyle.imageOver = buttonStyle.imageUp;

		marsking = new MarskingFullScreen(window);
		addActor(marsking);

	}

	private void addRankingListAction(Window w) {
		SequenceAction ss = new SequenceAction();
		ParallelAction aa = new ParallelAction();

		float duration = 0.1f;

		SizeToAction to = new SizeToAction();
		to.setDuration(duration);
		to.setHeight(WINDOW_H + 15);
		to.setWidth(WINDOW_W + 15);

		MoveToAction mto = new MoveToAction();
		mto.setDuration(duration);
		float x = D.STAGE_W / 2 - WINDOW_W / 2;
		float y = D.STAGE_H / 2 - WINDOW_H / 2;
		mto.setX(x);
		mto.setY(y);

		AlphaAction ala = new AlphaAction();
		ala.setAlpha(1);
		ala.setDuration(duration);

		Color c = w.getColor();
		c.set(c.r, c.g, c.b, 0);

		w.setColor(c);

		aa.addAction(ala);
		aa.addAction(to);
		aa.addAction(mto);

		ss.addAction(aa);

		SizeToAction tt = new SizeToAction();
		tt.setDuration(duration / 4);
		tt.setHeight(WINDOW_H);
		tt.setWidth(WINDOW_W);

		ss.addAction(tt);
		w.addAction(ss);
	}

	public boolean isRankingListShowing() {
		if (marsking == null) {
			return false;
		}
		return getChildren().contains(marsking, true);
	}

	public void hideRankingList() {
		if (marsking != null) {
			marsking.remove();
		}
	}
}
