package cn.javaplus.crazy.enter;

import cn.javaplus.crazy.protocol.Protocols.AbstractPkAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.crazy.user.User;
import cn.javaplus.crazy.waiting.PlayerImpl;
import cn.javaplus.crazy.waiting.WaitingRoom;

public class PkAction extends AbstractPkAction {

	@Override
	protected void excute(ProtocolContext ctx) {
		WaitingRoom room = Game.getWatingRoom();
		User user = ctx.getUser();
		if (user != null) {
			PlayerImpl player = new PlayerImpl(user);
			room.add(player);
		}
	}

}
