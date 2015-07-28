package cn.javaplus.crazy.cp;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.Protocols.CpP;
import cn.javaplus.crazy.Protocols.PlayHandlerChuPaiEvent;
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractChuPaiCallBack;
import cn.javaplus.crazy.main.GameStage;
import cn.javaplus.crazy.main.IPlayerUI;
import cn.javaplus.crazy.stage.IStage;

public class ChuPaiCallBack implements AbstractChuPaiCallBack {

	@Override
	public void completed(PlayHandlerChuPaiEvent e) {

		CpP back = e.getBack();
		int no = back.getPlaceNumber();

		IStage s = AppContext.getStage();
		if (s instanceof GameStage) {
			GameStage stage = (GameStage) s;
			IPlayerUI ui = stage.getUiByPlaceNumber(no);

			ui.getMessageBox().setText("");
			
			clearAllPockers(stage);
			ui.onSendCards(back.getCards());
		}

	}

	private void clearAllPockers(GameStage stage) {
		clearPocker(stage.getMine());
		clearPocker(stage.getLeft());
		clearPocker(stage.getRight());
	}

	private void clearPocker(IPlayerUI ui) {
		ui.getPockerBox().clear()	;
	}

}
