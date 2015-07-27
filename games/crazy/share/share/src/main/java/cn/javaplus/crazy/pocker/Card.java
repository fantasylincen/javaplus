package cn.javaplus.crazy.pocker;

public class Card implements Comparable<Card> {

	private int id;
	private String text;
	private int comparator;

	public Card(int id, String text, int comparator) {
		this.id = id;
		this.text = text;
		this.comparator = comparator;
	}

	public int getValue() {
		return comparator;
	}

	public int getComparator() {
		return comparator;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Card c) {
		return c.comparator - comparator;
	}

	public String getValueText() {
		return text;
	}

	public boolean isBlack() {
		return id % 2 == 0;
	}
	
	/** 
	 * 0 1 2 3
	 * @return h hong m fang
	 */
	public int getColor() {
		return id % 4;
	}

	@Override
	public String toString() {
		if (getValue() == 8) {
			return "G";
		}
		if (getValue() == 9) {
			return "O";
		}
		if (getValue() == 10) {
			return "K";
		}
		if (getValue() == 11) {
			return "V";
		}
		return getValueText();
	}

}
