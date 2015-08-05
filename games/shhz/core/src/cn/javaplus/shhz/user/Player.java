package cn.javaplus.shhz.user;

public class Player {

	private Value gold;

	public Player() {
		gold = new GoldValue();
	}

	public Value getGold() {
		return gold;
	}

}
