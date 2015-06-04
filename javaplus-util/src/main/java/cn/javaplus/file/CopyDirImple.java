package cn.javaplus.file;

import java.io.File;

/**
 * 完成文件夹结构的复制
 * @author 	唐青
 * @time	2012年3月29日 19:05:19
 */
class CopyDirImple implements CopyDir{

	/**
	 * 采用递归算法对源路径进行遍历
	 * 同时在目标路径下创建文件夹
	 * @param srcDirName		源路径
	 * @param resDirName		目标路径
	 */
	@Override
	public void copyEmptyDir(String srcDirName, String resDirName) {
		File sf = new File(srcDirName);
		visitFile(sf.getPath() + "/",resDirName);
	}

	/**
	 * 递归遍历目录树
	 * @param mainPath 源路径
	 * @param path 生成路径
	 */
	public void visitFile(String mainPath, String path) {

		// 递归遍历目录树
		File smbFile = new File(mainPath);
		path=path+"/"+smbFile.getName();
		File newfile=new File(path);
		newfile.mkdirs();
		File[] filename = smbFile.listFiles();// 得到该目录下文件列表
		if (filename != null) {
			for (int i = 0; i < filename.length; i++) {
				if (filename[i].isDirectory()) {// 是目录
					visitFile(filename[i].getPath() + "/",path);// 递归遍历目录树
//				}else{//是文件
//					//新建文件
//					try {
//						File file=new File(path+"/"+filename[i].getName());
//						file.createNewFile();
//						FileInputStream fis=new FileInputStream(filename[i]);
//						FileOutputStream fos=new FileOutputStream(file);
//						byte []temp=new byte[1024];
//						int n=0;
//						while((n=fis.read(temp))!=-1){
//							fos.write(temp,0,n);
//							fos.flush();
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				}
			}
		}
	}
//	/**
//	 * 测试
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		CopyDirImple cdi=new CopyDirImple();
//		cdi.copyEmptyDir("F:/临时", "e:/");
//	}
}
