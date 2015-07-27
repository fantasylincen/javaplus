package cn.javaplus.crazy.pocker;

public class GameRestartEvent {

	private Table table;

	public GameRestartEvent(Table table) {
		this.table = table;
	}

	public Table getTable() {
		return table;
	}

}
