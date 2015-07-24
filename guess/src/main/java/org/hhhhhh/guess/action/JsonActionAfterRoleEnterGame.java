package org.hhhhhh.guess.action;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.user.User;

/**
 * 当玩家进入游戏后, 的操作
 */
public abstract class JsonActionAfterRoleEnterGame extends JsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472358458699158336L;
	protected User user;

	@Override
	public final Object exec() {
		user = Server.getUser(session);
		if (user == null)
			throw new GuessException("请先登录");
		return run();
	}

	protected abstract Object run();

}
