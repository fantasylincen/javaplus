package cn.mxz.server.base;

import cn.mxz.server.token.TokenGenerator;

public abstract class CheckTokenHandler extends AbstractHandler {

	@Override
	protected final void handle(Response response, Parameters ps) {
		String roleId = ps.getString("roleId");
		String token = ps.getString("token");

		TokenGenerator.checkToken(roleId, token);
		handle(roleId, response, ps);
	}

	protected abstract void handle(String roleId, Response response,
			Parameters ps);

}
