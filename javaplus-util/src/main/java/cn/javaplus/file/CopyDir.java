package cn.javaplus.file;

public interface CopyDir {
	
	/**
	 * 将源目录及其内部 的目录结构拷贝到目标目录中, 前提是srcDirName是一个文件夹
	 * @param srcDirName		源路径
	 * @param resDirName		目标路径
	 */
	public abstract void copyEmptyDir(String srcDirName, String resDirName);
}