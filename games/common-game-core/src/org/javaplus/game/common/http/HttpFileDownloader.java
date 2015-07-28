package org.javaplus.game.common.http;

import java.net.SocketException;
import java.util.Iterator;
import java.util.List;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.cache.BaiduBucketResources.Path;
import org.javaplus.game.common.http.HttpComponents.CallBackBytes;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;

import com.badlogic.gdx.files.FileHandle;

public class HttpFileDownloader {

	public class DownloadFile implements CallBackBytes {

		private Iterator<Path> it;
		private DownloaderListener listener;
		private int index;
		private Path path;

		public DownloadFile(Path path, Iterator<Path> it,
				DownloaderListener listener, int index) {
			this.path = path;
			this.it = it;
			this.listener = listener;
			this.index = index;
		}

		@Override
		public void completed(byte[] result) {
			listener.onDownloadOver(index);
			save(result);
			download(it, listener, index + 1);
		}
		
		private Loader loader = Assets.getSd();

		public void setLoader(Loader loader) {
			this.loader = loader;
		}
		
		private void save(byte[] result) {
			FileHandle file = loader.getFile(path.getDst());
			file.writeBytes(result, false);
			Log.d("download success " + path.getSrc() + " ----> "
					+ path.getDst());
		}

		@Override
		public void onTimeOut() {
			listener.onNetError();
		}

		@Override
		public void onNetError(SocketException ex) {
			listener.onNetError();
		}

		@Override
		public void httpError(String string) {
			Log.d(string);
			listener.onNetError();

		}

		@Override
		public void cancelled() {
			listener.onNetError();
		}

	}

	public class RequestImpl extends RequestAdaptor {

		private Path path;

		public RequestImpl(Path path) {
			this.path = path;
		}

		@Override
		public String getUrl() {
			return path.getSrc();
		}

	}

	private HttpClient http;

	public HttpFileDownloader(HttpClient http) {
		this.http = http;
	}

	public void download(List<Path> paths, DownloaderListener l) {

		Iterator<Path> it = paths.iterator();

		download(it, l, 0);
	}

	private void download(Iterator<Path> it, DownloaderListener l, int index) {
		if (!it.hasNext()) {
			l.onDownloadOverAll();
			return;
		}

		Path path = it.next();
		http.requestBytes(new RequestImpl(path), new DownloadFile(path, it, l,
				index));
	}

}
