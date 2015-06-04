package cn.javaplus.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinesReader implements Iterable<String>, Closeable {

	private BufferedReader reader;

	private final class IteratorImplementation implements Iterator<String> {

		private String next;

		public IteratorImplementation() {
			reader = new BufferedReader(new InputStreamReader(input));
		}

		@Override
		public boolean hasNext() {
			try {
				next = reader.readLine();
			} catch (IOException e) {
				return false;
			}
			return next != null;
		}

		@Override
		public String next() {
			if (next == null) {
				try {
					next = reader.readLine();
				} catch (IOException e) {
					throw new NoSuchElementException();
				}
				
				if (next == null) {
					throw new NoSuchElementException();
				}
			}

			return next;
		}

		@Override
		public void remove() {
			throw new RuntimeException("unimplement method");
		}
	}

	private InputStream input;

	public LinesReader(InputStream input) {
		this.input = input;
	}

	@Override
	public Iterator<String> iterator() {
		return new IteratorImplementation();
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
