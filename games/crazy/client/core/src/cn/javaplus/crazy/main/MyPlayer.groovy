package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.D;
import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.Protocols.MineP;
import cn.javaplus.crazy.R.Uis.GameUI;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.My;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.My.Pockerbox;
import cn.javaplus.crazy.cp.BeforeCpEvent;
import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.util.Util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class MyPlayer implements IPlayerUI{

	private int placeNumber;
	private My my;
	private IPockerBox box;
	private CardUI bigUI;

	public MyPlayer(MineP mine, GameUI ui, CardUI bigUI) {
		this.bigUI = bigUI;
		My my = ui.getPanel().getMy();
		this.my = my;
		
		placeNumber = mine.getPlayer().getPlaceNumber();
		Pockerbox box = my.getPockerbox();
		
		this.box = new PockerBox(box.toGroup(), bigUI);
		getPockerBox().showCards(mine.getCards());
		
		my.getButtonjiaodizhu().addListener(new JdzListener());
		my.getButtonbujiao().addListener(new BjdzListener());
		my.getButtonqiangdizhu().addListener(new QdzListener());
		my.getButtonbuqiang().addListener(new BqdzListener());
		
		my.getButtonchupai().addListener(new CpListener());
		my.getButtonbuchu().addListener(new BcpListener());

	}

	public IPockerBox getPockerBox() {
		return box;
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	private final class JdzListener extends ButtonListener {
		@Override
		protected void onClick() {
			AppContext.getClient().getPlayHandler().jdz(true, null);
		}
	}

	private final class BjdzListener extends ButtonListener {

		@Override
		protected void onClick() {
			AppContext.getClient().getPlayHandler().jdz(false, null);
		}
	}

	private final class QdzListener extends ButtonListener {

		@Override
		protected void onClick() {
			AppContext.getClient().getPlayHandler().qdz(true, null);
		}
	}

	private final class BqdzListener extends ButtonListener {

		@Override
		protected void onClick() {
			AppContext.getClient().getPlayHandler().qdz(false, null);
		}
	}

	private final class CpListener extends ButtonListener {

		@Override
		protected void onClick() {
			List<CardActor> cards = box.getSelectedCards();
			List<Integer> ids = box.getSelectedIds(cards);
			String id = Util.Collection.linkWith(ids, ",");
			Events.dispatch(new BeforeCpEvent(ids, MyPlayer.this));
			AppContext.getClient().getPlayHandler().cp(id, null);
		}
	}

	private final class BcpListener extends ButtonListener {

		@Override
		protected void onClick() {
			AppContext.getClient().getPlayHandler().bcp(null);
		}
	}

	@Override
	public void showMessage(String message) {

		if (message.equals(D.WAIT_JDZ)) {

			my.getButtonjiaodizhu().setVisible(true);
			my.getButtonbujiao().setVisible(true);
			
		} else if (message.equals(D.WAIT_QDZ)) {
			
			my.getButtonqiangdizhu().setVisible(true);
			my.getButtonbuqiang().setVisible(true);

		} else if (message.equals(D.JDZ)) {

			my.getMyjiaodizhu().setVisible(true);
			
		} else if (message.equals(D.BJDZ)) {

			my.getMybujiao().setVisible(true);
		} else if (message.equals(D.QDZ)) {

			my.getMyqiangdizhu().setVisible(true);
		} else if (message.equals(D.BQDZ)) {

			my.getMybuqiang().setVisible(true);
			
		} else if (message.equals(D.WAIT_CP)) {

			my.getButtonchupai().setVisible(true);
			my.getButtonbuchu().setVisible(true);
			my.getButtontishi().setVisible(true);
			
		} else {
			my.getMessagebox().setText("undefine:" + message);
		}
	}

	@Override
	public void onSendCards(List<CardP> cards) {
		new PockerBoxAdjust().onSendCards(cards, box, bigUI);
		IPockerBox box = getPockerBox();
		box.remove(cards);
		box.sortCards();
		box.adjustPockersPosition();
	}

	@Override
	public Actor getBuQiang() {
		return my.getMybuqiang();
	}

	@Override
	public Actor getQiangDiZhu() {
		return my.getMyqiangdizhu();
	}

	@Override
	public Actor getJiaoDiZhu() {
		return my.getMyjiaodizhu();
	}

	@Override
	public Actor getBuJiao() {
		return my.getMybujiao();
	}

	@Override
	public Actor getBuChu() {
		return my.getMybuchu();
	}

	@Override
	public Actor getHead() {
		return my.getMyhead();
	}

	@Override
	public TextField getMessageBox() {
		return my.getMessagebox();
	}



}
