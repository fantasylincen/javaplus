package cn.javaplus.twolegs.game;

import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.TwoLegsRequest;

public class TwoLegsRequestAdaptor implements TwoLegsRequest {

	@Override
	public Parameters getParameters() {
		return new Parameters();
	}

	@Override
	public String getDomain() {
		return null;
	}

}
