package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.crazy.main.CardP;
import cn.javaplus.crazy.main.CpP;

import com.google.common.collect.Lists;

public class CpImpl implements CpP {

	private Place place;
	private List<Card> cards;

	public CpImpl(Place place, List<Card> cards) {
		this.place = place;
		this.cards = cards;
	}

	@Override
	public List<CardP> getCards() {
		ArrayList<CardP> ls = Lists.newArrayList();
		for (Card c : cards) {
			ls.add(new CardPImpl(c));
		}
		return ls;
	}

	@Override
	public int getPlaceNumber() {
		return place.getPlaceNumber();
	}

	@Override
	public int getRemainCards() {
		return place.getCardsCount();
	}

}
