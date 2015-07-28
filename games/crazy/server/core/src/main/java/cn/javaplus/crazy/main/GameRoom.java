package cn.javaplus.crazy.main;

import java.util.Collection;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.log.Log;

public class GameRoom {

	private Map<String, Table> tables;
	private Timer timer = new Timer();

	public GameRoom() {
		tables = new ConcurrentHashMap<String, Table>();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				notifyVetorsTimeUpdate();
			}
		}, 1000);
	}

	protected void notifyVetorsTimeUpdate() {
		Collection<Table> all = tables.values();
		for (Table table : all) {
			try {
				table.onTimeUpdate();
			} catch (Exception e) {
				Log.e(e);
			}
		}
	}

	public void add(Table table) {
		add(table.getA());
		add(table.getB());
		add(table.getC());
	}

	private void add(Place c) {
		Player p = c.getPlayer();
		tables.put(p.getId(), c.getTable());
	}

	public Table getTable(Player player) {
		return getTable(player.getId());
	}

	public Table getTable(String id) {
		return tables.get(id);
	}

}
