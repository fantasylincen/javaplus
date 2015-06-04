package cn.javaplus.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.javaplus.collections.list.Lists;

public class FileScannerImpl implements FileScanner {

	private File	file;

	public FileScannerImpl(String path) {

		file = new File(path);

		if(!file.exists()) {

			throw new IllegalArgumentException("文件不存在:" + path);
		}
	}

	@Override
	public Iterable<File> iterator() {

		List<File> ls = Lists.newArrayList();

		try {

			addToList(this.file, ls);

		} catch (IOException e) {

			throw new RuntimeException(e);
		}

		return ls;
	}

	private void addToList(File f, List<File> ls) throws IOException {

		if(f.isDirectory()) {

			String[] list = f.list();

			for (String file : list) {

				String p = f.getCanonicalPath() + "/" + file;

				addToList(new File(p), ls);
			}

		} else {

			ls.add(f);
		}
	}
}
