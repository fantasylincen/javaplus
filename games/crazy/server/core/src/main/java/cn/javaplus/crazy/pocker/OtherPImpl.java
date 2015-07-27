package cn.javaplus.crazy.pocker;

import cn.javaplus.crazy.main.OtherP;
import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.main.PlayerP;

public class OtherPImpl implements OtherP {

	private Player player;
	private Table table;

	public OtherPImpl(Player player, Table table) {
		this.player = player;
		this.table = table;
	}

	@Override
	public PlayerP getPlayer() {
		return new PlayerPImpl(player, table.getPlace(player));
	}

}
