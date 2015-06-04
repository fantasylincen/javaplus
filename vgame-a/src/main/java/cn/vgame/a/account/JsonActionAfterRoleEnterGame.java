package cn.vgame.a.account;

import cn.vgame.a.Server;
import cn.vgame.a.result.ErrorResult;

/**
 * 当玩家进入游戏后, 的操作
 */
public abstract class JsonActionAfterRoleEnterGame extends JsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472358458699158336L;
	protected Role role;

	@Override
	public final Object exec() {
		role = Server.getRole(session);
		if (role == null)
			throw new ErrorResult(10002).toException();
		return run();
	}

	protected abstract Object run();

}