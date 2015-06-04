package cn.javaplus.build.deploy;

import cn.javaplus.util.Util;

public class Deploy {

	public void deploy(final DeployRule rule) {
		Runnable runnable = new Runnable() {
			public void run() {
				rule.pack();
				rule.upload();
				Util.Thread.sleep(2000);
				rule.stop();
				Util.Thread.sleep(2000);
				rule.start();
			}
		};
		new Thread(runnable).start();
	}
}
