package org.hhhhhh.fqzs.result;

import java.util.List;

import org.javaplus.game.common.util.Lists;

/**
 * 每个转灯间隔毫秒
 */
public class SpaceDefine {
	private List<Integer> spaces = Lists.newArrayList();

	public SpaceDefine(String line) {
		String[] ss = line.split(",");
		for (String ll : ss) {
			spaces.add(new Integer(ll.trim()));
		}
	}

	public List<Integer> getSpaces() {
		return spaces;
	}

	public void setSpaces(List<Integer> spaces) {
		this.spaces = spaces;
	}
}
