package cn.javaplus.build;

import java.util.List;

public class InfoEvent {

	private List<String> infos;

	public InfoEvent(List<String> infos) {
		this.infos = infos;
	}

	public List<String> getInfos() {
		return infos;
	}
}