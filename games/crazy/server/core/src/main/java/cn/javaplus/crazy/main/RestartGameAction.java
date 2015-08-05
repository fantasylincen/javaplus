package cn.javaplus.crazy.main;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.crazy.protocol.Protocols.AbstractRestartGameAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;
import cn.javaplus.crazy.user.Game;

public class RestartGameAction extends AbstractRestartGameAction {

	@Override
	protected void excute(ProtocolContext ctx) {
		if (!D.RESTART_GAME_ENABLE) {
			return;
		}
		String id = ctx.getUser().getId();
		GameRoom room = Game.getGameRoom();
		Table table = room.getTable(id);
		table.restartGame();
	}

}
