package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.App;
import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.Protocols.PlayHandlerListeners.AbstractStartPlayPockerCallBack;
import cn.javaplus.crazy.Protocols.PlayHandlerStartPlayPockerEvent;
import cn.javaplus.crazy.Protocols.StartPlayP;
import cn.javaplus.crazy.R.Uis.GameUI;
import cn.javaplus.crazy.R.Uis.GameUI.Panel;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Spockerui;
import cn.javaplus.crazy.stage.IStage;
import cn.javaplus.crazy.util.Bound;

import com.badlogic.gdx.scenes.scene2d.Group;

public class StarPlayPockerCallBack implements AbstractStartPlayPockerCallBack {

	/**
		 * 
		 */
	private final GameStage stage;
	private SmallUI smallUI;

	/**
	 * @param gameStage
	 */
	StarPlayPockerCallBack(GameStage gameStage) {
		stage = gameStage;
		GameUI gameUI = stage.getGameUI();
		Panel panel = gameUI.getPanel();

		Spockerui s = panel.getSpockerui();
		smallUI = new SmallUI(s);
	}

//	private class MoveAction extends ParallelAction {
//
//		public MoveAction(Rect to) {
//			float duration = 0.3f;
//			MoveToAction a = new MoveToAction();
//			a.setPosition(to.getX(), to.getY());
//			a.setDuration(duration);
//			this.addAction(a);
//
//			SizeToAction action = new SizeToAction();
//			action.setSize(to.getW(), to.getH());
//			action.setDuration(duration);
//			this.addAction(action);
//
//		}
//
//	}

	@Override
	public void completed(PlayHandlerStartPlayPockerEvent e) {
		StartPlayP back = e.getBack();
		List<CardP> cards = back.getDzCards();

		IStage s = App.AppContext.getStage();
		if (s instanceof GameStage) {
			GameStage stage = (GameStage) s;
			showDzCards(cards, stage);
			showMyPockerAddAction(e, stage);
		}
	}

	private void showMyPockerAddAction(PlayHandlerStartPlayPockerEvent e,
			GameStage stage) {
		// StartPlayP back = e.getBack();
		// int nb = back.getDzPlaceNumber();
		// MinePlayer mine = stage.getMine();
		// if (mine.getPlaceNumber() == nb) {
		// for (CardP c : back.getDzCards()) {
		// mine.getPockerBox().addCard(new CardActor(c));
		// }
		// mine.getPockerBox().adjustPockersPosition();
		// }
	}

	private void showDzCards(List<CardP> cards, GameStage stage) {
		CardP a = cards.get(0);
		CardP b = cards.get(1);
		CardP c = cards.get(2);

		Spockerui ui = stage.gameUI.getPanel().getSpockerui();

		add(a, ui, stage, 0);
		add(b, ui, stage, 20);
		add(c, ui, stage, 40);
	}

	private void add(CardP c, Spockerui spockerui, GameStage ss, int dx) {
		Group m = spockerui.toGroup();

		CardActor actor = new CardActor(c, smallUI);
		new Bound(m).set(actor);
		actor.setX(m.getX() + dx);
		stage.addActor(actor);
	}

}