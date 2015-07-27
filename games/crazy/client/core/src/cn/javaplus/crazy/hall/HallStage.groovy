package cn.javaplus.crazy.hall;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.D;
import cn.javaplus.crazy.Protocols;
import cn.javaplus.crazy.Protocols.EnterHandler;
import cn.javaplus.crazy.Protocols.PlayHandler;
import cn.javaplus.crazy.Protocols.PlayHandlerEnterOldTableEvent;
import cn.javaplus.crazy.Protocols.PlayHandlerGameStartEvent;
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractEnterOldTableCallBack;
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractGameStartCallBack;
import cn.javaplus.crazy.Protocols.TableP;
import cn.javaplus.crazy.R;
import cn.javaplus.crazy.R.Uis.HallUI;
import cn.javaplus.crazy.main.ButtonListener;
import cn.javaplus.crazy.main.GameStage;
import cn.javaplus.crazy.stage.AbstractStage;
import cn.javaplus.crazy.util.VectorInt;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class HallStage extends AbstractStage {

	private HallUI hallUI;

	public class EnterOldTableCallBack implements AbstractEnterOldTableCallBack {

		@Override
		public void completed(PlayHandlerEnterOldTableEvent e) {
			TableP back = e.getBack();
			changeToGameStage(back);
		}
	}

	public class GameStartCallBack implements AbstractGameStartCallBack {
		@Override
		public void completed(PlayHandlerGameStartEvent e) {
			TableP back = e.getBack();
			changeToGameStage(back);
		}
	}

	public class EnterListener extends ButtonListener {

		@Override
		protected void onClick() {
			enter();
		}

	}

	private void changeToGameStage(TableP back) {
		GameStage stage = new GameStage(back);
		AppContext.setStage(stage);
	}

	public void enter() {
		Protocols c = AppContext.getClient();
		EnterHandler h = c.getEnterHandler();
		h.pk(null);

		PlayHandler ph = c.getPlayHandler();
		ph.waitGameStart(new GameStartCallBack());
		ph.waitEnterOldTable(new EnterOldTableCallBack());
	}

	public HallStage() {
		super(new ScalingViewport(Scaling.stretch, D.STAGE_W, D.STAGE_H,
				new OrthographicCamera()), null);
	}

	@Override
	public void onLoadingOver() {

		ImageButton room1 = hallUI.getPanel().getRoom1();
		room1.addListener(new EnterListener());
		addActor(hallUI.getPanel().toGroup());

		enter();
	}

	@Override
	public OrthographicCamera getCamera() {
		return (OrthographicCamera) super.getCamera();
	}

	public VectorInt screenToStageCoordinates(VectorInt screenCoords) {
		Vector2 vv = VectorInt.parse(screenCoords);
		Vector2 v = super.screenToStageCoordinates(vv);
		return VectorInt.parse(v);
	}

	@Override
	public HallUI getCocosUI() {
		if(hallUI == null) {
			hallUI = R.Uis.createHallUI();
		}
		return hallUI;
	}

}
