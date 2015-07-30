package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.core.App;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;

public class GetResultRequest implements Request {

	@Override
	public String getUrl() {
		String path = "turntable/getPlayResult";
		return buildFullPath(path);
	}

	private String buildFullPath(String path) {
		GameConfig config = App.getConfig();
		String rootPath = config.getRootPath();
		String url = rootPath + path;
		return url;
	}

	@Override
	public Parameters getParameters() {
		return new Parameters();
	}

}
