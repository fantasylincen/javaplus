package cn.javaplus.crazy.main;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.Protocols.PlayHandler;
import cn.javaplus.crazy.cp.ChuPaiCallBack;

public class CallBackSets {
	public void setCallBacks(GameStage stage) {
		PlayHandler h = AppContext.getClient().getPlayHandler();
		h.waitTip(new TipCallBack(stage));
		h.waitStartPlayPocker(new StarPlayPockerCallBack((GameStage) AppContext.getStage()));
		h.waitChuPai(new ChuPaiCallBack());
		h.waitClearAllTip(new ClearAllTipCallBack());
	}
}
