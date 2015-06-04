package cn.javaplus.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * 文件存取器, 可从文件加载一个对象, 也可以将一个指定类型的对象写入至文件
 * @author 	林岑
 * @since	2012年6月19日 10:34:42
 * @param <T>
 */
public class FileLoader<T> {

	private final File file;
	
	/**
	 * 新建一个文件存取器, 这个文件存取器将根据path关联到指定路径, 如果该路径不存在, 那么他将会创建一个新的文件
	 * @param path	文件路径
	 */
	public FileLoader(String path) {
		file = new File(path);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从该文件存取器绑定的文件中读取对象
	 * 如果在从未写入的情况下试图读取, 那么必将返回null
	 * @return
	 */
	public T load() {
		ObjectInputStream in = null;
		try {

			in = new ObjectInputStream  (new FileInputStream(file));

			@SuppressWarnings("unchecked")
			T lis = (T) in.readObject();
			return lis;
			
		} catch (Exception e) {
			return null;
		} finally {
			if(in != null) {
				try { in.close(); } catch (IOException e) { }
			}
		}
	}

	/**
	 * 将指定对象写入文件
	 * @param o
	 */
	public void write(T o) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream (new FileOutputStream(file));
			out.writeObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null ) {
				try { out.close(); } catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		FileLoader<Integer> f = new FileLoader<Integer>("resource/data/test.obj");
//		f.write(1);
//		f.write(2);
//		f.write(2);
//		f.write(2);
		System.out.println(f.load());
		System.out.println(f.load());
		System.out.println(f.load());
		System.out.println(f.load());
		System.out.println(f.load());
	}
}
