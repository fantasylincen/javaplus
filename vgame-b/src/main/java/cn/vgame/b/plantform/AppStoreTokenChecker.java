package cn.vgame.b.plantform;

import cn.vgame.b.result.ErrorResult;
import cn.vgame.plantform.TokenChecker;

public class AppStoreTokenChecker implements TokenChecker {

	@Override
	public void check(String userId, String token, String appId) {
		if(userId == null || userId.isEmpty()) {
			throw new ErrorResult("userId error").toException();
		}
	}

}
