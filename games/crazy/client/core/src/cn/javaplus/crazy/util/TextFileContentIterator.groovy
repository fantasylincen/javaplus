package cn.javaplus.crazy.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.exception.UnImplMethodException;

public class TextFileContentIterator implements Iterator<String> {

	List<File> files;
	private Iterator<File> iterator;

	/**
	 * 文本文件内容迭代器,
	 * 
	 * @param files
	 *            如果其中有 文件夹, 那么文件夹会被忽略掉
	 */
	public TextFileContentIterator(java.io.File[] files) {
		this.files = Lists.newArrayList(files);
		removeDirs();
	}

	private void removeDirs() {
		Iterator<File> it = files.iterator();
		while (it.hasNext()) {
			File file = it.next();
			if (file.isDirectory()) {
				it.remove();
			}
		}

		this.iterator = files.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public String next() {
		return Util.File.getContent(iterator.next());
	}

	@Override
	public void remove() {
		throw new UnImplMethodException();
	}

}
