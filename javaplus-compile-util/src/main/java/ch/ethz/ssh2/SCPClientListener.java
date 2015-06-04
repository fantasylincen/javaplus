package ch.ethz.ssh2;

public interface SCPClientListener {

	void onSendFile(SendFileEvent e);

}
