package cn.javaplus.twolegs.share;

import org.javaplus.game.common.Share;

import cn.javaplus.twolegs.App;

public class ShareWindows implements Share {

	@Override
	public void share() {
		String s = App.getCache().getString("testshare.js");
//		App.getScriptManager().run(s, "run");
	}

	@Override
	public void init() {
		
	}

}
