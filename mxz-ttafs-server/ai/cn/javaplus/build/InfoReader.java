package cn.javaplus.build;

import java.io.InputStream;
import java.util.List;

import cn.javaplus.build.CommandProcesser.EventsManager;

class InfoReader extends AbstractReader {

	public InfoReader(Process exec) {
		super(exec);
	}

	@Override
	protected InputStream getStream() {
		return exec.getInputStream();
	}

	@Override
	protected void dispatchEvent(List<String> ls) {
		EventsManager.getInstance().dispatchEvent(new InfoEvent(ls));
	}

}