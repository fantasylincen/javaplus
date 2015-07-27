package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.main.CardP;
import cn.javaplus.crazy.main.MineP;
import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.main.PlayerP;

public class MinePImpl implements MineP {

	private Player player;
	private Place place;

	public MinePImpl(Player player, Table table) {
		this.player = player;
		place = table.getPlace(player);
	}

	@Override
	public List<CardP> getCards() {
		ArrayList<CardP> ls = Lists.newArrayList();
		Collection<Card> cards = place.getCards();
		for (Card card : cards) {
			ls.add(new CardPImpl(card));
		}
		return ls;
	}

	@Override
	public PlayerP getPlayer() {
		return new PlayerPImpl(player, place);
	}

}
