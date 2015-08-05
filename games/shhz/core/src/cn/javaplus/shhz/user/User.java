package cn.javaplus.shhz.user;

public class User {

	private Player player;

	public Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

}
