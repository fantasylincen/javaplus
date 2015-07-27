package cn.javaplus.crazy.pocker;

import cn.javaplus.crazy.main.MineP;
import cn.javaplus.crazy.main.OtherP;
import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.main.TableP;

public class TablePImpl implements TableP {

	private Table table;
	private Player me;

	public TablePImpl(Player me, Table table) {
		this.me = me;
		this.table = table;
	}

	@Override
	public OtherP getLeft() {
		return new OtherPImpl(table.getLeftPlayer(me), table);
	}

	@Override
	public MineP getMine() {
		return new MinePImpl(me, table);
	}

	@Override
	public OtherP getRight() {
		return new OtherPImpl(table.getRightPlayer(me), table);
	}

}
