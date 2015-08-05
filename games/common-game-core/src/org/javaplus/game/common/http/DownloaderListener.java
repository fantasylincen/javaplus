package org.javaplus.game.common.http;

public interface DownloaderListener {

	void onDownloadOver(int fileIndex);

	void onDownloadOverAll();

	void onNetError();

}
