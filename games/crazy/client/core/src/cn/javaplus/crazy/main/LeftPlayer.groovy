package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.Protocols.OtherP;
import cn.javaplus.crazy.R.Uis.GameUI;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Left;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Pockerui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class LeftPlayer implements IPlayerUI {

	private int placeNumber;
	private Left ui;
	private PockerBox box;
	private BigUI bigUI;
	private MiddleUI middleUI;

	public LeftPlayer(OtherP left, GameUI gameUI, MiddleUI middleUI) {
		this.middleUI = middleUI;
		this.ui = gameUI.getPanel().getLeft();

		placeNumber = left.getPlayer().getPlaceNumber();
		Group group = ui.getLeftpockerbox().toGroup();

		Pockerui ui = gameUI.getPanel().getPockerui();

		bigUI = new BigUI(ui);
		box = new PockerBox(group, bigUI);
		this.ui.getLeftnick().setText(left.getPlayer().getNick());
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	@Override
	public void showMessage(String message) {
		new LeftRightUiMessageShower().showMessage(this, message);
	}

	@Override
	public Actor getHead() {
		return ui.getLefthead();
	}

	@Override
	public IPockerBox getPockerBox() {
		return box;
	}

	@Override
	public Actor getBuChu() {
		return ui.getLeftbuchu();
	}

	@Override
	public Actor getBuJiao() {
		return ui.getLeftbujiao();
	}

	@Override
	public Actor getJiaoDiZhu() {
		return ui.getLeftjiaodizhu();
	}

	@Override
	public Actor getBuQiang() {
		return ui.getLeftbuqiang();
	}

	@Override
	public Actor getQiangDiZhu() {
		return ui.getLeftqiangdizhu();
	}

	@Override
	public void onSendCards(List<CardP> cards) {
		Group group = ui.getLeftpockerbox().toGroup();
		PockerBox boxLeavHand = new PockerBox(group, middleUI);
		new PockerBoxAdjust().onSendCards(cards, boxLeavHand, bigUI);
	}

	@Override
	public TextField getMessageBox() {
		return ui.getLeftmessagebox();
	}

}
