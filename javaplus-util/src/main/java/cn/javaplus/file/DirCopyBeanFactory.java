package cn.javaplus.file;


/**
 * 文件工具工厂
 * @author 	林岑
 * @since	2012年3月29日 19:11:02
 */
public class DirCopyBeanFactory {
	
	private static CopyDir copyDirIns = new CopyDirImple();
	
	/**
	 * 获得一个文件夹拷贝工具
	 */
	public static CopyDir getCopyDir() {
		return DirCopyBeanFactory.copyDirIns;
	}
}
