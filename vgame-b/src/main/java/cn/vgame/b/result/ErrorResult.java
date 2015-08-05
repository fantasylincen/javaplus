package cn.vgame.b.result;

import cn.vgame.b.Server;
import cn.vgame.result.ErrorResultBase;
import cn.vgame.share.Xml;

public class ErrorResult extends ErrorResultBase {

	public ErrorResult(String error) {
		super(error);
	}

	public ErrorResult(int code, Object... args) {
		super(code, args);
	}

	@Override
	protected Xml getXml() {
		return Server.getXml();
	}
}