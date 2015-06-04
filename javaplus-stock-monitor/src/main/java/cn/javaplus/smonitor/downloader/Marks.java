package cn.javaplus.smonitor.downloader;

import java.util.List;

import cn.javaplus.util.Util;

public class Marks {

	private String id;
	private List<Integer> marks;

	public Marks(String line) {
		String[] split = line.split(":");
		id = split[0];
		marks = Util.Collection.getIntegers(split[1]);
	}

	/**
	 * 标记某个, 从1开始
	 * @param mark
	 */
	public void mark(int mark) {
		int index = mark - 1;
		int value = marks.get(index);
		if (value == 0) {
			marks.set(index, 1);
		} else {
			marks.set(index, 0);
		}
	}

	public List<Integer> toList() {
		return marks;
	}

	public String getId() {
		return id;
	}

	/**
	 * 是否标记了   从1开始
	 * @param mark
	 * @return
	 */
	public boolean isMark(int mark) {
		int index = mark - 1;
		int value = marks.get(index);
		return value != 0;
	}

}
