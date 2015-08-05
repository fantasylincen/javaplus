package cn.vgame.a.plantform;

import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.config.ServerConfig;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.plantform.TokenChecker;

public class VcTokenChecker implements TokenChecker {

	@Override
	public void check(String userId, String token, String appId) {

		String tokenKey = getTokenKey();
		boolean tokenRight = Util.Token.isTokenRight(tokenKey, userId, token);
		if (!tokenRight) {
			throw new ErrorResult(10003).toException();
		}		
	}

	private String getTokenKey() {
		ServerConfig c = Server.getConfig();
		String key = c.getString("tokenKey");
		return key;
	}
}
