package cn.javaplus.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.security.MD5;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util.Thread;




/**
 *
 * 文件夹照相机, 用于判定 某个文件夹下的文件是否发生变化<br>
 * 只要不引起非文件夹 文件的变化的操作, 都视为没有发生变化<br>
 *
 * <br>
 * 视为变化的操作:<br>
 * 		1.文件改名<br>
 * 		2.文件内容修改<br>
 * 		3.文件增加<br>
 * 		4.文件删除<br>
 * <br>
 * 视为无变化的操作:<br>
 * 		1.空文件夹删除<br>
 * 		2.空文件夹改名<br>
 * 		3.新建文件夹<br>
 * 		4.空文件夹改名<br>
 *
 * @author 林岑
 *
 */
public class FileCamera {


	private Map<String, String> allMD5 = new HashMap<String, String>();

	/**
	 * 文件或者文件夹路径
	 */
	private String DIR;

	private String[] except;

	/**
	 * @param DIR
	 * @param except	被排除的文件: 文件后缀. 只要检测到以except结尾的文件, 都会被忽略掉
	 */
	public FileCamera(String DIR, String... except) {

		this.except = except;

		this.DIR = DIR;

		File f = new File(DIR);

		if(!f.exists() || !f.isDirectory()) {

			throw new RuntimeException("文件夹不存在!" + DIR);
		}

		snapshot();
	}

	private void readAllMD5() {

		List<File> files = getFiles();

		for (File f : files) {

			if(!f.isDirectory()) {

				String name = f.getName();

				if(!isExcept(name)) {

					allMD5.put(f.getPath(), getMD5(f));
				}
			}
		}
	}

	/**
	 * 是否被排除
	 * @param name
	 * @return
	 */
	private boolean isExcept(String name) {

		for (String s : except) {

			if(name.endsWith(s)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 获取该文件的MD5
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String getMD5(File f) {

		FileInputStream fos = null;

		try {

			fos = new FileInputStream(f);

			int available = fos.available();

			byte [] b = new byte[available];

			fos.read(b);
//System.out.println(f);

			return MD5.getMD5(new String(b));

		} catch (Exception e) {

			e.printStackTrace();

			throw new RuntimeException(e);

		} finally {

			Closer.close(fos);
		}
	}

	public List<File> getFiles() {

		List<File> file = new ArrayList<File>();

		file.addAll(getFiles(DIR));

		return file;
	}

	private List<File> getFiles(String path) {

		List<File> files = new ArrayList<File>();

		File f = new File(path);

		String[] all = f.list();

		if(all != null) {

			for (String fname : all) {

				File fs = new File(path + "/" + fname);

				if(!isExcept(fname)) {

					if(fs.isDirectory()) {

						files.addAll(getFiles(path + "/" + fname));

					} else {

//						System.out.println("读取到的文件:" + fs);

						files.add(fs);
					}
				}
			}
		}

		return files;
	}


	public void snapshot() {

		clear();

		readAllMD5();
	}

	public boolean isChanged() {

		List<File> files = getFiles();

		if(allMD5.size() != files.size()) {

			return true;
		}

		for (File f : files) {

			if(!getMD5(f).equals(allMD5.get(f.getPath()))) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 获得发生变化的所有文件
	 *
	 * 不包含删除了的文件
	 *
	 * @return
	 */
	public List<File> getChanged() {

		List<File> files = new ArrayList<File>();

		for (File f : getFiles()) {

			if(f.exists()) {

				if(!getMD5(f).equals(allMD5.get(f.getPath()))) {

					files.add(f);
				}
			}
		}

		return files;
	}

	public static void main(String[] args) {

		FileCamera f = new FileCamera("D:/test");

		while(true) {

			if(f.isChanged()) {

				f.snapshot();

				System.out.println("文件夹发生了变化");
			}

			Thread.sleep(100);
		}
	}

	public void clear() {
		allMD5.clear();
	}

}
