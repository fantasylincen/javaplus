package cn.javaplus.crazy.pocker;

public class GameStartEvent {

	private Table table;

	public GameStartEvent(Table table) {
		this.table = table;
	}

	public Table getTable() {
		return table;
	}

}
