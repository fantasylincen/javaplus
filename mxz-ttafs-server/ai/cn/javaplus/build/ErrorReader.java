package cn.javaplus.build;

import java.io.InputStream;
import java.util.List;

import cn.javaplus.build.CommandProcesser.EventsManager;

class ErrorReader extends AbstractReader {

	public ErrorReader(Process exec) {
		super(exec);
	}

	@Override
	protected InputStream getStream() {
		return exec.getErrorStream();
	}

	@Override
	protected void dispatchEvent(List<String> ls) {
		if (ls.isEmpty()) {
			return;
		}
		EventsManager.getInstance().dispatchEvent(new ErrorEvent(ls));
	}

}