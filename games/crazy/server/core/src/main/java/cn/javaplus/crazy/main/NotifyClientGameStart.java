package cn.javaplus.crazy.main;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.pocker.GameStartEvent;
import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.crazy.pocker.TablePImpl;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.protocol.Protocols.PlayHandler;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.log.Log;

public class NotifyClientGameStart implements Listener<GameStartEvent> {

	private void gameStart(Place p) {
		Player player = p.getPlayer();
		Channel channel = player.getChannel();
		if (channel != null) {
			PlayHandler h = Game.getProtocols().getPlayHandler();
			h.gameStart(channel, new TablePImpl(player, p.getTable()));
			Log.d("NotifyClientGameStart.gameStart ", player.getId());
		}
	}

	@Override
	public void onEvent(GameStartEvent e) {
		Table table = e.getTable();
		gameStart(table.getA());
		gameStart(table.getB());
		gameStart(table.getC());

		table.sendTip(table.getCurrent(), D.WAIT_JDZ);
	}
}
