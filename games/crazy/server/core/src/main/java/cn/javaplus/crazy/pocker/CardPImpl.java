package cn.javaplus.crazy.pocker;

import cn.javaplus.crazy.main.CardP;

public class CardPImpl implements CardP {

	private Card card;

	public CardPImpl(Card card) {
		this.card = card;
	}

	@Override
	public int getId() {
		return card.getId();
	}

}
