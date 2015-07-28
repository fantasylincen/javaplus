package cn.javaplus.crazy.pocker;

import java.util.List;

public class UserSendCards {

	private List<Card> cards;
	private int placeNumber;

	public UserSendCards(List<Card> cards, int placeNumber) {
		this.cards = cards;
		this.placeNumber = placeNumber;
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

}
