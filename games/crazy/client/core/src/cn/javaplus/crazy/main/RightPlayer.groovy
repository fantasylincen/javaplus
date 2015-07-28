package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.Protocols.OtherP;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Right;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class RightPlayer implements IPlayerUI {

	private int placeNumber;
	private Right ui;
	private PockerBox box;

	public RightPlayer(OtherP right, Right rightUi, MiddleUI middleUI) {
		this.ui = rightUi;
		placeNumber = right.getPlayer().getPlaceNumber();
		box = new PockerBox(ui.getRightpockerbox().toGroup(), middleUI);
		rightUi.getRightnick().setText(right.getPlayer().getNick());
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	@Override
	public void showMessage(String message) {
		new LeftRightUiMessageShower().showMessage(this, message);
	}

	@Override
	public void onSendCards(List<CardP> cards) {

	}

	@Override
	public Actor getHead() {
		return ui.getRighthead();
	}

	@Override
	public IPockerBox getPockerBox() {
		return box;
	}

	@Override
	public Actor getBuChu() {
		return ui.getRightbuchu();
	}

	@Override
	public Actor getBuJiao() {
		return ui.getRightbujiao();
	}

	@Override
	public Actor getJiaoDiZhu() {
		return ui.getRightjiaodizhu();
	}

	@Override
	public Actor getBuQiang() {
		return ui.getRightbuqiang();
	}

	@Override
	public Actor getQiangDiZhu() {
		return ui.getRightqiangdizhu();
	}

	@Override
	public TextField getMessageBox() {
		return ui.getRightmessagebox();
	}
}
