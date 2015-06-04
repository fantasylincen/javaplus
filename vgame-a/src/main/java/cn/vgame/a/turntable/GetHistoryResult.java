package cn.vgame.a.turntable;

import java.util.List;

import cn.javaplus.collections.list.Lists;

public class GetHistoryResult {

	private List<History> history;

	public List<History> getHistory() {
		if(history == null)
			history = Lists.newArrayList();
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}
}