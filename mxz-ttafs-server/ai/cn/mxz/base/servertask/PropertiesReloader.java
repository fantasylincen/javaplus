package cn.mxz.base.servertask;

import java.io.File;
import java.util.List;

import cn.javaplus.log.Debuger;
import cn.javaplus.util.Util;
import cn.mxz.Loader;
import define.D;

public class PropertiesReloader extends Thread {

	private String lastMd5 = "";

	@Override
	public void run() {
		while (true) {
			if (fileChanged()) {
				Loader.loadAll();
				D.reload();
				Debuger.debug("PropertiesReloader.run() 配置表重新加载");
				lastMd5 = md5LastTime();
			}

			Util.Thread.sleep(15000);
		}
	}

	private String md5LastTime() {

		List<File> all = Util.File.getFiles("res/properties");
		StringBuilder sb = new StringBuilder();
		for (File file : all) {
			long last = file.lastModified();
			sb.append(last);
		}
		return Util.Secure.md5(sb.toString());
	}

	private boolean fileChanged() {
		String md5Now = md5LastTime();
		return !md5Now.equals(lastMd5);
	}
}
