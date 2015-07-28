package cn.javaplus.crazy.main;

import cn.javaplus.crazy.Protocols.PlayHandlerTipEvent
import cn.javaplus.crazy.Protocols.TipP
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractTipCallBack

import com.badlogic.gdx.scenes.scene2d.ui.TextField

public class TipCallBack implements AbstractTipCallBack {

	/**
	 * 
	 */
	private final GameStage stage;

	/**
	 * @param gameStage
	 */
	TipCallBack(GameStage gameStage) {
		stage = gameStage;
	}

	@Override
	public void completed(PlayHandlerTipEvent e) {

		TipP back = e.getBack();
		String message = back.getMessage();
		int no = back.getPlaceNumber();

		IPlayerUI ui = stage.getUiByPlaceNumber(no);

		TextField tip = ui.getMessageBox();
		tip.setText("");

		ui.showMessage(message);

		Log.d("TipCallBack.completed ", ui.getClass(), no, message);
	}
}