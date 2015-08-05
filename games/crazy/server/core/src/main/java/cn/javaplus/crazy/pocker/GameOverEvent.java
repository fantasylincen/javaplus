package cn.javaplus.crazy.pocker;

public class GameOverEvent {

	private Table table;

	public GameOverEvent(Table table) {
		this.table = table;

	}

	public Table getTable() {
		return table;
	}

}
