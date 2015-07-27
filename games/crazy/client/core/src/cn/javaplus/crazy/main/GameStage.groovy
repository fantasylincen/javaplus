package cn.javaplus.crazy.main;

import cn.javaplus.crazy.R
import cn.javaplus.crazy.Protocols.TableP
import cn.javaplus.crazy.R.Uis.GameUI
import cn.javaplus.crazy.R.Uis.GameUI.Panel
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Mpockerui
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Pockerui
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Right
import cn.javaplus.crazy.login.MessageBox
import cn.javaplus.crazy.stage.AbstractStage
import cn.javaplus.crazy.util.VectorInt

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScalingViewport

public class GameStage extends AbstractStage {

	public class MessageBoxImpl implements MessageBox {

		private TextField messagebox;

		public MessageBoxImpl(TextField messagebox) {
			this.messagebox = messagebox;
		}

		@Override
		public void showMessage(String text) {
			messagebox.setMessageText(text);
			messagebox.clearActions();
			messagebox.setColor(Color.WHITE);
			AlphaAction action = new AlphaAction();
			action.setDuration(1.5f);
			action.setAlpha(0);
			messagebox.addAction(action);
		}

	}

	GameUI gameUI;
	private LeftPlayer left;
	private RightPlayer right;
	private MyPlayer mine;
	private TableP back;
	private BigUI bigUI;
	private MiddleUI middleUI;

	public GameStage(TableP back) {
		super(new ScalingViewport(Scaling.stretch, D.STAGE_W, D.STAGE_H,
				new OrthographicCamera()), null);
		this.back = back;

		Panel p = gameUI.getPanel();
		Mpockerui mui = p.getMpockerui();
		Pockerui bui = p.getPockerui();
		bigUI = new BigUI(bui);
		middleUI = new MiddleUI(mui);
	}

	public GameUI getGameUI() {
		return gameUI;
	}

	@Override
	public void onLoadingOver() {
		clear();
		this.left = new LeftPlayer(back.getLeft(), gameUI, middleUI);
		Right right2 = gameUI.getPanel().getRight();
		this.right = new RightPlayer(back.getRight(), right2, middleUI);
		this.mine = new MyPlayer(back.getMine(), gameUI, bigUI);
		Panel p = gameUI.getPanel();
		addActor(p.toGroup());
		new CallBackSets().setCallBacks(this);
	}

	@Override
	public OrthographicCamera getCamera() {
		return (OrthographicCamera) super.getCamera();
	}

	@Override
	public MessageBox getMessageBox() {
		Panel p = gameUI.getPanel();
		TextField box = p.getMy().getMessagebox();
		return new MessageBoxImpl(box);
	}

	public VectorInt screenToStageCoordinates(VectorInt screenCoords) {
		Vector2 vv = VectorInt.parse(screenCoords);
		Vector2 v = super.screenToStageCoordinates(vv);
		return VectorInt.parse(v);
	}

	@Override
	public GameUI getCocosUI() {
		if (gameUI == null) {
			gameUI = R.Uis.createGameUI();
		}
		return gameUI;
	}

	public IPlayerUI getUiByPlaceNumber(int nb) {
		if (left.getPlaceNumber() == nb) {
			return left;
		}
		if (right.getPlaceNumber() == nb) {
			return right;
		}
		if (mine.getPlaceNumber() == nb) {
			return mine;
		}
		throw new IllegalArgumentException();
	}

	public LeftPlayer getLeft() {
		return left;
	}

	public RightPlayer getRight() {
		return right;
	}

	public MyPlayer getMine() {
		return mine;
	}
}
