package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;

public class PockerBoxAdjust {

	public void onSendCards(List<CardP> cards, IPockerBox boxLeavHand, CardUI ui) {
		for (CardP c : cards) {
			CardActor actor = new CardActor(c, ui);
			actor.clear();
			boxLeavHand.addCard(actor);
		}
		boxLeavHand.adjustPockersPosition();
	}
}
