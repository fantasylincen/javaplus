package cn.javaplus.crazy.enter;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.exception.ServerException;
import cn.javaplus.crazy.protocol.Protocols.AbstractEnterAction;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;
import cn.javaplus.crazy.role.RoleData;
import cn.javaplus.crazy.token.TokenGenerator;
import cn.javaplus.crazy.token.UserDataBuilder;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.crazy.user.User;
import cn.javaplus.crazy.user.UserEnterEvent;

public class EnterAction extends AbstractEnterAction {

	@Override
	protected EnterResultP excute(ProtocolContext ctx, String uname,
			String token, String roleData) {

		uname = uname.toLowerCase();

		if (!TokenGenerator.checkToken(uname, token)) {
			throw new ServerException("token error");
		}

		RoleData d = new UserDataBuilder().resoleve(roleData);

		Channel channel = ctx.getChannel();
		User user = new User(d, channel);
		int id = channel.getId();

		Game.getWorld().onEnter(id, user);
		Events.dispatch(new UserEnterEvent(user, id));

		return new EnterResultPtl(d.getRoleId(), d.getUname());
	}

}
