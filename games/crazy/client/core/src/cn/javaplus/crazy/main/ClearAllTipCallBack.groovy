package cn.javaplus.crazy.main;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.Protocols.PlayHandlerClearAllTipEvent;
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractClearAllTipCallBack;
import cn.javaplus.crazy.stage.IStage;

public class ClearAllTipCallBack implements AbstractClearAllTipCallBack {

	@Override
	public void completed(PlayHandlerClearAllTipEvent e) {
		IStage stage = AppContext.getStage();
		if (stage instanceof GameStage) {
			GameStage s = (GameStage) stage;
			MyPlayer mine = s.getMine();
			LeftPlayer left = s.getLeft();
			RightPlayer right = s.getRight();
			clearTip(left);
			clearTip(right);
			clearTip(mine);
		}
	}

	private void clearTip(IPlayerUI p) {
		p.getMessageBox().setText("");
	}

}
