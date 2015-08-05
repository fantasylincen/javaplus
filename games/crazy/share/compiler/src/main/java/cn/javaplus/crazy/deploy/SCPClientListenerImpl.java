package cn.javaplus.crazy.deploy;

import ch.ethz.ssh2.SCPClientListener;
import ch.ethz.ssh2.SendFileEvent;
import cn.javaplus.log.Log;

final class SCPClientListenerImpl implements SCPClientListener {
	private int a = 1;

	@Override
	public void onSendFile(SendFileEvent e) {
		if (a % 100 == 0) {
			Log.d("upload crazy-gate.jar " + (int) (e.getPercent() * 100)
					+ "%");
		}
		a++;
	}
}