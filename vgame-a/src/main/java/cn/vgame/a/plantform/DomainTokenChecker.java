package cn.vgame.a.plantform;

import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;
import cn.vgame.a.Server;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.plantform.TokenChecker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DomainTokenChecker implements TokenChecker {

	@Override
	public void check(String userId, String token, String appId) {
		// http://sy.test.cw678.com/Services/GetAccountInfo?AccName=1111&Time=1432121212&Flag=834598349543895

		int time = getTime();

		String url = "http://fqzs.cw678.com/Services/GetAccountInfo?AccName=USER_ID&Time=TIME&Flag=FLAG";
		url = url.replaceAll("USER_ID", userId);
		url = url.replaceAll("TIME", time + "");
		url = url.replaceAll("FLAG", getFlag(userId, time));
		String content = WebContentFethcer.get("utf8", url);

		JSONObject obj = JSON.parseObject(content);
		int ret = obj.getInteger("Ret");
		if (ret == 2) {
			throw new ErrorResult(0, "Sign Error").toException();
		}
		if (ret == 3) {
			throw new ErrorResult(0, "AccName not exist").toException();
		}
		if (ret != 1) {
			throw new ErrorResult(0, "Unknown Error").toException();
		}
	}

	private String getFlag(String userId, int time) {
		String key = Server.getConfig().getString("domainKey");
		String md5 = Util.Secure.md5(userId + time + key);
		return md5.toLowerCase();
	}

	private int getTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

}
