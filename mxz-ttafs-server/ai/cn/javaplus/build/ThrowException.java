package cn.javaplus.build;

import cn.javaplus.log.Log;
import cn.mxz.events.Listener;

class ThrowException implements Listener<ErrorEvent> {

	@Override
	public void onEvent(ErrorEvent e) {
		for (String er : e.getErrors()) {
			Log.e(er);
		}
		throw new CMDException();
	}
}