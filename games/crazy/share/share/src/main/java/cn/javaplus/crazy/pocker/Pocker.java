package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pocker {

	private static final Random R = new Random();

	private static List<Card> staticCards;

	private List<Card> cards;

	private static Map<Integer, Card> map;

	static {
		staticCards = new ArrayList<Card>();
		add(new Card(1, "3", 3));
		add(new Card(2, "3", 3));
		add(new Card(3, "3", 3));
		add(new Card(4, "3", 3));
		add(new Card(5, "4", 4));
		add(new Card(6, "4", 4));
		add(new Card(7, "4", 4));
		add(new Card(8, "4", 4));
		add(new Card(9, "5", 5));
		add(new Card(10, "5", 5));
		add(new Card(11, "5", 5));
		add(new Card(12, "5", 5));
		add(new Card(13, "6", 6));
		add(new Card(14, "6", 6));
		add(new Card(15, "6", 6));
		add(new Card(16, "6", 6));
		add(new Card(17, "7", 7));
		add(new Card(18, "7", 7));
		add(new Card(19, "7", 7));
		add(new Card(20, "7", 7));
		add(new Card(21, "8", 8));
		add(new Card(22, "8", 8));
		add(new Card(23, "8", 8));
		add(new Card(24, "8", 8));
		add(new Card(25, "9", 9));
		add(new Card(26, "9", 9));
		add(new Card(27, "9", 9));
		add(new Card(28, "9", 9));
		add(new Card(29, "10", 10));
		add(new Card(30, "10", 10));
		add(new Card(31, "10", 10));
		add(new Card(32, "10", 10));
		add(new Card(33, "J", 11));
		add(new Card(34, "J", 11));
		add(new Card(35, "J", 11));
		add(new Card(36, "J", 11));
		add(new Card(37, "Q", 12));
		add(new Card(38, "Q", 12));
		add(new Card(39, "Q", 12));
		add(new Card(40, "Q", 12));
		add(new Card(41, "K", 13));
		add(new Card(42, "K", 13));
		add(new Card(43, "K", 13));
		add(new Card(44, "K", 13));
		add(new Card(45, "A", 14));
		add(new Card(46, "A", 14));
		add(new Card(47, "A", 14));
		add(new Card(48, "A", 14));
		add(new Card(49, "2", 20));
		add(new Card(50, "2", 20));
		add(new Card(51, "2", 20));
		add(new Card(52, "2", 20));
		add(new Card(53, "S", 30));
		add(new Card(54, "S", 31));

		map = new HashMap<Integer, Card>();
		for (Card c : staticCards) {
			map.put(c.getId(), c);
		}
	}

	public List<Card> getCards() {
		return cards;
	}

	public static Card get(int id) {
		return map.get(id);
	}

	public Pocker() {
		cards = new ArrayList<Card>(staticCards);
	}

	private static void add(Card card) {
		staticCards.add(card);
	}

	public void randomCards() {
		upset(cards);
	}

	/**
	 * 打乱列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void upset(java.util.List all) {

		java.util.List ls = new ArrayList(all);

		all.clear();

		while (ls.size() > 0) {

			Object o = ls.remove(R.nextInt(ls.size()));

			all.add(o);
		}
	}

	public List<Card> peek(int count) {
		ArrayList<Card> ls = new ArrayList<Card>();
		Iterator<Card> it = cards.iterator();
		while (count > 0 && it.hasNext()) {
			Card next = it.next();
			ls.add(next);
			it.remove();
			count--;
		}
		return ls;
	}
}
