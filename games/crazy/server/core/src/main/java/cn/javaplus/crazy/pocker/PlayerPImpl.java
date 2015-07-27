package cn.javaplus.crazy.pocker;

import java.util.Collection;

import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.main.PlayerP;

public class PlayerPImpl implements PlayerP {

	private Player player;
	private Place place;

	public PlayerPImpl(Player player, Place place) {
		this.player = player;
		this.place = place;
	}

	@Override
	public String getNick() {
		return player.getNick();
	}

	@Override
	public boolean isDz() {
		return player.equals(place.getTable().getDz());
	}

	@Override
	public int getRemainSec() {
		return 0;
	}

	@Override
	public int getRamainCards() {
		Collection<Card> cards = place.getCards();
		return cards.size();
	}

	@Override
	public int getPlaceNumber() {
		return place.getPlaceNumber();
	}

}
