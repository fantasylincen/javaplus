package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.main.JdzManager;
import cn.javaplus.crazy.main.Player;

public class Place {

	private Player player;
	private Table table;
	private Map<Integer, Card> cards;
	private int number;

	public Place(Table table, int number) {
		this.table = table;
		this.number = number;
		cards = new HashMap<Integer, Card>();
	}

	public Table getTable() {
		return table;
	}

	public void sitDown(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isSitDown(Player player) {
		return player.equals(this.player);
	}

	public boolean isSitDown(String id) {
		return id.equals(player.getId());
	}

	public int getCardsCount() {
		return getCards().size();
	}

	public Collection<Card> getCards() {
		return cards.values();
	}

	public void addCard(Card card) {
		cards.put(card.getId(), card);
	}

	public int getPlaceNumber() {
		return number;
	}

	public void reset() {
		cards.clear();
	}

	/**
	 * @param isSayDz
	 * @param isJdzing
	 *            正在jdz? 否则 正在qdz
	 */
	public void sayDz(boolean isSayDz, boolean isJdzing) {
		table.checkCurrent(this);
		JdzManager manager = table.getJdzManager();
		manager.add(new JdzBean(isSayDz, this, isJdzing));
	}

	public void toBeDz() {
		table.setDz(this);
		table.setCurrent(this);
		Events.dispatch(new ToBeDzEvent(this));
	}

	public void addCards(List<Card> dzCards) {
		for (Card card : dzCards) {
			addCard(card);
		}
	}

	public void cp(List<Integer> ids) {
		Events.dispatch(new BeforeCpEvent(ids, this));
		List<Card> cards = removeCards(ids);
		int n = getPlaceNumber();
		UserSendCards cs = new UserSendCards(cards, n);
		table.setUserSendCards(cs);

		table.sendCpMessage(this, cards);

		if (getCardsCount() == 0) {
			Events.dispatch(new GameOverEvent(table));
		} else {
			table.next();
			table.sendTip(table.getCurrent(), D.WAIT_CP);
		}
	}

	private List<Card> removeCards(List<Integer> ids) {
		ArrayList<Card> ls = Lists.newArrayList();
		for (Integer id : ids) {
			Card c = cards.remove(id);
			ls.add(c);
		}
		return ls;
	}

	public void checkCurrent() {
		getTable().checkCurrent(this);
	}

	public void pass() {
		table.next();
		table.sendTip(table.getCurrent(), D.WAIT_CP);	
	}
}
