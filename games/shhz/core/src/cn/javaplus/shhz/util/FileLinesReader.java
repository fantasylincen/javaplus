package cn.javaplus.shhz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.shhz.collections.Lists;

/**
 * 文件行读取器, 可以读取一个文件所有行, 返回一个列表
 * 
 * 如果你将其作为迭代器使用, 那么最后一个元素将是一个null, 这一点请注意.
 * 
 * @author 林岑
 * 
 */
public class FileLinesReader implements Iterable<String> {

	public class FileLinesReaderIterator implements Iterator<String> {

		private boolean isEnd;

		private BufferedReader br = null;

		public FileLinesReaderIterator() {

			try {

				br = new BufferedReader(reader);

			} catch (Exception e) {

				throw new RuntimeException(e);
			}

		}

		@Override
		public boolean hasNext() {

			return !isEnd;
		}

		@Override
		public String next() {

			try {

				String readLine = br.readLine();

				if (readLine == null) {

					isEnd = true;

					Closer.close(br);
				}

				return readLine;

			} catch (IOException e) {

				Closer.close(br);

				throw new RuntimeException(e);
			}
		}

		@Override
		public void remove() {

			throw new RuntimeException("不支持的功能!");
		}

	}

	private Reader reader;

	public FileLinesReader(Reader reader) {
		this.reader = reader;
	}

	public List<String> readLines() {

		List<String> newArrayList = Lists.newArrayList();

		BufferedReader br = null;

		try {

			br = new BufferedReader(reader);

			read(br, newArrayList);

		} catch (Exception e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(br);
		}

		return newArrayList;
	}

	private void read(BufferedReader br, List<String> newArrayList)
			throws IOException {

		while (true) {

			String line = br.readLine();

			if (line == null) {

				break;
			}

			newArrayList.add(line);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new FileLinesReaderIterator();
	}
}
