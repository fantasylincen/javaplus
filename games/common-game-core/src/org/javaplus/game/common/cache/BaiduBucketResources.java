package org.javaplus.game.common.cache;

import java.net.SocketException;
import java.util.List;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.cache.ChangedFileList.FileUpdate;
import org.javaplus.game.common.http.DownloaderListener;
import org.javaplus.game.common.http.HttpFileDownloader;
import org.javaplus.game.common.http.RequestAdaptor;
import org.javaplus.game.common.http.HttpComponents.CallBackString;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.properties.GameProperties;
import org.javaplus.game.common.util.Lists;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.files.FileHandle;

public class BaiduBucketResources implements RemoteResources {

	public class Path {

		String src;
		String dst;

		public String getSrc() {
			return src;
		}

		public String getDst() {
			return dst;
		}
	}

	ChangedFileList list;

	public class ListFileCallBack implements CallBackString {

		private RemoteResourcesCallBack back;

		public ListFileCallBack(RemoteResourcesCallBack back) {
			this.back = back;
		}

		@Override
		public void completed(String result) {
			list = new ChangedFileList(JSON.parseObject(result));
			back.onBack(list);
		}

		@Override
		public void onTimeOut() {
			back.onNetError();
			Log.e("获取 files.json 文件出错 onTimeOut");
		}

		@Override
		public void onNetError(SocketException ex) {
			back.onNetError();
			Log.e("获取 files.json 文件出错 onNetError");
		}

		@Override
		public void httpError(String error) {
			back.onNetError();
			Log.e("获取 files.json 文件出错 onNetError");
		}

		@Override
		public void cancelled() {
			Log.e("获取 files.json 文件出错 cancelled");
			back.onNetError();
		}

	}

	private HttpClient http;

	public BaiduBucketResources(HttpClient http) {
		this.http = http;
	}

	public class DownloadFileRequest extends RequestAdaptor {

		private String fileName;

		public DownloadFileRequest(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public String getUrl() {
			String bucket = GameProperties.getString("baidu-bucket-name");
			return GameProperties.getString("bucket-host") + bucket
					+ "/scripts/" + fileName;
		}
	}

	public class FileListRequest extends RequestAdaptor {

		@Override
		public String getUrl() {
			String bucket = GameProperties.getString("baidu-bucket-name");
			return GameProperties.getString("bucket-host") + bucket
					+ "/files.json";
		}

	}

	@Override
	public String getString(Object fileName) {
		return new String(getBytes(fileName));
	}

	private Loader loader = Assets.getSd();

	public void setLoader(Loader loader) {
		this.loader = loader;
	}
	@Override
	public byte[] getBytes(Object fileName) {
		FileHandle file = loader.getFile(fileName + "");
		if (!file.exists()) {
			return null;
		}
		byte[] readBytes = file.readBytes();
		return readBytes;
	}

	@Override
	public void mergeFiles(final MergeFilesCallBack back) {
		if (this.list == null) {
			requestRemoteResourcesHasChanged(new RemoteResourcesCallBack() {

				@Override
				public void onNetError() {
					back.onNetError();
				}

				@Override
				public void onBack(ChangedFileList list) {
					BaiduBucketResources.this.list = list;
					download(back);
				}
			});
		} else {
			download(back);
		}
	}

	private void download(final MergeFilesCallBack back) {
		List<FileUpdate> all = list.getAll();
		final List<Path> paths = getPaths(all);
		new HttpFileDownloader(http).download(paths, new DownloaderListener() {
			
			@Override
			public void onDownloadOverAll() {
				back.onDownloadOverAll();
			}

			@Override
			public void onNetError() {
				back.onNetError();
			}

			@Override
			public void onDownloadOver(int fileIndex) {
				back.onDownloadOver(paths.size() - fileIndex);
			}
		});
	}

	private List<Path> getPaths(List<FileUpdate> all) {
		List<Path> ls = Lists.newArrayList();
		for (FileUpdate f : all) {
			ls.add(buildPath(f.name));
		}
		return ls;
	}

	private Path buildPath(String name) {
		String host = GameProperties.getString("bucket-host");
		String bucket = GameProperties.getString("baidu-bucket-name");
		Path p = new Path();
		p.dst = name;
		p.src = host + bucket + "/" + name;
		return p;
	}

	@Override
	public void requestRemoteResourcesHasChanged(RemoteResourcesCallBack back) {
		http.requestString(new FileListRequest(), new ListFileCallBack(back));
	}
}
