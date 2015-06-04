package cn.javaplus.string;

import java.util.Iterator;

public class StringIteratorImpl implements Iterator<String> {

	class Cursor {

		private int maxCount;
		private int wordLenthNow = 1;
		int index;
		
		public Cursor(int maxCount) {
			this.maxCount = maxCount;
		}

		public boolean touchEnd() {
			if(wordLenthNow == 1 && index == content.length()) {
				return false;
			}
			return true;
		}

		public String get() {

			return content.substring(index, index + wordLenthNow);
		}

		public void move() {
			wordLenthNow++;
			if(wordLenthNow > maxCount || index + wordLenthNow > content.length()) {
				index++;
				wordLenthNow = 1;
			}
			
//			if(index % 500 == 0) {
//				System.out.println("进度:" + (index + 0f) * 100 / content.length());
//			}
		}

	}

	private StringBuffer content;
	private Cursor cursor;

	public StringIteratorImpl(String content, int maxCount) {
		if(maxCount <= 0) {
			throw new RuntimeException("必须大于0");
		}
		this.content = new StringBuffer(content);
		cursor = new Cursor(maxCount);
	}

	@Override
	public boolean hasNext() {
		return cursor.touchEnd();
	}

	@Override
	public String next() {
		String s = cursor.get();
		cursor.move();
		return s;
	}

	@Override
	public void remove() {
		throw new RuntimeException("功能未开放");
	}
}
