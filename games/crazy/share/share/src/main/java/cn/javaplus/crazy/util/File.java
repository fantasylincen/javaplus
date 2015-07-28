package cn.javaplus.crazy.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.exception.FileNotFoundRuntimeException;
import cn.javaplus.exception.IORuntimeException;
import cn.javaplus.file.FileLinesReader;
import cn.javaplus.log.Debuger;
import cn.javaplus.util.TextFileContentIterator;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class File {
	
	
	/**
	 * 获取文件, 可能获取的是一个不存在的文件
	 * 
	 * @param dir
	 *            目录名字
	 * @param f
	 *            目录下的文件名
	 * @return
	 */
	public static java.io.File getFile(java.io.File dir, String f) {
		try {
			String canonicalPath = dir.getCanonicalPath();
			if (!canonicalPath.endsWith(java.io.File.separator)) {
				canonicalPath += java.io.File.separator;
			}
			f = canonicalPath + f;
			java.io.File file = new java.io.File(f);
			return file;
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
	
	/**
	 * 获得文本文件内容
	 * 
	 * @param file
	 * @return
	 */
	public static String getContent(java.io.File file) {
	
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(
					new java.io.FileReader(file));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundRuntimeException(e);
		} finally {
			Closer.close(bufferedReader);
		}
	
	}
	
	private static void read(StringBuffer sb, BufferedReader bufferedReader) {
		while (true) {
			String line;
			try {
				line = bufferedReader.readLine();
	
				if (line == null) {
					break;
				}
				sb.append(line);
				sb.append("\r");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 获得指定路径的文件内容
	 * 
	 * @param path
	 * @return
	 */
	public static String getContent(String path) {
		return getContent(new java.io.File(path));
	}
	
	/**
	 * 获得某个文件夹下, 所有的文件
	 * 
	 * @param path
	 *            文件夹路径
	 * @return
	 */
	public static List<java.io.File> getFiles(String path) {
		List<java.io.File> files = Lists.newArrayList();
		java.io.File f = new java.io.File(path);
		String[] all = f.list();
	
		if (all != null) {
			for (String fname : all) {
				java.io.File fs = new java.io.File(path + "/" + fname);
				if (fs.isDirectory()) {
					files.addAll(getFiles(path + "/" + fname));
				} else {
					files.add(fs);
				}
			}
		}
		return files;
	}
	
	/**
	 * 获得某个文件夹下, 所有的文件
	 * 
	 * @param dirPath
	 *            文件夹路径
	 * @param except
	 *            排除的文件後綴列表
	 * @return
	 */
	public static List<java.io.File> getFiles(String dirPath,
			String... except) {
	
		List<java.io.File> files = getFiles(dirPath);
		Iterator<java.io.File> it = files.iterator();
		while (it.hasNext()) {
			java.io.File file = it.next();
			String name = file.getName();
			if (isExcept(name, except)) {
				it.remove();
			}
		}
		return files;
	}
	
	/**
	 * 是否被排除
	 * 
	 * @param name
	 * @return
	 */
	private static boolean isExcept(String name, String... except) {
	
		for (String s : except) {
	
			if (name.endsWith(s)) {
	
				return true;
			}
		}
	
		return false;
	}
	
	/**
	 * 将content以文本的方式, 写入到dst文件中. 强制覆盖
	 * 
	 * @param dst
	 * @param content
	 */
	public static void write(String file, String content) {
		OutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			java.io.File f = new java.io.File(file);
			Debuger.debug("Util.File.write()" + f.getCanonicalPath());
			if (!f.exists()) {
				int lastIndexOf = file.lastIndexOf("/");
				if (lastIndexOf == -1) {
					lastIndexOf = file.lastIndexOf("\\");
				}
				java.io.File path = new java.io.File(file.substring(0,
						lastIndexOf));
				path.mkdirs();
				f.createNewFile();
			}
	
			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();
	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Closer.close(fos);
		}
	}
	
	public static void write(java.io.File file, String content) {
		try {
			write(file.getCanonicalPath(), content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获得某个文件中所有的行
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> getLines(String filePath) {
		java.io.File file;
		try {
			file = new java.io.File(filePath);
		} catch (java.lang.Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	
		return getLines(file);
	}
	
	public static List<String> getLines(java.io.File file) {
		FileLinesReader f;
		try {
			f = new FileLinesReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw Util.Exception.toRuntimeException(e);
		}
		return f.readLines();
	}
	
	public static String getContent(URL r) {
		InputStream s = null;
		BufferedReader bufferedReader = null;
		try {
			s = r.openStream();
	
			bufferedReader = new BufferedReader(new InputStreamReader(s));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();
	
		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(s);
			Closer.close(bufferedReader);
		}
	}
	
	public static List<String> getLines(URL resource) {
		FileLinesReader f;
		InputStream input = null;
		try {
	
			input = resource.openStream();
	
			f = new FileLinesReader(new InputStreamReader(input));
	
			return f.readLines();
	
		} catch (java.lang.Exception e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(input);
		}
	}
	
	/**
	 * 遍历一个文件夹中, 所有文本文件中的文本
	 * 
	 * @param dir
	 * @return
	 */
	public static Iterator<String> buildContentIterator(URL dir) {
	
		String file = dir.getFile();
		java.io.File f = new java.io.File(file);
		final java.io.File[] files = f.listFiles();
	
		return new TextFileContentIterator(files);
	}
	
	/**
	 * 遍历某个目录下所有文件
	 * 
	 * @param path
	 * @return
	 */
	public static Iterator<java.io.File> getFilesIterator(String path) {
		List<java.io.File> all = getFiles(path);
		return all.iterator();
	}
	
	public static List<java.io.File> getFiles(URL resource) {
		String file = resource.getFile();
		java.io.File f = new java.io.File(file);
		return Lists.newArrayList(f.listFiles());
	}
	
	/**
	 * 后缀名 返回值不包含"."
	 * 
	 * @param f
	 * @return
	 */
	public static String getSuffix(java.io.File f) {
		String name = f.getName();
		int l = name.lastIndexOf(".");
		name = name.substring(l + 1, name.length());
		return name;
	}
	
	
}

