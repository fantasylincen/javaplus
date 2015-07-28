package org.javaplus.game.common.cache;


/**
 * 远端资源库
 * 
 * @author 林岑
 * 
 */
public interface RemoteResources {

	public interface MergeFilesCallBack {
		void onDownloadOverAll();
		void onNetError();
		void onDownloadOver(int remainFileCount);
	}

	public interface RemoteResourcesCallBack {
		void onBack(ChangedFileList list);
		void onNetError();
	}

	/**
	 * 远端文件合并到本地
	 */
	void mergeFiles(MergeFilesCallBack back);

	/**
	 * 获取需要合并(发生变化的文件)的远端资源文件列表, 异步获取
	 */
	void requestRemoteResourcesHasChanged(RemoteResourcesCallBack back);

	String getString(Object fileName);

	byte[] getBytes(Object fileName);

}
