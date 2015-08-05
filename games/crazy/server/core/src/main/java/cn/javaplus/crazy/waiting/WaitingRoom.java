package cn.javaplus.crazy.waiting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.main.GameRoom;
import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.log.Log;

public class WaitingRoom {

	Map<String, Player> users = new ConcurrentHashMap<String, Player>();

	public void add(Player player) {
		Table table = Game.getGameRoom().getTable(player);
		if (table != null) {
			table.reEnter(player);
		} else {
			this.users.put(player.getId(), player);
			createTableToStartGame();
		}
	}

	private void createTableToStartGame() {
		if (users.size() >= 3) {
			List<Player> players = pollPlayers(3);
			createTableToStartGame(players);
		} else {
			Log.d("wait players... player count:" + users.size());
		}
	}

	private List<Player> pollPlayers(int count) {
		ArrayList<Player> ls = Lists.newArrayList(users.values());
		List<Player> players = ls.subList(0, count);
		for (Player player : players) {
			users.remove(player.getId());
		}
		return players;
	}

	private void createTableToStartGame(List<Player> players) {
		GameRoom room = Game.getGameRoom();
		Table table = new Table();
		table.sitDown(players);
		room.add(table);
		table.start();
	}
}
